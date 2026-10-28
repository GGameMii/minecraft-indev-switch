/*
 * Decompiled with CFR 0.152.
 */
package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.CodeBook;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.FuncFloor;
import com.jcraft.jorbis.Info;
import com.jcraft.jorbis.InfoMode;
import com.jcraft.jorbis.Lpc;
import com.jcraft.jorbis.Lsp;
import com.jcraft.jorbis.Util;
import util.Mth;

class Floor0
extends FuncFloor {
    float[] lsp = null;

    Floor0() {
    }

    @Override
    void pack(Object i2, Buffer opb) {
        InfoFloor0 info = (InfoFloor0)i2;
        opb.write(info.order, 8);
        opb.write(info.rate, 16);
        opb.write(info.barkmap, 16);
        opb.write(info.ampbits, 6);
        opb.write(info.ampdB, 8);
        opb.write(info.numbooks - 1, 4);
        int j2 = 0;
        while (j2 < info.numbooks) {
            opb.write(info.books[j2], 8);
            ++j2;
        }
    }

    @Override
    Object unpack(Info vi, Buffer opb) {
        InfoFloor0 info = new InfoFloor0();
        info.order = opb.read(8);
        info.rate = opb.read(16);
        info.barkmap = opb.read(16);
        info.ampbits = opb.read(6);
        info.ampdB = opb.read(8);
        info.numbooks = opb.read(4) + 1;
        if (info.order >= 1 && info.rate >= 1 && info.barkmap >= 1 && info.numbooks >= 1) {
            int j2 = 0;
            while (j2 < info.numbooks) {
                info.books[j2] = opb.read(8);
                if (info.books[j2] < 0 || info.books[j2] >= vi.books) {
                    return null;
                }
                ++j2;
            }
            return info;
        }
        return null;
    }

    @Override
    Object look(DspState vd, InfoMode mi, Object i2) {
        Info vi = vd.vi;
        InfoFloor0 info = (InfoFloor0)i2;
        LookFloor0 look = new LookFloor0();
        look.m = info.order;
        look.n = vi.blocksizes[mi.blockflag] / 2;
        look.ln = info.barkmap;
        look.vi = info;
        look.lpclook.init(look.ln, look.m);
        float scale = (float)look.ln / Floor0.toBARK((float)((double)info.rate / 2.0));
        look.linearmap = new int[look.n];
        int j2 = 0;
        while (j2 < look.n) {
            int val = Mth.floor(Floor0.toBARK((float)((double)info.rate / 2.0 / (double)look.n * (double)j2)) * scale);
            if (val >= look.ln) {
                val = look.ln;
            }
            look.linearmap[j2] = val;
            ++j2;
        }
        return look;
    }

    static float toBARK(float f2) {
        return (float)(13.1 * Math.atan(7.4E-4 * (double)f2) + 2.24 * Math.atan((double)(f2 * f2) * 1.85E-8) + 1.0E-4 * (double)f2);
    }

    Object state(Object i2) {
        EchstateFloor0 state = new EchstateFloor0();
        InfoFloor0 info = (InfoFloor0)i2;
        state.codewords = new int[info.order];
        state.curve = new float[info.barkmap];
        state.frameno = -1L;
        return state;
    }

    @Override
    void free_info(Object i2) {
    }

    @Override
    void free_look(Object i2) {
    }

    @Override
    void free_state(Object vs) {
    }

    @Override
    int forward(Block vb, Object i2, float[] in, float[] out, Object vs) {
        return 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive exception aggregation
     */
    int inverse(Block vb, Object i2, float[] out) {
        LookFloor0 look = (LookFloor0)i2;
        InfoFloor0 info = look.vi;
        int ampraw = vb.opb.read(info.ampbits);
        if (ampraw > 0) {
            int maxval = (1 << info.ampbits) - 1;
            float amp = (float)ampraw / (float)maxval * (float)info.ampdB;
            int booknum = vb.opb.read(Util.ilog(info.numbooks));
            if (booknum != -1 && booknum < info.numbooks) {
                Floor0 floor0 = this;
                synchronized (floor0) {
                    if (this.lsp != null && this.lsp.length >= look.m) {
                        int b2 = 0;
                        while (b2 < look.m) {
                            this.lsp[b2] = 0.0f;
                            ++b2;
                        }
                    } else {
                        this.lsp = new float[look.m];
                    }
                    CodeBook codeBook16 = vb.vd.fullbooks[info.books[booknum]];
                    float last = 0.0f;
                    int j2 = 0;
                    while (j2 < look.m) {
                        out[j2] = 0.0f;
                        ++j2;
                    }
                    j2 = 0;
                    while (j2 < look.m) {
                        if (codeBook16.decodevs(this.lsp, j2, vb.opb, 1, -1) == -1) {
                            int k2 = 0;
                            while (k2 < look.n) {
                                out[k2] = 0.0f;
                                ++k2;
                            }
                            return 0;
                        }
                        j2 += codeBook16.dim;
                    }
                    j2 = 0;
                    while (j2 < look.m) {
                        int k3 = 0;
                        while (k3 < codeBook16.dim) {
                            int n2 = j2++;
                            this.lsp[n2] = this.lsp[n2] + last;
                            ++k3;
                        }
                        last = this.lsp[j2 - 1];
                    }
                    Lsp.lsp_to_curve(out, look.linearmap, look.n, look.ln, this.lsp, look.m, amp, info.ampdB);
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    Object inverse1(Block vb, Object i2, Object memo) {
        int ampraw;
        LookFloor0 look = (LookFloor0)i2;
        InfoFloor0 info = look.vi;
        float[] lsp = null;
        if (memo instanceof float[]) {
            lsp = (float[])memo;
        }
        if ((ampraw = vb.opb.read(info.ampbits)) > 0) {
            int maxval = (1 << info.ampbits) - 1;
            float amp = (float)ampraw / (float)maxval * (float)info.ampdB;
            int booknum = vb.opb.read(Util.ilog(info.numbooks));
            if (booknum != -1 && booknum < info.numbooks) {
                int j2;
                CodeBook b2 = vb.vd.fullbooks[info.books[booknum]];
                float last = 0.0f;
                if (lsp != null && lsp.length >= look.m + 1) {
                    j2 = 0;
                    while (j2 < lsp.length) {
                        lsp[j2] = 0.0f;
                        ++j2;
                    }
                } else {
                    lsp = new float[look.m + 1];
                }
                j2 = 0;
                while (j2 < look.m) {
                    if (b2.decodev_set(lsp, j2, vb.opb, b2.dim) == -1) {
                        return null;
                    }
                    j2 += b2.dim;
                }
                j2 = 0;
                while (j2 < look.m) {
                    int k2 = 0;
                    while (k2 < b2.dim) {
                        int n2 = j2++;
                        lsp[n2] = lsp[n2] + last;
                        ++k2;
                    }
                    last = lsp[j2 - 1];
                }
                lsp[look.m] = amp;
                return lsp;
            }
        }
        return null;
    }

    @Override
    int inverse2(Block vb, Object i2, Object memo, float[] out) {
        LookFloor0 look = (LookFloor0)i2;
        InfoFloor0 info = look.vi;
        if (memo != null) {
            float[] f9 = (float[])memo;
            float amp = f9[look.m];
            Lsp.lsp_to_curve(out, look.linearmap, look.n, look.ln, f9, look.m, amp, info.ampdB);
            return 1;
        }
        int j2 = 0;
        while (j2 < look.n) {
            out[j2] = 0.0f;
            ++j2;
        }
        return 0;
    }

    static float fromdB(float x2) {
        return (float)Math.exp((double)x2 * 0.11512925);
    }

    static void lsp_to_lpc(float[] lsp, float[] lpc, int m2) {
        int m22 = m2 / 2;
        float[] O = new float[m22];
        float[] E = new float[m22];
        float[] Ae = new float[m22 + 1];
        float[] Ao = new float[m22 + 1];
        float[] Be = new float[m22];
        float[] Bo = new float[m22];
        int i2 = 0;
        while (i2 < m22) {
            O[i2] = (float)(-2.0 * Math.cos(lsp[i2 * 2]));
            E[i2] = (float)(-2.0 * Math.cos(lsp[i2 * 2 + 1]));
            ++i2;
        }
        int j2 = 0;
        while (j2 < m22) {
            Ae[j2] = 0.0f;
            Ao[j2] = 1.0f;
            Be[j2] = 0.0f;
            Bo[j2] = 1.0f;
            ++j2;
        }
        Ao[j2] = 1.0f;
        Ae[j2] = 1.0f;
        i2 = 1;
        while (i2 < m2 + 1) {
            float B = 0.0f;
            float A = 0.0f;
            j2 = 0;
            while (j2 < m22) {
                float temp = O[j2] * Ao[j2] + Ae[j2];
                Ae[j2] = Ao[j2];
                Ao[j2] = A;
                A += temp;
                temp = E[j2] * Bo[j2] + Be[j2];
                Be[j2] = Bo[j2];
                Bo[j2] = B;
                B += temp;
                ++j2;
            }
            lpc[i2 - 1] = (A + Ao[j2] + B - Ae[j2]) / 2.0f;
            Ao[j2] = A;
            Ae[j2] = B;
            ++i2;
        }
    }

    static void lpc_to_curve(float[] curve, float[] lpc, float amp, LookFloor0 l2, String name, int frameno) {
        float[] lcurve = new float[Math.max(l2.ln * 2, l2.m * 2 + 2)];
        if (amp == 0.0f) {
            int i2 = 0;
            while (i2 < l2.n) {
                curve[i2] = 0.0f;
                ++i2;
            }
        } else {
            l2.lpclook.lpc_to_curve(lcurve, lpc, amp);
            int i3 = 0;
            while (i3 < l2.n) {
                curve[i3] = lcurve[l2.linearmap[i3]];
                ++i3;
            }
        }
    }

    class EchstateFloor0 {
        int[] codewords;
        float[] curve;
        long frameno;
        long codes;

        EchstateFloor0() {
        }
    }

    class InfoFloor0 {
        int order;
        int rate;
        int barkmap;
        int ampbits;
        int ampdB;
        int numbooks;
        int[] books = new int[16];

        InfoFloor0() {
        }
    }

    class LookFloor0 {
        int n;
        int ln;
        int m;
        int[] linearmap;
        InfoFloor0 vi;
        Lpc lpclook = new Lpc();

        LookFloor0() {
        }
    }
}

