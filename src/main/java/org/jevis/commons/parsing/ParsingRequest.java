/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing;

import org.jevis.api.JEVisDataSource;
import org.joda.time.DateTimeZone;

/**
 *
 * @author bf
 */
public interface ParsingRequest {

    public DateTimeZone getTimezone();

    public void setTimeZone(DateTimeZone timeZone);

    public void setOutputType(String importType);

    public String getOutputType();

    public void setFileOutputPath(String outputPath);

    public String getFileOutputPath();
    
    public JEVisDataSource getClient();
    
    public void setJEVisDataSource(JEVisDataSource datasource);
}
