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

import java.util.ArrayList;
import java.util.List;
import org.jevis.api.JEVisConfiguration;
import org.jevis.api.JEVisOption;

/**
 * Basic implementation of the JEVisConfiguration.
 *
 * @author Florian Simon
 */
public class BasicConfiguration implements JEVisConfiguration {

    private List<JEVisOption> options = new ArrayList();

    @Override
    public void addOption(JEVisOption option, boolean overwrite) {
        if (hasOption(option.getGroup(), option.getKey()) && !overwrite) {
            return;
        }
        options.add(option);
    }

    @Override
    public void completeWith(JEVisConfiguration other) {
        for (JEVisOption otherOpt : other.getOptions()) {
            if (hasOption(otherOpt.getGroup(), otherOpt.getKey())) {
            } else {
                options.add(otherOpt);
            }
        }

    }

    @Override
    public boolean hasOption(JEVisOption option) {
        for (JEVisOption opt : options) {
            if (opt.equals(option)) {
                return opt.equals(option);
            }
        }
        return false;
    }

    @Override
    public boolean hasOption(String group, String key) {
        for (JEVisOption option : options) {
            if (option.getGroup().equals(group) && option.getKey().equals(key)) {
                return true;
            }
        }
//        System.out.println("false");
        return false;
    }

    @Override
    public List<JEVisOption> getOptions() {
        return options;
    }

    @Override
    public JEVisOption getOption(JEVisOption option) {
        for (JEVisOption opt : options) {
            if (opt.equals(option)) {
                return opt;
            }
        }
        return option;
    }

    @Override
    public JEVisOption getOption(String group, String key) {
        for (JEVisOption option : options) {
            if (option.getKey().equals(key) && option.getGroup().equals(group)) {
                return option;
            }
        }
        return OptionFactory.BuildOption(group, key, "", "", false);
    }

    @Override
    public String toString() {
        String tostring = "JEVisConfiguration:";
        for (JEVisOption option : options) {
            tostring += "\n" + option.toString();
        }
        return tostring;
    }

}
