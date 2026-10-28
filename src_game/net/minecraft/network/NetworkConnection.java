/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class NetworkConnection {
    public static final int BUFFER_SIZE = 5120;
    public static final int SOCKET_TIMEOUT = 30000;
    public static final long THREADS_SLEEP_TIME = 2L;
    public static final Object SYNC = new Object();
    private Object sendQueueLock = new Object();
    private Socket socket;
    private final SocketAddress remoteAddress;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean running = true;
    private List<Packet> readQueue = Collections.synchronizedList(new ArrayList());
    private List<Packet> writeQueue = Collections.synchronizedList(new ArrayList());
    private PacketListener callback;
    private boolean threadTerminating = false;
    private Thread readThread;
    private Thread writeThread;
    private boolean closing = false;
    private String disconnectReason = "";
    private int timeSinceLastRead = 0;
    private int sendQueueByteLength = 0;
    public long readBytesCount = 0L;
    public long writtenBytesCount = 0L;

    public NetworkConnection(Socket sock, String connName, PacketListener callback) throws IOException {
        this.socket = sock;
        this.remoteAddress = sock.getRemoteSocketAddress();
        this.callback = callback;
        try {
            sock.setTcpNoDelay(true);
            sock.setSoTimeout(30000);
            sock.setTrafficClass(24);
            sock.setKeepAlive(true);
            sock.setReuseAddress(true);
        }
        catch (SocketException e2) {
            System.err.println("Warning: " + e2);
        }
        InputStream is = sock.getInputStream();
        OutputStream os = sock.getOutputStream();
        this.input = new DataInputStream(is);
        this.output = new DataOutputStream(new BufferedOutputStream(os, 5120));
        this.readThread = new Thread(String.valueOf(connName) + " read thread"){

            @Override
            public void run() {
                while (NetworkConnection.this.running && !NetworkConnection.this.threadTerminating) {
                    while (NetworkConnection.this.readPacket()) {
                    }
                    try {
                        Thread.sleep(2L);
                    }
                    catch (InterruptedException interruptedException) {}
                }
            }
        };
        this.writeThread = new Thread(String.valueOf(connName) + " write thread"){

            @Override
            public void run() {
                while (NetworkConnection.this.running) {
                    while (NetworkConnection.this.writePacket()) {
                    }
                    try {
                        Thread.sleep(2L);
                    }
                    catch (InterruptedException interruptedException) {
                        // empty catch block
                    }
                    try {
                        if (NetworkConnection.this.output == null) continue;
                        NetworkConnection.this.output.flush();
                    }
                    catch (IOException e2) {
                        if (!NetworkConnection.this.closing) {
                            NetworkConnection.this.handleException(e2);
                        }
                        e2.printStackTrace();
                    }
                }
            }
        };
        this.readThread.start();
        this.writeThread.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addToQueue(Packet packet) {
        if (!this.threadTerminating) {
            Object object = this.sendQueueLock;
            synchronized (object) {
                this.sendQueueByteLength += packet.size() + 1;
                this.writeQueue.add(packet);
            }
        }
    }

    private boolean readPacket() {
        boolean read = false;
        try {
            Packet packet = Packet.readPacket(this.input);
            if (packet != null) {
                this.readBytesCount += (long)(packet.size() + 1);
                this.readQueue.add(packet);
                read = true;
            } else {
                this.disconnect("End of stream");
            }
            return read;
        }
        catch (Exception e2) {
            if (!this.closing) {
                this.handleException(e2);
            }
            return false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean writePacket() {
        boolean write = false;
        try {
            if (!this.writeQueue.isEmpty()) {
                Packet packet;
                Object object = this.sendQueueLock;
                synchronized (object) {
                    packet = this.writeQueue.remove(0);
                    this.sendQueueByteLength -= packet.size() + 1;
                }
                Packet.writePacket(packet, this.output);
                this.writtenBytesCount += (long)(packet.size() + 1);
                write = true;
            }
            return write;
        }
        catch (Exception e2) {
            if (!this.closing) {
                this.handleException(e2);
            }
            return false;
        }
    }

    public void handleException(Exception e2) {
        e2.printStackTrace();
        this.disconnect("Internal Exception: " + e2.toString());
    }

    public void disconnect(String reason) {
        if (this.running) {
            this.closing = true;
            this.disconnectReason = reason;
            new Thread(){

                @Override
                public void run() {
                    try {
                        Thread.sleep(5000L);
                        if (NetworkConnection.this.readThread.isAlive()) {
                            try {
                                NetworkConnection.this.readThread.stop();
                            }
                            catch (Throwable throwable) {
                                // empty catch block
                            }
                        }
                        if (NetworkConnection.this.writeThread.isAlive()) {
                            try {
                                NetworkConnection.this.writeThread.stop();
                            }
                            catch (Throwable throwable) {}
                        }
                    }
                    catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }.start();
            this.running = false;
            try {
                this.input.close();
                this.input = null;
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            try {
                this.output.close();
                this.output = null;
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            try {
                this.socket.close();
                this.socket = null;
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }

    public void closeConnection() {
        this.interrupt();
        this.threadTerminating = true;
        this.readThread.interrupt();
        new Thread(){

            @Override
            public void run() {
                try {
                    Thread.sleep(2000L);
                    if (NetworkConnection.this.running) {
                        NetworkConnection.this.writeThread.interrupt();
                        NetworkConnection.this.disconnect("Connection closed");
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }.start();
    }

    public void tick() {
        if (this.sendQueueByteLength > 0x100000) {
            this.disconnect("Buffer overflow");
        }
        if (this.readQueue.isEmpty()) {
            if (this.timeSinceLastRead++ == 1200) {
                this.disconnect("Timed out");
            }
        } else {
            this.timeSinceLastRead = 0;
        }
        int handleCount = 1000;
        while (!this.readQueue.isEmpty() && handleCount-- >= 0) {
            Packet packet = this.readQueue.remove(0);
            packet.handle(this.callback);
        }
        if (this.closing && this.readQueue.isEmpty()) {
            this.callback.disconnect(this.disconnectReason);
        }
    }

    public void interrupt() {
        this.readThread.interrupt();
        this.writeThread.interrupt();
    }

    public void setCallback(PacketListener callback) {
        this.callback = callback;
    }

    public SocketAddress getRemoteAddress() {
        return this.remoteAddress;
    }
}

