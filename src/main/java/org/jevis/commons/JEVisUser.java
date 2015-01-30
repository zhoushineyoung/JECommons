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
import org.jevis.api.JEVisConstants;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisRelationship;

/**
 * This class helps working with JEVisObjects from the JEVisClass User
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class JEVisUser {

    final JEVisObject _userObj;

    public JEVisUser(JEVisObject userObj) throws JEVisException, IllegalArgumentException {
        if (!userObj.getJEVisClass().getName().equals("User")) {
            throw new IllegalArgumentException("This Object is not from the JEVisClass User");
        }
        _userObj = userObj;
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
