package org.jevis.commons.parsing;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import org.jevis.api.JEVisClass;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.commons.JEVisTypes;
import org.jevis.commons.parsing.csvParsing.CSVParsing;
import org.jevis.commons.parsing.xmlParsing.XMLParsing;

/**
 *
 * @author broder
 */
public class ParsingFactory {

    private static final String CSV_PARSER = ("CSV Parser");
    private static final String CSV_MULTI_TIME_PARSER = ("CSV Multi Time");
    private static final String CSV_MULTI_DATA_PARSER = ("CSV Multi Data");
    private static final String MULTI_DATA_PARSER = ("Multi Data");
    private static final String XML_SINGLE_PARSER = ("XML Single");
    private static final String XML_MULTI_PARSER = ("XML Multi");
    private static final String SQL_PARSER = ("SQL");

    public static DataCollectorParser getParsing(JEVisObject jevisObject) throws JEVisException {

        JEVisClass parserClass = jevisObject.getDataSource().getJEVisClass(JEVisTypes.Parser.CSVParser.NAME);
        JEVisClass parserClassGeneral = jevisObject.getDataSource().getJEVisClass(JEVisTypes.Parser.NAME);
        JEVisObject parsingObject = null;

        boolean foundParsingObject = false;
        for (JEVisClass jevisClass : parserClassGeneral.getHeirs()) {
            List<JEVisObject> parserObjects = jevisObject.getChildren(jevisClass, true);
            if (parserObjects.size() == 1) {
                parsingObject = parserObjects.get(0);
                foundParsingObject = true;
            }
        }

        if (!foundParsingObject) {
            throw new JEVisException("Number of Parsing Objects != 1 under: " + jevisObject.getID(), 1);
        }

        DataCollectorParser parsing = null;
        String identifier = parsingObject.getJEVisClass().getName();
        org.apache.log4j.Logger.getLogger(ParsingFactory.class.getName()).log(org.apache.log4j.Level.ALL, "Parser: " + identifier);
        switch (identifier) {
            case JEVisTypes.Parser.CSVParser.NAME:
                parsing = new CSVParsing();
                break;
            case JEVisTypes.Parser.XMLParser.NAME: 
                parsing = new XMLParsing();
                break;
        }

        return parsing;
    }
}
