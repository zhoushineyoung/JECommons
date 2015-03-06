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
    private Integer _indexDatapoint;
    private Long _datapoint;
    private boolean _mappingError;
    private String _mappingValue;
    private Integer _valueIndex;
    private boolean _valueValid;
    private boolean _outofBounce;
    private String _decimalSep;
    private String _thousandSep;
    private Double _value;
    private long _targetID;

    public CSVDatapointParser(boolean incsv, Long datapoint, String mappingValue, Integer indexDatapoint) {
        _inCSV = incsv;
        if (indexDatapoint != null) {
            _indexDatapoint = indexDatapoint;
        }
        _mappingValue = mappingValue;
        _datapoint = datapoint;

    }

    public CSVDatapointParser(Long datapointID, Integer indexDatapoint, String target, String mappingIdentifier, String valueIdentifier, String decimalSep, String thousandSep) {
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
        _mappingError = false;
        try {
            String currentMappingValue = null;
            if (_indexDatapoint != null) {
                currentMappingValue = line[_indexDatapoint];
            }
            if (_mappingValue != null && !currentMappingValue.equals(_mappingValue)) {
                _mappingError = true;
            }
        } catch (Exception ex) {
            _valueValid = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARN, "This line in the file is not valid: " + line);
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
        } catch (Exception nfe) {
            _valueValid = false;
        }
    }

    @Override
    public String getMappingValue() {
        return _mappingValue;
    }

    @Override
    public boolean isMappingFailing() {
        return _mappingError;
    }

    @Override
    public boolean isValueValid() {
        return _valueValid;
    }

    @Override
    public boolean outOfBounce() {
        return _outofBounce;
    }

    @Override
    public double getValue() {
        return _value;
    }

    @Override
    public Long getTarget() {
        return _targetID;
    }

    public int getValueIndex() {
        return _valueIndex;
    }
}
