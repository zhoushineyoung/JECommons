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
package org.jevis.commons.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import org.jevis.commons.dataprocessing.Task;

/**
 * Simplistic Class to help converting an Task to an json object/string.
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class JsonTask {

    private String processor;
    private Map<String, String> options;
    private List<JsonTask> tasks = new ArrayList<>();
    private String id;

    public JsonTask() {
    }

    public JsonTask(Task task) {
        processor = task.getProcessor().getName();
        options = task.getOptions();
        id = task.getID();
        for (Task sTask : task.getSubTasks()) {
            tasks.add(new JsonTask(sTask));
        }

    }

    @XmlElement(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "processor")
    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @XmlElement(name = "options")
    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public List<JsonTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<JsonTask> tasks) {
        this.tasks = tasks;
    }

}
