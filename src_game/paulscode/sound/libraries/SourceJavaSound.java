/*
 * Decompiled with CFR 0.152.
 */
package paulscode.sound.libraries;

import java.util.LinkedList;
import javax.sound.sampled.AudioFormat;
import paulscode.sound.Channel;
import paulscode.sound.FilenameURL;
import paulscode.sound.ListenerData;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.Source;
import paulscode.sound.Vector3D;
import paulscode.sound.libraries.ChannelJavaSound;
import paulscode.sound.libraries.LibraryJavaSound;

public class SourceJavaSound
extends Source {
    protected ChannelJavaSound channelJavaSound;
    public ListenerData listener;
    private float pan;

    public SourceJavaSound(ListenerData listener, boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x2, float y2, float z2, int attModel, float distOrRoll, boolean temporary) {
        super(priority, toStream, toLoop, sourcename, filenameURL, soundBuffer, x2, y2, z2, attModel, distOrRoll, temporary);
        this.channelJavaSound = (ChannelJavaSound)this.channel;
        this.pan = 0.0f;
        this.libraryType = LibraryJavaSound.class;
        this.listener = listener;
        this.positionChanged();
    }

    public SourceJavaSound(ListenerData listener, Source old, SoundBuffer soundBuffer) {
        super(old, soundBuffer);
        this.channelJavaSound = (ChannelJavaSound)this.channel;
        this.pan = 0.0f;
        this.libraryType = LibraryJavaSound.class;
        this.listener = listener;
        this.positionChanged();
    }

    public SourceJavaSound(ListenerData listener, AudioFormat audioFormat, boolean priority, String sourcename, float x2, float y2, float z2, int attModel, float distOrRoll) {
        super(audioFormat, priority, sourcename, x2, y2, z2, attModel, distOrRoll);
        this.channelJavaSound = (ChannelJavaSound)this.channel;
        this.pan = 0.0f;
        this.libraryType = LibraryJavaSound.class;
        this.listener = listener;
        this.positionChanged();
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }

    @Override
    public void changeSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x2, float y2, float z2, int attModel, float distOrRoll, boolean temporary) {
        super.changeSource(priority, toStream, toLoop, sourcename, filenameURL, soundBuffer, x2, y2, z2, attModel, distOrRoll, temporary);
        if (this.channelJavaSound != null) {
            this.channelJavaSound.setLooping(toLoop);
        }
        this.positionChanged();
    }

    @Override
    public void listenerMoved() {
        this.positionChanged();
    }

    @Override
    public void setPosition(float x2, float y2, float z2) {
        super.setPosition(x2, y2, z2);
        this.positionChanged();
    }

    @Override
    public void positionChanged() {
        this.calculateGain();
        this.calculatePan();
        this.calculatePitch();
    }

    @Override
    public void setPitch(float value) {
        super.setPitch(value);
        this.calculatePitch();
    }

    @Override
    public void setAttenuation(int model) {
        super.setAttenuation(model);
        this.calculateGain();
    }

    @Override
    public void setDistOrRoll(float dr) {
        super.setDistOrRoll(dr);
        this.calculateGain();
    }

    @Override
    public void play(Channel c2) {
        if (!this.active()) {
            if (this.toLoop) {
                this.toPlay = true;
            }
        } else if (c2 == null) {
            this.errorMessage("Unable to play source, because channel was null");
        } else {
            boolean newChannel;
            boolean bl = newChannel = this.channel != c2;
            if (this.channel != null && this.channel.attachedSource != this) {
                newChannel = true;
            }
            boolean wasPaused = this.paused();
            boolean wasStopped = this.stopped();
            super.play(c2);
            this.channelJavaSound = (ChannelJavaSound)this.channel;
            if (newChannel) {
                if (this.channelJavaSound != null) {
                    this.channelJavaSound.setLooping(this.toLoop);
                }
                if (!this.toStream) {
                    if (this.soundBuffer == null) {
                        this.errorMessage("No sound buffer to play");
                        return;
                    }
                    this.channelJavaSound.attachBuffer(this.soundBuffer);
                }
            }
            this.positionChanged();
            if (wasStopped || !this.playing()) {
                if (this.toStream && !wasPaused) {
                    this.preLoad = true;
                }
                this.channel.play();
            }
        }
    }

    @Override
    public boolean preLoad() {
        if (this.codec == null) {
            return false;
        }
        this.codec.initialize(this.filenameURL.getURL());
        LinkedList<byte[]> preLoadBuffers = new LinkedList<byte[]>();
        int i2 = 0;
        while (i2 < SoundSystemConfig.getNumberStreamingBuffers()) {
            this.soundBuffer = this.codec.read();
            if (this.soundBuffer == null || this.soundBuffer.audioData == null) break;
            preLoadBuffers.add(this.soundBuffer.audioData);
            ++i2;
        }
        this.channelJavaSound.resetStream(this.codec.getAudioFormat());
        this.positionChanged();
        this.channel.preLoadBuffers(preLoadBuffers);
        this.preLoad = false;
        return true;
    }

    public void calculateGain() {
        float distX = this.position.x - this.listener.position.x;
        float distY = this.position.y - this.listener.position.y;
        float distZ = this.position.z - this.listener.position.z;
        this.distanceFromListener = (float)Math.sqrt(distX * distX + distY * distY + distZ * distZ);
        switch (this.attModel) {
            case 1: {
                if (this.distanceFromListener <= 0.0f) {
                    this.gain = 1.0f;
                    break;
                }
                float tweakFactor = 5.0E-4f;
                float attenuationFactor = this.distOrRoll * this.distanceFromListener * this.distanceFromListener * tweakFactor;
                if (attenuationFactor < 0.0f) {
                    attenuationFactor = 0.0f;
                }
                this.gain = 1.0f / (1.0f + attenuationFactor);
                break;
            }
            case 2: {
                if (this.distanceFromListener <= 0.0f) {
                    this.gain = 1.0f;
                    break;
                }
                if (this.distanceFromListener >= this.distOrRoll) {
                    this.gain = 0.0f;
                    break;
                }
                this.gain = 1.0f - this.distanceFromListener / this.distOrRoll;
                break;
            }
            default: {
                this.gain = 1.0f;
            }
        }
        if (this.gain > 1.0f) {
            this.gain = 1.0f;
        }
        if (this.gain < 0.0f) {
            this.gain = 0.0f;
        }
        this.gain *= this.sourceVolume * SoundSystemConfig.getMasterGain() * Math.abs(this.fadeOutGain) * this.fadeInGain;
        if (this.channel != null && this.channel.attachedSource == this && this.channelJavaSound != null) {
            this.channelJavaSound.setGain(this.gain);
        }
    }

    public void calculatePan() {
        Vector3D side = this.listener.up.cross(this.listener.lookAt);
        side.normalize();
        float x2 = this.position.dot(this.position.subtract(this.listener.position), side);
        float z2 = this.position.dot(this.position.subtract(this.listener.position), this.listener.lookAt);
        side = null;
        float angle = (float)Math.atan2(x2, z2);
        this.pan = (float)(-Math.sin(angle));
        if (this.channel != null && this.channel.attachedSource == this && this.channelJavaSound != null) {
            if (this.attModel == 0) {
                this.channelJavaSound.setPan(0.0f);
            } else {
                this.channelJavaSound.setPan(this.pan);
            }
        }
    }

    public void calculatePitch() {
        if (this.channel != null && this.channel.attachedSource == this && this.channelJavaSound != null) {
            this.channelJavaSound.setPitch(this.pitch);
        }
    }
}

