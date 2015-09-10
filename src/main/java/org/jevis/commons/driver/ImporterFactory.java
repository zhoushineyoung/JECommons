/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.driver;

import org.jevis.api.JEVisDataSource;
import org.jevis.api.JEVisObject;

/**
 *
 * @author Broder
 */
public class ImporterFactory {

    public static Importer getImporter(JEVisObject _dataSourceJEVis) {
        return new JEVisImporter();
    }

    public static void initializeImporter(JEVisDataSource _client) {
    }

}
