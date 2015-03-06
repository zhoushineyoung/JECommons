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
import org.jevis.commons.dataprocessing.processor.NullProcessor;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.jevis.api.JEVisDataSource;
import org.jevis.api.JEVisSample;
import org.jevis.commons.utils.Benchmark;

/**
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
@XmlRootElement(name = "Task")
public class TaskImp implements Task {

    private DataProcessor _processor = new NullProcessor();
    private Map<String, String> _options = new HashMap<>();
//    private List<List<JEVisSample>> _imputSamples = new ArrayList<>();
    private List<JEVisSample> _result;
    private List<Task> tasks = new ArrayList<>();
    private boolean isDone = false;
    private String _id = "*MISSING*";
    private JEVisDataSource _ds;

    public TaskImp() {
    }

    public TaskImp(JEVisDataSource ds, JsonTask task) {
        setOptions(task.getOptions());
        setProcessor(Tasks.getProcessor(task.getProcessor()));
        setID(task.getId());
        setJEVisDataSource(ds);
//        List<Task> subTask = new ArrayList<>();
        for (JsonTask jt : task.getTasks()) {
            System.out.println("make new subtak: " + jt);
            tasks.add(new TaskImp(ds, jt));
        }

    }

//
//    public TaskImp(DataProcessor processor, Options options, List<List<JEVisSample>> samples) {
//        _processor = processor;
//        _options = options;
//        _imputSamples = samples;
//    }
//
//    public TaskImp(DataProcessor processor, Options options, List<Task> preTask) {
//        _processor = processor;
//        _options = options;
//        _prevTask = preTask;
//    }
    @Override
    public void setID(String id) {
        _id = id;
    }

    @Override
    @XmlElement(name = "processor")
    public void setProcessor(DataProcessor processor) {
        System.out.println("setProcessor: " + processor.getName());
        _processor = processor;
    }

    @Override
    public DataProcessor getProcessor() {
        return _processor;
    }

//    @Override
//    public List<List<JEVisSample>> getImputSamples() {
//        if (_imputSamples == null && _prevTask != null) {
//            _imputSamples = new ArrayList<>();
//            for (Task task : _prevTask) {
//                _imputSamples.add(task.getResult());
//            }
//
//            return _imputSamples;
//        } else {
//            return _imputSamples;
//        }
//    }
    @Override
    public void setSubTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public List<Task> getSubTasks() {
        return tasks;

    }

    @Override
    public List<JEVisSample> getResult() {
        if (_result != null) {
            return _result;
        }

        if (!isDone) {
            if (getSubTasks().isEmpty()) {
//            System.out.println("[" + _id + "]  No more sub tasks!");

            } else {
                for (Task task : getSubTasks()) {
                    task.getResult();
                }
//            System.out.println("[" + _id + "] All subtask are done!");
            }
            isDone = true;

        }

        Benchmark bench = new Benchmark();
        _result = getProcessor().getResult(this);
        System.out.println("[" + _id + "] [" + _processor.getName() + "]  Result size: " + _result.size());

        bench.printBechmark(" Task " + getID());
        return _result;

//        if (getSubTasks().isEmpty()) {
////            System.out.println("[" + _id + "]  No more sub tasks!");
//
//        } else {
//            for (Task task : getSubTasks()) {
//                task.getResult();
//            }
////            System.out.println("[" + _id + "] All subtask are done!");
//        }
//        bench.printBechmark(" Task " + getID());
//        return getProcessor().getResult(this);
    }

    @Override
    public void setJEVisDataSource(JEVisDataSource ds) {
        _ds = ds;
    }

    @Override
    public JEVisDataSource getJEVisDataSource() {
        return _ds;
    }

    @Override
    @XmlElement(name = "id")
    public String getID() {
        return _id;
    }

    @Override
    public void addOption(String key, String option) {
        _options.put(key, option);
//        _options.put(option.getKey(), option);
    }

    @Override
    public String getOption(String key) {
        return _options.get(key);
    }

    @Override
    public Map<String, String> getOptions() {
        return _options;
    }

    @Override
    public void setOptions(Map<String, String> options) {
        _options = options;
    }

    @Override
    public String toString() {
        return "TaskImp{" + "_processor=" + _processor + ", _options=" + _options + ", _result=" + _result + ", _prevTask=" + tasks.size() + ", isDone=" + isDone + ", _id=" + _id + ", _ds=" + _ds + '}';
    }

    public void print() {
        System.out.println(toString());
        for (Task task : getSubTasks()) {
            ((TaskImp) task).print();
//            System.out.println("--- " + task.toString());
        }
    }

}
