/*
 * Decompiled with CFR 0.152.
 */
package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.FuncFloor;
import com.jcraft.jorbis.FuncMapping;
import com.jcraft.jorbis.FuncResidue;
import com.jcraft.jorbis.FuncTime;
import com.jcraft.jorbis.Info;
import com.jcraft.jorbis.InfoMode;
import com.jcraft.jorbis.Mdct;
import com.jcraft.jorbis.PsyLook;
import com.jcraft.jorbis.Util;

class Mapping0
extends FuncMapping {
    static int seq = 0;
    float[][] pcmbundle = null;
    int[] zerobundle = null;
    int[] nonzero = null;
    Object[] floormemo = null;

    Mapping0() {
    }

    @Override
    void free_info(Object imap) {
    }

    @Override
    void free_look(Object imap) {
    }

    @Override
    Object look(DspState vd, InfoMode vm, Object m2) {
        Info vi = vd.vi;
        LookMapping0 look = new LookMapping0();
        InfoMapping0 info = look.map = (InfoMapping0)m2;
        look.mode = vm;
        look.time_look = new Object[info.submaps];
        look.floor_look = new Object[info.submaps];
        look.residue_look = new Object[info.submaps];
        look.time_func = new FuncTime[info.submaps];
        look.floor_func = new FuncFloor[info.submaps];
        look.residue_func = new FuncResidue[info.submaps];
        int i2 = 0;
        while (i2 < info.submaps) {
            int timenum = info.timesubmap[i2];
            int floornum = info.floorsubmap[i2];
            int resnum = info.residuesubmap[i2];
            look.time_func[i2] = FuncTime.time_P[vi.time_type[timenum]];
            look.time_look[i2] = look.time_func[i2].look(vd, vm, vi.time_param[timenum]);
            look.floor_func[i2] = FuncFloor.floor_P[vi.floor_type[floornum]];
            look.floor_look[i2] = look.floor_func[i2].look(vd, vm, vi.floor_param[floornum]);
            look.residue_func[i2] = FuncResidue.residue_P[vi.residue_type[resnum]];
            look.residue_look[i2] = look.residue_func[i2].look(vd, vm, vi.residue_param[resnum]);
            ++i2;
        }
        int cfr_ignored_0 = vi.psys;
        look.ch = vi.channels;
        return look;
    }

    @Override
    void pack(Info vi, Object imap, Buffer opb) {
        int i2;
        InfoMapping0 info = (InfoMapping0)imap;
        if (info.submaps > 1) {
            opb.write(1, 1);
            opb.write(info.submaps - 1, 4);
        } else {
            opb.write(0, 1);
        }
        if (info.coupling_steps > 0) {
            opb.write(1, 1);
            opb.write(info.coupling_steps - 1, 8);
            i2 = 0;
            while (i2 < info.coupling_steps) {
                opb.write(info.coupling_mag[i2], Util.ilog2(vi.channels));
                opb.write(info.coupling_ang[i2], Util.ilog2(vi.channels));
                ++i2;
            }
        } else {
            opb.write(0, 1);
        }
        opb.write(0, 2);
        if (info.submaps > 1) {
            i2 = 0;
            while (i2 < vi.channels) {
                opb.write(info.chmuxlist[i2], 4);
                ++i2;
            }
        }
        i2 = 0;
        while (i2 < info.submaps) {
            opb.write(info.timesubmap[i2], 8);
            opb.write(info.floorsubmap[i2], 8);
            opb.write(info.residuesubmap[i2], 8);
            ++i2;
        }
    }

    @Override
    Object unpack(Info vi, Buffer opb) {
        int i2;
        InfoMapping0 info = new InfoMapping0();
        info.submaps = opb.read(1) != 0 ? opb.read(4) + 1 : 1;
        if (opb.read(1) != 0) {
            info.coupling_steps = opb.read(8) + 1;
            i2 = 0;
            while (i2 < info.coupling_steps) {
                int testM = info.coupling_mag[i2] = opb.read(Util.ilog2(vi.channels));
                int testA = info.coupling_ang[i2] = opb.read(Util.ilog2(vi.channels));
                if (testM < 0 || testA < 0 || testM == testA || testM >= vi.channels || testA >= vi.channels) {
                    info.free();
                    return null;
                }
                ++i2;
            }
        }
        if (opb.read(2) > 0) {
            info.free();
            return null;
        }
        if (info.submaps > 1) {
            i2 = 0;
            while (i2 < vi.channels) {
                info.chmuxlist[i2] = opb.read(4);
                if (info.chmuxlist[i2] >= info.submaps) {
                    info.free();
                    return null;
                }
                ++i2;
            }
        }
        i2 = 0;
        while (i2 < info.submaps) {
            info.timesubmap[i2] = opb.read(8);
            if (info.timesubmap[i2] >= vi.times) {
                info.free();
                return null;
            }
            info.floorsubmap[i2] = opb.read(8);
            if (info.floorsubmap[i2] >= vi.floors) {
                info.free();
                return null;
            }
            info.residuesubmap[i2] = opb.read(8);
            if (info.residuesubmap[i2] >= vi.residues) {
                info.free();
                return null;
            }
            ++i2;
        }
        return info;
    }

    @Override
    synchronized int inverse(Block vb, Object l2) {
        int j1;
        int j2;
        float[] pcm;
        DspState vd = vb.vd;
        Info vi = vd.vi;
        LookMapping0 look = (LookMapping0)l2;
        InfoMapping0 info = look.map;
        InfoMode mode = look.mode;
        int n2 = vb.pcmend = vi.blocksizes[vb.W];
        float[] window = vd.window[vb.W][vb.lW][vb.nW][mode.windowtype];
        if (this.pcmbundle == null || this.pcmbundle.length < vi.channels) {
            this.pcmbundle = new float[vi.channels][];
            this.nonzero = new int[vi.channels];
            this.zerobundle = new int[vi.channels];
            this.floormemo = new Object[vi.channels];
        }
        int i2 = 0;
        while (i2 < vi.channels) {
            pcm = vb.pcm[i2];
            j2 = info.chmuxlist[i2];
            this.floormemo[i2] = look.floor_func[j2].inverse1(vb, look.floor_look[j2], this.floormemo[i2]);
            this.nonzero[i2] = this.floormemo[i2] != null ? 1 : 0;
            j1 = 0;
            while (j1 < n2 / 2) {
                pcm[j1] = 0.0f;
                ++j1;
            }
            ++i2;
        }
        i2 = 0;
        while (i2 < info.coupling_steps) {
            if (this.nonzero[info.coupling_mag[i2]] != 0 || this.nonzero[info.coupling_ang[i2]] != 0) {
                this.nonzero[info.coupling_mag[i2]] = 1;
                this.nonzero[info.coupling_ang[i2]] = 1;
            }
            ++i2;
        }
        i2 = 0;
        while (i2 < info.submaps) {
            int i16 = 0;
            j2 = 0;
            while (j2 < vi.channels) {
                if (info.chmuxlist[j2] == i2) {
                    this.zerobundle[i16] = this.nonzero[j2] != 0 ? 1 : 0;
                    this.pcmbundle[i16++] = vb.pcm[j2];
                }
                ++j2;
            }
            look.residue_func[i2].inverse(vb, look.residue_look[i2], this.pcmbundle, this.zerobundle, i16);
            ++i2;
        }
        i2 = info.coupling_steps - 1;
        while (i2 >= 0) {
            pcm = vb.pcm[info.coupling_mag[i2]];
            float[] f17 = vb.pcm[info.coupling_ang[i2]];
            j1 = 0;
            while (j1 < n2 / 2) {
                float mag = pcm[j1];
                float ang = f17[j1];
                if (mag > 0.0f) {
                    if (ang > 0.0f) {
                        pcm[j1] = mag;
                        f17[j1] = mag - ang;
                    } else {
                        f17[j1] = mag;
                        pcm[j1] = mag + ang;
                    }
                } else if (ang > 0.0f) {
                    pcm[j1] = mag;
                    f17[j1] = mag + ang;
                } else {
                    f17[j1] = mag;
                    pcm[j1] = mag - ang;
                }
                ++j1;
            }
            --i2;
        }
        i2 = 0;
        while (i2 < vi.channels) {
            pcm = vb.pcm[i2];
            j2 = info.chmuxlist[i2];
            look.floor_func[j2].inverse2(vb, look.floor_look[j2], this.floormemo[i2], pcm);
            ++i2;
        }
        i2 = 0;
        while (i2 < vi.channels) {
            pcm = vb.pcm[i2];
            ((Mdct)vd.transform[vb.W][0]).backward(pcm, pcm);
            ++i2;
        }
        i2 = 0;
        while (i2 < vi.channels) {
            pcm = vb.pcm[i2];
            if (this.nonzero[i2] != 0) {
                j2 = 0;
                while (j2 < n2) {
                    int n3 = j2;
                    pcm[n3] = pcm[n3] * window[j2];
                    ++j2;
                }
            } else {
                j2 = 0;
                while (j2 < n2) {
                    pcm[j2] = 0.0f;
                    ++j2;
                }
            }
            ++i2;
        }
        return 0;
    }

    class InfoMapping0 {
        int submaps;
        int[] chmuxlist = new int[256];
        int[] timesubmap = new int[16];
        int[] floorsubmap = new int[16];
        int[] residuesubmap = new int[16];
        int[] psysubmap = new int[16];
        int coupling_steps;
        int[] coupling_mag = new int[256];
        int[] coupling_ang = new int[256];

        InfoMapping0() {
        }

        void free() {
            this.chmuxlist = null;
            this.timesubmap = null;
            this.floorsubmap = null;
            this.residuesubmap = null;
            this.psysubmap = null;
            this.coupling_mag = null;
            this.coupling_ang = null;
        }
    }

    class LookMapping0 {
        InfoMode mode;
        InfoMapping0 map;
        Object[] time_look;
        Object[] floor_look;
        Object[] floor_state;
        Object[] residue_look;
        PsyLook[] psy_look;
        FuncTime[] time_func;
        FuncFloor[] floor_func;
        FuncResidue[] residue_func;
        int ch;
        float[][] decay;
        int lastframe;

        LookMapping0() {
        }
    }
}

