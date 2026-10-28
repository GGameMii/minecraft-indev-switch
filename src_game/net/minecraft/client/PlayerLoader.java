/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.PlayerSP;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelLoader;
import util.IProgressUpdate;

public final class PlayerLoader
extends LevelLoader {
    private Minecraft mc;

    public PlayerLoader(Minecraft minecraft, IProgressUpdate guiLoading) {
        super(guiLoading);
        this.mc = minecraft;
    }

    @Override
    protected final Entity loadEntity(Level world, String playerName) {
        return playerName.equals("LocalPlayer") ? new PlayerSP(this.mc, world, this.mc.user) : super.loadEntity(world, playerName);
    }
}

