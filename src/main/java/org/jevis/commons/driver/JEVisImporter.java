/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import org.jevis.commons.JEVisTypes;
import org.jevis.commons.parsing.Result;
import org.jevis.commons.parsing.TimeConverter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 *
 * @author bf
 */
public class JEVisImporter implements Importer {

    private JEVisDataSource _client = null;
    private JEVisObject httpObject;
    private DateTimeZone _timezone;

    @Override
    public boolean importResult(List<Result> results) {
        try {
            Logger.getLogger(JEVisImporter.class.getName()).log(Level.INFO, "Number of imported Data overall: " + results.size());
            if (results.isEmpty()) {
                return false;
            }

            //look into all results and map the sample to the online node
            Map<JEVisObject, List<JEVisSample>> onlineToSampleMap = new HashMap<JEVisObject, List<JEVisSample>>();
            for (Result s : results) {
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
            }

            for (JEVisObject o : onlineToSampleMap.keySet()) {
                List<JEVisSample> samples = onlineToSampleMap.get(o);
                Logger.getLogger(JEVisImporter.class.getName()).log(Level.INFO, samples.size() + " Samples import into ID");
                o.getAttribute("Value").addSamples(samples);
                Logger.getLogger(JEVisImporter.class.getName()).log(Level.INFO, samples.size() + " Samples imported into ID");
            }
            Logger.getLogger(JEVisImporter.class.getName()).log(Level.INFO, " Finish imported into ID");
        } catch (JEVisException ex) {
            Logger.getLogger(JEVisImporter.class.getName()).log(Level.ERROR, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public void initialize(JEVisObject dataSource) {
        try {
            _client = dataSource.getDataSource();
            JEVisClass dataSourceClass = _client.getJEVisClass(JEVisTypes.DataServer.NAME);
            JEVisType timezoneType = dataSourceClass.getType(JEVisTypes.DataServer.TIMEZONE);
            String timezone = DatabaseHelper.getObjectAsString(dataSource, timezoneType);
            _timezone = DateTimeZone.forID(timezone);
        } catch (JEVisException ex) {
            java.util.logging.Logger.getLogger(JEVisImporter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
