/*
 * Decompiled with CFR 0.152.
 */
package com.jcraft.jorbis;

import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Residue0;

class Residue2
extends Residue0 {
    Residue2() {
    }

    @Override
    int inverse(Block vb, Object vl, float[][] in, int[] nonzero, int ch) {
        int i7 = 0;
        while (i7 < ch && nonzero[i7] == 0) {
            ++i7;
        }
        return i7 == ch ? 0 : Residue2._2inverse(vb, vl, in, ch);
    }
}

