/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jevis.api.JEVisClass;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisType;
import org.jevis.commons.DatabaseHelper;
import org.jevis.commons.JEVisTypes;

/**
 *
 * @author bf
 */
public class Driver {
    
    private String _dataSourceName;
    private String _connectionSourceName;
    private String _parserSourceName;
    private String _connectionClassName;
    private String _parserClassName;
    
    public void init(JEVisObject driverObject) {
        try {
            JEVisClass driverType = driverObject.getDataSource().getJEVisClass(JEVisTypes.Driver.NAME);
            JEVisType connectionType = driverType.getType(JEVisTypes.Driver.CONNECTION_SOURCE);
            JEVisType parserType = driverType.getType(JEVisTypes.Driver.PARSER_SOURCE);
            JEVisType connectionClassType = driverType.getType(JEVisTypes.Driver.CONNECTION_CLASS);
            JEVisType parserClassType = driverType.getType(JEVisTypes.Driver.PARSER_CLASS);
            JEVisType dataSourceNameType = driverType.getType(JEVisTypes.Driver.DATA_SOURCE_NAME);

//            setConnectionClassName(DatabaseHelper.getObjectAsString(driverObject, connectionClassType));
//            setParserClassName(DatabaseHelper.getObjectAsString(driverObject, parserClassType));
//            setConnectionSourceName(DatabaseHelper.getObjectAsString(driverObject, connectionType));
//            setParserSourceName(DatabaseHelper.getObjectAsString(driverObject, parserType));
//            setDataSourceName(DatabaseHelper.getObjectAsString(driverObject, dataSourceNameType));
            setDataSourceName("VIDA350");
        } catch (JEVisException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.ERROR, ex.getMessage());
        }
    }

    /**
     * @return the _dataSourceName
     */
    public String getDataSourceName() {
        return _dataSourceName;
    }

    /**
     * @param dataSourceName the _dataSourceName to set
     */
    public void setDataSourceName(String dataSourceName) {
        this._dataSourceName = dataSourceName;
    }

    /**
     * @return the _connectionName
     */
    public String getConnectionSourceName() {
        return _connectionSourceName;
    }

    /**
     * @param connectionName the _connectionName to set
     */
    public void setConnectionSourceName(String connectionName) {
        this._connectionSourceName = connectionName;
    }

    /**
     * @return the _parserName
     */
    public String getParserSourceName() {
        return _parserSourceName;
    }

    /**
     * @param parserName the _parserName to set
     */
    public void setParserSourceName(String parserName) {
        this._parserSourceName = parserName;
    }

    /**
     * @return the _connectionClassName
     */
    public String getConnectionClassName() {
        return _connectionClassName;
    }

    /**
     * @param connectionClassName the _connectionClassName to set
     */
    public void setConnectionClassName(String connectionClassName) {
        this._connectionClassName = connectionClassName;
    }

    /**
     * @return the _parserClassName
     */
    public String getParserClassName() {
        return _parserClassName;
    }

    /**
     * @param parserClassName the _parserClassName to set
     */
    public void setParserClassName(String parserClassName) {
        this._parserClassName = parserClassName;
    }
}
