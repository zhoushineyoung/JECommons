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
    private final Long _target;
    private final String _mappingIdentifier;
    private final String _valueIdentifier;
    private final String _decimalSep;
    private final String _thousandSep;

//    public XMLDatapointParser(boolean incsv, Long datapoint) {
//        _isInFile = incsv;
//        _datapoint = datapoint;
//    }

    public XMLDatapointParser(Long datapointID, String target, String mappingIdentifier, String valueIdentifier, String decimalSeperator, String thousandSeperator) {
        _datapoint = datapointID;
        _target = Long.parseLong(target);
        _mappingIdentifier = mappingIdentifier;
        _valueIdentifier = valueIdentifier;
        _decimalSep = decimalSeperator;
        _thousandSep = thousandSeperator;
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
        return _target;
    }
    
    public String getValueIdentifier(){
        return _valueIdentifier;
    }
}
