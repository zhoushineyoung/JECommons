/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.driver.datasourcce;

import java.io.InputStream;
import java.util.List;
import org.jevis.api.JEVisObject;

/**
 * The interface for the DataSource. Each DataSource object represents a data
 * source in the JEVis System at runtime.
 *
 * @author Broder
 */
public interface DataSource extends Runnable {

    @Override
    public void run();

    /**
     * Initialize the Data source. For the generic data sources all attributes
     * under the data source object in the JEVis System are loaded.
     *
     * @param _dataSourceJEVis
     */
    public void initialize(JEVisObject _dataSourceJEVis);

    /**
     * Sends the sample request to the data source and gets the data from the
     * query.
     *
     * @param channel
     * @return
     */
    public List<InputStream> sendSampleRequest(JEVisObject channel);

    /**
     * Parse the data from the input from the data source query. There
     *
     * @param input
     */
    public void parse(List<InputStream> input);

    /**
     * Imports the result.
     *
     */
    public void importResult();

}
