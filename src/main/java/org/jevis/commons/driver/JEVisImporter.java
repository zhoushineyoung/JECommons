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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jevis.api.JEVisClass;
import org.jevis.api.JEVisDataSource;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisSample;
import org.jevis.api.JEVisType;
import org.jevis.commons.DatabaseHelper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import sun.misc.Launcher;

/**
 *
 * @author bf
 */
public class JEVisImporter implements Importer {

    private JEVisDataSource _client = null;
    private DateTimeZone _timezone;
//    private DateTime _latestDateTime;

    @Override
    public DateTime importResult(List<Result> results) {
        try {
            if (results.isEmpty()) {
                return null;
            }

            //look into all results and map the sample to the online node
            Map<JEVisObject, List<JEVisSample>> onlineToSampleMap = new HashMap<JEVisObject, List<JEVisSample>>();
            for (Result s : results) {
                try {
                    long onlineID = s.getOnlineID();
                    JEVisObject onlineData = null;
                    //look for the correct jevis object
                    for (JEVisObject j : onlineToSampleMap.keySet()) {
                        if (j.getID() == onlineID) {
                            onlineData = j;
                            break;
                        }
                    }
                    //jevis object is not in the map
                    if (onlineData == null) {
                        onlineData = _client.getObject(onlineID);
                        onlineToSampleMap.put(onlineData, new ArrayList<JEVisSample>());
                    }
                    List<JEVisSample> samples = onlineToSampleMap.get(onlineData);
                    DateTime convertedDate = TimeConverter.convertTime(_timezone, s.getDate());
                    JEVisSample sample = onlineData.getAttribute("Value").buildSample(convertedDate, s.getValue(), "Imported by JEDataCollector");
                    samples.add(sample);
                } catch (Exception ex1) {
                    Logger.getLogger(JEVisImporter.class.getName()).log(Level.ERROR, "Error while importing samples", ex1);
                }
            }
            DateTime lastDateTime = null;
            for (JEVisObject o : onlineToSampleMap.keySet()) {
                try {
                    List<JEVisSample> samples = onlineToSampleMap.get(o);
                    o.getAttribute("Value").addSamples(samples);
                    if (lastDateTime == null || o.getAttribute("Value").getLatestSample().getTimestamp().isBefore(lastDateTime)) {
                        lastDateTime = o.getAttribute("Value").getLatestSample().getTimestamp();
                    }
                    Logger.getLogger(JEVisImporter.class.getName()).log(Level.ALL, samples.size() + " Samples import into ID:" + o.getID());
                } catch (Exception ex2) {
                    Logger.getLogger(JEVisImporter.class.getName()).log(Level.ERROR, "Error while setting last readout", ex2);
                }
            }
            return lastDateTime;
        } catch (Exception ex) {
            Logger.getLogger(JEVisImporter.class.getName()).log(Level.ERROR, null, ex);
        }
        return null;
    }

    @Override
    public void initialize(JEVisObject dataSource) {
        try {
            _client = dataSource.getDataSource();
            JEVisClass dataSourceClass = _client.getJEVisClass(DataCollectorTypes.DataSource.NAME);
            JEVisType timezoneType = dataSourceClass.getType(DataCollectorTypes.DataSource.TIMEZONE);
            String timezone = DatabaseHelper.getObjectAsString(dataSource, timezoneType);
            _timezone = DateTimeZone.forID(timezone);
        } catch (JEVisException ex) {
            java.util.logging.Logger.getLogger(JEVisImporter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

//    @Override
//    public DateTime getLatestDatapoint() {
//        String toString = _latestDateTime.toString(DateTimeFormat.forPattern("HH:mm:ss dd.MM.yyyy"));
//        return _latestDateTime;
//    }
}
