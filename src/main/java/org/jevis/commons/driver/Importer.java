/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.driver;

import java.util.List;
import org.jevis.api.JEVisObject;

/**
 *
 * @author Broder
 */
public interface Importer {

    public boolean importResult(List<Result> result);

    public void initialize(JEVisObject dataSource);

}
