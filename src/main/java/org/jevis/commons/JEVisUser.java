/**
 * Copyright (C) 2014 Envidatec GmbH <info@envidatec.com>
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
package org.jevis.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jevis.api.JEVisAttribute;
import org.jevis.api.JEVisConstants;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisRelationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class helps working with JEVisObjects from the JEVisClass User
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class JEVisUser {

    private JEVisObject _userObj;
    private boolean _isSysAdmin = false;
    private boolean _enabled = false;
    Logger logger = LoggerFactory.getLogger(JEVisUser.class);

    public JEVisUser(JEVisObject userObj, boolean enabled, boolean sysAdmin) throws JEVisException, IllegalArgumentException {
        if (!userObj.getJEVisClass().getName().equals("User")) {
            throw new IllegalArgumentException("This Object is not from the JEVisClass User");
        }
        _userObj = userObj;
        _isSysAdmin = sysAdmin;
        _enabled = enabled;

    }

    public JEVisUser() {
    }

    public JEVisUser(JEVisObject userObj) throws JEVisException, IllegalArgumentException {
        if (!userObj.getJEVisClass().getName().equals("User")) {
            throw new IllegalArgumentException("This Object is not from the JEVisClass User");
        }
        _userObj = userObj;

    }

    public boolean isSysAdmin() {
        return _isSysAdmin;
    }

    public void setSysAdmin(boolean isadmin) {
        _isSysAdmin = isadmin;
    }

    public boolean isEnabled() {
        return _enabled;
    }

    public void setEnabled(boolean enable) {
        _enabled = enable;
    }

    public void setObject(JEVisObject obj) {
        _userObj = obj;
    }

    public List<JEVisObject> getUserGroups() throws JEVisException {
        List<JEVisObject> groups = new ArrayList<>();
        List<JEVisRelationship> rels = _userObj.getRelationships(JEVisConstants.ObjectRelationship.MEMBER_READ, JEVisConstants.Direction.FORWARD);
        for (JEVisRelationship rel : rels) {
            if (!groups.contains(rel.getEndObject())) {
//                System.out.println("add group: " + rel.getEndObject());
                groups.add(rel.getEndObject());
            }
        }

        return groups;
    }

}
