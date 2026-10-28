/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.ChestScreen;
import net.minecraft.client.gui.inventory.CraftingScreen;
import net.minecraft.client.gui.inventory.FurnaceScreen;
import net.minecraft.client.particle.PickupEffect;
import net.minecraft.client.player.MovementInput;
import net.minecraft.network.User;
import net.minecraft.world.IInventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.tileentity.TileEntityFurnace;

public class PlayerSP
extends Player {
    public MovementInput movementInput;
    protected Minecraft mc;

    public PlayerSP(Minecraft minecraft, Level world, User user) {
        super(world);
        this.mc = minecraft;
        if (user != null) {
            this.skinUrl = "http://www.minecraft.net/skin/" + user.name + ".png";
        }
        this.username = user.name;
    }

    @Override
    public final void updateState() {
        super.updateState();
        this.moveStrafing = this.movementInput.moveStrafe;
        this.moveForward = this.movementInput.moveForward;
        this.isJumping = this.movementInput.jump;
    }

    @Override
    public void livingTick() {
        this.movementInput.updatePlayerMoveState();
        super.livingTick();
    }

    @Override
    protected final String getEntityString() {
        return "LocalPlayer";
    }

    @Override
    public final void displayGUIChest(IInventory iInventory) {
        this.mc.setScreen(new ChestScreen(this.inventory, iInventory));
    }

    @Override
    public final void displayWorkbenchGUI(int x2, int y2, int z2) {
        this.mc.setScreen(new CraftingScreen(this.inventory, this.level, x2, y2, z2));
    }

    @Override
    public final void displayGUIFurnace(TileEntityFurnace furnace) {
        this.mc.setScreen(new FurnaceScreen(this.inventory, furnace));
    }

    @Override
    public final void onItemPickup(Entity entity, int i2) {
        this.mc.particles.addEffect(new PickupEffect(this.mc.level, entity, this, -0.5f));
    }

    @Override
    public void closeScreen() {
        super.closeScreen();
        this.mc.setScreen(null);
    }

    public void addChatMessage(String msg) {
        this.mc.hud.addChatMessage(msg);
    }

    public void hurtTo(int newHealth) {
        int dmg = this.health - newHealth;
        if (dmg <= 0) {
            this.health = newHealth;
            if (dmg < 0) {
                this.invulnerableTime = this.invulnerableDuration / 2;
            }
        } else {
            this.lastHurt = dmg;
            this.prevHealth = this.health;
            this.invulnerableTime = this.invulnerableDuration;
            this.actuallyHurt(dmg);
            this.hurtDuration = 10;
            this.hurtTime = 10;
        }
    }
}

