/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTTagByte;
import com.mojang.nbt.NBTTagByteArray;
import com.mojang.nbt.NBTTagCompound;
import com.mojang.nbt.NBTTagDouble;
import com.mojang.nbt.NBTTagEnd;
import com.mojang.nbt.NBTTagFloat;
import com.mojang.nbt.NBTTagInt;
import com.mojang.nbt.NBTTagList;
import com.mojang.nbt.NBTTagLong;
import com.mojang.nbt.NBTTagShort;
import com.mojang.nbt.NBTTagString;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NBTBase {
    private String key = null;

    abstract void writeTagContents(DataOutput var1) throws IOException;

    abstract void readTagContents(DataInput var1) throws IOException;

    public abstract byte getType();

    public final String getKey() {
        return this.key == null ? "" : this.key;
    }

    public final NBTBase setKey(String string) {
        this.key = string;
        return this;
    }

    public static NBTBase readTag(DataInput dataInput) throws IOException {
        byte by = dataInput.readByte();
        if (by == 0) {
            return new NBTTagEnd();
        }
        NBTBase nBTBase = NBTBase.createTagOfType(by);
        byte[] byArray = new byte[dataInput.readShort()];
        dataInput.readFully(byArray);
        nBTBase.key = new String(byArray, "UTF-8");
        nBTBase.readTagContents(dataInput);
        return nBTBase;
    }

    public static void writeTag(NBTBase nBTBase, DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(nBTBase.getType());
        if (nBTBase.getType() != 0) {
            byte[] byArray = nBTBase.getKey().getBytes("UTF-8");
            dataOutput.writeShort(byArray.length);
            dataOutput.write(byArray);
            nBTBase.writeTagContents(dataOutput);
        }
    }

    public static NBTBase createTagOfType(byte by) {
        switch (by) {
            case 0: {
                return new NBTTagEnd();
            }
            case 1: {
                return new NBTTagByte();
            }
            case 2: {
                return new NBTTagShort();
            }
            case 3: {
                return new NBTTagInt();
            }
            case 4: {
                return new NBTTagLong();
            }
            case 5: {
                return new NBTTagFloat();
            }
            case 6: {
                return new NBTTagDouble();
            }
            case 7: {
                return new NBTTagByteArray();
            }
            case 8: {
                return new NBTTagString();
            }
            case 9: {
                return new NBTTagList();
            }
            case 10: {
                return new NBTTagCompound();
            }
        }
        return null;
    }
}

