/*
 * Decompiled with CFR 0.152.
 */
package com.jcraft.jorbis;

class Mdct {
    int n;
    int log2n;
    float[] trig;
    int[] bitrev;
    float scale;
    float[] _x = new float[1024];
    float[] _w = new float[1024];

    Mdct() {
    }

    void init(int n2) {
        this.bitrev = new int[n2 / 4];
        this.trig = new float[n2 + n2 / 4];
        this.log2n = (int)Math.rint(Math.log(n2) / Math.log(2.0));
        this.n = n2;
        int AE = 0;
        int AO = 1;
        int BE = AE + n2 / 2;
        int BO = BE + 1;
        int CE = BE + n2 / 2;
        int CO = CE + 1;
        int mask = 0;
        while (mask < n2 / 4) {
            this.trig[AE + mask * 2] = (float)Math.cos(Math.PI / (double)n2 * (double)(4 * mask));
            this.trig[AO + mask * 2] = (float)(-Math.sin(Math.PI / (double)n2 * (double)(4 * mask)));
            this.trig[BE + mask * 2] = (float)Math.cos(Math.PI / (double)(2 * n2) * (double)(2 * mask + 1));
            this.trig[BO + mask * 2] = (float)Math.sin(Math.PI / (double)(2 * n2) * (double)(2 * mask + 1));
            ++mask;
        }
        mask = 0;
        while (mask < n2 / 8) {
            this.trig[CE + mask * 2] = (float)Math.cos(Math.PI / (double)n2 * (double)(4 * mask + 2));
            this.trig[CO + mask * 2] = (float)(-Math.sin(Math.PI / (double)n2 * (double)(4 * mask + 2)));
            ++mask;
        }
        mask = (1 << this.log2n - 1) - 1;
        int msb = 1 << this.log2n - 2;
        int i2 = 0;
        while (i2 < n2 / 8) {
            int acc = 0;
            int j2 = 0;
            while (msb >>> j2 != 0) {
                if ((msb >>> j2 & i2) != 0) {
                    acc |= 1 << j2;
                }
                ++j2;
            }
            this.bitrev[i2 * 2] = ~acc & mask;
            this.bitrev[i2 * 2 + 1] = acc;
            ++i2;
        }
        this.scale = 4.0f / (float)n2;
    }

    void clear() {
    }

    void forward(float[] in, float[] out) {
    }

    synchronized void backward(float[] in, float[] out) {
        if (this._x.length < this.n / 2) {
            this._x = new float[this.n / 2];
        }
        if (this._w.length < this.n / 2) {
            this._w = new float[this.n / 2];
        }
        float[] x2 = this._x;
        float[] w2 = this._w;
        int n2 = this.n >>> 1;
        int n4 = this.n >>> 2;
        int n8 = this.n >>> 3;
        int xxx = 1;
        int xx = 0;
        int B = n2;
        int o1 = 0;
        while (o1 < n8) {
            x2[xx++] = -in[xxx + 2] * this.trig[(B -= 2) + 1] - in[xxx] * this.trig[B];
            x2[xx++] = in[xxx] * this.trig[B + 1] - in[xxx + 2] * this.trig[B];
            xxx += 4;
            ++o1;
        }
        xxx = n2 - 4;
        o1 = 0;
        while (o1 < n8) {
            x2[xx++] = in[xxx] * this.trig[(B -= 2) + 1] + in[xxx + 2] * this.trig[B];
            x2[xx++] = in[xxx] * this.trig[B] - in[xxx + 2] * this.trig[B + 1];
            xxx -= 4;
            ++o1;
        }
        float[] f18 = this.mdct_kernel(x2, w2, this.n, n2, n4, n8);
        xx = 0;
        B = n2;
        o1 = n4;
        int o2 = n4 - 1;
        int o3 = n4 + n2;
        int o4 = o3 - 1;
        int i2 = 0;
        while (i2 < n4) {
            float temp1 = f18[xx] * this.trig[B + 1] - f18[xx + 1] * this.trig[B];
            float temp2 = -(f18[xx] * this.trig[B] + f18[xx + 1] * this.trig[B + 1]);
            out[o1] = -temp1;
            out[o2] = temp1;
            out[o3] = temp2;
            out[o4] = temp2;
            ++o1;
            --o2;
            ++o3;
            --o4;
            xx += 2;
            B += 2;
            ++i2;
        }
    }

    private float[] mdct_kernel(float[] x2, float[] w2, int n2, int n22, int n4, int n8) {
        float wB;
        float wD;
        float wC;
        float wA;
        int t2;
        int i27;
        int i26;
        int xA = n4;
        int xB = 0;
        int w22 = n4;
        int A = n22;
        int C = 0;
        while (C < n4) {
            float bit = x2[xA] - x2[xB];
            w2[w22 + C] = x2[xA++] + x2[xB++];
            float x1 = x2[xA] - x2[xB];
            w2[C++] = bit * this.trig[A -= 4] + x1 * this.trig[A + 1];
            w2[C] = x1 * this.trig[A] - bit * this.trig[A + 1];
            w2[w22 + C] = x2[xA++] + x2[xB++];
            ++C;
        }
        C = 0;
        while (C < this.log2n - 3) {
            i26 = n2 >>> C + 2;
            i27 = 1 << C + 3;
            int x22 = n22 - 2;
            A = 0;
            int t1 = 0;
            while (t1 < i26 >>> 2) {
                t2 = x22;
                w22 = x22 - (i26 >> 1);
                wA = this.trig[A];
                wC = this.trig[A + 1];
                x22 -= 2;
                ++i26;
                int wACE = 0;
                while (wACE < 2 << C) {
                    wD = w2[t2] - w2[w22];
                    x2[t2] = w2[t2] + w2[w22];
                    float f10000 = w2[++t2];
                    wB = f10000 - w2[++w22];
                    x2[t2] = w2[t2] + w2[w22];
                    x2[w22] = wB * wA - wD * wC;
                    x2[w22 - 1] = wD * wA + wB * wC;
                    t2 -= i26;
                    w22 -= i26;
                    ++wACE;
                }
                --i26;
                A += i27;
                ++t1;
            }
            float[] i2 = w2;
            w2 = x2;
            x2 = i2;
            ++C;
        }
        C = n2;
        i26 = 0;
        i27 = 0;
        int x23 = n22 - 1;
        int i28 = 0;
        while (i28 < n8) {
            int t1 = this.bitrev[i26++];
            t2 = this.bitrev[i26++];
            wA = w2[t1] - w2[t2 + 1];
            wB = w2[t1 - 1] + w2[t2];
            wC = w2[t1] + w2[t2 + 1];
            wD = w2[t1 - 1] - w2[t2];
            float f30 = wA * this.trig[C];
            float wBCE = wB * this.trig[C++];
            float wACO = wA * this.trig[C];
            float wBCO = wB * this.trig[C++];
            x2[i27++] = (wC + wACO + wBCE) * 0.5f;
            x2[x23--] = (-wD + wBCO - f30) * 0.5f;
            x2[i27++] = (wD + wBCO - f30) * 0.5f;
            x2[x23--] = (wC - wACO - wBCE) * 0.5f;
            ++i28;
        }
        return x2;
    }
}

