/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.driver;

import org.joda.time.DateTime;

/**
 *
 * @author broder
 */
public class Result {

    private final Object _value;
    private final DateTime _date;
    private final Long _datapoint;

    public Result(Long datapoint, Object val, DateTime date) {
        _datapoint = datapoint;
        _value = val;
        _date = date;
    }

    public Object getValue() {
        return _value;
    }

    public DateTime getDate() {
        return _date;
    }

    public Long getOnlineID() {
        return _datapoint;
    }
}
