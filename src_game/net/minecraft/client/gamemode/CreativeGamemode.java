/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gamemode;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gamemode.Gamemode;
import net.minecraft.network.User;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class CreativeGamemode
extends Gamemode {
    public CreativeGamemode(Minecraft game) {
        super(game);
        this.creativeMode = true;
    }

    @Override
    public final void respawn(Player player) {
        int i2 = 0;
        while (i2 < 9) {
            if (player.inventory.mainInventory[i2] == null) {
                this.mc.player.inventory.mainInventory[i2] = new ItemStack(User.allowedTiles.get((int)i2).id);
            } else {
                this.mc.player.inventory.mainInventory[i2].stackSize = 1;
            }
            ++i2;
        }
    }

    @Override
    public final boolean shouldDrawHUD() {
        return false;
    }

    @Override
    public final void setLevel(Level level) {
        super.setLevel(level);
    }

    @Override
    public final void tick() {
    }
}

