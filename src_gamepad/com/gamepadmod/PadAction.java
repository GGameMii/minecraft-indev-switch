/*
 * Decompiled with CFR 0.152.
 */
package com.gamepadmod;

enum PadAction {
    JUMP("jump", "Jump", 0),
    BREAK("break", "Break", 111),
    PLACE("place", "Place", 110),
    INVENTORY("inventory", "Inventory", 3),
    BUILD("build", "Build Menu", 2),
    DROP("drop", "Drop Item", 101),
    CHAT("chat", "Chat", 100),
    TOGGLE_FOG("togglefog", "Toggle Fog", -1),
    THIRD_PERSON("thirdperson", "Perspective", 9),
    SAVE_POS("savepos", "Save Location", -1),
    LOAD_POS("loadpos", "Load Location", -1),
    HOTBAR_PREV("hotbarprev", "Prev Slot", 4),
    HOTBAR_NEXT("hotbarnext", "Next Slot", 5),
    BACK("back", "Back (Menus)", 1),
    PAUSE("pause", "Pause", 7),
    CONFIRM("confirm", "Confirm", 0),
    RIGHT_CLICK("rightclick", "Split / One", 2),
    QUICK_MOVE("quickmove", "Quick Move", 4),
    QUICK_MOVE_RIGHT("quickmoveright", "Quick Move R", 5);

    final String id;
    final String label;
    final int defaultCode;

    private PadAction(String string2, String string3, int n2) {
        this.id = string2;
        this.label = string3;
        this.defaultCode = n2;
    }
}

