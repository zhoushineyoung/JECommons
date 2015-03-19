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

import com.google.gson.Gson;
import org.jevis.api.JEVisAttribute;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.commons.json.JsonTask;

/**
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class ProcessorObjectHandler {

    public static Task getTask(JEVisObject object) throws JEVisException {
        if (!object.getJEVisClass().getName().equalsIgnoreCase("Data Processor")) {
            throw new IllegalArgumentException("Object is not from the Class Data Processor!");
        }

        JEVisAttribute taskAttribute = object.getAttribute("Task Description");
        String jsonString = taskAttribute.getLatestSample().getValueAsString();

        Gson gson = new Gson();
        JsonTask jTask = gson.fromJson(jsonString, JsonTask.class);
        Task newTask = new TaskImp(object.getDataSource(), jTask, object);
        return newTask;
    }

}
