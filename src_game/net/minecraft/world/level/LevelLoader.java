/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level;

import com.mojang.nbt.NBTBase;
import com.mojang.nbt.NBTTagCompound;
import com.mojang.nbt.NBTTagInt;
import com.mojang.nbt.NBTTagList;
import com.mojang.nbt.NBTTagShort;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Painting;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.GiantZombie;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.level.tile.tileentity.TileEntity;
import net.minecraft.world.level.tile.tileentity.TileEntityChest;
import net.minecraft.world.level.tile.tileentity.TileEntityFurnace;
import util.IProgressUpdate;

public abstract class LevelLoader {
    private IProgressUpdate guiLoading;

    public LevelLoader(IProgressUpdate guiLoading) {
        this.guiLoading = guiLoading;
    }

    public Level load(InputStream writeLevelTags) throws IOException {
        Level level = new Level();
        return this.loadData(level, writeLevelTags);
    }

    public Level loadData(Level level, InputStream writeLevelTags) throws IOException {
        if (this.guiLoading != null) {
            this.guiLoading.beginLevelLoading("Loading level");
        }
        if (this.guiLoading != null) {
            this.guiLoading.levelLoadUpdate("Reading..");
        }
        NBTTagCompound levelTag = LevelLoader.writeLevelTags(writeLevelTags);
        NBTTagCompound about = levelTag.getCompoundTag("About");
        NBTTagCompound map = levelTag.getCompoundTag("Map");
        NBTTagCompound environment = levelTag.getCompoundTag("Environment");
        NBTTagList entities = levelTag.getTagList("Entities");
        short width = map.getShort("Width");
        short len = map.getShort("Length");
        short height = map.getShort("Height");
        if (this.guiLoading != null) {
            this.guiLoading.levelLoadUpdate("Preparing level..");
        }
        NBTTagList spawn = map.getTagList("Spawn");
        try {
            level.xSpawn = ((NBTTagInt)spawn.tagAt((int)0)).intValue;
            level.ySpawn = ((NBTTagInt)spawn.tagAt((int)1)).intValue;
            level.zSpawn = ((NBTTagInt)spawn.tagAt((int)2)).intValue;
        }
        catch (Exception e2) {
            level.xSpawn = ((NBTTagShort)spawn.tagAt((int)0)).shortValue;
            level.ySpawn = ((NBTTagShort)spawn.tagAt((int)1)).shortValue;
            level.zSpawn = ((NBTTagShort)spawn.tagAt((int)2)).shortValue;
        }
        level.authorName = about.getString("Author");
        level.name = about.getString("Name");
        level.createTime = about.getLong("CreatedOn");
        if (about.hasKey("Survival")) {
            level.survivalWorld = about.getBoolean("Survival");
        }
        level.cloudColor = environment.getInteger("CloudColor");
        level.skyColor = environment.getInteger("SkyColor");
        level.fogColor = environment.getInteger("FogColor");
        level.skyBrightness = environment.getByte("SkyBrightness");
        if (level.skyBrightness < 0) {
            level.skyBrightness = 0;
        }
        if (level.skyBrightness > 16) {
            level.skyBrightness = level.skyBrightness * 15 / 100;
        }
        level.cloudHeight = environment.getShort("CloudHeight");
        level.groundLevel = environment.getShort("SurroundingGroundHeight");
        level.waterLevel = environment.getShort("SurroundingWaterHeight");
        level.defaultFluid = environment.getByte("SurroundingWaterType");
        level.worldTime = environment.getShort("TimeOfDay");
        level.skylightSubtracted = level.getSkyBrightness();
        byte[] data = null;
        boolean old = false;
        if (map.hasKey("Data") && !map.hasKey("LightData")) {
            data = map.getByteArray("Data");
            old = true;
        } else {
            data = map.getByteArray("LightData");
        }
        level.setData(width, height, len, map.getByteArray("Blocks"), data, false);
        if (!old) {
            level.data = map.getByteArray("Data");
        }
        if (this.guiLoading != null) {
            this.guiLoading.levelLoadUpdate("Preparing entities..");
        }
        int i2 = 0;
        while (i2 < entities.tagCount()) {
            try {
                map = (NBTTagCompound)entities.tagAt(i2);
                String entityId = map.getString("id");
                Entity e3 = this.loadEntity(level, entityId);
                if (e3 != null) {
                    e3.readFromNBT(map);
                    level.spawnEntityInWorld(e3);
                } else {
                    System.out.println("Skipping unknown entity id \"" + entityId + "\"");
                }
            }
            catch (Exception ex) {
                System.out.println("Error reading entity");
                ex.printStackTrace();
            }
            ++i2;
        }
        NBTTagList tileEntities = levelTag.getTagList("TileEntities");
        int i3 = 0;
        while (i3 < tileEntities.tagCount()) {
            try {
                TileEntity tileEntity;
                String tileEntityId;
                environment = (NBTTagCompound)tileEntities.tagAt(i3);
                int pos = environment.getInteger("Pos");
                String tileEntityName = tileEntityId = environment.getString("id");
                if (tileEntityId.equals("Chest")) {
                    tileEntity = new TileEntityChest();
                } else if (tileEntityName.equals("Furnace")) {
                    tileEntity = new TileEntityFurnace();
                } else {
                    tileEntity = null;
                }
                if (tileEntity != null) {
                    int x2 = pos % 1024;
                    int y2 = (pos >> 10) % 1024;
                    int z2 = (pos >> 20) % 1024;
                    tileEntity.readFromNBT(environment);
                    level.setTileEntity(x2, y2, z2, tileEntity);
                } else {
                    System.out.println("Skipping unknown tile entity id \"" + tileEntityId + "\"");
                }
            }
            catch (Exception ex) {
                System.out.println("Error reading tileentity");
                ex.printStackTrace();
            }
            ++i3;
        }
        return level;
    }

    protected Entity loadEntity(Level world, String playerName) {
        return playerName.equals("Pig") ? new Pig(world) : (playerName.equals("Sheep") ? new Sheep(world) : (playerName.equals("Creeper") ? new Creeper(world) : (playerName.equals("Skeleton") ? new Skeleton(world) : (playerName.equals("Spider") ? new Spider(world) : (playerName.equals("Zombie") ? new Zombie(world) : (playerName.equals("Giant") ? new GiantZombie(world) : (playerName.equals("Item") ? new ItemEntity(world) : (playerName.equals("Painting") ? new Painting(world) : null))))))));
    }

    public final void save(Level world, OutputStream out, boolean progress) throws IOException {
        if (this.guiLoading != null && progress) {
            this.guiLoading.beginLevelLoading("Saving level");
        }
        if (this.guiLoading != null && progress) {
            this.guiLoading.levelLoadUpdate("Preparing level..");
        }
        NBTTagCompound environment = new NBTTagCompound();
        environment.setInteger("CloudColor", world.cloudColor);
        environment.setInteger("SkyColor", world.skyColor);
        environment.setInteger("FogColor", world.fogColor);
        environment.setByte("SkyBrightness", (byte)world.skyBrightness);
        environment.setShort("CloudHeight", (short)world.cloudHeight);
        environment.setShort("SurroundingGroundHeight", (short)world.groundLevel);
        environment.setShort("SurroundingWaterHeight", (short)world.waterLevel);
        environment.setByte("SurroundingGroundType", (byte)Tile.grass.id);
        environment.setByte("SurroundingWaterType", (byte)world.defaultFluid);
        environment.setShort("TimeOfDay", (short)world.worldTime);
        NBTTagCompound map = new NBTTagCompound();
        map.setShort("Width", (short)world.width);
        map.setShort("Length", (short)world.depth);
        map.setShort("Height", (short)world.height);
        map.setByteArray("Blocks", world.blocks);
        map.setByteArray("Data", world.data);
        map.setByteArray("LightData", world.lightData);
        NBTTagList nBTTagList5 = new NBTTagList();
        nBTTagList5.setTag(new NBTTagInt(world.xSpawn));
        nBTTagList5.setTag(new NBTTagInt(world.ySpawn));
        nBTTagList5.setTag(new NBTTagInt(world.zSpawn));
        map.setTag("Spawn", nBTTagList5);
        NBTTagCompound about = new NBTTagCompound();
        about.setString("Author", world.authorName);
        about.setString("Name", world.name);
        about.setLong("CreatedOn", world.createTime);
        about.setBoolean("Survival", world.survivalWorld);
        if (this.guiLoading != null && progress) {
            this.guiLoading.levelLoadUpdate("Preparing entities..");
        }
        NBTTagList entities = new NBTTagList();
        for (Entity e2 : world.entityMap.entities) {
            NBTTagCompound edata = new NBTTagCompound();
            e2.writeToNBT(edata);
            if (edata.emptyNBTMap()) continue;
            entities.setTag(edata);
        }
        NBTTagList tileEntities = new NBTTagList();
        for (int pos : world.tileEntities.keySet()) {
            NBTTagCompound tePos = new NBTTagCompound();
            tePos.setInteger("Pos", pos);
            world.tileEntities.get(pos).writeToNBT(tePos);
            tileEntities.setTag(tePos);
        }
        NBTTagCompound mcLevel = new NBTTagCompound();
        mcLevel.setKey("MinecraftLevel");
        mcLevel.setCompoundTag("About", about);
        mcLevel.setCompoundTag("Map", map);
        mcLevel.setCompoundTag("Environment", environment);
        mcLevel.setTag("Entities", entities);
        mcLevel.setTag("TileEntities", tileEntities);
        if (this.guiLoading != null && progress) {
            this.guiLoading.levelLoadUpdate("Writing..");
        }
        NBTTagCompound mcLevelTag = mcLevel;
        try (DataOutputStream fileOut = new DataOutputStream(new GZIPOutputStream(out));){
            NBTBase.writeTag(mcLevelTag, fileOut);
        }
        if (this.guiLoading != null && !progress) {
            this.guiLoading.levelLoadUpdate("Level saved");
        }
    }

    public final boolean save(Level level, String uri, String creator, String sessionId, String lvlName, int slot) {
        BufferedReader data;
        block12: {
            if (sessionId == null) {
                sessionId = "";
            }
            if (this.guiLoading != null) {
                this.guiLoading.beginLevelLoading("Saving level");
            }
            try {
                if (this.guiLoading != null) {
                    this.guiLoading.levelLoadUpdate("Compressing..");
                }
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                this.save(level, stream, true);
                stream.close();
                byte[] levelFileData = stream.toByteArray();
                this.guiLoading.levelLoadUpdate("Connecting..");
                HttpURLConnection con = (HttpURLConnection)new URL("http://" + uri + "/level/save.html").openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeUTF(creator);
                out.writeUTF(sessionId);
                out.writeUTF(lvlName);
                out.writeByte(slot);
                out.writeInt(levelFileData.length);
                this.guiLoading.levelLoadUpdate("Saving..");
                out.write(levelFileData);
                out.close();
                data = new BufferedReader(new InputStreamReader(con.getInputStream()));
                if (data.readLine().equalsIgnoreCase("ok")) break block12;
                if (this.guiLoading != null) {
                    this.guiLoading.levelLoadUpdate("Failed: " + data.readLine());
                }
                data.close();
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException err) {
                    err.printStackTrace();
                }
                return false;
            }
            catch (Exception e2) {
                e2.printStackTrace();
                if (this.guiLoading != null) {
                    this.guiLoading.levelLoadUpdate("Failed!");
                }
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException err) {
                    err.printStackTrace();
                }
                return false;
            }
        }
        try {
            if (data != null) {
                data.close();
            }
        } catch (Exception ignored) {
        }
        return true;
    }

    public final Level load(String string1, String string2, int i3) {
        if (this.guiLoading != null) {
            this.guiLoading.beginLevelLoading("Loading level");
        }
        try {
            DataInputStream in;
            if (this.guiLoading != null) {
                this.guiLoading.levelLoadUpdate("Connecting..");
            }
            HttpURLConnection con = (HttpURLConnection)new URL("http://" + string1 + "/level/load.html?id=" + i3 + "&user=" + string2).openConnection();
            con.setDoInput(true);
            if (this.guiLoading != null) {
                this.guiLoading.levelLoadUpdate("Loading..");
            }
            if ((in = new DataInputStream(con.getInputStream())).readUTF().equalsIgnoreCase("ok")) {
                return this.load(in);
            }
            if (this.guiLoading != null) {
                this.guiLoading.levelLoadUpdate("Failed: " + in.readUTF());
            }
            in.close();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException err) {
                err.printStackTrace();
            }
            return null;
        }
        catch (Exception e2) {
            e2.printStackTrace();
            if (this.guiLoading != null) {
                this.guiLoading.levelLoadUpdate("Failed!");
            }
            try {
                Thread.sleep(3000L);
            }
            catch (InterruptedException err) {
                err.printStackTrace();
            }
            return null;
        }
    }

    public static void saveGzipped(Level level0, OutputStream os) {
        try {
            DataOutputStream dos = new DataOutputStream(new GZIPOutputStream(os));
            dos.writeInt(656127880);
            dos.writeByte(2);
            ObjectOutputStream oos = new ObjectOutputStream(dos);
            oos.writeObject(level0);
            oos.close();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static byte[] loadGzipped(InputStream in) {
        try {
            DataInputStream dis = new DataInputStream(new GZIPInputStream(in));
            byte[] data = new byte[dis.readInt()];
            dis.readFully(data);
            dis.close();
            return data;
        }
        catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static NBTTagCompound writeLevelTags(InputStream inputstream) throws IOException {
        NBTTagCompound tags;
        try (DataInputStream dis = new DataInputStream(new GZIPInputStream(inputstream));){
            NBTBase tag = NBTBase.readTag(dis);
            if (!(tag instanceof NBTTagCompound)) {
                throw new IOException("Root tag must be a named compound tag");
            }
            tags = (NBTTagCompound)tag;
        }
        return tags;
    }
}

