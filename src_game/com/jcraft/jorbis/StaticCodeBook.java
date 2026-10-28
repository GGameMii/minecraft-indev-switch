/*
 * Decompiled with CFR 0.152.
 */
package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;
import com.jcraft.jorbis.Util;
import util.Mth;

class StaticCodeBook {
    int dim;
    int entries;
    int[] lengthlist;
    int maptype;
    int q_min;
    int q_delta;
    int q_quant;
    int q_sequencep;
    int[] quantlist;
    static final int VQ_FEXP = 10;
    static final int VQ_FMAN = 21;
    static final int VQ_FEXP_BIAS = 768;

    StaticCodeBook() {
    }

    int pack(Buffer opb) {
        int quantvals;
        boolean ordered = false;
        opb.write(5653314, 24);
        opb.write(this.dim, 16);
        opb.write(this.entries, 24);
        int i2 = 1;
        while (i2 < this.entries && this.lengthlist[i2] >= this.lengthlist[i2 - 1]) {
            ++i2;
        }
        if (i2 == this.entries) {
            ordered = true;
        }
        if (ordered) {
            quantvals = 0;
            opb.write(1, 1);
            opb.write(this.lengthlist[0] - 1, 5);
            i2 = 1;
            while (i2 < this.entries) {
                int _this = this.lengthlist[i2];
                int _last = this.lengthlist[i2 - 1];
                if (_this > _last) {
                    int j2 = _last;
                    while (j2 < _this) {
                        opb.write(i2 - quantvals, Util.ilog(this.entries - quantvals));
                        quantvals = i2;
                        ++j2;
                    }
                }
                ++i2;
            }
            opb.write(i2 - quantvals, Util.ilog(this.entries - quantvals));
        } else {
            opb.write(0, 1);
            i2 = 0;
            while (i2 < this.entries && this.lengthlist[i2] != 0) {
                ++i2;
            }
            if (i2 == this.entries) {
                opb.write(0, 1);
                i2 = 0;
                while (i2 < this.entries) {
                    opb.write(this.lengthlist[i2] - 1, 5);
                    ++i2;
                }
            } else {
                opb.write(1, 1);
                i2 = 0;
                while (i2 < this.entries) {
                    if (this.lengthlist[i2] == 0) {
                        opb.write(0, 1);
                    } else {
                        opb.write(1, 1);
                        opb.write(this.lengthlist[i2] - 1, 5);
                    }
                    ++i2;
                }
            }
        }
        opb.write(this.maptype, 4);
        switch (this.maptype) {
            case 1: 
            case 2: {
                if (this.quantlist == null) {
                    return -1;
                }
                opb.write(this.q_min, 32);
                opb.write(this.q_delta, 32);
                opb.write(this.q_quant - 1, 4);
                opb.write(this.q_sequencep, 1);
                quantvals = 0;
                switch (this.maptype) {
                    case 1: {
                        quantvals = this.maptype1_quantvals();
                        break;
                    }
                    case 2: {
                        quantvals = this.entries * this.dim;
                    }
                }
                i2 = 0;
                while (i2 < quantvals) {
                    opb.write(Math.abs(this.quantlist[i2]), this.q_quant);
                    ++i2;
                }
            }
            case 0: {
                return 0;
            }
        }
        return -1;
    }

    int unpack(Buffer opb) {
        int quantvals;
        int i2;
        if (opb.read(24) != 5653314) {
            this.clear();
            return -1;
        }
        this.dim = opb.read(16);
        this.entries = opb.read(24);
        if (this.entries == -1) {
            this.clear();
            return -1;
        }
        switch (opb.read(1)) {
            case 0: {
                this.lengthlist = new int[this.entries];
                if (opb.read(1) != 0) {
                    for (i2 = 0; i2 < this.entries; ++i2) {
                        if (opb.read(1) != 0) {
                            quantvals = opb.read(5);
                            if (quantvals == -1) {
                                this.clear();
                                return -1;
                            }
                            this.lengthlist[i2] = quantvals + 1;
                            continue;
                        }
                        this.lengthlist[i2] = 0;
                    }
                } else {
                    for (i2 = 0; i2 < this.entries; ++i2) {
                        quantvals = opb.read(5);
                        if (quantvals == -1) {
                            this.clear();
                            return -1;
                        }
                        this.lengthlist[i2] = quantvals + 1;
                    }
                }
                break;
            }
            case 1: {
                quantvals = opb.read(5) + 1;
                this.lengthlist = new int[this.entries];
                i2 = 0;
                while (i2 < this.entries) {
                    int num = opb.read(Util.ilog(this.entries - i2));
                    if (num == -1) {
                        this.clear();
                        return -1;
                    }
                    int j2 = 0;
                    while (j2 < num) {
                        this.lengthlist[i2] = quantvals;
                        ++j2;
                        ++i2;
                    }
                    ++quantvals;
                }
                break;
            }
            default: {
                return -1;
            }
        }
        this.maptype = opb.read(4);
        switch (this.maptype) {
            case 1: 
            case 2: {
                this.q_min = opb.read(32);
                this.q_delta = opb.read(32);
                this.q_quant = opb.read(4) + 1;
                this.q_sequencep = opb.read(1);
                quantvals = 0;
                switch (this.maptype) {
                    case 1: {
                        quantvals = this.maptype1_quantvals();
                        break;
                    }
                    case 2: {
                        quantvals = this.entries * this.dim;
                    }
                }
                this.quantlist = new int[quantvals];
                i2 = 0;
                while (i2 < quantvals) {
                    this.quantlist[i2] = opb.read(this.q_quant);
                    ++i2;
                }
                if (this.quantlist[quantvals - 1] == -1) {
                    this.clear();
                    return -1;
                }
            }
            case 0: {
                return 0;
            }
        }
        this.clear();
        return -1;
    }

    private int maptype1_quantvals() {
        int vals = Mth.floor(Math.pow(this.entries, 1.0 / (double)this.dim));
        while (true) {
            int acc = 1;
            int acc1 = 1;
            int i2 = 0;
            while (i2 < this.dim) {
                acc *= vals;
                acc1 *= vals + 1;
                ++i2;
            }
            if (acc <= this.entries && acc1 > this.entries) {
                return vals;
            }
            if (acc > this.entries) {
                --vals;
                continue;
            }
            ++vals;
        }
    }

    void clear() {
    }

    float[] unquantize() {
        if (this.maptype != 1 && this.maptype != 2) {
            return null;
        }
        float mindel = StaticCodeBook.float32_unpack(this.q_min);
        float delta = StaticCodeBook.float32_unpack(this.q_delta);
        float[] r2 = new float[this.entries * this.dim];
        switch (this.maptype) {
            case 1: {
                int quantvals = this.maptype1_quantvals();
                int j2 = 0;
                while (j2 < this.entries) {
                    float last = 0.0f;
                    int k2 = 1;
                    int i11 = 0;
                    while (i11 < this.dim) {
                        int index = j2 / k2 % quantvals;
                        float val1 = this.quantlist[index];
                        val1 = Math.abs(val1) * delta + mindel + last;
                        if (this.q_sequencep != 0) {
                            last = val1;
                        }
                        r2[j2 * this.dim + i11] = val1;
                        k2 *= quantvals;
                        ++i11;
                    }
                    ++j2;
                }
                return r2;
            }
            case 2: {
                int j3 = 0;
                while (j3 < this.entries) {
                    float last = 0.0f;
                    int k3 = 0;
                    while (k3 < this.dim) {
                        float val = this.quantlist[j3 * this.dim + k3];
                        val = Math.abs(val) * delta + mindel + last;
                        if (this.q_sequencep != 0) {
                            last = val;
                        }
                        r2[j3 * this.dim + k3] = val;
                        ++k3;
                    }
                    ++j3;
                }
                break;
            }
        }
        return r2;
    }

    static long float32_pack(float val) {
        int sign = 0;
        if (val < 0.0f) {
            sign = Integer.MIN_VALUE;
            val = -val;
        }
        int exp = Mth.floor(Math.log(val) / Math.log(2.0));
        int mant = (int)Math.rint(Math.pow(val, 20 - exp));
        exp = exp + 768 << 21;
        return sign | exp | mant;
    }

    static float float32_unpack(int val) {
        float mant = val & 0x1FFFFF;
        float exp = (val & 0x7FE00000) >>> 21;
        if ((val & Integer.MIN_VALUE) != 0) {
            mant = -mant;
        }
        return StaticCodeBook.ldexp(mant, (int)exp - 20 - 768);
    }

    static float ldexp(float foo, int e2) {
        return (float)((double)foo * Math.pow(2.0, e2));
    }
}

