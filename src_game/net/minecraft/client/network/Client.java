/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.network;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gamemode.MultiplayerGamemode;
import net.minecraft.client.gui.DeathScreen;
import net.minecraft.client.gui.ErrorScreen;
import net.minecraft.client.network.ClientNetworkPlayer;
import net.minecraft.client.network.LevelClient;
import net.minecraft.client.network.NetworkPlayer;
import net.minecraft.client.particle.PickupEffect;
import net.minecraft.network.NetworkConnection;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.ArmAnimationPacket;
import net.minecraft.network.packet.BlockChangePacket;
import net.minecraft.network.packet.ChatPacket;
import net.minecraft.network.packet.CloseWindowPacket;
import net.minecraft.network.packet.CollectPacket;
import net.minecraft.network.packet.DestroyEntityPacket;
import net.minecraft.network.packet.DisconnectPlayerPacket;
import net.minecraft.network.packet.EntityFirePacket;
import net.minecraft.network.packet.EntityFlyingPacket;
import net.minecraft.network.packet.EntityMetadataPacket;
import net.minecraft.network.packet.EntityStatusPacket;
import net.minecraft.network.packet.EntityTeleportPacket;
import net.minecraft.network.packet.EntityVelocityPacket;
import net.minecraft.network.packet.ExplosionPacket;
import net.minecraft.network.packet.GamemodePacket;
import net.minecraft.network.packet.HandshakePacket;
import net.minecraft.network.packet.ItemSpawnPacket;
import net.minecraft.network.packet.KeepAlivePacket;
import net.minecraft.network.packet.LevelDataChunkPacket;
import net.minecraft.network.packet.LevelFinalizePacket;
import net.minecraft.network.packet.LevelInitPacket;
import net.minecraft.network.packet.LevelMetadataChunkPacket;
import net.minecraft.network.packet.LevelSettingsPacket;
import net.minecraft.network.packet.LoginPacket;
import net.minecraft.network.packet.MicrophonePacket;
import net.minecraft.network.packet.MobSpawnPacket;
import net.minecraft.network.packet.OpenWindowPacket;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PaintingPacket;
import net.minecraft.network.packet.PlayerFlyingPacket;
import net.minecraft.network.packet.PlayerInventoryPacket;
import net.minecraft.network.packet.PlayerSpawnPacket;
import net.minecraft.network.packet.RevivePacket;
import net.minecraft.network.packet.SetSlotPacket;
import net.minecraft.network.packet.SetUserTypePacket;
import net.minecraft.network.packet.SoundEffectPacket;
import net.minecraft.network.packet.SpawnPositionPacket;
import net.minecraft.network.packet.TimePacket;
import net.minecraft.network.packet.TransactionPacket;
import net.minecraft.network.packet.UpdateHealthPacket;
import net.minecraft.network.packet.UpdateProgressbarPacket;
import net.minecraft.network.packet.VehicleSpawnPacket;
import net.minecraft.network.packet.WindowItemsPacket;
import net.minecraft.world.container.Container;
import net.minecraft.world.container.InventoryBasic;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Painting;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.LevelLoader;
import net.minecraft.world.level.data.EntityList;
import net.minecraft.world.level.data.WatchableObject;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.level.tile.tileentity.TileEntityFurnace;
import util.Mth;

public class Client
extends PacketListener {
    private NetworkConnection conn;
    private boolean disconnected = false;
    protected Minecraft craft;
    private ByteArrayOutputStream levelBuffer = null;
    private ByteArrayOutputStream levelMetadataBuffer = null;
    private ClientNetworkPlayer player;
    public boolean finishedConnecting = false;
    byte[] blocks;
    byte[] metadata;
    private LevelClient levelClient;
    private int ticks = 0;
    private boolean started = false;
    private boolean audioLoaded = false;
    private ArrayList<String> names = new ArrayList();

    public Client(Minecraft craft, String ip, int port) throws IOException {
        this.craft = craft;
        Socket sock = new Socket(InetAddress.getByName(ip), port);
        this.conn = new NetworkConnection(sock, "Client", this);
        this.player = new ClientNetworkPlayer(this.craft, this);
    }

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
    public void handleHandshake(HandshakePacket p2) {
        if (p2.secret.equals("-")) {
            this.addToQueue(new LoginPacket((byte)8, this.craft.user.name, this.craft.user.mpPass, (byte)66));
        } else {
            try {
                URL uri = new URL("http://www.minecraft.net/game/joinserver.jsp?user=" + this.craft.user.name + "&sessionId=" + this.craft.user.sessionId + "&serverId=" + p2.secret);
                BufferedReader reader = new BufferedReader(new InputStreamReader(uri.openStream()));
                String output = reader.readLine();
                reader.close();
                if (output.equalsIgnoreCase("ok")) {
                    this.addToQueue(new LoginPacket((byte)8, this.craft.user.name, this.craft.user.mpPass, (byte)66));
                } else {
                    this.disconnect("Failed to login " + output);
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
                this.disconnect("Internal client error: " + e2.toString());
            }
        }
    }

    @Override
    public void handleLogin(LoginPacket packet) {
        this.craft.hideScreen = true;
        this.craft.loadingScreen.beginLevelLoading(packet.usernameOrServerName);
        this.craft.loadingScreen.levelLoadUpdate(packet.mppassOrMotd);
        this.player.userType = packet.userStatus;
    }

    @Override
    public void handleLevelInit(LevelInitPacket p2) {
        this.craft.loadingScreen.setLoadingProgress(0);
        this.craft.hideScreen = true;
        this.craft.setLevel(null);
        this.levelBuffer = new ByteArrayOutputStream();
        this.levelMetadataBuffer = new ByteArrayOutputStream();
        System.out.println("Level init!");
    }

    @Override
    public void handleLevelDataChunk(LevelDataChunkPacket p2) {
        short len = p2.length;
        byte[] data = p2.data;
        byte progress = p2.percentComplete;
        this.craft.loadingScreen.setLoadingProgress(progress);
        this.levelBuffer.write(data, 0, len);
    }

    @Override
    public void handleLevelMetadataChunk(LevelMetadataChunkPacket p2) {
        short len = p2.length;
        byte[] data = p2.metadata;
        byte progress = p2.percentComplete;
        this.craft.loadingScreen.setLoadingProgress(progress);
        this.levelMetadataBuffer.write(data, 0, len);
    }

    @Override
    public void handleLevelFinalize(LevelFinalizePacket p2) {
        this.blocks = LevelLoader.loadGzipped(new ByteArrayInputStream(this.levelBuffer.toByteArray()));
        this.metadata = LevelLoader.loadGzipped(new ByteArrayInputStream(this.levelMetadataBuffer.toByteArray()));
        this.levelBuffer = null;
        this.levelMetadataBuffer = null;
        this.levelClient = new LevelClient(this);
        System.out.println("Finished!");
        this.levelClient.worldTime = p2.worldTime;
        this.levelClient.skyBrightness = p2.skyBrightness;
        this.levelClient.skylightSubtracted = p2.skylightSubtracted;
        this.levelClient.setData(p2.x, p2.z, p2.y, this.blocks, null, false);
        this.levelClient.data = this.metadata;
        this.levelClient.updateLighting();
        this.craft.loadingScreen.levelLoadUpdate("Lighting..");
        int i2 = 0;
        while (i2 < 10000) {
            this.levelClient.updateLighting();
            ++i2;
        }
        this.player.setWorld(this.levelClient);
        this.player.setInventoryContainer(this.levelClient);
        this.craft.player = this.player;
        this.craft.setLevel(this.levelClient);
        this.craft.gamemode = new MultiplayerGamemode(this.craft, this);
        this.craft.hideScreen = false;
        this.finishedConnecting = true;
        this.craft.setScreen(null);
    }

    @Override
    public void handleLevelSettings(LevelSettingsPacket p2) {
        this.levelClient.authorName = p2.authorName;
        this.levelClient.name = p2.name;
        this.levelClient.createTime = p2.createTime;
        this.levelClient.cloudColor = p2.cloudColor;
        this.levelClient.skyColor = p2.skyColor;
        this.levelClient.fogColor = p2.fogColor;
        this.levelClient.cloudHeight = p2.cloudHeight;
        this.levelClient.groundLevel = p2.groundLevel;
        this.levelClient.waterLevel = p2.waterLevel;
        this.levelClient.defaultFluid = p2.defaultFluid;
        this.levelClient.updateBorders(this.levelClient.width, this.levelClient.height, this.levelClient.depth);
    }

    @Override
    public void handleSpawnPosition(SpawnPositionPacket packet) {
        this.levelClient.xSpawn = packet.x;
        this.levelClient.ySpawn = packet.y;
        this.levelClient.zSpawn = packet.z;
    }

    @Override
    public void handleTime(TimePacket p2) {
        this.levelClient.worldTime = p2.newTime;
        int cfr_ignored_0 = this.levelClient.skylightSubtracted;
        int cfr_ignored_1 = p2.skylightSubtracted;
    }

    @Override
    public void handleChat(ChatPacket packet) {
        String msg = packet.message;
        this.craft.hud.addChatMessage(msg);
    }

    @Override
    public void handleSetUserType(SetUserTypePacket packet) {
        this.player.userType = packet.userType;
    }

    @Override
    public void handleDisconnectPlayer(DisconnectPlayerPacket packet) {
        this.conn.disconnect("Kicked");
        this.disconnected = true;
        this.craft.hideScreen = false;
        this.craft.setLevel(null);
        this.craft.setScreen(new ErrorScreen("Connection lost", packet.reason, true));
        this.craft.voiceChatters.clear();
    }

    public void addToQueue(Packet packet) {
        if (!this.disconnected) {
            this.conn.addToQueue(packet);
        }
    }

    @Override
    public void disconnect(String reason) {
        if (!this.disconnected) {
            this.disconnected = true;
            this.conn.interrupt();
            this.conn.disconnect("Closed");
            this.craft.setLevel(null);
            this.craft.setScreen(new ErrorScreen("Disconnected!", reason, true));
            this.craft.voiceChatters.clear();
        }
    }

    public List<String> getUsernames() {
        this.names.clear();
        this.names.trimToSize();
        this.names.add(this.craft.user.name);
        int i2 = 0;
        while (i2 < this.levelClient.playerEntities.size()) {
            Player p2 = (Player)this.levelClient.playerEntities.get(i2);
            if (p2 != null && p2 != this.player) {
                this.names.add(p2.username);
            }
            ++i2;
        }
        return this.names;
    }

    public void shutdown(Packet packet) {
        if (!this.disconnected) {
            this.conn.addToQueue(packet);
            this.conn.closeConnection();
            this.craft.voiceChatters.clear();
        }
    }

    public void closeConnection() {
        this.disconnected = true;
        this.conn.interrupt();
        this.conn.disconnect("Closed");
        this.craft.voiceChatters.clear();
    }

    @Override
    public void handleDefaultPacket(Packet packet) {
        this.craft.setLevel(null);
        this.craft.hideScreen = false;
        this.craft.setScreen(new ErrorScreen("Disconnected!", "Unexpected Packet: " + packet, true));
        this.closeConnection();
    }

    public void tick() {
        if (!this.disconnected) {
            ++this.ticks;
            if (this.ticks % 20 == 0) {
                this.conn.addToQueue(new KeepAlivePacket());
            }
            this.conn.tick();
        }
        this.conn.interrupt();
    }

    @Override
    public void handleBlockChange(BlockChangePacket packet) {
        if (this.levelClient == null) {
            return;
        }
        this.levelClient.handleBlockChange(packet.xPosition, packet.yPosition, packet.zPosition, packet.type, packet.metadata);
    }

    @Override
    public void handlePlayerSpawn(PlayerSpawnPacket packet) {
        if (this.levelClient == null) {
            return;
        }
        float xx = (float)packet.x / 32.0f;
        float yy = (float)packet.y / 32.0f;
        float zz = (float)packet.z / 32.0f;
        float xrot = (float)(packet.rotation * 360) / 256.0f;
        float yrot = (float)(packet.pitch * 360) / 256.0f;
        NetworkPlayer newPlayer = new NetworkPlayer(this.craft.level, packet.name);
        newPlayer.onlineOffset = 0.625f;
        newPlayer.serverPosX = packet.x;
        newPlayer.serverPosY = packet.y;
        newPlayer.serverPosZ = packet.z;
        newPlayer.entityID = packet.entityId;
        int itemId = packet.currentItem;
        newPlayer.inventory.mainInventory[newPlayer.inventory.currentItem] = itemId == 0 ? null : new ItemStack(itemId);
        newPlayer.absMoveTo(xx, yy + newPlayer.heightOffset, zz, xrot, yrot);
        this.levelClient.addEntity(packet.entityId, newPlayer);
    }

    @Override
    public void handleHealthUpdate(UpdateHealthPacket p2) {
        if (this.levelClient == null) {
            return;
        }
        this.player.hurtTo(p2.healthMP);
    }

    @Override
    public void handleArmAnimation(ArmAnimationPacket packet) {
        if (this.levelClient == null) {
            return;
        }
        Entity e2 = this.levelClient.getEntity(packet.entityId);
        if (e2 != null) {
            Player player = (Player)e2;
            player.swingItem();
        }
    }

    @Override
    public void handleFlying(PlayerFlyingPacket packet) {
        ClientNetworkPlayer player = this.player;
        float px = player.x;
        float py = player.y;
        float pz = player.z;
        float xr = player.xRot;
        float yr = player.yRot;
        if (packet.moving) {
            px = packet.xPosition;
            py = packet.yPosition;
            pz = packet.zPosition;
        }
        if (packet.rotating) {
            xr = packet.yaw;
            yr = packet.pitch;
        }
        player.ySlideOffset = 0.0f;
        player.zd = 0.0f;
        player.yd = 0.0f;
        player.xd = 0.0f;
        player.absMoveTo(px, py, pz, xr, yr);
        packet.xPosition = player.x;
        packet.yPosition = player.bb.y0;
        packet.zPosition = player.z;
        packet.stance = player.y;
        this.conn.addToQueue(packet);
        if (!this.started) {
            this.player.xo = this.player.x;
            this.player.yo = this.player.y;
            this.player.zo = this.player.z;
            this.started = true;
        }
    }

    @Override
    public void handleMobSpawn(MobSpawnPacket p2) {
        if (this.levelClient == null) {
            return;
        }
        float x2 = (float)p2.xPosition / 32.0f;
        float y2 = (float)p2.yPosition / 32.0f;
        float z2 = (float)p2.zPosition / 32.0f;
        float xr = (float)(p2.yaw * 360) / 256.0f;
        float yr = (float)(p2.pitch * 360) / 256.0f;
        LivingEntity e2 = (LivingEntity)EntityList.createEntity(p2.type, this.levelClient);
        e2.noPhysics = true;
        e2.onlineOffset = -e2.bbHeight * 0.5f;
        e2.serverPosX = p2.xPosition;
        e2.serverPosY = p2.yPosition;
        e2.serverPosZ = p2.zPosition;
        e2.entityID = p2.entityId;
        e2.absMoveTo(x2, y2, z2, xr, yr);
        e2.onlineEntity = true;
        this.levelClient.addEntity(p2.entityId, e2);
        List<WatchableObject> meta = p2.getMetadata();
        if (meta != null) {
            e2.getDataWatcher().updateWatchedObjectsFromList(meta);
        }
    }

    @Override
    public void handleEntityMetadata(EntityMetadataPacket p2) {
        if (this.levelClient == null) {
            return;
        }
        Entity e2 = this.levelClient.getEntity(p2.entityId);
        if (e2 != null && p2.getData() != null) {
            e2.getDataWatcher().updateWatchedObjectsFromList(p2.getData());
        }
    }

    @Override
    public void handleEntityTeleport(EntityTeleportPacket packet) {
        if (this.levelClient == null) {
            return;
        }
        Entity e2 = this.levelClient.getEntity(packet.entityId);
        if (e2 != null) {
            e2.serverPosX = packet.xPosition;
            e2.serverPosY = packet.yPosition;
            e2.serverPosZ = packet.zPosition;
            float x2 = (float)e2.serverPosX / 32.0f;
            float y2 = (float)e2.serverPosY / 32.0f;
            float z2 = (float)e2.serverPosZ / 32.0f;
            float xr = (float)(packet.yaw * 360) / 256.0f;
            float yr = (float)(packet.pitch * 360) / 256.0f;
            e2.lerpTo(x2, y2, z2, xr, yr, 3);
        }
    }

    @Override
    public void handleEntity(EntityFlyingPacket packet) {
        if (this.levelClient == null) {
            return;
        }
        Entity e2 = this.levelClient.getEntity(packet.entityId);
        if (e2 != null) {
            e2.serverPosX = packet.xPosition;
            e2.serverPosY = packet.yPosition;
            e2.serverPosZ = packet.zPosition;
            float x2 = (float)e2.serverPosX / 32.0f;
            float y2 = (float)e2.serverPosY / 32.0f;
            float z2 = (float)e2.serverPosZ / 32.0f;
            float xr = packet.rotating ? (float)(packet.yaw * 360) / 256.0f : e2.xRot;
            float yr = packet.rotating ? (float)(packet.pitch * 360) / 256.0f : e2.yRot;
            e2.lerpTo(x2, y2, z2, xr, yr, 3);
        }
    }

    @Override
    public void handleEntityVelocity(EntityVelocityPacket p2) {
        if (this.levelClient == null) {
            return;
        }
        Entity e2 = this.levelClient.getEntity(p2.entityId);
        if (p2.entityId == -1) {
            e2 = this.player;
        }
        if (e2 != null) {
            e2.setVelocity((float)p2.motionX / 8000.0f, (float)p2.motionY / 8000.0f, (float)p2.motionZ / 8000.0f);
        }
    }

    @Override
    public void handleEntityFire(EntityFirePacket p2) {
        if (this.levelClient == null) {
            return;
        }
        Entity e2 = this.levelClient.getEntity(p2.entityId);
        if (p2.entityId == -999) {
            e2 = this.craft.player;
        }
        if (e2 != null) {
            e2.fire = p2.fire;
        }
    }

    @Override
    public void handleEntityStatusUpdate(EntityStatusPacket p2) {
        if (this.levelClient == null) {
            return;
        }
        float xx = (float)p2.x / 32.0f;
        float zz = (float)p2.z / 32.0f;
        Entity e2 = this.levelClient.getEntity(p2.entityId);
        if (e2 != null) {
            e2.handleHealthUpdate(p2.entityStatus, xx, zz);
        }
    }

    @Override
    public void handleDestroyEntity(DestroyEntityPacket packet) {
        if (this.levelClient != null) {
            this.levelClient.removeEntity(packet.entityId);
        }
    }

    @Override
    public void handleItemSpawn(ItemSpawnPacket p2) {
        float x2 = (float)p2.xPosition / 32.0f;
        float y2 = (float)p2.yPosition / 32.0f;
        float z2 = (float)p2.zPosition / 32.0f;
        ItemEntity item = new ItemEntity(this.levelClient, x2, y2, z2, new ItemStack(p2.itemID, p2.count, p2.itemDamage));
        item.xd = (float)p2.rotation / 128.0f;
        item.yd = (float)p2.pitch / 128.0f;
        item.zd = (float)p2.roll / 128.0f;
        item.serverPosX = p2.xPosition;
        item.serverPosY = p2.yPosition;
        item.serverPosZ = p2.zPosition;
        item.xRot = p2.xRot;
        item.yRot = p2.yRot;
        item.entityID = p2.entityId;
        this.levelClient.addEntity(p2.entityId, item);
    }

    @Override
    public void handleCollect(CollectPacket packet) {
        if (this.levelClient == null) {
            return;
        }
        Entity item = this.levelClient.getEntity(packet.collectedEntityId);
        LivingEntity player = (LivingEntity)this.levelClient.getEntity(packet.collectorEntityId);
        if (player == null) {
            player = this.craft.player;
        }
        if (item != null) {
            this.levelClient.playSoundAtEntity(item, "random.pop", 0.2f, ((this.levelClient.random.nextFloat() - this.levelClient.random.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            this.craft.particles.addEffect(new PickupEffect(this.craft.level, item, player, -0.5f));
        }
    }

    @Override
    public void handleVehicleSpawn(VehicleSpawnPacket p2) {
        float x2 = (float)p2.xPosition / 32.0f;
        float y2 = (float)p2.yPosition / 32.0f;
        float z2 = (float)p2.zPosition / 32.0f;
        Entity e2 = null;
        int cfr_ignored_0 = p2.type;
        int cfr_ignored_1 = p2.type;
        int cfr_ignored_2 = p2.type;
        int cfr_ignored_3 = p2.type;
        if (p2.type == 60) {
            e2 = new Arrow(this.levelClient, x2, y2, z2);
        }
        if (p2.type == 63) {
            p2.field_28044_i = 0;
        }
        int cfr_ignored_4 = p2.type;
        int cfr_ignored_5 = p2.type;
        if (p2.type == 50) {
            e2 = new PrimedTnt(this.levelClient, x2, y2, z2);
        }
        if (e2 != null) {
            e2.serverPosX = p2.xPosition;
            e2.serverPosY = p2.yPosition;
            e2.serverPosZ = p2.zPosition;
            e2.xRot = 0.0f;
            e2.yRot = 0.0f;
            e2.entityID = p2.entityId;
            this.levelClient.addEntity(p2.entityId, e2);
            if (p2.field_28044_i > 0) {
                Entity eId;
                if (p2.type == 60 && (eId = this.levelClient.getEntity(p2.field_28044_i)) instanceof LivingEntity) {
                    ((Arrow)e2).owner = (LivingEntity)eId;
                }
                e2.setVelocity((float)p2.field_28047_e / 8000.0f, (float)p2.field_28046_f / 8000.0f, (float)p2.field_28045_g / 8000.0f);
            }
        }
    }

    @Override
    public void handlePainting(PaintingPacket p2) {
        Painting painting = new Painting(this.levelClient, p2.xPosition, p2.yPosition, p2.zPosition, p2.direction, p2.title);
        painting.entityID = p2.entityId;
        this.levelClient.addEntity(p2.entityId, painting);
    }

    @Override
    public void handlePlayerInventory(PlayerInventoryPacket packet) {
        if (this.levelClient == null) {
            return;
        }
        Entity e2 = this.levelClient.getEntity(packet.entityID);
        if (e2 != null) {
            e2.outfitWithItem(packet.slot, packet.itemID, packet.itemDamage);
        }
    }

    @Override
    public void handleOpenWindow(OpenWindowPacket p2) {
        if (p2.inventoryType == 0) {
            InventoryBasic inv = new InventoryBasic(p2.windowTitle, p2.slotsCount);
            this.player.displayGUIChest(inv);
            this.player.craftingInventory.windowId = p2.windowId;
        } else if (p2.inventoryType == 2) {
            TileEntityFurnace inv = new TileEntityFurnace();
            this.player.displayGUIFurnace(inv);
            this.player.craftingInventory.windowId = p2.windowId;
        } else if (p2.inventoryType == 3) {
            try {
                throw new IOException("Illegal window type!");
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        } else if (p2.inventoryType == 1) {
            ClientNetworkPlayer player = this.player;
            this.player.displayWorkbenchGUI(Mth.floor(player.x), Mth.floor(player.y), Mth.floor(player.z));
            this.player.craftingInventory.windowId = p2.windowId;
        }
    }

    @Override
    public void handleCloseWindow(CloseWindowPacket p2) {
        this.player.closeScreen();
    }

    @Override
    public void handleSetSlot(SetSlotPacket p2) {
        if (p2.windowId == -1) {
            this.player.inventory.setItemStack(p2.myItemStack);
        } else if (p2.windowId == 0 && p2.itemSlot >= 36 && p2.itemSlot < 45) {
            ItemStack var2 = this.player.inventorySlots.getSlot(p2.itemSlot).getStack();
            if (p2.myItemStack != null && (var2 == null || var2.stackSize < p2.myItemStack.stackSize)) {
                p2.myItemStack.animationsToGo = 5;
            }
            this.player.inventorySlots.putStackInSlot(p2.itemSlot, p2.myItemStack);
        } else if (p2.windowId == this.player.craftingInventory.windowId) {
            this.player.craftingInventory.putStackInSlot(p2.itemSlot, p2.myItemStack);
        }
    }

    @Override
    public void handleWindowItems(WindowItemsPacket p2) {
        if (p2.windowId == 0) {
            this.player.inventorySlots.putStacksInSlots(p2.itemStack);
        } else if (p2.windowId == this.player.craftingInventory.windowId) {
            this.player.craftingInventory.putStacksInSlots(p2.itemStack);
        }
    }

    @Override
    public void handleTransaction(TransactionPacket var1) {
        Container var2 = null;
        if (var1.windowId == 0) {
            var2 = this.player.inventorySlots;
        } else if (var1.windowId == this.player.craftingInventory.windowId) {
            var2 = this.player.craftingInventory;
        }
        if (var2 != null) {
            if (var1.field_20030_c) {
                var2.deleteBackup(var1.field_20028_b);
            } else {
                var2.rollbackToBackup(var1.field_20028_b);
                this.addToQueue(new TransactionPacket(var1.windowId, var1.field_20028_b, true));
            }
        }
    }

    @Override
    public void handleFurnaceProgress(UpdateProgressbarPacket p2) {
        if (this.player.craftingInventory != null && this.player.craftingInventory.windowId == p2.windowId) {
            this.player.craftingInventory.setContainerStatus(p2.progressBar, p2.progressBarValue);
        }
    }

    @Override
    public void handleExplosion(ExplosionPacket var1) {
        Explosion explosion = new Explosion(this.levelClient, null, var1.explosionX, var1.explosionY, var1.explosionZ, var1.explosionSize);
        explosion.destroyedBlockPositions = var1.destroyedBlockPositions;
        explosion.applyTerrain(true);
    }

    @Override
    public void handleMicrophoneAudio(MicrophonePacket p2) {
        Entity e2;
        if (!this.audioLoaded) {
            this.audioLoaded = true;
            System.out.println("Loaded audio, ready to listen");
        }
        if ((e2 = this.levelClient.getEntity(p2.entityId)) != null) {
            try {
                this.craft.send(this.craft.micThread.format, p2.audioData, e2);
                byte[] sorted = (byte[])p2.audioData.clone();
                Arrays.sort(sorted);
                e2.speaking = Mth.abs(sorted[p2.audioData.length - 1]) > 10.0f;
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    @Override
    public void handleReviving(RevivePacket p2) {
        if (!p2.allowsReviving) {
            this.craft.sndManager.playSoundFX("random.wood click", 1.0f, 1.0f);
            if (this.craft.screen instanceof DeathScreen) {
                ((DeathScreen)this.craft.screen).hardcore = true;
            }
        } else {
            Player player = p2.entityId != -1 ? (Player)this.levelClient.getEntity(p2.entityId) : this.craft.player;
            player.heightOffset = 1.62f;
            player.setSize(0.6f, 1.8f);
            player.health = 20;
            player.deathTime = 0;
            player.fire = 0;
            this.craft.setScreen(null);
        }
    }

    @Override
    public void handleGamemodePacket(GamemodePacket packet) {
        this.craft.gamemode.creativeMode = packet.gamemode == 1;
        this.levelClient.survivalWorld = packet.gamemode == 0;
    }

    @Override
    public void handleSoundEffect(SoundEffectPacket p2) {
        int id;
        Tile tile;
        float zz;
        float yy;
        float xx;
        if (p2.type == 2) {
            xx = (float)p2.x + 0.5f;
            yy = (float)p2.y + 0.5f;
            zz = (float)p2.z + 0.5f;
            Tile tile2 = Tile.tiles[this.craft.level.getTile(p2.x, p2.y, p2.z)];
            if (tile2 != null) {
                String name = tile2.stepSound.getSndName();
                float vol = (tile2.stepSound.soundVolume + 1.0f) / 2.0f;
                this.craft.level.playSoundAtPlayer(xx, yy, zz, name, vol, tile2.stepSound.soundPitch * 0.8f);
            }
        }
        if (p2.type == 1 && (tile = Tile.tiles[id = this.craft.level.getTile(p2.x, p2.y, p2.z)]) != null) {
            String name = tile.stepSound.stepSoundDir();
            float x0 = (float)p2.x + 0.5f;
            float y0 = (float)p2.y + 0.5f;
            float z0 = (float)p2.z + 0.5f;
            float vol = (tile.stepSound.soundVolume + 1.0f) / 2.0f;
            this.craft.level.playSoundAtPlayer(z0, x0, y0, name, vol, tile.stepSound.soundPitch * 0.8f);
        }
        if (p2.type == 3) {
            xx = (float)p2.x / 32.0f;
            yy = (float)p2.y / 32.0f;
            zz = (float)p2.z / 32.0f;
            this.craft.level.playSoundAtPlayer(xx, yy, zz, "random.bow", 1.0f, 1.0f / (this.craft.level.random.nextFloat() * 0.4f + 0.8f));
        }
    }
}

