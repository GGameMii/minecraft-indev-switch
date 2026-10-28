/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.data.WatchableObject;

public class DataWatcher {
    private static final HashMap<Class<?>, Integer> dataTypes = new HashMap();
    private final Map<Integer, WatchableObject> watchedObjects = new HashMap<Integer, WatchableObject>();
    private boolean objectChanged;

    public void addObject(int n2, Object object) {
        Integer n3 = dataTypes.get(object.getClass());
        if (n3 == null) {
            throw new IllegalArgumentException("Unknown data type: " + object.getClass());
        }
        if (n2 > 31) {
            throw new IllegalArgumentException("Data value id is too big with " + n2 + "! (Max is " + 31 + ")");
        }
        if (this.watchedObjects.containsKey(n2)) {
            throw new IllegalArgumentException("Duplicate id value for " + n2 + "!");
        }
        WatchableObject watchableObject = new WatchableObject(n3, n2, object);
        this.watchedObjects.put(n2, watchableObject);
    }

    public byte getWatchableObjectByte(int n2) {
        return (Byte)this.watchedObjects.get(n2).getObject();
    }

    public int getWatchableObjectInt(int n2) {
        return (Integer)this.watchedObjects.get(n2).getObject();
    }

    public String getWatchableObjectString(int n2) {
        return (String)this.watchedObjects.get(n2).getObject();
    }

    public void updateObject(int n2, Object object) {
        WatchableObject watchableObject = this.watchedObjects.get(n2);
        if (!object.equals(watchableObject.getObject())) {
            watchableObject.setObject(object);
            watchableObject.setWatching(true);
            this.objectChanged = true;
        }
    }

    public static void writeObjectsInListToStream(List<WatchableObject> list, DataOutputStream dataOutputStream) throws IOException {
        if (list != null) {
            for (WatchableObject watchableObject : list) {
                DataWatcher.writeWatchableObject(dataOutputStream, watchableObject);
            }
        }
        dataOutputStream.writeByte(127);
    }

    public void writeWatchableObjects(DataOutputStream dataOutputStream) throws IOException {
        for (WatchableObject watchableObject : this.watchedObjects.values()) {
            DataWatcher.writeWatchableObject(dataOutputStream, watchableObject);
        }
        dataOutputStream.writeByte(127);
    }

    private static void writeWatchableObject(DataOutputStream dataOutputStream, WatchableObject watchableObject) throws IOException {
        int n2 = (watchableObject.getObjectType() << 5 | watchableObject.getDataValueId() & 0x1F) & 0xFF;
        dataOutputStream.writeByte(n2);
        switch (watchableObject.getObjectType()) {
            case 0: {
                dataOutputStream.writeByte(((Byte)watchableObject.getObject()).byteValue());
                break;
            }
            case 1: {
                dataOutputStream.writeShort(((Short)watchableObject.getObject()).shortValue());
                break;
            }
            case 2: {
                dataOutputStream.writeInt((Integer)watchableObject.getObject());
                break;
            }
            case 3: {
                dataOutputStream.writeFloat(((Float)watchableObject.getObject()).floatValue());
                break;
            }
            case 4: {
                try {
                    throw new Exception("Can't use Packet DataWatcher type!");
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    break;
                }
            }
            case 5: {
                ItemStack itemStack = (ItemStack)watchableObject.getObject();
                dataOutputStream.writeShort(itemStack.getItem().shiftedIndex);
                dataOutputStream.writeByte(itemStack.stackSize);
                dataOutputStream.writeShort(itemStack.itemDamage);
                break;
            }
            case 6: {
                try {
                    throw new Exception("Can't use ChunkCoordinates DataWatcher type!");
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public static List<WatchableObject> readWatchableObjects(DataInputStream dataInputStream) throws IOException {
        ArrayList<WatchableObject> arrayList = null;
        byte by = dataInputStream.readByte();
        while (by != 127) {
            if (arrayList == null) {
                arrayList = new ArrayList<WatchableObject>();
            }
            int n2 = (by & 0xE0) >> 5;
            int n3 = by & 0x1F;
            WatchableObject watchableObject = null;
            switch (n2) {
                case 0: {
                    watchableObject = new WatchableObject(n2, n3, dataInputStream.readByte());
                    break;
                }
                case 1: {
                    watchableObject = new WatchableObject(n2, n3, dataInputStream.readShort());
                    break;
                }
                case 2: {
                    watchableObject = new WatchableObject(n2, n3, dataInputStream.readInt());
                    break;
                }
                case 3: {
                    watchableObject = new WatchableObject(n2, n3, Float.valueOf(dataInputStream.readFloat()));
                    break;
                }
                case 4: {
                    try {
                        throw new Exception("Can't use Packet DataWatcher type!");
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                        break;
                    }
                }
                case 5: {
                    short s2 = dataInputStream.readShort();
                    byte by2 = dataInputStream.readByte();
                    short s3 = dataInputStream.readShort();
                    watchableObject = new WatchableObject(n2, n3, new ItemStack(s2, (int)by2, (int)s3));
                    break;
                }
                case 6: {
                    try {
                        throw new Exception("Can't use ChunkCoordinates DataWatcher type!");
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            arrayList.add(watchableObject);
            by = dataInputStream.readByte();
        }
        return arrayList;
    }

    public void updateWatchedObjectsFromList(List<WatchableObject> list) {
        for (WatchableObject watchableObject : list) {
            WatchableObject watchableObject2 = this.watchedObjects.get(watchableObject.getDataValueId());
            if (watchableObject2 == null) continue;
            watchableObject2.setObject(watchableObject.getObject());
        }
    }

    public ArrayList<WatchableObject> getChangedObjects() {
        ArrayList<WatchableObject> arrayList = null;
        if (this.objectChanged) {
            for (WatchableObject watchableObject : this.watchedObjects.values()) {
                WatchableObject watchableObject2 = watchableObject;
                if (!watchableObject2.getWatching()) continue;
                watchableObject2.setWatching(false);
                if (arrayList == null) {
                    arrayList = new ArrayList<WatchableObject>();
                }
                arrayList.add(watchableObject2);
            }
        }
        this.objectChanged = false;
        return arrayList;
    }

    public boolean hasObjectChanged() {
        return this.objectChanged;
    }

    static {
        dataTypes.put(Byte.class, 0);
        dataTypes.put(Short.class, 1);
        dataTypes.put(Integer.class, 2);
        dataTypes.put(Float.class, 3);
        dataTypes.put(String.class, 4);
        dataTypes.put(ItemStack.class, 5);
    }
}

