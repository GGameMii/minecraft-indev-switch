/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import com.mojang.nbt.NBTTagCompound;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.network.PacketListener;
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
import net.minecraft.network.packet.EntityLookPacket;
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
import net.minecraft.network.packet.PaintingPacket;
import net.minecraft.network.packet.PlaceTilePacket;
import net.minecraft.network.packet.PlayerFlyingPacket;
import net.minecraft.network.packet.PlayerInventoryPacket;
import net.minecraft.network.packet.PlayerLookMovePacket;
import net.minecraft.network.packet.PlayerLookPacket;
import net.minecraft.network.packet.PlayerPositionPacket;
import net.minecraft.network.packet.PlayerSpawnPacket;
import net.minecraft.network.packet.RelEntityMoveLookPacket;
import net.minecraft.network.packet.RelEntityMovePacket;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.loader.LevelCompresser;

public abstract class Packet {
    public static final Charset CODEPAGE_437 = Charset.forName("Cp437");
    protected static final int BYTE = 1;
    protected static final int BOOL = 1;
    protected static final int SHORT = 2;
    protected static final int INT = 4;
    protected static final int FLOAT = 4;
    protected static final int LONG = 8;
    protected static final int DOUBLE = 8;
    protected static final int STRING = 64;
    protected static final int BYTE_ARRAY = 1024;
    protected static final int LARGE_ARRAY = 4096;
    protected static final byte PADDING_BYTE = 32;
    protected static final byte LOGINPACKET_PADDING_BYTE_EX = 66;
    private static Map<Integer, Class<? extends Packet>> idClassMap = new HashMap<Integer, Class<? extends Packet>>();
    private static Map<Class<? extends Packet>, Integer> classIdMap = new HashMap<Class<? extends Packet>, Integer>();

    static {
        Packet.register(0, LoginPacket.class);
        Packet.register(1, KeepAlivePacket.class);
        Packet.register(2, LevelInitPacket.class);
        Packet.register(3, LevelDataChunkPacket.class);
        Packet.register(4, LevelFinalizePacket.class);
        Packet.register(5, PlaceTilePacket.class);
        Packet.register(6, BlockChangePacket.class);
        Packet.register(7, SpawnPositionPacket.class);
        Packet.register(8, PlayerFlyingPacket.class);
        Packet.register(9, PlayerPositionPacket.class);
        Packet.register(10, PlayerLookMovePacket.class);
        Packet.register(11, PlayerLookPacket.class);
        Packet.register(12, DisconnectPlayerPacket.class);
        Packet.register(13, ChatPacket.class);
        Packet.register(14, SetUserTypePacket.class);
        Packet.register(16, TimePacket.class);
        Packet.register(17, LevelMetadataChunkPacket.class);
        Packet.register(18, LevelSettingsPacket.class);
        Packet.register(19, BlockDigPacket.class);
        Packet.register(20, BlockItemSwitchPacket.class);
        Packet.register(21, PlayerSpawnPacket.class);
        Packet.register(22, EntityFlyingPacket.class);
        Packet.register(23, EntityTeleportPacket.class);
        Packet.register(24, EntityLookPacket.class);
        Packet.register(25, RelEntityMovePacket.class);
        Packet.register(26, RelEntityMoveLookPacket.class);
        Packet.register(27, DestroyEntityPacket.class);
        Packet.register(28, PlayerInventoryPacket.class);
        Packet.register(29, ArmAnimationPacket.class);
        Packet.register(30, CloseWindowPacket.class);
        Packet.register(31, OpenWindowPacket.class);
        Packet.register(32, SetSlotPacket.class);
        Packet.register(33, WindowItemsPacket.class);
        Packet.register(34, UpdateProgressbarPacket.class);
        Packet.register(35, WindowClickPacket.class);
        Packet.register(36, TransactionPacket.class);
        Packet.register(37, CollectPacket.class);
        Packet.register(38, UpdateHealthPacket.class);
        Packet.register(39, ItemSpawnPacket.class);
        Packet.register(40, VehicleSpawnPacket.class);
        Packet.register(41, MobSpawnPacket.class);
        Packet.register(42, UseEntityPacket.class);
        Packet.register(43, EntityVelocityPacket.class);
        Packet.register(44, EntityMetadataPacket.class);
        Packet.register(45, EntityStatusPacket.class);
        Packet.register(46, GamemodePacket.class);
        Packet.register(47, RevivePacket.class);
        Packet.register(48, PaintingPacket.class);
        Packet.register(49, HandshakePacket.class);
        Packet.register(50, SetCreativeSlotPacket.class);
        Packet.register(51, ExplosionPacket.class);
        Packet.register(54, EntityFirePacket.class);
        Packet.register(55, MicrophonePacket.class);
        Packet.register(56, SoundEffectPacket.class);
    }

    public static Packet readPacket(DataInputStream dis) throws IOException {
        int id;
        Packet packet;
        block4: {
            packet = null;
            try {
                id = dis.read();
                if (id != -1) break block4;
                return null;
            }
            catch (EOFException eof) {
                System.out.println("Reached end of stream");
                return null;
            }
        }
        packet = Packet.createPacket(id);
        if (packet == null) {
            throw new IOException("Bad opcode " + id);
        }
        packet.read(dis);
        return packet;
    }

    public static void writePacket(Packet packet, DataOutputStream dos) throws IOException {
        dos.write(packet.getId());
        packet.write(dos);
    }

    protected ItemStack readItemStack(DataInputStream in) throws IOException {
        ItemStack itemstack = null;
        short word0 = in.readShort();
        if (word0 >= 0) {
            byte byte0 = in.readByte();
            short word1 = in.readShort();
            itemstack = new ItemStack(word0, (int)byte0, (int)word1);
            if (Item.itemsList[word0].isDamageable()) {
                itemstack.stackTagCompound = this.readNBTTagCompound(in);
            }
        }
        return itemstack;
    }

    protected void writeItemStack(ItemStack item, DataOutputStream out) throws IOException {
        if (item == null) {
            out.writeShort(-1);
        } else {
            out.writeShort(item.itemID);
            out.writeByte(item.stackSize);
            out.writeShort(item.itemDamage);
            if (item.getItem().isDamageable()) {
                this.writeNBTTagCompound(item.stackTagCompound, out);
            }
        }
    }

    protected NBTTagCompound readNBTTagCompound(DataInputStream in) throws IOException {
        short word0 = in.readShort();
        if (word0 < 0) {
            return null;
        }
        byte[] abyte0 = new byte[word0];
        in.readFully(abyte0);
        return LevelCompresser.decompress(abyte0);
    }

    protected void writeNBTTagCompound(NBTTagCompound nbt, DataOutputStream out) throws IOException {
        if (nbt == null) {
            out.writeShort(-1);
        } else {
            byte[] abyte0 = LevelCompresser.compress(nbt);
            out.writeShort((short)abyte0.length);
            out.write(abyte0);
        }
    }

    public static Packet createPacket(int id) {
        try {
            Class<? extends Packet> pack = idClassMap.get(id);
            return pack == null ? null : pack.getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception e2) {
            if (e2 instanceof NoSuchMethodException) {
                System.err.println("Packet id " + Integer.toHexString(id) + " is missing the no-arg constructor!");
            } else {
                System.err.println("Failed to create packet: " + e2);
            }
            return null;
        }
    }

    public int getId() {
        return classIdMap.get(this.getClass());
    }

    public abstract void read(DataInputStream var1) throws IOException;

    public abstract void write(DataOutputStream var1) throws IOException;

    public abstract void handle(PacketListener var1);

    public abstract int size();

    public static String readString(DataInputStream dis) throws IOException {
        byte[] buf = new byte[64];
        dis.readFully(buf);
        return new String(buf, CODEPAGE_437).trim();
    }

    public static void writeString(DataOutputStream dos, String str) throws IOException {
        byte[] buf = new byte[64];
        byte[] stringBuf = str.getBytes(CODEPAGE_437);
        Arrays.fill(buf, (byte)32);
        int i2 = 0;
        while (i2 < 64 && i2 < stringBuf.length) {
            buf[i2] = (byte)(stringBuf[i2] & 0xFF);
            ++i2;
        }
        i2 = stringBuf.length;
        while (i2 < 64) {
            buf[i2] = 32;
            ++i2;
        }
        dos.write(buf);
    }

    public static byte[] readByteArray(DataInputStream dis) throws IOException {
        byte[] buf = new byte[1024];
        dis.readFully(buf);
        return buf;
    }

    public static void writeByteArray(DataOutputStream dos, byte[] data) throws IOException {
        if (data.length < 1024) {
            byte[] full = new byte[1024];
            int i2 = 0;
            while (i2 < data.length) {
                full[i2] = data[i2];
                ++i2;
            }
            data = full;
        }
        dos.write(data);
    }

    public static byte[] readLargeArray(DataInputStream dis) throws IOException {
        byte[] buf = new byte[4096];
        dis.readFully(buf);
        return buf;
    }

    public static void writeLargeArray(DataOutputStream dos, byte[] data) throws IOException {
        if (data.length < 4096) {
            data = Arrays.copyOf(data, 4096);
        }
        dos.write(data);
    }

    private static void register(int id, Class<? extends Packet> p_class) {
        if (idClassMap.containsKey(id)) {
            throw new Error("Duplicate packet id:" + id);
        }
        if (classIdMap.containsKey(p_class)) {
            throw new Error("Duplicate packet class:" + p_class);
        }
        idClassMap.put(id, p_class);
        classIdMap.put(p_class, id);
    }
}

