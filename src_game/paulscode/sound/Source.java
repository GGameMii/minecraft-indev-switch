/*
 * Decompiled with CFR 0.152.
 */
package paulscode.sound;

import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.sound.sampled.AudioFormat;
import paulscode.sound.Channel;
import paulscode.sound.FilenameURL;
import paulscode.sound.ICodec;
import paulscode.sound.Library;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;
import paulscode.sound.Vector3D;

public class Source {
    protected Class<? extends Library> libraryType = Library.class;
    private SoundSystemLogger logger = SoundSystemConfig.getLogger();
    public boolean rawDataStream = false;
    public AudioFormat rawDataFormat = null;
    public boolean temporary = false;
    public boolean priority = false;
    public boolean toStream = false;
    public boolean toLoop = false;
    public boolean toPlay = false;
    public String sourcename = "";
    public FilenameURL filenameURL = null;
    public Vector3D position;
    public int attModel = 0;
    public float distOrRoll = 0.0f;
    public float gain = 1.0f;
    public float sourceVolume = 1.0f;
    protected float pitch = 1.0f;
    public float distanceFromListener = 0.0f;
    public Channel channel = null;
    private boolean active = true;
    private boolean stopped = true;
    private boolean paused = false;
    public SoundBuffer soundBuffer = null;
    protected ICodec codec = null;
    protected boolean reverseByteOrder = false;
    protected LinkedList<FilenameURL> soundSequenceQueue = null;
    protected final Object soundSequenceLock = new Object();
    public boolean preLoad = false;
    protected float fadeOutGain = -1.0f;
    protected float fadeInGain = 1.0f;
    protected long fadeOutMilis = 0L;
    protected long fadeInMilis = 0L;
    protected long lastFadeCheck = 0L;

    public Source(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x2, float y2, float z2, int attModel, float distOrRoll, boolean temporary) {
        this.priority = priority;
        this.toStream = toStream;
        this.toLoop = toLoop;
        this.sourcename = sourcename;
        this.filenameURL = filenameURL;
        this.soundBuffer = soundBuffer;
        this.position = new Vector3D(x2, y2, z2);
        this.attModel = attModel;
        this.distOrRoll = distOrRoll;
        this.temporary = temporary;
        if (toStream && filenameURL != null) {
            this.codec = SoundSystemConfig.getCodec(filenameURL.getFilename());
        }
    }

    public Source(Source old, SoundBuffer soundBuffer) {
        this.priority = old.priority;
        this.toStream = old.toStream;
        this.toLoop = old.toLoop;
        this.sourcename = old.sourcename;
        this.filenameURL = old.filenameURL;
        this.position = old.position.clone();
        this.attModel = old.attModel;
        this.distOrRoll = old.distOrRoll;
        this.temporary = old.temporary;
        this.sourceVolume = old.sourceVolume;
        this.rawDataStream = old.rawDataStream;
        this.rawDataFormat = old.rawDataFormat;
        this.soundBuffer = soundBuffer;
        if (this.toStream && this.filenameURL != null) {
            this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
        }
    }

    public Source(AudioFormat audioFormat, boolean priority, String sourcename, float x2, float y2, float z2, int attModel, float distOrRoll) {
        this.priority = priority;
        this.toStream = true;
        this.toLoop = false;
        this.sourcename = sourcename;
        this.filenameURL = null;
        this.soundBuffer = null;
        this.position = new Vector3D(x2, y2, z2);
        this.attModel = attModel;
        this.distOrRoll = distOrRoll;
        this.temporary = false;
        this.rawDataStream = true;
        this.rawDataFormat = audioFormat;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void cleanup() {
        if (this.codec != null) {
            this.codec.cleanup();
        }
        Object object = this.soundSequenceLock;
        synchronized (object) {
            if (this.soundSequenceQueue != null) {
                this.soundSequenceQueue.clear();
            }
            this.soundSequenceQueue = null;
        }
        this.sourcename = null;
        this.filenameURL = null;
        this.position = null;
        this.soundBuffer = null;
        this.codec = null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void queueSound(FilenameURL filenameURL) {
        if (!this.toStream) {
            this.errorMessage("Method 'queueSound' may only be used for streaming and MIDI sources.");
        } else if (filenameURL == null) {
            this.errorMessage("File not specified in method 'queueSound'");
        } else {
            Object object = this.soundSequenceLock;
            synchronized (object) {
                if (this.soundSequenceQueue == null) {
                    this.soundSequenceQueue = new LinkedList();
                }
                this.soundSequenceQueue.add(filenameURL);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void dequeueSound(String filename) {
        if (!this.toStream) {
            this.errorMessage("Method 'dequeueSound' may only be used for streaming and MIDI sources.");
        } else {
            if (filename != null && !filename.equals("")) {
                Object object = this.soundSequenceLock;
                synchronized (object) {
                    if (this.soundSequenceQueue != null) {
                        this.soundSequenceQueue.remove(filename);
                    }
                }
                object = this.soundSequenceLock;
                synchronized (object) {
                    if (this.soundSequenceQueue != null) {
                        ListIterator i2 = this.soundSequenceQueue.listIterator();
                        while (i2.hasNext()) {
                            if (!((FilenameURL)i2.next()).getFilename().equals(filename)) continue;
                            i2.remove();
                            break;
                        }
                    }
                }
            }
            this.errorMessage("Filename not specified in method 'dequeueSound'");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void fadeOut(FilenameURL filenameURL, long milis) {
        if (!this.toStream) {
            this.errorMessage("Method 'fadeOut' may only be used for streaming and MIDI sources.");
        } else if (milis < 0L) {
            this.errorMessage("Miliseconds may not be negative in method 'fadeOut'.");
        } else {
            this.fadeOutMilis = milis;
            this.fadeInMilis = 0L;
            this.fadeOutGain = 1.0f;
            this.lastFadeCheck = System.currentTimeMillis();
            Object object = this.soundSequenceLock;
            synchronized (object) {
                if (this.soundSequenceQueue != null) {
                    this.soundSequenceQueue.clear();
                }
                if (filenameURL != null) {
                    if (this.soundSequenceQueue == null) {
                        this.soundSequenceQueue = new LinkedList();
                    }
                    this.soundSequenceQueue.add(filenameURL);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void fadeOutIn(FilenameURL filenameURL, long milisOut, long milisIn) {
        if (!this.toStream) {
            this.errorMessage("Method 'fadeOutIn' may only be used for streaming and MIDI sources.");
        } else if (filenameURL == null) {
            this.errorMessage("Filename/URL not specified in method 'fadeOutIn'.");
        } else if (milisOut >= 0L && milisIn >= 0L) {
            this.fadeOutMilis = milisOut;
            this.fadeInMilis = milisIn;
            this.fadeOutGain = 1.0f;
            this.lastFadeCheck = System.currentTimeMillis();
            Object object = this.soundSequenceLock;
            synchronized (object) {
                if (this.soundSequenceQueue == null) {
                    this.soundSequenceQueue = new LinkedList();
                }
                this.soundSequenceQueue.clear();
                this.soundSequenceQueue.add(filenameURL);
            }
        } else {
            this.errorMessage("Miliseconds may not be negative in method 'fadeOutIn'.");
        }
    }

    public boolean checkFadeOut() {
        if (!this.toStream) {
            return false;
        }
        if (this.fadeOutGain == -1.0f && this.fadeInGain == 1.0f) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        long milisPast = currentTime - this.lastFadeCheck;
        this.lastFadeCheck = currentTime;
        if (this.fadeOutGain >= 0.0f) {
            if (this.fadeOutMilis == 0L) {
                this.fadeOutGain = 0.0f;
                this.fadeInGain = 0.0f;
                if (!this.incrementSoundSequence()) {
                    this.stop();
                }
                this.positionChanged();
                this.preLoad = true;
                return false;
            }
            float fadeInIncrease = (float)milisPast / (float)this.fadeOutMilis;
            this.fadeOutGain -= fadeInIncrease;
            if (this.fadeOutGain <= 0.0f) {
                this.fadeOutGain = -1.0f;
                this.fadeInGain = 0.0f;
                if (!this.incrementSoundSequence()) {
                    this.stop();
                }
                this.positionChanged();
                this.preLoad = true;
                return false;
            }
            this.positionChanged();
            return true;
        }
        if (this.fadeInGain < 1.0f) {
            this.fadeOutGain = -1.0f;
            if (this.fadeInMilis == 0L) {
                this.fadeOutGain = -1.0f;
                this.fadeInGain = 1.0f;
            } else {
                float fadeInIncrease = (float)milisPast / (float)this.fadeInMilis;
                this.fadeInGain += fadeInIncrease;
                if (this.fadeInGain >= 1.0f) {
                    this.fadeOutGain = -1.0f;
                    this.fadeInGain = 1.0f;
                }
            }
            this.positionChanged();
            return true;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean incrementSoundSequence() {
        if (!this.toStream) {
            this.errorMessage("Method 'incrementSoundSequence' may only be used for streaming and MIDI sources.");
            return false;
        }
        Object object = this.soundSequenceLock;
        synchronized (object) {
            block6: {
                if (this.soundSequenceQueue == null || this.soundSequenceQueue.size() <= 0) break block6;
                this.filenameURL = this.soundSequenceQueue.remove(0);
                if (this.codec != null) {
                    this.codec.cleanup();
                }
                this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
                return true;
            }
            return false;
        }
    }

    public void setTemporary(boolean tmp) {
        this.temporary = tmp;
    }

    public void listenerMoved() {
    }

    public void setPosition(float x2, float y2, float z2) {
        this.position.x = x2;
        this.position.y = y2;
        this.position.z = z2;
    }

    public void positionChanged() {
    }

    public void setPriority(boolean pri) {
        this.priority = pri;
    }

    public void setLooping(boolean lp) {
        this.toLoop = lp;
    }

    public void setAttenuation(int model) {
        this.attModel = model;
    }

    public void setDistOrRoll(float dr) {
        this.distOrRoll = dr;
    }

    public float getDistanceFromListener() {
        return this.distanceFromListener;
    }

    public void setPitch(float value) {
        float newPitch = value;
        if (value < 0.5f) {
            newPitch = 0.5f;
        } else if (value > 2.0f) {
            newPitch = 2.0f;
        }
        this.pitch = newPitch;
    }

    public float getPitch() {
        return this.pitch;
    }

    public boolean reverseByteOrderRequired() {
        return this.reverseByteOrder;
    }

    public void changeSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x2, float y2, float z2, int attModel, float distOrRoll, boolean temporary) {
        this.priority = priority;
        this.toStream = toStream;
        this.toLoop = toLoop;
        this.sourcename = sourcename;
        this.filenameURL = filenameURL;
        this.soundBuffer = soundBuffer;
        this.position.x = x2;
        this.position.y = y2;
        this.position.z = z2;
        this.attModel = attModel;
        this.distOrRoll = distOrRoll;
        this.temporary = temporary;
    }

    public int feedRawAudioData(Channel c2, byte[] buffer) {
        if (!this.active(false, false)) {
            this.toPlay = true;
            return -1;
        }
        if (this.channel != c2) {
            this.channel = c2;
            this.channel.close();
            this.channel.setAudioFormat(this.rawDataFormat);
            this.positionChanged();
        }
        this.stopped(true, false);
        this.paused(true, false);
        if (this.channel == null) {
            return -1;
        }
        return this.channel.feedRawAudioData(buffer);
    }

    public void play(Channel c2) {
        if (!this.active(false, false)) {
            if (this.toLoop) {
                this.toPlay = true;
            }
        } else {
            if (this.channel != c2) {
                this.channel = c2;
                this.channel.close();
            }
            this.stopped(true, false);
            this.paused(true, false);
        }
    }

    public boolean stream() {
        if (this.channel == null) {
            return false;
        }
        if (this.preLoad) {
            if (!this.rawDataStream) {
                return this.preLoad();
            }
            this.preLoad = false;
        }
        if (this.rawDataStream) {
            if (this.stopped() || this.paused()) {
                return true;
            }
            if (this.channel.buffersProcessed() > 0) {
                this.channel.processBuffer();
            }
        } else {
            if (this.codec == null) {
                return false;
            }
            if (this.stopped()) {
                return false;
            }
            if (this.paused()) {
                return true;
            }
            int processed = this.channel.buffersProcessed();
            SoundBuffer buffer = null;
            int i2 = 0;
            while (i2 < processed) {
                buffer = this.codec.read();
                if (buffer != null) {
                    if (buffer.audioData != null) {
                        this.channel.queueBuffer(buffer.audioData);
                    }
                    buffer.cleanup();
                    buffer = null;
                }
                if (this.codec.endOfStream()) {
                    return false;
                }
                ++i2;
            }
        }
        return true;
    }

    public boolean preLoad() {
        if (this.channel == null) {
            return false;
        }
        if (this.codec == null) {
            return false;
        }
        URL url = this.filenameURL.getURL();
        this.codec.initialize(url);
        SoundBuffer buffer = null;
        int i2 = 0;
        while (i2 < SoundSystemConfig.getNumberStreamingBuffers()) {
            buffer = this.codec.read();
            if (buffer != null) {
                if (this.soundBuffer.audioData != null) {
                    this.channel.queueBuffer(this.soundBuffer.audioData);
                }
                buffer.cleanup();
                buffer = null;
            }
            ++i2;
        }
        return true;
    }

    public void pause() {
        this.toPlay = false;
        this.paused(true, true);
        if (this.channel != null) {
            this.channel.pause();
        } else {
            this.errorMessage("Channel null in method 'pause'");
        }
    }

    public void stop() {
        this.toPlay = false;
        this.stopped(true, true);
        this.paused(true, false);
        if (this.channel != null) {
            this.channel.stop();
        } else {
            this.errorMessage("Channel null in method 'stop'");
        }
    }

    public void rewind() {
        if (this.paused(false, false)) {
            this.stop();
        }
        if (this.channel != null) {
            boolean rePlay = this.playing();
            this.channel.rewind();
            if (this.toStream && rePlay) {
                this.stop();
                this.play(this.channel);
            }
        } else {
            this.errorMessage("Channel null in method 'rewind'");
        }
    }

    public void flush() {
        if (this.channel != null) {
            this.channel.flush();
        } else {
            this.errorMessage("Channel null in method 'flush'");
        }
    }

    public void cull() {
        if (this.active(false, false)) {
            if (this.playing() && this.toLoop) {
                this.toPlay = true;
            }
            if (this.rawDataStream) {
                this.toPlay = true;
            }
            this.active(true, false);
            if (this.channel != null) {
                this.channel.close();
            }
            this.channel = null;
        }
    }

    public void activate() {
        this.active(true, true);
    }

    public boolean active() {
        return this.active(false, false);
    }

    public boolean playing() {
        return this.channel != null && this.channel.attachedSource == this ? (!this.paused() && !this.stopped() ? this.channel.playing() : false) : false;
    }

    public boolean stopped() {
        return this.stopped(false, false);
    }

    public boolean paused() {
        return this.paused(false, false);
    }

    private synchronized boolean active(boolean action, boolean value) {
        if (action) {
            this.active = value;
        }
        return this.active;
    }

    private synchronized boolean stopped(boolean action, boolean value) {
        if (action) {
            this.stopped = value;
        }
        return this.stopped;
    }

    private synchronized boolean paused(boolean action, boolean value) {
        if (action) {
            this.paused = value;
        }
        return this.paused;
    }

    public String getClassName() {
        String libTitle = SoundSystemConfig.getLibraryTitle(this.libraryType);
        return libTitle.equals("No Sound") ? "Source" : "Source" + libTitle;
    }

    protected void message(String message) {
        this.logger.message(message, 0);
    }

    protected void importantMessage(String message) {
        this.logger.importantMessage(message, 0);
    }

    protected boolean errorCheck(boolean error, String message) {
        return this.logger.errorCheck(error, this.getClassName(), message, 0);
    }

    protected void errorMessage(String message) {
        this.logger.errorMessage(this.getClassName(), message, 0);
    }

    protected void printStackTrace(Exception e2) {
        this.logger.printStackTrace(e2, 1);
    }
}

