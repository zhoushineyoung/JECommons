package org.jevis.commons.parsing.csvParsing;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jevis.api.JEVisClass;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisType;
import org.jevis.commons.DatabaseHelper;
import org.jevis.commons.parsing.DataCollectorParser;
import org.jevis.commons.parsing.GeneralDateParser;
import org.jevis.commons.parsing.GeneralMappingParser;
import org.jevis.commons.parsing.GeneralValueParser;
import org.jevis.commons.parsing.JEVisParsingAttributes;
import org.jevis.commons.parsing.Result;
import org.jevis.commons.parsing.SampleParserContainer;
import org.jevis.commons.parsing.ValuePolicy;
import org.jevis.commons.parsing.inputHandler.InputHandler;
import org.joda.time.DateTime;

/**
 *
 * @author broder
 */
public class CSVParsing extends DataCollectorParser {

    private String _quote;
    private String _delim;
    private Integer _headerLines;
    private Integer _dateIndex;
    private Integer _timeIndex;
    private Integer _dpIndex;

    public CSVParsing(String quote, String delim, int headerlines) {
        _quote = quote;
        _delim = delim;
        _headerLines = headerlines;
        _dateIndex = -1;
        _timeIndex = -1;
    }

    public CSVParsing() {
        _dateIndex = -1;
        _timeIndex = -1;
    }

    //this should be easier.. perhaps with a JEObject?
    public void setQuote(String q) {
        _quote = q;
    }

    public void setDelim(String d) {
        _delim = d;
    }

    public void setHeaderLines(int h) {
        _headerLines = h;
    }

    /**
     *
     * @param ic
     */
    @Override
    public void parse(InputHandler ic) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Start CSV parsing");
        String[] stringArrayInput = ic.getStringArrayInput();
        Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Count of date/value/mapping variations" + _sampleParsers.size());
        Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Count of lines" + stringArrayInput.length);
        System.out.println("Quote," + _quote);
        for (int i = _headerLines; i < stringArrayInput.length; i++) {
            String line[] = stringArrayInput[i].split(String.valueOf(_delim), -1);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "line: " + stringArrayInput[i]);
            if (_quote != null) {
                line = removeQuotes(line);
                System.out.println("Date," + line[0]);
            }

            //line noch setzen im InputConverter als temp oder so
            ic.setCSVInput(line);

            DateTime dateTime;
            Double value;
            Long datapoint;
            for (SampleParserContainer parser : _sampleParsers) {
                GeneralDateParser dateParser = parser.getDateParser();
                dateParser.parse(ic);
                dateTime = dateParser.getDateTime();
                GeneralValueParser valueParser = parser.getValueParser();
                valueParser.parse(ic);
                value = valueParser.getValue();
                GeneralMappingParser dpParser = parser.getDpParser();
                dpParser.parse(ic);
                datapoint = dpParser.getDatapoint();


                if (((ValueCSVParser) valueParser).outOfBounce()) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARN, "Date for value out of bounce: " + dateTime);
                    Logger.getLogger(this.getClass().getName()).log(Level.WARN, "Value out of bounce: " + value);
                }
                boolean valueIsValid = ValuePolicy.checkValue(parser);
                if (!valueIsValid) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARN, "Date for value is invalid: " + dateTime);
                    Logger.getLogger(this.getClass().getName()).log(Level.WARN, "Value is invalid: " + value);
                    continue;
                }

                boolean datapointIsValid = ValuePolicy.checkDatapoint(parser);
                if (!datapointIsValid) {
                    Logger.getLogger(this.getClass().getName()).log(Level.ALL, "------Datapoint is invalid!!!------");
                    Logger.getLogger(this.getClass().getName()).log(Level.ALL, "OnlineID:" + datapoint);
                    Logger.getLogger(this.getClass().getName()).log(Level.ALL, "ChannelID:" + dpParser.getMappingValue());
                    Logger.getLogger(this.getClass().getName()).log(Level.ALL, "------------------------------------");
                    continue;
                }
                Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Parsed DP" + datapoint);
                Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Parsed value" + value);
                Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Parsed date" + dateTime);
                _results.add(new Result(datapoint, value, dateTime));
            }
        }
    }

    private String[] removeQuotes(String[] line) {
        String[] removed = new String[line.length];
        for (int i = 0; i < line.length; i++) {
            removed[i] = line[i].replace(_quote, "");
        }
        return removed;
    }

    @Override
    public void initialize(JEVisObject pn) {
        _jevisParser = pn;
        try {
            JEVisClass jeClass = pn.getJEVisClass();
            JEVisType seperatorColumn = jeClass.getType(JEVisParsingAttributes.CSV_DELIM);
            JEVisType enclosedBy = jeClass.getType(JEVisParsingAttributes.CSV_QUOTE);
            JEVisType ignoreFirstNLines = jeClass.getType(JEVisParsingAttributes.CSV_HEADERLINES);
            JEVisType dpIndexType = jeClass.getType(JEVisParsingAttributes.MAPPING_CSV_DPINDEX);

            _delim = DatabaseHelper.getObjectAsString(pn, seperatorColumn);
            _quote = DatabaseHelper.getObjectAsString(pn, enclosedBy);
            _headerLines = DatabaseHelper.getObjectAsInteger(pn, ignoreFirstNLines);

            
            JEVisType indexDateType = jeClass.getType(JEVisParsingAttributes.DATE_CSV_DATEINDEX);
            JEVisType indexTimeType = jeClass.getType(JEVisParsingAttributes.DATE_CSV_TIMEINDEX);
            _dateIndex = DatabaseHelper.getObjectAsInteger(pn, indexDateType);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "DateIndex" + _dateIndex);

            _timeIndex = DatabaseHelper.getObjectAsInteger(pn, indexTimeType);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "TimeIndex" + _timeIndex);

            _dpIndex = DatabaseHelper.getObjectAsInteger(pn, dpIndexType);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "DpIndex" + _dpIndex);
            
        } catch (JEVisException ex) {
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ERROR, null, ex);
        }
    }

//    public SampleParserContainer extractSampleContainer(JEVisObject mapping, JEVisObject dateObject, JEVisObject valueObject) {
//        SampleParserContainer container = null;
//        try {
//            //DateObject 
//
//
//
//           
//
//            //ValueObject
//            GeneralValueParser valueParser = null;
//            JEVisClass valueClass = valueObject.getJEVisClass();
//            JEVisType seperatorDecimalType = valueClass.getType(JevisAttributes.VALUE_DECIMSEPERATOR);
//            JEVisType seperatorThousandType = valueClass.getType(JevisAttributes.VALUE_THOUSANDSEPERATOR);
//
//            String seperatorDecimal = valueObject.getAttribute(seperatorDecimalType).getLatestSample().getValueAsString();
//            System.out.println("sepDecimal" + seperatorDecimal);
//            String seperatorThousand = valueObject.getAttribute(seperatorThousandType).getLatestSample().getValueAsString();
//            System.out.println("sepThousand" + seperatorThousand);
//            valueParser = new ValueCSVParser(indexValue, seperatorDecimal, seperatorThousand);
//
//            container = new SampleParserContainer(datapointParser, dateParser, valueParser);
//        } catch (JEVisException ex) {
//            Logger.getLogger(DataCollectorParser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return container;
//    }
    @Override
    public GeneralDateParser initializeDateParser(JEVisObject dateObject, JEVisObject valueObject, JEVisObject mapping) {
        DateCSVParser dateParser = null;
        try {
            JEVisClass dateClass = dateObject.getJEVisClass();

            JEVisType dateFormatType = dateClass.getType(JEVisParsingAttributes.DATE_DATEFORMAT);
            JEVisType timeFormatType = dateClass.getType(JEVisParsingAttributes.DATE_TIMEFORMAT);
            JEVisType timeZoneType = dateClass.getType(JEVisParsingAttributes.DATE_TIMEZONE);

            String date = DatabaseHelper.getObjectAsString(dateObject, dateFormatType);
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ALL, "date Value: " + date);

            String time = DatabaseHelper.getObjectAsString(dateObject, timeFormatType);
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ALL, "time Value: " + time);
//            DateTimeZone timezone = DateTimeZone.forTimeZone(TimeZone.getTimeZone(dateObject.getAttribute(timeZoneType).getLatestSample().getValueAsString()));




            dateParser = new DateCSVParser(time, _timeIndex, date, _dateIndex);
        } catch (JEVisException ex) {
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ERROR, null, ex);
        }
        return dateParser;
    }

    @Override
    public GeneralMappingParser initializeDatapointParser(JEVisObject dateObject, JEVisObject valueObject, JEVisObject mappingObject) {
        GeneralMappingParser datapointParser = null;
        try {
            //Mappingclass
            JEVisClass mappingClass = mappingObject.getJEVisClass();
            JEVisType indexValueType = mappingClass.getType(JEVisParsingAttributes.MAPPING_VALUE_SPECIFICATION);
            //            JEVisType indexDatapointType = mappingClass.getType("Index Datapoint");
            //            JEVisType datapointInFileType = mappingClass.getType("infile");
            JEVisType datapointType = mappingClass.getType(JEVisParsingAttributes.MAPPING_ONLINEID);
            JEVisType mappingType = mappingClass.getType(JEVisParsingAttributes.MAPPING_VALUE_MAPPING);
            JEVisType mappingNecessaryType = mappingClass.getType(JEVisParsingAttributes.MAPPING_NECESSARY);

            int indexValue = -1;
            if (mappingObject.getAttribute(indexValueType) != null) {
                indexValue = (int) (long) mappingObject.getAttribute(indexValueType).getLatestSample().getValueAsLong();
            }
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ALL, "index Value: " + indexValue);
            //            int indexDatapoint = 0;
            //            if (mapping.getAttribute(indexDatapointType) != null) {
            //                indexDatapoint = Integer.parseInt((String) mapping.getAttribute(indexDatapointType).getLatestSample().getValue());
            //            }
            long onlineID = -1;
            if (mappingObject.getAttribute(datapointType) != null) {
                onlineID = mappingObject.getAttribute(datapointType).getLatestSample().getValueAsLong();
            }
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ALL, "online ID: " + onlineID);

            String mapping = DatabaseHelper.getObjectAsString(mappingObject, mappingType);
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ALL, "mapping Value: " + mapping);

            boolean mappingNecessary = DatabaseHelper.getObjectAsBoolean(mappingObject, mappingNecessaryType);

            //            boolean inFile = false;
            //            if (mapping.getAttribute(datapointInFileType) != null) {
            //                inFile = Boolean.parseBoolean((String) mapping.getAttribute(datapointInFileType).getLatestSample().getValue());
            //            }
            //entweder den einen oder den anderen Parser!!!!! am besten als factory
            if (mappingNecessary) {
                datapointParser = new MappingCSVParser(true, onlineID, mapping, _dpIndex);
            } else {
                datapointParser = new MappingFixCSVParser(false, onlineID);
            }
        } catch (JEVisException ex) {
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ERROR, null, ex);
        }
        return datapointParser;
    }

    @Override
    public GeneralValueParser initializeValueParser(JEVisObject dateObject, JEVisObject valueObject, JEVisObject mapping) {
        GeneralValueParser valueParser = null;
        try {
            //get the index from the mapping object
            JEVisClass mappingClass = mapping.getJEVisClass();
            JEVisType indexValueType = mappingClass.getType(JEVisParsingAttributes.MAPPING_VALUE_SPECIFICATION);
            //            JEVisType indexDatapointType = mappingClass.getType("Index Datapoint");
            //            JEVisType datapointInFileType = mappingClass.getType("infile");
            int indexValue = -1;
            if (mapping.getAttribute(indexValueType) != null) {
                indexValue = (int) (long) mapping.getAttribute(indexValueType).getLatestSample().getValueAsLong();
            }

            //ValueObject
            JEVisClass valueClass = valueObject.getJEVisClass();
            JEVisType seperatorDecimalType = valueClass.getType(JEVisParsingAttributes.VALUE_DECIMSEPERATOR);
            JEVisType seperatorThousandType = valueClass.getType(JEVisParsingAttributes.VALUE_THOUSANDSEPERATOR);
            String seperatorDecimal = DatabaseHelper.getObjectAsString(valueObject, seperatorDecimalType);
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ALL, "decimal seperator: " + seperatorDecimal);

            String seperatorThousand = DatabaseHelper.getObjectAsString(valueObject, seperatorThousandType);
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ALL, "thousand seperator: " + seperatorThousand);

            valueParser = new ValueCSVParser(indexValue, seperatorDecimal, seperatorThousand);
        } catch (JEVisException ex) {
            Logger.getLogger(CSVParsing.class.getName()).log(Level.ERROR, null, ex);
        }
        return valueParser;
    }
}
