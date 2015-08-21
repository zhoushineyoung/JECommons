/**
 * Copyright (C) 2015 Envidatec GmbH <info@envidatec.com>
 *
 * This file is part of JECommons.
 *
 * JECommons is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation in version 3.
 *
 * JECommons is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * JECommons. If not, see <http://www.gnu.org/licenses/>.
 *
 * JECommons is part of the OpenJEVis project, further project information are
 * published at <http://www.OpenJEVis.org/>.
 */
package org.jevis.commons.datasource;

import org.jevis.api.JEVisDataSource;
import org.jevis.api.JEVisOption;
import org.jevis.commons.config.CommonOptions;

/**
 * The DataSourceLoader helps with the dynamic loading of a JEVisDataSource
 *
 * @author Florian Simon
 */
public class DataSourceLoader {

    public DataSourceLoader() {
    }

    /**
     * Load a JEVisDataSource based on a JEVisConfiguration object. The
     * DataSource is not initialized yet.
     *
     * @param config
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public JEVisDataSource getDataSource(JEVisOption config) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (config.getKey().equalsIgnoreCase(CommonOptions.DataSoure.DataSoure.getKey())) {

            if (config.hasOption(CommonOptions.DataSoure.CLASS.getKey())) {
                JEVisOption classOption = config.getOption(CommonOptions.DataSoure.CLASS.getKey());
                String className = classOption.getValue();
                JEVisDataSource ds = JEVisDataSource.class.cast(Class.forName(className).newInstance());
//                config.completeWith(ds.getConfiguration());

                return ds;
            } else {
                throw new ClassNotFoundException("Class name option not found");
            }

        } else {
            throw new ClassNotFoundException("DataSource option group not found");
        }

    }

    /**
     * Get a DataSource by class name
     *
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public JEVisDataSource getDataSource(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        JEVisDataSource ds = JEVisDataSource.class.cast(Class.forName(className).newInstance());
        return ds;
    }

    /**
     * instantiate the class by name
     *
     * @param <T>
     * @param className
     * @param type
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T instantiate(final String className, final Class<T> type) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return type.cast(Class.forName(className).newInstance());
    }

}
