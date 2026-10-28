/*
 * Decompiled with CFR 0.152.
 */
package paulscode.sound;

public class SoundSystemLogger {
    public void message(String message, int indent) {
        String spacer = "";
        int x2 = 0;
        while (x2 < indent) {
            spacer = String.valueOf(spacer) + "    ";
            ++x2;
        }
        String messageText = String.valueOf(spacer) + message;
        System.out.println(messageText);
    }

    public void importantMessage(String message, int indent) {
        String spacer = "";
        int x2 = 0;
        while (x2 < indent) {
            spacer = String.valueOf(spacer) + "    ";
            ++x2;
        }
        String messageText = String.valueOf(spacer) + message;
        System.out.println(messageText);
    }

    public boolean errorCheck(boolean error, String classname, String message, int indent) {
        if (error) {
            this.errorMessage(classname, message, indent);
        }
        return error;
    }

    public void errorMessage(String classname, String message, int indent) {
        String spacer = "";
        int x2 = 0;
        while (x2 < indent) {
            spacer = String.valueOf(spacer) + "    ";
            ++x2;
        }
        String headerLine = String.valueOf(spacer) + "Error in class '" + classname + "'";
        String messageText = "    " + spacer + message;
        System.out.println(headerLine);
        System.out.println(messageText);
    }

    public void printStackTrace(Exception e2, int indent) {
        StackTraceElement[] stack;
        this.printExceptionMessage(e2, indent);
        this.importantMessage("STACK TRACE:", indent);
        if (e2 != null && (stack = e2.getStackTrace()) != null) {
            int x2 = 0;
            while (x2 < stack.length) {
                StackTraceElement line = stack[x2];
                if (line != null) {
                    this.message(line.toString(), indent + 1);
                }
                ++x2;
            }
        }
    }

    public void printExceptionMessage(Exception e2, int indent) {
        this.importantMessage("ERROR MESSAGE:", indent);
        if (e2.getMessage() == null) {
            this.message("(none)", indent + 1);
        } else {
            this.message(e2.getMessage(), indent + 1);
        }
    }
}

