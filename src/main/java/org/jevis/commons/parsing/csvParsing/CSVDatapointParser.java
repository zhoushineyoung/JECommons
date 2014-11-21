package org.jevis.commons.parsing.csvParsing;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jevis.commons.parsing.GeneralMappingParser;
import org.jevis.commons.parsing.inputHandler.InputHandler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * The config file for a simple csv file
 *
 * @author broder
 */
public class CSVDatapointParser implements GeneralMappingParser {

    private boolean _inCSV;
    private int _indexDatapoint;
    private Long _datapoint;
    private boolean _mappingIsSuccessfull;
    private String _mappingValue;
    private boolean _isValid;
    private int _valueIndex;
    private boolean _valueValid;
    private boolean _outofBounce;
    private String _decimalSep;
    private String _thousandSep;
    private double _value;
    private long _targetID;

    public CSVDatapointParser(boolean incsv, Long datapoint, String mappingValue, Integer indexDatapoint) {
        _inCSV = incsv;
        if (indexDatapoint != null) {
            _indexDatapoint = indexDatapoint - 1;
        }
        _mappingValue = mappingValue;
        _datapoint = datapoint;

    }

    CSVDatapointParser(Long datapointID, Integer indexDatapoint, String target, String mappingIdentifier, String valueIdentifier, String decimalSep, String thousandSep) {
        if (indexDatapoint != null) {
            _indexDatapoint = indexDatapoint;
        }
        _mappingValue = mappingIdentifier;
        _datapoint = datapointID;
        Integer valuIdent = Integer.parseInt(valueIdentifier);
        _valueIndex = valuIdent - 1;
        _decimalSep = decimalSep;
        _thousandSep = thousandSep;
        if (target != null) {
            _targetID = Long.parseLong(target);
        }
    }

    public int getDatapointIndex() {
        return _indexDatapoint;
    }

    @Override
    public boolean isInFile() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getDatapoint() {
        return _datapoint;
    }

    @Override
    public void parse(InputHandler ic) {
        String[] line = ic.getCSVInput();
        try {
            _mappingIsSuccessfull = false;
            String currentMappingValue = line[_indexDatapoint];
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "##Line: " + line);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "##currentMappingValue: " + currentMappingValue);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "##_mappingValue: " + _mappingValue);
            if (currentMappingValue.equals(_mappingValue)) {
                _mappingIsSuccessfull = true;
            }
        } catch (Exception ex) {
            _isValid = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARN, "Date not valud in line: " + line);
        }


        _valueValid = false;
        _outofBounce = false;
        String sVal = null;
        try {
            sVal = line[_valueIndex];
            if (_thousandSep != null && !_thousandSep.equals("")) {
                sVal = sVal.replaceAll("\\" + _thousandSep, "");
            }
            if (_decimalSep != null && !_decimalSep.equals("")) {
                sVal = sVal.replaceAll("\\" + _decimalSep, ".");
            }
            _value = Double.parseDouble(sVal);
            _valueValid = true;
        } catch (NumberFormatException nfe) {
//            System.out.println("Value is wrong " + sVal);
        } catch (ArrayIndexOutOfBoundsException oob) {
            _outofBounce = true;
        }
    }

    @Override
    public String getMappingValue() {
        return _mappingValue;
    }

    @Override
    public boolean isMappingSuccessfull() {
        return _mappingIsSuccessfull;
    }

    @Override
    public boolean isValueValid() {
        return _isValid;
    }

    public boolean outOfBounce() {
        return _outofBounce;
    }

    public double getValue() {
        return _value;
    }

    public Long getTarget() {
        return _targetID;
    }
}
