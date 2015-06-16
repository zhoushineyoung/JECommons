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
package org.jevis.commons.unit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.measure.converter.UnitConverter;
import javax.measure.unit.Unit;
import org.jevis.api.JEVisUnit;
import org.jevis.commons.json.JsonUnit;

/**
 * JEVisUnit implementation based on the javax.measure.unit
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class JEVisUnitImp implements JEVisUnit {

    private Unit _unit = Unit.ONE;
    private String _label = "";
    private Prefix _prefix = Prefix.NONE;
    private JsonUnit _json = null;

    public JEVisUnitImp(Unit unit) {
        _unit = unit;
    }

    public JEVisUnitImp(JsonUnit json) {
        _json = json;
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println("gson: " + gson.toJson(json, JsonUnit.class));
        _label = json.getLabel();
        _prefix = UnitManager.getInstance().getPrefix(json.getPrefix(), Locale.getDefault());

        Unit productUnit = null;
        List<JEVisUnitImp> subUnits = new ArrayList<>();
        if (json.getSubUnits() != null) {
            for (JsonUnit subu : json.getSubUnits()) {
                JEVisUnitImp newSub = new JEVisUnitImp(subu);
                subUnits.add(newSub);
            }

            for (JEVisUnitImp subu : subUnits) {
                if (productUnit == null) {
                    productUnit = subu.getUnit();
                } else {
                    switch (json.getFunction()) {
                        case "times":
                            productUnit = productUnit.times(UnitManager.getInstance().getUnitWithPrefix(subu.getUnit(), subu.getPrefix()));
                            break;
                        case "divided":
                            productUnit = productUnit.divide(UnitManager.getInstance().getUnitWithPrefix(subu.getUnit(), subu.getPrefix()));
                            break;
                    }
                }
            }
        }

//        System.out.println("_prefix: " + _prefix);
        if (productUnit != null) {
//            System.out.println("Proct Unit: " + productUnit);
            _unit = productUnit;
        } else {

//            System.out.println("json.symbol: " + json.getSymbol());
            _unit = Unit.valueOf(json.getSymbol());
        }

    }

    @Override
    public String toJSON() {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new GsonBuilder().create();
        JsonUnit junit = new JsonUnit();
        junit.setSymbol(_unit.toString());
        junit.setPrefix(UnitManager.getInstance().getPrefixName(_prefix, Locale.getDefault()));

        return gson.toJson(junit, JsonUnit.class);
    }

    public Unit toUnit() {

        return UnitManager.getInstance().getUnitWithPrefix(_unit, getPrefix());
//        return _unit;
    }

    /**
     *
     * @param unit
     * @param label
     * @param prefix
     */
    public JEVisUnitImp(Unit unit, String label, Prefix prefix) {
//        System.out.println("new JEVisUnitImp1: " + unit + " - " + label + " - " + prefix);
        try {
            _unit = unit;
        } catch (Exception ex) {
            System.out.println("Warning! Could not parse unit: '" + unit + "' " + ex.getMessage());
        }
        _label = label;
        _prefix = prefix;
    }

    /**
     * Create an new JEVisUnit from an JEVisUnit string
     *
     * @param prefix
     * @TODO example of an string
     *
     * @param unit
     * @param label
     */
    public JEVisUnitImp(String unit, String label, Prefix prefix) {
//        System.out.println("new JEVisUnitImp2: " + unit + " - " + label + " - " + prefix);
        UnitFormula up = new UnitFormula(unit, label);
        _unit = up.getUnit();
        _label = label;
        _prefix = prefix;
    }

    @Override
    public void setPrefix(Prefix prefix) {
        //TODO remove pre prefix?
        _prefix = prefix;
    }

    @Override
    public Prefix getPrefix() {
        return _prefix;
    }

    public Unit getUnit() {
        return _unit;
    }

    @Override
    public String getLabel() {
        return _label;
    }

    @Override
    public void setLabel(String label) {
        _label = label;
    }

    @Override
    public double converteTo(JEVisUnit unit, double number) {
        //TODo check if unit is compatible

        Unit targetUnit = UnitManager.getInstance().getUnitWithPrefix(((JEVisUnitImp) unit).getUnit(), unit.getPrefix());
        Unit sourceUnit = UnitManager.getInstance().getUnitWithPrefix(_unit, getPrefix());

        UnitConverter uc = sourceUnit.getConverterTo(targetUnit);
        return uc.convert(number);
    }

    @Override
    public JEVisUnit plus(double offset) {
        Unit newUnit = getUnit().plus(offset);
        return new JEVisUnitImp(newUnit);
    }

    @Override
    public JEVisUnit times(double factor) {
        Unit newUnit = getUnit().times(factor);
        return new JEVisUnitImp(newUnit);
    }

    @Override
    public JEVisUnit times(JEVisUnit factor) {
        Unit newUnit = getUnit().times(((JEVisUnitImp) factor).getUnit());
        return new JEVisUnitImp(newUnit);
    }

    @Override
    public JEVisUnit divide(double factor) {
        Unit newUnit = getUnit().divide(factor);
        return new JEVisUnitImp(newUnit);
    }

    @Override
    public JEVisUnit divide(JEVisUnit factor) {
        Unit newUnit = getUnit().divide(((JEVisUnitImp) factor).getUnit());
        return new JEVisUnitImp(newUnit);
    }

    @Override
    public boolean isCompatible(JEVisUnit unit) {
        return getUnit().isCompatible(((JEVisUnitImp) unit).getUnit());
    }

    @Override
    public String toString() {

        String formatted = _unit.toString().replace("·", "");

        return UnitManager.getInstance().getPrefixChar(_prefix) + formatted;

//        System.out.println("print unit: " + _unit + " label: " + _label);
////        String outstring = _unit != null ? _unit.toString() : "-/-";
//        try {
//            return UnitManager.getInstance().formate(UnitManager.getInstance().getUnitWithPrefix(_unit, _prefix));
////            return _unit.toString();
//        } catch (Exception ex) {
//
//        }
//        return "-/-";
//                        return _unit ?  : _unit.toString() : "-/-";
//        return _unit.toString() + "(" + _label + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final JEVisUnitImp other = (JEVisUnitImp) obj;
        if (!Objects.equals(this._unit, other._unit)) {
            return false;
        }
        return true;
    }

}
