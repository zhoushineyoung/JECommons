/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Broder
 */
public class DataSourceHelper {
    
    public static void test(){
        
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
