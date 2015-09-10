/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.driver;

import java.io.InputStream;

/**
 *
 * @author Broder
 */
public interface Converter {

    public void convertInput(InputStream input);

    public Object getConvertedInput(Class convertedClass);

}
