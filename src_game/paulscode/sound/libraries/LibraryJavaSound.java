/*
 * Decompiled with CFR 0.152.
 */
package paulscode.sound.libraries;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import paulscode.sound.Channel;
import paulscode.sound.FilenameURL;
import paulscode.sound.ICodec;
import paulscode.sound.Library;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.Source;
import paulscode.sound.libraries.ChannelJavaSound;
import paulscode.sound.libraries.SourceJavaSound;

public class LibraryJavaSound
extends Library {
    private static Mixer myMixer = null;
    private static LibraryJavaSound instance = null;

    public LibraryJavaSound() throws SoundSystemException {
        instance = this;
    }

    @Override
    public void init() throws SoundSystemException {
        if (myMixer == null) {
            myMixer = AudioSystem.getMixer(null);
        }
        if (myMixer == null) {
            this.importantMessage("\"Java Sound Audio Engine\" was not found!");
            super.init();
            throw new SoundSystemException("\"Java Sound Audio Engine\" was not found in the list of available mixers.", 14);
        }
        this.setMasterVolume(1.0f);
        this.message("JavaSound initialized.");
        super.init();
    }

    public static boolean libraryCompatible() {
        Mixer.Info[] mixer$Info3 = AudioSystem.getMixerInfo();
        int i2 = mixer$Info3.length;
        int i1 = 0;
        while (i1 < i2) {
            Mixer.Info mixerInfo = mixer$Info3[i1];
            if (mixerInfo.getName().equals("Java Sound Audio Engine")) {
                return true;
            }
            ++i1;
        }
        return false;
    }

    @Override
    protected Channel createChannel(int type) {
        return new ChannelJavaSound(type, myMixer);
    }

    @Override
    public void cleanup() {
        super.cleanup();
        instance = null;
        myMixer = null;
    }

    @Override
    public boolean loadSound(FilenameURL filenameURL) {
        if (this.bufferMap == null) {
            this.bufferMap = new HashMap();
            this.importantMessage("Buffer Map was null in method 'loadSound'");
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
        URL url = filenameURL.getURL();
        if (this.errorCheck(url == null, "Unable to open file '" + filenameURL.getFilename() + "' in method 'loadSound'")) {
            return false;
        }
        codec.initialize(url);
        SoundBuffer buffer = codec.readAll();
        codec.cleanup();
        codec = null;
        if (buffer != null) {
            this.bufferMap.put(filenameURL.getFilename(), buffer);
        } else {
            this.errorMessage("Sound buffer null in method 'loadSound'");
        }
        return true;
    }

    @Override
    public void setMasterVolume(float value) {
        super.setMasterVolume(value);
        Set keys = this.sourceMap.keySet();
        for (String sourcename : keys) {
            Source source = (Source)this.sourceMap.get(sourcename);
            if (source == null) continue;
            source.positionChanged();
        }
    }

    @Override
    public void newSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x2, float y2, float z2, int attModel, float distOrRoll) {
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
        if (!toStream && buffer != null) {
            buffer.trimData(0x100000);
        }
        this.sourceMap.put(sourcename, new SourceJavaSound(this.listener, priority, toStream, toLoop, sourcename, filenameURL, buffer, x2, y2, z2, attModel, distOrRoll, false));
    }

    @Override
    public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float x2, float y2, float z2, int attModel, float distOrRoll) {
        this.sourceMap.put(sourcename, new SourceJavaSound(this.listener, audioFormat, priority, sourcename, x2, y2, z2, attModel, distOrRoll));
    }

    @Override
    public void quickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x2, float y2, float z2, int attModel, float distOrRoll, boolean temporary) {
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
        if (!toStream && buffer != null) {
            buffer.trimData(0x100000);
        }
        this.sourceMap.put(sourcename, new SourceJavaSound(this.listener, priority, toStream, toLoop, sourcename, filenameURL, buffer, x2, y2, z2, attModel, distOrRoll, temporary));
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
                if (!source.toStream && buffer != null) {
                    buffer.trimData(0x100000);
                }
                if (!source.toStream && buffer == null) continue;
                this.sourceMap.put(sourcename, new SourceJavaSound(this.listener, source, buffer));
            }
        }
    }

    public static Mixer getMixer() {
        return LibraryJavaSound.mixer(false, null);
    }

    public static void setMixer(Mixer m2) throws SoundSystemException {
        LibraryJavaSound.mixer(true, m2);
        SoundSystemException e2 = SoundSystem.getLastException();
        SoundSystem.setException(null);
        if (e2 != null) {
            throw e2;
        }
    }

    private static synchronized Mixer mixer(boolean action, Mixer m2) {
        if (action) {
            myMixer = m2;
            if (instance != null) {
                ChannelJavaSound c2;
                ListIterator itr = LibraryJavaSound.instance.normalChannels.listIterator();
                SoundSystem.setException(null);
                while (itr.hasNext()) {
                    c2 = (ChannelJavaSound)itr.next();
                    c2.newMixer(m2);
                }
                itr = LibraryJavaSound.instance.streamingChannels.listIterator();
                while (itr.hasNext()) {
                    c2 = (ChannelJavaSound)itr.next();
                    c2.newMixer(m2);
                }
            }
        }
        return myMixer;
    }

    public static String getTitle() {
        return "Java Sound";
    }

    public static String getDescription() {
        return "The Java Sound API.  For more information, see http://java.sun.com/products/java-media/sound/";
    }

    @Override
    public String getClassName() {
        return "LibraryJavaSound";
    }
}

