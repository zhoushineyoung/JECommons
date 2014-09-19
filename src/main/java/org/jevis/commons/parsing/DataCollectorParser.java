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

    public List<Result> getResults();

    public abstract void parse(InputHandler ic);

    abstract public void initialize(JEVisObject equipment);
    
    public void createSampleContainers(JEVisObject parser, List<JEVisObject> datapoints);
    
    public List<SampleParserContainer> getSampleParserContianers();
}
