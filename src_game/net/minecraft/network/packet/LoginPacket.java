/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class LoginPacket
extends Packet {
    public byte protocolVer;
    public String usernameOrServerName;
    public String mppassOrMotd;
    public byte userStatus;

    public LoginPacket(byte pvn, String uname, String ass, byte usrStat) {
        this.protocolVer = pvn;
        this.usernameOrServerName = uname;
        this.mppassOrMotd = ass;
        this.userStatus = usrStat;
    }

    public LoginPacket() {
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.protocolVer = dis.readByte();
        this.usernameOrServerName = LoginPacket.readString(dis);
        this.mppassOrMotd = LoginPacket.readString(dis);
        this.userStatus = dis.readByte();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.protocolVer);
        LoginPacket.writeString(dos, this.usernameOrServerName);
        LoginPacket.writeString(dos, this.mppassOrMotd);
        dos.writeByte(this.userStatus);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleLogin(this);
    }

    @Override
    public int size() {
        return 130;
    }
}

