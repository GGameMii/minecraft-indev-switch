/*
 * Decompiled with CFR 0.152.
 */
package com.gamepadmod;

import com.gamepadmod.GamepadMod;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class Reflect {
    private Reflect() {
    }

    static Field field(Class<?> clazz, String string) {
        try {
            Field field = clazz.getDeclaredField(string);
            field.setAccessible(true);
            return field;
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    static Method method(Class<?> clazz, String string, Class<?> ... classArray) {
        try {
            Method method = clazz.getDeclaredMethod(string, classArray);
            method.setAccessible(true);
            return method;
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    static Object get(Field field, Object object) {
        try {
            return field == null ? null : field.get(object);
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    static boolean set(Field field, Object object, Object object2) {
        try {
            if (field == null) {
                return false;
            }
            field.set(object, object2);
            return true;
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    static Object call(Method method, Object object, Object ... objectArray) {
        try {
            return method == null ? null : method.invoke(object, objectArray);
        }
        catch (Throwable throwable) {
            GamepadMod.warnOnce("reflect:" + (method == null ? "null" : method.getName()), "Reflective call failed: " + throwable);
            return null;
        }
    }
}

