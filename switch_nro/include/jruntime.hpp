#pragma once

#include <string>
#include <vector>
#include <memory>
#include <unordered_map>
#include <cmath>
#include <chrono>
#include <cstring>
#include <algorithm>
#include <iostream>
#include <sstream>

#include <switch.h>
#include <glad/glad.h>
#include <EGL/egl.h>

namespace java {

// Smart Pointer helper
template<typename T, typename... Args>
inline std::shared_ptr<T> make_ref(Args&&... args) {
    return std::make_shared<T>(std::forward<Args>(args)...);
}

// Java String wrapper
class String {
public:
    std::string str;

    String() : str("") {}
    String(const char* s) : str(s ? s : "") {}
    String(const std::string& s) : str(s) {}
    String(int val) : str(std::to_string(val)) {}
    String(long long val) : str(std::to_string(val)) {}
    String(float val) : str(std::to_string(val)) {}
    String(double val) : str(std::to_string(val)) {}

    int length() const { return (int)str.length(); }
    bool isEmpty() const { return str.empty(); }
    char charAt(int i) const { return (i >= 0 && i < (int)str.length()) ? str[i] : 0; }

    bool equals(const String& other) const { return str == other.str; }
    bool equalsIgnoreCase(const String& other) const {
        if (str.length() != other.str.length()) return false;
        for (size_t i = 0; i < str.length(); ++i) {
            if (tolower(str[i]) != tolower(other.str[i])) return false;
        }
        return true;
    }

    bool startsWith(const String& prefix) const {
        return str.rfind(prefix.str, 0) == 0;
    }

    String substring(int start) const {
        if (start < 0 || start > (int)str.length()) return String("");
        return String(str.substr(start));
    }

    String substring(int start, int end) const {
        if (start < 0 || end < start || start > (int)str.length()) return String("");
        return String(str.substr(start, end - start));
    }

    String trim() const {
        size_t first = str.find_first_not_of(" \t\r\n");
        if (first == std::string::npos) return String("");
        size_t last = str.find_last_not_of(" \t\r\n");
        return String(str.substr(first, (last - first + 1)));
    }

    int indexOf(char ch) const {
        size_t pos = str.find(ch);
        return (pos == std::string::npos) ? -1 : (int)pos;
    }

    std::vector<String> split(const String& delim) const {
        std::vector<String> result;
        size_t start = 0;
        size_t end = str.find(delim.str);
        while (end != std::string::npos) {
            result.push_back(String(str.substr(start, end - start)));
            start = end + delim.str.length();
            end = str.find(delim.str, start);
        }
        result.push_back(String(str.substr(start)));
        return result;
    }

    const char* c_str() const { return str.c_str(); }

    bool operator==(const String& o) const { return str == o.str; }
    bool operator!=(const String& o) const { return str != o.str; }
    String operator+(const String& o) const { return String(str + o.str); }
    String operator+(const char* o) const { return String(str + (o ? o : "")); }
    String operator+(int o) const { return String(str + std::to_string(o)); }
};

inline String operator+(const char* lhs, const String& rhs) {
    return String(std::string(lhs ? lhs : "") + rhs.str);
}

// Java Array wrapper
template<typename T>
class Array {
public:
    std::vector<T> data;
    int length;

    Array() : length(0) {}
    Array(int size) : data(size), length(size) {}
    Array(int size, const T& def) : data(size, def), length(size) {}

    T& operator[](int index) { return data[index]; }
    const T& operator[](int index) const { return data[index]; }

    T* ptr() { return data.data(); }
};

// Math
class Math {
public:
    static constexpr float PI = 3.14159265358979323846f;

    static float sin(float a) { return std::sin(a); }
    static float cos(float a) { return std::cos(a); }
    static float sqrt(float a) { return std::sqrt(a); }
    static float floor(float a) { return std::floor(a); }
    static float abs(float a) { return std::abs(a); }
    static int abs(int a) { return std::abs(a); }
    static float min(float a, float b) { return std::min(a, b); }
    static float max(float a, float b) { return std::max(a, b); }
    static int min(int a, int b) { return std::min(a, b); }
    static int max(int a, int b) { return std::max(a, b); }
};

// System
class System {
public:
    static long long currentTimeMillis() {
        return std::chrono::duration_cast<std::chrono::milliseconds>(
            std::chrono::system_clock::now().time_since_epoch()
        ).count();
    }

    static long long nanoTime() {
        return std::chrono::duration_cast<std::chrono::nanoseconds>(
            std::chrono::high_resolution_clock::now().time_since_epoch()
        ).count();
    }
};

// Switch Hardware Bridge (Joy-Cons, Touch, Keyboard)
struct SwitchInputBridge {
    // Gamepad
    bool btnA = false;
    bool btnB = false;
    bool btnX = false;
    bool btnY = false;
    bool btnL = false;
    bool btnR = false;
    bool btnZL = false;
    bool btnZR = false;
    bool btnPlus = false;
    bool btnMinus = false;
    bool btnLStick = false;
    bool btnRStick = false;
    bool dpadUp = false;
    bool dpadDown = false;
    bool dpadLeft = false;
    bool dpadRight = false;

    float leftStickX = 0.0f;
    float leftStickY = 0.0f;
    float rightStickX = 0.0f;
    float rightStickY = 0.0f;

    // Touch
    bool touching = false;
    int touchX = 0;
    int touchY = 0;

    // Software Keyboard
    static std::string openKeyboard(const std::string& guideText, const std::string& initialText, int maxLen) {
        SwkbdConfig kbd;
        char outStr[256] = {0};

        Result rc = swkbdCreate(&kbd, 0);
        if (R_SUCCEEDED(rc)) {
            swkbdConfigMakePresetDefault(&kbd);
            swkbdConfigSetGuideText(&kbd, guideText.c_str());
            swkbdConfigSetInitialText(&kbd, initialText.c_str());
            swkbdConfigSetStringLenMax(&kbd, maxLen);

            rc = swkbdShow(&kbd, outStr, sizeof(outStr));
            swkbdClose(&kbd);
            if (R_SUCCEEDED(rc)) {
                return std::string(outStr);
            }
        }
        return initialText;
    }
};

} // namespace java
