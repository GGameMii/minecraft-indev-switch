/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import net.minecraft.client.EnumOS;

final class EnumOSMappingHelper {
    static final int[] osValues = new int[EnumOS.values().length];

    static {
        try {
            EnumOSMappingHelper.osValues[EnumOS.linux.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            EnumOSMappingHelper.osValues[EnumOS.solaris.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            EnumOSMappingHelper.osValues[EnumOS.windows.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            EnumOSMappingHelper.osValues[EnumOS.macos.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }

    EnumOSMappingHelper() {
    }
}

