/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class NBTTagList
extends NBTBase {
    private List<NBTBase> tagList = new ArrayList<NBTBase>();
    private byte tagType;

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
        this.tagType = this.tagList.size() > 0 ? this.tagList.get(0).getType() : (byte)1;
        dataOutput.writeByte(this.tagType);
        dataOutput.writeInt(this.tagList.size());
        for (int i2 = 0; i2 < this.tagList.size(); ++i2) {
            this.tagList.get(i2).writeTagContents(dataOutput);
        }
    }

    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
        this.tagType = dataInput.readByte();
        int n2 = dataInput.readInt();
        this.tagList = new ArrayList<NBTBase>();
        for (int i2 = 0; i2 < n2; ++i2) {
            NBTBase nBTBase = NBTBase.createTagOfType(this.tagType);
            nBTBase.readTagContents(dataInput);
            this.tagList.add(nBTBase);
        }
    }

    @Override
    public final byte getType() {
        return 9;
    }

    public final String toString() {
        String string;
        StringBuilder stringBuilder = new StringBuilder().append("").append(this.tagList.size()).append(" entries of type ");
        switch (this.tagType) {
            case 0: {
                string = "TAG_End";
                break;
            }
            case 1: {
                string = "TAG_Byte";
                break;
            }
            case 2: {
                string = "TAG_Short";
                break;
            }
            case 3: {
                string = "TAG_Int";
                break;
            }
            case 4: {
                string = "TAG_Long";
                break;
            }
            case 5: {
                string = "TAG_Float";
                break;
            }
            case 6: {
                string = "TAG_Double";
                break;
            }
            case 7: {
                string = "TAG_Byte_Array";
                break;
            }
            case 8: {
                string = "TAG_String";
                break;
            }
            case 9: {
                string = "TAG_List";
                break;
            }
            case 10: {
                string = "TAG_Compound";
                break;
            }
            default: {
                string = "UNKNOWN";
            }
        }
        return stringBuilder.append(string).toString();
    }

    public final void setTag(NBTBase nBTBase) {
        this.tagType = nBTBase.getType();
        this.tagList.add(nBTBase);
    }

    public final NBTBase tagAt(int n2) {
        return this.tagList.get(n2);
    }

    public final int tagCount() {
        return this.tagList.size();
    }
}

