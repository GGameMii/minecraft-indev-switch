/*
 * Decompiled with CFR 0.152.
 */
package util;

public class IntHashMap {
    private transient Entry[] table = new Entry[16];
    private transient int size;
    private int threshold = 12;
    private final float loadFactor = 0.75f;
    private volatile transient int modCount;

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

    public Object get(int i1) {
        int i2 = IntHashMap.hash(i1);
        Entry intHashMap$Entry3 = this.table[IntHashMap.indexFor(i2, this.table.length)];
        while (intHashMap$Entry3 != null) {
            if (intHashMap$Entry3.key == i1) {
                return intHashMap$Entry3.value;
            }
            intHashMap$Entry3 = intHashMap$Entry3.next;
        }
        return null;
    }

    public boolean containsKey(int i1) {
        return this.getEntry(i1) != null;
    }

    final Entry getEntry(int i1) {
        int i2 = IntHashMap.hash(i1);
        Entry intHashMap$Entry3 = this.table[IntHashMap.indexFor(i2, this.table.length)];
        while (intHashMap$Entry3 != null) {
            if (intHashMap$Entry3.key == i1) {
                return intHashMap$Entry3;
            }
            intHashMap$Entry3 = intHashMap$Entry3.next;
        }
        return null;
    }

    public void put(int i1, Object object2) {
        int i3 = IntHashMap.hash(i1);
        int i4 = IntHashMap.indexFor(i3, this.table.length);
        Entry intHashMap$Entry5 = this.table[i4];
        while (intHashMap$Entry5 != null) {
            if (intHashMap$Entry5.key == i1) {
                intHashMap$Entry5.value = object2;
            }
            intHashMap$Entry5 = intHashMap$Entry5.next;
        }
        ++this.modCount;
        this.addEntry(i3, i1, object2, i4);
    }

    private void resize(int i1) {
        Entry[] intHashMap$Entry2 = this.table;
        int i3 = intHashMap$Entry2.length;
        if (i3 == 0x40000000) {
            this.threshold = Integer.MAX_VALUE;
        } else {
            Entry[] intHashMap$Entry4 = new Entry[i1];
            this.transfer(intHashMap$Entry4);
            this.table = intHashMap$Entry4;
            this.threshold = (int)((float)i1 * 0.75f);
        }
    }

    private void transfer(Entry[] intHashMap$Entry1) {
        Entry[] intHashMap$Entry2 = this.table;
        int i3 = intHashMap$Entry1.length;
        int i4 = 0;
        while (i4 < intHashMap$Entry2.length) {
            Entry intHashMap$Entry5 = intHashMap$Entry2[i4];
            if (intHashMap$Entry5 != null) {
                Entry intHashMap$Entry6;
                intHashMap$Entry2[i4] = null;
                do {
                    intHashMap$Entry6 = intHashMap$Entry5.next;
                    int i7 = IntHashMap.indexFor(intHashMap$Entry5.hash, i3);
                    intHashMap$Entry5.next = intHashMap$Entry1[i7];
                    intHashMap$Entry1[i7] = intHashMap$Entry5;
                    intHashMap$Entry5 = intHashMap$Entry6;
                } while (intHashMap$Entry6 != null);
            }
            ++i4;
        }
    }

    public Object remove(int i1) {
        Entry intHashMap$Entry2 = this.removeEntryForKey(i1);
        return intHashMap$Entry2 == null ? null : intHashMap$Entry2.value;
    }

    final Entry removeEntryForKey(int i1) {
        Entry intHashMap$Entry4;
        int i2 = IntHashMap.hash(i1);
        int i3 = IntHashMap.indexFor(i2, this.table.length);
        Entry intHashMap$Entry5 = intHashMap$Entry4 = this.table[i3];
        while (intHashMap$Entry5 != null) {
            Entry intHashMap$Entry6 = intHashMap$Entry5.next;
            if (intHashMap$Entry5.key == i1) {
                ++this.modCount;
                --this.size;
                if (intHashMap$Entry4 == intHashMap$Entry5) {
                    this.table[i3] = intHashMap$Entry6;
                } else {
                    intHashMap$Entry4.next = intHashMap$Entry6;
                }
                return intHashMap$Entry5;
            }
            intHashMap$Entry4 = intHashMap$Entry5;
            intHashMap$Entry5 = intHashMap$Entry6;
        }
        return intHashMap$Entry5;
    }

    public void clear() {
        ++this.modCount;
        Entry[] intHashMap$Entry1 = this.table;
        int i2 = 0;
        while (i2 < intHashMap$Entry1.length) {
            intHashMap$Entry1[i2] = null;
            ++i2;
        }
        this.size = 0;
    }

    public boolean containsValue(Object object1) {
        if (object1 == null) {
            return this.containsNullValue();
        }
        Entry[] intHashMap$Entry2 = this.table;
        int i3 = 0;
        while (i3 < intHashMap$Entry2.length) {
            Entry intHashMap$Entry4 = intHashMap$Entry2[i3];
            while (intHashMap$Entry4 != null) {
                if (object1.equals(intHashMap$Entry4.value)) {
                    return true;
                }
                intHashMap$Entry4 = intHashMap$Entry4.next;
            }
            ++i3;
        }
        return false;
    }

    private boolean containsNullValue() {
        Entry[] intHashMap$Entry1 = this.table;
        int i2 = 0;
        while (i2 < intHashMap$Entry1.length) {
            Entry intHashMap$Entry3 = intHashMap$Entry1[i2];
            while (intHashMap$Entry3 != null) {
                if (intHashMap$Entry3.value == null) {
                    return true;
                }
                intHashMap$Entry3 = intHashMap$Entry3.next;
            }
            ++i2;
        }
        return false;
    }

    private void addEntry(int i1, int i2, Object object3, int i4) {
        Entry intHashMap$Entry5 = this.table[i4];
        this.table[i4] = new Entry(i1, i2, object3, intHashMap$Entry5);
        if (this.size++ >= this.threshold) {
            this.resize(2 * this.table.length);
        }
    }

    private static class Entry {
        final int key;
        Object value;
        Entry next;
        final int hash;

        Entry(int i1, int i2, Object object3, Entry intHashMap$Entry4) {
            this.value = object3;
            this.next = intHashMap$Entry4;
            this.key = i2;
            this.hash = i1;
        }

        public final int getKey() {
            return this.key;
        }

        public final Object getValue() {
            return this.value;
        }

        public final boolean equals(Object object1) {
            Object object6;
            Object object5;
            Integer integer4;
            if (!(object1 instanceof Entry)) {
                return false;
            }
            Entry intHashMap$Entry2 = (Entry)object1;
            Integer integer3 = this.getKey();
            return (integer3 == (integer4 = Integer.valueOf(intHashMap$Entry2.getKey())) || integer3 != null && integer3.equals(integer4)) && ((object5 = this.getValue()) == (object6 = intHashMap$Entry2.getValue()) || object5 != null && object5.equals(object6));
        }

        public final int hashCode() {
            return IntHashMap.hash(this.key);
        }

        public final String toString() {
            return String.valueOf(this.getKey()) + "=" + this.getValue();
        }
    }
}

