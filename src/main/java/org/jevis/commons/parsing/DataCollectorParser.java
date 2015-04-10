/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing;

import java.util.List;
import org.jevis.api.JEVisObject;
import org.jevis.commons.parsing.inputHandler.InputHandler;

/**
 *
 * @author bf
 */
public interface DataCollectorParser {

    /**
     * Returns the parsed samples. These samples will be imported into the JEVis database.
     * 
     * @return 
     */
    public List<Result> getResults();

    /**
     * The parsing process. The InputHandler contains the fetched information from the DataSource.
     * 
     * @param ic 
     */
    public void parse(InputHandler ic);

    /**
     * The initialization with the parsing JEVisObject from the JEVis system.
     * 
     * @param equipment 
     */
    public void initialize(JEVisObject equipment);

    /**
     * The information of the several datapoints. This method is called for all datapoints under the given DataSource object.
     * 
     * @param datapointID : The ID of the datapoint in the JEVis system
     * @param target : The target where the information should be stored. If the samples should be stored in the JEVis system this will be the ID of the Raw Data row.
     * @param mappingIdentifier : The information for the mapping, if the data isnt mappable by the syntax. For example in a csv file with several columns for the data this parameter isnt needed.
     * @param valueIdentifier : The identifier for the value. For example in a csv file this will be the index of the column with the values.
     */
    public void addDataPointParser(Long datapointID, String target, String mappingIdentifier, String valueIdentifier);
}
