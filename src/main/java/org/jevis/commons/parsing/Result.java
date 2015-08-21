/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing;

import org.joda.time.DateTime;

/**
 *
 * @author broder
 */
public class Result implements Comparable<Result> {

    private Object _value;
    private DateTime _date;
    private Long _datapoint;

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

    @Override
    public int compareTo(Result o) {
        if (_date.isBefore(o.getDate())) {
            return -1;
        }
        if (_date.isAfter(o.getDate())) {
            return 1;
        }
        return 0; //they are equal
    }
}
