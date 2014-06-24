/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing;

import org.jevis.commons.parsing.inputHandler.InputHandler;


/**
 * Interface for parsing a line in a csv File
 * 
 * @author broder
 */
interface GeneralParser {

    public void parse(InputHandler ic);
}
