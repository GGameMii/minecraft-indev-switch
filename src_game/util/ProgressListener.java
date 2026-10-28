/*
 * Decompiled with CFR 0.152.
 */
package util;

public interface ProgressListener {
    public void progressStartNoAbort(String var1);

    public void progressStart(String var1);

    public void progressStage(String var1);

    public void progressStagePercentage(int var1);
}

