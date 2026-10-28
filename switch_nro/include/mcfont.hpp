#pragma once
#include <glad/glad.h>
#include <vector>
#include <string>
#include "glshim.hpp"
#include "texload.hpp"

// Minecraft Indev bitmap font renderer
// Uses default.png: 16x16 grid of 8x8 pixel chars = 128x128 texture
namespace mcfont {

struct MCFont {
    GLuint texId = 0;
    int charWidths[256] = {};
    float scale = 1.0f;

    bool load(const std::string& path) {
        texId = texload::TextureManager::get().getOrLoad(path);
        if (!texId) return false;

        // Minecraft Indev glyph widths: most chars are 6 pixels wide, space is 4
        for (int i = 0; i < 256; i++) charWidths[i] = 6;
        charWidths[32] = 4; // space
        charWidths[33] = 2; // !
        charWidths[39] = 3; // '
        charWidths[44] = 3; // ,
        charWidths[46] = 2; // .
        charWidths[58] = 2; // :
        charWidths[59] = 2; // ;
        charWidths[105] = 3; // i
        charWidths[108] = 3; // l
        charWidths[124] = 2; // |

        return true;
    }

    int textWidth(const std::string& text) const {
        int w = 0;
        for (char c : text) w += charWidths[(unsigned char)c] + 1;
        return w;
    }

    // Draw text using glshim
    void draw(const std::string& text, float x, float y, int color, bool shadow = false, float s = 1.0f) {
        float r = ((color >> 16) & 0xFF) / 255.0f;
        float g = ((color >> 8)  & 0xFF) / 255.0f;
        float b = ((color)       & 0xFF) / 255.0f;

        if (shadow) {
            float sr = r * 0.25f, sg = g * 0.25f, sb = b * 0.25f;
            drawStr(text, x + 1.0f * s, y + 1.0f * s, sr, sg, sb, 1.0f, s);
        }
        drawStr(text, x, y, r, g, b, 1.0f, s);
    }

    void drawCentered(const std::string& text, float cx, float y, int color, bool shadow = false, float s = 1.0f) {
        draw(text, cx - textWidth(text) * s * 0.5f, y, color, shadow, s);
    }

private:
    void drawStr(const std::string& text, float startX, float y, float r, float g, float b, float a, float s) {
        auto& gl = glshim::GL11Shim::get();
        gl.enableTexture2D(true);
        glBindTexture(GL_TEXTURE_2D, texId);
        gl.color4f(r, g, b, a);

        float cx = startX;
        for (unsigned char c : text) {
            if ((int)c >= 128) { cx += (charWidths[c] + 1) * s; continue; }
            int row = c / 16;
            int col = c % 16;
            float u0 = col * 8.0f / 128.0f;
            float v0 = row * 8.0f / 128.0f;
            float u1 = u0 + 8.0f / 128.0f;
            float v1 = v0 + 8.0f / 128.0f;
            float cw = charWidths[c] * s;
            float ch = 8.0f * s;

            gl.begin(7); // GL_QUADS
                gl.texCoord2f(u0, v0); gl.vertex3f(cx,      y,      0);
                gl.texCoord2f(u1, v0); gl.vertex3f(cx + cw, y,      0);
                gl.texCoord2f(u1, v1); gl.vertex3f(cx + cw, y + ch, 0);
                gl.texCoord2f(u0, v1); gl.vertex3f(cx,      y + ch, 0);
            gl.end();

            cx += (charWidths[c] + 1) * s;
        }
        gl.enableTexture2D(false);
    }
};

} // namespace mcfont
