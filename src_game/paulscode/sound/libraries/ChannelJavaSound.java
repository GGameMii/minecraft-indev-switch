/*
 * Decompiled with CFR 0.152.
 */
package paulscode.sound.libraries;

import java.util.LinkedList;
import java.util.List;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import paulscode.sound.Channel;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemException;
import paulscode.sound.libraries.LibraryJavaSound;

public class ChannelJavaSound
extends Channel {
    public Clip clip = null;
    SoundBuffer soundBuffer;
    public SourceDataLine sourceDataLine = null;
    private List<SoundBuffer> streamBuffers;
    private int processed = 0;
    private Mixer myMixer = null;
    private AudioFormat myFormat = null;
    private FloatControl gainControl = null;
    private FloatControl panControl = null;
    private float initialGain = 0.0f;
    private boolean toLoop = false;
    float lastGain;

    public ChannelJavaSound(int type, Mixer mixer) {
        super(type);
        this.libraryType = LibraryJavaSound.class;
        this.myMixer = mixer;
        this.clip = null;
        this.sourceDataLine = null;
        this.streamBuffers = new LinkedList<SoundBuffer>();
    }

    @Override
    public void cleanup() {
        if (this.streamBuffers != null) {
            SoundBuffer buf = null;
            while (!this.streamBuffers.isEmpty()) {
                buf = this.streamBuffers.remove(0);
                buf.cleanup();
                buf = null;
            }
            this.streamBuffers.clear();
        }
        this.clip = null;
        this.soundBuffer = null;
        this.sourceDataLine = null;
        this.streamBuffers.clear();
        this.myMixer = null;
        this.myFormat = null;
        this.streamBuffers = null;
        super.cleanup();
    }

    public void newMixer(Mixer m2) {
        if (this.myMixer != m2) {
            try {
                if (this.clip != null) {
                    this.clip.close();
                } else if (this.sourceDataLine != null) {
                    this.sourceDataLine.close();
                }
            }
            catch (SecurityException securityException) {
                // empty catch block
            }
            this.myMixer = m2;
            if (this.channelType == 0) {
                this.attachBuffer(this.soundBuffer);
            } else {
                this.resetStream(this.myFormat);
            }
        }
    }

    public boolean attachBuffer(SoundBuffer buffer) {
        if (this.errorCheck(this.channelType != 0, "Buffers may only be attached to non-streaming sources")) {
            return false;
        }
        if (this.errorCheck(this.myMixer == null, "Mixer null in method 'attachBuffer'")) {
            return false;
        }
        if (this.errorCheck(buffer == null, "Buffer null in method 'attachBuffer'")) {
            return false;
        }
        if (this.errorCheck(buffer.audioData == null, "Buffer missing audio data in method 'attachBuffer'")) {
            return false;
        }
        if (this.errorCheck(buffer.audioFormat == null, "Buffer missing format information in method 'attachBuffer'")) {
            return false;
        }
        DataLine.Info lineInfo = new DataLine.Info(Clip.class, buffer.audioFormat);
        if (this.errorCheck(!AudioSystem.isLineSupported(lineInfo), "Line not supported in method 'attachBuffer'")) {
            return false;
        }
        Clip newClip = null;
        try {
            newClip = (Clip)this.myMixer.getLine(lineInfo);
        }
        catch (Exception exception6) {
            this.errorMessage("Unable to create clip in method 'attachBuffer'");
            this.printStackTrace(exception6);
            return false;
        }
        if (this.errorCheck(newClip == null, "New clip null in method 'attachBuffer'")) {
            return false;
        }
        if (this.clip != null) {
            this.clip.stop();
            this.clip.flush();
            this.clip.close();
        }
        this.clip = newClip;
        this.soundBuffer = buffer;
        this.myFormat = buffer.audioFormat;
        newClip = null;
        try {
            this.clip.open(this.myFormat, buffer.audioData, 0, buffer.audioData.length);
        }
        catch (Exception exception5) {
            this.errorMessage("Unable to attach buffer to clip in method 'attachBuffer'");
            this.printStackTrace(exception5);
            return false;
        }
        this.resetControls();
        this.gainControl.setValue(this.lastGain);
        return true;
    }

    @Override
    public void setAudioFormat(AudioFormat audioFormat) {
        this.resetStream(audioFormat);
        if (this.attachedSource != null && this.attachedSource.rawDataStream && this.attachedSource.active() && this.sourceDataLine != null) {
            this.sourceDataLine.start();
        }
    }

    public boolean resetStream(AudioFormat format) {
        if (this.errorCheck(this.myMixer == null, "Mixer null in method 'resetStream'")) {
            return false;
        }
        if (this.errorCheck(format == null, "AudioFormat null in method 'resetStream'")) {
            return false;
        }
        DataLine.Info lineInfo = new DataLine.Info(SourceDataLine.class, format);
        if (this.errorCheck(!AudioSystem.isLineSupported(lineInfo), "Line not supported in method 'resetStream'")) {
            return false;
        }
        SourceDataLine newSourceDataLine = null;
        try {
            newSourceDataLine = (SourceDataLine)this.myMixer.getLine(lineInfo);
        }
        catch (Exception exception6) {
            this.errorMessage("Unable to create a SourceDataLine in method 'resetStream'");
            this.printStackTrace(exception6);
            return false;
        }
        if (this.errorCheck(newSourceDataLine == null, "New SourceDataLine null in method 'resetStream'")) {
            return false;
        }
        this.streamBuffers.clear();
        this.processed = 0;
        if (this.sourceDataLine != null) {
            this.sourceDataLine.stop();
            this.sourceDataLine.flush();
            this.sourceDataLine.close();
        }
        this.sourceDataLine = newSourceDataLine;
        this.myFormat = format;
        newSourceDataLine = null;
        try {
            this.sourceDataLine.open(this.myFormat);
        }
        catch (Exception exception5) {
            this.errorMessage("Unable to open the new SourceDataLine in method 'resetStream'");
            this.printStackTrace(exception5);
            return false;
        }
        this.resetControls();
        return true;
    }

    private void resetControls() {
        switch (this.channelType) {
            case 0: {
                this.panControl = this.errorCheck(!this.clip.isControlSupported(FloatControl.Type.PAN), "Pan control not supported") ? null : (FloatControl)this.clip.getControl(FloatControl.Type.PAN);
                if (this.errorCheck(!this.clip.isControlSupported(FloatControl.Type.MASTER_GAIN), "Gain control not supported")) {
                    this.gainControl = null;
                    this.initialGain = 0.0f;
                    break;
                }
                this.gainControl = (FloatControl)this.clip.getControl(FloatControl.Type.MASTER_GAIN);
                this.initialGain = this.gainControl.getValue();
                this.gainControl.setValue(0.2f);
                break;
            }
            case 1: {
                this.panControl = this.errorCheck(!this.sourceDataLine.isControlSupported(FloatControl.Type.PAN), "Pan control not supported") ? null : (FloatControl)this.sourceDataLine.getControl(FloatControl.Type.PAN);
                if (this.errorCheck(!this.sourceDataLine.isControlSupported(FloatControl.Type.MASTER_GAIN), "Gain control not supported")) {
                    this.gainControl = null;
                    this.initialGain = 0.0f;
                    break;
                }
                this.gainControl = (FloatControl)this.sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
                this.initialGain = this.gainControl.getValue();
                break;
            }
            default: {
                this.errorMessage("Unrecognized channel type in method 'resetControls'");
                this.panControl = null;
                this.gainControl = null;
            }
        }
        if (this.gainControl == null) {
            SoundSystem.setException(new SoundSystemException("Gain control not supported on the selected Mixer", 15));
        } else if (this.panControl == null) {
            SoundSystem.setException(new SoundSystemException("Pan control not supported on the selected Mixer", 16));
        }
    }

    public void setLooping(boolean value) {
        this.toLoop = value;
    }

    public void setPan(float p2) {
        if (this.panControl != null) {
            float pan = p2;
            if (p2 < -1.0f) {
                pan = -1.0f;
            }
            if (pan > 1.0f) {
                pan = 1.0f;
            }
            this.panControl.setValue(pan);
        }
    }

    public void setGain(float g2) {
        if (this.gainControl != null) {
            float valueDB;
            float gain = g2;
            if (g2 < 0.0f) {
                gain = 0.0f;
            }
            if (gain > 1.0f) {
                gain = 1.0f;
            }
            double minimumDB = this.gainControl.getMinimum();
            double maximumDB = this.initialGain;
            double ampGainDB = 0.5 * maximumDB - minimumDB;
            double cste = Math.log(10.0) / 20.0;
            this.lastGain = valueDB = (float)(minimumDB + 1.0 / cste * Math.log(1.0 + (Math.exp(cste * ampGainDB) - 1.0) * (double)gain));
            this.gainControl.setValue(valueDB);
        }
    }

    public void setPitch(float p2) {
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public boolean preLoadBuffers(LinkedList<byte[]> bufferList) {
        if (this.errorCheck(this.channelType != 1, "Buffers may only be queued for streaming sources.")) {
            return false;
        }
        if (this.errorCheck(this.sourceDataLine == null, "SourceDataLine null in method 'preLoadBuffers'.")) {
            return false;
        }
        this.sourceDataLine.start();
        if (bufferList.isEmpty()) {
            return true;
        }
        preLoad = bufferList.remove(0);
        if (!this.errorCheck(preLoad == null, "Missing sound-bytes in method 'preLoadBuffers'.")) ** GOTO lbl13
        return false;
lbl-1000:
        // 1 sources

        {
            this.streamBuffers.add(new SoundBuffer(bufferList.remove(0), this.myFormat));
lbl13:
            // 2 sources

            ** while (!bufferList.isEmpty())
        }
lbl14:
        // 1 sources

        this.sourceDataLine.write(preLoad, 0, preLoad.length);
        this.processed = 0;
        return true;
    }

    @Override
    public boolean queueBuffer(byte[] buffer) {
        if (this.errorCheck(this.channelType != 1, "Buffers may only be queued for streaming sources.")) {
            return false;
        }
        if (this.errorCheck(this.sourceDataLine == null, "SourceDataLine null in method 'queueBuffer'.")) {
            return false;
        }
        if (this.errorCheck(this.myFormat == null, "AudioFormat null in method 'queueBuffer'")) {
            return false;
        }
        this.streamBuffers.add(new SoundBuffer(buffer, this.myFormat));
        this.processBuffer();
        this.processed = 0;
        return true;
    }

    @Override
    public boolean processBuffer() {
        if (this.errorCheck(this.channelType != 1, "Buffers are only processed for streaming sources.")) {
            return false;
        }
        if (this.errorCheck(this.sourceDataLine == null, "SourceDataLine null in method 'processBuffer'.")) {
            return false;
        }
        if (this.streamBuffers != null && !this.streamBuffers.isEmpty()) {
            SoundBuffer nextBuffer = this.streamBuffers.remove(0);
            this.sourceDataLine.write(nextBuffer.audioData, 0, nextBuffer.audioData.length);
            if (!this.sourceDataLine.isActive()) {
                this.sourceDataLine.start();
            }
            nextBuffer.cleanup();
            nextBuffer = null;
            return true;
        }
        return false;
    }

    @Override
    public int feedRawAudioData(byte[] buffer) {
        if (this.errorCheck(this.channelType != 1, "Raw audio data can only be processed by streaming sources.")) {
            return -1;
        }
        if (this.errorCheck(this.streamBuffers == null, "StreamBuffers queue null in method 'feedRawAudioData'.")) {
            return -1;
        }
        this.streamBuffers.add(new SoundBuffer(buffer, this.myFormat));
        return this.buffersProcessed();
    }

    @Override
    public int buffersProcessed() {
        this.processed = 0;
        if (this.errorCheck(this.channelType != 1, "Buffers may only be queued for streaming sources.")) {
            if (this.streamBuffers != null) {
                this.streamBuffers.clear();
            }
            return 0;
        }
        if (this.sourceDataLine == null) {
            if (this.streamBuffers != null) {
                this.streamBuffers.clear();
            }
            return 0;
        }
        if (this.sourceDataLine.available() > 0) {
            this.processed = 1;
        }
        return this.processed;
    }

    @Override
    public void flush() {
        if (this.channelType == 1 && !this.errorCheck(this.sourceDataLine == null, "SourceDataLine null in method 'flush'.")) {
            this.sourceDataLine.stop();
            this.sourceDataLine.flush();
            this.sourceDataLine.drain();
            this.streamBuffers.clear();
            this.processed = 0;
        }
    }

    @Override
    public void close() {
        switch (this.channelType) {
            case 0: {
                if (this.clip == null) break;
                this.clip.stop();
                this.clip.flush();
                this.clip.close();
                break;
            }
            case 1: {
                if (this.sourceDataLine == null) break;
                this.flush();
                this.sourceDataLine.close();
            }
        }
    }

    @Override
    public void play() {
        switch (this.channelType) {
            case 0: {
                if (this.clip == null) break;
                if (this.toLoop) {
                    this.clip.stop();
                    this.gainControl.setValue(this.lastGain);
                    this.clip.loop(-1);
                    this.gainControl.setValue(this.lastGain);
                    break;
                }
                this.clip.stop();
                this.gainControl.setValue(this.lastGain);
                this.clip.start();
                this.gainControl.setValue(this.lastGain);
                break;
            }
            case 1: {
                if (this.sourceDataLine == null) break;
                this.sourceDataLine.start();
            }
        }
    }

    @Override
    public void pause() {
        switch (this.channelType) {
            case 0: {
                if (this.clip == null) break;
                this.clip.stop();
                break;
            }
            case 1: {
                if (this.sourceDataLine == null) break;
                this.sourceDataLine.stop();
            }
        }
    }

    @Override
    public void stop() {
        switch (this.channelType) {
            case 0: {
                if (this.clip == null) break;
                this.clip.stop();
                this.clip.setFramePosition(0);
                break;
            }
            case 1: {
                if (this.sourceDataLine == null) break;
                this.sourceDataLine.stop();
            }
        }
    }

    @Override
    public void rewind() {
        switch (this.channelType) {
            case 0: {
                if (this.clip == null) break;
                boolean rePlay = this.clip.isRunning();
                this.clip.stop();
                this.clip.setFramePosition(0);
                if (!rePlay) break;
                if (this.toLoop) {
                    this.clip.loop(-1);
                    break;
                }
                this.clip.start();
            }
        }
    }

    @Override
    public boolean playing() {
        switch (this.channelType) {
            case 0: {
                if (this.clip == null) {
                    return false;
                }
                return this.clip.isActive();
            }
            case 1: {
                if (this.sourceDataLine == null) {
                    return false;
                }
                return this.sourceDataLine.isActive();
            }
        }
        return false;
    }
}

