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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
                String[] pathStream = getPathTokens(_filePattern);
                DateTime folderTime = getFolderTime(ze.getName(), pathStream);
                boolean match = matchDateString(ze.getName(), _filePattern);
                boolean isLater = folderTime.isAfter(_lastReadout);
                if (match && isLater) {
                    System.out.println("Unzipping " + ze.getName());
                    List<String> tmp = new ArrayList<String>();
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        sb.append((char) c);
                    }
                    System.out.println("input," + sb.toString());
                    _inputStream.add(new ByteArrayInputStream(sb.toString().getBytes()));
                }
                zin.closeEntry();
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

    private static boolean matchDateString(String currentFolder, String nextToken) {
        String[] substringsBetween = StringUtils.substringsBetween(nextToken, "${D:", "}");
        for (int i = 0; i < substringsBetween.length; i++) {
            nextToken = nextToken.replace("${D:" + substringsBetween[i] + "}", ".{" + substringsBetween[i].length() + "}");
        }
        Pattern p = Pattern.compile(nextToken);
        Matcher m = p.matcher(currentFolder);
        return m.matches();
    }

    private DateTime getFolderTime(String name, String[] pathStream) {
        String compactDateString = getCompactDateString(name, pathStream);
        String compactDataFormatString = getCompactDateFormatString(name, pathStream);

        DateTimeFormatter dtf = DateTimeFormat.forPattern(compactDataFormatString);

        DateTime parseDateTime = dtf.parseDateTime(compactDateString);
        if (parseDateTime.year().get() == parseDateTime.year().getMinimumValue()) {
            parseDateTime = parseDateTime.year().withMaximumValue();
        }
        if (parseDateTime.monthOfYear().get() == parseDateTime.monthOfYear().getMinimumValue()) {
            parseDateTime = parseDateTime.monthOfYear().withMaximumValue();
        }
        if (parseDateTime.dayOfMonth().get() == parseDateTime.dayOfMonth().getMinimumValue()) {
            parseDateTime = parseDateTime.dayOfMonth().withMaximumValue();
        }
        if (parseDateTime.hourOfDay().get() == parseDateTime.hourOfDay().getMinimumValue()) {
            parseDateTime = parseDateTime.hourOfDay().withMaximumValue();
        }
        if (parseDateTime.minuteOfHour().get() == parseDateTime.minuteOfHour().getMinimumValue()) {
            parseDateTime = parseDateTime.minuteOfHour().withMaximumValue();
        }
        if (parseDateTime.secondOfMinute().get() == parseDateTime.secondOfMinute().getMinimumValue()) {
            parseDateTime = parseDateTime.secondOfMinute().withMaximumValue();
        }
        if (parseDateTime.millisOfSecond().get() == parseDateTime.millisOfSecond().getMinimumValue()) {
            parseDateTime = parseDateTime.millisOfSecond().withMaximumValue();
        }
        return parseDateTime;
    }

    private String getCompactDateString(String name, String[] pathStream) {
        String[] realTokens = StringUtils.split(name, "/");
        String compactDateString = null;
        for (int i = 0; i < realTokens.length; i++) {
            String currentString = pathStream[i];
            if (currentString.contains("${D:")) {
                int startindex = currentString.indexOf("${D:");
                int endindex = currentString.indexOf("}");
                if (compactDateString == null) {
                    compactDateString = realTokens[i].substring(startindex, endindex - 4);
                } else {
                    compactDateString += " " + realTokens[i].substring(startindex, endindex - 4);
                }
            }
        }
        return compactDateString;
    }

    private String getCompactDateFormatString(String name, String[] pathStream) {
        String[] realTokens = StringUtils.split(name, "/");
        String compactDateString = null;
        //contains more than one date token?
        for (int i = 0; i < realTokens.length; i++) {
            String currentString = pathStream[i];
            if (currentString.contains("${")) {
                int startindex = currentString.indexOf("${");
                int endindex = currentString.indexOf("}");
                if (compactDateString == null) {
                    compactDateString = currentString.substring(startindex + 4, endindex);
                } else {
                    compactDateString += " " + currentString.substring(startindex + 4, endindex);
                }
            }
        }
        return compactDateString;
    }

    private String[] getPathTokens(String filePath) {
//        List<String> tokens = new ArrayList<String>();
        //        filePath.substring("\\$\\{","\\}");
        String[] tokens = StringUtils.split(filePath, "/");
//         String[] tokens = filePath.trim().split("\\%");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
        }
        return tokens;
    }
}
