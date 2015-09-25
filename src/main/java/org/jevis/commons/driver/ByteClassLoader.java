/**
 * Copyright (C) 2015 Envidatec GmbH <info@envidatec.com>
 *
 * This file is part of JECommons.
 *
 * JECommons is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation in version 3.
 *
 * JECommons is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * JECommons. If not, see <http://www.gnu.org/licenses/>.
 *
 * JECommons is part of the OpenJEVis project, further project information are
 * published at <http://www.OpenJEVis.org/>.
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

    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        try {
            return this.getParent().loadClass(name);
        } catch (ClassNotFoundException e) {
            return findClass(name);
        }
    }

    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = classes.get(name);
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    public static Class loadDriver(JEVisFile parserFile, String className) throws JEVisException, MalformedURLException, ClassNotFoundException, IOException {
        System.out.println("load Driver,"+className);
        byte[] bytes = parserFile.getBytes();
        ByteClassLoader loadDriver = new ByteClassLoader(bytes);
        return loadDriver.loadClass(className);
    }

}
