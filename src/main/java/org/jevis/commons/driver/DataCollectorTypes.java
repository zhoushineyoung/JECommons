/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.driver;

/**
 *
 * @author bf
 */
public interface DataCollectorTypes {

    interface DataSourceDriverDirectory {

        public final static String NAME = "Data Source Driver Directory";
    }

    interface ParserDriverDirectory {

        public final static String NAME = "Parser Driver Directory";
    }

    interface ConverterDriverDirectory {

        public final static String NAME = "Converter Driver Directory";
    }

    interface ImporterDriverDirectory {

        public final static String NAME = "Importer Driver Directory";
    }

    interface JEDataCollector {

        public final static String NAME = "JEDataCollector";
        public final static String MAX_NUMBER_THREADS = "Max Number Threads";
        public final static String DATA_SOURCE_TIMEOUT = "Data Source Timeout";
        public final static String ENABLE = "Enable";
    }

    interface Driver {

        public final static String NAME = "Driver";
        public final static String SOURCE_FILE = "Source File";
        public final static String MAIN_CLASS = "Main Class";
        public final static String JEVIS_CLASS = "JEVis Class";

        interface DataSourceDriver extends Driver {

            public final static String NAME = "Data Source Driver";
        }

        interface ParserDriver extends Driver {

            public final static String NAME = "Parser Driver";
        }

        interface ConverterDriver extends Driver {

            public final static String NAME = "Converter Driver";
        }

        interface ImporterDriver extends Driver {

            public final static String NAME = "Importer Driver";
        }
    }

    interface Parser {

        public final static String NAME = "Parser";

        interface CSVParser extends Parser {

            public final static String NAME = "CSV Parser";
            public final static String DATAPOINT_INDEX = "Datapoint Index";
            public final static String DATE_INDEX = "Date Index";
            public final static String DELIMITER = "Delimiter";
            public final static String NUMBER_HEADLINES = "Number Of Headlines";
            public final static String QUOTE = "Quote";
            public final static String TIME_INDEX = "Time Index";
            public final static String DATE_FORMAT = "Date Format";
            public final static String DECIMAL_SEPERATOR = "Decimal Separator";
            public final static String TIME_FORMAT = "Time Format";
            public final static String THOUSAND_SEPERATOR = "Thousand Separator";
        }

        interface XMLParser extends Parser {

            public final static String NAME = "XML Parser";
            public final static String GENERAL_TAG = "General Tag";
            public final static String SPECIFICATION_TAG = "Specification Tag";
            public final static String SPECIFICATION_ATTRIBUTE = "Specification In Attribute";
            public final static String VALUE_TAG = "Value Tag";
            public final static String VALUE_IN_ATTRIBUTE = "Value In Attribute";
            public final static String DATE_TAG = "Date Tag";
            public final static String DATE_IN_ATTRIBUTE = "Date In Attribute";
            public final static String TIME_TAG = "Time Tag";
            public final static String TIME_IN_ATTRIBUTE = "Time In Attribute";
            public final static String MAIN_ELEMENT = "Main Element";
            public final static String MAIN_ATTRIBUTE = "Main Attribute";
            public final static String DATE_ELEMENT = "Date Element";
            public final static String DATE_ATTRIBUTE = "Date Attribute";
            public final static String DATE_IN_ELEMENT = "Date in Element";
            public final static String VALUE_ELEMENT = "Value Element";
            public final static String VALUE_ATTRIBUTE = "Value Attribute";
            public final static String VALUE_IN_ELEMENT = "Value in Element";
            public final static String DATE_FORMAT = "Date Format";
            public final static String DECIMAL_SEPERATOR = "Decimal Separator";
            public final static String TIME_FORMAT = "Time Format";
            public final static String THOUSAND_SEPERATOR = "Thousand Separator";
        }
    }

    interface DataSource {

        public final static String NAME = "Data Source";
        public final static String TIMEZONE = "Timezone";
        public final static String ENABLE = "Enabled";

        interface DataServer extends DataSource {

            public final static String NAME = "Data Server";
            public final static String CONNECTION_TIMEOUT = "Connection Timeout";
            public final static String READ_TIMEOUT = "Read Timeout";
            public final static String HOST = "Host";
            public final static String PORT = "Port";

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

            interface SOAP extends DataServer {

                public final static String NAME = "SOAP Server";
                public final static String PASSWORD = "Password";
                public final static String SSL = "SSL";
                public final static String USER = "User";
            }
        }
    }

    public static interface Channel {

        public final static String NAME = "Channel";
        public final static String LAST_READOUT = "Last Readout";

        interface HTTPChannel extends Channel {

            public final static String NAME = "HTTP Channel";
            public final static String PATH = "Path";
        }

        interface SOAPChannel extends Channel {

            public final static String NAME = "SOAP Channel";
            public final static String TEMPLATE = "Template";
            public final static String PATH = "Path";
        }
    }

    public static interface Importer {

        public final static String NAME = "Importer";
    }

    public static interface DataPointDirectory {

        public final static String NAME = "Data Point Directory";

        interface CSVDataPointDirectory extends DataPointDirectory {

            public final static String NAME = "CSV Data Point Directory";
        }

        interface XMLDataPointDirectory extends DataPointDirectory {

            public final static String NAME = "XML Data Point Directory";
        }

    }

    public static interface DataPoint {

        public final static String NAME = "Data Point";

        interface CSVDataPoint extends DataPoint {

            public final static String NAME = "CSV Data Point";
            public final static String MAPPING_IDENTIFIER = "Mapping Identifier";
            public final static String VALUE_INDEX = "Value Index";
            public final static String TARGET = "Target";
        }

        interface XMLDataPoint extends DataPoint {

            public final static String NAME = "XML Data Point";
            public final static String MAPPING_IDENTIFIER = "Mapping Identifier";
            public final static String VALUE_INDEX = "Value Identifier";
            public final static String TARGET = "Target";
        }
    }
}
