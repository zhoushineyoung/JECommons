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
package org.jevis.commons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jevis.api.JEVisClass;
import org.jevis.api.JEVisClassRelationship;
import org.jevis.api.JEVisDataSource;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisType;

/**
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class NullJEVisClass implements JEVisClass {

    private String _name = "Null Class";

    public NullJEVisClass(String name) {
        _name = name;
    }

    @Override
    public String getName() throws JEVisException {
        return _name;
    }

    @Override
    public void setName(String name) throws JEVisException {
        _name = name;
    }

    @Override
    public BufferedImage getIcon() throws JEVisException {
        return new BufferedImage(1, 1, 1);
    }

    @Override
    public void setIcon(BufferedImage icon) throws JEVisException {

    }

    @Override
    public void setIcon(File icon) throws JEVisException {

    }

    @Override
    public String getDescription() throws JEVisException {
        return "This is an Null Class which should not exist. If you see this class the something is wrong";
    }

    @Override
    public void setDescription(String discription) throws JEVisException {

    }

    @Override
    public List<JEVisType> getTypes() throws JEVisException {
        return new ArrayList<>();
    }

    @Override
    public JEVisType getType(String typename) throws JEVisException {
        return null;
    }

    @Override
    public JEVisType buildType(String name) throws JEVisException {
        return null;
    }

    @Override
    public JEVisClass getInheritance() throws JEVisException {
        return null;
    }

    @Override
    public List<JEVisClass> getHeirs() throws JEVisException {
        return new ArrayList<>();
    }

    @Override
    public List<JEVisClass> getValidParents() throws JEVisException {
        return new ArrayList<>();
    }

    @Override
    public boolean isAllowedUnder(JEVisClass jevisClass) throws JEVisException {
        return false;
    }

    @Override
    public boolean isUnique() throws JEVisException {
        return false;
    }

    @Override
    public void setUnique(boolean unique) throws JEVisException {

    }

    @Override
    public boolean delete() throws JEVisException {
        return false;
    }

    @Override
    public List<JEVisClassRelationship> getRelationships() throws JEVisException {
        return new ArrayList<>();
    }

    @Override
    public List<JEVisClassRelationship> getRelationships(int type) throws JEVisException {
        return new ArrayList<>();
    }

    @Override
    public List<JEVisClassRelationship> getRelationships(int type, int direction) throws JEVisException {
        return new ArrayList<>();
    }

    @Override
    public JEVisClassRelationship buildRelationship(JEVisClass jclass, int type, int direction) throws JEVisException {
        return null;
    }

    @Override
    public void deleteRelationship(JEVisClassRelationship rel) throws JEVisException {

    }

    @Override
    public JEVisDataSource getDataSource() throws JEVisException {
        return null;
    }

    @Override
    public void commit() throws JEVisException {

    }

    @Override
    public void rollBack() throws JEVisException {

    }

    @Override
    public boolean hasChanged() {
        return false;
    }

    @Override
    public int compareTo(JEVisClass o) {
        return 2;
    }

}
