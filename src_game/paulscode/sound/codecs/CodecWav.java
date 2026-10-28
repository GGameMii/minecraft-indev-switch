/*
 * Decompiled with CFR 0.152.
 */
package paulscode.sound.codecs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import paulscode.sound.ICodec;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;

public class CodecWav
implements ICodec {
    private boolean endOfStream = false;
    private boolean initialized = false;
    private AudioInputStream myAudioInputStream = null;
    private SoundSystemLogger logger = SoundSystemConfig.getLogger();

    @Override
    public void reverseByteOrder(boolean b2) {
    }

    @Override
    public boolean initialize(URL url) {
        this.initialized(true, false);
        this.cleanup();
        if (url == null) {
            this.errorMessage("url null in method 'initialize'");
            this.cleanup();
            return false;
        }
        try {
            this.myAudioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(url.openStream()));
        }
        catch (UnsupportedAudioFileException unsupportedAudioFileException3) {
            this.errorMessage("Unsupported audio format in method 'initialize'");
            this.printStackTrace(unsupportedAudioFileException3);
            return false;
        }
        catch (IOException iOException4) {
            this.errorMessage("Error setting up audio input stream in method 'initialize'");
            this.printStackTrace(iOException4);
            return false;
        }
        this.endOfStream(true, false);
        this.initialized(true, true);
        return true;
    }

    @Override
    public boolean initialized() {
        return this.initialized(false, false);
    }

    @Override
    public SoundBuffer read() {
        if (this.myAudioInputStream == null) {
            return null;
        }
        AudioFormat audioFormat = this.myAudioInputStream.getFormat();
        if (audioFormat == null) {
            this.errorMessage("Audio Format null in method 'read'");
            return null;
        }
        int bytesRead = 0;
        byte[] streamBuffer = new byte[SoundSystemConfig.getStreamingBufferSize()];
        try {
            while (!this.endOfStream(false, false) && bytesRead < streamBuffer.length) {
                int cnt1 = this.myAudioInputStream.read(streamBuffer, bytesRead, streamBuffer.length - bytesRead);
                if (cnt1 <= 0) {
                    this.endOfStream(true, true);
                    break;
                }
                bytesRead += cnt1;
            }
        }
        catch (IOException iOException7) {
            this.endOfStream(true, true);
            return null;
        }
        if (bytesRead <= 0) {
            return null;
        }
        if (bytesRead < streamBuffer.length) {
            streamBuffer = CodecWav.trimArray(streamBuffer, bytesRead);
        }
        byte[] data = CodecWav.convertAudioBytes(streamBuffer, audioFormat.getSampleSizeInBits() == 16);
        SoundBuffer buffer = new SoundBuffer(data, audioFormat);
        return buffer;
    }

    @Override
    public SoundBuffer readAll() {
        if (this.myAudioInputStream == null) {
            this.errorMessage("Audio input stream null in method 'readAll'");
            return null;
        }
        AudioFormat myAudioFormat = this.myAudioInputStream.getFormat();
        if (myAudioFormat == null) {
            this.errorMessage("Audio Format null in method 'readAll'");
            return null;
        }
        byte[] fullBuffer = null;
        int fileSize = myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * myAudioFormat.getSampleSizeInBits() / 8;
        if (fileSize > 0) {
            fullBuffer = new byte[myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * myAudioFormat.getSampleSizeInBits() / 8];
            int soundBuffer = 0;
            try {
                int data1;
                while ((data1 = this.myAudioInputStream.read(fullBuffer, soundBuffer, fullBuffer.length - soundBuffer)) != -1 && soundBuffer < fullBuffer.length) {
                    soundBuffer += data1;
                }
            }
            catch (IOException iOException11) {
                this.errorMessage("Exception thrown while reading from the AudioInputStream (location #1).");
                this.printStackTrace(iOException11);
                return null;
            }
        }
        int data1 = 0;
        byte[] smallBuffer = null;
        smallBuffer = new byte[SoundSystemConfig.getFileChunkSize()];
        while (!this.endOfStream(false, false) && data1 < SoundSystemConfig.getMaxFileSize()) {
            int soundBuffer = 0;
            try {
                while (soundBuffer < smallBuffer.length) {
                    int cnt1 = this.myAudioInputStream.read(smallBuffer, soundBuffer, smallBuffer.length - soundBuffer);
                    if (cnt1 <= 0) {
                        this.endOfStream(true, true);
                        break;
                    }
                    soundBuffer += cnt1;
                }
            }
            catch (IOException iOException10) {
                this.errorMessage("Exception thrown while reading from the AudioInputStream (location #2).");
                this.printStackTrace(iOException10);
                return null;
            }
            data1 += soundBuffer;
            fullBuffer = CodecWav.appendByteArrays(fullBuffer, smallBuffer, soundBuffer);
        }
        byte[] data2 = CodecWav.convertAudioBytes(fullBuffer, myAudioFormat.getSampleSizeInBits() == 16);
        SoundBuffer soundBuffer2 = new SoundBuffer(data2, myAudioFormat);
        try {
            this.myAudioInputStream.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return soundBuffer2;
    }

    @Override
    public boolean endOfStream() {
        return this.endOfStream(false, false);
    }

    @Override
    public void cleanup() {
        if (this.myAudioInputStream != null) {
            try {
                this.myAudioInputStream.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.myAudioInputStream = null;
    }

    @Override
    public AudioFormat getAudioFormat() {
        return this.myAudioInputStream == null ? null : this.myAudioInputStream.getFormat();
    }

    private synchronized boolean initialized(boolean action, boolean value) {
        if (action) {
            this.initialized = value;
        }
        return this.initialized;
    }

    private synchronized boolean endOfStream(boolean action, boolean value) {
        if (action) {
            this.endOfStream = value;
        }
        return this.endOfStream;
    }

    private static byte[] trimArray(byte[] array, int maxLength) {
        byte[] trimmedArray = null;
        if (array != null && array.length > maxLength) {
            trimmedArray = new byte[maxLength];
            System.arraycopy(array, 0, trimmedArray, 0, maxLength);
        }
        return trimmedArray;
    }

    /*
     * Unable to fully structure code
     */
    private static byte[] convertAudioBytes(byte[] audio_bytes, boolean two_bytes_data) {
        block3: {
            dest = ByteBuffer.allocateDirect(audio_bytes.length);
            dest.order(ByteOrder.nativeOrder());
            src = ByteBuffer.wrap(audio_bytes);
            src.order(ByteOrder.LITTLE_ENDIAN);
            if (!two_bytes_data) ** GOTO lbl17
            arrayBackedBuffer = dest.asShortBuffer();
            src_short = src.asShortBuffer();
            while (src_short.hasRemaining()) {
                arrayBackedBuffer.put(src_short.get());
            }
            break block3;
lbl-1000:
            // 1 sources

            {
                dest.put(src.get());
lbl17:
                // 2 sources

                ** while (src.hasRemaining())
            }
        }
        dest.rewind();
        if (!dest.hasArray()) {
            arrayBackedBuffer1 = new byte[dest.capacity()];
            dest.get(arrayBackedBuffer1);
            dest.clear();
            return arrayBackedBuffer1;
        }
        return dest.array();
    }

    private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo, int length) {
        byte[] newArray;
        if (arrayOne == null && arrayTwo == null) {
            return null;
        }
        if (arrayOne == null) {
            newArray = new byte[length];
            System.arraycopy(arrayTwo, 0, newArray, 0, length);
            arrayTwo = null;
        } else if (arrayTwo == null) {
            newArray = new byte[arrayOne.length];
            System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
            arrayOne = null;
        } else {
            newArray = new byte[arrayOne.length + length];
            System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
            System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, length);
            arrayOne = null;
            arrayTwo = null;
        }
        return newArray;
    }

    private void errorMessage(String message) {
        this.logger.errorMessage("CodecWav", message, 0);
    }

    private void printStackTrace(Exception e2) {
        this.logger.printStackTrace(e2, 1);
    }
}

