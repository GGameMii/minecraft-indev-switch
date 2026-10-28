#pragma once

#include <vector>
#include <glad/glad.h>
#include <cmath>
#include <cstring>

namespace glshim {

struct Vertex {
    float x, y, z;
    float u, v;
    float r, g, b, a;
};

class GL11Shim {
public:
    static GL11Shim& get() {
        static GL11Shim instance;
        return instance;
    }

    bool init() {
        const char* vsSrc = R"glsl(
            #version 330 core
            layout (location = 0) in vec3 aPos;
            layout (location = 1) in vec2 aTexCoord;
            layout (location = 2) in vec4 aColor;

            uniform mat4 uMVP;

            out vec2 vTexCoord;
            out vec4 vColor;

            void main() {
                gl_Position = uMVP * vec4(aPos, 1.0);
                vTexCoord = aTexCoord;
                vColor = aColor;
            }
        )glsl";

        const char* fsSrc = R"glsl(
            #version 330 core
            in vec2 vTexCoord;
            in vec4 vColor;

            uniform sampler2D uTexture;
            uniform int uUseTexture;

            out vec4 FragColor;

            void main() {
                if (uUseTexture != 0) {
                    vec4 tex = texture(uTexture, vTexCoord);
                    FragColor = tex * vColor;
                } else {
                    FragColor = vColor;
                }
                if (FragColor.a < 0.1) discard; // Alpha test emulation
            }
        )glsl";

        GLuint vs = compileShader(GL_VERTEX_SHADER, vsSrc);
        GLuint fs = compileShader(GL_FRAGMENT_SHADER, fsSrc);
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vs);
        glAttachShader(shaderProgram, fs);
        glLinkProgram(shaderProgram);
        glDeleteShader(vs);
        glDeleteShader(fs);

        uMVP = glGetUniformLocation(shaderProgram, "uMVP");
        uTexture = glGetUniformLocation(shaderProgram, "uTexture");
        uUseTexture = glGetUniformLocation(shaderProgram, "uUseTexture");

        glGenVertexArrays(1, &vao);
        glGenBuffers(1, &vbo);

        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        // Pos
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, sizeof(Vertex), (void*)offsetof(Vertex, x));
        // Tex
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, GL_FALSE, sizeof(Vertex), (void*)offsetof(Vertex, u));
        // Color
        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2, 4, GL_FLOAT, GL_FALSE, sizeof(Vertex), (void*)offsetof(Vertex, r));

        glBindVertexArray(0);

        loadIdentity(matProj);
        loadIdentity(matModelView);
        return true;
    }

    void matrixMode(int mode) { currentMode = mode; }
    void loadIdentity() {
        if (currentMode == 0) loadIdentity(matModelView);
        else loadIdentity(matProj);
    }

    void ortho(float left, float right, float bottom, float top, float nearZ, float farZ) {
        // Column-major orthographic projection
        float mat[16] = {};
        mat[0]  = 2.0f / (right - left);
        mat[5]  = 2.0f / (top - bottom);
        mat[10] = -2.0f / (farZ - nearZ);
        mat[12] = -(right + left) / (right - left);
        mat[13] = -(top + bottom) / (top - bottom);
        mat[14] = -(farZ + nearZ) / (farZ - nearZ);
        mat[15] = 1.0f;
        std::memcpy(currentMode == 1 ? matProj : matModelView, mat, 64);
    }

    void translate(float x, float y, float z) {
        float t[16] = {0};
        loadIdentity(t);
        t[12] = x; t[13] = y; t[14] = z;
        if (currentMode == 0) multMatrix(matModelView, t);
        else multMatrix(matProj, t);
    }

    void scale(float x, float y, float z) {
        float s[16] = {0};
        s[0] = x; s[5] = y; s[10] = z; s[15] = 1.0f;
        if (currentMode == 0) multMatrix(matModelView, s);
        else multMatrix(matProj, s);
    }

    void color4f(float r, float g, float b, float a) { curR = r; curG = g; curB = b; curA = a; }
    void texCoord2f(float u, float v) { curU = u; curV = v; }

    void begin(GLenum mode) {
        drawMode = mode;
        vertices.clear();
    }

    void vertex3f(float x, float y, float z) {
        vertices.push_back({x, y, z, curU, curV, curR, curG, curB, curA});
    }

    void end() {
        if (vertices.empty()) return;

        glUseProgram(shaderProgram);

        float mvp[16];
        multiply(mvp, matProj, matModelView);
        glUniformMatrix4fv(uMVP, 1, GL_FALSE, mvp);
        glUniform1i(uUseTexture, texture2DEnabled ? 1 : 0);

        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices.size() * sizeof(Vertex), vertices.data(), GL_STREAM_DRAW);

        if (drawMode == 7) { // GL_QUADS
            // Convert Quads to Triangles
            std::vector<Vertex> tris;
            for (size_t i = 0; i + 3 < vertices.size(); i += 4) {
                tris.push_back(vertices[i]);
                tris.push_back(vertices[i + 1]);
                tris.push_back(vertices[i + 2]);
                tris.push_back(vertices[i]);
                tris.push_back(vertices[i + 2]);
                tris.push_back(vertices[i + 3]);
            }
            glBufferData(GL_ARRAY_BUFFER, tris.size() * sizeof(Vertex), tris.data(), GL_STREAM_DRAW);
            glDrawArrays(GL_TRIANGLES, 0, (GLsizei)tris.size());
        } else {
            glDrawArrays(drawMode, 0, (GLsizei)vertices.size());
        }

        glBindVertexArray(0);
        vertices.clear();
    }

    void enableTexture2D(bool enable) { texture2DEnabled = enable; }

private:
    GLuint shaderProgram = 0;
    GLint uMVP = -1, uTexture = -1, uUseTexture = -1;
    GLuint vao = 0, vbo = 0;

    int currentMode = 0; // 0 = ModelView, 1 = Projection
    float matModelView[16] = {0};
    float matProj[16] = {0};

    float curR = 1.0f, curG = 1.0f, curB = 1.0f, curA = 1.0f;
    float curU = 0.0f, curV = 0.0f;
    GLenum drawMode = GL_TRIANGLES;
    bool texture2DEnabled = false;

    std::vector<Vertex> vertices;

    void loadIdentity(float* m) {
        std::memset(m, 0, 16 * sizeof(float));
        m[0] = m[5] = m[10] = m[15] = 1.0f;
    }

    // OpenGL uses column-major matrices (mat[col*4+row])
    // multiply: out = a * b  (column-major)
    void multiply(float* out, const float* a, const float* b) {
        float tmp[16] = {};
        for (int col = 0; col < 4; ++col)
            for (int row = 0; row < 4; ++row)
                for (int k = 0; k < 4; ++k)
                    tmp[col*4+row] += a[k*4+row] * b[col*4+k];
        std::memcpy(out, tmp, 64);
    }

    void multMatrix(float* target, const float* mat) {
        float temp[16];
        multiply(temp, target, mat);
        std::memcpy(target, temp, 16 * sizeof(float));
    }

    GLuint compileShader(GLenum type, const char* src) {
        GLuint s = glCreateShader(type);
        glShaderSource(s, 1, &src, nullptr);
        glCompileShader(s);
        return s;
    }
};

} // namespace glshim
