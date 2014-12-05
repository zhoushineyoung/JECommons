/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing.xmlParsing;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jevis.api.JEVisClass;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisType;
import org.jevis.commons.JEVisTypes;
import org.jevis.commons.parsing.GenericParser;
import org.jevis.commons.parsing.GeneralDateParser;
import org.jevis.commons.parsing.GeneralMappingParser;
import org.jevis.commons.parsing.GeneralValueParser;
import org.jevis.commons.parsing.Result;
import org.jevis.commons.parsing.inputHandler.InputHandler;
import org.joda.time.DateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author bf
 */
public class XMLParsing extends GenericParser {

    private String _generalTag;
    private String _specificationTag;
    private Boolean _specificationInAttribute;
    private List<XMLDatapointParser> _datapointParsers = new ArrayList<XMLDatapointParser>();

    public XMLParsing(String generalTag, String specificationTag, Boolean specificationInAttribute) {
        _generalTag = generalTag;
        _specificationTag = specificationTag;
        _specificationInAttribute = specificationInAttribute;
    }

    @Override
    public void parse(InputHandler ic) {
        System.out.println("XMl File Parsing starts");
        System.out.println("Sampleparserlist " + _sampleParsers.size());
        List<Document> documents = ic.getDocuments();
        for (Document d : documents) {
            NodeList elementsByTagName = d.getElementsByTagName(_generalTag);

            //iterate over all nodes with the element name
            for (int i = 0; i < elementsByTagName.getLength(); i++) {
                Node currentNode = elementsByTagName.item(i);
                ic.setXMLInput(currentNode);

                //single parsing
                boolean isCorrectNode = true; //eigentl false
                DateTime dateTime = null;
                Double value = null;
                Long datapoint = null;
                for (XMLDatapointParser dpParser : _datapointParsers) {
                    dpParser.parse(ic);
                    datapoint = dpParser.getTarget();
                    value = dpParser.getValue();

                    if (dpParser.outOfBounce()) {
                        org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.WARN, "Date for value out of bounce: " + dateTime);
                        org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.WARN, "Value out of bounce: " + value);
                    }

                    org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "Parsed DP" + datapoint);
                    org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "Parsed value" + value);
                    org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "Parsed date" + dateTime);
                    _results.add(new Result(datapoint, value, dateTime));
                }

                //parse the correct node
                if (isCorrectNode) {
                }
            }
        }
    }

    @Override
    public void initialize(JEVisObject parser) {
        _jevisParser = parser;
        try {
            JEVisClass jeClass = parser.getJEVisClass();
            JEVisType generalTag = jeClass.getType(JEVisTypes.Parser.XMLParser.XML_GENERAL_TAG);
            JEVisType specificationTag = jeClass.getType(JEVisTypes.Parser.XMLParser.XML_SPECIFICATION_TAG);
            JEVisType specificationIsAttribute = jeClass.getType(JEVisTypes.Parser.XMLParser.XML_SPECIFICATION_ATTRIBUTE);

            _generalTag = parser.getAttribute(generalTag).getLatestSample().getValueAsString();

            if (parser.getAttribute(specificationTag).getLatestSample() != null) {
                _specificationTag = (String) parser.getAttribute(specificationTag).getLatestSample().getValue();
            }

            if (parser.getAttribute(specificationIsAttribute).getLatestSample() != null) {
                _specificationInAttribute = parser.getAttribute(specificationIsAttribute).getLatestSample().getValueAsBoolean();
            }
        } catch (JEVisException ex) {
            Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    public GeneralDateParser initializeDateParser(JEVisObject dateObject, JEVisObject valueObject, JEVisObject mapping) {
//        DateXMLParsing dateParser = null;
//        try {
//            JEVisClass dateClass = dateObject.getJEVisClass();
//            System.out.println("Dateobjectid " + dateObject.getID());
//
//            JEVisType dateFormatType = dateClass.getType(JEVisTypes.Date.DATE_DATEFORMAT);
//            JEVisType dateTagType = dateClass.getType(JEVisTypes.Parser.XMLParser.XML_DATE_TAG);
//            JEVisType dateInAttributeType = dateClass.getType(JEVisTypes.Parser.XMLParser.XML_DATE_ATTRIBUTE);
//            JEVisType timeFormatType = dateClass.getType(JEVisTypes.Date.DATE_TIMEFORMAT);
//            JEVisType timeTagType = dateClass.getType(JEVisTypes.Parser.XMLParser.XML_TIME_TAG);
//            JEVisType timeInAttributeType = dateClass.getType(JEVisTypes.Parser.XMLParser.XML_TIME_ATTRIBUTE);
//
//            String dateFormat = dateObject.getAttribute(dateFormatType).getLatestSample().getValueAsString();
//            System.out.println("Dateformat" + dateFormat);
//            String dateTag = dateObject.getAttribute(dateTagType).getLatestSample().getValueAsString();
//            System.out.println("DateTag" + dateTag);
//            boolean dateInAttribute = dateObject.getAttribute(dateInAttributeType).getLatestSample().getValueAsBoolean();
//            System.out.println("DateInAttribute" + dateInAttribute);
//
//
//            String timeFormat = dateObject.getAttribute(timeFormatType).getLatestSample().getValueAsString();
//            System.out.println("Time" + timeFormat);
//            String timeTag = dateObject.getAttribute(timeTagType).getLatestSample().getValueAsString();
//            System.out.println("DateTag" + timeTag);
//            boolean timeInAttribute = dateObject.getAttribute(timeInAttributeType).getLatestSample().getValueAsBoolean();
//            System.out.println("DateInAttribute" + timeInAttribute);
//
//
//            dateParser = new DateXMLParsing(dateFormat, dateTag, dateInAttribute, timeFormat, timeTag, timeInAttribute);
//        } catch (JEVisException ex) {
//            Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return dateParser;
//    }
//
//    @Override
//    public GeneralMappingParser initializeDatapointParser(JEVisObject date, JEVisObject value, JEVisObject mapping) {
//        GeneralMappingParser datapointParser = null;
//        try {
//            //Mappingclass
//            JEVisClass mappingClass = mapping.getJEVisClass();
//            JEVisType indexValueType = mappingClass.getType(JEVisTypes.DataPoint.VALUE_SPEC);
//            //            JEVisType indexDatapointType = mappingClass.getType("Index Datapoint");
//            //            JEVisType datapointInFileType = mappingClass.getType("infile");
//            JEVisType datapointType = mappingClass.getType(JEVisTypes.DataPoint.ONLINE_ID);
//
//            String valueSpecification = null;
//            if (mapping.getAttribute(indexValueType) != null) {
//                valueSpecification = mapping.getAttribute(indexValueType).getLatestSample().getValueAsString();
//            }
//            System.out.println("ValueSpecification" + valueSpecification);
//            //            int indexDatapoint = 0;
//            //            if (mapping.getAttribute(indexDatapointType) != null) {
//            //                indexDatapoint = Integer.parseInt((String) mapping.getAttribute(indexDatapointType).getLatestSample().getValue());
//            //            }
//            long datapoint = -1;
//            if (mapping.getAttribute(datapointType) != null) {
//                datapoint = mapping.getAttribute(datapointType).getLatestSample().getValueAsLong();
//            }
//            System.out.println("Datapoint" + datapoint);
//            //            boolean inFile = false;
//            //            if (mapping.getAttribute(datapointInFileType) != null) {
//            //                inFile = Boolean.parseBoolean((String) mapping.getAttribute(datapointInFileType).getLatestSample().getValue());
//            //            }
//            //entweder den einen oder den anderen Parser!!!!!
//            datapointParser = new XMLDatapointParser(false, datapoint);
//        } catch (JEVisException ex) {
//            Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
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
//            String valueSpecification = null;
//            if (mapping.getAttribute(indexValueType) != null) {
//                valueSpecification = mapping.getAttribute(indexValueType).getLatestSample().getValueAsString();
//            }
//            JEVisType valueInAttributeType = mappingClass.getType(JEVisTypes.Parser.XMLParser.XML_VALUE_ATTRIBUTE);
//            //            JEVisType indexDatapointType = mappingClass.getType("Index Datapoint");
//            //            JEVisType datapointInFileType = mappingClass.getType("infile");
//            boolean valueInAttribute = false;
//            if (mapping.getAttribute(indexValueType) != null) {
//                valueInAttribute = mapping.getAttribute(valueInAttributeType).getLatestSample().getValueAsBoolean();
//            }
//
//            //ValueObject
//            JEVisClass valueClass = valueObject.getJEVisClass();
//            JEVisType seperatorDecimalType = valueClass.getType(JEVisTypes.Value.VALUE_DECIMSEPERATOR);
//            JEVisType seperatorThousandType = valueClass.getType(JEVisTypes.Value.VALUE_THOUSANDSEPERATOR);
//
//            String seperatorDecimal = valueObject.getAttribute(seperatorDecimalType).getLatestSample().getValueAsString();
//            System.out.println("sepDecimal" + seperatorDecimal);
//            String seperatorThousand = valueObject.getAttribute(seperatorThousandType).getLatestSample().getValueAsString();
//            System.out.println("sepThousand " + seperatorThousand);
//            valueParser = new ValueXMLParsing(valueSpecification, valueInAttribute, seperatorDecimal, seperatorThousand);
//        } catch (JEVisException ex) {
//            Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return valueParser;
//    }

    @Override
    public void addDataPointParser(Long datapointID, String target, String mappingIdentifier, String valueIdentifier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
