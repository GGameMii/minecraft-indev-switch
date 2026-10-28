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
import com.jcraft.jorbis.JOrbisException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class VorbisFile {
    static final int CHUNKSIZE = 8500;
    static final int SEEK_SET = 0;
    static final int SEEK_CUR = 1;
    static final int SEEK_END = 2;
    static final int OV_FALSE = -1;
    static final int OV_EOF = -2;
    static final int OV_HOLE = -3;
    static final int OV_EREAD = -128;
    static final int OV_EFAULT = -129;
    static final int OV_EIMPL = -130;
    static final int OV_EINVAL = -131;
    static final int OV_ENOTVORBIS = -132;
    static final int OV_EBADHEADER = -133;
    static final int OV_EVERSION = -134;
    static final int OV_ENOTAUDIO = -135;
    static final int OV_EBADPACKET = -136;
    static final int OV_EBADLINK = -137;
    static final int OV_ENOSEEK = -138;
    InputStream datasource;
    boolean seekable = false;
    long offset;
    long end;
    SyncState oy = new SyncState();
    int links;
    long[] offsets;
    long[] dataoffsets;
    int[] serialnos;
    long[] pcmlengths;
    Info[] vi;
    Comment[] vc;
    long pcm_offset;
    boolean decode_ready = false;
    int current_serialno;
    int current_link;
    float bittrack;
    float samptrack;
    StreamState os = new StreamState();
    DspState vd = new DspState();
    Block vb = new Block(this.vd);

    public VorbisFile(String file) throws JOrbisException {
        SeekableInputStream is = null;
        try {
            try {
                is = new SeekableInputStream(file);
                int e2 = this.open(is, null, 0);
                if (e2 == -1) {
                    throw new JOrbisException("VorbisFile: open return -1");
                }
            }
            catch (Exception exception11) {
                throw new JOrbisException("VorbisFile: " + exception11.toString());
            }
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (IOException iOException10) {
                    iOException10.printStackTrace();
                }
            }
        }
    }

    public VorbisFile(InputStream is, byte[] initial, int ibytes) throws JOrbisException {
        this.open(is, initial, ibytes);
    }

    private int get_data() {
        int bytes1;
        int index = this.oy.buffer(8500);
        byte[] buffer = this.oy.data;
        try {
            bytes1 = this.datasource.read(buffer, index, 8500);
        }
        catch (Exception exception5) {
            return -128;
        }
        this.oy.wrote(bytes1);
        if (bytes1 == -1) {
            bytes1 = 0;
        }
        return bytes1;
    }

    private void seek_helper(long offst) {
        VorbisFile.fseek(this.datasource, offst, 0);
        this.offset = offst;
        this.oy.reset();
    }

    private int get_next_page(Page page, long boundary) {
        if (boundary > 0L) {
            boundary += this.offset;
        }
        while (boundary <= 0L || this.offset < boundary) {
            int ret;
            int more = this.oy.pageseek(page);
            if (more < 0) {
                this.offset -= (long)more;
                continue;
            }
            if (more != 0) {
                ret = (int)this.offset;
                this.offset += (long)more;
                return ret;
            }
            if (boundary == 0L) {
                return -1;
            }
            ret = this.get_data();
            if (ret == 0) {
                return -2;
            }
            if (ret >= 0) continue;
            return -128;
        }
        return -1;
    }

    private int get_prev_page(Page page) throws JOrbisException {
        long begin = this.offset;
        int offst = -1;
        block0: while (true) {
            int ret;
            if (offst == -1) {
                if ((begin -= 8500L) < 0L) {
                    begin = 0L;
                }
                this.seek_helper(begin);
                while (true) {
                    if (this.offset >= begin + 8500L) continue block0;
                    ret = this.get_next_page(page, begin + 8500L - this.offset);
                    if (ret == -128) {
                        return -128;
                    }
                    if (ret >= 0) {
                        offst = ret;
                        continue;
                    }
                    break;
                }
            } else {
                this.seek_helper(offst);
                ret = this.get_next_page(page, 8500L);
                if (ret < 0) {
                    return -129;
                }
                return offst;
            }
            if (offst == -1) break;
        }
        throw new JOrbisException();
    }

    int bisect_forward_serialno(long begin, long searched, long end, int currentno, int m2) {
        int ret;
        long endsearched = end;
        long next = end;
        Page page = new Page();
        while (searched < endsearched) {
            long bisect = endsearched - searched < 8500L ? searched : (searched + endsearched) / 2L;
            this.seek_helper(bisect);
            ret = this.get_next_page(page, -1L);
            if (ret == -128) {
                return -128;
            }
            if (ret >= 0 && page.serialno() == currentno) {
                searched = ret + page.header_len + page.body_len;
                continue;
            }
            endsearched = bisect;
            if (ret < 0) continue;
            next = ret;
        }
        this.seek_helper(next);
        ret = this.get_next_page(page, -1L);
        if (ret == -128) {
            return -128;
        }
        if (searched < end && ret != -1) {
            ret = this.bisect_forward_serialno(next, this.offset, end, page.serialno(), m2 + 1);
            if (ret == -128) {
                return -128;
            }
        } else {
            this.links = m2 + 1;
            this.offsets = new long[m2 + 2];
            this.offsets[m2 + 1] = searched;
        }
        this.offsets[m2] = begin;
        return 0;
    }

    int fetch_headers(Info vi, Comment vc, int[] serialno, Page og_ptr) {
        Page og = new Page();
        Packet op = new Packet();
        if (og_ptr == null) {
            int ret = this.get_next_page(og, 8500L);
            if (ret == -128) {
                return -128;
            }
            if (ret < 0) {
                return -132;
            }
            og_ptr = og;
        }
        if (serialno != null) {
            serialno[0] = og_ptr.serialno();
        }
        this.os.init(og_ptr.serialno());
        vi.init();
        vc.init();
        int i2 = 0;
        block0: do {
            if (i2 >= 3) {
                return 0;
            }
            this.os.pagein(og_ptr);
            while (i2 < 3) {
                int result = this.os.packetout(op);
                if (result == 0) continue block0;
                if (result == -1) {
                    vi.clear();
                    vc.clear();
                    this.os.clear();
                    return -1;
                }
                if (vi.synthesis_headerin(vc, op) != 0) {
                    vi.clear();
                    vc.clear();
                    this.os.clear();
                    return -1;
                }
                ++i2;
            }
        } while (i2 >= 3 || this.get_next_page(og_ptr, 1L) >= 0);
        vi.clear();
        vc.clear();
        this.os.clear();
        return -1;
    }

    void prefetch_all_headers(Info first_i, Comment first_c, int dataoffset) throws JOrbisException {
        Page og = new Page();
        this.vi = new Info[this.links];
        this.vc = new Comment[this.links];
        this.dataoffsets = new long[this.links];
        this.pcmlengths = new long[this.links];
        this.serialnos = new int[this.links];
        int i2 = 0;
        while (i2 < this.links) {
            block6: {
                if (first_i != null && first_c != null && i2 == 0) {
                    this.vi[i2] = first_i;
                    this.vc[i2] = first_c;
                    this.dataoffsets[i2] = dataoffset;
                } else {
                    this.seek_helper(this.offsets[i2]);
                    this.vi[i2] = new Info();
                    this.vc[i2] = new Comment();
                    if (this.fetch_headers(this.vi[i2], this.vc[i2], null, null) == -1) {
                        this.dataoffsets[i2] = -1L;
                    } else {
                        this.dataoffsets[i2] = this.offset;
                        this.os.clear();
                    }
                }
                long end = this.offsets[i2 + 1];
                this.seek_helper(end);
                do {
                    int ret;
                    if ((ret = this.get_prev_page(og)) != -1) continue;
                    this.vi[i2].clear();
                    this.vc[i2].clear();
                    break block6;
                } while (og.granulepos() == -1L);
                this.serialnos[i2] = og.serialno();
                this.pcmlengths[i2] = og.granulepos();
            }
            ++i2;
        }
    }

    private int make_decode_ready() {
        if (this.decode_ready) {
            System.exit(1);
        }
        this.vd.synthesis_init(this.vi[0]);
        this.vb.init(this.vd);
        this.decode_ready = true;
        return 0;
    }

    int open_seekable() throws JOrbisException {
        Info initial_i = new Info();
        Comment initial_c = new Comment();
        Page og = new Page();
        int[] foo = new int[1];
        int ret = this.fetch_headers(initial_i, initial_c, foo, null);
        int serialno = foo[0];
        int dataoffset = (int)this.offset;
        this.os.clear();
        if (ret == -1) {
            return -1;
        }
        if (ret < 0) {
            return ret;
        }
        this.seekable = true;
        VorbisFile.fseek(this.datasource, 0L, 2);
        long end = this.offset = VorbisFile.ftell(this.datasource);
        end = this.get_prev_page(og);
        if (og.serialno() != serialno) {
            if (this.bisect_forward_serialno(0L, 0L, end + 1L, serialno, 0) < 0) {
                this.clear();
                return -128;
            }
        } else if (this.bisect_forward_serialno(0L, end, end + 1L, serialno, 0) < 0) {
            this.clear();
            return -128;
        }
        this.prefetch_all_headers(initial_i, initial_c, dataoffset);
        return 0;
    }

    int open_nonseekable() {
        this.links = 1;
        this.vi = new Info[this.links];
        this.vi[0] = new Info();
        this.vc = new Comment[this.links];
        this.vc[0] = new Comment();
        int[] foo = new int[1];
        if (this.fetch_headers(this.vi[0], this.vc[0], foo, null) == -1) {
            return -1;
        }
        this.current_serialno = foo[0];
        this.make_decode_ready();
        return 0;
    }

    void decode_clear() {
        this.os.clear();
        this.vd.clear();
        this.vb.clear();
        this.decode_ready = false;
        this.bittrack = 0.0f;
        this.samptrack = 0.0f;
    }

    int process_packet(int readp) {
        Page og = new Page();
        while (true) {
            Packet i2;
            int foo;
            if (this.decode_ready && (foo = this.os.packetout(i2 = new Packet())) > 0) {
                long ret = i2.granulepos;
                if (this.vb.synthesis(i2) == 0) {
                    int link = this.vd.synthesis_pcmout(null, null);
                    this.vd.synthesis_blockin(this.vb);
                    this.samptrack += (float)(this.vd.synthesis_pcmout(null, null) - link);
                    this.bittrack += (float)(i2.bytes * 8);
                    if (ret != -1L && i2.e_o_s == 0) {
                        link = this.seekable ? this.current_link : 0;
                        int samples = this.vd.synthesis_pcmout(null, null);
                        ret -= (long)samples;
                        int i1 = 0;
                        while (i1 < link) {
                            ret += this.pcmlengths[i1];
                            ++i1;
                        }
                        this.pcm_offset = ret;
                    }
                    return 1;
                }
            }
            if (readp == 0) {
                return 0;
            }
            if (this.get_next_page(og, -1L) < 0) {
                return 0;
            }
            this.bittrack += (float)(og.header_len * 8);
            if (this.decode_ready && this.current_serialno != og.serialno()) {
                this.decode_clear();
            }
            if (!this.decode_ready) {
                if (!this.seekable) {
                    int[] i12 = new int[1];
                    int i13 = this.fetch_headers(this.vi[0], this.vc[0], i12, og);
                    this.current_serialno = i12[0];
                    if (i13 != 0) {
                        return i13;
                    }
                    ++this.current_link;
                } else {
                    this.current_serialno = og.serialno();
                    int i10 = 0;
                    while (i10 < this.links && this.serialnos[i10] != this.current_serialno) {
                        ++i10;
                    }
                    if (i10 == this.links) {
                        return -1;
                    }
                    this.current_link = i10;
                    this.os.init(this.current_serialno);
                    this.os.reset();
                }
                this.make_decode_ready();
            }
            this.os.pagein(og);
        }
    }

    int clear() {
        this.vb.clear();
        this.vd.clear();
        this.os.clear();
        if (this.vi != null && this.links != 0) {
            int i2 = 0;
            while (i2 < this.links) {
                this.vi[i2].clear();
                this.vc[i2].clear();
                ++i2;
            }
            this.vi = null;
            this.vc = null;
        }
        if (this.dataoffsets != null) {
            this.dataoffsets = null;
        }
        if (this.pcmlengths != null) {
            this.pcmlengths = null;
        }
        if (this.serialnos != null) {
            this.serialnos = null;
        }
        if (this.offsets != null) {
            this.offsets = null;
        }
        this.oy.clear();
        return 0;
    }

    static int fseek(InputStream fis, long off, int whence) {
        if (fis instanceof SeekableInputStream) {
            SeekableInputStream e2 = (SeekableInputStream)fis;
            try {
                if (whence == 0) {
                    e2.seek(off);
                } else if (whence == 2) {
                    e2.seek(e2.getLength() - off);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            return 0;
        }
        try {
            if (whence == 0) {
                fis.reset();
            }
            fis.skip(off);
            return 0;
        }
        catch (Exception exception7) {
            return -1;
        }
    }

    static long ftell(InputStream fis) {
        try {
            if (fis instanceof SeekableInputStream) {
                SeekableInputStream sis = (SeekableInputStream)fis;
                return sis.tell();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return 0L;
    }

    int open(InputStream is, byte[] initial, int ibytes) throws JOrbisException {
        return this.open_callbacks(is, initial, ibytes);
    }

    int open_callbacks(InputStream is, byte[] initial, int ibytes) throws JOrbisException {
        int ret;
        this.datasource = is;
        this.oy.init();
        if (initial != null) {
            int index = this.oy.buffer(ibytes);
            System.arraycopy(initial, 0, this.oy.data, index, ibytes);
            this.oy.wrote(ibytes);
        }
        if ((ret = is instanceof SeekableInputStream ? this.open_seekable() : this.open_nonseekable()) != 0) {
            this.datasource = null;
            this.clear();
        }
        return ret;
    }

    public int streams() {
        return this.links;
    }

    public boolean seekable() {
        return this.seekable;
    }

    public int bitrate(int i2) {
        if (i2 >= this.links) {
            return -1;
        }
        if (!this.seekable && i2 != 0) {
            return this.bitrate(0);
        }
        if (i2 >= 0) {
            return this.seekable ? (int)Math.rint((float)((this.offsets[i2 + 1] - this.dataoffsets[i2]) * 8L) / this.time_total(i2)) : (this.vi[i2].bitrate_nominal > 0 ? this.vi[i2].bitrate_nominal : (this.vi[i2].bitrate_upper > 0 ? (this.vi[i2].bitrate_lower > 0 ? (this.vi[i2].bitrate_upper + this.vi[i2].bitrate_lower) / 2 : this.vi[i2].bitrate_upper) : -1));
        }
        long bits = 0L;
        int j2 = 0;
        while (j2 < this.links) {
            bits += (this.offsets[j2 + 1] - this.dataoffsets[j2]) * 8L;
            ++j2;
        }
        return (int)Math.rint((float)bits / this.time_total(-1));
    }

    public int bitrate_instant() {
        int _link;
        int n2 = _link = this.seekable ? this.current_link : 0;
        if (this.samptrack == 0.0f) {
            return -1;
        }
        int ret = (int)((double)(this.bittrack / this.samptrack * (float)this.vi[_link].rate) + 0.5);
        this.bittrack = 0.0f;
        this.samptrack = 0.0f;
        return ret;
    }

    public int serialnumber(int i2) {
        return i2 >= this.links ? -1 : (!this.seekable && i2 >= 0 ? this.serialnumber(-1) : (i2 < 0 ? this.current_serialno : this.serialnos[i2]));
    }

    public long raw_total(int i2) {
        if (this.seekable && i2 < this.links) {
            if (i2 >= 0) {
                return this.offsets[i2 + 1] - this.offsets[i2];
            }
            long acc = 0L;
            int j2 = 0;
            while (j2 < this.links) {
                acc += this.raw_total(j2);
                ++j2;
            }
            return acc;
        }
        return -1L;
    }

    public long pcm_total(int i2) {
        if (this.seekable && i2 < this.links) {
            if (i2 >= 0) {
                return this.pcmlengths[i2];
            }
            long acc = 0L;
            int j2 = 0;
            while (j2 < this.links) {
                acc += this.pcm_total(j2);
                ++j2;
            }
            return acc;
        }
        return -1L;
    }

    public float time_total(int i2) {
        if (this.seekable && i2 < this.links) {
            if (i2 >= 0) {
                return (float)this.pcmlengths[i2] / (float)this.vi[i2].rate;
            }
            float acc = 0.0f;
            int j2 = 0;
            while (j2 < this.links) {
                acc += this.time_total(j2);
                ++j2;
            }
            return acc;
        }
        return -1.0f;
    }

    public int raw_seek(int pos) {
        if (!this.seekable) {
            return -1;
        }
        if (pos >= 0 && (long)pos <= this.offsets[this.links]) {
            this.pcm_offset = -1L;
            this.decode_clear();
            this.seek_helper(pos);
            switch (this.process_packet(1)) {
                case -1: {
                    this.pcm_offset = -1L;
                    this.decode_clear();
                    return -1;
                }
                case 0: {
                    this.pcm_offset = this.pcm_total(-1);
                    return 0;
                }
            }
            while (true) {
                switch (this.process_packet(0)) {
                    case -1: {
                        this.pcm_offset = -1L;
                        this.decode_clear();
                        return -1;
                    }
                    case 0: {
                        return 0;
                    }
                }
            }
        }
        this.pcm_offset = -1L;
        this.decode_clear();
        return -1;
    }

    /*
     * Unable to fully structure code
     */
    public int pcm_seek(long pos) {
        block9: {
            total = this.pcm_total(-1);
            if (!this.seekable) {
                return -1;
            }
            if (pos < 0L || pos > total) break block9;
            i19 = this.links - 1;
            while (i19 >= 0) {
                if (pos >= (total -= this.pcmlengths[i19])) break;
                --i19;
            }
            target = pos - total;
            _index = this.offsets[i19 + 1];
            begin = this.offsets[i19];
            best = (int)begin;
            og = new Page();
            while (begin < _index) {
                bisect = _index - begin < 8500L ? begin : (_index + begin) / 2L;
                this.seek_helper(bisect);
                ret = this.get_next_page(og, _index - bisect);
                if (ret == -1) {
                    _index = bisect;
                    continue;
                }
                granulepos = og.granulepos();
                if (granulepos < target) {
                    best = ret;
                    begin = this.offset;
                    continue;
                }
                _index = bisect;
            }
            if (this.raw_seek(best) != 0) {
                this.pcm_offset = -1L;
                this.decode_clear();
                return -1;
            }
            if (this.pcm_offset >= pos) {
                this.pcm_offset = -1L;
                this.decode_clear();
                return -1;
            }
            if (pos <= this.pcm_total(-1)) ** GOTO lbl52
            this.pcm_offset = -1L;
            this.decode_clear();
            return -1;
lbl-1000:
            // 1 sources

            {
                i20 = (int)(pos - this.pcm_offset);
                _pcm = new float[1][][];
                i21 = new int[this.getInfo((int)-1).channels];
                samples = this.vd.synthesis_pcmout(_pcm, i21);
                if (samples > i20) {
                    samples = i20;
                }
                this.vd.synthesis_read(samples);
                this.pcm_offset += (long)samples;
                if (samples >= i20 || this.process_packet(1) != 0) continue;
                this.pcm_offset = this.pcm_total(-1);
lbl52:
                // 3 sources

                ** while (this.pcm_offset < pos)
            }
lbl53:
            // 1 sources

            return 0;
        }
        this.pcm_offset = -1L;
        this.decode_clear();
        return -1;
    }

    int time_seek(float seconds) {
        long pcm_total = this.pcm_total(-1);
        float time_total = this.time_total(-1);
        if (!this.seekable) {
            return -1;
        }
        if (seconds >= 0.0f && seconds <= time_total) {
            int i8 = this.links - 1;
            while (i8 >= 0) {
                pcm_total -= this.pcmlengths[i8];
                if (seconds >= (time_total -= this.time_total(i8))) break;
                --i8;
            }
            long target = (long)((float)pcm_total + (seconds - time_total) * (float)this.vi[i8].rate);
            return this.pcm_seek(target);
        }
        this.pcm_offset = -1L;
        this.decode_clear();
        return -1;
    }

    public long raw_tell() {
        return this.offset;
    }

    public long pcm_tell() {
        return this.pcm_offset;
    }

    public float time_tell() {
        int link = -1;
        long pcm_total = 0L;
        float time_total = 0.0f;
        if (this.seekable) {
            pcm_total = this.pcm_total(-1);
            time_total = this.time_total(-1);
            link = this.links - 1;
            while (link >= 0) {
                time_total -= this.time_total(link);
                if (this.pcm_offset >= (pcm_total -= this.pcmlengths[link])) break;
                --link;
            }
        }
        return time_total + (float)(this.pcm_offset - pcm_total) / (float)this.vi[link].rate;
    }

    public Info getInfo(int link) {
        return this.seekable ? (link < 0 ? (this.decode_ready ? this.vi[this.current_link] : null) : (link >= this.links ? null : this.vi[link])) : (this.decode_ready ? this.vi[0] : null);
    }

    public Comment getComment(int link) {
        return this.seekable ? (link < 0 ? (this.decode_ready ? this.vc[this.current_link] : null) : (link >= this.links ? null : this.vc[link])) : (this.decode_ready ? this.vc[0] : null);
    }

    int host_is_big_endian() {
        return 1;
    }

    int read(byte[] buffer, int length, int bigendianp, int word, int sgned, int[] bitstream) {
        int host_endian = this.host_is_big_endian();
        int index = 0;
        while (true) {
            if (this.decode_ready) {
                float[][][] _pcm = new float[1][][];
                int[] _index = new int[this.getInfo((int)-1).channels];
                int samples = this.vd.synthesis_pcmout(_pcm, _index);
                float[][] pcm = _pcm[0];
                if (samples != 0) {
                    int channels = this.getInfo((int)-1).channels;
                    int bytespersample = word * channels;
                    if (samples > length / bytespersample) {
                        samples = length / bytespersample;
                    }
                    if (word == 1) {
                        int off = sgned != 0 ? 0 : 128;
                        int j2 = 0;
                        while (j2 < samples) {
                            int i2 = 0;
                            while (i2 < channels) {
                                int val = (int)((double)pcm[i2][_index[i2] + j2] * 128.0 + 0.5);
                                if (val > 127) {
                                    val = 127;
                                } else if (val < -128) {
                                    val = -128;
                                }
                                buffer[index++] = (byte)(val + off);
                                ++i2;
                            }
                            ++j2;
                        }
                    } else {
                        int off;
                        int n2 = off = sgned != 0 ? 0 : 32768;
                        if (host_endian == bigendianp) {
                            if (sgned != 0) {
                                int j3 = 0;
                                while (j3 < channels) {
                                    int i3 = _index[j3];
                                    int dest = j3;
                                    int j1 = 0;
                                    while (j1 < samples) {
                                        int val = (int)((double)pcm[j3][i3 + j1] * 32768.0 + 0.5);
                                        if (val > Short.MAX_VALUE) {
                                            val = Short.MAX_VALUE;
                                        } else if (val < Short.MIN_VALUE) {
                                            val = Short.MIN_VALUE;
                                        }
                                        buffer[dest] = (byte)(val >>> 8);
                                        buffer[dest + 1] = (byte)val;
                                        dest += channels * 2;
                                        ++j1;
                                    }
                                    ++j3;
                                }
                            } else {
                                int j4 = 0;
                                while (j4 < channels) {
                                    float[] f21 = pcm[j4];
                                    int dest = j4;
                                    int j1 = 0;
                                    while (j1 < samples) {
                                        int val = (int)((double)f21[j1] * 32768.0 + 0.5);
                                        if (val > Short.MAX_VALUE) {
                                            val = Short.MAX_VALUE;
                                        } else if (val < Short.MIN_VALUE) {
                                            val = Short.MIN_VALUE;
                                        }
                                        buffer[dest] = (byte)(val + off >>> 8);
                                        buffer[dest + 1] = (byte)(val + off);
                                        dest += channels * 2;
                                        ++j1;
                                    }
                                    ++j4;
                                }
                            }
                        } else if (bigendianp != 0) {
                            int j5 = 0;
                            while (j5 < samples) {
                                int i4 = 0;
                                while (i4 < channels) {
                                    int val = (int)((double)pcm[i4][j5] * 32768.0 + 0.5);
                                    if (val > Short.MAX_VALUE) {
                                        val = Short.MAX_VALUE;
                                    } else if (val < Short.MIN_VALUE) {
                                        val = Short.MIN_VALUE;
                                    }
                                    buffer[index++] = (byte)((val += off) >>> 8);
                                    buffer[index++] = (byte)val;
                                    ++i4;
                                }
                                ++j5;
                            }
                        } else {
                            int j6 = 0;
                            while (j6 < samples) {
                                int i5 = 0;
                                while (i5 < channels) {
                                    int val = (int)((double)pcm[i5][j6] * 32768.0 + 0.5);
                                    if (val > Short.MAX_VALUE) {
                                        val = Short.MAX_VALUE;
                                    } else if (val < Short.MIN_VALUE) {
                                        val = Short.MIN_VALUE;
                                    }
                                    buffer[index++] = (byte)(val += off);
                                    buffer[index++] = (byte)(val >>> 8);
                                    ++i5;
                                }
                                ++j6;
                            }
                        }
                    }
                    this.vd.synthesis_read(samples);
                    this.pcm_offset += (long)samples;
                    if (bitstream != null) {
                        bitstream[0] = this.current_link;
                    }
                    return samples * bytespersample;
                }
            }
            switch (this.process_packet(1)) {
                case -1: {
                    return -1;
                }
                case 0: {
                    return 0;
                }
            }
        }
    }

    public Info[] getInfo() {
        return this.vi;
    }

    public Comment[] getComment() {
        return this.vc;
    }

    public void close() throws IOException {
        this.datasource.close();
    }

    class SeekableInputStream
    extends InputStream {
        RandomAccessFile raf = null;
        final String mode = "r";

        SeekableInputStream(String file) throws IOException {
            this.raf = new RandomAccessFile(file, "r");
        }

        @Override
        public int read() throws IOException {
            return this.raf.read();
        }

        @Override
        public int read(byte[] buf) throws IOException {
            return this.raf.read(buf);
        }

        @Override
        public int read(byte[] buf, int s2, int len) throws IOException {
            return this.raf.read(buf, s2, len);
        }

        @Override
        public long skip(long n2) throws IOException {
            return this.raf.skipBytes((int)n2);
        }

        public long getLength() throws IOException {
            return this.raf.length();
        }

        public long tell() throws IOException {
            return this.raf.getFilePointer();
        }

        @Override
        public int available() throws IOException {
            return this.raf.length() == this.raf.getFilePointer() ? 0 : 1;
        }

        @Override
        public void close() throws IOException {
            this.raf.close();
        }

        @Override
        public synchronized void mark(int m2) {
        }

        @Override
        public synchronized void reset() throws IOException {
        }

        @Override
        public boolean markSupported() {
            return false;
        }

        public void seek(long pos) throws IOException {
            this.raf.seek(pos);
        }
    }
}

