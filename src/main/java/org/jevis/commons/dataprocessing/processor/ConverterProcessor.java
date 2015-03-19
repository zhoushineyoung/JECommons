/**
 * Copyright (C) 2015 Envidatec GmbH <info@envidatec.com>
 *
 * This file is part of JECommons.
 *
 * JECommons is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation in version 3.
 *
 * JECommons is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * JECommons. If not, see <http://www.gnu.org/licenses/>.
 *
 * JECommons is part of the OpenJEVis project, further project information are
 * published at <http://www.OpenJEVis.org/>.
 */
package org.jevis.commons.dataprocessing.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisSample;
import org.jevis.commons.dataprocessing.DataProcessor;
import org.jevis.commons.dataprocessing.Task;
import org.jevis.commons.dataprocessing.VirtualAttribute;
import org.jevis.commons.dataprocessing.VirtuelSample;

/**
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class ConverterProcessor implements DataProcessor {

    public final static String NAME = "Converter";
    public static final String MULTIPLAYER = "multiplier";
    public static final String OFFSET = "offset";
    private List<JEVisSample> _result;

    @Override
    public void resetResult() {
        _result = null;
    }

    @Override
    public List<JEVisSample> getResult(Task mainTask) {
        if (_result != null) {
            return _result;
        } else {
            _result = new ArrayList<>();

            double m = 1;
            double b = 0;

            if (!mainTask.getOptions().containsKey(MULTIPLAYER)) {
                System.out.println("Converter Processor missing option " + MULTIPLAYER);
                //todo throw
            } else {
                m = Double.parseDouble(mainTask.getOption(MULTIPLAYER));
            }

            if (!mainTask.getOptions().containsKey(OFFSET)) {
                System.out.println("Converter Processor missing option " + OFFSET);
            } else {
                b = Double.parseDouble(mainTask.getOption(OFFSET));
            }

            if (mainTask.getSubTasks().size() != 1) {
                System.out.println("Waring Counter processor can only handel one input. using first only!");
            }

            System.out.println("Using M:" + m + "  B:" + b);
            for (JEVisSample sample : mainTask.getSubTasks().get(0).getResult()) {

                try {
                    double sum = (sample.getValueAsDouble() * m) + b;
//                    System.out.println("TS: " + sample.getTimestamp() + " new Value: " + sum);
                    _result.add(new VirtuelSample(sample.getTimestamp(), sum, mainTask.getJEVisDataSource(), new VirtualAttribute(null)));
                } catch (JEVisException ex) {
                    Logger.getLogger(ConverterProcessor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return _result;
    }

    @Override
    public String getName() {
        return NAME;
    }

}
