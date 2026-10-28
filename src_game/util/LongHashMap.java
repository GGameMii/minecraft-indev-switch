/*
 * Decompiled with CFR 0.152.
 */
package util;

public class LongHashMap {
    private transient Entry[] table = new Entry[16];
    private transient int size;
    private int threshold = 12;
    private final float loadFactor = 0.75f;
    private volatile transient int modCount;

    private static int hash(long j0) {
        return LongHashMap.hash((int)(j0 ^ j0 >>> 32));
    }

    private static int hash(int i0) {
        i0 ^= i0 >>> 20 ^ i0 >>> 12;
        return i0 ^ i0 >>> 7 ^ i0 >>> 4;
    }

    private static int indexFor(int i0, int i1) {
        return i0 & i1 - 1;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Object get(long j1) {
        int i3 = LongHashMap.hash(j1);
        Entry longHashMap$Entry4 = this.table[LongHashMap.indexFor(i3, this.table.length)];
        while (longHashMap$Entry4 != null) {
            if (longHashMap$Entry4.key == j1) {
                return longHashMap$Entry4.value;
            }
            longHashMap$Entry4 = longHashMap$Entry4.next;
        }
        return null;
    }

    public boolean containsKey(long j1) {
        return this.getEntry(j1) != null;
    }

    final Entry getEntry(long j1) {
        int i3 = LongHashMap.hash(j1);
        Entry longHashMap$Entry4 = this.table[LongHashMap.indexFor(i3, this.table.length)];
        while (longHashMap$Entry4 != null) {
            if (longHashMap$Entry4.key == j1) {
                return longHashMap$Entry4;
            }
            longHashMap$Entry4 = longHashMap$Entry4.next;
        }
        return null;
    }

    public void put(long j1, Object object3) {
        int i4 = LongHashMap.hash(j1);
        int i5 = LongHashMap.indexFor(i4, this.table.length);
        Entry longHashMap$Entry6 = this.table[i5];
        while (longHashMap$Entry6 != null) {
            if (longHashMap$Entry6.key == j1) {
                longHashMap$Entry6.value = object3;
            }
            longHashMap$Entry6 = longHashMap$Entry6.next;
        }
        ++this.modCount;
        this.addEntry(i4, j1, object3, i5);
    }

    private void resize(int i1) {
        Entry[] longHashMap$Entry2 = this.table;
        int i3 = longHashMap$Entry2.length;
        if (i3 == 0x40000000) {
            this.threshold = Integer.MAX_VALUE;
        } else {
            Entry[] longHashMap$Entry4 = new Entry[i1];
            this.transfer(longHashMap$Entry4);
            this.table = longHashMap$Entry4;
            this.threshold = (int)((float)i1 * 0.75f);
        }
    }

    private void transfer(Entry[] longHashMap$Entry1) {
        Entry[] longHashMap$Entry2 = this.table;
        int i3 = longHashMap$Entry1.length;
        int i4 = 0;
        while (i4 < longHashMap$Entry2.length) {
            Entry longHashMap$Entry5 = longHashMap$Entry2[i4];
            if (longHashMap$Entry5 != null) {
                Entry longHashMap$Entry6;
                longHashMap$Entry2[i4] = null;
                do {
                    longHashMap$Entry6 = longHashMap$Entry5.next;
                    int i7 = LongHashMap.indexFor(longHashMap$Entry5.hash, i3);
                    longHashMap$Entry5.next = longHashMap$Entry1[i7];
                    longHashMap$Entry1[i7] = longHashMap$Entry5;
                    longHashMap$Entry5 = longHashMap$Entry6;
                } while (longHashMap$Entry6 != null);
            }
            ++i4;
        }
    }

    public Object remove(long j1) {
        Entry longHashMap$Entry3 = this.removeEntryForKey(j1);
        return longHashMap$Entry3 == null ? null : longHashMap$Entry3.value;
    }

    final Entry removeEntryForKey(long j1) {
        Entry longHashMap$Entry5;
        int i3 = LongHashMap.hash(j1);
        int i4 = LongHashMap.indexFor(i3, this.table.length);
        Entry longHashMap$Entry6 = longHashMap$Entry5 = this.table[i4];
        while (longHashMap$Entry6 != null) {
            Entry longHashMap$Entry7 = longHashMap$Entry6.next;
            if (longHashMap$Entry6.key == j1) {
                ++this.modCount;
                --this.size;
                if (longHashMap$Entry5 == longHashMap$Entry6) {
                    this.table[i4] = longHashMap$Entry7;
                } else {
                    longHashMap$Entry5.next = longHashMap$Entry7;
                }
                return longHashMap$Entry6;
            }
            longHashMap$Entry5 = longHashMap$Entry6;
            longHashMap$Entry6 = longHashMap$Entry7;
        }
        return longHashMap$Entry6;
    }

    public void clear() {
        ++this.modCount;
        Entry[] longHashMap$Entry1 = this.table;
        int i2 = 0;
        while (i2 < longHashMap$Entry1.length) {
            longHashMap$Entry1[i2] = null;
            ++i2;
        }
        this.size = 0;
    }

    public boolean containsValue(Object object1) {
        if (object1 == null) {
            return this.containsNullValue();
        }
        Entry[] longHashMap$Entry2 = this.table;
        int i3 = 0;
        while (i3 < longHashMap$Entry2.length) {
            Entry longHashMap$Entry4 = longHashMap$Entry2[i3];
            while (longHashMap$Entry4 != null) {
                if (object1.equals(longHashMap$Entry4.value)) {
                    return true;
                }
                longHashMap$Entry4 = longHashMap$Entry4.next;
            }
            ++i3;
        }
        return false;
    }

    private boolean containsNullValue() {
        Entry[] longHashMap$Entry1 = this.table;
        int i2 = 0;
        while (i2 < longHashMap$Entry1.length) {
            Entry longHashMap$Entry3 = longHashMap$Entry1[i2];
            while (longHashMap$Entry3 != null) {
                if (longHashMap$Entry3.value == null) {
                    return true;
                }
                longHashMap$Entry3 = longHashMap$Entry3.next;
            }
            ++i2;
        }
        return false;
    }

    private void addEntry(int i1, long j2, Object object4, int i5) {
        Entry longHashMap$Entry6 = this.table[i5];
        this.table[i5] = new Entry(i1, j2, object4, longHashMap$Entry6);
        if (this.size++ >= this.threshold) {
            this.resize(2 * this.table.length);
        }
    }

    private static class Entry {
        final long key;
        Object value;
        Entry next;
        final int hash;

        Entry(int i1, long j2, Object object4, Entry longHashMap$Entry5) {
            this.value = object4;
            this.next = longHashMap$Entry5;
            this.key = j2;
            this.hash = i1;
        }

        public final long getKey() {
            return this.key;
        }

        public final Object getValue() {
            return this.value;
        }

        public final boolean equals(Object object1) {
            Object object6;
            Object object5;
            Long long4;
            if (!(object1 instanceof Entry)) {
                return false;
            }
            Entry longHashMap$Entry2 = (Entry)object1;
            Long long3 = this.getKey();
            return (long3 == (long4 = Long.valueOf(longHashMap$Entry2.getKey())) || long3 != null && long3.equals(long4)) && ((object5 = this.getValue()) == (object6 = longHashMap$Entry2.getValue()) || object5 != null && object5.equals(object6));
        }

        public final int hashCode() {
            return LongHashMap.hash(this.key);
        }

        public final String toString() {
            return String.valueOf(this.getKey()) + "=" + this.getValue();
        }
    }
}

