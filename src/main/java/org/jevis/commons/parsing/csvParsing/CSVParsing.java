package org.jevis.commons.parsing.csvParsing;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jevis.api.JEVisClass;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisType;
import org.jevis.commons.DatabaseHelper;
import org.jevis.commons.JEVisTypes;
import org.jevis.commons.parsing.DataCollectorParser;
import org.jevis.commons.parsing.Result;
import org.jevis.commons.parsing.inputHandler.InputHandler;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author broder
 */
public class CSVParsing implements DataCollectorParser {

    private String _quote;
    private String _delim;
    private Integer _headerLines;
    private Integer _dateIndex;
    private Integer _timeIndex;
    private Integer _dpIndex;
    private String _dateFormat;
    private String _timeFormat;
    private String _decimalSeperator;
    private String _thousandSeperator;
    private List<CSVDatapointParser> _datapointParsers = new ArrayList<CSVDatapointParser>();
    private List<Result> _results = new ArrayList<Result>();

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

    public CSVParsing(String quote, String delim, Integer headerlines, Integer dateIndex, Integer timeIndex, Integer dpIndex, String dateFormat, String timeFormat, String decimalSep, String thousandSep) {
        _quote = quote;
        _delim = delim;
        _headerLines = headerlines;
        if (dateIndex != null) {
            _dateIndex = dateIndex - 1;
        }
        if (timeIndex != null) {
            _timeIndex = timeIndex - 1;
        }
        if (dpIndex != null) {
            _dpIndex = dpIndex - 1;
        }
        _dateFormat = dateFormat;
        _timeFormat = timeFormat;
        _decimalSeperator = decimalSep;
        _thousandSeperator = thousandSep;
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
        Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Start CSV parsing");
        String[] stringArrayInput = ic.getStringArrayInput();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Count of lines" + stringArrayInput.length);
        if (ic.getFilePath() != null) {
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "File Path: " + ic.getFilePath());
        }
        for (int i = _headerLines; i < stringArrayInput.length; i++) {
            try {
                String line[] = stringArrayInput[i].split(String.valueOf(_delim), -1);
//                Logger.getLogger(this.getClass().getName()).log(Level.ALL, "line: " + stringArrayInput[i]);
                if (_quote != null) {
                    line = removeQuotes(line);
                }


                //line noch setzen im InputConverter als temp oder so
                ic.setCSVInput(line);
//                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Line: " + line);


                DateTime dateTime = getDateTime(line);
                Double value;
                Long target;
                for (CSVDatapointParser dpParser : _datapointParsers) {
                    dpParser.parse(ic);
                    target = dpParser.getTarget();
                    value = dpParser.getValue();

                    if (!dpParser.isValueValid()) {
//                        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "ValueValid: " + dpParser.isValueValid());
                        StringBuilder failureLine = new StringBuilder();
                        for (int current = 0; current < line.length; current++) {
                            failureLine.append(line[current]);
                            if (current < line.length - 1) {
                                failureLine.append(_delim);
                            }
                        }
                        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Value is not valid in line: " + failureLine.toString());
                        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Value Index: " + dpParser.getValueIndex());
                        continue;
                    }

                    if (dpParser.isMappingFailing()) {
//                        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Mapping Failure: " + dpParser.isMappingFailing());
                        continue;
                    }
                    if (dateTime == null) {
//                        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "DateTime Null: " + dateTime);
                        continue;
                    }

//                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Value: " + dpParser.getValue());
//                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Date: " + dateTime);
//                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Target: " + dpParser.getTarget());
//                    Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Parsed DP" + datapoint);
//                    Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Parsed value" + value);
//                    Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Parsed date" + dateTime);
                    _results.add(new Result(target, value, dateTime));
                }
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARN, "Detect a Problem in the Parsing Process");
            }
        }
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Number of Results: " + _results.size());
        if (!_results.isEmpty()) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "LastResult (Date,Target,Value): " + _results.get(_results.size() - 1).getDate() + "," + _results.get(_results.size() - 1).getOnlineID() + "," + _results.get(_results.size() - 1).getValue());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Cant parse or cant find any parsable data");
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
    public void initialize(JEVisObject equipmentObject) {
        try {
            JEVisClass parser = equipmentObject.getDataSource().getJEVisClass(JEVisTypes.Parser.CSVParser.NAME);
            JEVisObject parserObject = equipmentObject.getChildren(parser, true).get(0);
            JEVisClass jeClass = parserObject.getJEVisClass();
            JEVisType seperatorColumn = jeClass.getType(JEVisTypes.Parser.CSVParser.DELIMITER);
            JEVisType enclosedBy = jeClass.getType(JEVisTypes.Parser.CSVParser.QUOTE);
            JEVisType ignoreFirstNLines = jeClass.getType(JEVisTypes.Parser.CSVParser.NUMBER_HEADLINES);
            JEVisType dpIndexType = jeClass.getType(JEVisTypes.Parser.CSVParser.DATAPOINT_INDEX);
            JEVisType dateIndexType = jeClass.getType(JEVisTypes.Parser.CSVParser.DATE_INDEX);
            JEVisType timeIndexType = jeClass.getType(JEVisTypes.Parser.CSVParser.TIME_INDEX);
            JEVisType dateFormatType = jeClass.getType(JEVisTypes.Parser.CSVParser.DATE_FORMAT);
            JEVisType timeFormatType = jeClass.getType(JEVisTypes.Parser.CSVParser.TIME_FORMAT);
            JEVisType decimalSeperatorType = jeClass.getType(JEVisTypes.Parser.CSVParser.DECIMAL_SEPERATOR);
            JEVisType thousandSeperatorType = jeClass.getType(JEVisTypes.Parser.CSVParser.THOUSAND_SEPERATOR);

            _delim = DatabaseHelper.getObjectAsString(parserObject, seperatorColumn);
            _quote = DatabaseHelper.getObjectAsString(parserObject, enclosedBy);
            _headerLines = DatabaseHelper.getObjectAsInteger(parserObject, ignoreFirstNLines);
            if (_headerLines == null) {
                _headerLines = 0;
            }
            _dpIndex = DatabaseHelper.getObjectAsInteger(parserObject, dpIndexType);
            if (_dpIndex != null) {
                _dpIndex--;
            }
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "DpIndex: " + _dpIndex);

            _dateIndex = DatabaseHelper.getObjectAsInteger(parserObject, dateIndexType);
            if (_dateIndex != null) {
                _dateIndex--;
            }
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "DateIndex: " + _dateIndex);

            _timeIndex = DatabaseHelper.getObjectAsInteger(parserObject, timeIndexType);
            if (_timeIndex != null) {
                _timeIndex--;
            }
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "TimeIndex: " + _timeIndex);

            _dateFormat = DatabaseHelper.getObjectAsString(parserObject, dateFormatType);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "DateFormat: " + _dateFormat);

            _timeFormat = DatabaseHelper.getObjectAsString(parserObject, timeFormatType);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "TimeFormat: " + _timeFormat);

            _decimalSeperator = DatabaseHelper.getObjectAsString(parserObject, decimalSeperatorType);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "DecimalSeperator: " + _decimalSeperator);

            _thousandSeperator = DatabaseHelper.getObjectAsString(parserObject, thousandSeperatorType);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "ThousandSeperator: " + _thousandSeperator);

        } catch (JEVisException ex) {
            Logger.getLogger(CSVParsing.class
                    .getName()).log(Level.ERROR, null, ex);
        }
    }

//    @Override
//    public GeneralDateParser initializeDateParser(JEVisObject dateObject, JEVisObject valueObject, JEVisObject mapping) {
//        DateCSVParser dateParser = null;
//        try {
//            JEVisClass dateClass = dateObject.getJEVisClass();
//
//            JEVisType dateFormatType = dateClass.getType(JEVisTypes.Date.DATE_DATEFORMAT);
//            JEVisType timeFormatType = dateClass.getType(JEVisTypes.Date.DATE_TIMEFORMAT);
//
//            String date = DatabaseHelper.getObjectAsString(dateObject, dateFormatType);
//            Logger
//                    .getLogger(CSVParsing.class
//                    .getName()).log(Level.ALL, "date Value: " + date);
//
//            String time = DatabaseHelper.getObjectAsString(dateObject, timeFormatType);
//
//            Logger.getLogger(CSVParsing.class
//                    .getName()).log(Level.ALL, "time Value: " + time);
////            DateTimeZone timezone = DateTimeZone.forTimeZone(TimeZone.getTimeZone(dateObject.getAttribute(timeZoneType).getLatestSample().getValueAsString()));
//
//            dateParser = new DateCSVParser(time, _timeIndex, date, _dateIndex);
//        } catch (JEVisException ex) {
//            Logger.getLogger(CSVParsing.class.getName()).log(Level.ERROR, null, ex);
//        }
//        return dateParser;
//    }
//
//    @Override
//    public GeneralMappingParser initializeDatapointParser(JEVisObject dateObject, JEVisObject valueObject, JEVisObject mappingObject) {
//        GeneralMappingParser datapointParser = null;
//        try {
//            //Mappingclass
//            JEVisClass mappingClass = mappingObject.getJEVisClass();
//            JEVisType indexValueType = mappingClass.getType(JEVisTypes.DataPoint.VALUE_SPEC);
//            //            JEVisType indexDatapointType = mappingClass.getType("Index Datapoint");
//            //            JEVisType datapointInFileType = mappingClass.getType("infile");
//            JEVisType datapointType = mappingClass.getType(JEVisTypes.DataPoint.ONLINE_ID);
//            JEVisType mappingType = mappingClass.getType(JEVisTypes.DataPoint.CHANNEL_ID);
//            JEVisType mappingNecessaryType = mappingClass.getType(JEVisTypes.DataPoint.MAPPING_NECESSARY);
//
//            int indexValue = -1;
//            if (mappingObject.getAttribute(indexValueType) != null) {
//                indexValue = (int) (long) mappingObject.getAttribute(indexValueType).getLatestSample().getValueAsLong();
//
//            }
//            Logger.getLogger(CSVParsing.class
//                    .getName()).log(Level.ALL, "index Value: " + indexValue);
//            //            int indexDatapoint = 0;
//            //            if (mapping.getAttribute(indexDatapointType) != null) {
//            //                indexDatapoint = Integer.parseInt((String) mapping.getAttribute(indexDatapointType).getLatestSample().getValue());
//            //            }
//            long onlineID = -1;
//
//            if (mappingObject.getAttribute(datapointType)
//                    != null) {
//                onlineID = mappingObject.getAttribute(datapointType).getLatestSample().getValueAsLong();
//            }
//
//            Logger.getLogger(CSVParsing.class
//                    .getName()).log(Level.ALL, "online ID: " + onlineID);
//
//            String mapping = DatabaseHelper.getObjectAsString(mappingObject, mappingType);
//
//            Logger.getLogger(CSVParsing.class
//                    .getName()).log(Level.ALL, "mapping Value: " + mapping);
//
//            boolean mappingNecessary = DatabaseHelper.getObjectAsBoolean(mappingObject, mappingNecessaryType);
//            //            boolean inFile = false;
//            //            if (mapping.getAttribute(datapointInFileType) != null) {
//            //                inFile = Boolean.parseBoolean((String) mapping.getAttribute(datapointInFileType).getLatestSample().getValue());
//            //            }
//            //entweder den einen oder den anderen Parser!!!!! am besten als factory
//            if (mappingNecessary) {
//                datapointParser = new CSVDatapointParser(true, onlineID, mapping, _dpIndex);
//            } 
//        } catch (JEVisException ex) {
//            Logger.getLogger(CSVParsing.class.getName()).log(Level.ERROR, null, ex);
//        }
//        return datapointParser;
//    }
//
//    @Override
//    public GeneralValueParser initializeValueParser(JEVisObject dateObject, JEVisObject valueObject, JEVisObject mapping) {
//        GeneralValueParser valueParser = null;
//        try {
//            //get the index from the mapping object
//            JEVisClass mappingClass = mapping.getJEVisClass();
//            JEVisType indexValueType = mappingClass.getType(JEVisTypes.DataPoint.VALUE_SPEC);
//            //            JEVisType indexDatapointType = mappingClass.getType("Index Datapoint");
//            //            JEVisType datapointInFileType = mappingClass.getType("infile");
//            int indexValue = -1;
//            if (mapping.getAttribute(indexValueType) != null) {
//                indexValue = (int) (long) mapping.getAttribute(indexValueType).getLatestSample().getValueAsLong();
//            }
//
//            //ValueObject
//            JEVisClass valueClass = valueObject.getJEVisClass();
//            JEVisType seperatorDecimalType = valueClass.getType(JEVisTypes.Value.VALUE_DECIMSEPERATOR);
//            JEVisType seperatorThousandType = valueClass.getType(JEVisTypes.Value.VALUE_THOUSANDSEPERATOR);
//            String seperatorDecimal = DatabaseHelper.getObjectAsString(valueObject, seperatorDecimalType);
//            Logger.getLogger(CSVParsing.class.getName()).log(Level.ALL, "decimal seperator: " + seperatorDecimal);
//
//            String seperatorThousand = DatabaseHelper.getObjectAsString(valueObject, seperatorThousandType);
//            Logger.getLogger(CSVParsing.class.getName()).log(Level.ALL, "thousand seperator: " + seperatorThousand);
//
//            valueParser = new ValueCSVParser(indexValue, seperatorDecimal, seperatorThousand);
//        } catch (JEVisException ex) {
//            Logger.getLogger(CSVParsing.class.getName()).log(Level.ERROR, null, ex);
//        }
//        return valueParser;
//    }
    @Override
    public void addDataPointParser(Long datapointID, String target, String mappingIdentifier, String valueIdentifier) {
        _datapointParsers.add(new CSVDatapointParser(datapointID, _dpIndex, target, mappingIdentifier, valueIdentifier, _decimalSeperator, _thousandSeperator));
    }

    private DateTime getDateTime(String[] line) {
        try {
            String date = line[_dateIndex];
//        String dateFormat = _dateFormat;

            String pattern = _dateFormat;
            String format = date;

            if (_timeFormat != null && _timeIndex > -1) {
                String time = line[_timeIndex];
                pattern += " " + _timeFormat;
                format += " " + time;
            }
//            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "complete time " + format);
//            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "complete pattern " + pattern);

            DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
            return fmt.parseDateTime(format);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARN, "Date not parsable: " + line);
            Logger.getLogger(this.getClass().getName()).log(Level.WARN, "DateFormat: " + _dateFormat);
            Logger.getLogger(this.getClass().getName()).log(Level.WARN, "DateIndex: " + _dateIndex);
            Logger.getLogger(this.getClass().getName()).log(Level.WARN, "DateFormat: " + _timeFormat);
            Logger.getLogger(this.getClass().getName()).log(Level.WARN, "DateIndex: " + _timeIndex);
        }

        if (_dateFormat == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "No Datetime found");
            return null;
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Current Datetime");
            return new DateTime();
        }

    }
//    @Override
//    public void addDataPoints(List<DataPoint> dataPoints) {
//        for (DataPoint dp : dataPoints) {
//            _datapointParsers.add(new CSVDatapointParser(dp.getDatapointId(), dp.getTarget(), dp.getMappingIdentifier(), dp.getValueIdentifier(), _decimalSeperator, _thousandSeperator));
//        }
//    }

    @Override
    public List<Result> getResults() {
        return _results;
    }
}
