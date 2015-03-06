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
import java.util.Map;
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
public class CounterProcessor implements DataProcessor {

    public static final String NAME = "Counter Processor";

    private List<JEVisSample> _result;

    public enum TS_MODE {

        BEGINNING, END
    }

    @Override
    public List<JEVisSample> getResult(Task task) {
        if (_result != null) {
            return _result;
        } else {
            _result = new ArrayList<>();

            if (task.getSubTasks().size() != 1) {
                System.out.println("Waring Counter processor can only handel one input. using first only!");
            }

            JEVisSample lastSample = null;

            TS_MODE mode = TS_MODE.BEGINNING;//TODO get from options

            for (JEVisSample sample : task.getSubTasks().get(0).getResult()) {

                if (lastSample == null) {
                    lastSample = sample;
                } else {
                    double diff = 0;
                    try {
                        if (sample.getValueAsDouble() >= lastSample.getValueAsDouble()) {

                            diff = sample.getValueAsDouble() - lastSample.getValueAsDouble();
//                            System.out.println("pV: " + lastSample.getValueAsDouble() + "  nV:" + sample.getValueAsDouble() + "  diff:" + diff);
                            if (mode == TS_MODE.BEGINNING) {
                                _result.add(new VirtuelSample(lastSample.getTimestamp(), diff, task.getJEVisDataSource(), new VirtualAttribute(null)));
                            } else {
                                _result.add(new VirtuelSample(sample.getTimestamp(), diff, task.getJEVisDataSource(), new VirtualAttribute(null)));
                            }

                        } else {
                            System.out.println("Error counter is smaler the the previsus. maybe an counter overflow?");
                        }
                    } catch (JEVisException ex) {
                        Logger.getLogger(CounterProcessor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lastSample = sample;
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
