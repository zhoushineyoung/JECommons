/**
 * Copyright (C) 2014 Envidatec GmbH <info@envidatec.com>
 *
 * This file is part of JEApplication.
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.measure.quantity.*;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import org.jevis.api.JEVisAttribute;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisType;
import org.jscience.economics.money.Money;

/**
 *
 * @author fs
 */
public class UnitManager {

    private final static UnitManager unitManager = new UnitManager();
    private List<Unit> prefixes;
    private List<String> prefixes2;
    private List<Unit> quantities;
    private List<Unit> nonSI;
    private List<Unit> si;
    private List<Unit> additonalUnits;
    private HashMap<Unit, String> names;
    private HashMap<Unit, String> dimNames;

//    public static final Unit SELECT_LATER = Dimensionless.UNIT.ONE.alternate("SEL");
    public interface Prefix {

        public static String ZETTA = "Zetta";
        public static String EXA = "Exa";
        public static String PETA = "Peta";
        public static String TERA = "Tera";
        public static String GIGA = "Giga";
        public static String MEGA = "Mega";
        public static String KILO = "Kilo";
        public static String HECTO = "Hecto";
        public static String DEKA = "Deka";
        public static String DECI = "Deci";
        public static String CENTI = "Centi";
        public static String MILLI = "Milli";
        public static String MICRO = "Micro";
        public static String NANO = "Nano";
        public static String PICO = "Pico";
        public static String FEMTO = "Femto";
        public static String ATTO = "Atto";
        public static String ZEPTO = "Zepto";
        public static String YOCTO = "Yocto";

    }

    /* A private Constructor prevents any other 
     * class from instantiating.
     */
    private UnitManager() {
    }

    public static UnitManager getInstance() {
        return unitManager;
    }

    public List<Unit> getQuantities() {

        if (quantities != null) {
            return quantities;
        }
        quantities = new ArrayList<>();

        quantities.add(Money.BASE_UNIT);

        quantities.add(Acceleration.UNIT);
        quantities.add(AngularVelocity.UNIT);
        quantities.add(AmountOfSubstance.UNIT);
        quantities.add(Angle.UNIT);
        quantities.add(AngularAcceleration.UNIT);
        quantities.add(Area.UNIT);
        quantities.add(CatalyticActivity.UNIT);
        quantities.add(DataAmount.UNIT);
        quantities.add(DataRate.UNIT);
        quantities.add(Dimensionless.UNIT);
        quantities.add(Duration.UNIT);
        quantities.add(DynamicViscosity.UNIT);
        quantities.add(ElectricCapacitance.UNIT);
        quantities.add(ElectricConductance.UNIT);
        quantities.add(ElectricCharge.UNIT);
        quantities.add(ElectricCurrent.UNIT);
        quantities.add(ElectricInductance.UNIT);
        quantities.add(ElectricPotential.UNIT);
        quantities.add(ElectricResistance.UNIT);
        quantities.add(Energy.UNIT);
        quantities.add(Force.UNIT);
        quantities.add(Frequency.UNIT);
        quantities.add(Illuminance.UNIT);
        quantities.add(KinematicViscosity.UNIT);
        quantities.add(Length.UNIT);
        quantities.add(LuminousFlux.UNIT);
        quantities.add(LuminousIntensity.UNIT);
        quantities.add(Mass.UNIT);
        quantities.add(MagneticFlux.UNIT);
        quantities.add(MagneticFluxDensity.UNIT);
        quantities.add(MassFlowRate.UNIT);
        quantities.add(Power.UNIT);
        quantities.add(Pressure.UNIT);
        quantities.add(RadiationDoseAbsorbed.UNIT);
        quantities.add(RadiationDoseEffective.UNIT);
        quantities.add(RadioactiveActivity.UNIT);
        quantities.add(SolidAngle.UNIT);
        quantities.add(Temperature.UNIT);
        quantities.add(Torque.UNIT);
        quantities.add(Velocity.UNIT);
        quantities.add(Volume.UNIT);
        quantities.add(VolumetricDensity.UNIT);
        quantities.add(VolumetricFlowRate.UNIT);

        return quantities;
    }

    /**
     * This funktion will change in the future
     *
     * @return
     */
    public List<String> getPrefixes() {
        if (prefixes2 != null) {
            return prefixes2;
        }
        prefixes2 = new ArrayList<>();

        prefixes2.add("");
        prefixes2.add(Prefix.ZETTA);
        prefixes2.add(Prefix.EXA);
        prefixes2.add(Prefix.PETA);
        prefixes2.add(Prefix.TERA);
        prefixes2.add(Prefix.GIGA);
        prefixes2.add(Prefix.MEGA);
        prefixes2.add(Prefix.KILO);
        prefixes2.add(Prefix.HECTO);
        prefixes2.add(Prefix.DEKA);
        prefixes2.add(Prefix.DECI);
        prefixes2.add(Prefix.CENTI);
        prefixes2.add(Prefix.MILLI);
        prefixes2.add(Prefix.MICRO);
        prefixes2.add(Prefix.NANO);
        prefixes2.add(Prefix.PICO);
        prefixes2.add(Prefix.FEMTO);
        prefixes2.add(Prefix.ATTO);
        prefixes2.add(Prefix.ZEPTO);
        prefixes2.add(Prefix.YOCTO);

//        prefixes2.add("");
//        prefixes2.add("Zetta");
//        prefixes2.add("Exa");
//        prefixes2.add("Peta");
//        prefixes2.add("Tera");
//        prefixes2.add("Giga");
//        prefixes2.add("Mega");
//        prefixes2.add("Kilo");
//        prefixes2.add("Hecto");
//        prefixes2.add("Deka");
//        prefixes2.add("Deci");
//        prefixes2.add("Centi");
//        prefixes2.add("Milli");
//        prefixes2.add("Micro");
//        prefixes2.add("Nano");
//        prefixes2.add("Pico");
//        prefixes2.add("Femto");
//        prefixes2.add("Atto");
//        prefixes2.add("Zepto");
//        prefixes2.add("Yocto");
        return prefixes2;
    }

    public String getPrefixChar(String prefix) {
        /*
         public static String ZETTA = "Z";
         public static String EXA = "E";
         public static String PETA = "P";
         public static String TERA = "T";
         public static String GIGA = "G";
         public static String MEGA = "M";
         public static String KILO = "k";
         public static String HECTO = "h";
         public static String DEKA = "da";
         public static String DECI = "d";
         public static String CENTI = "c";
         public static String MILLI = "m";
         public static String MICRO = "µ";
         public static String NANO = "n";
         public static String PICO = "p";
         public static String FEMTO = "f";
         public static String ATTO = "a";
         public static String ZEPTO = "z";
         public static String YOCTO = "y";
         */

        switch (prefix) {
            case Prefix.ATTO:
                return "a";
            case Prefix.CENTI:
                return "c";
            case Prefix.DECI:
                return "d";
            case Prefix.DEKA:
                return "da";
            case Prefix.EXA:
                return "E";
            case Prefix.FEMTO:
                return "f";
            case Prefix.GIGA:
                return "G";
            case Prefix.HECTO:
                return "h";
            case Prefix.KILO:
                return "k";
            case Prefix.MEGA:
                return "m";
            case Prefix.MICRO:
                return "µ";
            case Prefix.MILLI:
                return "m";
            case Prefix.NANO:
                return "n";
            case Prefix.PETA:
                return "P";
            case Prefix.PICO:
                return "P";
            case Prefix.TERA:
                return "T";
            case Prefix.YOCTO:
                return "y";
            case Prefix.ZEPTO:
                return "Z";
            case Prefix.ZETTA:
                return "Z";
            default:
                throw new AssertionError();
        }
    }

    public Unit getUnitWithPrefix(Unit unit, String prefix) {
        switch (prefix) {
            case Prefix.ATTO:
                return SI.ATTO(unit);
            case Prefix.CENTI:
                return SI.CENTI(unit);
            case Prefix.DECI:
                return SI.DECI(unit);
            case Prefix.DEKA:
                return SI.DEKA(unit);
            case Prefix.EXA:
                return SI.EXA(unit);
            case Prefix.FEMTO:
                return SI.FEMTO(unit);
            case Prefix.GIGA:
                return SI.GIGA(unit);
            case Prefix.HECTO:
                return SI.HECTO(unit);
            case Prefix.KILO:
                return SI.KILO(unit);
            case Prefix.MEGA:
                return SI.MEGA(unit);
            case Prefix.MICRO:
                return SI.MICRO(unit);
            case Prefix.MILLI:
                return SI.MILLI(unit);
            case Prefix.NANO:
                return SI.NANO(unit);
            case Prefix.PETA:
                return SI.PETA(unit);
            case Prefix.PICO:
                return SI.PICO(unit);
            case Prefix.TERA:
                return SI.TERA(unit);
            case Prefix.YOCTO:
                return SI.YOCTO(unit);
            case Prefix.ZEPTO:
                return SI.ZEPTO(unit);
            case Prefix.ZETTA:
                return SI.ZETTA(unit);
            default:
                throw new AssertionError();
        }
    }

    public List<Unit> getNonSIUnits() {
        if (nonSI != null) {
            return nonSI;
        }
        nonSI = new ArrayList<>();
        nonSI.add(javax.measure.unit.NonSI.ANGSTROM);
        nonSI.add(javax.measure.unit.NonSI.ARE);
        nonSI.add(javax.measure.unit.NonSI.ASTRONOMICAL_UNIT);
        nonSI.add(javax.measure.unit.NonSI.ATMOSPHERE);
        nonSI.add(javax.measure.unit.NonSI.ATOM);
        nonSI.add(javax.measure.unit.NonSI.ATOMIC_MASS);
        nonSI.add(javax.measure.unit.NonSI.BAR);
        nonSI.add(javax.measure.unit.NonSI.BYTE);
        nonSI.add(javax.measure.unit.NonSI.C);
        nonSI.add(javax.measure.unit.NonSI.CENTIRADIAN);
        nonSI.add(javax.measure.unit.NonSI.COMPUTER_POINT);
        nonSI.add(javax.measure.unit.NonSI.CUBIC_INCH);
        nonSI.add(javax.measure.unit.NonSI.CURIE);
        nonSI.add(javax.measure.unit.NonSI.DAY);
        nonSI.add(javax.measure.unit.NonSI.DAY_SIDEREAL);
        nonSI.add(javax.measure.unit.NonSI.DECIBEL);
        nonSI.add(javax.measure.unit.NonSI.DEGREE_ANGLE);
        nonSI.add(javax.measure.unit.NonSI.DYNE);
        nonSI.add(javax.measure.unit.NonSI.E);
        nonSI.add(javax.measure.unit.NonSI.ELECTRON_MASS);
        nonSI.add(javax.measure.unit.NonSI.ELECTRON_VOLT);
        nonSI.add(javax.measure.unit.NonSI.ERG);
        nonSI.add(javax.measure.unit.NonSI.FAHRENHEIT);
        nonSI.add(javax.measure.unit.NonSI.FARADAY);
        nonSI.add(javax.measure.unit.NonSI.FOOT);
        nonSI.add(javax.measure.unit.NonSI.FOOT_SURVEY_US);
        nonSI.add(javax.measure.unit.NonSI.FRANKLIN);
        nonSI.add(javax.measure.unit.NonSI.G);
        nonSI.add(javax.measure.unit.NonSI.GALLON_DRY_US);
        nonSI.add(javax.measure.unit.NonSI.GALLON_LIQUID_US);
        nonSI.add(javax.measure.unit.NonSI.GALLON_UK);
        nonSI.add(javax.measure.unit.NonSI.GAUSS);
        nonSI.add(javax.measure.unit.NonSI.GILBERT);
        nonSI.add(javax.measure.unit.NonSI.GRADE);
        nonSI.add(javax.measure.unit.NonSI.HECTARE);
        nonSI.add(javax.measure.unit.NonSI.HORSEPOWER);
        nonSI.add(javax.measure.unit.NonSI.HOUR);
        nonSI.add(javax.measure.unit.NonSI.INCH);
        nonSI.add(javax.measure.unit.NonSI.INCH_OF_MERCURY);
        nonSI.add(javax.measure.unit.NonSI.KILOGRAM_FORCE);
        nonSI.add(javax.measure.unit.NonSI.KILOMETERS_PER_HOUR);
        nonSI.add(javax.measure.unit.NonSI.KNOT);
        nonSI.add(javax.measure.unit.NonSI.LAMBERT);
        nonSI.add(javax.measure.unit.NonSI.LIGHT_YEAR);
        nonSI.add(javax.measure.unit.NonSI.LITER);
        nonSI.add(javax.measure.unit.NonSI.LITRE);
        nonSI.add(javax.measure.unit.NonSI.MACH);
        nonSI.add(javax.measure.unit.NonSI.MAXWELL);
        nonSI.add(javax.measure.unit.NonSI.METRIC_TON);
        nonSI.add(javax.measure.unit.NonSI.MILE);
        nonSI.add(javax.measure.unit.NonSI.MILES_PER_HOUR);
        nonSI.add(javax.measure.unit.NonSI.MILLIMETER_OF_MERCURY);
        nonSI.add(javax.measure.unit.NonSI.MINUTE);
        nonSI.add(javax.measure.unit.NonSI.MINUTE_ANGLE);
        nonSI.add(javax.measure.unit.NonSI.MONTH);
        nonSI.add(javax.measure.unit.NonSI.NAUTICAL_MILE);
        nonSI.add(javax.measure.unit.NonSI.OCTET);
        nonSI.add(javax.measure.unit.NonSI.OUNCE);
        nonSI.add(javax.measure.unit.NonSI.OUNCE_LIQUID_UK);
        nonSI.add(javax.measure.unit.NonSI.OUNCE_LIQUID_US);
        nonSI.add(javax.measure.unit.NonSI.PARSEC);
        nonSI.add(javax.measure.unit.NonSI.PERCENT);
        nonSI.add(javax.measure.unit.NonSI.PIXEL);
        nonSI.add(javax.measure.unit.NonSI.POINT);
        nonSI.add(javax.measure.unit.NonSI.POISE);
        nonSI.add(javax.measure.unit.NonSI.POUND);
        nonSI.add(javax.measure.unit.NonSI.POUND_FORCE);
        nonSI.add(javax.measure.unit.NonSI.RAD);
        nonSI.add(javax.measure.unit.NonSI.RANKINE);
        nonSI.add(javax.measure.unit.NonSI.REM);
        nonSI.add(javax.measure.unit.NonSI.REVOLUTION);
        nonSI.add(javax.measure.unit.NonSI.ROENTGEN);
        nonSI.add(javax.measure.unit.NonSI.RUTHERFORD);
        nonSI.add(javax.measure.unit.NonSI.SECOND_ANGLE);
        nonSI.add(javax.measure.unit.NonSI.SPHERE);
        nonSI.add(javax.measure.unit.NonSI.STOKE);
        nonSI.add(javax.measure.unit.NonSI.TON_UK);
        nonSI.add(javax.measure.unit.NonSI.TON_US);
        nonSI.add(javax.measure.unit.NonSI.WEEK);
        nonSI.add(javax.measure.unit.NonSI.YARD);
        nonSI.add(javax.measure.unit.NonSI.YEAR);
        nonSI.add(javax.measure.unit.NonSI.YEAR_CALENDAR);
        nonSI.add(javax.measure.unit.NonSI.YEAR_SIDEREAL);

        return nonSI;
    }

    public Unit parseUnit(String unitString) {
        //1. try basic parsing
        try {
            return Unit.valueOf(unitString);
        } catch (Exception ex) {
            //2. Maybe it aniregilar unit of ours?
            //ToDo: remove hardcode do proper code
            for (Unit au : getAdditonalUnits()) {
                if (au.toString().equals(unitString)) {
//                    System.out.println("Is Additonal Unit: " + unitString);
                    return au;
                }
            }

            //3. is unknown, we cound return Unit.ONE with alternative synbol 
            // but i want to know which have problem so a mark them in the beta
            return Unit.ONE.alternate("E|" + unitString);

        }

    }

    public boolean hasTimes(Unit unit) {
        return (unit.getDimension()).toString().contains("T");
    }

    public String formate(JEVisAttribute att) throws JEVisException {
        return formate(att.getUnit(), att.getAlternativSymbol());
    }

    public String formate(JEVisType type) throws JEVisException {
        return formate(type.getUnit(), type.getAlternativSymbol());
    }

    public String formate(Unit unit) {
        return formate(unit, "");
    }

    public String formate(Unit unit, String altSymbol) {
        System.out.println("Formate unit: " + unit + "  AltUnit: " + altSymbol);
//        String u1 = unit.getStandardUnit().toString().replace("·", "");
        String u1 = unit.toString().replace("·", "");
        u1 = u1.replace("(", "");
        u1 = u1.replace(")", "");
        u1 = u1.replace("/", "");

        return u1;

//        String prefix = "";
//
//        //dirty part... basic and hour based. 
//        if (false) {
//
//        } else if (unit.toString().contains("*1.0E24") || unit.toString().contains("*3.6E24")) {//YOTTA
//            prefix = "Y";
//        } else if (unit.toString().contains("*1.0E21") || unit.toString().contains("*3.6E21")) {//ZETTA
//            prefix = "Z";
//        } else if (unit.toString().contains("*1000000000000000000") || unit.toString().contains("*3.6E21")) {//EXA
//            prefix = "E";
//        } else if (unit.toString().contains("*1000000000000000") || unit.toString().contains("*3600000000000000000")) {//PETA
//            prefix = "P";
//        } else if (unit.toString().contains("*1000000000000") || unit.toString().contains("*3600000000000000")) {//TERA
//            prefix = "T";
//        } else if (unit.toString().contains("*1000000000") || unit.toString().contains("*3600000000000")) {//GIGA
//            prefix = "G";
//        } else if (unit.toString().contains("*1000000") || unit.toString().contains("*3600000000")) {//MEGA
//            prefix = "M";
//        } else if (unit.toString().contains("*1000") || unit.toString().contains("*3600000")) {//KILO
//            prefix = "k";
//        } else if (unit.toString().contains("*100") || unit.toString().contains("*360000")) {//HECTO
//            prefix = "h";
//        } else if (unit.toString().contains("*10") || unit.toString().contains("*36000")) {//DEKA
//            prefix = "da"; ///-----------------------
//        } else if (unit.toString().contains("/1.0E-24") || unit.toString().contains("/3.6E27")) {//Yokto
//            prefix = "y";
//        } else if (unit.toString().contains("/1.0E-21") || unit.toString().contains("/3.599999999999999E-18")) {//ZEPTO
//            prefix = "z";
//        } else if (unit.toString().contains("/1000000000000000000") || unit.toString().contains("*9/2500000000000000")) {//ATTO
//            prefix = "a";
//        } else if (unit.toString().contains("/1000000000000000") || unit.toString().contains("*9/2500000000000")) {//FEMTO
//            prefix = "f";
//        } else if (unit.toString().contains("/1000000000000") || unit.toString().contains("*9/2500000000")) {//PICO
//            prefix = "p";
//        } else if (unit.toString().contains("/1000000000") || unit.toString().contains("*9/2500000")) {//NANO
//            prefix = "n";
//        } else if (unit.toString().contains("/1000000") || unit.toString().contains("*9/2500")) {//MICRO
//            prefix = "µ";
//        } else if (unit.toString().contains("/1000") || unit.toString().contains("*18/5")) {//MILLI
//            prefix = "m";
//        } else if (unit.toString().contains("/100") || unit.toString().contains("*36")) {//CENTI
//            prefix = "c";
//        } else if (unit.toString().contains("/10") || unit.toString().contains("*360")) {//DECI
//            prefix = "d";
//        }
//        if (altSymbol != null && !altSymbol.equals("")) {
//            return prefix + altSymbol;
//        }
//        int end = u1.indexOf("/");
//
//        String u2 = "";
//        if (end != -1) {
//            u2 = u1.substring(0, end - 1);
//        }
//        end = u1.indexOf("*");
//
//        if (end != -1) {
//            u2 = u1.substring(0, end - 1);
//        }
//
//        return prefix + u2;
    }

    public List<Unit> getAdditonalUnits() {
        if (additonalUnits != null) {
            return additonalUnits;
        }
        additonalUnits = new ArrayList<>();

        additonalUnits.add(Unit.valueOf("Hz"));
        additonalUnits.add(NonSI.REVOLUTION.divide(SI.SECOND));
        additonalUnits.add(SI.WATT.times(SI.SECOND));
        additonalUnits.add(SI.WATT.times(NonSI.HOUR));

        additonalUnits.add(Money.BASE_UNIT.alternate("€"));
        additonalUnits.add(Money.BASE_UNIT.alternate("$"));
        additonalUnits.add(Money.BASE_UNIT.alternate("£"));
        additonalUnits.add(Money.BASE_UNIT.alternate("¥"));
        additonalUnits.add(Money.BASE_UNIT.alternate("₦"));
        additonalUnits.add(Money.BASE_UNIT.alternate("₹"));

        //pecial unit for gui. if this Unit is selected the user can choose the unit later.
//        Unit ws = SI.WATT.times(SI.SECOND);
//        Unit s = SI.SECOND;
//        Unit wh = SI.WATT.times(NonSI.HOUR);
//        Unit kwh = SI.KILO(SI.WATT.times(NonSI.HOUR));
//
//        System.out.println("=========Testing============");
//        magic(ws, NonSI.HOUR);
//        magic(wh, SI.SECOND);
//        magic(SI.GRAM, SI.SECOND);
//        magic(kwh, NonSI.YEAR);
//        System.out.println("=======end===========");
//
//        Unit x = wh;
//
//        if (x.isStandardUnit()) {
//            System.out.println("Standard Unit");
//        } else {
//            System.out.println("keine Standard Unit, Faktor = " + x.divide(x.getStandardUnit()));
//        }
//
//        System.out.println(
//                "ws: " + ws.divide(ws));
//        System.out.println(
//                "kwh: " + kwh.divide(ws));
//
//        System.out.println(
//                "wh: " + wh.divide(s));
//        System.out.println(
//                "kwh: " + kwh.divide(s));
//
//        System.out.println(
//                "is Ws time: " + hasTimes(ws));
//        System.out.println(
//                "is W time: " + hasTimes(SI.WATT));
        return additonalUnits;
    }

    public List<Unit> getSIUnits() {
        if (si != null) {
            return si;
        }
        si = new ArrayList<>();
        si.add(javax.measure.unit.SI.AMPERE);
        si.add(javax.measure.unit.SI.BECQUEREL);
        si.add(javax.measure.unit.SI.BIT);
        si.add(javax.measure.unit.SI.CANDELA);
        si.add(javax.measure.unit.SI.CELSIUS);
        si.add(javax.measure.unit.SI.CENTIMETER);
        si.add(javax.measure.unit.SI.CENTIMETRE);
        si.add(javax.measure.unit.SI.COULOMB);
        si.add(javax.measure.unit.SI.CUBIC_METRE);
        si.add(javax.measure.unit.SI.FARAD);
        si.add(javax.measure.unit.SI.GRAM);
        si.add(javax.measure.unit.SI.GRAY);
        si.add(javax.measure.unit.SI.HENRY);
        si.add(javax.measure.unit.SI.HERTZ);
        si.add(javax.measure.unit.SI.JOULE);
        si.add(javax.measure.unit.SI.KATAL);
        si.add(javax.measure.unit.SI.KELVIN);
        si.add(javax.measure.unit.SI.KILOGRAM);
        si.add(javax.measure.unit.SI.KILOMETER);
        si.add(javax.measure.unit.SI.LUMEN);
        si.add(javax.measure.unit.SI.LUX);
        si.add(javax.measure.unit.SI.METER);
        si.add(javax.measure.unit.SI.METERS_PER_SECOND);
        si.add(javax.measure.unit.SI.METERS_PER_SQUARE_SECOND);
        si.add(javax.measure.unit.SI.METRE);
        si.add(javax.measure.unit.SI.METRES_PER_SECOND);
        si.add(javax.measure.unit.SI.METRES_PER_SQUARE_SECOND);
        si.add(javax.measure.unit.SI.MILLIMETER);
        si.add(javax.measure.unit.SI.MILLIMETRE);
        si.add(javax.measure.unit.SI.MOLE);
        si.add(javax.measure.unit.SI.NEWTON);
        si.add(javax.measure.unit.SI.OHM);
        si.add(javax.measure.unit.SI.PASCAL);
        si.add(javax.measure.unit.SI.RADIAN);
        si.add(javax.measure.unit.SI.SECOND);
        si.add(javax.measure.unit.SI.SIEMENS);
        si.add(javax.measure.unit.SI.SIEVERT);
        si.add(javax.measure.unit.SI.SQUARE_METRE);
        si.add(javax.measure.unit.SI.STERADIAN);
        si.add(javax.measure.unit.SI.TESLA);
        si.add(javax.measure.unit.SI.VOLT);
        si.add(javax.measure.unit.SI.WATT);
        si.add(javax.measure.unit.SI.WEBER);
        si.add(javax.measure.unit.SI.METRE);

        return si;
    }

    public HashMap<Unit, String> getNameMapQuantities() {
        if (dimNames != null) {
            return dimNames;
        }
        dimNames = new HashMap<>();

        dimNames.put(Money.BASE_UNIT, "Currency");

        dimNames.put(Acceleration.UNIT, "Acceleration");
        dimNames.put(AngularVelocity.UNIT, "Angular Velocity");
        dimNames.put(AmountOfSubstance.UNIT, "Amount Of Substance");
        dimNames.put(Angle.UNIT, "Angle");
        dimNames.put(AngularAcceleration.UNIT, "Angular Acceleration");
        dimNames.put(Area.UNIT, "Area");
        dimNames.put(CatalyticActivity.UNIT, "Catalytic Activity");
        dimNames.put(DataAmount.UNIT, "Data Amount");
        dimNames.put(DataRate.UNIT, "Data Rate");
        dimNames.put(Dimensionless.UNIT, "Dimensionless");
        dimNames.put(Duration.UNIT, "Duration");
        dimNames.put(DynamicViscosity.UNIT, "DynamicViscosity");
        dimNames.put(ElectricCapacitance.UNIT, "Electric Capacitance");
        dimNames.put(ElectricConductance.UNIT, "Electric Conductance");
        dimNames.put(ElectricCharge.UNIT, "Electric Charge");
        dimNames.put(ElectricCurrent.UNIT, "Electric Current");
        dimNames.put(ElectricInductance.UNIT, "Electric Inductance");
        dimNames.put(ElectricPotential.UNIT, "Electric Potential");
        dimNames.put(ElectricResistance.UNIT, "Electric Resistance");
        dimNames.put(Energy.UNIT, "Energy");
        dimNames.put(Force.UNIT, "Force");
        dimNames.put(Frequency.UNIT, "Frequency");
        dimNames.put(Illuminance.UNIT, "Illuminance");
        dimNames.put(KinematicViscosity.UNIT, "Kinematic Viscosity");
        dimNames.put(Length.UNIT, "Length");
        dimNames.put(LuminousFlux.UNIT, "Luminous Flux");
        dimNames.put(LuminousIntensity.UNIT, "Luminous Intensity");
        dimNames.put(Mass.UNIT, "Mass");
        dimNames.put(MagneticFlux.UNIT, "Magnetic Flux");
        dimNames.put(MagneticFluxDensity.UNIT, "Magnetic Flux Density");
        dimNames.put(MassFlowRate.UNIT, "Mass Flow Rate");
        dimNames.put(Power.UNIT, "Power");
        dimNames.put(Pressure.UNIT, "Pressure");
        dimNames.put(RadiationDoseAbsorbed.UNIT, "Radiation Dose Absorbed");
        dimNames.put(RadiationDoseEffective.UNIT, "Radiation Dose Effective");
        dimNames.put(RadioactiveActivity.UNIT, "Radioactive Activity");
        dimNames.put(SolidAngle.UNIT, "Solid Angle");
        dimNames.put(Temperature.UNIT, "Temperature");
        dimNames.put(Torque.UNIT, "Torque");
        dimNames.put(Velocity.UNIT, "Velocity");
        dimNames.put(Volume.UNIT, "Volume");
        dimNames.put(VolumetricDensity.UNIT, "Volumetric Density");
        dimNames.put(VolumetricFlowRate.UNIT, "Volumetric Flow Rate");

        return dimNames;
    }

    public HashMap<Unit, String> getNameMap() {
        if (names != null) {
            return names;
        }
        names = new HashMap<>();

//        //--- SI units
//        names.put(SI.AMPERE, "AMPERE");
//        names.put(SI.BECQUEREL, "BECQUEREL");
//        names.put(SI.BIT, "BIT");
//        names.put(SI.CANDELA, "CANDELA");
//        names.put(SI.CELSIUS, "CELSIUS");
//        names.put(SI.CENTIMETER, "CENTIMETER");
//        names.put(SI.CENTIMETRE, "CENTIMETRE");
//        names.put(SI.COULOMB, "COULOMB");
//        names.put(SI.CUBIC_METRE, "CUBIC_METRE");
//        names.put(SI.FARAD, "FARAD");
//        names.put(SI.GRAM, "GRAM");
//        names.put(SI.GRAY, "GRAY");
//        names.put(SI.HENRY, "HENRY");
//        names.put(SI.HERTZ, "HERTZ");
//        names.put(SI.JOULE, "JOULE");
//        names.put(SI.KATAL, "KATAL");
//        names.put(SI.KELVIN, "KELVIN");
//        names.put(SI.KILOGRAM, "KILOGRAM");
//        names.put(SI.KILOMETER, "KILOMETER");
//        names.put(SI.LUMEN, "LUMEN");
//        names.put(SI.LUX, "LUX");
//        names.put(SI.METER, "METER");
//        names.put(SI.METERS_PER_SECOND, "METERS PER SECOND");
//        names.put(SI.METERS_PER_SQUARE_SECOND, "METERS PER SQUARE SECOND");
//        names.put(SI.METRE, "METRE");
//        names.put(SI.METRES_PER_SECOND, "METRES PER SECOND");
//        names.put(SI.METRES_PER_SQUARE_SECOND, "METRES_PER_SQUARE_SECOND");
//        names.put(SI.MILLIMETER, "MILLIMETER");
//        names.put(SI.LUX, "LUX");
//        names.put(SI.MILLIMETRE, "MILLIMETRE");
//        names.put(SI.MOLE, "MOLE");
//        names.put(SI.NEWTON, "NEWTON");
//        names.put(SI.OHM, "OHM");
//        names.put(SI.PASCAL, "PASCAL");
//        names.put(SI.RADIAN, "RADIAN");
//        names.put(SI.SECOND, "SECOND");
//        names.put(SI.SIEMENS, "SIEMENS");
//        names.put(SI.SIEVERT, "SIEVERT");
//        names.put(SI.SQUARE_METRE, "SQUARE_METRE");
//        names.put(SI.STERADIAN, "STERADIAN");
//        names.put(SI.TESLA, "TESLA");
//        names.put(SI.VOLT, "VOLT");
//        names.put(SI.WATT, "WATT");
//        names.put(SI.WEBER, "WEBER");
//        //---- NON SI
//        names.put(NonSI.ANGSTROM, "ANGSTROM");
//        names.put(NonSI.ARE, "ARE");
//        names.put(NonSI.ASTRONOMICAL_UNIT, "ASTRONOMICAL_UNIT");
//        names.put(NonSI.ATMOSPHERE, "ATMOSPHERE");
//        names.put(NonSI.ATOM, "ATOM");
//        names.put(NonSI.ATOMIC_MASS, "ATOMIC_MASS");
//        names.put(NonSI.BAR, "BAR");
//        names.put(NonSI.BYTE, "BYTE");
//        names.put(NonSI.C, "C");
//        names.put(NonSI.CENTIRADIAN, "CENTIRADIAN");
//        names.put(NonSI.COMPUTER_POINT, "COMPUTER_POINT");
//        names.put(NonSI.CUBIC_INCH, "CUBIC_INCH");
//        names.put(NonSI.CURIE, "CURIE");
//        names.put(NonSI.DAY, "DAY");
//        names.put(NonSI.DAY_SIDEREAL, "DAY_SIDEREAL");
//        names.put(NonSI.DECIBEL, "DECIBEL");
//        names.put(NonSI.DEGREE_ANGLE, "DEGREE_ANGLE");
//        names.put(NonSI.DYNE, "DYNE");
//        names.put(NonSI.E, "E");
//        names.put(NonSI.ELECTRON_MASS, "ELECTRON_MASS");
//        names.put(NonSI.ELECTRON_VOLT, "ELECTRON_VOLT");
//        names.put(NonSI.ERG, "ERG");
//        names.put(NonSI.FAHRENHEIT, "FAHRENHEIT");
//        names.put(NonSI.FARADAY, "FARADAY");
//        names.put(NonSI.FOOT, "FOOT");
//        names.put(NonSI.FOOT_SURVEY_US, "FOOT_SURVEY_US");
//        names.put(NonSI.FRANKLIN, "FRANKLIN");
//        names.put(NonSI.G, "G");
//        names.put(NonSI.GALLON_DRY_US, "GALLON_DRY_US");
//        names.put(NonSI.GALLON_LIQUID_US, "GALLON_LIQUID_US");
//        names.put(NonSI.GALLON_UK, "GALLON_UK");
//        names.put(NonSI.GAUSS, "GAUSS");
//        names.put(NonSI.GILBERT, "GILBERT");
//        names.put(NonSI.GRADE, "GRADE");
//        names.put(NonSI.HECTARE, "HECTARE");
//        names.put(NonSI.HORSEPOWER, "HORSEPOWER");
//        names.put(NonSI.HOUR, "HOUR");
//        names.put(NonSI.INCH, "INCH");
//        names.put(NonSI.INCH_OF_MERCURY, "INCH_OF_MERCURY");
//        names.put(NonSI.KILOGRAM_FORCE, "KILOGRAM_FORCE");
//        names.put(NonSI.KILOMETERS_PER_HOUR, "KILOMETERS_PER_HOUR");
//        names.put(NonSI.KNOT, "KNOT");
//        names.put(NonSI.LAMBERT, "LAMBERT");
//        names.put(NonSI.LIGHT_YEAR, "LIGHT_YEAR");
//        names.put(NonSI.LITER, "LITER");
//        names.put(NonSI.LITRE, "LITRE");
//        names.put(NonSI.MACH, "MACH");
//        names.put(NonSI.MAXWELL, "MAXWELL");
//        names.put(NonSI.METRIC_TON, "METRIC_TON");
//        names.put(NonSI.MILE, "MILE");
//        names.put(NonSI.MILES_PER_HOUR, "MILES_PER_HOUR");
//        names.put(NonSI.MILLIMETER_OF_MERCURY, "MILLIMETER_OF_MERCURY");
//        names.put(NonSI.MINUTE, "MINUTE");
//        names.put(NonSI.MINUTE_ANGLE, "MINUTE_ANGLE");
//        names.put(NonSI.MONTH, "MONTH");
//        names.put(NonSI.NAUTICAL_MILE, "NAUTICAL_MILE");
//        names.put(NonSI.OCTET, "OCTET");
//        names.put(NonSI.OUNCE, "OUNCE");
//        names.put(NonSI.OUNCE_LIQUID_UK, "OUNCE_LIQUID_UK");
//        names.put(NonSI.OUNCE_LIQUID_US, "OUNCE_LIQUID_US");
//        names.put(NonSI.PARSEC, "PARSEC");
//        names.put(NonSI.PERCENT, "PERCENT");
//        names.put(NonSI.PIXEL, "PIXEL");
//        names.put(NonSI.POINT, "POINT");
//        names.put(NonSI.POISE, "POISE");
//        names.put(NonSI.POUND, "POUND");
//        names.put(NonSI.POUND_FORCE, "POUND_FORCE");
//        names.put(NonSI.RAD, "RAD");
//        names.put(NonSI.RANKINE, "RANKINE");
//        names.put(NonSI.REM, "REM");
//        names.put(NonSI.REVOLUTION, "REVOLUTION");
//        names.put(NonSI.ROENTGEN, "ROENTGEN");
//        names.put(NonSI.RUTHERFORD, "RUTHERFORD");
//        names.put(NonSI.SECOND_ANGLE, "SECOND_ANGLE");
//        names.put(NonSI.SPHERE, "SPHERE");
//        names.put(NonSI.STOKE, "STOKE");
//        names.put(NonSI.TON_UK, "TON_UK");
//        names.put(NonSI.TON_US, "TON_US");
//        names.put(NonSI.WEEK, "WEEK");
//        names.put(NonSI.YARD, "YARD");
//        names.put(NonSI.YEAR, "YEAR");
//        names.put(NonSI.YEAR_CALENDAR, "YEAR_CALENDAR");
//        names.put(NonSI.YEAR_SIDEREAL, "YEAR_SIDEREAL");
//        //Prefix
//
//        names.put(SI.ZETTA(Unit.ONE), "ZETTA");
//        names.put(SI.EXA(Unit.ONE), "EXA");
//        names.put(SI.PETA(Unit.ONE), "PETA");
//        names.put(SI.TERA(Unit.ONE), "TERA");
//        names.put(SI.GIGA(Unit.ONE), "GIGA");
//        names.put(SI.MEGA(Unit.ONE), "MEGA");
//        names.put(SI.KILO(Unit.ONE), "KILO");
//        names.put(SI.HECTO(Unit.ONE), "HECTO");
//        names.put(SI.DEKA(Unit.ONE), "DEKA");
//        names.put(SI.DECI(Unit.ONE), "DECI");
//        names.put(SI.CENTI(Unit.ONE), "CENTI");
//        names.put(SI.MILLI(Unit.ONE), "MILLI");
//        names.put(SI.MICRO(Unit.ONE), "MICRO");
//        names.put(SI.NANO(Unit.ONE), "NANO");
//        names.put(SI.PICO(Unit.ONE), "PICO");
//        names.put(SI.FEMTO(Unit.ONE), "FEMTO");
//        names.put(SI.ATTO(Unit.ONE), "ATTO");
//        names.put(SI.ZEPTO(Unit.ONE), "ZEPTO");
//        names.put(SI.ATTO(Unit.ONE), "ATTO");
//        names.put(SI.YOCTO(Unit.ONE), "YOCTO");
        names.put(SI.AMPERE, "Ampere");
        names.put(SI.BECQUEREL, "Becquerel");
        names.put(SI.BIT, "Bit");
        names.put(SI.CANDELA, "Candela");
        names.put(SI.CELSIUS, "Celsius");
        names.put(SI.CENTIMETER, "Centimeter");
        names.put(SI.CENTIMETRE, "Centimetre");
        names.put(SI.COULOMB, "Coulomb");
        names.put(SI.CUBIC_METRE, "Cubic Metre");
        names.put(SI.FARAD, "Farad");
        names.put(SI.GRAM, "Gram");
        names.put(SI.GRAY, "Gray");
        names.put(SI.HENRY, "Henry");
        names.put(SI.HERTZ, "Hertz");
        names.put(SI.JOULE, "Joule");
        names.put(SI.KATAL, "Katal");
        names.put(SI.KELVIN, "Kelvin");
        names.put(SI.KILOGRAM, "Kilogram");
        names.put(SI.KILOMETER, "Kilometer");
        names.put(SI.LUMEN, "Lumen");
        names.put(SI.LUX, "Lux");
        names.put(SI.METER, "Meter");
        names.put(SI.METERS_PER_SECOND, "Meters Per Second");
        names.put(SI.METERS_PER_SQUARE_SECOND, "Meters Per Square Second");
        names.put(SI.MILLIMETRE, "Millimetre");
        names.put(SI.MOLE, "Mole");
        names.put(SI.NEWTON, "Newton");
        names.put(SI.OHM, "Ohm");
        names.put(SI.PASCAL, "Pascal");
        names.put(SI.RADIAN, "Radian");
        names.put(SI.SECOND, "Second");
        names.put(SI.SIEMENS, "Siemens");
        names.put(SI.SIEVERT, "Sievert");
        names.put(SI.SQUARE_METRE, "Square Metre");
        names.put(SI.STERADIAN, "Steradian");
        names.put(SI.TESLA, "Tesla");
        names.put(SI.VOLT, "Volt");
        names.put(SI.WATT, "Watt");
        names.put(SI.WEBER, "Weber");
        //---- NON SI
        names.put(NonSI.ANGSTROM, "Angstrom");
        names.put(NonSI.ARE, "Are");
        names.put(NonSI.ASTRONOMICAL_UNIT, "Astronomical Unit");
        names.put(NonSI.ATMOSPHERE, "Atmosphere");
        names.put(NonSI.ATOM, "Atom");
        names.put(NonSI.ATOMIC_MASS, "Atomic Mass");
        names.put(NonSI.BAR, "Bar");
        names.put(NonSI.BYTE, "Byte");
        names.put(NonSI.C, "C");
        names.put(NonSI.CENTIRADIAN, "Centiradian");
        names.put(NonSI.COMPUTER_POINT, "Computer Point");
        names.put(NonSI.CUBIC_INCH, "Cubic_Inch");
        names.put(NonSI.CURIE, "Curie");
        names.put(NonSI.DAY, "Day");
        names.put(NonSI.DAY_SIDEREAL, "Day_Sidereal");
        names.put(NonSI.DECIBEL, "Decibel");
        names.put(NonSI.DEGREE_ANGLE, "Degree Angle");
        names.put(NonSI.DYNE, "Dyne");
        names.put(NonSI.E, "E");
        names.put(NonSI.ELECTRON_MASS, "Electron Mass");
        names.put(NonSI.ELECTRON_VOLT, "Electron Volt");
        names.put(NonSI.ERG, "Erg");
        names.put(NonSI.FAHRENHEIT, "Fahrenheit");
        names.put(NonSI.FARADAY, "Faraday");
        names.put(NonSI.FOOT, "Foot");
        names.put(NonSI.FOOT_SURVEY_US, "Foot Survey Us");
        names.put(NonSI.FRANKLIN, "Franklin");
        names.put(NonSI.G, "G");
        names.put(NonSI.GALLON_DRY_US, "Gallon Dry Us");
        names.put(NonSI.GALLON_LIQUID_US, "Gallon Liquid US");
        names.put(NonSI.GALLON_UK, "Gallon UK");
        names.put(NonSI.GAUSS, "Gauss");
        names.put(NonSI.GILBERT, "Gilbert");
        names.put(NonSI.GRADE, "Grade");
        names.put(NonSI.HECTARE, "Hectare");
        names.put(NonSI.HORSEPOWER, "Horsepower");
        names.put(NonSI.HOUR, "Hour");
        names.put(NonSI.INCH, "Inch");
        names.put(NonSI.INCH_OF_MERCURY, "Inch Of Mercury");
        names.put(NonSI.KILOGRAM_FORCE, "Kilogram Force");
        names.put(NonSI.KILOMETERS_PER_HOUR, "Kilometers Per Hour");
        names.put(NonSI.KNOT, "Knot");
        names.put(NonSI.LAMBERT, "Lambert");
        names.put(NonSI.LIGHT_YEAR, "Light Year");
        names.put(NonSI.LITER, "Liter");
        names.put(NonSI.LITRE, "Litre");
        names.put(NonSI.MACH, "Mach");
        names.put(NonSI.MAXWELL, "Maxwell");
        names.put(NonSI.METRIC_TON, "Metric Ton");
        names.put(NonSI.MILE, "Mile");
        names.put(NonSI.MILES_PER_HOUR, "Miles Per Hour");
        names.put(NonSI.MILLIMETER_OF_MERCURY, "Millimeter Of Mercury");
        names.put(NonSI.MINUTE, "Minute");
        names.put(NonSI.MINUTE_ANGLE, "Minute Angle");
        names.put(NonSI.MONTH, "Month");
        names.put(NonSI.NAUTICAL_MILE, "Nautical Mile");
        names.put(NonSI.OCTET, "Octet");
        names.put(NonSI.OUNCE, "Ounce");
        names.put(NonSI.OUNCE_LIQUID_UK, "Ounce Liquid UK");
        names.put(NonSI.PARSEC, "Parsec");
        names.put(NonSI.PERCENT, "Percent");
        names.put(NonSI.PIXEL, "Pixel");
        names.put(NonSI.POINT, "Point");
        names.put(NonSI.POISE, "Poise");
        names.put(NonSI.POUND, "Pound");
        names.put(NonSI.POUND_FORCE, "Pound_Force");
        names.put(NonSI.RAD, "Rad");
        names.put(NonSI.RANKINE, "Rankine");
        names.put(NonSI.REM, "Rem");
        names.put(NonSI.REVOLUTION, "Revolution");
        names.put(NonSI.ROENTGEN, "Roentgen");
        names.put(NonSI.RUTHERFORD, "Rutherford");
        names.put(NonSI.SECOND_ANGLE, "Second Angle");
        names.put(NonSI.SPHERE, "Sphere");
        names.put(NonSI.STOKE, "Stoke");
        names.put(NonSI.TON_UK, "Ton UK");
        names.put(NonSI.TON_US, "Ton US");
        names.put(NonSI.WEEK, "Week");
        names.put(NonSI.YARD, "Yard");
        names.put(NonSI.YEAR, "Year");
        names.put(NonSI.YEAR_CALENDAR, "Year Calendar");
        names.put(NonSI.YEAR_SIDEREAL, "Year Sidereal");
        //Prefix

        names.put(SI.ZETTA(Unit.ONE), "Zetta");
        names.put(SI.EXA(Unit.ONE), "Exa");
        names.put(SI.PETA(Unit.ONE), "Peta");
        names.put(SI.TERA(Unit.ONE), "Tera");
        names.put(SI.GIGA(Unit.ONE), "Giga");
        names.put(SI.MEGA(Unit.ONE), "Mega");
        names.put(SI.KILO(Unit.ONE), "Kilo");
        names.put(SI.HECTO(Unit.ONE), "Hecto");
        names.put(SI.DEKA(Unit.ONE), "Deka");
        names.put(SI.DECI(Unit.ONE), "Deci");
        names.put(SI.CENTI(Unit.ONE), "Centi");
        names.put(SI.MILLI(Unit.ONE), "Milli");
        names.put(SI.MICRO(Unit.ONE), "Micro");
        names.put(SI.NANO(Unit.ONE), "Nano");
        names.put(SI.PICO(Unit.ONE), "Pico");
        names.put(SI.FEMTO(Unit.ONE), "Femto");
        names.put(SI.ATTO(Unit.ONE), "Atto");
        names.put(SI.ZEPTO(Unit.ONE), "Zepto");
        names.put(SI.ATTO(Unit.ONE), "Atto");
        names.put(SI.YOCTO(Unit.ONE), "Yocto");

        //money does not work with the rest of the system. The API will store € but cannot parse it again....
        // we have to use Currency + alt symbol :(
        names.put(Money.BASE_UNIT.alternate("€"), "Euro");
        names.put(Money.BASE_UNIT.alternate("£"), "Pound");
        names.put(Money.BASE_UNIT.alternate("$"), "US-Dollar");
        names.put(Money.BASE_UNIT.alternate("£"), "Yen");
        names.put(Money.BASE_UNIT.alternate("¥"), "Yuan");
        names.put(Money.BASE_UNIT.alternate("₦"), "Naira");
        names.put(Money.BASE_UNIT.alternate("元"), "Renminbi");
        names.put(Money.BASE_UNIT.alternate("₹"), "Rupee");
        ///--additonal
//        name.put
        return names;
    }

    /**
     * Ask the JEADPI localization feature for the correct name of the unit.
     * Unit is identyfiyed by its symbole(this should be unitq?)
     *
     * TODO: The functionality is hardcoded in the moment....
     *
     * @param unit
     * @param locale
     * @return
     */
    public String getUnitName(Unit unit, Locale locale) {
        String name = getNameMap().get(unit);
        if (name != null && !name.isEmpty()) {
            return name;
        } else {
            return unit.toString();
        }

    }

    public String getQuantitiesName(Unit unit, Locale locale) {

        String name = getNameMapQuantities().get(unit.getStandardUnit());
        if (name != null && !name.isEmpty()) {
//            System.out.println(String.format(" get name for: %s [%s] = %s", unit.toString(), unit.getStandardUnit().toString(), name));
            return name;
        } else {
            return "Dimensionless";
        }

    }

    public List<Unit> getCompatibleNonSIUnit(Unit unit) {
        List<Unit> units = new ArrayList<Unit>();

        for (Unit other : getNonSIUnits()) {
            if (unit.isCompatible(other)) {
                units.add(other);
            }
        }

        return units;
    }

    public List<Unit> getCompatibleQuantityUnit(Unit unit) {
        List<Unit> units = new ArrayList<Unit>();

        for (Unit other : getQuantities()) {
            if (unit.isCompatible(other)) {
                units.add(other);
            }
        }

        return units;
    }

    public List<Unit> getCompatibleSIUnit(Unit unit) {
        List<Unit> units = new ArrayList<Unit>();

        for (Unit other : getSIUnits()) {
            if (unit.isCompatible(other) && !other.equals(unit)) {
                units.add(other);
            }
        }

        return units;
    }

    public List<Unit> getCompatibleAdditionalUnit(Unit unit) {
        List<Unit> units = new ArrayList<Unit>();
//        System.out.println("Found add units for: " + unit);

        for (Unit other : getAdditonalUnits()) {
//            System.out.print(other + " ? ...");
            if (unit.getStandardUnit().isCompatible(other) && !other.equals(unit)) {
//                System.out.println("is");
                units.add(other);
            }
//            System.out.println("NOT");
        }

        return units;
    }

    public List<Unit> getFavoriteQuantitys() {
        List<Unit> list = new ArrayList<Unit>();

        list.add(Dimensionless.UNIT);
        list.add(Energy.UNIT);
        list.add(Power.UNIT);
        list.add(Temperature.UNIT);
        list.add(Volume.UNIT);
        list.add(Area.UNIT);

        return list;
    }

}
