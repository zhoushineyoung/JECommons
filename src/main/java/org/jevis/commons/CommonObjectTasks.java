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

import org.jevis.api.JEVisConstants;
import org.jevis.api.JEVisObject;
import org.jevis.api.JEVisRelationship;

/**
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class CommonObjectTasks {

    public static JEVisRelationship createLink(JEVisObject linkObj, JEVisObject targetObject) throws Exception {
        //TODO handel inherited
        if (!linkObj.getJEVisClass().getName().equals(CommonClasses.LINK.NAME)) {
            throw new IllegalArgumentException("Link Object is not from the Class " + CommonClasses.LINK.NAME);
        }

        return linkObj.buildRelationship(targetObject, JEVisConstants.ObjectRelationship.LINK, JEVisConstants.Direction.FORWARD);

    }
}
