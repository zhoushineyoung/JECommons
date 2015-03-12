/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author bf
 */
public class LoadingDriver {

    public Object loadClass(String fileName, String className) {
        Object newObject = null;
        try {
            File file = new File(fileName);
            URL[] urls = new URL[1];
            urls[0] = file.toURI().toURL();
            ClassLoader cl = new URLClassLoader(urls);
            Class c = cl.loadClass(className);
            newObject = c.newInstance();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LoadingDriver.class.getName()).log(Level.ERROR, ex.getMessage());
        } catch (InstantiationException ex) {
            Logger.getLogger(LoadingDriver.class.getName()).log(Level.ERROR, ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoadingDriver.class.getName()).log(Level.ERROR, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoadingDriver.class.getName()).log(Level.ERROR, "Class not fount for file: " + fileName + " with class: " + className);
            Logger.getLogger(LoadingDriver.class.getName()).log(Level.ERROR, ex.getMessage());

        }
        return newObject;
    }
}
