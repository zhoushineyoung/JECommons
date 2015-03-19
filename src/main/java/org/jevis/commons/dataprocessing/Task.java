/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.dataprocessing;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.jevis.api.JEVisDataSource;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisSample;

/**
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
@XmlRootElement(name = "Task")
public interface Task {

    /**
     * Set the JEVisDatasource
     *
     * @param ds
     */
    void setJEVisDataSource(JEVisDataSource ds);

    /**
     * Returns the JEVisDatasource
     *
     * @return
     */
    JEVisDataSource getJEVisDataSource();

    /**
     * Set the ID of this Task. The ID should be Unique unter the parent task.
     *
     * @param id
     */
    void setID(String id);

    /**
     * Retuns the ID of this Task. The ID should be Unique unter the parent
     * task.
     *
     * @return
     */
    @XmlElement(name = "id")
    String getID();

    /**
     * Add an option to this task
     *
     * @param option
     */
    void addOption(String key, String option);

    /**
     * Return an option by its key.
     *
     * @param key
     * @return
     */
    String getOption(String key);

    /**
     * Returns an list of all Options for this task
     *
     * @return
     */
    @XmlElement(name = "options")
    Map<String, String> getOptions();

    /**
     * Returns the processor type.
     *
     * @return
     */
    @XmlElement(name = "processor")
    DataProcessor getProcessor();

    /**
     * Set The processor used to this task
     *
     * @param processor
     */
    void setProcessor(DataProcessor processor);

    /**
     * Set the options for this task
     *
     * @param options
     */
    void setOptions(Map<String, String> options);

//    List<JEVisSample> getImputSamples();
//    void setInputSamples(List<List<JEVisSample>> samples);
//    void setResult(List<JEVisSample> result);
    /**
     * returns the result of this task
     *
     * @return
     */
    List<JEVisSample> getResult();

    void setSubTasks(List<Task> tasks);

    List<Task> getSubTasks();

    public void setObject(JEVisObject object);

    public JEVisObject getObject();

    public void restResult();

}
