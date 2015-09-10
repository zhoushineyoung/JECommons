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
package org.jevis.commons.drivermanagment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import org.jevis.api.JEVisClass;
import org.jevis.api.JEVisException;
import org.jevis.commons.json.JsonFactory;
import org.jevis.commons.json.JsonJEVisClass;

/**
 * Very basic JEVisClass exporter. The implemnetation will be replaced in the
 * future.
 *
 * @author Florian Simon
 */
public class ClassExporter {

    public ClassExporter(File targetFile, List<JEVisClass> classes) {

        List<File> classFiles = new ArrayList<>();

        for (JEVisClass jclass : classes) {
            try {
                classFiles.addAll(filesForClass(jclass));
            } catch (JEVisException ex) {
                Logger.getLogger(ClassExporter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClassExporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        writeFile(targetFile, classFiles);

    }

    private List<File> filesForClass(JEVisClass jclass) throws JEVisException, IOException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        List<File> classFiles = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonJEVisClass jsonClass = JsonFactory.buildJEVisClassComplete(jclass);

        String s = gson.toJson(jsonClass);

        FileOutputStream outputStream;
        File temp = new File(tmpdir + "/" + jclass.getName() + ".json");
        System.out.println("File name: " + temp);

        outputStream = new FileOutputStream(temp);
        outputStream.write(s.getBytes());
        outputStream.close();
        classFiles.add(temp);

        if (jclass.getIcon() != null) {
            File tempIcon = new File(tmpdir + "/" + jclass.getName() + ".png");
            ImageIO.write(jclass.getIcon(), "png", tempIcon);
            classFiles.add(tempIcon);
            tempIcon.deleteOnExit();
        }
        temp.deleteOnExit();

        return classFiles;
    }

    private void writeFile(File targetfile, List<File> files) {
        try {
            FileOutputStream fos = new FileOutputStream(targetfile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (File file : files) {
                addToZipFile(file, zos);
            }

            zos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addToZipFile(File file, ZipOutputStream zos) throws FileNotFoundException, IOException {

        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(file.getName());
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }
}
