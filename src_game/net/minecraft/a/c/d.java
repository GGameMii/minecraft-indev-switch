/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c;

public enum d {
    a("Kebab", 16, 16, 0, 0),
    g("Aztec", 16, 16, 16, 0),
    h("Alban", 16, 16, 32, 0),
    i("Aztec2", 16, 16, 48, 0),
    j("Bomb", 16, 16, 64, 0),
    k("Plant", 16, 16, 80, 0),
    l("Wasteland", 16, 16, 96, 0),
    m("Pool", 32, 16, 0, 32),
    n("Courbet", 32, 16, 32, 32),
    o("Sea", 32, 16, 64, 32),
    p("Sunset", 32, 16, 96, 32),
    q("Wanderer", 16, 32, 0, 64),
    r("Match", 32, 32, 0, 128),
    s("Bust", 32, 32, 32, 128),
    t("Stage", 32, 32, 64, 128),
    u("Void", 32, 32, 96, 128),
    v("SkullAndRoses", 32, 32, 128, 128),
    w("Fighters", 64, 32, 0, 96),
    x("Pointer", 64, 64, 0, 192);

    public final String b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private d(int n2, int n3, int n4) {
        void var6_4;
        this.b = (String)n2;
        this.c = n3;
        this.d = n4;
        this.e = var6_4;
        this.f = var7_5;
    }
}

