/**
 * Copyright (C) 2015 Envidatec GmbH <info@envidatec.com>
 *
 * This file is part of JECommons.
 *
 * JECommons is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation in version 3.
 *
 * JECommons is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * JECommons. If not, see <http://www.gnu.org/licenses/>.
 *
 * JECommons is part of the OpenJEVis project, further project information are
 * published at <http://www.OpenJEVis.org/>.
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
