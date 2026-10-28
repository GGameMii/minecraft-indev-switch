/*
 * Decompiled with CFR 0.152.
 */
package com.jcraft.jorbis;

import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Comment;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.Info;
import java.io.FileInputStream;

class DecodeExample {
    static int convsize = 8192;
    static byte[] convbuffer = new byte[convsize];

    DecodeExample() {
    }

    /*
     * Unable to fully structure code
     */
    public static void main(String[] arg) {
        input = System.in;
        if (arg.length > 0) {
            try {
                input = new FileInputStream(arg[0]);
            }
            catch (Exception exception28) {
                System.err.println(exception28);
            }
        }
        oy = new SyncState();
        os = new StreamState();
        og = new Page();
        op = new Packet();
        vi = new Info();
        vc = new Comment();
        vd = new DspState();
        vb = new Block(vd);
        bytes = 0;
        oy.init();
        while (true) {
            eos = false;
            index = oy.buffer(4096);
            buffer = oy.data;
            try {
                bytes = input.read(buffer, index, 4096);
            }
            catch (Exception exception27) {
                System.err.println(exception27);
                System.exit(-1);
            }
            oy.wrote(bytes);
            if (oy.pageout(og) != 1) {
                if (bytes < 4096) {
                    oy.clear();
                    System.err.println("Done.");
                    return;
                }
                System.err.println("Input does not appear to be an Ogg bitstream.");
                System.exit(1);
            }
            os.init(og.serialno());
            vi.init();
            vc.init();
            if (os.pagein(og) < 0) {
                System.err.println("Error reading first page of Ogg bitstream data.");
                System.exit(1);
            }
            if (os.packetout(op) != 1) {
                System.err.println("Error reading initial header packet.");
                System.exit(1);
            }
            if (vi.synthesis_headerin(vc, op) < 0) {
                System.err.println("This Ogg bitstream does not contain Vorbis audio data.");
                System.exit(1);
            }
            i = 0;
            while (i < 2) {
                block10: while (i < 2 && (_pcm = oy.pageout(og)) != 0) {
                    if (_pcm != 1) continue;
                    os.pagein(og);
                    while (i < 2) {
                        _pcm = os.packetout(op);
                        if (_pcm == 0) continue block10;
                        if (_pcm == -1) {
                            System.err.println("Corrupt secondary header.  Exiting.");
                            System.exit(1);
                        }
                        vi.synthesis_headerin(vc, op);
                        ++i;
                    }
                }
                index = oy.buffer(4096);
                buffer = oy.data;
                try {
                    bytes = input.read(buffer, index, 4096);
                }
                catch (Exception exception26) {
                    System.err.println(exception26);
                    System.exit(1);
                }
                if (bytes == 0 && i < 2) {
                    System.err.println("End of file before finding all Vorbis headers!");
                    System.exit(1);
                }
                oy.wrote(bytes);
            }
            b29 = vc.user_comments;
            _index = 0;
            while (_index < b29.length && b29[_index] != null) {
                System.err.println(new String(b29[_index], 0, b29[_index].length - 1));
                ++_index;
            }
            System.err.println("\nBitstream is " + vi.channels + " channel, " + vi.rate + "Hz");
            System.err.println("Encoded by: " + new String(vc.vendor, 0, vc.vendor.length - 1) + "\n");
            DecodeExample.convsize = 4096 / vi.channels;
            vd.synthesis_init(vi);
            vb.init(vd);
            f30 = new float[1][][];
            i31 = new int[vi.channels];
            while (!eos) {
                block14: while (!eos && (e = oy.pageout(og)) != 0) {
                    if (e == -1) {
                        System.err.println("Corrupt or missing data in bitstream; continuing...");
                        continue;
                    }
                    os.pagein(og);
                    block15: while (true) {
                        if ((e = os.packetout(op)) == 0) {
                            if (og.eos() == 0) continue block14;
                            eos = true;
                            continue block14;
                        }
                        if (e == -1) continue;
                        if (vb.synthesis(op) == 0) {
                            vd.synthesis_blockin(vb);
                        }
                        while (true) {
                            if ((samples = vd.synthesis_pcmout(f30, i31)) > 0) ** break;
                            continue block15;
                            pcm = f30[0];
                            bout = samples < DecodeExample.convsize ? samples : DecodeExample.convsize;
                            i = 0;
                            while (i < vi.channels) {
                                ptr = i * 2;
                                mono = i31[i];
                                j = 0;
                                while (j < bout) {
                                    val = (int)((double)pcm[i][mono + j] * 32767.0);
                                    if (val > 32767) {
                                        val = 32767;
                                    }
                                    if (val < -32768) {
                                        val = -32768;
                                    }
                                    if (val < 0) {
                                        val |= 32768;
                                    }
                                    DecodeExample.convbuffer[ptr] = (byte)val;
                                    DecodeExample.convbuffer[ptr + 1] = (byte)(val >>> 8);
                                    ptr += 2 * vi.channels;
                                    ++j;
                                }
                                ++i;
                            }
                            System.out.write(DecodeExample.convbuffer, 0, 2 * vi.channels * bout);
                            vd.synthesis_read(bout);
                        }
                        break;
                    }
                }
                if (eos) continue;
                index = oy.buffer(4096);
                buffer = oy.data;
                try {
                    bytes = input.read(buffer, index, 4096);
                }
                catch (Exception exception25) {
                    System.err.println(exception25);
                    System.exit(1);
                }
                oy.wrote(bytes);
                if (bytes != 0) continue;
                eos = true;
            }
            os.clear();
            vb.clear();
            vd.clear();
            vi.clear();
        }
    }
}

