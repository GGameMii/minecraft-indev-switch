/*
 * Decompiled with CFR 0.152.
 */
package paulscode.sound;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import paulscode.sound.FilenameURL;
import paulscode.sound.SimpleThread;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.SoundSystemLogger;

public class MidiChannel
implements MetaEventListener {
    private SoundSystemLogger logger;
    private FilenameURL filenameURL;
    private String sourcename;
    private Sequencer sequencer = null;
    private Synthesizer synthesizer = null;
    private MidiDevice synthDevice = null;
    private Sequence sequence = null;
    private boolean toLoop = true;
    private float gain = 1.0f;
    private boolean loading = true;
    private LinkedList<FilenameURL> sequenceQueue = null;
    private final Object sequenceQueueLock = new Object();
    protected float fadeOutGain = -1.0f;
    protected float fadeInGain = 1.0f;
    protected long fadeOutMilis = 0L;
    protected long fadeInMilis = 0L;
    protected long lastFadeCheck = 0L;
    private FadeThread fadeThread = null;

    public MidiChannel(boolean toLoop, String sourcename, String filename) {
        this.loading(true, true);
        this.logger = SoundSystemConfig.getLogger();
        this.filenameURL(true, new FilenameURL(filename));
        this.sourcename(true, sourcename);
        this.setLooping(toLoop);
        this.init();
        this.loading(true, false);
    }

    public MidiChannel(boolean toLoop, String sourcename, URL midiFile, String identifier) {
        this.loading(true, true);
        this.logger = SoundSystemConfig.getLogger();
        this.filenameURL(true, new FilenameURL(midiFile, identifier));
        this.sourcename(true, sourcename);
        this.setLooping(toLoop);
        this.init();
        this.loading(true, false);
    }

    public MidiChannel(boolean toLoop, String sourcename, FilenameURL midiFilenameURL) {
        this.loading(true, true);
        this.logger = SoundSystemConfig.getLogger();
        this.filenameURL(true, midiFilenameURL);
        this.sourcename(true, sourcename);
        this.setLooping(toLoop);
        this.init();
        this.loading(true, false);
    }

    private void init() {
        this.getSequencer();
        this.setSequence(this.filenameURL(false, null).getURL());
        this.getSynthesizer();
        this.resetGain();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void cleanup() {
        this.loading(true, true);
        this.setLooping(true);
        if (this.sequencer != null) {
            try {
                this.sequencer.stop();
                this.sequencer.close();
                this.sequencer.removeMetaEventListener(this);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.logger = null;
        this.sequencer = null;
        this.synthesizer = null;
        this.sequence = null;
        Object object = this.sequenceQueueLock;
        synchronized (object) {
            if (this.sequenceQueue != null) {
                this.sequenceQueue.clear();
            }
            this.sequenceQueue = null;
        }
        if (this.fadeThread != null) {
            boolean z8 = false;
            try {
                this.fadeThread.kill();
                this.fadeThread.interrupt();
            }
            catch (Exception exception5) {
                z8 = true;
            }
            if (!z8) {
                int i2 = 0;
                while (i2 < 50 && this.fadeThread.alive()) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException interruptedException) {
                        // empty catch block
                    }
                    ++i2;
                }
            }
            if (z8 || this.fadeThread.alive()) {
                this.errorMessage("MIDI fade effects thread did not die!");
                this.message("Ignoring errors... continuing clean-up.");
            }
        }
        this.fadeThread = null;
        this.loading(true, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void queueSound(FilenameURL filenameURL) {
        if (filenameURL == null) {
            this.errorMessage("Filename/URL not specified in method 'queueSound'");
        } else {
            Object object = this.sequenceQueueLock;
            synchronized (object) {
                if (this.sequenceQueue == null) {
                    this.sequenceQueue = new LinkedList();
                }
                this.sequenceQueue.add(filenameURL);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void dequeueSound(String filename) {
        if (filename != null && !filename.equals("")) {
            Object object = this.sequenceQueueLock;
            synchronized (object) {
                if (this.sequenceQueue != null) {
                    ListIterator i2 = this.sequenceQueue.listIterator();
                    while (i2.hasNext()) {
                        if (!((FilenameURL)i2.next()).getFilename().equals(filename)) continue;
                        i2.remove();
                        break;
                    }
                }
            }
        } else {
            this.errorMessage("Filename not specified in method 'dequeueSound'");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void fadeOut(FilenameURL filenameURL, long milis) {
        if (milis < 0L) {
            this.errorMessage("Miliseconds may not be negative in method 'fadeOut'.");
        } else {
            this.fadeOutMilis = milis;
            this.fadeInMilis = 0L;
            this.fadeOutGain = 1.0f;
            this.lastFadeCheck = System.currentTimeMillis();
            Object object = this.sequenceQueueLock;
            synchronized (object) {
                if (this.sequenceQueue != null) {
                    this.sequenceQueue.clear();
                }
                if (filenameURL != null) {
                    if (this.sequenceQueue == null) {
                        this.sequenceQueue = new LinkedList();
                    }
                    this.sequenceQueue.add(filenameURL);
                }
            }
            if (this.fadeThread == null) {
                this.fadeThread = new FadeThread(null);
                this.fadeThread.start();
            }
            this.fadeThread.interrupt();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void fadeOutIn(FilenameURL filenameURL, long milisOut, long milisIn) {
        if (filenameURL == null) {
            this.errorMessage("Filename/URL not specified in method 'fadeOutIn'.");
        } else if (milisOut >= 0L && milisIn >= 0L) {
            this.fadeOutMilis = milisOut;
            this.fadeInMilis = milisIn;
            this.fadeOutGain = 1.0f;
            this.lastFadeCheck = System.currentTimeMillis();
            Object object = this.sequenceQueueLock;
            synchronized (object) {
                if (this.sequenceQueue == null) {
                    this.sequenceQueue = new LinkedList();
                }
                this.sequenceQueue.clear();
                this.sequenceQueue.add(filenameURL);
            }
            if (this.fadeThread == null) {
                this.fadeThread = new FadeThread(null);
                this.fadeThread.start();
            }
            this.fadeThread.interrupt();
        } else {
            this.errorMessage("Miliseconds may not be negative in method 'fadeOutIn'.");
        }
    }

    private synchronized boolean checkFadeOut() {
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
                if (!this.incrementSequence()) {
                    this.stop();
                }
                this.rewind();
                this.resetGain();
                return false;
            }
            float fadeInIncrease = (float)milisPast / (float)this.fadeOutMilis;
            this.fadeOutGain -= fadeInIncrease;
            if (this.fadeOutGain <= 0.0f) {
                this.fadeOutGain = -1.0f;
                this.fadeInGain = 0.0f;
                if (!this.incrementSequence()) {
                    this.stop();
                }
                this.rewind();
                this.resetGain();
                return false;
            }
            this.resetGain();
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
            this.resetGain();
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean incrementSequence() {
        Object object = this.sequenceQueueLock;
        synchronized (object) {
            block9: {
                block10: {
                    if (this.sequenceQueue == null || this.sequenceQueue.size() <= 0) break block9;
                    this.filenameURL(true, this.sequenceQueue.remove(0));
                    this.loading(true, true);
                    if (this.sequencer == null) {
                        this.getSequencer();
                    } else {
                        this.sequencer.stop();
                        this.sequencer.setMicrosecondPosition(0L);
                        this.sequencer.removeMetaEventListener(this);
                        try {
                            Thread.sleep(100L);
                        }
                        catch (InterruptedException interruptedException) {
                            // empty catch block
                        }
                    }
                    if (this.sequencer != null) break block10;
                    this.errorMessage("Unable to set the sequence in method 'incrementSequence', because there wasn't a sequencer to use.");
                    this.loading(true, false);
                    return false;
                }
                this.setSequence(this.filenameURL(false, null).getURL());
                this.sequencer.start();
                this.resetGain();
                this.sequencer.addMetaEventListener(this);
                this.loading(true, false);
                return true;
            }
            return false;
        }
    }

    public void play() {
        if (!this.loading()) {
            if (this.sequencer == null) {
                return;
            }
            try {
                this.sequencer.start();
                this.sequencer.addMetaEventListener(this);
            }
            catch (Exception exception3) {
                this.errorMessage("Exception in method 'play'");
                this.printStackTrace(exception3);
                SoundSystemException sse = new SoundSystemException(exception3.getMessage());
                SoundSystem.setException(sse);
            }
        }
    }

    public void stop() {
        if (!this.loading()) {
            if (this.sequencer == null) {
                return;
            }
            try {
                this.sequencer.stop();
                this.sequencer.setMicrosecondPosition(0L);
                this.sequencer.removeMetaEventListener(this);
            }
            catch (Exception exception3) {
                this.errorMessage("Exception in method 'stop'");
                this.printStackTrace(exception3);
                SoundSystemException sse = new SoundSystemException(exception3.getMessage());
                SoundSystem.setException(sse);
            }
        }
    }

    public void pause() {
        if (!this.loading()) {
            if (this.sequencer == null) {
                return;
            }
            try {
                this.sequencer.stop();
            }
            catch (Exception exception3) {
                this.errorMessage("Exception in method 'pause'");
                this.printStackTrace(exception3);
                SoundSystemException sse = new SoundSystemException(exception3.getMessage());
                SoundSystem.setException(sse);
            }
        }
    }

    public void rewind() {
        if (!this.loading()) {
            if (this.sequencer == null) {
                return;
            }
            try {
                this.sequencer.setMicrosecondPosition(0L);
            }
            catch (Exception exception3) {
                this.errorMessage("Exception in method 'rewind'");
                this.printStackTrace(exception3);
                SoundSystemException sse = new SoundSystemException(exception3.getMessage());
                SoundSystem.setException(sse);
            }
        }
    }

    public void setVolume(float value) {
        this.gain = value;
        this.resetGain();
    }

    public float getVolume() {
        return this.gain;
    }

    public void switchSource(boolean toLoop, String sourcename, String filename) {
        this.loading(true, true);
        this.filenameURL(true, new FilenameURL(filename));
        this.sourcename(true, sourcename);
        this.setLooping(toLoop);
        this.reset();
        this.loading(true, false);
    }

    public void switchSource(boolean toLoop, String sourcename, URL midiFile, String identifier) {
        this.loading(true, true);
        this.filenameURL(true, new FilenameURL(midiFile, identifier));
        this.sourcename(true, sourcename);
        this.setLooping(toLoop);
        this.reset();
        this.loading(true, false);
    }

    public void switchSource(boolean toLoop, String sourcename, FilenameURL filenameURL) {
        this.loading(true, true);
        this.filenameURL(true, filenameURL);
        this.sourcename(true, sourcename);
        this.setLooping(toLoop);
        this.reset();
        this.loading(true, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void reset() {
        Object object = this.sequenceQueueLock;
        synchronized (object) {
            if (this.sequenceQueue != null) {
                this.sequenceQueue.clear();
            }
        }
        if (this.sequencer == null) {
            this.getSequencer();
        } else {
            this.sequencer.stop();
            this.sequencer.setMicrosecondPosition(0L);
            this.sequencer.removeMetaEventListener(this);
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        }
        if (this.sequencer == null) {
            this.errorMessage("Unable to set the sequence in method 'reset', because there wasn't a sequencer to use.");
        } else {
            this.setSequence(this.filenameURL(false, null).getURL());
            this.sequencer.start();
            this.resetGain();
            this.sequencer.addMetaEventListener(this);
        }
    }

    public void setLooping(boolean value) {
        this.toLoop(true, value);
    }

    public boolean getLooping() {
        return this.toLoop(false, false);
    }

    private synchronized boolean toLoop(boolean action, boolean value) {
        if (action) {
            this.toLoop = value;
        }
        return this.toLoop;
    }

    public boolean loading() {
        return this.loading(false, false);
    }

    private synchronized boolean loading(boolean action, boolean value) {
        if (action) {
            this.loading = value;
        }
        return this.loading;
    }

    public void setSourcename(String value) {
        this.sourcename(true, value);
    }

    public String getSourcename() {
        return this.sourcename(false, null);
    }

    private synchronized String sourcename(boolean action, String value) {
        if (action) {
            this.sourcename = value;
        }
        return this.sourcename;
    }

    public void setFilenameURL(FilenameURL value) {
        this.filenameURL(true, value);
    }

    public String getFilename() {
        return this.filenameURL(false, null).getFilename();
    }

    public FilenameURL getFilenameURL() {
        return this.filenameURL(false, null);
    }

    private synchronized FilenameURL filenameURL(boolean action, FilenameURL value) {
        if (action) {
            this.filenameURL = value;
        }
        return this.filenameURL;
    }

    @Override
    public void meta(MetaMessage message) {
        if (message.getType() == 47) {
            if (this.toLoop) {
                if (!this.checkFadeOut()) {
                    if (!this.incrementSequence()) {
                        try {
                            this.sequencer.setMicrosecondPosition(0L);
                            this.sequencer.start();
                            this.resetGain();
                        }
                        catch (Exception exception) {}
                    }
                } else if (this.sequencer != null) {
                    try {
                        this.sequencer.setMicrosecondPosition(0L);
                        this.sequencer.start();
                        this.resetGain();
                    }
                    catch (Exception exception) {}
                }
            } else if (!this.checkFadeOut()) {
                if (!this.incrementSequence()) {
                    try {
                        this.sequencer.stop();
                        this.sequencer.setMicrosecondPosition(0L);
                        this.sequencer.removeMetaEventListener(this);
                    }
                    catch (Exception exception) {}
                }
            } else {
                try {
                    this.sequencer.stop();
                    this.sequencer.setMicrosecondPosition(0L);
                    this.sequencer.removeMetaEventListener(this);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    public void resetGain() {
        if (this.gain < 0.0f) {
            this.gain = 0.0f;
        }
        if (this.gain > 1.0f) {
            this.gain = 1.0f;
        }
        int midiVolume = (int)(this.gain * SoundSystemConfig.getMasterGain() * Math.abs(this.fadeOutGain) * this.fadeInGain * 127.0f);
        if (this.synthesizer != null) {
            javax.sound.midi.MidiChannel[] e2 = this.synthesizer.getChannels();
            int volumeMessage = 0;
            while (e2 != null && volumeMessage < e2.length) {
                e2[volumeMessage].controlChange(7, midiVolume);
                ++volumeMessage;
            }
        } else if (this.synthDevice != null) {
            try {
                ShortMessage shortMessage7 = new ShortMessage();
                int volumeMessage = 0;
                while (volumeMessage < 16) {
                    shortMessage7.setMessage(176, volumeMessage, 7, midiVolume);
                    this.synthDevice.getReceiver().send(shortMessage7, -1L);
                    ++volumeMessage;
                }
            }
            catch (Exception exception6) {
                this.errorMessage("Error resetting gain on MIDI device");
                this.printStackTrace(exception6);
            }
        } else if (this.sequencer != null && this.sequencer instanceof Synthesizer) {
            this.synthesizer = (Synthesizer)((Object)this.sequencer);
            javax.sound.midi.MidiChannel[] e3 = this.synthesizer.getChannels();
            int volumeMessage = 0;
            while (e3 != null && volumeMessage < e3.length) {
                e3[volumeMessage].controlChange(7, midiVolume);
                ++volumeMessage;
            }
        } else {
            try {
                Receiver receiver8 = MidiSystem.getReceiver();
                ShortMessage shortMessage9 = new ShortMessage();
                int c2 = 0;
                while (c2 < 16) {
                    shortMessage9.setMessage(176, c2, 7, midiVolume);
                    receiver8.send(shortMessage9, -1L);
                    ++c2;
                }
            }
            catch (Exception exception5) {
                this.errorMessage("Error resetting gain on default receiver");
                this.printStackTrace(exception5);
            }
        }
    }

    private void getSequencer() {
        block7: {
            try {
                this.sequencer = MidiSystem.getSequencer();
                if (this.sequencer == null) break block7;
                try {
                    this.sequencer.getTransmitter();
                }
                catch (MidiUnavailableException midiUnavailableException2) {
                    this.message("Unable to get a transmitter from the default MIDI sequencer");
                }
                this.sequencer.open();
            }
            catch (MidiUnavailableException midiUnavailableException3) {
                this.message("Unable to open the default MIDI sequencer");
                this.sequencer = null;
            }
        }
        if (this.sequencer == null) {
            this.sequencer = this.openSequencer("Real Time Sequencer");
        }
        if (this.sequencer == null) {
            this.sequencer = this.openSequencer("Java Sound Sequencer");
        }
        if (this.sequencer == null) {
            this.errorMessage("Failed to find an available MIDI sequencer");
        }
    }

    private void setSequence(URL midiSource) {
        if (this.sequencer == null) {
            this.errorMessage("Unable to update the sequence in method 'setSequence', because variable 'sequencer' is null");
        } else if (midiSource == null) {
            this.errorMessage("Unable to load Midi file in method 'setSequence'.");
        } else {
            try {
                this.sequence = MidiSystem.getSequence(midiSource);
            }
            catch (IOException iOException5) {
                this.errorMessage("Input failed while reading from MIDI file in method 'setSequence'.");
                this.printStackTrace(iOException5);
                return;
            }
            catch (InvalidMidiDataException invalidMidiDataException6) {
                this.errorMessage("Invalid MIDI data encountered, or not a MIDI file in method 'setSequence' (1).");
                this.printStackTrace(invalidMidiDataException6);
                return;
            }
            if (this.sequence == null) {
                this.errorMessage("MidiSystem 'getSequence' method returned null in method 'setSequence'.");
            } else {
                try {
                    this.sequencer.setSequence(this.sequence);
                }
                catch (InvalidMidiDataException invalidMidiDataException3) {
                    this.errorMessage("Invalid MIDI data encountered, or not a MIDI file in method 'setSequence' (2).");
                    this.printStackTrace(invalidMidiDataException3);
                    return;
                }
                catch (Exception exception4) {
                    this.errorMessage("Problem setting sequence from MIDI file in method 'setSequence'.");
                    this.printStackTrace(exception4);
                    return;
                }
            }
        }
    }

    private void getSynthesizer() {
        if (this.sequencer == null) {
            this.errorMessage("Unable to load a Synthesizer in method 'getSynthesizer', because variable 'sequencer' is null");
        } else if (this.sequencer instanceof Synthesizer) {
            this.synthesizer = (Synthesizer)((Object)this.sequencer);
        } else {
            try {
                this.synthesizer = MidiSystem.getSynthesizer();
                this.synthesizer.open();
            }
            catch (MidiUnavailableException midiUnavailableException5) {
                this.message("Unable to open the default synthesizer");
                this.synthesizer = null;
            }
            if (this.synthesizer == null) {
                this.synthDevice = this.openMidiDevice("Java Sound Synthesizer");
                if (this.synthDevice == null) {
                    this.synthDevice = this.openMidiDevice("Microsoft GS Wavetable");
                }
                if (this.synthDevice == null) {
                    this.synthDevice = this.openMidiDevice("Gervill");
                }
                if (this.synthDevice == null) {
                    this.errorMessage("Failed to find an available MIDI synthesizer");
                    return;
                }
            }
            if (this.synthesizer == null) {
                try {
                    this.sequencer.getTransmitter().setReceiver(this.synthDevice.getReceiver());
                }
                catch (MidiUnavailableException midiUnavailableException4) {
                    this.errorMessage("Unable to link sequencer transmitter with MIDI device receiver");
                }
            } else if (this.synthesizer.getDefaultSoundbank() == null) {
                try {
                    this.sequencer.getTransmitter().setReceiver(MidiSystem.getReceiver());
                }
                catch (MidiUnavailableException midiUnavailableException3) {
                    this.errorMessage("Unable to link sequencer transmitter with default receiver");
                }
            } else {
                try {
                    this.sequencer.getTransmitter().setReceiver(this.synthesizer.getReceiver());
                }
                catch (MidiUnavailableException midiUnavailableException2) {
                    this.errorMessage("Unable to link sequencer transmitter with synthesizer receiver");
                }
            }
        }
    }

    private Sequencer openSequencer(String containsString) {
        Sequencer s2 = null;
        s2 = (Sequencer)this.openMidiDevice(containsString);
        if (s2 == null) {
            return null;
        }
        try {
            s2.getTransmitter();
            return s2;
        }
        catch (MidiUnavailableException midiUnavailableException4) {
            this.message("    Unable to get a transmitter from this sequencer");
            s2 = null;
            return null;
        }
    }

    private MidiDevice openMidiDevice(String containsString) {
        this.message("Searching for MIDI device with name containing '" + containsString + "'");
        MidiDevice device = null;
        MidiDevice.Info[] midiDevices = MidiSystem.getMidiDeviceInfo();
        int i2 = 0;
        while (i2 < midiDevices.length) {
            device = null;
            try {
                device = MidiSystem.getMidiDevice(midiDevices[i2]);
            }
            catch (MidiUnavailableException midiUnavailableException7) {
                this.message("    Problem in method 'getMidiDevice':  MIDIUnavailableException was thrown");
                device = null;
            }
            if (device != null && midiDevices[i2].getName().contains(containsString)) {
                this.message("    Found MIDI device named '" + midiDevices[i2].getName() + "'");
                if (device instanceof Synthesizer) {
                    this.message("        *this is a Synthesizer instance");
                }
                if (device instanceof Sequencer) {
                    this.message("        *this is a Sequencer instance");
                }
                try {
                    device.open();
                }
                catch (MidiUnavailableException midiUnavailableException6) {
                    this.message("    Unable to open this MIDI device");
                    device = null;
                }
                return device;
            }
            ++i2;
        }
        this.message("    MIDI device not found");
        return null;
    }

    protected void message(String message) {
        this.logger.message(message, 0);
    }

    protected void importantMessage(String message) {
        this.logger.importantMessage(message, 0);
    }

    protected boolean errorCheck(boolean error, String message) {
        return this.logger.errorCheck(error, "MidiChannel", message, 0);
    }

    protected void errorMessage(String message) {
        this.logger.errorMessage("MidiChannel", message, 0);
    }

    protected void printStackTrace(Exception e2) {
        this.logger.printStackTrace(e2, 1);
    }

    private class FadeThread
    extends SimpleThread {
        private FadeThread() {
        }

        @Override
        public void run() {
            while (!this.dying()) {
                if (MidiChannel.this.fadeOutGain == -1.0f && MidiChannel.this.fadeInGain == 1.0f) {
                    this.snooze(3600000L);
                }
                MidiChannel.this.checkFadeOut();
                this.snooze(50L);
            }
            this.cleanup();
        }

        FadeThread(FadeThread midiChannel$FadeThread2) {
            this();
        }
    }
}

