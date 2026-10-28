/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.level.tile.Tile;

public final class User {
    public static List<Tile> allowedTiles = new ArrayList<Tile>();
    public String name;
    public String sessionId;
    public String mpPass;

    public User(String string, String string2) {
        this.name = string;
        this.sessionId = string2;
        this.mpPass = "-";
    }

    static {
        allowedTiles.add(Tile.stone);
        allowedTiles.add(Tile.cobblestone);
        allowedTiles.add(Tile.brick);
        allowedTiles.add(Tile.dirt);
        allowedTiles.add(Tile.planks);
        allowedTiles.add(Tile.wood);
        allowedTiles.add(Tile.leaves);
        allowedTiles.add(Tile.torch);
        allowedTiles.add(Tile.stairSingle);
        allowedTiles.add(Tile.glass);
        allowedTiles.add(Tile.cobblestoneMossy);
        allowedTiles.add(Tile.sapling);
        allowedTiles.add(Tile.plantYellow);
        allowedTiles.add(Tile.plantRed);
        allowedTiles.add(Tile.mushroomBrown);
        allowedTiles.add(Tile.mushroomRed);
        allowedTiles.add(Tile.sand);
        allowedTiles.add(Tile.gravel);
        allowedTiles.add(Tile.sponge);
        allowedTiles.add(Tile.clothRed);
        allowedTiles.add(Tile.clothOrange);
        allowedTiles.add(Tile.clothYellow);
        allowedTiles.add(Tile.clothChartreuse);
        allowedTiles.add(Tile.clothGreen);
        allowedTiles.add(Tile.clothSpringGreen);
        allowedTiles.add(Tile.clothCyan);
        allowedTiles.add(Tile.clothCapri);
        allowedTiles.add(Tile.clothUltramarine);
        allowedTiles.add(Tile.clothViolet);
        allowedTiles.add(Tile.clothPurple);
        allowedTiles.add(Tile.clothMagenta);
        allowedTiles.add(Tile.clothRose);
        allowedTiles.add(Tile.clothDarkGray);
        allowedTiles.add(Tile.clothGray);
        allowedTiles.add(Tile.clothWhite);
        allowedTiles.add(Tile.oreCoal);
        allowedTiles.add(Tile.oreIron);
        allowedTiles.add(Tile.oreGold);
        allowedTiles.add(Tile.oreDiamond);
        allowedTiles.add(Tile.blockIron);
        allowedTiles.add(Tile.blockGold);
        allowedTiles.add(Tile.blockDiamond);
        allowedTiles.add(Tile.bookShelf);
        allowedTiles.add(Tile.tnt);
        allowedTiles.add(Tile.obsidian);
        allowedTiles.add(Tile.chest);
        allowedTiles.add(Tile.workbench);
        allowedTiles.add(Tile.furnace);
        System.out.println(allowedTiles.size());
    }
}

