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
import org.jevis.commons.dataprocessing.Options;
import org.jevis.commons.dataprocessing.Task;
import org.jevis.commons.dataprocessing.VirtuelSample;
import static org.jevis.commons.dataprocessing.Options.getAllTimestamps;
import org.jevis.commons.dataprocessing.VirtualAttribute;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

/**
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class AggrigatorProcessor implements DataProcessor {

    public static final String NAME = "Aggrigator";

    @Override
    public List<JEVisSample> getResult(Task mainTask) {
        List<JEVisSample> result = new ArrayList<>();

//
//       
//        List<Interval> durations = ProcessController.buildIntervals(Period.days(1), ProcessController.getOffset(), samples.get(0).getTimestamp(), samples.get(samples.size() - 1).getTimestamp());
        List<Map<DateTime, JEVisSample>> sampleMaps = new ArrayList<>();
        List<List<JEVisSample>> allSamples = new ArrayList<>();
        for (Task task : mainTask.getSubTasks()) {
            allSamples.add(task.getResult());
            System.out.println("Add input result: " + allSamples.size());
        }

        List<DateTime> allTimestamps = getAllTimestamps(allSamples);
        if (allTimestamps.isEmpty()) {
            return result;
        }
        List<Interval> intervals = Options.getIntervals(mainTask, allTimestamps.get(0), allTimestamps.get(allTimestamps.size() - 1));

        System.out.println("intervals: " + intervals.size());

        int lastPos = 0;
        for (Interval interval : intervals) {
            List<JEVisSample> samplesInPeriod = new ArrayList<>();
            System.out.println("interval: " + interval);

            for (List<JEVisSample> samples : allSamples) {
                for (int i = lastPos; i < samples.size(); i++) {
                    try {
                        if (interval.contains(samples.get(i).getTimestamp())) {
//                        System.out.println("add sample: " + samples.get(i));
                            samplesInPeriod.add(samples.get(i));
                        } else if (samples.get(i).getTimestamp().isAfter(interval.getEnd())) {
                            lastPos = i;
                            break;
                        }
                    } catch (JEVisException ex) {
                        System.out.println("JEVisExeption while going trou sample: " + ex.getMessage());
                    }
                }

                double sum = 0;
                for (JEVisSample sample : samplesInPeriod) {
                    try {
                        sum += sample.getValueAsDouble();
                    } catch (JEVisException ex) {
                        Logger.getLogger(AggrigatorProcessor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                JEVisSample resultSum = new VirtuelSample(interval.getStart(), sum, mainTask.getJEVisDataSource(), new VirtualAttribute(null));
                result.add(resultSum);
                try {
                    System.out.println("resultSum: " + resultSum.getTimestamp() + "  " + resultSum.getValueAsDouble());
                } catch (JEVisException ex) {
                    Logger.getLogger(AggrigatorProcessor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void resetResult() {
    }
}
