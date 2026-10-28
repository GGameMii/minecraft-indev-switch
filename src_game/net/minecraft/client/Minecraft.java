/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.Sys
 *  org.lwjgl.input.Controllers
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.ContextCapabilities
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.DisplayMode
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GLContext
 */
package net.minecraft.client;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;
import net.minecraft.client.ChatScreen;
import net.minecraft.client.EnumOS;
import net.minecraft.client.EnumOSMappingHelper;
import net.minecraft.client.GameOptions;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.MinecraftApplet;
import net.minecraft.client.MinecraftError;
import net.minecraft.client.MouseHelper;
import net.minecraft.client.PlayerLoader;
import net.minecraft.client.ThreadDownloadResources;
import net.minecraft.client.ThreadSleepForever;
import net.minecraft.client.Timer;
import net.minecraft.client.TitleScreen;
import net.minecraft.client.VoiceChat;
import net.minecraft.client.gamemode.CreativeGamemode;
import net.minecraft.client.gamemode.Gamemode;
import net.minecraft.client.gamemode.SurvivalGamemode;
import net.minecraft.client.gui.DeathScreen;
import net.minecraft.client.gui.ErrorScreen;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.HUD;
import net.minecraft.client.gui.PauseScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.inventory.BuildScreen;
import net.minecraft.client.gui.inventory.InventoryScreen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.network.Client;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.player.MovementInputFromOptions;
import net.minecraft.client.player.PlayerSP;
import net.minecraft.client.renderer.Chunk;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.ptexture.TextureFlamesFX;
import net.minecraft.client.renderer.ptexture.TextureGearsFX;
import net.minecraft.client.renderer.ptexture.TextureLavaFX;
import net.minecraft.client.renderer.ptexture.TextureWaterFX;
import net.minecraft.client.renderer.ptexture.TextureWaterFlowFX;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.network.User;
import net.minecraft.network.packet.HandshakePacket;
import net.minecraft.network.packet.SetCreativeSlotPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.LevelGen;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.HitResult;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import net.minecraft.client.controller.GamepadController;
import net.minecraft.client.controller.TouchController;

public class Minecraft
implements Runnable {
    public GamepadController gamepad;
    public TouchController touch;
    public Gamemode gamemode = new SurvivalGamemode(this);
    private boolean fullscreen = false;
    public int width;
    public int height;
    private Timer timer = new Timer(20.0f);
    public Level level;
    public LevelRenderer levelRenderer;
    public PlayerSP player;
    public EffectRenderer particles;
    public User user = null;
    public String minecraftUri;
    public Canvas mcCanvas;
    public boolean appletMode = true;
    public volatile boolean isGamePaused = false;
    public RenderEngine renderEngine;
    public Font font;
    public Screen screen = null;
    public LoadingScreenRenderer loadingScreen = new LoadingScreenRenderer(this);
    public GameRenderer gameRenderer = new GameRenderer(this);
    private ThreadDownloadResources downloadResourcesThread;
    private int ticksRan = 0;
    private int leftClickCounter = 0;
    private int tempWidth;
    private int tempHeight;
    public String loadMapUser = null;
    public int loadMapID = 0;
    public HUD hud;
    public boolean skipRenderWorld = false;
    public HitResult hitResult;
    public GameOptions options;
    private MinecraftApplet mcApplet;
    public SoundManager sndManager;
    public MouseHelper mouseHelper;
    public File mcDataDir;
    private String server;
    private int port;
    public Client networkClient;
    private TextureWaterFX textureWaterFX;
    private TextureLavaFX textureLavaFX;
    volatile boolean running;
    public String debug;
    public boolean mouseGrabbed;
    private int prevFrameTime;
    public boolean hideScreen = false;
    public VoiceChat micThread;
    public ArrayList<String> voiceChatters = new ArrayList();
    public TargetDataLine mic;
    public Mixer targetMixer;

    public Minecraft(Component component, Canvas canvas, MinecraftApplet minecraftApplet, int n2, int n3, boolean bl) {
        new ModelBiped(0.0f);
        this.hitResult = null;
        this.sndManager = new SoundManager();
        this.server = null;
        this.textureWaterFX = new TextureWaterFX();
        this.textureLavaFX = new TextureLavaFX();
        this.running = false;
        this.debug = "";
        this.mouseGrabbed = false;
        this.prevFrameTime = 0;
        this.tempWidth = n2;
        this.tempHeight = n3;
        this.fullscreen = bl;
        this.mcApplet = minecraftApplet;
        new ThreadSleepForever(this, "Timer hack thread");
        this.mcCanvas = canvas;
        this.width = n2;
        this.height = n3;
        this.fullscreen = bl;
    }

    public final void setScreen(Screen screen) {
        if (this.screen != null) {
            this.screen.closeScreen();
        }
        if (screen == null && this.level == null) {
            screen = new TitleScreen();
        } else if (screen == null && this.player.health <= 0) {
            screen = new DeathScreen();
        }
        this.screen = screen;
        if (screen != null) {
            this.releaseMouse();
            ScaledResolution scaledResolution = new ScaledResolution(this.width, this.height);
            int n2 = scaledResolution.getScaledWidth();
            int n3 = scaledResolution.getScaledHeight();
            screen.setWorldAndResolution(this, n2, n3);
            this.skipRenderWorld = false;
        } else {
            this.grabMouse();
        }
    }

    public final void shutdownMinecraftApplet() {
        try {
            if (this.downloadResourcesThread != null) {
                this.downloadResourcesThread.closeMinecraft();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        try {
            if (this.micThread != null) {
                this.micThread.closeInput();
                this.micThread.running = false;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        try {
            this.sndManager.closeMinecraft();
            Mouse.destroy();
            Keyboard.destroy();
        }
        finally {
            Display.destroy();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void run() {
        this.running = true;
        try {
            this.init();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.toString(), "Failed to start Minecraft", 0);
            return;
        }
        long l2 = System.currentTimeMillis();
        int n2 = 0;
        try {
            while (this.running) {
                if (this.level != null) {
                    this.level.updateLighting();
                }
                if (this.mcCanvas == null && Display.isCloseRequested()) {
                    this.running = false;
                }
                try {
                    if (this.isGamePaused) {
                        float f2 = this.timer.a;
                        this.timer.advanceTime();
                        this.timer.a = f2;
                    } else {
                        this.timer.advanceTime();
                    }
                    for (int i2 = 0; i2 < this.timer.ticks; ++i2) {
                        ++this.ticksRan;
                        this.tick();
                    }
                    GL11.glEnable((int)3553);
                    if (this.level != null) {
                        this.gamemode.setPartialTime(this.timer.a);
                    }
                    this.sndManager.setListener(this.player, this.timer.a);
                    this.gameRenderer.render(this.timer.a);
                    if (!Display.isActive()) {
                        if (this.fullscreen) {
                            this.toggleFullscreen();
                        }
                        Thread.sleep(10L);
                    }
                    if (!(this.mcCanvas == null || this.fullscreen || this.mcCanvas.getWidth() == this.width && this.mcCanvas.getHeight() == this.height)) {
                        this.width = this.mcCanvas.getWidth();
                        this.height = this.mcCanvas.getHeight();
                        this.resize(this.width, this.height);
                    }
                    if (this.options.limitFramerate) {
                        Thread.sleep(5L);
                    }
                    ++n2;
                    this.isGamePaused = this.screen != null && this.screen.doesGuiPauseGame() && !this.isOnlineClient();
                }
                catch (Exception exception) {
                    this.setScreen(new ErrorScreen("Client error", "The game broke! [" + exception + "]"));
                    exception.printStackTrace();
                    this.shutdownMinecraftApplet();
                    return;
                }
                while (System.currentTimeMillis() >= l2 + 1000L) {
                    this.debug = n2 + " fps, " + Chunk.chunksUpdated + " chunk updates";
                    Chunk.chunksUpdated = 0;
                    l2 += 1000L;
                    n2 = 0;
                }
            }
            return;
        }
        catch (MinecraftError minecraftError) {
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
        finally {
            this.shutdownMinecraftApplet();
        }
    }

    private void init() throws LWJGLException {
        File file;
        Object object;
        String string;
        if (this.mcCanvas != null) {
            Display.setParent((Canvas)this.mcCanvas);
        } else if (this.fullscreen) {
            Display.setFullscreen((boolean)true);
            this.width = Display.getDisplayMode().getWidth();
            this.height = Display.getDisplayMode().getHeight();
        } else {
            Display.setDisplayMode((DisplayMode)new DisplayMode(this.width, this.height));
        }
        Display.setTitle((String)"Minecraft Minecraft Indev");
        try {
            Display.create();
            System.out.println("LWJGL version: " + Sys.getVersion());
            System.out.println("GL RENDERER: " + GL11.glGetString((int)7937));
            System.out.println("GL VENDOR: " + GL11.glGetString((int)7936));
            System.out.println("GL VERSION: " + GL11.glGetString((int)7938));
            ContextCapabilities caps = GLContext.getCapabilities();
            System.out.println("OpenGL 3.0: " + caps.OpenGL30);
            System.out.println("OpenGL 3.1: " + caps.OpenGL31);
            System.out.println("OpenGL 3.2: " + caps.OpenGL32);
            System.out.println("ARB_compatibility: " + caps.GL_ARB_compatibility);
            if (caps.OpenGL32) {
                IntBuffer intBuf = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asIntBuffer();
                GL11.glGetInteger((int)37158, intBuf);
                int n2 = intBuf.get(0);
                System.out.println("PROFILE MASK: " + Integer.toBinaryString(n2));
                System.out.println("CORE PROFILE: " + ((n2 & 1) != 0));
                System.out.println("COMPATIBILITY PROFILE: " + ((n2 & 2) != 0));
            }
        }
        catch (LWJGLException lWJGLException) {
            lWJGLException.printStackTrace();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            Display.create();
        }
        Keyboard.create();
        Mouse.create();
        this.mouseHelper = new MouseHelper(this.mcCanvas);
        try {
            Controllers.create();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        GL11.glEnable((int)3553);
        GL11.glShadeModel((int)7425);
        GL11.glClearDepth((double)1.0);
        GL11.glEnable((int)2929);
        GL11.glDepthFunc((int)515);
        GL11.glEnable((int)3008);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glCullFace((int)1029);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glMatrixMode((int)5888);
        string = "minecraft";
        object = System.getProperty("user.home", ".");
        String string2 = System.getProperty("os.name").toLowerCase();
        switch (EnumOSMappingHelper.osValues[(string2.contains("win") ? EnumOS.windows : (string2.contains("mac") ? EnumOS.macos : (string2.contains("solaris") ? EnumOS.solaris : (string2.contains("sunos") ? EnumOS.solaris : (string2.contains("linux") ? EnumOS.linux : (string2.contains("unix") ? EnumOS.linux : EnumOS.unknown)))))).ordinal()]) {
            case 1: 
            case 2: {
                file = new File((String)object, '.' + string + '/');
                break;
            }
            case 3: {
                string2 = System.getenv("APPDATA");
                if (string2 != null) {
                    file = new File(string2, "." + string + '/');
                    break;
                }
                file = new File((String)object, '.' + string + '/');
                break;
            }
            case 4: {
                file = new File((String)object, "Library/Application Support/" + string);
                break;
            }
            default: {
                file = new File((String)object, string + '/');
            }
        }
        if (!file.exists() && !file.mkdirs()) {
            throw new RuntimeException("The working directory could not be created: " + file);
        }
        this.mcDataDir = file;
        this.options = new GameOptions(this, this.mcDataDir);
        if (this.user == null || this.user.name == null || this.user.name.startsWith("Player")) {
            this.user = new User(this.options.username != null ? this.options.username : "Steve", "");
        }
        this.sndManager.loadSoundSettings(this.options);
        this.renderEngine = new RenderEngine(this.options);
        this.renderEngine.addDynamicTexture(this.textureLavaFX);
        this.renderEngine.addDynamicTexture(this.textureWaterFX);
        this.renderEngine.addDynamicTexture(new TextureWaterFlowFX());
        this.renderEngine.addDynamicTexture(new TextureFlamesFX(0));
        this.renderEngine.addDynamicTexture(new TextureFlamesFX(1));
        this.renderEngine.addDynamicTexture(new TextureGearsFX(0));
        this.renderEngine.addDynamicTexture(new TextureGearsFX(1));
        RenderManager.instance.itemRenderer = new ItemRenderer(this);
        this.font = new Font(this.options, "/default.png", this.renderEngine);
        BufferUtils.createIntBuffer((int)256).clear().limit(256);
        this.levelRenderer = new LevelRenderer(this, this.renderEngine);
        GL11.glViewport((int)0, (int)0, (int)this.width, (int)this.height);
        boolean bl = false;
        if (this.server != null && this.user != null) {
            this.connectTo(this.server, this.port);
        } else if (this.loadMapUser != null && this.loadMapID != 0) {
            bl = this.loadLevel(this.loadMapUser, this.loadMapID);
        }
        if (!bl || this.level == null) {
            this.setScreen(new TitleScreen());
        }
        this.particles = new EffectRenderer(this.level, this.renderEngine);
        try {
            this.downloadResourcesThread = new ThreadDownloadResources(this.mcDataDir, this);
            this.downloadResourcesThread.start();
        }
        catch (Exception exception) {
            // empty catch block
        }
        try {
            this.micThread = new VoiceChat(this);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.hud = new HUD(this);
        this.gamepad = new GamepadController(this);
        this.touch = new TouchController(this);
    }

    public final void grabMouse() {
        if (Display.isActive() && !this.mouseGrabbed) {
            this.mouseGrabbed = true;
            this.mouseHelper.grabMouseCursor();
            this.setScreen(null);
            this.prevFrameTime = this.ticksRan + 10000;
        }
    }

    private void releaseMouse() {
        if (this.mouseGrabbed) {
            if (this.player != null) {
                this.player.movementInput.resetKeyState();
            }
            this.mouseGrabbed = false;
            this.mouseHelper.ungrabMouseCursor();
        }
    }

    public final void displayInGameMenu() {
        if (this.screen == null) {
            this.setScreen(new PauseScreen());
        }
    }

    public void clickMouse(int n2) {
        if (n2 != 0 || this.leftClickCounter <= 0) {
            ItemStack itemStack;
            if (n2 == 0) {
                this.player.swingItem();
            }
            if (this.hitResult == null) {
                if (n2 == 0 && !(this.gamemode instanceof CreativeGamemode)) {
                    this.leftClickCounter = 10;
                }
            } else if (this.hitResult.typeOfHit == 1) {
                if (n2 == 0) {
                    this.gamemode.attackEntity(this.player, this.hitResult.entityHit);
                }
                if (n2 == 1) {
                    this.gamemode.interactWithEntity(this.player, this.hitResult.entityHit);
                }
            } else if (this.hitResult.typeOfHit == 0) {
                int n3 = this.hitResult.x;
                int n4 = this.hitResult.y;
                int n5 = this.hitResult.z;
                int n6 = this.hitResult.f;
                Tile tile = Tile.tiles[this.level.getTile(n3, n4, n5)];
                if (n2 == 0) {
                    this.level.onTileHit(n3, n4, n5, this.hitResult.f);
                    if (tile != Tile.bedrock || this.player.userType >= 100) {
                        this.gamemode.clickBlock(n3, n4, n5, this.hitResult.f);
                    }
                } else {
                    int n7;
                    ItemStack itemStack2 = this.player.inventory.getCurrentItem();
                    int n8 = n7 = itemStack2 != null ? itemStack2.stackSize : 0;
                    if (this.gamemode.onPlayerRightClick(this.player, this.level, itemStack2, n3, n4, n5, n6)) {
                        this.player.swingItem();
                    }
                    if (itemStack2 == null) {
                        return;
                    }
                    if (itemStack2.stackSize == 0) {
                        this.player.inventory.mainInventory[this.player.inventory.currentItem] = null;
                    } else if (itemStack2.stackSize != n7) {
                        this.gameRenderer.itemRenderer.resetEquippedProgress();
                    }
                }
            }
            if (n2 != 1 || (itemStack = this.player.inventory.getCurrentItem()) == null || this.gamemode.sendUseItem(this.player, this.level, itemStack)) {
                // empty if block
            }
        }
    }

    public final void toggleFullscreen() {
        try {
            this.fullscreen = !this.fullscreen;
            System.out.println("Toggle fullscreen!");
            if (this.fullscreen) {
                Display.setDisplayMode((DisplayMode)Display.getDesktopDisplayMode());
                this.width = Display.getDisplayMode().getWidth();
                this.height = Display.getDisplayMode().getHeight();
            } else {
                if (this.mcCanvas != null) {
                    this.width = this.mcCanvas.getWidth();
                    this.height = this.mcCanvas.getHeight();
                } else {
                    this.width = this.tempWidth;
                    this.height = this.tempHeight;
                }
                Display.setDisplayMode((DisplayMode)new DisplayMode(this.tempWidth, this.tempHeight));
            }
            this.releaseMouse();
            Display.setFullscreen((boolean)this.fullscreen);
            Display.update();
            Thread.sleep(1000L);
            if (this.fullscreen) {
                this.grabMouse();
            }
            if (this.screen != null) {
                this.releaseMouse();
                this.resize(this.width, this.height);
            }
            System.out.println("Size: " + this.width + ", " + this.height);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void resize(int n2, int n3) {
        this.width = n2;
        this.height = n3;
        if (this.screen != null) {
            ScaledResolution scaledResolution = new ScaledResolution(n2, n3);
            n3 = scaledResolution.getScaledWidth();
            n2 = scaledResolution.getScaledHeight();
            this.screen.setWorldAndResolution(this, n3, n2);
        }
    }

    private void tick() {
        if (this.gamepad != null) {
            this.gamepad.update();
        }
        if (this.touch != null) {
            this.touch.update();
        }
        this.hud.updateChat();
        if (!this.isGamePaused && this.level != null) {
            this.gamemode.tick();
        }
        GL11.glBindTexture((int)3553, (int)this.renderEngine.getTexture("/terrain.png"));
        if (!this.isGamePaused) {
            this.renderEngine.updateDynamicTextures();
        }
        if (this.networkClient != null) {
            this.networkClient.tick();
        }
        if (this.screen == null && this.player != null && this.player.health <= 0) {
            this.setScreen(null);
        }
        if (this.screen == null || this.screen.allowUserInput) {
            int n2;
            int n3;
            while (Mouse.next()) {
                if (this.screen == null && (n3 = Mouse.getEventDWheel()) != 0) {
                    n2 = n3;
                    InventoryPlayer inventoryPlayer = this.player.inventory;
                    if (n3 > 0) {
                        n2 = 1;
                    }
                    if (n2 < 0) {
                        n2 = -1;
                    }
                    inventoryPlayer.currentItem -= n2;
                    while (inventoryPlayer.currentItem < 0) {
                        inventoryPlayer.currentItem += 9;
                    }
                    while (inventoryPlayer.currentItem >= 9) {
                        inventoryPlayer.currentItem -= 9;
                    }
                }
                if (this.screen == null) {
                    if (!this.mouseGrabbed && Mouse.getEventButtonState()) {
                        this.grabMouse();
                        continue;
                    }
                    if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
                        this.clickMouse(0);
                        this.prevFrameTime = this.ticksRan;
                    }
                    if (Mouse.getEventButton() == 1 && Mouse.getEventButtonState()) {
                        this.clickMouse(1);
                        this.prevFrameTime = this.ticksRan;
                    }
                    if (Mouse.getEventButton() != 2 || !Mouse.getEventButtonState() || this.hitResult == null || !this.gamemode.creativeMode) continue;
                    int n4 = this.level.getTile(this.hitResult.x, this.hitResult.y, this.hitResult.z);
                    if (n4 == Tile.grass.id) {
                        n4 = Tile.dirt.id;
                    }
                    if (n4 == Tile.bedrock.id) {
                        n4 = Tile.cobblestone.id;
                    }
                    boolean bl = n4 != Tile.waterSource.id && n4 != Tile.lavaSource.id && n4 != Tile.lavaCalm.id && n4 != Tile.waterCalm.id && n4 != Tile.water.id && n4 != Tile.lava.id;
                    this.player.inventory.setCurrentItem(n4, 0, bl);
                    if (!this.isOnlineClient()) continue;
                    this.networkClient.addToQueue(new SetCreativeSlotPacket(-999, new ItemStack(n4)));
                    continue;
                }
                if (this.screen == null) continue;
                this.screen.handleMouseInput();
            }
            if (this.leftClickCounter > 0) {
                --this.leftClickCounter;
            }
            while (true) {
                int n5;
                if (!Keyboard.next()) {
                    boolean bl;
                    if (this.screen == null) {
                        if (Mouse.isButtonDown((int)0) && (float)(this.ticksRan - this.prevFrameTime) >= this.timer.ticksPerSecond / 4.0f && this.mouseGrabbed) {
                            this.clickMouse(0);
                            this.prevFrameTime = this.ticksRan;
                        }
                        if (Mouse.isButtonDown((int)1) && (float)(this.ticksRan - this.prevFrameTime) >= this.timer.ticksPerSecond / 4.0f && this.mouseGrabbed) {
                            this.clickMouse(1);
                            this.prevFrameTime = this.ticksRan;
                        }
                    }
                    boolean bl2 = bl = this.screen == null && Mouse.isButtonDown((int)0) && this.mouseGrabbed;
                    if (this.gamemode.creativeMode || this.leftClickCounter > 0) break;
                    if (bl && this.hitResult != null && this.hitResult.typeOfHit == 0) {
                        n2 = this.hitResult.x;
                        int n6 = this.hitResult.y;
                        n5 = this.hitResult.z;
                        this.gamemode.sendBlockRemoving(n2, n6, n5, this.hitResult.f);
                        this.particles.addBlockHitEffects(n2, n6, n5, this.hitResult.f);
                        break;
                    }
                    this.gamemode.resetBlockRemoving();
                    break;
                }
                PlayerSP playerSP = this.player;
                n5 = Keyboard.getEventKey();
                boolean bl = Keyboard.getEventKeyState();
                n2 = n5;
                playerSP.movementInput.checkKeyForMovementInput(n2, bl);
                if (!Keyboard.getEventKeyState()) continue;
                if (Keyboard.getEventKey() == 87) {
                    this.toggleFullscreen();
                    continue;
                }
                if (this.screen != null) {
                    this.screen.handleKeyboardInput();
                } else {
                    if (Keyboard.getEventKey() == 1) {
                        this.displayInGameMenu();
                    }
                    if (Keyboard.getEventKey() == 65) {
                        this.gameRenderer.renderIsometricScreenshot();
                    }
                    if (this.gamemode instanceof CreativeGamemode || this.gamemode.creativeMode) {
                        if (Keyboard.getEventKey() == this.options.keyBindLoad.keyCode) {
                            this.player.preparePlayerToSpawn();
                        }
                        if (Keyboard.getEventKey() == this.options.keyBindSave.keyCode) {
                            this.level.setSpawnLocation((int)this.player.x, (int)this.player.y, (int)this.player.z, this.player.xRot);
                            this.player.preparePlayerToSpawn();
                        }
                    }
                    if (Keyboard.getEventKey() == 63) {
                        boolean bl3 = this.options.thirdPersonView = !this.options.thirdPersonView;
                    }
                    if (Keyboard.getEventKey() == this.options.keyBindInventory.keyCode) {
                        this.setScreen(new InventoryScreen(this.player));
                    }
                    if (Keyboard.getEventKey() == this.options.keyBindDrop.keyCode) {
                        this.player.dropCurrentItem();
                    }
                    if (Keyboard.getEventKey() == this.options.keyBindChat.keyCode && this.isOnlineClient()) {
                        this.setScreen(new ChatScreen());
                    }
                    if (Keyboard.getEventKey() == this.options.keyBindBuild.keyCode && this.gamemode.creativeMode) {
                        this.setScreen(new BuildScreen(this.player));
                    }
                }
                for (n3 = 0; n3 < 9; ++n3) {
                    if (Keyboard.getEventKey() != n3 + 2) continue;
                    this.player.inventory.currentItem = n3;
                }
                if (Keyboard.getEventKey() != this.options.keyBindToggleFog.keyCode) continue;
                this.options.setOptionValue(4, !Keyboard.isKeyDown((int)42) && !Keyboard.isKeyDown((int)54) ? 1 : -1);
            }
        }
        if (this.screen != null) {
            this.prevFrameTime = this.ticksRan + 10000;
        }
        if (this.screen != null) {
            Screen screen = this.screen;
            while (Mouse.next()) {
                screen.handleMouseInput();
            }
            while (Keyboard.next()) {
                screen.handleKeyboardInput();
            }
            if (this.screen != null) {
                this.screen.tick();
            }
        }
        if (this.level != null) {
            this.level.difficultySetting = this.options.difficulty;
            if (!this.isGamePaused) {
                this.gameRenderer.updateRenderer();
            }
            if (!this.isGamePaused) {
                this.levelRenderer.updateClouds();
            }
            if (!this.isGamePaused) {
                this.level.updateEntities();
            }
            if (!this.isGamePaused) {
                this.level.tick();
            }
            if (!this.isGamePaused) {
                this.level.createRandomTicks((int)this.player.x, (int)this.player.y, (int)this.player.z);
            }
            if (!this.isGamePaused) {
                this.particles.updateEffects();
            }
        }
    }

    public final void generateLevel(int n2, int n3, int n4, int n5, boolean bl) {
        this.setLevel(null);
        System.gc();
        String string = this.user != null ? this.user.name : "anonymous";
        LevelGen levelGen = new LevelGen(this.loadingScreen);
        levelGen.survival = bl;
        levelGen.islandGen = n4 == 1;
        levelGen.floatingGen = n4 == 2;
        levelGen.flatGen = n4 == 3;
        levelGen.levelType = n5;
        n4 = n2 = 128 << n2;
        int n6 = 64;
        if (n3 == 1) {
            n2 /= 2;
            n4 <<= 1;
        } else if (n3 == 2) {
            n4 = n2 /= 2;
            n6 = 256;
        }
        Level level = levelGen.generateLevel(string, n2, n4, n6);
        level.survivalWorld = bl;
        this.setLevel(level);
    }

    public final void setLevel(Level level) {
        if (this.level != null) {
            this.level.clearEntitiesInLevel();
        }
        this.level = level;
        if (level != null) {
            level.initTransient();
            this.gamemode.setLevel(level);
            if (!this.isOnlineClient()) {
                this.gamemode = level.survivalWorld ? new SurvivalGamemode(this) : new CreativeGamemode(this);
                this.player = (PlayerSP)level.findSubclassOf(PlayerSP.class);
                if (this.player == null) {
                    this.player = new PlayerSP(this, level, this.user);
                    this.player.preparePlayerToSpawn();
                    if (level != null) {
                        level.spawnEntityInWorld(this.player);
                    }
                }
            } else {
                this.player.preparePlayerToSpawn();
                level.spawnEntityInWorld(this.player);
            }
            if (this.player != null) {
                this.player.movementInput = new MovementInputFromOptions(this.options);
                this.gamemode.respawn(this.player);
            }
            if (this.levelRenderer != null) {
                this.levelRenderer.changeWorld(level);
            }
            if (this.particles != null) {
                this.particles.clearEffects(level);
            }
            this.textureWaterFX.textureId = 0;
            this.textureLavaFX.textureId = 0;
            int n2 = this.renderEngine.getTexture("/water.png");
            if (level.defaultFluid == Tile.water.id) {
                this.textureWaterFX.textureId = n2;
            } else {
                this.textureLavaFX.textureId = n2;
            }
        }
        System.gc();
    }

    public final boolean saveLevel(int n2, String string) {
        return new PlayerLoader(this, this.loadingScreen).save(this.level, this.minecraftUri, this.user.name, this.user.sessionId, string, n2);
    }

    public final boolean loadLevel(String string, int n2) {
        Level level = new PlayerLoader(this, this.loadingScreen).load(this.minecraftUri, string, n2);
        if (level == null) {
            return false;
        }
        this.setLevel(level);
        return true;
    }

    public static void start(String string, String string2) throws LWJGLException {
        Minecraft.startAndConnectTo(string, string2, null);
    }

    public static void startAndConnectTo(String string, String string2, String string3) {
        Frame frame = new Frame("Minecraft");
        Canvas canvas = new Canvas();
        frame.setLayout(new BorderLayout());
        frame.add((Component)canvas, "Center");
        canvas.setPreferredSize(new Dimension(1708, 960));
        frame.pack();
        frame.setLocationRelativeTo(null);
        final Minecraft minecraft = new Minecraft(frame, canvas, null, 854, 480, false);
        final Thread thread = new Thread((Runnable)minecraft, "Minecraft main thread");
        thread.setPriority(10);
        minecraft.minecraftUri = "www.minecraft.net";
        minecraft.user = string != null && string2 != null ? new User(string, string2) : new User("Steve", "");
        if (string3 != null) {
            String[] stringArray = string3.split(":");
            minecraft.joinServer(stringArray[0], Integer.parseInt(stringArray[1]));
        }
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                minecraft.running = false;
                try {
                    thread.join();
                }
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                System.exit(0);
            }
        });
        thread.start();
    }

    public static void main(String[] stringArray) throws LWJGLException {
        String string = null;
        if (stringArray.length > 0) {
            string = stringArray[0];
        }
        String string2 = "-";
        if (stringArray.length > 1) {
            string2 = stringArray[1];
        }
        Minecraft.start(string, string2);
    }

    public void joinServer(String string, int n2) {
        this.server = string;
        this.port = n2;
    }

    public void connectTo(String string, int n2) {
        try {
            this.level = null;
            this.networkClient = new Client(this, string, n2);
            try {
                this.networkClient.addToQueue(new HandshakePacket(this.user.name));
            }
            catch (Exception exception) {
                exception.printStackTrace();
                this.networkClient.disconnect("Internal client error: " + exception.toString());
            }
        }
        catch (IOException iOException) {
            this.hideScreen = false;
            this.networkClient = null;
            this.setScreen(new ErrorScreen("Failed to connect", "You failed to connect to the server. It's probably down!", true));
            return;
        }
    }

    public boolean isOnlineClient() {
        return this.networkClient != null;
    }

    public void send(AudioFormat audioFormat, byte[] byArray, Entity entity) throws InterruptedException {
        if (this.sndManager == null || !this.sndManager.loaded || !this.options.sound) {
            return;
        }
        if (entity instanceof Player) {
            Player player = (Player)entity;
            String string = "Mic" + player.username;
            if (!this.voiceChatters.contains(player.username)) {
                this.sndManager.sndSystem.rawDataStream(this.micThread.getAudioFormat(), true, string, entity.x, entity.y, entity.z, 1, 2048.0f);
                this.voiceChatters.add(player.username);
            }
            this.sndManager.sndSystem.setVolume(string, 2.0f);
            this.sndManager.sndSystem.setPosition(string, entity.x, entity.y, entity.z);
            this.sndManager.sndSystem.play(string);
            this.sndManager.sndSystem.feedRawAudioData(string, byArray);
        }
    }
}

