/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisSample;
import org.jevis.api.JEVisType;

/**
 *
 * @author bf
 */
public class DatabaseHelper {

    public static boolean checkValidStringObject(JEVisObject jevisObject, JEVisType jevisType) throws JEVisException {
        boolean isValid = false;
        if (jevisObject.getAttribute(jevisType).hasSample() && jevisObject.getAttribute(jevisType).getLatestSample() != null && jevisObject.getAttribute(jevisType).getLatestSample().getValueAsString() instanceof String && !jevisObject.getAttribute(jevisType).getLatestSample().getValueAsString().equals("")) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean checkValidNumberObject(JEVisObject jevisObject, JEVisType jevisType) throws JEVisException {
        boolean isValid = false;
        if (jevisObject.getAttribute(jevisType).hasSample() && jevisObject.getAttribute(jevisType).getLatestSample() != null && !jevisObject.getAttribute(jevisType).getLatestSample().getValueAsString().equals("") && jevisObject.getAttribute(jevisType).getLatestSample().getValueAsLong() instanceof Long) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean checkValidBooleanObject(JEVisObject jevisObject, JEVisType jevisType) throws JEVisException {
        boolean isValid = false;
        if (jevisObject.getAttribute(jevisType).hasSample() && jevisObject.getAttribute(jevisType).getLatestSample() != null) {
            isValid = true;
        }
        return isValid;
    }

    public static String getObjectAsString(JEVisObject jevisObject, JEVisType jevisType) throws JEVisException {
        if (DatabaseHelper.checkValidStringObject(jevisObject, jevisType)) {
            return jevisObject.getAttribute(jevisType).getLatestSample().getValueAsString();

        } else {
            return null;
        }
    }

    public static Boolean getObjectAsBoolean(JEVisObject jevisObject, JEVisType jevisType) throws JEVisException {
        if (DatabaseHelper.checkValidBooleanObject(jevisObject, jevisType)) {
            return jevisObject.getAttribute(jevisType).getLatestSample().getValueAsBoolean();
        } else {
            return false;
        }
    }

    public static Integer getObjectAsInteger(JEVisObject jevisObject, JEVisType jevisType) {
        Integer value = null;
        try {
            if (DatabaseHelper.checkValidNumberObject(jevisObject, jevisType)) {
                value = new Integer(jevisObject.getAttribute(jevisType).getLatestSample().getValueAsLong().intValue());

            }
        } catch (NumberFormatException | JEVisException nfe) {
        }
        return value;
    }

    public static Long getObjectAsLong(JEVisObject jevisObject, JEVisType jevisType) throws JEVisException {
        Long value = null;
        try {
            if (DatabaseHelper.checkValidNumberObject(jevisObject, jevisType)) {
                value = jevisObject.getAttribute(jevisType).getLatestSample().getValueAsLong();

            }
        } catch (NumberFormatException | JEVisException nfe) {
        }
        return value;
    }
}
