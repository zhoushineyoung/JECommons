/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons;

/**
 *
 * @author bf
 */
public interface JEVisTypes {

    interface Equipment {

        public final static String NAME = "Data Server";
        public static String SINGLE_CONNECTION = "Single Reader";
        public static String STARTDATE = "Start Data Collecting";
        public static String STARTDATEFORMAT = "Start Date Format";
        public static String TIMEZONE = "Timezone";
    }

    interface Parser {

        public final static String NAME = "Parser";
        public final static String PARSER_DATE = "Date";
        public final static String PARSER_VALUE = "Value";
        public final static String DATE_FORMAT = "Date Format";
        public final static String DECIMAL_SEPERATOR = "Decimal Seperator";
        public final static String TIME_FORMAT = "Time Format";
        public final static String THOUSAND_SEPERATOR = "Thousand Seperator";

        interface CSVParser extends Parser {

            public final static String NAME = "CSV Parser";
            public final static String CSV_QUOTE = "Quote";
            public final static String CSV_DELIM = "Delimiter";
            public final static String CSV_HEADERLINES = "Number Of Headerlines";
            public final static String MAPPING_CSV_DPINDEX = "Datapoint Index";
            public final static String DATE_CSV_TIMEINDEX = "Time Index";
            public final static String DATE_CSV_DATEINDEX = "Date Index";
            public final static String DATAPOINT_INDEX = "Datapoint Index";
            public final static String DATE_INDEX = "Date Index";
            public final static String DELIMITER = "Delimiter";
            public final static String NUMBER_HEADLINES = "Number Of Headlines";
            public final static String QUOTE = "Quote";
            public final static String TIME_INDEX = "Time Index";
        }

        interface XMLParser extends Parser {

            public final static String XML_GENERAL_TAG = "General Tag";
            public final static String XML_SPECIFICATION_TAG = "Specification Tag";
            public final static String XML_SPECIFICATION_ATTRIBUTE = "Specification In Attribute";
            public final static String XMl_VALUE_TAG = "Value Tag";
            public final static String XML_VALUE_ATTRIBUTE = "Value In Attribute";
            public final static String XML_DATE_TAG = "Date Tag";
            public final static String XML_DATE_ATTRIBUTE = "Date In Attribute";
            public final static String XML_TIME_TAG = "Time Tag";
            public final static String XML_TIME_ATTRIBUTE = "Time In Attribute";
        }
    }

    interface Date {

        public final static String NAME = "Date";
        public final static String DATE_TIMEFORMAT = "Time Format";
        public final static String DATE_DATEFORMAT = "Date Format";
    }

    interface Value {

        public final static String NAME = "Value";
        public final static String VALUE_DECIMSEPERATOR = "Decimal Seperator";
        public final static String VALUE_THOUSANDSEPERATOR = "Thousand Seperator";
    }

    interface Connection {

        public final static String Name = "Connection";
        public final static String DateFormat = "Date Format";
        public final static String FilePath = "File Path";
        public final static String Server = "Server URL";
        public final static String Port = "Port";
        public final static String ConnectionTimeout = "Connection Timeout";
        public final static String ReadTimeout = "Read Timeout";
        public final static String User = "User";
        public final static String Password = "Password";

        interface HTTP extends Connection {

            public final static String Name = "HTTP";
            public final static String SSL = "SSL";
        }

        interface FTP extends Connection {

            public final static String Name = "FTP";
            public final static String SSL = "SSL";
            public final static String FileNameScheme = "File Name Scheme";
        }

        interface sFTP extends Connection {

            public final static String Name = "sFTP";
            public final static String FileNameScheme = "File Name Scheme";
        }

        interface SOAP extends Connection {

            public final static String Name = "SOAP";
        }

        interface SQL extends Connection {

            public final static String Name = "SQL";
        }
    }

    interface DataServer {

        public final static String NAME = "Data Server";
        public final static String CONNECTION_TIMEOUT = "Connection Timeout";
        public final static String HOST = "Host";
        public final static String START_DATA_COLLECTING = "Start Data Collecting";
        public final static String READ_TIMEOUT = "Read Timeout";
        public final static String TIMEZONE = "Timezone";
        public final static String PORT = "Port";
        public final static String ENABLE = "Enabled";

        interface HTTP extends DataServer {

            public final static String NAME = "HTTP Server";
            public final static String PASSWORD = "Password";
            public final static String SSL = "SSL";
            public final static String USER = "User";
        }

        interface FTP extends DataServer {

            public final static String NAME = "FTP Server";
            public final static String PASSWORD = "Password";
            public final static String SSL = "SSL";
            public final static String USER = "User";
        }

        interface sFTP extends DataServer {

            public final static String NAME = "sFTP Server";
            public final static String PASSWORD = "Password";
            public final static String SSL = "SSL";
            public final static String USER = "User";
        }
    }

    interface DataPointDirectory {

        public final static String NAME = "Data Point Directory";
        public final static String FOLDER = "Folder";

        interface DataPointDirectoryCompressed extends DataPointDirectory {

            public final static String NAME = "Data Point Directory Compressed";
            public final static String ARCHIVE_FORMAT = "Archive Format";
        }
    }

    interface DataPoint {

        public final static String NAME = "Data Point";
        public final static String CHANNEL_ID = "ChannelID";
        public final static String DATA_LOGGER_NAME = "Data Logger Name";
        public final static String ONLINE_ID = "OnlineID";
        public final static String VALUE_SPEC = "Value Specification";
        public final static String MAPPING_NECESSARY = "Mapping Necessary";
        public final static String FILE_PATH = "File Path";
        public final static String MAPPING_IDENTIFIER = "Mapping Identifier";
        public final static String TARGET = "Target";
        public final static String VALUE_IDENTIFIER = "Value Identifier";
        public final static String DATE_FORMAT = "Date Format";
        public final static String LAST_READOUT = "Last Readout";
        public final static String PERIODICALLY_SAMPLING = "Periodically Sampling";
    }
}
