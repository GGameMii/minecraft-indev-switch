/*
 * Decompiled with CFR 0.152.
 */
package paulscode.sound.codecs;

import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Comment;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.Info;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownServiceException;
import java.nio.ByteOrder;
import javax.sound.sampled.AudioFormat;
import paulscode.sound.ICodec;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;

public class CodecJOrbis
implements ICodec {
    protected URL url;
    protected URLConnection urlConnection = null;
    private InputStream inputStream;
    private AudioFormat audioFormat;
    private boolean endOfStream = false;
    private boolean initialized = false;
    private byte[] buffer = null;
    private int bufferSize;
    private int count = 0;
    private int index = 0;
    private int convertedBufferSize;
    private float[][][] pcmInfo;
    private int[] pcmIndex;
    private Packet joggPacket = new Packet();
    private Page joggPage = new Page();
    private StreamState joggStreamState = new StreamState();
    private SyncState joggSyncState = new SyncState();
    private DspState jorbisDspState = new DspState();
    private Block jorbisBlock = new Block(this.jorbisDspState);
    private Comment jorbisComment = new Comment();
    private Info jorbisInfo = new Info();
    private SoundSystemLogger logger = SoundSystemConfig.getLogger();
    private static final boolean LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;

    @Override
    public void reverseByteOrder(boolean b2) {
    }

    @Override
    public boolean initialize(URL url) {
        this.initialized(true, false);
        if (this.joggStreamState != null) {
            this.joggStreamState.clear();
        }
        if (this.jorbisBlock != null) {
            this.jorbisBlock.clear();
        }
        if (this.jorbisDspState != null) {
            this.jorbisDspState.clear();
        }
        if (this.jorbisInfo != null) {
            this.jorbisInfo.clear();
        }
        if (this.joggSyncState != null) {
            this.joggSyncState.clear();
        }
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        this.url = url;
        this.bufferSize = SoundSystemConfig.getStreamingBufferSize() / 2;
        this.buffer = null;
        this.count = 0;
        this.index = 0;
        this.joggStreamState = new StreamState();
        this.jorbisBlock = new Block(this.jorbisDspState);
        this.jorbisDspState = new DspState();
        this.jorbisInfo = new Info();
        this.joggSyncState = new SyncState();
        try {
            this.urlConnection = url.openConnection();
        }
        catch (UnknownServiceException unknownServiceException5) {
            this.errorMessage("Unable to create a UrlConnection in method 'initialize'.");
            this.printStackTrace(unknownServiceException5);
            this.cleanup();
            return false;
        }
        catch (IOException iOException6) {
            this.errorMessage("Unable to create a UrlConnection in method 'initialize'.");
            this.printStackTrace(iOException6);
            this.cleanup();
            return false;
        }
        if (this.urlConnection != null) {
            try {
                this.inputStream = this.openInputStream();
            }
            catch (IOException iOException4) {
                this.errorMessage("Unable to acquire inputstream in method 'initialize'.");
                this.printStackTrace(iOException4);
                this.cleanup();
                return false;
            }
        }
        this.endOfStream(true, false);
        this.joggSyncState.init();
        this.joggSyncState.buffer(this.bufferSize);
        this.buffer = this.joggSyncState.data;
        try {
            if (!this.readHeader()) {
                this.errorMessage("Error reading the header");
                return false;
            }
        }
        catch (IOException iOException8) {
            this.errorMessage("Error reading the header");
            return false;
        }
        this.convertedBufferSize = this.bufferSize * 2;
        this.jorbisDspState.synthesis_init(this.jorbisInfo);
        this.jorbisBlock.init(this.jorbisDspState);
        int channels = this.jorbisInfo.channels;
        int rate = this.jorbisInfo.rate;
        this.audioFormat = new AudioFormat(rate, 16, channels, true, false);
        this.pcmInfo = new float[1][][];
        this.pcmIndex = new int[this.jorbisInfo.channels];
        this.initialized(true, true);
        return true;
    }

    protected InputStream openInputStream() throws IOException {
        return this.urlConnection.getInputStream();
    }

    @Override
    public boolean initialized() {
        return this.initialized(false, false);
    }

    @Override
    public SoundBuffer read() {
        byte[] buff = this.readBytes();
        return buff == null ? null : new SoundBuffer(buff, this.audioFormat);
    }

    @Override
    public SoundBuffer readAll() {
        byte[] buff = this.readBytes();
        while (!this.endOfStream(false, false)) {
            if ((buff = CodecJOrbis.appendByteArrays(buff, this.readBytes())) != null && buff.length >= SoundSystemConfig.getMaxFileSize()) break;
        }
        return new SoundBuffer(buff, this.audioFormat);
    }

    @Override
    public boolean endOfStream() {
        return this.endOfStream(false, false);
    }

    @Override
    public void cleanup() {
        this.joggStreamState.clear();
        this.jorbisBlock.clear();
        this.jorbisDspState.clear();
        this.jorbisInfo.clear();
        this.joggSyncState.clear();
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        this.joggStreamState = null;
        this.jorbisBlock = null;
        this.jorbisDspState = null;
        this.jorbisInfo = null;
        this.joggSyncState = null;
        this.inputStream = null;
    }

    @Override
    public AudioFormat getAudioFormat() {
        return this.audioFormat;
    }

    private boolean readHeader() throws IOException {
        this.index = this.joggSyncState.buffer(this.bufferSize);
        int bytes = this.inputStream.read(this.joggSyncState.data, this.index, this.bufferSize);
        if (bytes < 0) {
            bytes = 0;
        }
        this.joggSyncState.wrote(bytes);
        if (this.joggSyncState.pageout(this.joggPage) != 1) {
            if (bytes < this.bufferSize) {
                return true;
            }
            this.errorMessage("Ogg header not recognized in method 'readHeader'.");
            return false;
        }
        this.joggStreamState.init(this.joggPage.serialno());
        this.jorbisInfo.init();
        this.jorbisComment.init();
        if (this.joggStreamState.pagein(this.joggPage) < 0) {
            this.errorMessage("Problem with first Ogg header page in method 'readHeader'.");
            return false;
        }
        if (this.joggStreamState.packetout(this.joggPacket) != 1) {
            this.errorMessage("Problem with first Ogg header packet in method 'readHeader'.");
            return false;
        }
        if (this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket) < 0) {
            this.errorMessage("File does not contain Vorbis header in method 'readHeader'.");
            return false;
        }
        int i2 = 0;
        while (i2 < 2) {
            int result;
            block1: while (i2 < 2 && (result = this.joggSyncState.pageout(this.joggPage)) != 0) {
                if (result != 1) continue;
                this.joggStreamState.pagein(this.joggPage);
                while (i2 < 2) {
                    result = this.joggStreamState.packetout(this.joggPacket);
                    if (result == 0) continue block1;
                    if (result == -1) {
                        this.errorMessage("Secondary Ogg header corrupt in method 'readHeader'.");
                        return false;
                    }
                    this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket);
                    ++i2;
                }
            }
            this.index = this.joggSyncState.buffer(this.bufferSize);
            bytes = this.inputStream.read(this.joggSyncState.data, this.index, this.bufferSize);
            if (bytes < 0) {
                bytes = 0;
            }
            if (bytes == 0 && i2 < 2) {
                this.errorMessage("End of file reached before finished readingOgg header in method 'readHeader'");
                return false;
            }
            this.joggSyncState.wrote(bytes);
        }
        this.index = this.joggSyncState.buffer(this.bufferSize);
        this.buffer = this.joggSyncState.data;
        return true;
    }

    private byte[] readBytes() {
        if (!this.initialized(false, false)) {
            return null;
        }
        if (this.endOfStream(false, false)) {
            return null;
        }
        byte[] returnBuffer = null;
        block1 : switch (this.joggSyncState.pageout(this.joggPage)) {
            case -1: 
            case 0: {
                this.endOfStream(true, true);
                break;
            }
            case 1: {
                this.joggStreamState.pagein(this.joggPage);
                if (this.joggPage.granulepos() == 0L) {
                    this.endOfStream(true, true);
                    break;
                }
                while (true) {
                    switch (this.joggStreamState.packetout(this.joggPacket)) {
                        case -1: 
                        case 0: {
                            if (this.joggPage.eos() == 0) break block1;
                            this.endOfStream(true, true);
                            break block1;
                        }
                        case 1: {
                            returnBuffer = CodecJOrbis.appendByteArrays(returnBuffer, this.decodeCurrentPacket());
                        }
                    }
                }
            }
        }
        if (!this.endOfStream(false, false)) {
            this.index = this.joggSyncState.buffer(this.bufferSize);
            if (this.index == -1) {
                this.endOfStream(true, true);
            } else {
                this.buffer = this.joggSyncState.data;
                try {
                    this.count = this.inputStream.read(this.buffer, this.index, this.bufferSize);
                }
                catch (Exception exception3) {
                    this.printStackTrace(exception3);
                    return returnBuffer;
                }
                this.joggSyncState.wrote(this.count);
                if (this.count == 0) {
                    this.endOfStream(true, true);
                }
            }
        }
        return returnBuffer;
    }

    private byte[] decodeCurrentPacket() {
        int samples;
        byte[] convertedBuffer = new byte[this.convertedBufferSize];
        if (this.jorbisBlock.synthesis(this.joggPacket) == 0) {
            this.jorbisDspState.synthesis_blockin(this.jorbisBlock);
        }
        int maxRange = this.convertedBufferSize / (this.jorbisInfo.channels * 2);
        int convertedDataLength = 0;
        while (convertedDataLength < this.convertedBufferSize && (samples = this.jorbisDspState.synthesis_pcmout(this.pcmInfo, this.pcmIndex)) > 0) {
            int range = samples < maxRange ? samples : maxRange;
            int i2 = 0;
            while (i2 < this.jorbisInfo.channels) {
                int sampleIndex = i2 * 2;
                int j2 = 0;
                while (j2 < range) {
                    int value = (int)(this.pcmInfo[0][i2][this.pcmIndex[i2] + j2] * 32767.0f);
                    if (value > Short.MAX_VALUE) {
                        value = Short.MAX_VALUE;
                    }
                    if (value < Short.MIN_VALUE) {
                        value = Short.MIN_VALUE;
                    }
                    if (value < 0) {
                        value |= 0x8000;
                    }
                    if (LITTLE_ENDIAN) {
                        convertedBuffer[convertedDataLength + sampleIndex] = (byte)value;
                        convertedBuffer[convertedDataLength + sampleIndex + 1] = (byte)(value >>> 8);
                    } else {
                        convertedBuffer[convertedDataLength + sampleIndex + 1] = (byte)value;
                        convertedBuffer[convertedDataLength + sampleIndex] = (byte)(value >>> 8);
                    }
                    sampleIndex += 2 * this.jorbisInfo.channels;
                    ++j2;
                }
                ++i2;
            }
            convertedDataLength += range * this.jorbisInfo.channels * 2;
            this.jorbisDspState.synthesis_read(range);
        }
        convertedBuffer = CodecJOrbis.trimArray(convertedBuffer, convertedDataLength);
        return convertedBuffer;
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

    private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo) {
        byte[] newArray;
        if (arrayOne == null && arrayTwo == null) {
            return null;
        }
        if (arrayOne == null) {
            newArray = new byte[arrayTwo.length];
            System.arraycopy(arrayTwo, 0, newArray, 0, arrayTwo.length);
            arrayTwo = null;
        } else if (arrayTwo == null) {
            newArray = new byte[arrayOne.length];
            System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
            arrayOne = null;
        } else {
            newArray = new byte[arrayOne.length + arrayTwo.length];
            System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
            System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, arrayTwo.length);
            arrayOne = null;
            arrayTwo = null;
        }
        return newArray;
    }

    private void errorMessage(String message) {
        this.logger.errorMessage("CodecJOrbis", message, 0);
    }

    private void printStackTrace(Exception e2) {
        this.logger.printStackTrace(e2, 1);
    }
}

