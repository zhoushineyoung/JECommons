/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing.inputHandler;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author bf
 */
public class InputStreamHandler extends InputHandler {

    public InputStreamHandler(InputStream input) {
        super(input);
    }

    @Override
    public void convertInput() {
//        byte[] contents = new byte[1024];
//
//        int bytesRead = 0;
//        String strFileContents = null;
//        try {
//            while ((bytesRead = ((InputStream) _rawInput).read(contents)) != -1) {
//                strFileContents = new String(contents, 0, bytesRead);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(InputStreamHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.print(strFileContents);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = ((InputStream) _rawInput).read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
        } catch (IOException ex) {
            Logger.getLogger(InputStreamHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        InputStream zippedCopy = new ByteArrayInputStream(baos.toByteArray());
        InputStream unzipedCopy = new ByteArrayInputStream(baos.toByteArray());
        try {
            boolean isZiped = false;
            ZipInputStream zin = new ZipInputStream((InputStream) zippedCopy);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                isZiped = true;
                StringBuilder sb = new StringBuilder();
                System.out.println("Unzipping " + ze.getName());
                List<String> tmp = new ArrayList<String>();
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    sb.append((char) c);
                }
                System.out.println("input," + sb.toString());
                zin.closeEntry();
                _inputStream.add(new ByteArrayInputStream(sb.toString().getBytes()));
            }
//            zin.close();
            IOUtils.closeQuietly(zin);
            if (!isZiped) {
//                ((InputStream) _rawInput).reset();
                _inputStream.add(new BufferedInputStream(unzipedCopy));
            }
        } catch (IOException ex) {
            Logger.getLogger(InputStreamHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
