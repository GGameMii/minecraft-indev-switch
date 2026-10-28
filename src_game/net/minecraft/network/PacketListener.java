/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network;

import net.minecraft.network.packet.ArmAnimationPacket;
import net.minecraft.network.packet.BlockChangePacket;
import net.minecraft.network.packet.BlockDigPacket;
import net.minecraft.network.packet.BlockItemSwitchPacket;
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
import net.minecraft.network.packet.PlaceTilePacket;
import net.minecraft.network.packet.PlayerFlyingPacket;
import net.minecraft.network.packet.PlayerInventoryPacket;
import net.minecraft.network.packet.PlayerSpawnPacket;
import net.minecraft.network.packet.RevivePacket;
import net.minecraft.network.packet.SetCreativeSlotPacket;
import net.minecraft.network.packet.SetSlotPacket;
import net.minecraft.network.packet.SetUserTypePacket;
import net.minecraft.network.packet.SoundEffectPacket;
import net.minecraft.network.packet.SpawnPositionPacket;
import net.minecraft.network.packet.TimePacket;
import net.minecraft.network.packet.TransactionPacket;
import net.minecraft.network.packet.UpdateHealthPacket;
import net.minecraft.network.packet.UpdateProgressbarPacket;
import net.minecraft.network.packet.UseEntityPacket;
import net.minecraft.network.packet.VehicleSpawnPacket;
import net.minecraft.network.packet.WindowClickPacket;
import net.minecraft.network.packet.WindowItemsPacket;

public abstract class PacketListener {
    public abstract boolean isClient();

    public void disconnect(String reason) {
    }

    public void handleDefaultPacket(Packet packet) {
    }

    public void handleLogin(LoginPacket loginPacket) {
    }

    public void handleDisconnectPlayer(DisconnectPlayerPacket p2) {
    }

    public void handleChat(ChatPacket p2) {
    }

    public void handleHandshake(HandshakePacket handshakePacket) {
    }

    public void handleLevelInit(LevelInitPacket levelInitPacket) {
    }

    public void handleLevelDataChunk(LevelDataChunkPacket levelDataChunkPacket) {
    }

    public void handleLevelFinalize(LevelFinalizePacket levelFinalizePacket) {
    }

    public void handleSetUserType(SetUserTypePacket setUserTypePacket) {
    }

    public void handleTime(TimePacket timePacket) {
    }

    public void handleLevelSettings(LevelSettingsPacket levelSettingsPacket) {
    }

    public void handleLevelMetadataChunk(LevelMetadataChunkPacket levelMetadataChunkPacket) {
    }

    public void handlePlaceTile(PlaceTilePacket placeTilePacket) {
    }

    public void handleBlockDig(BlockDigPacket blockDigPacket) {
    }

    public void handleBlockChange(BlockChangePacket blockChangePacket) {
    }

    public void handleSpawnPosition(SpawnPositionPacket spawnPositionPacket) {
    }

    public void handleHealthUpdate(UpdateHealthPacket updateHealthPacket) {
    }

    public void handleFlying(PlayerFlyingPacket playerFlyingPacket) {
    }

    public void handleEntityFire(EntityFirePacket entityFirePacket) {
    }

    public void handleEntity(EntityFlyingPacket entityPacket) {
    }

    public void handleEntityTeleport(EntityTeleportPacket entityTeleportPacket) {
    }

    public void handleEntityVelocity(EntityVelocityPacket entityVelocityPacket) {
    }

    public void handlePlayerSpawn(PlayerSpawnPacket playerSpawnPacket) {
    }

    public void handleArmAnimation(ArmAnimationPacket armAnimationPacket) {
    }

    public void handleDestroyEntity(DestroyEntityPacket destroyEntityPacket) {
    }

    public void handleMobSpawn(MobSpawnPacket mobSpawnPacket) {
    }

    public void handleEntityMetadata(EntityMetadataPacket entityMetadataPacket) {
    }

    public void handleItemSpawn(ItemSpawnPacket pickupSpawnPacket) {
    }

    public void handleEntityStatusUpdate(EntityStatusPacket entityStatusPacket) {
    }

    public void handleUseEntity(UseEntityPacket useEntityPacket) {
    }

    public void handleBlockItemSwitch(BlockItemSwitchPacket blockItemSwitchPacket) {
    }

    public void handlePlayerInventory(PlayerInventoryPacket playerInventoryPacket) {
    }

    public void handleClickWindow(WindowClickPacket windowClickPacket) {
    }

    public void handleTransaction(TransactionPacket transactionPacket) {
    }

    public void handleWindowItems(WindowItemsPacket windowItemsPacket) {
    }

    public void handleSetSlot(SetSlotPacket setSlotPacket) {
    }

    public void handleCloseWindow(CloseWindowPacket closeWindowPacket) {
    }

    public void handleOpenWindow(OpenWindowPacket openWindowPacket) {
    }

    public void handleFurnaceProgress(UpdateProgressbarPacket updateProgressbarPacket) {
    }

    public void handleCollect(CollectPacket collectPacket) {
    }

    public void handleVehicleSpawn(VehicleSpawnPacket vehicleSpawnPacket) {
        Thread.dumpStack();
    }

    public void handlePainting(PaintingPacket entityPaintingPacket) {
        Thread.dumpStack();
    }

    public void handleExplosion(ExplosionPacket explosionPacket) {
        Thread.dumpStack();
    }

    public void handleMicrophoneAudio(MicrophonePacket microphonePacket) {
        Thread.dumpStack();
    }

    public void handleReviving(RevivePacket p2) {
        Thread.dumpStack();
    }

    public void handleGamemodePacket(GamemodePacket p2) {
        Thread.dumpStack();
    }

    public void handleCreativeSetSlot(SetCreativeSlotPacket p2) {
        Thread.dumpStack();
    }

    public void handleSoundEffect(SoundEffectPacket p2) {
        Thread.dumpStack();
    }
}

