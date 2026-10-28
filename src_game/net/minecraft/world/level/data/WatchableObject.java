/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.data;

public class WatchableObject {
    private final int objectType;
    private final int dataValueId;
    private Object watchedObject;
    private boolean isWatching;

    public WatchableObject(int n2, int n3, Object object) {
        this.dataValueId = n3;
        this.watchedObject = object;
        this.objectType = n2;
        this.isWatching = true;
    }

    public int getDataValueId() {
        return this.dataValueId;
    }

    public void setObject(Object object) {
        this.watchedObject = object;
    }

    public Object getObject() {
        return this.watchedObject;
    }

    public int getObjectType() {
        return this.objectType;
    }

    public boolean getWatching() {
        return this.isWatching;
    }

    public void setWatching(boolean bl) {
        this.isWatching = bl;
    }
}

