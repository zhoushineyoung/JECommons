/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing.outputHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.jevis.commons.parsing.ParsingRequest;
import org.jevis.commons.parsing.Result;

/**
 *
 * @author bf
 */
public class CSVFileOutputHandler extends OutputHandler {

    @Override
    public void writeOutput(ParsingRequest request, List<Result> results) {
        try {
            String outputPath = request.getFileOutputPath();
            if (outputPath == null) {
                outputPath = "output.csv";
            }
            File output = new File(outputPath);

            BufferedWriter write = new BufferedWriter(new FileWriter(output));
             write.write("value,date\n");
            for (Result r : results) {
                write.write(r.getValue() + "," + r.getDate() + "\n");
            }
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
