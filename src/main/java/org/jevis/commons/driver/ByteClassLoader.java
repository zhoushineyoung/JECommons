/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.driver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisFile;

/**
 *
 * @author broder
 */
public class ByteClassLoader extends ClassLoader {

    private Map<String, byte[]> classes = new HashMap<String, byte[]>();

    public ByteClassLoader(byte[] classBytes) {
        super(ByteClassLoader.class.getClassLoader());
        try {
            JarInputStream jis = new JarInputStream(new ByteArrayInputStream(classBytes));
            JarEntry je = null;
            String entryName = null;
            while ((je = jis.getNextJarEntry()) != null) {
                entryName = je.getName();
                if (je.getName().endsWith(".class")) {
                    byte[] tmpclassBytes = readClass(jis);
                    String canonicalName = entryName.replaceAll("/", ".").replaceAll(".class", "");
                    classes.put(canonicalName, tmpclassBytes);
                }
            }
            jis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] readClass(InputStream stream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (true) {
            int qwe = stream.read();
            if (qwe == -1) {
                break;
            }
            baos.write(qwe);
        }
        return baos.toByteArray();
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        try {
            return this.getParent().loadClass(name);
        } catch (ClassNotFoundException e) {
            return findClass(name);
        }
    }

    public Class findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = classes.get(name);
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    public static Class loadDriver(JEVisFile parserFile, String className) throws JEVisException, MalformedURLException, ClassNotFoundException, IOException {
        byte[] bytes = parserFile.getBytes();
        ByteClassLoader loadDriver = new ByteClassLoader(bytes);
        return loadDriver.loadClass(className);
    }

}
