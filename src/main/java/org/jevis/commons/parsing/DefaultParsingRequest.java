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
public class DefaultParsingRequest implements ParsingRequest {

    private DateTimeZone _dateTimeZone;
    private String _importType;
    private String _outputPath;
    private JEVisDataSource _datasource;

   @Override
    public DateTimeZone getTimezone() {
        return _dateTimeZone;
    }

    @Override
    public void setTimeZone(DateTimeZone timeZone) {
        _dateTimeZone = timeZone;
    }

    @Override
    public void setOutputType(String importType) {
        _importType = importType;
    }

    @Override
    public String getOutputType() {
        return _importType;
    }

    @Override
    public void setFileOutputPath(String outputPath) {
        _outputPath = outputPath;
    }

    @Override
    public String getFileOutputPath() {
        return _outputPath;
    }

    @Override
    public JEVisDataSource getClient() {
        return _datasource;
    }

    @Override
    public void setJEVisDataSource(JEVisDataSource datasource) {
        _datasource = datasource;
    }
}
