/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing;

import java.util.List;
import org.jevis.api.JEVisObject;
import org.jevis.commons.JEVisTypes.DataPoint;
import org.jevis.commons.parsing.inputHandler.InputHandler;

/**
 *
 * @author bf
 */
public interface DataCollectorParser {

    public List<Result> getResults();

    public void parse(InputHandler ic);

    public void initialize(JEVisObject equipment);

    public void addDataPointParser(Long datapointID, String target, String mappingIdentifier, String valueIdentifier);
}
