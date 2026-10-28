/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.ChatScreen
 *  net.minecraft.client.KeyBinding
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Button
 *  net.minecraft.client.gui.ControlsScreen
 *  net.minecraft.client.gui.HUD
 *  net.minecraft.client.gui.PauseScreen
 *  net.minecraft.client.gui.Screen
 *  net.minecraft.client.gui.inventory.BuildScreen
 *  net.minecraft.client.gui.inventory.ContainerScreen
 *  net.minecraft.client.gui.inventory.InventoryScreen
 *  net.minecraft.client.player.MovementInput
 *  net.minecraft.client.player.MovementInputFromOptions
 *  net.minecraft.loader.ModContainer
 *  net.minecraft.loader.ModInitializer
 *  net.minecraft.loader.ModLoader
 *  net.minecraft.loader.events.ClientStopListener
 *  net.minecraft.loader.events.ClientTickListener
 *  net.minecraft.loader.events.HudRenderListener
 *  net.minecraft.world.container.Slot
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.phys.HitResult
 *  org.lwjgl.input.Cursor
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.Display
 */
package com.gamepadmod;

import com.gamepadmod.Bindings;
import com.gamepadmod.GamepadScreen;
import com.gamepadmod.KeyInject;
import com.gamepadmod.PadAction;
import com.gamepadmod.PadInput;
import com.gamepadmod.Reflect;
import com.gamepadmod.Settings;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import net.minecraft.client.ChatScreen;
import net.minecraft.client.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.ControlsScreen;
import net.minecraft.client.gui.HUD;
import net.minecraft.client.gui.PauseScreen;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.inventory.BuildScreen;
import net.minecraft.client.gui.inventory.ContainerScreen;
import net.minecraft.client.gui.inventory.InventoryScreen;
import net.minecraft.client.player.MovementInput;
import net.minecraft.client.player.MovementInputFromOptions;
import net.minecraft.loader.ModContainer;
import net.minecraft.loader.ModInitializer;
import net.minecraft.loader.ModLoader;
import net.minecraft.loader.events.ClientStopListener;
import net.minecraft.loader.events.ClientTickListener;
import net.minecraft.loader.events.HudRenderListener;
import net.minecraft.world.container.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class GamepadMod
implements ModInitializer,
ClientTickListener,
HudRenderListener,
ClientStopListener {
    private static final Set<String> WARNED = new HashSet<String>();
    private static final Method M_CLICK_MOUSE = Reflect.method(Minecraft.class, "clickMouse", Integer.TYPE);
    private static final Field F_LEFT_COUNTER = Reflect.field(Minecraft.class, "leftClickCounter");
    private static final Method M_MOUSE_CLICKED = Reflect.method(Screen.class, "mouseClicked", Integer.TYPE, Integer.TYPE, Integer.TYPE);
    private static final Method M_MOUSE_UP = Reflect.method(Screen.class, "mouseMovedOrUp", Integer.TYPE, Integer.TYPE, Integer.TYPE);
    private static final Method M_KEY_TYPED = Reflect.method(Screen.class, "keyTyped", Character.TYPE, Integer.TYPE);
    private static final Field F_BUTTONS = Reflect.field(Screen.class, "buttons");
    private static final Method M_SLOT_AT = Reflect.method(ContainerScreen.class, "getSlotAtPosition", Integer.TYPE, Integer.TYPE);
    private static final Method M_HANDLE_CLICK = Reflect.method(ContainerScreen.class, "handleMouseClick", Slot.class, Integer.TYPE, Integer.TYPE, Boolean.TYPE);
    private static final Field F_XSIZE = Reflect.field(ContainerScreen.class, "xSize");
    private static final Field F_YSIZE = Reflect.field(ContainerScreen.class, "ySize");
    private static final Field F_CONTROLS_BUTTON_ID = Reflect.field(ControlsScreen.class, "buttonId");
    private static final Field F_MOVE_STATES = Reflect.field(MovementInputFromOptions.class, "movementKeyStates");
    private static Cursor nativeCursor;
    private Settings settings;
    private Bindings bindings;
    private PadInput pad;
    private Minecraft mc;
    private boolean cursorInstalled;
    private int cursorTries;
    private float curX;
    private float curY;
    private int lastSetX = -1;
    private int lastSetY = -1;
    private int obsX = -1;
    private int obsY = -1;
    private boolean cursorInit;
    private long cursorNanos;
    private final boolean[] padMouseHeld = new boolean[2];
    private final Screen[] padMouseScreen = new Screen[2];
    private boolean breakHeld;
    private boolean placeHeld;
    private boolean counterHacked;
    private int breakTicks;
    private int placeTicks;
    private final boolean[] wantMove = new boolean[5];
    private long frameNanos;
    private Screen decoratedScreen;
    private OpenButton openButton;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void warnOnce(String string, String string2) {
        Set<String> set = WARNED;
        synchronized (set) {
            if (!WARNED.add(string)) {
                return;
            }
        }
        ModLoader.warn((String)("Gamepad: " + string2));
    }

    public void onInitialize(ModContainer modContainer) {
        File file = modContainer.getConfigDir();
        this.settings = Settings.load(file);
        this.bindings = new Bindings(file);
        this.pad = new PadInput(this.settings);
        ModLoader.log((String)"Gamepad mod ready");
        ModLoader.register((Object)this);
    }

    public void onClientStop() {
    }

    public void onClientTick(Minecraft minecraft) {
        this.mc = minecraft;
        try {
            this.tick(minecraft);
        }
        catch (Throwable throwable) {
            GamepadMod.warnOnce("tick", "tick error: " + throwable);
        }
    }

    private void tick(Minecraft minecraft) {
        if (this.settings.customCursor && !this.cursorInstalled && this.cursorTries < 3) {
            ++this.cursorTries;
            this.installCursor();
        }
        this.pad.tick();
        Screen screen = minecraft.screen;
        this.decorateControls(minecraft, screen);
        if (!this.pad.hasController() || !Display.isActive()) {
            this.releaseHeld(minecraft);
        } else if (screen instanceof GamepadScreen && ((GamepadScreen)screen).isCapturing()) {
            this.releaseHeld(minecraft);
        } else if (screen != null) {
            this.menuTick(minecraft);
        } else {
            this.worldTick(minecraft);
        }
    }

    private int code(PadAction padAction) {
        return this.bindings.get(padAction);
    }

    private void menuTick(Minecraft minecraft) {
        this.releaseHeld(minecraft);
        this.frameNanos = 0L;
        if (minecraft.level == null) {
            this.cursorUpdate(minecraft);
        }
        this.padMouseButton(minecraft, 0, this.code(PadAction.CONFIRM));
        this.padMouseButton(minecraft, 1, this.code(PadAction.RIGHT_CLICK));
        Screen screen = minecraft.screen;
        if (screen instanceof ContainerScreen) {
            if (this.pad.justPressed(this.code(PadAction.QUICK_MOVE))) {
                this.containerClick(minecraft, (ContainerScreen)screen, 0, true);
            }
            if ((screen = minecraft.screen) instanceof ContainerScreen && this.pad.justPressed(this.code(PadAction.QUICK_MOVE_RIGHT))) {
                this.containerClick(minecraft, (ContainerScreen)screen, 1, true);
            }
            if ((screen = minecraft.screen) instanceof ContainerScreen && this.pad.justPressed(this.code(PadAction.INVENTORY))) {
                Reflect.call(M_KEY_TYPED, screen, Character.valueOf('\u0000'), minecraft.options.keyBindInventory.keyCode);
            }
            if ((screen = minecraft.screen) instanceof BuildScreen && this.pad.justPressed(this.code(PadAction.BUILD))) {
                this.back(screen);
            }
        }
        if ((screen = minecraft.screen) != null) {
            if (this.pad.justPressed(this.code(PadAction.BACK))) {
                this.back(screen);
            } else if (screen instanceof PauseScreen && this.pad.justPressed(this.code(PadAction.PAUSE))) {
                this.back(screen);
            }
        }
    }

    private void padMouseButton(Minecraft minecraft, int n, int n2) {
        Screen screen;
        if (this.padMouseHeld[n] && minecraft.screen != this.padMouseScreen[n]) {
            this.padMouseHeld[n] = false;
            this.padMouseScreen[n] = null;
        }
        if ((screen = minecraft.screen) != null && this.pad.justPressed(n2)) {
            this.mouseDown(minecraft, screen, n);
            if (minecraft.screen == screen) {
                this.padMouseHeld[n] = true;
                this.padMouseScreen[n] = screen;
            }
        }
        if (this.padMouseHeld[n] && !this.pad.isDown(n2)) {
            Screen screen2 = this.padMouseScreen[n];
            this.padMouseHeld[n] = false;
            this.padMouseScreen[n] = null;
            if (minecraft.screen == screen2 && screen2 != null) {
                this.mouseUp(minecraft, screen2, n);
            }
        }
    }

    private int[] scaled(Minecraft minecraft, Screen screen) {
        int n = Mouse.getX();
        int n2 = Mouse.getY();
        int n3 = Math.max(1, minecraft.width);
        int n4 = Math.max(1, minecraft.height);
        return new int[]{n * screen.width / n3, screen.height - n2 * screen.height / n4 - 1};
    }

    private void mouseDown(Minecraft minecraft, Screen screen, int n) {
        int[] nArray = this.scaled(minecraft, screen);
        Reflect.call(M_MOUSE_CLICKED, screen, nArray[0], nArray[1], n);
    }

    private void mouseUp(Minecraft minecraft, Screen screen, int n) {
        int[] nArray = this.scaled(minecraft, screen);
        Reflect.call(M_MOUSE_UP, screen, nArray[0], nArray[1], n);
    }

    private void containerClick(Minecraft minecraft, ContainerScreen containerScreen, int n, boolean bl) {
        int[] nArray = this.scaled(minecraft, (Screen)containerScreen);
        Object object = Reflect.call(M_SLOT_AT, containerScreen, nArray[0], nArray[1]);
        Slot slot = object instanceof Slot ? (Slot)object : null;
        Object object2 = Reflect.get(F_XSIZE, containerScreen);
        Object object3 = Reflect.get(F_YSIZE, containerScreen);
        if (object2 instanceof Integer && object3 instanceof Integer) {
            int n2 = (Integer)object2;
            int n3 = (Integer)object3;
            int n4 = (containerScreen.width - n2) / 2;
            int n5 = (containerScreen.height - n3) / 2;
            boolean bl2 = nArray[0] < n4 || nArray[1] < n5 || nArray[0] >= n4 + n2 || nArray[1] >= n5 + n3;
            int n6 = -1;
            if (slot != null) {
                n6 = slot.slotNumber;
            }
            if (bl2) {
                n6 = -999;
            }
            if (n6 != -1) {
                Reflect.call(M_HANDLE_CLICK, containerScreen, slot, n6, n, bl && n6 != -999);
            }
        }
    }

    private void back(Screen screen) {
        Object object;
        if (screen instanceof ControlsScreen && (object = Reflect.get(F_CONTROLS_BUTTON_ID, screen)) instanceof Integer && (Integer)object >= 0) {
            return;
        }
        Reflect.call(M_KEY_TYPED, screen, Character.valueOf('\u0000'), 1);
    }

    private void cursorUpdate(Minecraft minecraft) {
        boolean bl;
        long l = System.nanoTime();
        float f = this.cursorNanos == 0L ? 0.0f : (float)(l - this.cursorNanos) / 1.0E9f;
        this.cursorNanos = l;
        if (f > 0.1f) {
            f = 0.1f;
        }
        int n = Mouse.getX();
        int n2 = Mouse.getY();
        boolean bl2 = bl = n != this.obsX || n2 != this.obsY;
        if (bl && n == this.lastSetX && n2 == this.lastSetY) {
            bl = false;
        }
        this.obsX = n;
        this.obsY = n2;
        if (!this.cursorInit || bl) {
            this.curX = n;
            this.curY = n2;
            this.cursorInit = true;
        }
        float f2 = this.pad.lx() + this.pad.rx() * 0.45f;
        float f3 = this.pad.ly() + this.pad.ry() * 0.45f;
        if (f2 != 0.0f || f3 != 0.0f) {
            float f4 = this.settings.cursorSpeed * (float)Math.max(1, minecraft.height) * 1.1f * f;
            this.curX += GamepadMod.curve(f2) * f4;
            this.curY -= GamepadMod.curve(f3) * f4;
            this.curX = Math.max(0.0f, Math.min((float)(minecraft.width - 1), this.curX));
            this.curY = Math.max(0.0f, Math.min((float)(minecraft.height - 1), this.curY));
            int n3 = (int)this.curX;
            int n4 = (int)this.curY;
            if (n3 != n || n4 != n2) {
                Mouse.setCursorPosition((int)n3, (int)n4);
                this.lastSetX = n3;
                this.lastSetY = n4;
                this.obsX = n3;
                this.obsY = n4;
            }
        }
    }

    private static float curve(float f) {
        float f2 = Math.abs(f);
        return f * (0.3f + 0.7f * f2);
    }

    private void worldTick(Minecraft minecraft) {
        this.padMouseHeld[0] = false;
        this.padMouseHeld[1] = false;
        this.padMouseScreen[0] = null;
        this.padMouseScreen[1] = null;
        this.cursorNanos = 0L;
        if (minecraft.level != null && minecraft.player != null && minecraft.gamemode != null) {
            this.movement(minecraft);
            if (this.pad.justPressed(this.code(PadAction.PAUSE))) {
                this.releaseHeld(minecraft);
                minecraft.displayInGameMenu();
            } else if (this.pad.justPressed(this.code(PadAction.INVENTORY))) {
                this.releaseHeld(minecraft);
                minecraft.setScreen((Screen)new InventoryScreen((Player)minecraft.player));
            } else if (this.pad.justPressed(this.code(PadAction.BUILD)) && minecraft.gamemode.creativeMode) {
                this.releaseHeld(minecraft);
                minecraft.setScreen((Screen)new BuildScreen((Player)minecraft.player));
            } else if (this.pad.justPressed(this.code(PadAction.CHAT)) && minecraft.isOnlineClient()) {
                this.releaseHeld(minecraft);
                minecraft.setScreen((Screen)new ChatScreen());
            } else {
                if (this.pad.justPressed(this.code(PadAction.DROP))) {
                    minecraft.player.dropCurrentItem();
                }
                if (this.pad.justPressed(this.code(PadAction.TOGGLE_FOG))) {
                    minecraft.options.setOptionValue(4, 1);
                }
                if (this.pad.justPressed(this.code(PadAction.THIRD_PERSON))) {
                    this.togglePerspective(minecraft);
                }
                if (minecraft.gamemode.creativeMode) {
                    if (this.pad.justPressed(this.code(PadAction.LOAD_POS))) {
                        minecraft.player.preparePlayerToSpawn();
                    }
                    if (this.pad.justPressed(this.code(PadAction.SAVE_POS))) {
                        minecraft.level.setSpawnLocation((int)minecraft.player.x, (int)minecraft.player.y, (int)minecraft.player.z, minecraft.player.xRot);
                        minecraft.player.preparePlayerToSpawn();
                    }
                }
                if (this.pad.justPressed(this.code(PadAction.HOTBAR_PREV))) {
                    minecraft.player.inventory.currentItem = (minecraft.player.inventory.currentItem + 8) % 9;
                }
                if (this.pad.justPressed(this.code(PadAction.HOTBAR_NEXT))) {
                    minecraft.player.inventory.currentItem = (minecraft.player.inventory.currentItem + 1) % 9;
                }
                this.breakAndPlace(minecraft);
            }
        } else {
            this.releaseHeld(minecraft);
        }
    }

    private void movement(Minecraft minecraft) {
        Object object;
        MovementInput movementInput = minecraft.player.movementInput;
        boolean[] blArray = null;
        if (movementInput instanceof MovementInputFromOptions && (object = Reflect.get(F_MOVE_STATES, movementInput)) instanceof boolean[]) {
            blArray = (boolean[])object;
        }
        if (blArray != null && blArray.length >= 5) {
            float f = 0.35f;
            boolean[] blArray2 = new boolean[]{this.pad.ly() < -f, this.pad.ly() > f, this.pad.lx() < -f, this.pad.lx() > f, this.pad.isDown(this.code(PadAction.JUMP))};
            int[] nArray = new int[]{minecraft.options.keyBindForward.keyCode, minecraft.options.keyBindBack.keyCode, minecraft.options.keyBindLeft.keyCode, minecraft.options.keyBindRight.keyCode, minecraft.options.keyBindJump.keyCode};
            for (int i = 0; i < 5; ++i) {
                if (blArray2[i]) {
                    blArray[i] = true;
                } else if (this.wantMove[i]) {
                    blArray[i] = Keyboard.isKeyDown((int)nArray[i]);
                }
                this.wantMove[i] = blArray2[i];
            }
        }
    }

    private void ensureGrabbed(Minecraft minecraft) {
        if (!minecraft.mouseGrabbed) {
            minecraft.grabMouse();
        }
    }

    private void breakAndPlace(Minecraft minecraft) {
        boolean bl = minecraft.gamemode.creativeMode;
        int n = this.code(PadAction.BREAK);
        int n2 = this.code(PadAction.PLACE);
        if (this.pad.justPressed(n)) {
            this.ensureGrabbed(minecraft);
            this.breakHeld = true;
            this.breakTicks = 0;
            this.clickMouse(minecraft, 0);
        }
        if (this.breakHeld && !this.pad.isDown(n)) {
            this.stopBreak(minecraft);
        } else if (this.breakHeld) {
            if (bl) {
                if (++this.breakTicks >= 5) {
                    this.breakTicks = 0;
                    this.clickMouse(minecraft, 0);
                }
            } else {
                HitResult hitResult;
                if (++this.breakTicks >= 5) {
                    this.breakTicks = 0;
                    minecraft.player.swingItem();
                }
                if ((hitResult = minecraft.hitResult) != null && hitResult.typeOfHit == 0) {
                    minecraft.gamemode.sendBlockRemoving(hitResult.x, hitResult.y, hitResult.z, hitResult.f);
                    minecraft.particles.addBlockHitEffects(hitResult.x, hitResult.y, hitResult.z, hitResult.f);
                } else {
                    minecraft.gamemode.resetBlockRemoving();
                }
                Reflect.set(F_LEFT_COUNTER, minecraft, 2);
                this.counterHacked = true;
            }
        }
        if (this.pad.justPressed(n2)) {
            this.ensureGrabbed(minecraft);
            this.placeHeld = true;
            this.placeTicks = 0;
            this.clickMouse(minecraft, 1);
        }
        if (this.placeHeld && !this.pad.isDown(n2)) {
            this.placeHeld = false;
        } else if (this.placeHeld && ++this.placeTicks >= 5) {
            this.placeTicks = 0;
            this.clickMouse(minecraft, 1);
        }
    }

    private void clickMouse(Minecraft minecraft, int n) {
        Reflect.call(M_CLICK_MOUSE, minecraft, n);
    }

    private void stopBreak(Minecraft minecraft) {
        this.breakHeld = false;
        if (this.counterHacked) {
            this.counterHacked = false;
            Reflect.set(F_LEFT_COUNTER, minecraft, 0);
            if (minecraft.gamemode != null) {
                minecraft.gamemode.resetBlockRemoving();
            }
        }
    }

    private void releaseHeld(Minecraft minecraft) {
        if (this.breakHeld) {
            this.stopBreak(minecraft);
        }
        this.placeHeld = false;
    }

    private void togglePerspective(Minecraft minecraft) {
        int n = this.perspectiveKey(minecraft);
        if (KeyInject.press(n)) {
            if (this.settings.debug) {
                ModLoader.log((String)("Gamepad[debug]: perspective toggled via key " + n));
            }
            return;
        }
        boolean bl = minecraft.options.thirdPersonView = !minecraft.options.thirdPersonView;
        if (this.settings.debug) {
            ModLoader.log((String)("Gamepad[debug]: perspective toggled directly (thirdPersonView=" + minecraft.options.thirdPersonView + ")"));
        }
    }

    private int perspectiveKey(Minecraft minecraft) {
        try {
            for (Field field : minecraft.options.getClass().getFields()) {
                String string;
                if (field.getType() != KeyBinding.class) continue;
                KeyBinding keyBinding = (KeyBinding)field.get(minecraft.options);
                String string2 = string = keyBinding == null || keyBinding.keyDescription == null ? "" : keyBinding.keyDescription.toLowerCase();
                if (keyBinding == null || keyBinding.keyCode <= 0 || !string.contains("perspective") && !string.contains("third")) continue;
                return keyBinding.keyCode;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return 63;
    }

    public void onHudRender(HUD hUD) {
        Minecraft minecraft = this.mc;
        if (minecraft != null && this.pad != null) {
            try {
                if (!this.pad.hasController() || !Display.isActive()) {
                    this.frameNanos = 0L;
                    return;
                }
                this.pad.pollFrame();
                Screen screen = minecraft.screen;
                if (screen != null) {
                    this.frameNanos = 0L;
                    if (!(minecraft.level == null || screen instanceof GamepadScreen && ((GamepadScreen)screen).isCapturing())) {
                        this.cursorUpdate(minecraft);
                    }
                    return;
                }
                long l = System.nanoTime();
                float f = this.frameNanos == 0L ? 0.0f : (float)(l - this.frameNanos) / 1.0E9f;
                this.frameNanos = l;
                if (f <= 0.0f || f > 0.25f || minecraft.player == null || !minecraft.mouseGrabbed) {
                    return;
                }
                float f2 = this.pad.rx();
                float f3 = this.pad.ry();
                if (f2 == 0.0f && f3 == 0.0f) {
                    return;
                }
                float f4 = minecraft.options.sliders[0].value * 0.6f + 0.2f;
                float f5 = f4 * f4 * f4 * 8.0f;
                float f6 = 1000.0f * this.settings.lookSpeed * f;
                float f7 = minecraft.options.invertMouse ? -1.0f : 1.0f;
                minecraft.player.interpolateTurn(GamepadMod.curve(f2) * f6 * f5, -GamepadMod.curve(f3) * f6 * f5 * f7);
            }
            catch (Throwable throwable) {
                GamepadMod.warnOnce("hud", "frame error: " + throwable);
            }
        }
    }

    private void decorateControls(final Minecraft minecraft, final Screen screen) {
        if (!(screen instanceof ControlsScreen)) {
            this.decoratedScreen = null;
            this.openButton = null;
        } else {
            List list;
            Object object = Reflect.get(F_BUTTONS, screen);
            if (object instanceof List && !(list = (List)object).isEmpty()) {
                if (screen != this.decoratedScreen || this.openButton == null) {
                    this.decoratedScreen = screen;
                    this.openButton = new OpenButton(0, 0, "Gamepad Controls...", new Runnable(){

                        @Override
                        public void run() {
                            minecraft.setScreen((Screen)new GamepadScreen(screen, GamepadMod.this.pad, GamepadMod.this.bindings));
                        }
                    });
                }
                if (!list.contains((Object)this.openButton)) {
                    list.add(this.openButton);
                }
                this.openButton.xPosition = screen.width / 2 - 100;
                this.openButton.yPosition = screen.height / 6 + 144;
            }
        }
    }

    private void installCursor() {
        block13: {
            try {
                int n;
                int n2;
                BufferedImage bufferedImage;
                if (!Mouse.isCreated()) {
                    return;
                }
                InputStream inputStream = GamepadMod.class.getResourceAsStream("/assets/gamepad/pointer.png");
                if (inputStream == null) {
                    GamepadMod.warnOnce("cursorres", "pointer.png missing from the mod jar");
                    this.cursorInstalled = true;
                    return;
                }
                try {
                    bufferedImage = ImageIO.read(inputStream);
                }
                finally {
                    inputStream.close();
                }
                int n3 = bufferedImage.getWidth();
                int n4 = bufferedImage.getHeight();
                int n5 = Cursor.getMinCursorSize();
                int n6 = Cursor.getMaxCursorSize();
                if (n6 <= 0) {
                    GamepadMod.warnOnce("cursorsize", "native cursors are not supported here, using the system cursor");
                    this.cursorInstalled = true;
                    return;
                }
                boolean bl = (Cursor.getCapabilities() & 2) != 0;
                int n7 = Math.max(n5, Math.min(n3, n6));
                int n8 = Math.max(n5, Math.min(n4, n6));
                int[] nArray = new int[n7 * n8];
                int n9 = n7;
                int n10 = n8;
                int n11 = -1;
                int n12 = -1;
                for (n2 = 0; n2 < n8 && n2 < n4; ++n2) {
                    for (n = 0; n < n7 && n < n3; ++n) {
                        int n13 = bufferedImage.getRGB(n, n2);
                        int n14 = n13 >>> 24;
                        if (!bl) {
                            n13 = n14 >= 128 ? n13 | 0xFF000000 : 0;
                        }
                        nArray[n2 * n7 + n] = n13;
                        if (n14 < 128) continue;
                        n9 = Math.min(n9, n);
                        n11 = Math.max(n11, n);
                        n10 = Math.min(n10, n2);
                        n12 = Math.max(n12, n2);
                    }
                }
                n2 = n11 >= 0 ? (n9 + n11) / 2 : n7 / 2;
                n = n12 >= 0 ? (n10 + n12) / 2 : n8 / 2;
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(n7 * n8 * 4).order(ByteOrder.nativeOrder());
                IntBuffer intBuffer = byteBuffer.asIntBuffer();
                for (int i = n8 - 1; i >= 0; --i) {
                    intBuffer.put(nArray, i * n7, n7);
                }
                intBuffer.flip();
                nativeCursor = new Cursor(n7, n8, n2, n8 - 1 - n, 1, intBuffer, null);
                Mouse.setNativeCursor((Cursor)nativeCursor);
                this.cursorInstalled = true;
                ModLoader.log((String)("Gamepad: custom cursor installed (" + n7 + "x" + n8 + ")"));
            }
            catch (Throwable throwable) {
                GamepadMod.warnOnce("cursor", "could not install the custom cursor (" + throwable + "), using the system cursor");
                if (this.cursorTries < 3) break block13;
                this.cursorInstalled = true;
            }
        }
    }

    private static final class OpenButton
    extends Button {
        private final Runnable action;

        OpenButton(int n, int n2, String string, Runnable runnable) {
            super(300, n, n2, 200, 20, string);
            this.action = runnable;
        }

        public boolean mousePressed(Minecraft minecraft, int n, int n2) {
            if (super.mousePressed(minecraft, n, n2)) {
                try {
                    minecraft.sndManager.playSoundFX("random.click", 1.0f, 1.0f);
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                this.action.run();
            }
            return false;
        }
    }
}

