/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import com.mojang.nbt.NBTTagByte;
import com.mojang.nbt.NBTTagByteArray;
import com.mojang.nbt.NBTTagFloat;
import com.mojang.nbt.NBTTagInt;
import com.mojang.nbt.NBTTagList;
import com.mojang.nbt.NBTTagLong;
import com.mojang.nbt.NBTTagShort;
import com.mojang.nbt.NBTTagString;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class NBTTagCompound
extends NBTBase {
    private Map<String, NBTBase> tagMap = new HashMap<String, NBTBase>();

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
        Iterator<NBTBase> iterator = this.tagMap.values().iterator();
        while (iterator.hasNext()) {
            NBTBase.writeTag(iterator.next(), dataOutput);
        }
        dataOutput.writeByte(0);
    }

    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
        NBTBase nBTBase;
        this.tagMap.clear();
        while ((nBTBase = NBTBase.readTag(dataInput)).getType() != 0) {
            this.tagMap.put(nBTBase.getKey(), nBTBase);
        }
    }

    @Override
    public final byte getType() {
        return 10;
    }

    public final void setTag(String string, NBTBase nBTBase) {
        this.tagMap.put(string, nBTBase.setKey(string));
    }

    public final void setByte(String string, byte by) {
        this.tagMap.put(string, new NBTTagByte(by).setKey(string));
    }

    public final void setShort(String string, short s2) {
        this.tagMap.put(string, new NBTTagShort(s2).setKey(string));
    }

    public final void setInteger(String string, int n2) {
        this.tagMap.put(string, new NBTTagInt(n2).setKey(string));
    }

    public final void setLong(String string, long l2) {
        this.tagMap.put(string, new NBTTagLong(l2).setKey(string));
    }

    public final void setFloat(String string, float f2) {
        this.tagMap.put(string, new NBTTagFloat(f2).setKey(string));
    }

    public final void setString(String string, String string2) {
        this.tagMap.put(string, new NBTTagString(string2).setKey(string));
    }

    public final void setByteArray(String string, byte[] byArray) {
        this.tagMap.put(string, new NBTTagByteArray(byArray).setKey(string));
    }

    public final void setCompoundTag(String string, NBTTagCompound nBTTagCompound) {
        this.tagMap.put(string, nBTTagCompound.setKey(string));
    }

    public final void setBoolean(String string, boolean bl) {
        this.setByte(string, (byte)(bl ? 1 : 0));
    }

    public final boolean hasKey(String string) {
        return this.tagMap.containsKey(string);
    }

    public final byte getByte(String string) {
        return !this.tagMap.containsKey(string) ? (byte)0 : ((NBTTagByte)this.tagMap.get((Object)string)).byteValue;
    }

    public final short getShort(String string) {
        return !this.tagMap.containsKey(string) ? (short)0 : ((NBTTagShort)this.tagMap.get((Object)string)).shortValue;
    }

    public final int getInteger(String string) {
        return !this.tagMap.containsKey(string) ? 0 : ((NBTTagInt)this.tagMap.get((Object)string)).intValue;
    }

    public final long getLong(String string) {
        return !this.tagMap.containsKey(string) ? 0L : ((NBTTagLong)this.tagMap.get((Object)string)).longValue;
    }

    public final float getFloat(String string) {
        return !this.tagMap.containsKey(string) ? 0.0f : ((NBTTagFloat)this.tagMap.get((Object)string)).floatValue;
    }

    public final String getString(String string) {
        return !this.tagMap.containsKey(string) ? "" : ((NBTTagString)this.tagMap.get((Object)string)).stringValue;
    }

    public final byte[] getByteArray(String string) {
        return !this.tagMap.containsKey(string) ? new byte[]{} : ((NBTTagByteArray)this.tagMap.get((Object)string)).byteArray;
    }

    public final NBTTagCompound getCompoundTag(String string) {
        return !this.tagMap.containsKey(string) ? new NBTTagCompound() : (NBTTagCompound)this.tagMap.get(string);
    }

    public final NBTTagList getTagList(String string) {
        return !this.tagMap.containsKey(string) ? new NBTTagList() : (NBTTagList)this.tagMap.get(string);
    }

    public final boolean getBoolean(String string) {
        return this.getByte(string) != 0;
    }

    public final String toString() {
        return "" + this.tagMap.size() + " entries";
    }

    public final boolean emptyNBTMap() {
        return this.tagMap.isEmpty();
    }
}

