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
package org.jevis.commons.config;

import java.util.Objects;
import org.jevis.api.JEVisOption;

/**
 * Basic implementation of the JEVisOption interface.
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class BasicOption implements JEVisOption {

    private String group;
    private String value;
    private String key;
    private boolean required;
    private String description;

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean isRequired() {
        return this.required;
    }

    @Override
    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "JEVisOption{ Group: '" + group + "' Key: '" + key + "' value:'" + value + "' }";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.group);
        hash = 97 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasicOption other = (BasicOption) obj;
        if (!Objects.equals(this.group, other.group)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }

}
