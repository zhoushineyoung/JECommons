/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.driver;

import org.jevis.api.JEVisDataSource;
import org.jevis.api.JEVisObject;
import org.jevis.commons.parsing.inputHandler.GenericConverter;

/**
 *
 * @author Broder
 */
public class ConverterFactory {

    public static void initializeConverter(JEVisDataSource _client) {
    }

    public static Converter getConverter(JEVisObject _dataSourceJEVis) {
        return new GenericConverter();
    }

}
