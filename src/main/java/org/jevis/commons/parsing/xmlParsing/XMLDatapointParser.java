/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing.xmlParsing;

import org.jevis.commons.parsing.GeneralMappingParser;
import org.jevis.commons.parsing.inputHandler.InputHandler;


/**
 *
 * @author bf
 */
public class XMLDatapointParser implements GeneralMappingParser {

    private boolean _isInFile;
    private Long _datapoint;

    public XMLDatapointParser(boolean incsv, Long datapoint) {
        _isInFile = incsv;
        _datapoint = datapoint;
    }

    @Override
    public boolean isInFile() {
        return _isInFile;
    }

    @Override
    public Long getDatapoint() {
        return _datapoint;
    }

    @Override
    public void parse(InputHandler ic) {
        //no parsing necessary
    }

    @Override
    public String getMappingValue() {
        return null; //no mapping value
    }

    @Override
    public boolean isMappingSuccessfull() {
        return true; //no mapping needed
    }

    @Override
    public boolean isValueValid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean outOfBounce() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTarget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
