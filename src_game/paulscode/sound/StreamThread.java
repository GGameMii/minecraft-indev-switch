/*
 * Decompiled with CFR 0.152.
 */
package paulscode.sound;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import paulscode.sound.SimpleThread;
import paulscode.sound.Source;

public class StreamThread
extends SimpleThread {
    private List<Source> streamingSources = new LinkedList<Source>();
    private final Object listLock = new Object();

    @Override
    protected void cleanup() {
        this.kill();
        super.cleanup();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     */
    @Override
    public void run() {
        try {
            this.snooze(3600000L);
            ** GOTO lbl50
            {
                var1_1 = this.listLock;
                synchronized (var1_1) {
                    iter = this.streamingSources.listIterator();
                    while (!this.dying() && iter.hasNext()) {
                        src = iter.next();
                        if (src == null) {
                            iter.remove();
                            continue;
                        }
                        if (src.stopped()) {
                            if (src.rawDataStream) continue;
                            iter.remove();
                            continue;
                        }
                        if (src.active()) {
                            if (src.paused()) continue;
                            src.checkFadeOut();
                            if (src.stream() || src.rawDataStream || src.channel != null && src.channel.processBuffer()) continue;
                            if (src.toLoop) {
                                if (src.playing()) continue;
                                if (src.checkFadeOut()) {
                                    src.preLoad = true;
                                    continue;
                                }
                                src.incrementSoundSequence();
                                src.preLoad = true;
                                continue;
                            }
                            if (src.playing() || src.checkFadeOut()) continue;
                            if (src.incrementSoundSequence()) {
                                src.preLoad = true;
                                continue;
                            }
                            iter.remove();
                            continue;
                        }
                        if (src.toLoop || src.rawDataStream) {
                            src.toPlay = true;
                        }
                        iter.remove();
                    }
                }
                if (!this.dying() && !this.streamingSources.isEmpty()) {
                    this.snooze(20L);
                }
                do {
                    if (!this.dying() && !this.streamingSources.isEmpty()) continue block6;
                    if (this.dying() || !this.streamingSources.isEmpty()) continue;
                    this.snooze(3600000L);
lbl50:
                    // 3 sources

                } while (!this.dying());
            }
        }
        finally {
            this.cleanup();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void watch(Source source) {
        if (source != null && !this.streamingSources.contains(source)) {
            Object object = this.listLock;
            synchronized (object) {
                ListIterator<Source> iter = this.streamingSources.listIterator();
                while (iter.hasNext()) {
                    Source src = iter.next();
                    if (src == null) {
                        iter.remove();
                        continue;
                    }
                    if (source.channel != src.channel) continue;
                    src.stop();
                    iter.remove();
                }
                this.streamingSources.add(source);
            }
        }
    }
}

