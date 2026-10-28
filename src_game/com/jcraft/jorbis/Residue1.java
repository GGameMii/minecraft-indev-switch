/*
 * Decompiled with CFR 0.152.
 */
package com.jcraft.jorbis;

import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Residue0;

class Residue1
extends Residue0 {
    Residue1() {
    }

    @Override
    int inverse(Block vb, Object vl, float[][] in, int[] nonzero, int ch) {
        int used = 0;
        int i2 = 0;
        while (i2 < ch) {
            if (nonzero[i2] != 0) {
                in[used++] = in[i2];
            }
            ++i2;
        }
        if (used != 0) {
            return Residue1._01inverse(vb, vl, in, used, 1);
        }
        return 0;
    }
}

