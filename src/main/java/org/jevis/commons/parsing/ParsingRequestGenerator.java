/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing;

import org.jevis.api.JEVisDataSource;
import org.jevis.commons.parsing.outputHandler.OutputHandler;
import org.joda.time.DateTimeZone;

/**
 *
 * @author bf
 */
public class ParsingRequestGenerator {

    public static ParsingRequest generateJEVisParsingRequest(DateTimeZone timezone, JEVisDataSource client) {
        ParsingRequest req = new DefaultParsingRequest();
        req.setJEVisDataSource(client);
        req.setOutputType(OutputHandler.JEVIS_OUTPUT);
        return req;
    }
    
      public static ParsingRequest generateOutputfileRequest(String outputPath) {
        ParsingRequest req = new DefaultParsingRequest();
        req.setOutputType(OutputHandler.FILE_OUTPUT);
        req.setFileOutputPath(outputPath);
        return req;
    }
}
