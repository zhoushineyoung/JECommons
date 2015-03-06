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
package org.jevis.commons.dataprocessing;

import org.jevis.commons.json.JsonTask;
import org.jevis.commons.dataprocessing.processor.ConverterProcessor;
import org.jevis.commons.dataprocessing.processor.InputProcessor;
import org.jevis.commons.dataprocessing.processor.CounterProcessor;
import org.jevis.commons.dataprocessing.processor.AggrigatorProcessor;
import org.jevis.commons.dataprocessing.processor.NullProcessor;
import org.jevis.commons.dataprocessing.processor.MathProcessor;
import org.jevis.commons.dataprocessing.processor.LimitCheckerProcessor;
import org.jevis.commons.dataprocessing.processor.ImpulsProcessor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jevis.api.JEVisDataSource;

/**
 * Helperclass to work with Tasks
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class Tasks {

    public Tasks() {
//        new JEVisUnitImp(new Gson().fromJson(rs.getString(AttributeTable.COLUMN_DISPLAY_UNIT), JsonUnit.class));
    }

    /**
     * Returns an Json String representation of an Task
     *
     * @param task
     * @return
     */
    public static String taskToJSon(Task task) {
        TaskImp json = new TaskImp();
        json.setProcessor(new NullProcessor());
//        Gson gson = new GsonBuilder().create();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(new JsonTask(task), JsonTask.class);
    }

    /**
     * Creates an new Task from an JSon String
     *
     * @param ds
     * @param json
     * @return
     */
    public static Task jsonToTask(JEVisDataSource ds, String json) {
        JsonTask jTask = new Gson().fromJson(json, JsonTask.class);
        Task newTask = new TaskImp(ds, jTask);

        return newTask;
    }

    /**
     * Returns an matching Processor based on the name.
     *
     * @param name Name of the Processor
     * @return
     */
    public static DataProcessor getProcessor(String name) {
        switch (name) {
            case InputProcessor.NAME:
                return new InputProcessor();
            case AggrigatorProcessor.NAME:
                return new AggrigatorProcessor();
            case CounterProcessor.NAME:
                return new CounterProcessor();
            case ImpulsProcessor.NAME:
                return new ImpulsProcessor();
            case LimitCheckerProcessor.NAME:
                return new LimitCheckerProcessor();
            case MathProcessor.NAME:
                return new MathProcessor();
            case NullProcessor.NAME:
                return new NullProcessor();
            case ConverterProcessor.NAME:
                return new ConverterProcessor();
            default:
                return new NullProcessor();
        }
    }

}
