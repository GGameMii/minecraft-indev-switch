/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import net.minecraft.client.d;

public final class j
extends Thread {
    private File a;
    private d b;
    private boolean c = false;

    public j(File file, d d2) {
        this.b = d2;
        this.setName("Resource download thread");
        this.setDaemon(true);
        this.a = new File(file, "resources/");
        if (!this.a.exists() && !this.a.mkdirs()) {
            throw new RuntimeException("The working directory could not be created: " + this.a);
        }
    }

    public final void run() {
        try {
            Object object;
            ArrayList<String> arrayList = new ArrayList<String>();
            URL uRL = new URL("http://www.minecraft.net/resources/");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRL.openStream()));
            while ((object = bufferedReader.readLine()) != null) {
                arrayList.add((String)object);
            }
            bufferedReader.close();
            for (int i2 = 0; i2 < arrayList.size(); ++i2) {
                block13: {
                    Object object2 = (String)arrayList.get(i2);
                    Object object3 = uRL;
                    object = this;
                    try {
                        String string;
                        object2 = ((String)object2).split(",");
                        String string2 = object2[0];
                        int n2 = Integer.parseInt(object2[1]);
                        Long.parseLong(object2[2]);
                        object2 = new File(((j)object).a, string2);
                        if (!((File)object2).exists() || ((File)object2).length() != (long)n2) {
                            ((File)object2).getParentFile().mkdirs();
                            string = string2.replaceAll(" ", "%20");
                            super.a(new URL((URL)object3, string), (File)object2);
                            if (((j)object).c) break block13;
                        }
                        object3 = string2;
                        object = ((j)object).b;
                        int n3 = ((String)object3).indexOf("/");
                        string = ((String)object3).substring(0, n3);
                        object3 = ((String)object3).substring(n3 + 1);
                        if (string.equalsIgnoreCase("sound")) {
                            ((d)object).x.a((String)object3, (File)object2);
                        } else if (string.equalsIgnoreCase("newsound")) {
                            ((d)object).x.a((String)object3, (File)object2);
                        } else if (string.equalsIgnoreCase("music")) {
                            ((d)object).x.b((String)object3, (File)object2);
                        }
                    }
                    catch (Exception exception) {
                        object2 = exception;
                        exception.printStackTrace();
                    }
                }
                if (!this.c) continue;
                return;
            }
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    private void a(URL object, File object2) {
        int n2;
        byte[] byArray = new byte[4096];
        object = new DataInputStream(((URL)object).openStream());
        object2 = new DataOutputStream(new FileOutputStream((File)object2));
        while ((n2 = ((DataInputStream)object).read(byArray)) >= 0) {
            ((DataOutputStream)object2).write(byArray, 0, n2);
            if (!this.c) continue;
            return;
        }
        ((FilterInputStream)object).close();
        ((FilterOutputStream)object2).close();
    }

    public final void a() {
        this.c = true;
    }
}

