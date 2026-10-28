/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.Client;
import net.minecraft.client.player.PlayerSP;
import net.minecraft.network.packet.ArmAnimationPacket;
import net.minecraft.network.packet.BlockDigPacket;
import net.minecraft.network.packet.ChatPacket;
import net.minecraft.network.packet.CloseWindowPacket;
import net.minecraft.network.packet.PlayerFlyingPacket;
import net.minecraft.network.packet.PlayerLookMovePacket;
import net.minecraft.network.packet.PlayerLookPacket;
import net.minecraft.network.packet.PlayerPositionPacket;
import net.minecraft.world.entity.Entity;

public class ClientNetworkPlayer
extends PlayerSP {
    private Client connection;
    private boolean flashOnSetHealth = false;
    private float xLast;
    private float yLast1;
    private float yLast2;
    private float zLast;
    private float xRotLast;
    private float yRotLast;

    public ClientNetworkPlayer(Minecraft game, Client con) {
        super(game, game.level, game.user);
        this.connection = con;
        if (this.username != null) {
            this.skinUrl = "http://www.minecraft.net/skin/" + this.username + ".png";
        }
        this.entityID = -999;
    }

    public Client getClientConnection() {
        return this.connection;
    }

    @Override
    public void addChatMessage(String msg) {
        this.mc.networkClient.addToQueue(new ChatPacket(msg));
    }

    @Override
    public void swingItem() {
        super.swingItem();
        this.connection.addToQueue(new ArmAnimationPacket(this, (byte)1));
    }

    @Override
    public void dropCurrentItem() {
        this.connection.addToQueue(new BlockDigPacket(4, 0, 0, 0, 0));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.connection.finishedConnecting) {
            this.sendPosition();
        }
    }

    public void sendPosition() {
        boolean rot;
        float xdd = this.x - this.xLast;
        float ydd1 = this.bb.y0 - this.yLast1;
        float ydd2 = this.y - this.yLast2;
        float zdd = this.z - this.zLast;
        float rxdd = this.xRot - this.xRotLast;
        float rydd = this.yRot - this.yRotLast;
        boolean move = ydd1 != 0.0f || ydd2 != 0.0f || xdd != 0.0f || zdd != 0.0f;
        boolean bl = rot = rxdd != 0.0f || rydd != 0.0f;
        if (move && rot || this.ticks % 100 == 0) {
            this.connection.addToQueue(new PlayerLookMovePacket(this.x, this.bb.y0, this.y, this.z, this.xRot, this.yRot, this.onGround));
        } else if (move) {
            this.connection.addToQueue(new PlayerPositionPacket(this.x, this.bb.y0, this.y, this.z, this.onGround));
        } else if (rot) {
            this.connection.addToQueue(new PlayerLookPacket(this.xRot, this.yRot, this.onGround));
        } else {
            this.connection.addToQueue(new PlayerFlyingPacket(this.onGround));
        }
        if (move) {
            this.xLast = this.x;
            this.yLast1 = this.bb.y0;
            this.yLast2 = this.y;
            this.zLast = this.z;
        }
        if (rot) {
            this.xRotLast = this.xRot;
            this.yRotLast = this.yRot;
        }
    }

    @Override
    public void hurtTo(int newHealth) {
        if (this.flashOnSetHealth) {
            super.hurtTo(newHealth);
        } else {
            this.health = newHealth;
            this.flashOnSetHealth = true;
        }
    }

    @Override
    public void closeScreen() {
        this.connection.addToQueue(new CloseWindowPacket(this.craftingInventory.windowId));
        this.inventory.setItemStack(null);
        super.closeScreen();
    }

    @Override
    public void fall(float dist) {
    }

    @Override
    public void heal(int points) {
    }

    @Override
    public boolean hurt(Entity source, int dmg) {
        return false;
    }
}

