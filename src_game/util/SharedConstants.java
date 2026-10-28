/*
 * Decompiled with CFR 0.152.
 */
package util;

public class SharedConstants {
    public static final String MOD_VERSION = "v1.3.6";
    public static final int PVN = 8;
    public static final String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-_'*!\"#%/()=+?[]{}<>";
    public static final byte CLIENT_EXT = 66;
    public static final int SOUND_REMOVE_TILE = 1;
    public static final int SOUND_PLACE_TILE = 2;
    public static final int SOUND_BOW = 3;

    public static boolean charAllowed(char c2) {
        return allowedChars.indexOf(c2) >= 0;
    }
}

