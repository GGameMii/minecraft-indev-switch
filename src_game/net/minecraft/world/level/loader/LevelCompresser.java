/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.loader;

import com.mojang.nbt.NBTBase;
import com.mojang.nbt.NBTTagCompound;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class LevelCompresser {
    public static byte[] compress(NBTTagCompound par0NBTTagCompound) throws IOException {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        try (DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(bytearrayoutputstream));){
            LevelCompresser.writeLevelCompounds(par0NBTTagCompound, dataoutputstream);
        }
        return bytearrayoutputstream.toByteArray();
    }

    public static void writeLevelCompounds(NBTTagCompound nbttagcompound, OutputStream outputstream) throws IOException {
        try (DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(outputstream));){
            LevelCompresser.writeNBTTag(nbttagcompound, dataoutputstream);
        }
    }

    public static void writeNBTTag(NBTTagCompound nbttagcompound, DataOutput dataoutput) throws IOException {
        NBTBase.writeTag(nbttagcompound, dataoutput);
    }

    public static NBTTagCompound readData(InputStream inputstream) throws IOException {
        try (DataInputStream datainputstream = new DataInputStream(new GZIPInputStream(inputstream));){
            NBTTagCompound nbttagcompound;
            NBTTagCompound nBTTagCompound = nbttagcompound = LevelCompresser.readInputStream(datainputstream);
            return nBTTagCompound;
        }
    }

    public static NBTTagCompound readInputStream(DataInput datainput) throws IOException {
        NBTBase nbtbase = NBTBase.readTag(datainput);
        if (nbtbase instanceof NBTTagCompound) {
            return (NBTTagCompound)nbtbase;
        }
        throw new IOException("Root tag must be a named compound tag");
    }

    public static NBTTagCompound decompress(byte[] par0ArrayOfByte) throws IOException {
        try (DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(par0ArrayOfByte))));){
            NBTTagCompound nbttagcompound;
            NBTTagCompound nBTTagCompound = nbttagcompound = LevelCompresser.readData(datainputstream);
            return nBTTagCompound;
        }
    }
}

