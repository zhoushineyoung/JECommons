/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons;

/**
 *
 * @author bf
 */
public class ReportTypes {

    public interface JEReport {

        public final static String NAME = "Report";
        public final static String EMAIL = "E-Mails";
        public final static String SUBJECT = "Subject";
        public final static String TEMPLATE = "Template";
        public final static String LAST_REPORT = "Last Report";
        public final static String START_RECORD = "Start Record";
        public final static String SCHEDULE = "Schedule";
        public final static String ENABLED = "Enabled";
    }

    public interface JEReportLink {

        public final static String NAME = "ReportLink";
        public final static String ID = "ID";
        public final static String IDENTIFIER = "Identifier";
        public final static String IGNORE_TIMESTAMP = "Ignore Timestamp";
        public final static String AGGREGATION = "Aggregation";
    }

    public interface JEReportService {

        public final static String NAME = "JEReport";
        public final static String HOST = "Host";
        public final static String PASSWORD = "Password";
        public final static String PORT = "Port";
        public final static String USER = "User";
    }

    public interface Data {

        public final static String NAME = "Data";
        public final static String VALUE = "Value";
    }
}
