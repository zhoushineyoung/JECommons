/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing.xmlParsing;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author bf
 */
public class XMLParsing implements DataCollectorParser {

//    private String _generalTag;
    private String _specificationTag;
    private Boolean _specificationInAttribute;
    private List<XMLDatapointParser> _datapointParsers = new ArrayList<XMLDatapointParser>();
    private String _dateFormat;
    private String _timeFormat;
    private String _decimalSeperator;
    private String _thousandSeperator;
    private String _mainElement;
    private String _mainAttribute;
    private String _valueElement;
    private String _valueAtribute;
    private Boolean _valueInElement;
    private String _dateElement;
    private String _dateAttribute;
    private Boolean _dateInElement;
    private List<Result> _results = new ArrayList<Result>();

    public XMLParsing(String generalTag, String specificationTag, Boolean specificationInAttribute) {
        _mainElement = generalTag;
        _specificationTag = specificationTag;
        _specificationInAttribute = specificationInAttribute;
    }

    public XMLParsing() {
    }

    @Override
    public void parse(InputHandler ic) {
        System.out.println("XMl File Parsing starts");
        List<Document> documents = ic.getDocuments();
        for (Document d : documents) {
            NodeList elementsByTagName = d.getElementsByTagName(_mainElement);

            DOMSource domSource = new DOMSource(d);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer;
            try {
                transformer = tf.newTransformer();
                transformer.transform(domSource, result);
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(writer.toString());

            //iterate over all nodes with the element name
            for (int i = 0; i < elementsByTagName.getLength(); i++) {
                Node currentNode = elementsByTagName.item(i);
                Node mainAttributeNode = null;
                if (_mainAttribute != null) {
                    NamedNodeMap attributes = currentNode.getAttributes();
                    mainAttributeNode = attributes.getNamedItem(_mainAttribute);
                    if (mainAttributeNode == null) {
                        continue;
                    }
                }
                ic.setXMLInput(currentNode);

                //single parsing
                boolean isCorrectNode = true; //eigentl false
                DateTime dateTime = null;
                Double value = null;
                Long datapoint = null;
                for (XMLDatapointParser dpParser : _datapointParsers) {
                    if (mainAttributeNode != null && !mainAttributeNode.getNodeValue().equals(dpParser.getValueIdentifier())) {
                        continue;
                    }

                    boolean correct = false;
                    try {
                        //get Date
                        Node dateNode = null;
                        if (_dateElement != null) {
                            for (int j = 0; j < currentNode.getChildNodes().getLength(); j++) {
                                Node item = currentNode.getChildNodes().item(j);
                                if (item.getNodeName().equals(_dateElement)) {
                                    dateNode = item;
                                    break;
                                }
                            }
                        } else {
                            dateNode = currentNode.cloneNode(true);
                        }
                        String dateString = null;
                        if (_dateAttribute != null) {
                            Node namedItem = dateNode.getAttributes().getNamedItem(_dateAttribute);
                            dateString = namedItem.getNodeValue();
                        } else {
                            dateString = dateNode.getTextContent();
                        }
                        String pattern = _dateFormat;

                        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
                        dateTime = fmt.parseDateTime(dateString);

//                    dpParser.parse(ic);
//                    value = dpParser.getValue();
                        datapoint = dpParser.getTarget();


                        //get value
                        Node valueNode = null;
                        if (_valueElement != null) {
                            for (int j = 0; j < currentNode.getChildNodes().getLength(); j++) {
                                Node item = currentNode.getChildNodes().item(j);
                                if (item.getNodeName().equals(_valueElement)) {
                                    valueNode = item;
                                    break;
                                }
                            }
                        } else {
                            valueNode = currentNode.cloneNode(true);
                        }
                        String valueString = null;
                        if (_valueAtribute != null) {
                            Node namedItem = valueNode.getAttributes().getNamedItem(_valueAtribute);
                            valueString = namedItem.getNodeValue();
                        } else {
                            valueString = valueNode.getTextContent();
                        }
                        value = Double.parseDouble(valueString);
                        correct = true;
                    } catch (Exception ex) {
                    }

//                    if (dpParser.outOfBounce()) {
//                        org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.WARN, "Date for value out of bounce: " + dateTime);
//                        org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.WARN, "Value out of bounce: " + value);
//                    }

                    if (!correct) {
                        continue;
                    }
                    org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "Parsed DP" + datapoint);
                    org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "Parsed value" + value);
                    org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "Parsed date" + dateTime);
                    System.out.println("Parsed DP" + datapoint);
                    System.out.println("Parsed value" + value);
                    System.out.println("Parsed date" + dateTime);
                    _results.add(new Result(datapoint, value, dateTime));
                }

                //parse the correct node
                if (isCorrectNode) {
                }
            }
        }
    }

    @Override
    public void initialize(JEVisObject equipmentObject) {
        try {
            JEVisClass parser = equipmentObject.getDataSource().getJEVisClass(JEVisTypes.Parser.XMLParser.NAME);
            JEVisObject parserObject = equipmentObject.getChildren(parser, true).get(0);
            JEVisClass jeClass = parserObject.getJEVisClass();

            JEVisType dateFormatType = jeClass.getType(JEVisTypes.Parser.DATE_FORMAT);
            JEVisType timeFormatType = jeClass.getType(JEVisTypes.Parser.TIME_FORMAT);
            JEVisType decimalSeperatorType = jeClass.getType(JEVisTypes.Parser.DECIMAL_SEPERATOR);
            JEVisType thousandSeperatorType = jeClass.getType(JEVisTypes.Parser.THOUSAND_SEPERATOR);

            JEVisType mainElementType = jeClass.getType(JEVisTypes.Parser.XMLParser.MAIN_ELEMENT);
            JEVisType mainAttributeType = jeClass.getType(JEVisTypes.Parser.XMLParser.MAIN_ATTRIBUTE);
            JEVisType valueElementType = jeClass.getType(JEVisTypes.Parser.XMLParser.VALUE_ELEMENT);
            JEVisType valueAttributeType = jeClass.getType(JEVisTypes.Parser.XMLParser.VALUE_ATTRIBUTE);
            JEVisType valueInElement = jeClass.getType(JEVisTypes.Parser.XMLParser.VALUE_IN_ELEMENT);
            JEVisType dateElementType = jeClass.getType(JEVisTypes.Parser.XMLParser.DATE_ELEMENT);
            JEVisType dateAttributeType = jeClass.getType(JEVisTypes.Parser.XMLParser.DATE_ATTRIBUTE);
            JEVisType dateInElement = jeClass.getType(JEVisTypes.Parser.XMLParser.DATE_IN_ELEMENT);


            _dateFormat = DatabaseHelper.getObjectAsString(parserObject, dateFormatType);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "DateFormat: " + _dateFormat);
            _timeFormat = DatabaseHelper.getObjectAsString(parserObject, timeFormatType);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "TimeFormat: " + _timeFormat);
            _decimalSeperator = DatabaseHelper.getObjectAsString(parserObject, decimalSeperatorType);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "DecimalSeperator: " + _decimalSeperator);
            _thousandSeperator = DatabaseHelper.getObjectAsString(parserObject, thousandSeperatorType);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "ThousandSeperator: " + _thousandSeperator);

            _mainElement = DatabaseHelper.getObjectAsString(parserObject, mainElementType);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "MainElement: " + _mainElement);
            _mainAttribute = DatabaseHelper.getObjectAsString(parserObject, mainAttributeType);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "MainAttribute: " + _mainAttribute);
            _valueElement = DatabaseHelper.getObjectAsString(parserObject, valueElementType);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "ValueElement: " + _valueElement);
            _valueAtribute = DatabaseHelper.getObjectAsString(parserObject, valueAttributeType);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "ValueAttribute: " + _valueAtribute);
            _valueInElement = DatabaseHelper.getObjectAsBoolean(parserObject, valueInElement);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "ValueInElement: " + _valueInElement);
            _dateElement = DatabaseHelper.getObjectAsString(parserObject, dateElementType);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "DateElement: " + _dateElement);
            _dateAttribute = DatabaseHelper.getObjectAsString(parserObject, dateAttributeType);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "DateAttribute: " + _dateAttribute);
            _dateInElement = DatabaseHelper.getObjectAsBoolean(parserObject, dateInElement);
            org.apache.log4j.Logger.getLogger(this.getClass().getName()).log(org.apache.log4j.Level.ALL, "DateInElement: " + _dateInElement);
        } catch (JEVisException ex) {
            Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addDataPointParser(Long datapointID, String target, String mappingIdentifier, String valueIdentifier) {
        _datapointParsers.add(new XMLDatapointParser(datapointID, target, mappingIdentifier, valueIdentifier, _decimalSeperator, _thousandSeperator));
    }

    @Override
    public List<Result> getResults() {
        return _results;
    }

    public void setSpecificationTag(String _specificationTag) {
        this._specificationTag = _specificationTag;
    }

    public void setSpecificationInAttribute(Boolean _specificationInAttribute) {
        this._specificationInAttribute = _specificationInAttribute;
    }

    public void setDatapointParsers(List<XMLDatapointParser> _datapointParsers) {
        this._datapointParsers = _datapointParsers;
    }

    public void setDateFormat(String _dateFormat) {
        this._dateFormat = _dateFormat;
    }

    public void setTimeFormat(String _timeFormat) {
        this._timeFormat = _timeFormat;
    }

    public void setDecimalSeperator(String _decimalSeperator) {
        this._decimalSeperator = _decimalSeperator;
    }

    public void setThousandSeperator(String _thousandSeperator) {
        this._thousandSeperator = _thousandSeperator;
    }

    public void setMainElement(String _mainElement) {
        this._mainElement = _mainElement;
    }

    public void setMainAttribute(String _mainAttribute) {
        this._mainAttribute = _mainAttribute;
    }

    public void setValueElement(String _valueElement) {
        this._valueElement = _valueElement;
    }

    public void setValueAtribute(String _valueAtribute) {
        this._valueAtribute = _valueAtribute;
    }

    public void setValueInElement(Boolean _valueInElement) {
        this._valueInElement = _valueInElement;
    }

    public void setDateElement(String _dateElement) {
        this._dateElement = _dateElement;
    }

    public void setDateAttribute(String _dateAttribute) {
        this._dateAttribute = _dateAttribute;
    }

    public void setDateInElement(Boolean _dateInElement) {
        this._dateInElement = _dateInElement;
    }

    public void setResults(List<Result> _results) {
        this._results = _results;
    }
    
    
}
