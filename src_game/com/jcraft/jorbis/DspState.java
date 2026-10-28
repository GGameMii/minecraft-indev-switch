/*
 * Decompiled with CFR 0.152.
 */
package com.jcraft.jorbis;

import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.CodeBook;
import com.jcraft.jorbis.FuncMapping;
import com.jcraft.jorbis.Info;
import com.jcraft.jorbis.Mdct;
import com.jcraft.jorbis.Util;

public class DspState {
    static final float M_PI = (float)Math.PI;
    static final int VI_TRANSFORMB = 1;
    static final int VI_WINDOWB = 1;
    int analysisp;
    Info vi;
    int modebits;
    float[][] pcm;
    int pcm_storage;
    int pcm_current;
    int pcm_returned;
    float[] multipliers;
    int envelope_storage;
    int envelope_current;
    int eofflag;
    int lW;
    int W;
    int nW;
    int centerW;
    long granulepos;
    long sequence;
    long glue_bits;
    long time_bits;
    long floor_bits;
    long res_bits;
    float[][][][][] window;
    Object[][] transform = new Object[2][];
    CodeBook[] fullbooks;
    Object[] mode;
    byte[] header;
    byte[] header1;
    byte[] header2;

    public DspState() {
        this.window = new float[2][][][][];
        this.window[0] = new float[2][][][];
        this.window[0][0] = new float[2][][];
        this.window[0][1] = new float[2][][];
        this.window[0][0][0] = new float[2][];
        this.window[0][0][1] = new float[2][];
        this.window[0][1][0] = new float[2][];
        this.window[0][1][1] = new float[2][];
        this.window[1] = new float[2][][][];
        this.window[1][0] = new float[2][][];
        this.window[1][1] = new float[2][][];
        this.window[1][0][0] = new float[2][];
        this.window[1][0][1] = new float[2][];
        this.window[1][1][0] = new float[2][];
        this.window[1][1][1] = new float[2][];
    }

    static float[] window(int type, int window, int left, int right) {
        float[] ret = new float[window];
        switch (type) {
            case 0: {
                float x2;
                int leftbegin = window / 4 - left / 2;
                int rightbegin = window - window / 4 - right / 2;
                int i2 = 0;
                while (i2 < left) {
                    x2 = (float)(((double)i2 + 0.5) / (double)left * 3.1415927410125732 / 2.0);
                    x2 = (float)Math.sin(x2);
                    x2 *= x2;
                    x2 = (float)((double)x2 * 1.5707963705062866);
                    ret[i2 + leftbegin] = x2 = (float)Math.sin(x2);
                    ++i2;
                }
                i2 = leftbegin + left;
                while (i2 < rightbegin) {
                    ret[i2] = 1.0f;
                    ++i2;
                }
                i2 = 0;
                while (i2 < right) {
                    x2 = (float)(((double)(right - i2) - 0.5) / (double)right * 3.1415927410125732 / 2.0);
                    x2 = (float)Math.sin(x2);
                    x2 *= x2;
                    x2 = (float)((double)x2 * 1.5707963705062866);
                    ret[i2 + rightbegin] = x2 = (float)Math.sin(x2);
                    ++i2;
                }
                return ret;
            }
        }
        return null;
    }

    int init(Info vi, boolean encp) {
        this.vi = vi;
        this.modebits = Util.ilog2(vi.modes);
        this.transform[0] = new Object[1];
        this.transform[1] = new Object[1];
        this.transform[0][0] = new Mdct();
        this.transform[1][0] = new Mdct();
        ((Mdct)this.transform[0][0]).init(vi.blocksizes[0]);
        ((Mdct)this.transform[1][0]).init(vi.blocksizes[1]);
        this.window[0][0][0] = new float[1][];
        this.window[0][0][1] = this.window[0][0][0];
        this.window[0][1][0] = this.window[0][0][0];
        this.window[0][1][1] = this.window[0][0][0];
        this.window[1][0][0] = new float[1][];
        this.window[1][0][1] = new float[1][];
        this.window[1][1][0] = new float[1][];
        this.window[1][1][1] = new float[1][];
        int i2 = 0;
        while (i2 < 1) {
            this.window[0][0][0][i2] = DspState.window(i2, vi.blocksizes[0], vi.blocksizes[0] / 2, vi.blocksizes[0] / 2);
            this.window[1][0][0][i2] = DspState.window(i2, vi.blocksizes[1], vi.blocksizes[0] / 2, vi.blocksizes[0] / 2);
            this.window[1][0][1][i2] = DspState.window(i2, vi.blocksizes[1], vi.blocksizes[0] / 2, vi.blocksizes[1] / 2);
            this.window[1][1][0][i2] = DspState.window(i2, vi.blocksizes[1], vi.blocksizes[1] / 2, vi.blocksizes[0] / 2);
            this.window[1][1][1][i2] = DspState.window(i2, vi.blocksizes[1], vi.blocksizes[1] / 2, vi.blocksizes[1] / 2);
            ++i2;
        }
        this.fullbooks = new CodeBook[vi.books];
        i2 = 0;
        while (i2 < vi.books) {
            this.fullbooks[i2] = new CodeBook();
            this.fullbooks[i2].init_decode(vi.book_param[i2]);
            ++i2;
        }
        this.pcm_storage = 8192;
        this.pcm = new float[vi.channels][];
        i2 = 0;
        while (i2 < vi.channels) {
            this.pcm[i2] = new float[this.pcm_storage];
            ++i2;
        }
        this.lW = 0;
        this.W = 0;
        this.pcm_current = this.centerW = vi.blocksizes[1] / 2;
        this.mode = new Object[vi.modes];
        i2 = 0;
        while (i2 < vi.modes) {
            int mapnum = vi.mode_param[i2].mapping;
            int maptype = vi.map_type[mapnum];
            this.mode[i2] = FuncMapping.mapping_P[maptype].look(this, vi.mode_param[i2], vi.map_param[mapnum]);
            ++i2;
        }
        return 0;
    }

    public int synthesis_init(Info vi) {
        this.init(vi, false);
        this.pcm_returned = this.centerW;
        this.centerW -= vi.blocksizes[this.W] / 4 + vi.blocksizes[this.lW] / 4;
        this.granulepos = -1L;
        this.sequence = -1L;
        return 0;
    }

    DspState(Info vi) {
        this();
        this.init(vi, false);
        this.pcm_returned = this.centerW;
        this.centerW -= vi.blocksizes[this.W] / 4 + vi.blocksizes[this.lW] / 4;
        this.granulepos = -1L;
        this.sequence = -1L;
    }

    public int synthesis_blockin(Block vb) {
        int j2;
        int _centerW;
        int sizeW;
        if (this.centerW > this.vi.blocksizes[1] / 2 && this.pcm_returned > 8192) {
            sizeW = this.centerW - this.vi.blocksizes[1] / 2;
            sizeW = this.pcm_returned < sizeW ? this.pcm_returned : sizeW;
            this.pcm_current -= sizeW;
            this.centerW -= sizeW;
            this.pcm_returned -= sizeW;
            if (sizeW != 0) {
                _centerW = 0;
                while (_centerW < this.vi.channels) {
                    System.arraycopy(this.pcm[_centerW], sizeW, this.pcm[_centerW], 0, this.pcm_current);
                    ++_centerW;
                }
            }
        }
        this.lW = this.W;
        this.W = vb.W;
        this.nW = -1;
        this.glue_bits += (long)vb.glue_bits;
        this.time_bits += (long)vb.time_bits;
        this.floor_bits += (long)vb.floor_bits;
        this.res_bits += (long)vb.res_bits;
        if (this.sequence + 1L != vb.sequence) {
            this.granulepos = -1L;
        }
        this.sequence = vb.sequence;
        sizeW = this.vi.blocksizes[this.W];
        _centerW = this.centerW + this.vi.blocksizes[this.lW] / 4 + sizeW / 4;
        int beginW = _centerW - sizeW / 2;
        int endW = beginW + sizeW;
        int beginSl = 0;
        int endSl = 0;
        if (endW > this.pcm_storage) {
            this.pcm_storage = endW + this.vi.blocksizes[1];
            j2 = 0;
            while (j2 < this.vi.channels) {
                float[] _pcm = new float[this.pcm_storage];
                System.arraycopy(this.pcm[j2], 0, _pcm, 0, this.pcm[j2].length);
                this.pcm[j2] = _pcm;
                ++j2;
            }
        }
        switch (this.W) {
            case 0: {
                beginSl = 0;
                endSl = this.vi.blocksizes[0] / 2;
                break;
            }
            case 1: {
                beginSl = this.vi.blocksizes[1] / 4 - this.vi.blocksizes[this.lW] / 4;
                endSl = beginSl + this.vi.blocksizes[this.lW] / 2;
            }
        }
        j2 = 0;
        while (j2 < this.vi.channels) {
            int i11 = beginW;
            int i12 = beginSl;
            while (i12 < endSl) {
                float[] fArray = this.pcm[j2];
                int n2 = i11 + i12;
                fArray[n2] = fArray[n2] + vb.pcm[j2][i12];
                ++i12;
            }
            while (i12 < sizeW) {
                this.pcm[j2][i11 + i12] = vb.pcm[j2][i12];
                ++i12;
            }
            ++j2;
        }
        if (this.granulepos == -1L) {
            this.granulepos = vb.granulepos;
        } else {
            this.granulepos += (long)(_centerW - this.centerW);
            if (vb.granulepos != -1L && this.granulepos != vb.granulepos) {
                if (this.granulepos > vb.granulepos && vb.eofflag != 0) {
                    _centerW = (int)((long)_centerW - (this.granulepos - vb.granulepos));
                }
                this.granulepos = vb.granulepos;
            }
        }
        this.centerW = _centerW;
        this.pcm_current = endW;
        if (vb.eofflag != 0) {
            this.eofflag = 1;
        }
        return 0;
    }

    public int synthesis_pcmout(float[][][] _pcm, int[] index) {
        if (this.pcm_returned >= this.centerW) {
            return 0;
        }
        if (_pcm != null) {
            int i2 = 0;
            while (i2 < this.vi.channels) {
                index[i2] = this.pcm_returned;
                ++i2;
            }
            _pcm[0] = this.pcm;
        }
        return this.centerW - this.pcm_returned;
    }

    public int synthesis_read(int bytes) {
        if (bytes != 0 && this.pcm_returned + bytes > this.centerW) {
            return -1;
        }
        this.pcm_returned += bytes;
        return 0;
    }

    public void clear() {
    }
}

