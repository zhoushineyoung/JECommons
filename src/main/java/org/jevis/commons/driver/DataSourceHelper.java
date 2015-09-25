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

import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisSample;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Broder
 */
public class DataSourceHelper {

    public static void test() {

    }

    static public void doTrustToCertificates() throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                    return;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                    return;
                }
            }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
                    System.out.println("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
                }
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    public static void setLastReadout(JEVisObject channel, DateTime lastDateTime) {
        try {
            String currentReadout = null;
//        JEVisClass channelClass = channel.getJEVisClass();
//        JEVisType readoutType = channelClass.getType(DataCollectorTypes.Channel.HTTPChannel.LAST_READOUT);
            String toString = lastDateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));

            JEVisSample buildSample = channel.getAttribute(DataCollectorTypes.Channel.LAST_READOUT).buildSample(new DateTime(), toString);
            buildSample.commit();
//        List<JEVisSample> sampleList = new ArrayList<JEVisSample>();
//        sampleList.add(buildSample);
//        dp.getJEVisDatapoint().getAttribute(JEVisTypes.DataPoint.LAST_READOUT).addSamples(sampleList);
        } catch (JEVisException ex) {
            java.util.logging.Logger.getLogger(DataSourceHelper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static String replaceDateFrom(String template, DateTime date) {
        DateTimeFormatter dtf = getFromDateFormat(template);
        int startindex = template.indexOf("${DF:");
        int endindex = template.indexOf("}") + 1;
        String first = template.substring(0, startindex);
        String last = template.substring(endindex, template.length());
        return first + date.toString(dtf) + last;
    }

    public static String replaceDateUntil(String template, DateTime date) {
        DateTimeFormatter dtf = getUntilDateFormat(template);
        int startindex = template.indexOf("${DU:");
        int endindex = template.indexOf("}") + 1;
        String first = template.substring(0, startindex);
        String last = template.substring(endindex, template.length());
        return first + date.toString(dtf) + last;
    }

    public static String replaceDateFromUntil(DateTime from, DateTime until, String filePath) {
        int fromstartindex = filePath.indexOf("${DF:");
        int untilstartindex = filePath.indexOf("${DU:");
        String replacedString = null;
        if (fromstartindex < untilstartindex) {
            replacedString = replaceDateFrom(filePath, from);
            replacedString = replaceDateUntil(replacedString, until);
        } else {
            replacedString = replaceDateUntil(filePath, until);
            replacedString = replaceDateFrom(replacedString, from);
        }
        return replacedString;
    }

    public static DateTimeFormatter getFromDateFormat(String stringWithDate) {
        int startindex = stringWithDate.indexOf("${DF:");
        int endindex = stringWithDate.indexOf("}");
        String date = stringWithDate.substring(startindex + 5, endindex);
        DateTimeFormatter dtf = DateTimeFormat.forPattern(date);
        return dtf;
    }

    public static DateTimeFormatter getUntilDateFormat(String stringWithDate) {
        int startindex = stringWithDate.indexOf("${DU:");
        int endindex = stringWithDate.indexOf("}");
        String date = stringWithDate.substring(startindex + 5, endindex);
        DateTimeFormatter dtf = DateTimeFormat.forPattern(date);
        return dtf;
    }

    public static Boolean containsTokens(String path) {
        if (path.contains("${")) {
            return true;
        } else {
            return false;
        }
    }
}
