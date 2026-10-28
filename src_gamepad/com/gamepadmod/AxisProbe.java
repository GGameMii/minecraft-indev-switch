/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.loader.ModLoader
 *  org.lwjgl.input.Controller
 *  org.lwjgl.input.Controllers
 */
package com.gamepadmod;

import com.gamepadmod.Settings;
import java.lang.reflect.Field;
import net.minecraft.loader.ModLoader;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

final class AxisProbe {
    private static float[] sign = new float[0];
    private static String[] roleOf = new String[0];
    private static float[] rest = new float[0];
    private static boolean[] active = new boolean[0];
    private static boolean debug;
    private static long lastScan;
    private static Field cField;

    private AxisProbe() {
    }

    static float value(Object object, int n) {
        try {
            Controller controller;
            if (cField == null) {
                cField = object.getClass().getDeclaredField("c");
                cField.setAccessible(true);
            }
            if ((controller = (Controller)cField.get(object)) == null) {
                return 0.0f;
            }
            if (debug) {
                AxisProbe.monitor(controller);
            }
            if (n >= 0 && n < controller.getAxisCount()) {
                float f = controller.getAxisValue(n);
                if (f != f) {
                    return 0.0f;
                }
                return n < sign.length ? f * sign[n] : f;
            }
            return 0.0f;
        }
        catch (Throwable throwable) {
            return 0.0f;
        }
    }

    private static void monitor(Controller controller) {
        long l = System.nanoTime();
        if (l - lastScan >= 30000000L) {
            lastScan = l;
            int n = Math.min(controller.getAxisCount(), active.length);
            for (int i = 0; i < n; ++i) {
                float f = Math.abs(controller.getAxisValue(i) - rest[i]);
                if (!active[i] && f > 0.6f) {
                    AxisProbe.active[i] = true;
                    ModLoader.log((String)("Gamepad[debug]: axis #" + i + " \"" + controller.getAxisName(i) + "\" moved (" + (float)((int)(controller.getAxisValue(i) * 100.0f)) / 100.0f + ") -> used as: " + roleOf[i]));
                    continue;
                }
                if (!active[i] || !(f < 0.3f)) continue;
                AxisProbe.active[i] = false;
            }
        }
    }

    static void map(Object object) {
        try {
            AxisProbe.doMap(object);
        }
        catch (Throwable throwable) {
            ModLoader.warn((String)("Gamepad: automatic axis detection failed (" + throwable + ")"));
            try {
                AxisProbe.legacy(object, null);
            }
            catch (Throwable throwable2) {
                // empty catch block
            }
        }
    }

    private static void doMap(Object object) throws Exception {
        int n;
        Controller controller = (Controller)AxisProbe.get(object, "c");
        int n2 = controller.getAxisCount();
        String[] stringArray = new String[n2];
        for (int i = 0; i < n2; ++i) {
            stringArray[i] = controller.getAxisName(i);
        }
        float[] fArray = new float[n2];
        float[] fArray2 = new float[n2];
        for (n = 0; n < n2; ++n) {
            fArray[n] = -2.0f;
        }
        for (n = 0; n < 8; ++n) {
            Controllers.poll();
            for (int i = 0; i < n2; ++i) {
                float f = controller.getAxisValue(i);
                if (f != f) {
                    f = 0.0f;
                }
                fArray2[i] = f;
                if (n < 4 || !(f > fArray[i])) continue;
                fArray[i] = f;
            }
            try {
                Thread.sleep(15L);
                continue;
            }
            catch (InterruptedException interruptedException) {
                break;
            }
        }
        rest = (float[])fArray2.clone();
        active = new boolean[n2];
        new StringBuilder();
        boolean[] blArray = new boolean[n2];
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            boolean bl = blArray[i] = fArray[i] <= -0.9f;
            if (blArray[i]) {
                ++n3;
            }
            ModLoader.log((String)("Gamepad: axis #" + i + " \"" + stringArray[i] + "\" rest=" + (float)((int)(fArray2[i] * 100.0f)) / 100.0f + (blArray[i] ? " (trigger-like)" : "")));
        }
        int[] nArray = new int[n2];
        int[] nArray2 = new int[n2];
        int n4 = 0;
        int n5 = 0;
        for (int i = 0; i < n2; ++i) {
            if (blArray[i]) {
                nArray2[n5++] = i;
                continue;
            }
            nArray[n4++] = i;
        }
        if (n5 == 2 && n4 >= 4) {
            AxisProbe.set(object, "axLX", nArray[0]);
            AxisProbe.set(object, "axLY", nArray[1]);
            AxisProbe.set(object, "axRX", nArray[2]);
            AxisProbe.set(object, "axRY", nArray[3]);
            AxisProbe.set(object, "axLT", nArray2[0]);
            AxisProbe.set(object, "axRT", nArray2[1]);
            AxisProbe.setBool(object, "combinedTrigger", false);
            ModLoader.log((String)("Gamepad: auto layout (triggers on axes #" + nArray2[0] + "/#" + nArray2[1] + ", sticks on #" + nArray[0] + "," + nArray[1] + "," + nArray[2] + "," + nArray[3] + ")"));
            AxisProbe.applyOverrides(object);
            AxisProbe.applyTweaks(object, n2);
        } else {
            ModLoader.log((String)"Gamepad: no 2-trigger layout detected, using name based layout");
            AxisProbe.legacy(object, controller);
        }
    }

    private static void legacy(Object object, Controller controller) throws Exception {
        Controller controller2 = controller != null ? controller : (Controller)AxisProbe.get(object, "c");
        int n = controller2.getAxisCount();
        int n2 = AxisProbe.find(controller2, n, "X Axis");
        int n3 = AxisProbe.find(controller2, n, "Y Axis");
        int n4 = AxisProbe.find(controller2, n, "X Rotation");
        int n5 = AxisProbe.find(controller2, n, "Y Rotation");
        int n6 = AxisProbe.find(controller2, n, "Z Axis");
        int n7 = AxisProbe.find(controller2, n, "Z Rotation");
        boolean bl = false;
        int n8 = -1;
        int n9 = -1;
        if (n4 >= 0 && n5 >= 0) {
            n8 = n6;
            if (n7 >= 0) {
                n9 = n7;
            } else if (n6 >= 0) {
                bl = true;
                n9 = n6;
            }
        } else if (n6 >= 0 && n7 >= 0) {
            n4 = n6;
            n5 = n7;
            n8 = AxisProbe.find(controller2, n, "X Rotation");
            n9 = AxisProbe.find(controller2, n, "Y Rotation");
        }
        if (n2 < 0) {
            int n10 = n2 = n > 0 ? 0 : -1;
        }
        if (n3 < 0) {
            int n11 = n3 = n > 1 ? 1 : -1;
        }
        if (n4 < 0) {
            int n12 = n4 = n > 3 ? 3 : -1;
        }
        if (n5 < 0) {
            n5 = n > 4 ? 4 : -1;
        }
        AxisProbe.set(object, "axLX", n2);
        AxisProbe.set(object, "axLY", n3);
        AxisProbe.set(object, "axRX", n4);
        AxisProbe.set(object, "axRY", n5);
        AxisProbe.set(object, "axLT", n8);
        AxisProbe.set(object, "axRT", n9);
        AxisProbe.setBool(object, "combinedTrigger", bl);
        AxisProbe.applyOverrides(object);
        if (rest.length != n) {
            rest = new float[n];
            active = new boolean[n];
        }
        AxisProbe.applyTweaks(object, n);
    }

    private static void applyOverrides(Object object) throws Exception {
        Object object2 = AxisProbe.get(object, "settings");
        int n = AxisProbe.sint(object2, "axisLeftX");
        if (n >= 0) {
            AxisProbe.set(object, "axLX", n);
        }
        if ((n = AxisProbe.sint(object2, "axisLeftY")) >= 0) {
            AxisProbe.set(object, "axLY", n);
        }
        if ((n = AxisProbe.sint(object2, "axisRightX")) >= 0) {
            AxisProbe.set(object, "axRX", n);
        }
        if ((n = AxisProbe.sint(object2, "axisRightY")) >= 0) {
            AxisProbe.set(object, "axRY", n);
        }
        if ((n = AxisProbe.sint(object2, "axisLeftTrigger")) >= 0) {
            AxisProbe.set(object, "axLT", n);
            AxisProbe.setBool(object, "combinedTrigger", false);
        }
        if ((n = AxisProbe.sint(object2, "axisRightTrigger")) >= 0) {
            AxisProbe.set(object, "axRT", n);
            AxisProbe.setBool(object, "combinedTrigger", false);
        }
    }

    private static void applyTweaks(Object object, int n) throws Exception {
        int n2;
        Settings settings = (Settings)AxisProbe.get(object, "settings");
        int n3 = AxisProbe.sint(object, "axLX");
        int n4 = AxisProbe.sint(object, "axLY");
        int n5 = AxisProbe.sint(object, "axRX");
        int n6 = AxisProbe.sint(object, "axRY");
        int n7 = AxisProbe.sint(object, "axLT");
        int n8 = AxisProbe.sint(object, "axRT");
        if (settings.swapSticks) {
            n2 = n3;
            n3 = n5;
            n5 = n2;
            n2 = n4;
            n4 = n6;
            n6 = n2;
        }
        if (settings.swapLeftXY) {
            n2 = n3;
            n3 = n4;
            n4 = n2;
        }
        if (settings.swapRightXY) {
            n2 = n5;
            n5 = n6;
            n6 = n2;
        }
        AxisProbe.set(object, "axLX", n3);
        AxisProbe.set(object, "axLY", n4);
        AxisProbe.set(object, "axRX", n5);
        AxisProbe.set(object, "axRY", n6);
        sign = new float[n];
        for (n2 = 0; n2 < n; ++n2) {
            AxisProbe.sign[n2] = 1.0f;
        }
        if (settings.invertLeftX && n3 >= 0 && n3 < n) {
            AxisProbe.sign[n3] = -sign[n3];
        }
        if (settings.invertLeftY && n4 >= 0 && n4 < n) {
            AxisProbe.sign[n4] = -sign[n4];
        }
        if (settings.invertRightX && n5 >= 0 && n5 < n) {
            AxisProbe.sign[n5] = -sign[n5];
        }
        if (settings.invertRightY && n6 >= 0 && n6 < n) {
            AxisProbe.sign[n6] = -sign[n6];
        }
        roleOf = new String[n];
        for (n2 = 0; n2 < n; ++n2) {
            AxisProbe.roleOf[n2] = "(unused)";
        }
        if (n3 >= 0 && n3 < n) {
            AxisProbe.roleOf[n3] = "left stick X (walk left/right)";
        }
        if (n4 >= 0 && n4 < n) {
            AxisProbe.roleOf[n4] = "left stick Y (walk forward/back)";
        }
        if (n5 >= 0 && n5 < n) {
            AxisProbe.roleOf[n5] = "right stick X (look left/right)";
        }
        if (n6 >= 0 && n6 < n) {
            AxisProbe.roleOf[n6] = "right stick Y (look up/down)";
        }
        if (n7 >= 0 && n7 < n) {
            AxisProbe.roleOf[n7] = "left trigger";
        }
        if (n8 >= 0 && n8 < n) {
            AxisProbe.roleOf[n8] = "right trigger";
        }
        debug = settings.debug;
        ModLoader.log((String)("Gamepad: tweaks swapSticks=" + settings.swapSticks + " swapLeftXY=" + settings.swapLeftXY + " swapRightXY=" + settings.swapRightXY + " invert[LX,LY,RX,RY]=" + settings.invertLeftX + "," + settings.invertLeftY + "," + settings.invertRightX + "," + settings.invertRightY + " debug=" + debug));
    }

    private static int find(Controller controller, int n, String string) {
        for (int i = 0; i < n; ++i) {
            String string2 = controller.getAxisName(i);
            if (string2 == null || !string2.equalsIgnoreCase(string)) continue;
            return i;
        }
        return -1;
    }

    private static Field fld(Object object, String string) throws Exception {
        Field field = object.getClass().getDeclaredField(string);
        field.setAccessible(true);
        return field;
    }

    private static Object get(Object object, String string) throws Exception {
        return AxisProbe.fld(object, string).get(object);
    }

    private static void set(Object object, String string, int n) throws Exception {
        AxisProbe.fld(object, string).setInt(object, n);
    }

    private static void setBool(Object object, String string, boolean bl) throws Exception {
        AxisProbe.fld(object, string).setBoolean(object, bl);
    }

    private static int sint(Object object, String string) throws Exception {
        return AxisProbe.fld(object, string).getInt(object);
    }
}

