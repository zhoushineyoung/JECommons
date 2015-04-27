package org.jevis.commons.parsing;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
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
    public static List<Driver> _drivers = new ArrayList<Driver>();

    public static void setDrivers(List<Driver> drivers) {
        _drivers = drivers;
    }

    public static DataCollectorParser loadParsingDrivers(Driver driver) {
        LoadingDriver loadDriver = new LoadingDriver();
//        return (DataCollectorParser) loadDriver.loadClass("/home/bf/NetBeansProjects/JEDataCollector/Driver/VIDA350_Parser.jar", "org.jevis.jedatacollector.vida350parser.VIDA350_Parser");
//        return (DataCollectorParser) loadDriver.loadClass("/home/jedc/bin/Driver/VIDA350_Parser.jar", "org.jevis.jedatacollector.vida350parser.VIDA350_Parser");

        return (DataCollectorParser) loadDriver.loadClass(driver.getParserSourceName(), driver.getParserClassName());
//        return (DataCollectorParser) loadDriver.loadClass("/home/bf/NetBeansProjects/JEDataCollector/Driver/VIDA350_Parser.jar", driver.getParserName());
    }

    public static DataCollectorParser getParsing(JEVisObject jevisObject) throws JEVisException {

        JEVisClass parserClass = jevisObject.getDataSource().getJEVisClass(JEVisTypes.Parser.CSVParser.NAME);
        JEVisClass parserClassGeneral = jevisObject.getDataSource().getJEVisClass(JEVisTypes.Parser.NAME);
        JEVisObject parsingObject = null;

        boolean multipleParsingObjects = false;
        for (JEVisClass jevisClass : parserClassGeneral.getHeirs()) {
            List<JEVisObject> parserObjects = jevisObject.getChildren(jevisClass, true);
            if (parserObjects.size() > 1) {
                multipleParsingObjects = true;
            } else if (parserObjects.size() == 1) {
                parsingObject = parserObjects.get(0);
                break;
            }
        }

        if (multipleParsingObjects) {
            throw new JEVisException("Number of Parsing Objects > 1 under: " + jevisObject.getID(), 1);
        }

        DataCollectorParser parsing = null;
        String identifier = null;
        boolean searchDriver = false;
        if (parsingObject != null) {
            identifier = parsingObject.getJEVisClass().getName();

        } else {
            searchDriver = true;
            identifier = jevisObject.getJEVisClass().getName();
        }

        org.apache.log4j.Logger.getLogger(ParsingFactory.class.getName()).log(org.apache.log4j.Level.ALL, "Parser: " + identifier);
        switch (identifier) {
            case JEVisTypes.Parser.CSVParser.NAME:
                parsing = new CSVParsing();
                break;
            case JEVisTypes.Parser.XMLParser.NAME:
                parsing = new XMLParsing();
                break;
        }

        if (parsing == null) {
            for (Driver driver : _drivers) {
                if (identifier.equals(driver.getDataSourceName())) {
                    parsing = loadParsingDrivers(driver);
                }
            }
        }

        if (parsing == null) {
            String failure = "\nDataSource: " + jevisObject.getID();
            for (JEVisClass jevisClass : parserClassGeneral.getHeirs()) {
                List<JEVisObject> parserObjects = jevisObject.getChildren(jevisClass, true);
                failure += "\nSearched JevisClass: " + jevisClass.getName() + "with Nr of found JEVis Parser: " + parserObjects.size();
            }
            if (searchDriver) {
                failure += "\nSearched Driver: " + identifier;
            } else {
                failure += "\nSearched regular Parser: " + identifier;
            }
            throw new JEVisException("No correct Parsing Object under: " + jevisObject.getID() + "; ParsingClass: " + identifier + "; DataSourceClass: " + jevisObject.getJEVisClass().getName() + ";NoFDataSourceChilden: " + jevisObject.getChildren().size() + failure, 1);
        }

        return parsing;
    }
}
