package net.minecraft.client.controller;

public final class SwitchKeyboard {
    public interface Callback {
        void onTextEntered(String text);
    }

    private static NativeKeyboardHandler nativeHandler = null;

    public interface NativeKeyboardHandler {
        String showKeyboard(String guideText, String initialText, int maxLength);
    }

    public static void setNativeHandler(NativeKeyboardHandler handler) {
        nativeHandler = handler;
    }

    /**
     * Opens the software keyboard (Swkbd on Nintendo Switch, or desktop fallback).
     */
    public static void open(final String guideText, final String initialText, final int maxLength, final Callback callback) {
        if (callback == null) return;

        if (nativeHandler != null) {
            try {
                String result = nativeHandler.showKeyboard(guideText, initialText != null ? initialText : "", maxLength);
                if (result != null) {
                    callback.onTextEntered(result);
                    return;
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        // Desktop test fallback: if on PC and Swing is available, show an input dialog
        try {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        String input = javax.swing.JOptionPane.showInputDialog(null, guideText, initialText != null ? initialText : "");
                        if (input != null) {
                            if (input.length() > maxLength) {
                                input = input.substring(0, maxLength);
                            }
                            callback.onTextEntered(input);
                        }
                    } catch (Throwable ignored) {
                    }
                }
            });
        } catch (Throwable ignored) {
        }
    }
}
