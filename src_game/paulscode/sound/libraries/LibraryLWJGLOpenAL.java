/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.openal.AL
 *  org.lwjgl.openal.AL10
 */
package paulscode.sound.libraries;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.sound.sampled.AudioFormat;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import paulscode.sound.Channel;
import paulscode.sound.FilenameURL;
import paulscode.sound.ICodec;
import paulscode.sound.Library;
import paulscode.sound.ListenerData;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.Source;
import paulscode.sound.libraries.ChannelLWJGLOpenAL;
import paulscode.sound.libraries.SourceLWJGLOpenAL;

public class LibraryLWJGLOpenAL
extends Library {
    private FloatBuffer listenerPositionAL = null;
    private FloatBuffer listenerOrientation = null;
    private FloatBuffer listenerVelocity = null;
    private HashMap<String, IntBuffer> ALBufferMap = new HashMap();
    private static boolean alPitchSupported = true;

    @Override
    public void init() throws SoundSystemException {
        boolean errors = false;
        try {
            AL.create();
            errors = this.checkALError();
        }
        catch (LWJGLException lWJGLException5) {
            this.errorMessage("Unable to initialize OpenAL.  Probable cause: OpenAL not supported.");
            this.printStackTrace((Exception)((Object)lWJGLException5));
            throw new SoundSystemException(lWJGLException5.getMessage(), 6);
        }
        if (errors) {
            this.importantMessage("OpenAL did not initialize properly!");
        } else {
            this.message("OpenAL initialized.");
        }
        this.listenerPositionAL = BufferUtils.createFloatBuffer((int)3).put(new float[]{this.listener.position.x, this.listener.position.y, this.listener.position.z});
        this.listenerOrientation = BufferUtils.createFloatBuffer((int)6).put(new float[]{this.listener.lookAt.x, this.listener.lookAt.y, this.listener.lookAt.z, this.listener.up.x, this.listener.up.y, this.listener.up.z});
        this.listenerVelocity = BufferUtils.createFloatBuffer((int)3).put(new float[]{0.0f, 0.0f, 0.0f});
        this.listenerPositionAL.flip();
        this.listenerOrientation.flip();
        this.listenerVelocity.flip();
        AL10.alListener((int)4100, (FloatBuffer)this.listenerPositionAL);
        errors = this.checkALError() || errors;
        AL10.alListener((int)4111, (FloatBuffer)this.listenerOrientation);
        errors = this.checkALError() || errors;
        AL10.alListener((int)4102, (FloatBuffer)this.listenerVelocity);
        boolean bl = errors = this.checkALError() || errors;
        if (errors) {
            this.importantMessage("OpenAL did not initialize properly!");
            throw new SoundSystemException("Problem encountered while loading OpenAL or creating the listener.  Probably cause:  OpenAL not supported", 6);
        }
        super.init();
        ChannelLWJGLOpenAL channel = (ChannelLWJGLOpenAL)this.normalChannels.get(1);
        try {
            AL10.alSourcef((int)channel.ALSource.get(0), (int)4099, (float)1.0f);
            if (this.checkALError()) {
                LibraryLWJGLOpenAL.alPitchSupported(true, false);
                throw new SoundSystemException("OpenAL: AL_PITCH not supported.", 13);
            }
            LibraryLWJGLOpenAL.alPitchSupported(true, true);
        }
        catch (Exception exception4) {
            LibraryLWJGLOpenAL.alPitchSupported(true, false);
            throw new SoundSystemException("OpenAL: AL_PITCH not supported.", 13);
        }
    }

    public static boolean libraryCompatible() {
        if (AL.isCreated()) {
            return true;
        }
        try {
            AL.create();
        }
        catch (Exception exception2) {
            return false;
        }
        try {
            AL.destroy();
        }
        catch (Exception exception) {
            // empty catch block
        }
        return true;
    }

    @Override
    protected Channel createChannel(int type) {
        IntBuffer ALSource = BufferUtils.createIntBuffer((int)1);
        try {
            AL10.alGenSources((IntBuffer)ALSource);
        }
        catch (Exception exception5) {
            AL10.alGetError();
            return null;
        }
        if (AL10.alGetError() != 0) {
            return null;
        }
        ChannelLWJGLOpenAL channel = new ChannelLWJGLOpenAL(type, ALSource);
        return channel;
    }

    @Override
    public void cleanup() {
        super.cleanup();
        Set keys = this.bufferMap.keySet();
        for (String filename : keys) {
            IntBuffer buffer = this.ALBufferMap.get(filename);
            if (buffer == null) continue;
            AL10.alDeleteBuffers((IntBuffer)buffer);
            this.checkALError();
            buffer.clear();
        }
        this.bufferMap.clear();
        AL.destroy();
        this.bufferMap = null;
        this.listenerPositionAL = null;
        this.listenerOrientation = null;
        this.listenerVelocity = null;
    }

    @Override
    public boolean loadSound(FilenameURL filenameURL) {
        int soundFormat1;
        if (this.bufferMap == null) {
            this.bufferMap = new HashMap();
            this.importantMessage("Buffer Map was null in method 'loadSound'");
        }
        if (this.ALBufferMap == null) {
            this.ALBufferMap = new HashMap();
            this.importantMessage("Open AL Buffer Map was null in method'loadSound'");
        }
        if (this.errorCheck(filenameURL == null, "Filename/URL not specified in method 'loadSound'")) {
            return false;
        }
        if (this.bufferMap.get(filenameURL.getFilename()) != null) {
            return true;
        }
        ICodec codec = SoundSystemConfig.getCodec(filenameURL.getFilename());
        if (this.errorCheck(codec == null, "No codec found for file '" + filenameURL.getFilename() + "' in method 'loadSound'")) {
            return false;
        }
        codec.initialize(filenameURL.getURL());
        SoundBuffer buffer = codec.readAll();
        codec.cleanup();
        codec = null;
        if (this.errorCheck(buffer == null, "Sound buffer null in method 'loadSound'")) {
            return false;
        }
        this.bufferMap.put(filenameURL.getFilename(), buffer);
        AudioFormat audioFormat = buffer.audioFormat;
        if (audioFormat.getChannels() == 1) {
            if (audioFormat.getSampleSizeInBits() == 8) {
                soundFormat1 = 4352;
            } else {
                if (audioFormat.getSampleSizeInBits() != 16) {
                    this.errorMessage("Illegal sample size in method 'loadSound'");
                    return false;
                }
                soundFormat1 = 4353;
            }
        } else {
            if (audioFormat.getChannels() != 2) {
                this.errorMessage("File neither mono nor stereo in method 'loadSound'");
                return false;
            }
            if (audioFormat.getSampleSizeInBits() == 8) {
                soundFormat1 = 4354;
            } else {
                if (audioFormat.getSampleSizeInBits() != 16) {
                    this.errorMessage("Illegal sample size in method 'loadSound'");
                    return false;
                }
                soundFormat1 = 4355;
            }
        }
        IntBuffer intBuffer = BufferUtils.createIntBuffer((int)1);
        AL10.alGenBuffers((IntBuffer)intBuffer);
        if (this.errorCheck(AL10.alGetError() != 0, "alGenBuffers error when loading " + filenameURL.getFilename())) {
            return false;
        }
        ByteBuffer data = BufferUtils.createByteBuffer((int)buffer.audioData.length);
        data.clear();
        data.put(buffer.audioData);
        data.flip();
        AL10.alBufferData((int)intBuffer.get(0), (int)soundFormat1, (ByteBuffer)data, (int)((int)audioFormat.getSampleRate()));
        if (this.errorCheck(AL10.alGetError() != 0, "alBufferData error when loading " + filenameURL.getFilename()) && this.errorCheck(intBuffer == null, "Sound buffer was not created for " + filenameURL.getFilename())) {
            return false;
        }
        this.ALBufferMap.put(filenameURL.getFilename(), intBuffer);
        return true;
    }

    @Override
    public void unloadSound(String filename) {
        this.ALBufferMap.remove(filename);
        super.unloadSound(filename);
    }

    @Override
    public void setMasterVolume(float value) {
        super.setMasterVolume(value);
        AL10.alListenerf((int)4106, (float)value);
        this.checkALError();
    }

    @Override
    public void newSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x2, float y2, float z2, int attModel, float distOrRoll) {
        IntBuffer myBuffer = null;
        if (!toStream) {
            myBuffer = this.ALBufferMap.get(filenameURL.getFilename());
            if (myBuffer == null && !this.loadSound(filenameURL)) {
                this.errorMessage("Source '" + sourcename + "' was not created " + "because an error occurred while loading " + filenameURL.getFilename());
                return;
            }
            myBuffer = this.ALBufferMap.get(filenameURL.getFilename());
            if (myBuffer == null) {
                this.errorMessage("Source '" + sourcename + "' was not created " + "because a sound buffer was not found for " + filenameURL.getFilename());
                return;
            }
        }
        SoundBuffer buffer = null;
        if (!toStream) {
            buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
            if (buffer == null && !this.loadSound(filenameURL)) {
                this.errorMessage("Source '" + sourcename + "' was not created " + "because an error occurred while loading " + filenameURL.getFilename());
                return;
            }
            buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
            if (buffer == null) {
                this.errorMessage("Source '" + sourcename + "' was not created " + "because audio data was not found for " + filenameURL.getFilename());
                return;
            }
        }
        this.sourceMap.put(sourcename, new SourceLWJGLOpenAL(this.listenerPositionAL, myBuffer, priority, toStream, toLoop, sourcename, filenameURL, buffer, x2, y2, z2, attModel, distOrRoll, false));
    }

    @Override
    public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float x2, float y2, float z2, int attModel, float distOrRoll) {
        this.sourceMap.put(sourcename, new SourceLWJGLOpenAL(this.listenerPositionAL, audioFormat, priority, sourcename, x2, y2, z2, attModel, distOrRoll));
    }

    @Override
    public void quickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x2, float y2, float z2, int attModel, float distOrRoll, boolean temporary) {
        IntBuffer myBuffer = null;
        if (!toStream) {
            myBuffer = this.ALBufferMap.get(filenameURL.getFilename());
            if (myBuffer == null) {
                this.loadSound(filenameURL);
            }
            if ((myBuffer = this.ALBufferMap.get(filenameURL.getFilename())) == null) {
                this.errorMessage("Sound buffer was not created for " + filenameURL.getFilename());
                return;
            }
        }
        SoundBuffer buffer = null;
        if (!toStream) {
            buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
            if (buffer == null && !this.loadSound(filenameURL)) {
                this.errorMessage("Source '" + sourcename + "' was not created " + "because an error occurred while loading " + filenameURL.getFilename());
                return;
            }
            buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
            if (buffer == null) {
                this.errorMessage("Source '" + sourcename + "' was not created " + "because audio data was not found for " + filenameURL.getFilename());
                return;
            }
        }
        SourceLWJGLOpenAL s2 = new SourceLWJGLOpenAL(this.listenerPositionAL, myBuffer, priority, toStream, toLoop, sourcename, filenameURL, buffer, x2, y2, z2, attModel, distOrRoll, false);
        this.sourceMap.put(sourcename, s2);
        this.play(s2);
        if (temporary) {
            s2.setTemporary(true);
        }
    }

    @Override
    public void copySources(HashMap<?, ?> srcMap) {
        if (srcMap != null) {
            Set<?> keys = srcMap.keySet();
            Iterator<?> iter = keys.iterator();
            if (this.bufferMap == null) {
                this.bufferMap = new HashMap();
                this.importantMessage("Buffer Map was null in method 'copySources'");
            }
            if (this.ALBufferMap == null) {
                this.ALBufferMap = new HashMap();
                this.importantMessage("Open AL Buffer Map was null in method'copySources'");
            }
            this.sourceMap.clear();
            while (true) {
                if (!iter.hasNext()) {
                    return;
                }
                String sourcename = (String)iter.next();
                Source source = (Source)srcMap.get(sourcename);
                if (source == null) continue;
                SoundBuffer buffer = null;
                if (!source.toStream) {
                    this.loadSound(source.filenameURL);
                    buffer = (SoundBuffer)this.bufferMap.get(source.filenameURL.getFilename());
                }
                if (!source.toStream && buffer == null) continue;
                this.sourceMap.put(sourcename, new SourceLWJGLOpenAL(this.listenerPositionAL, this.ALBufferMap.get(source.filenameURL.getFilename()), source, buffer));
            }
        }
    }

    @Override
    public void setListenerPosition(float x2, float y2, float z2) {
        super.setListenerPosition(x2, y2, z2);
        this.listenerPositionAL.put(0, x2);
        this.listenerPositionAL.put(1, y2);
        this.listenerPositionAL.put(2, z2);
        AL10.alListener((int)4100, (FloatBuffer)this.listenerPositionAL);
        this.checkALError();
    }

    @Override
    public void setListenerAngle(float angle) {
        super.setListenerAngle(angle);
        this.listenerOrientation.put(0, this.listener.lookAt.x);
        this.listenerOrientation.put(2, this.listener.lookAt.z);
        AL10.alListener((int)4111, (FloatBuffer)this.listenerOrientation);
        this.checkALError();
    }

    @Override
    public void setListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ) {
        super.setListenerOrientation(lookX, lookY, lookZ, upX, upY, upZ);
        this.listenerOrientation.put(0, lookX);
        this.listenerOrientation.put(1, lookY);
        this.listenerOrientation.put(2, lookZ);
        this.listenerOrientation.put(3, upX);
        this.listenerOrientation.put(4, upY);
        this.listenerOrientation.put(5, upZ);
        AL10.alListener((int)4111, (FloatBuffer)this.listenerOrientation);
        this.checkALError();
    }

    @Override
    public void setListenerData(ListenerData l2) {
        super.setListenerData(l2);
        this.listenerPositionAL.put(0, l2.position.x);
        this.listenerPositionAL.put(1, l2.position.y);
        this.listenerPositionAL.put(2, l2.position.z);
        AL10.alListener((int)4100, (FloatBuffer)this.listenerPositionAL);
        this.listenerOrientation.put(0, l2.lookAt.x);
        this.listenerOrientation.put(1, l2.lookAt.y);
        this.listenerOrientation.put(2, l2.lookAt.z);
        this.listenerOrientation.put(3, l2.up.x);
        this.listenerOrientation.put(4, l2.up.y);
        this.listenerOrientation.put(5, l2.up.z);
        AL10.alListener((int)4111, (FloatBuffer)this.listenerOrientation);
        this.checkALError();
    }

    private boolean checkALError() {
        switch (AL10.alGetError()) {
            case 0: {
                return false;
            }
            case 40961: {
                this.errorMessage("Invalid name parameter.");
                return true;
            }
            case 40962: {
                this.errorMessage("Invalid parameter.");
                return true;
            }
            case 40963: {
                this.errorMessage("Invalid enumerated parameter value.");
                return true;
            }
            case 40964: {
                this.errorMessage("Illegal call.");
                return true;
            }
            case 40965: {
                this.errorMessage("Unable to allocate memory.");
                return true;
            }
        }
        this.errorMessage("An unrecognized error occurred.");
        return true;
    }

    public static boolean alPitchSupported() {
        return LibraryLWJGLOpenAL.alPitchSupported(false, false);
    }

    private static synchronized boolean alPitchSupported(boolean action, boolean value) {
        if (action) {
            alPitchSupported = value;
        }
        return alPitchSupported;
    }

    public static String getTitle() {
        return "LWJGL OpenAL";
    }

    public static String getDescription() {
        return "The LWJGL binding of OpenAL.  For more information, see http://www.lwjgl.org";
    }

    @Override
    public String getClassName() {
        return "LibraryLWJGLOpenAL";
    }
}

