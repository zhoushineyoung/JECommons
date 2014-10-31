/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing;

/**
 *
 * @author broder
 */
public interface GeneralMappingParser extends GeneralParser{

    public boolean isInFile();

    public Long getDatapoint();
    
    public String getMappingValue();
    
    public boolean isMappingSuccessfull();
}
