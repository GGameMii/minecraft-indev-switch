/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c;

import java.io.File;
import java.io.FilenameFilter;
import net.minecraft.client.c.f;

final class q
implements FilenameFilter {
    q(f f2) {
    }

    public final boolean accept(File file, String string) {
        return string.toLowerCase().endsWith(".mclevel");
    }
}

