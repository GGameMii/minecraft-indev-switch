/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.e;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.client.e.a;

public final class b {
    private Random a = new Random();
    private Map b = new HashMap();
    private int c = 0;

    public final a a(String string, File object) {
        try {
            String string2 = string;
            string = string.substring(0, string.indexOf("."));
            while (Character.isDigit(string.charAt(string.length() - 1))) {
                string = string.substring(0, string.length() - 1);
            }
            if (!this.b.containsKey(string = string.replaceAll("/", "."))) {
                this.b.put(string, new ArrayList());
            }
            object = new a(string2, ((File)object).toURI().toURL());
            ((List)this.b.get(string)).add(object);
            ++this.c;
            return object;
        }
        catch (MalformedURLException malformedURLException) {
            MalformedURLException malformedURLException2 = malformedURLException;
            malformedURLException.printStackTrace();
            throw new RuntimeException(malformedURLException2);
        }
    }

    public final a a(String object) {
        if ((object = (List)this.b.get(object)) == null) {
            return null;
        }
        return (a)object.get(this.a.nextInt(object.size()));
    }
}

