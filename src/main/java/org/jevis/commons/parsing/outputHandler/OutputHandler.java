/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing.outputHandler;

import java.util.List;
import org.jevis.commons.parsing.ParsingRequest;
import org.jevis.commons.parsing.Result;

/**
 *
 * @author bf
 */
public abstract class OutputHandler {

    public static String FILE_OUTPUT = "csv_file";
    public static String JEVIS_OUTPUT = "jevis";

    abstract public void writeOutput(ParsingRequest request, List<Result> results);
}
