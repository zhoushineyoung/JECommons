/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons;

import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisType;

/**
 *
 * @author bf
 */
public class DatabaseHelper {

    public static boolean checkValidObject(JEVisObject jevisObject, JEVisType jevisType) throws JEVisException {
        boolean isValid = false;
        if (jevisObject.getAttribute(jevisType).hasSample() && jevisObject.getAttribute(jevisType).getLatestSample() != null && !jevisObject.getAttribute(jevisType).getLatestSample().getValueAsString().equals("")) {
            isValid = true;
        }
        return isValid;
    }

    public static String getObjectAsString(JEVisObject jevisObject, JEVisType jevisType) throws JEVisException {
        if (DatabaseHelper.checkValidObject(jevisObject, jevisType)) {
            return jevisObject.getAttribute(jevisType).getLatestSample().getValueAsString();

        } else {
            return null;
        }
    }

    public static Boolean getObjectAsBoolean(JEVisObject jevisObject, JEVisType jevisType) throws JEVisException {
        if (DatabaseHelper.checkValidObject(jevisObject, jevisType)) {
            return jevisObject.getAttribute(jevisType).getLatestSample().getValueAsBoolean();
        } else {
            return false;
        }
    }

    public static Integer getObjectAsInteger(JEVisObject jevisObject, JEVisType jevisType) {
        try {
            if (DatabaseHelper.checkValidObject(jevisObject, jevisType)) {
                return Integer.parseInt(jevisObject.getAttribute(jevisType).getLatestSample().getValueAsString());

            } else {
                return null;
            }
        } catch (Exception nfe) {
            nfe.printStackTrace();
            return null;
        }
    }

    public static Long getObjectAsLong(JEVisObject jevisObject, JEVisType jevisType) throws JEVisException {
        if (DatabaseHelper.checkValidObject(jevisObject, jevisType)) {
            return jevisObject.getAttribute(jevisType).getLatestSample().getValueAsLong();

        } else {
            return null;
        }
    }
}
