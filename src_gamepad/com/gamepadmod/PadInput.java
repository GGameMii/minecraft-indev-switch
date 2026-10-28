/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.loader.ModLoader
 *  org.lwjgl.input.Controller
 *  org.lwjgl.input.Controllers
 */
package com.gamepadmod;

import com.gamepadmod.AxisProbe;
import com.gamepadmod.Settings;
import net.minecraft.loader.ModLoader;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

final class PadInput {
    static final int DPAD_UP = 100;
    static final int DPAD_DOWN = 101;
    static final int DPAD_LEFT = 102;
    static final int DPAD_RIGHT = 103;
    static final int TRIGGER_L = 110;
    static final int TRIGGER_R = 111;
    static final int CODES = 128;
    private final Settings settings;
    private Controller c;
    private boolean failed;
    private boolean initialised;
    private int rescanWait = 100;
    private int rescanCountdown = 100;
    private int axLX = -1;
    private int axLY = -1;
    private int axRX = -1;
    private int axRY = -1;
    private int axLT = -1;
    private int axRT = -1;
    private boolean combinedTrigger;
    private final boolean[] down = new boolean[128];
    private final boolean[] latch = new boolean[128];
    private final boolean[] cur = new boolean[128];
    private final boolean[] prev = new boolean[128];
    private float lx;
    private float ly;
    private float rx;
    private float ry;

    PadInput(Settings settings) {
        this.settings = settings;
    }

    boolean hasController() {
        return this.c != null && !this.failed;
    }

    String controllerName() {
        try {
            return this.c == null ? null : this.c.getName();
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    float lx() {
        return this.lx;
    }

    float ly() {
        return this.ly;
    }

    float rx() {
        return this.rx;
    }

    float ry() {
        return this.ry;
    }

    boolean isDown(int n) {
        return n >= 0 && n < 128 && this.down[n];
    }

    boolean justPressed(int n) {
        return n >= 0 && n < 128 && this.cur[n] && !this.prev[n];
    }

    boolean justReleased(int n) {
        return n >= 0 && n < 128 && !this.cur[n] && this.prev[n];
    }

    int anyJustPressed() {
        for (int i = 0; i < 128; ++i) {
            if (!this.cur[i] || this.prev[i]) continue;
            return i;
        }
        return -1;
    }

    void tick() {
        if (!this.failed) {
            try {
                if (!this.initialised) {
                    this.initialised = true;
                    this.ensureCreated();
                    this.select();
                } else if (this.c == null && this.settings.rescan && --this.rescanCountdown <= 0) {
                    this.rescanCountdown = this.rescanWait = Math.min(this.rescanWait * 2, 1200);
                    try {
                        Controllers.destroy();
                    }
                    catch (Throwable throwable) {
                        // empty catch block
                    }
                    this.ensureCreated();
                    this.select();
                }
                this.poll();
            }
            catch (Throwable throwable) {
                this.failed = true;
                this.c = null;
                ModLoader.error((String)"Gamepad: input disabled after error", (Throwable)throwable);
                return;
            }
            for (int i = 0; i < 128; ++i) {
                this.prev[i] = this.cur[i];
                this.cur[i] = this.latch[i];
                this.latch[i] = false;
            }
        }
    }

    void pollFrame() {
        if (!this.failed && this.c != null) {
            try {
                this.poll();
            }
            catch (Throwable throwable) {
                this.failed = true;
                this.c = null;
                ModLoader.error((String)"Gamepad: input disabled after error", (Throwable)throwable);
            }
        }
    }

    private void ensureCreated() {
        try {
            if (!Controllers.isCreated()) {
                Controllers.create();
            }
        }
        catch (Throwable throwable) {
            ModLoader.warn((String)("Gamepad: controller support unavailable (" + throwable + ")"));
        }
    }

    private void select() {
        int n;
        this.c = null;
        try {
            if (!Controllers.isCreated()) {
                return;
            }
            n = Controllers.getControllerCount();
        }
        catch (Throwable throwable) {
            return;
        }
        String string = this.settings.controller == null ? "" : this.settings.controller.toLowerCase();
        Controller controller = null;
        for (int i = 0; i < n; ++i) {
            boolean bl;
            Controller controller2 = Controllers.getController((int)i);
            if (controller2 == null) continue;
            if (this.rescanWait == 100 && this.rescanCountdown == 100) {
                ModLoader.log((String)("Gamepad: found controller #" + i + " \"" + controller2.getName() + "\" (" + controller2.getButtonCount() + " buttons, " + controller2.getAxisCount() + " axes)"));
            }
            if (string.length() > 0) {
                bl = controller2.getName().toLowerCase().indexOf(string) >= 0 || string.equals(Integer.toString(i));
            } else {
                boolean bl2 = bl = controller2.getButtonCount() >= 4 && controller2.getAxisCount() >= 2;
            }
            if (!bl || controller != null) continue;
            controller = controller2;
        }
        if (controller != null) {
            this.c = controller;
            this.mapAxes();
            this.rescanWait = 100;
            this.rescanCountdown = 100;
            ModLoader.log((String)("Gamepad: using \"" + controller.getName() + "\" axes LX=" + this.axLX + " LY=" + this.axLY + " RX=" + this.axRX + " RY=" + this.axRY + " LT=" + this.axLT + " RT=" + this.axRT + (this.combinedTrigger ? " (combined triggers)" : "")));
        }
    }

    private int axis(String string) {
        int n = this.c.getAxisCount();
        for (int i = 0; i < n; ++i) {
            String string2 = this.c.getAxisName(i);
            if (string2 == null || !string2.equalsIgnoreCase(string)) continue;
            return i;
        }
        return -1;
    }

    private void mapAxes() {
        AxisProbe.map(this);
    }

    private float value(int n) {
        return AxisProbe.value(this, n);
    }

    private float dead(float f) {
        float f2 = this.settings.deadZone;
        float f3 = Math.abs(f);
        if (f3 < f2) {
            return 0.0f;
        }
        float f4 = (f3 - f2) / (1.0f - f2);
        return f < 0.0f ? -f4 : f4;
    }

    private void poll() {
        Controller controller = this.c;
        if (controller != null) {
            Controllers.poll();
            int n = Math.min(controller.getButtonCount(), 100);
            for (int i = 0; i < n; ++i) {
                this.down[i] = controller.isButtonPressed(i);
            }
            float f = controller.getPovX();
            float f2 = controller.getPovY();
            if (this.settings.invertPovY) {
                f2 = -f2;
            }
            this.down[102] = f < -0.5f;
            this.down[103] = f > 0.5f;
            this.down[100] = f2 < -0.5f;
            this.down[101] = f2 > 0.5f;
            this.lx = this.dead(this.value(this.axLX));
            this.ly = this.dead(this.value(this.axLY));
            this.rx = this.dead(this.value(this.axRX));
            this.ry = this.dead(this.value(this.axRY));
            if (this.combinedTrigger) {
                float f3 = this.value(this.axLT);
                this.down[110] = f3 > 0.5f;
                this.down[111] = f3 < -0.5f;
            } else {
                this.down[110] = this.axLT >= 0 && this.value(this.axLT) > 0.5f;
                this.down[111] = this.axRT >= 0 && this.value(this.axRT) > 0.5f;
            }
            for (int i = 0; i < 128; ++i) {
                if (!this.down[i]) continue;
                this.latch[i] = true;
            }
        }
    }

    static String codeName(int n) {
        if (n < 0) {
            return "NONE";
        }
        switch (n) {
            case 100: {
                return "D-Pad Up";
            }
            case 101: {
                return "D-Pad Down";
            }
            case 102: {
                return "D-Pad Left";
            }
            case 103: {
                return "D-Pad Right";
            }
            default: {
                return "Btn " + n;
            }
            case 110: {
                return "L Trigger";
            }
            case 111: 
        }
        return "R Trigger";
    }
}

