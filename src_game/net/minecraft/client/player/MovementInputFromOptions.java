/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.player;

import net.minecraft.client.GameOptions;
import net.minecraft.client.player.MovementInput;

public final class MovementInputFromOptions
extends MovementInput {
    private boolean[] movementKeyStates = new boolean[10];
    private GameOptions gameSettings;

    public MovementInputFromOptions(GameOptions gameSettings) {
        this.gameSettings = gameSettings;
    }

    @Override
    public final void checkKeyForMovementInput(int i1, boolean z2) {
        int b3 = -1;
        if (i1 == this.gameSettings.keyBindForward.keyCode) {
            b3 = 0;
        }
        if (i1 == this.gameSettings.keyBindBack.keyCode) {
            b3 = 1;
        }
        if (i1 == this.gameSettings.keyBindLeft.keyCode) {
            b3 = 2;
        }
        if (i1 == this.gameSettings.keyBindRight.keyCode) {
            b3 = 3;
        }
        if (i1 == this.gameSettings.keyBindJump.keyCode) {
            b3 = 4;
        }
        if (b3 >= 0) {
            this.movementKeyStates[b3] = z2;
        }
    }

    @Override
    public final void resetKeyState() {
        int i1 = 0;
        while (i1 < 10) {
            this.movementKeyStates[i1] = false;
            ++i1;
        }
    }

    public static float padForward = 0.0f;
    public static float padStrafe = 0.0f;
    public static boolean padJump = false;

    @Override
    public final void updatePlayerMoveState() {
        this.moveStrafe = 0.0f;
        this.moveForward = 0.0f;
        if (this.movementKeyStates[0]) {
            this.moveForward += 1.0f;
        }
        if (this.movementKeyStates[1]) {
            this.moveForward -= 1.0f;
        }
        if (this.movementKeyStates[2]) {
            this.moveStrafe += 1.0f;
        }
        if (this.movementKeyStates[3]) {
            this.moveStrafe -= 1.0f;
        }
        this.moveForward += padForward;
        this.moveStrafe += padStrafe;
        this.jump = this.movementKeyStates[4] || padJump;
    }
}

