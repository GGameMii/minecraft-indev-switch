/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class WorkbenchTile
extends Tile {
    protected WorkbenchTile(int blockID) {
        super(58, Material.wood);
        this.tex = 59;
    }

    @Override
    public final int getTexture(int blockSide) {
        return blockSide == 1 ? this.tex - 16 : (blockSide == 0 ? Tile.planks.getTexture(0) : (blockSide != 2 && blockSide != 4 ? this.tex : this.tex + 1));
    }

    @Override
    public boolean blockActivated(Level level, int x2, int y2, int z2, Player player) {
        if (level.onlineLevel) {
            return true;
        }
        player.displayWorkbenchGUI(x2, y2, z2);
        return true;
    }
}

