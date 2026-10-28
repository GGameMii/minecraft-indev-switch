/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.packet.PlayerFlyingPacket;

public class PlayerLookPacket
extends PlayerFlyingPacket {
    public PlayerLookPacket() {
        this.rotating = true;
    }

    public PlayerLookPacket(float yaw, float pitch, boolean onGround) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.rotating = true;
    }

    @Override
    public void read(DataInputStream in) throws IOException {
        this.yaw = in.readFloat();
        this.pitch = in.readFloat();
        super.read(in);
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeFloat(this.yaw);
        out.writeFloat(this.pitch);
        super.write(out);
    }

    @Override
    public int size() {
        return 9;
    }
}

