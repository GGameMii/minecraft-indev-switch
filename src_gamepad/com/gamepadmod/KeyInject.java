/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package com.gamepadmod;

import com.gamepadmod.GamepadMod;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.lwjgl.input.Keyboard;

final class KeyInject {
    private static final int EVENT_SIZE = 18;
    private static Field bufferField;
    private static boolean failed;

    private KeyInject() {
    }

    static boolean press(int n) {
        if (failed || n <= 0 || n > 255) {
            return false;
        }
        try {
            Object object;
            if (bufferField == null) {
                object = Keyboard.class.getDeclaredField("EVENT_SIZE");
                ((AccessibleObject)object).setAccessible(true);
                if (((Field)object).getInt(null) != 18) {
                    throw new IllegalStateException("unexpected LWJGL keyboard event size " + ((Field)object).getInt(null));
                }
                Field field = Keyboard.class.getDeclaredField("readBuffer");
                field.setAccessible(true);
                bufferField = field;
            }
            if ((object = (ByteBuffer)bufferField.get(null)) == null) {
                return false;
            }
            if (((Buffer)object).capacity() - ((Buffer)object).remaining() < 36) {
                return false;
            }
            long l = System.nanoTime();
            ((ByteBuffer)object).compact();
            KeyInject.put((ByteBuffer)object, n, true, l);
            KeyInject.put((ByteBuffer)object, n, false, l);
            ((Buffer)object).flip();
            return true;
        }
        catch (Throwable throwable) {
            failed = true;
            GamepadMod.warnOnce("keyinject", "cannot inject key events (" + throwable + "), using a direct fallback");
            return false;
        }
    }

    private static void put(ByteBuffer byteBuffer, int n, boolean bl, long l) {
        byteBuffer.putInt(n);
        byteBuffer.put((byte)(bl ? 1 : 0));
        byteBuffer.putInt(0);
        byteBuffer.putLong(l);
        byteBuffer.put((byte)0);
    }
}

