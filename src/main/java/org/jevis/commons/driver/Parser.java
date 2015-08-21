/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.driver;

import java.io.InputStream;
import java.util.List;
import org.jevis.api.JEVisObject;
import org.jevis.commons.parsing.Result;

/**
 *
 * @author Broder
 */
public interface Parser {

    /**
     * Initialize the parser.
     *
     * @param parserObject
     */
    public void initialize(JEVisObject parserObject);

    /**
     * Parse the input.
     *
     * @param input
     */
    public void parse(List<InputStream> input);

    /**
     * Gets the results from the parsing process.
     *
     * @return
     */
    public List<Result> getResult();

//    public void resetResult(); oder neuen parser?
}
