#pragma once
#include <glad/glad.h>
#include <png.h>
#include <stdio.h>
#include <string>
#include <vector>
#include <unordered_map>
#include <cstdint>

// Loads a PNG from romfs:/ path and returns OpenGL texture ID
namespace texload {

static GLuint loadPNG(const char* path) {
    FILE* fp = fopen(path, "rb");
    if (!fp) {
        printf("[TEX] Could not open: %s\n", path);
        return 0;
    }

    // Verify PNG signature
    uint8_t sig[8];
    fread(sig, 1, 8, fp);
    if (png_sig_cmp(sig, 0, 8)) {
        printf("[TEX] Not a PNG: %s\n", path);
        fclose(fp);
        return 0;
    }

    png_structp png = png_create_read_struct(PNG_LIBPNG_VER_STRING, nullptr, nullptr, nullptr);
    if (!png) { fclose(fp); return 0; }
    png_infop info = png_create_info_struct(png);
    if (!info) { png_destroy_read_struct(&png, nullptr, nullptr); fclose(fp); return 0; }

    if (setjmp(png_jmpbuf(png))) {
        printf("[TEX] PNG decode error: %s\n", path);
        png_destroy_read_struct(&png, &info, nullptr);
        fclose(fp);
        return 0;
    }

    png_init_io(png, fp);
    png_set_sig_bytes(png, 8); // we already read 8 bytes
    png_read_info(png, info);

    int width  = (int)png_get_image_width(png, info);
    int height = (int)png_get_image_height(png, info);
    png_byte color_type = png_get_color_type(png, info);
    png_byte bit_depth  = png_get_bit_depth(png, info);

    // Normalise everything to RGBA8
    if (bit_depth == 16)                                                        png_set_strip_16(png);
    if (color_type == PNG_COLOR_TYPE_PALETTE)                                   png_set_palette_to_rgb(png);
    if (color_type == PNG_COLOR_TYPE_GRAY && bit_depth < 8)                     png_set_expand_gray_1_2_4_to_8(png);
    if (png_get_valid(png, info, PNG_INFO_tRNS))                                png_set_tRNS_to_alpha(png);
    if (color_type == PNG_COLOR_TYPE_RGB   ||
        color_type == PNG_COLOR_TYPE_GRAY  ||
        color_type == PNG_COLOR_TYPE_PALETTE)                                   png_set_filler(png, 0xFF, PNG_FILLER_AFTER);
    if (color_type == PNG_COLOR_TYPE_GRAY  ||
        color_type == PNG_COLOR_TYPE_GRAY_ALPHA)                                png_set_gray_to_rgb(png);
    png_read_update_info(png, info);

    // ONE contiguous RGBA pixel buffer — critical fix
    int rowBytes = (int)png_get_rowbytes(png, info);
    std::vector<uint8_t> pixels((size_t)height * (size_t)rowBytes);
    std::vector<png_bytep> rowPtrs((size_t)height);
    for (int y = 0; y < height; y++)
        rowPtrs[y] = pixels.data() + (size_t)y * (size_t)rowBytes;

    png_read_image(png, rowPtrs.data());
    png_destroy_read_struct(&png, &info, nullptr);
    fclose(fp);

    GLuint tex = 0;
    glGenTextures(1, &tex);
    glBindTexture(GL_TEXTURE_2D, tex);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0,
                 GL_RGBA, GL_UNSIGNED_BYTE, pixels.data());
    glBindTexture(GL_TEXTURE_2D, 0);
    printf("[TEX] Loaded %s (%dx%d) id=%u\n", path, width, height, tex);
    return tex;
}

class TextureManager {
public:
    static TextureManager& get() { static TextureManager i; return i; }

    GLuint getOrLoad(const std::string& romfsPath) {
        auto it = cache.find(romfsPath);
        if (it != cache.end()) return it->second;
        GLuint id = loadPNG(romfsPath.c_str());
        cache[romfsPath] = id;
        return id;
    }

private:
    std::unordered_map<std::string, GLuint> cache;
};

} // namespace texload
