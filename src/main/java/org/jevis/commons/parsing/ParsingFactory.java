package org.jevis.commons.parsing;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.commons.parsing.csvParsing.CSVParsing;

/**
 *
 * @author broder
 */
public class ParsingFactory {

    private static final String CSV_PARSER = ("CSV");
    private static final String CSV_MULTI_TIME_PARSER = ("CSV Multi Time");
    private static final String CSV_MULTI_DATA_PARSER = ("CSV Multi Data");
    private static final String MULTI_DATA_PARSER = ("Multi Data");
    private static final String XML_SINGLE_PARSER = ("XML Single");
    private static final String XML_MULTI_PARSER = ("XML Multi");
    private static final String SQL_PARSER = ("SQL");

    public static GenericParser getParsing(JEVisObject parsingData) throws JEVisException {

        GenericParser parsing = null;
        String identifier = parsingData.getJEVisClass().getName();
        if (identifier.equals(CSV_PARSER)) {
            parsing = new CSVParsing();
        } else if (identifier.equals(CSV_MULTI_TIME_PARSER)) {
// parsing = new MultipleDataCoherentTimeCSV();
        } else if (identifier.equals(CSV_MULTI_DATA_PARSER)) {
// parsing = new MultipleDataCoherentDataCSV();
        } else if (identifier.equals(MULTI_DATA_PARSER)) {
// parsing = new MultipleDataCompact();
        } else if (identifier.equals(XML_SINGLE_PARSER)) {
// parsing = new XMLParsingSingleData();
        } else if (identifier.equals(XML_MULTI_PARSER)) {
// parsing = new XMLParsingMultipleData();
        } else if (identifier.equals(SQL_PARSER)) {
// parsing = new SQLParsing();
        }

        return parsing;
    }
}
