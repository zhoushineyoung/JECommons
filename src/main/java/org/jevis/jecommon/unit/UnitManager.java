/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.jecommon.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.measure.quantity.*;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

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

    public interface Prefix {

        public static String ZETTA = "ZETTA";
        public static String EXA = "EXA";
        public static String PETA = "PETA";
        public static String TERA = "TERA";
        public static String GIGA = "GIGA";
        public static String MEGA = "MEGA";
        public static String KILO = "KILO";
        public static String HECTO = "HECTO";
        public static String DEKA = "DEKA";
        public static String DECI = "DECI";
        public static String CENTI = "CENTI";
        public static String MILLI = "MILLI";
        public static String MICRO = "MICRO";
        public static String NANO = "NANO";
        public static String PICO = "PICO";
        public static String FEMTO = "FEMTO";
        public static String ATTO = "ATTO";
        public static String ZEPTO = "ZEPTO";
        public static String YOCTO = "YOCTO";

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
        prefixes2.add("ZETTA");
        prefixes2.add("EXA");
        prefixes2.add("PETA");
        prefixes2.add("TERA");
        prefixes2.add("GIGA");
        prefixes2.add("MEGA");
        prefixes2.add("KILO");
        prefixes2.add("HECTO");
        prefixes2.add("DEKA");
        prefixes2.add("DECI");
        prefixes2.add("CENTI");
        prefixes2.add("MILLI");
        prefixes2.add("MICRO");
        prefixes2.add("NANO");
        prefixes2.add("PICO");
        prefixes2.add("FEMTO");
        prefixes2.add("ATTO");
        prefixes2.add("ZEPTO");
        prefixes2.add("YOCTO");

        return prefixes2;
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

    public boolean hasTimes(Unit unit) {
        return (unit.getDimension()).toString().contains("T");
    }

//    public boolean removeTime(Unit){
//        
//    }
//    
    public void magic(Unit unit, Unit dur) {
        System.out.println(String.format("do magic for %s to %s", unit.toString(), dur.toString()));
        try {
            Unit sunit = unit.getStandardUnit();
            System.out.println("default dimesion: " + sunit.getDimension());
            Unit searchFor = null;
            if (sunit.getDimension().toString().contains("T")) {
                System.out.println("has Time");

                Unit s = sunit.divide(SI.SECOND);
                System.out.println("devid s: " + s);
                Unit h = sunit.divide(NonSI.HOUR);
                System.out.println("....");
                System.out.println("dim1: " + s.getDimension());
                System.out.println("dim2: " + h.getDimension());
                System.out.println("..");

                if (!s.getDimension().toString().contains("T")) {
                    System.out.println("is secound based");
                    searchFor = s;
                } else if (!h.getDimension().toString().contains("T")) {
                    System.out.println("is hour based");
                    searchFor = h;
                }

                System.out.println(String.format("Now in %s: %s",
                        dur.toString(),
                        searchFor.times(dur).toString())
                );

            } else {
                System.out.println("has NO time");
            }
        } catch (Exception ex) {
            System.out.println("sorry Dave in can not do this");
//            ex.printStackTrace();

        }

        System.out.println("");

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

        Unit ws = SI.WATT.times(SI.SECOND);
        Unit s = SI.SECOND;
        Unit wh = SI.WATT.times(NonSI.HOUR);
        Unit kwh = SI.KILO(SI.WATT.times(NonSI.HOUR));

        System.out.println("=========Testing============");
        magic(ws, NonSI.HOUR);
        magic(wh, SI.SECOND);
        magic(SI.GRAM, SI.SECOND);
        magic(kwh, NonSI.YEAR);
        System.out.println("=======end===========");

        Unit x = wh;

        if (x.isStandardUnit()) {
            System.out.println("Standard Unit");
        } else {
            System.out.println("keine Standard Unit, Faktor = " + x.divide(x.getStandardUnit()));
        }

        System.out.println(
                "ws: " + ws.divide(ws));
        System.out.println(
                "kwh: " + kwh.divide(ws));

        System.out.println(
                "wh: " + wh.divide(s));
        System.out.println(
                "kwh: " + kwh.divide(s));

        System.out.println(
                "is Ws time: " + hasTimes(ws));
        System.out.println(
                "is W time: " + hasTimes(SI.WATT));

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

        //--- SI units
        names.put(SI.AMPERE, "AMPERE");
        names.put(SI.BECQUEREL, "BECQUEREL");
        names.put(SI.BIT, "BIT");
        names.put(SI.CANDELA, "CANDELA");
        names.put(SI.CELSIUS, "CELSIUS");
        names.put(SI.CENTIMETER, "CENTIMETER");
        names.put(SI.CENTIMETRE, "CENTIMETRE");
        names.put(SI.COULOMB, "COULOMB");
        names.put(SI.CUBIC_METRE, "CUBIC_METRE");
        names.put(SI.FARAD, "FARAD");
        names.put(SI.GRAM, "GRAM");
        names.put(SI.GRAY, "GRAY");
        names.put(SI.HENRY, "HENRY");
        names.put(SI.HERTZ, "HERTZ");
        names.put(SI.JOULE, "JOULE");
        names.put(SI.KATAL, "KATAL");
        names.put(SI.KELVIN, "KELVIN");
        names.put(SI.KILOGRAM, "KILOGRAM");
        names.put(SI.KILOMETER, "KILOMETER");
        names.put(SI.LUMEN, "LUMEN");
        names.put(SI.LUX, "LUX");
        names.put(SI.METER, "METER");
        names.put(SI.METERS_PER_SECOND, "METERS PER SECOND");
        names.put(SI.METERS_PER_SQUARE_SECOND, "METERS PER SQUARE SECOND");
        names.put(SI.METRE, "METRE");
        names.put(SI.METRES_PER_SECOND, "METRES PER SECOND");
        names.put(SI.METRES_PER_SQUARE_SECOND, "METRES_PER_SQUARE_SECOND");
        names.put(SI.MILLIMETER, "MILLIMETER");
        names.put(SI.LUX, "LUX");
        names.put(SI.MILLIMETRE, "MILLIMETRE");
        names.put(SI.MOLE, "MOLE");
        names.put(SI.NEWTON, "NEWTON");
        names.put(SI.OHM, "OHM");
        names.put(SI.PASCAL, "PASCAL");
        names.put(SI.RADIAN, "RADIAN");
        names.put(SI.SECOND, "SECOND");
        names.put(SI.SIEMENS, "SIEMENS");
        names.put(SI.SIEVERT, "SIEVERT");
        names.put(SI.SQUARE_METRE, "SQUARE_METRE");
        names.put(SI.STERADIAN, "STERADIAN");
        names.put(SI.TESLA, "TESLA");
        names.put(SI.VOLT, "VOLT");
        names.put(SI.WATT, "WATT");
        names.put(SI.WEBER, "WEBER");
        //---- NON SI
        names.put(NonSI.ANGSTROM, "ANGSTROM");
        names.put(NonSI.ARE, "ARE");
        names.put(NonSI.ASTRONOMICAL_UNIT, "ASTRONOMICAL_UNIT");
        names.put(NonSI.ATMOSPHERE, "ATMOSPHERE");
        names.put(NonSI.ATOM, "ATOM");
        names.put(NonSI.ATOMIC_MASS, "ATOMIC_MASS");
        names.put(NonSI.BAR, "BAR");
        names.put(NonSI.BYTE, "BYTE");
        names.put(NonSI.C, "C");
        names.put(NonSI.CENTIRADIAN, "CENTIRADIAN");
        names.put(NonSI.COMPUTER_POINT, "COMPUTER_POINT");
        names.put(NonSI.CUBIC_INCH, "CUBIC_INCH");
        names.put(NonSI.CURIE, "CURIE");
        names.put(NonSI.DAY, "DAY");
        names.put(NonSI.DAY_SIDEREAL, "DAY_SIDEREAL");
        names.put(NonSI.DECIBEL, "DECIBEL");
        names.put(NonSI.DEGREE_ANGLE, "DEGREE_ANGLE");
        names.put(NonSI.DYNE, "DYNE");
        names.put(NonSI.E, "E");
        names.put(NonSI.ELECTRON_MASS, "ELECTRON_MASS");
        names.put(NonSI.ELECTRON_VOLT, "ELECTRON_VOLT");
        names.put(NonSI.ERG, "ERG");
        names.put(NonSI.FAHRENHEIT, "FAHRENHEIT");
        names.put(NonSI.FARADAY, "FARADAY");
        names.put(NonSI.FOOT, "FOOT");
        names.put(NonSI.FOOT_SURVEY_US, "FOOT_SURVEY_US");
        names.put(NonSI.FRANKLIN, "FRANKLIN");
        names.put(NonSI.G, "G");
        names.put(NonSI.GALLON_DRY_US, "GALLON_DRY_US");
        names.put(NonSI.GALLON_LIQUID_US, "GALLON_LIQUID_US");
        names.put(NonSI.GALLON_UK, "GALLON_UK");
        names.put(NonSI.GAUSS, "GAUSS");
        names.put(NonSI.GILBERT, "GILBERT");
        names.put(NonSI.GRADE, "GRADE");
        names.put(NonSI.HECTARE, "HECTARE");
        names.put(NonSI.HORSEPOWER, "HORSEPOWER");
        names.put(NonSI.HOUR, "HOUR");
        names.put(NonSI.INCH, "INCH");
        names.put(NonSI.INCH_OF_MERCURY, "INCH_OF_MERCURY");
        names.put(NonSI.KILOGRAM_FORCE, "KILOGRAM_FORCE");
        names.put(NonSI.KILOMETERS_PER_HOUR, "KILOMETERS_PER_HOUR");
        names.put(NonSI.KNOT, "KNOT");
        names.put(NonSI.LAMBERT, "LAMBERT");
        names.put(NonSI.LIGHT_YEAR, "LIGHT_YEAR");
        names.put(NonSI.LITER, "LITER");
        names.put(NonSI.LITRE, "LITRE");
        names.put(NonSI.MACH, "MACH");
        names.put(NonSI.MAXWELL, "MAXWELL");
        names.put(NonSI.METRIC_TON, "METRIC_TON");
        names.put(NonSI.MILE, "MILE");
        names.put(NonSI.MILES_PER_HOUR, "MILES_PER_HOUR");
        names.put(NonSI.MILLIMETER_OF_MERCURY, "MILLIMETER_OF_MERCURY");
        names.put(NonSI.MINUTE, "MINUTE");
        names.put(NonSI.MINUTE_ANGLE, "MINUTE_ANGLE");
        names.put(NonSI.MONTH, "MONTH");
        names.put(NonSI.NAUTICAL_MILE, "NAUTICAL_MILE");
        names.put(NonSI.OCTET, "OCTET");
        names.put(NonSI.OUNCE, "OUNCE");
        names.put(NonSI.OUNCE_LIQUID_UK, "OUNCE_LIQUID_UK");
        names.put(NonSI.OUNCE_LIQUID_US, "OUNCE_LIQUID_US");
        names.put(NonSI.PARSEC, "PARSEC");
        names.put(NonSI.PERCENT, "PERCENT");
        names.put(NonSI.PIXEL, "PIXEL");
        names.put(NonSI.POINT, "POINT");
        names.put(NonSI.POISE, "POISE");
        names.put(NonSI.POUND, "POUND");
        names.put(NonSI.POUND_FORCE, "POUND_FORCE");
        names.put(NonSI.RAD, "RAD");
        names.put(NonSI.RANKINE, "RANKINE");
        names.put(NonSI.REM, "REM");
        names.put(NonSI.REVOLUTION, "REVOLUTION");
        names.put(NonSI.ROENTGEN, "ROENTGEN");
        names.put(NonSI.RUTHERFORD, "RUTHERFORD");
        names.put(NonSI.SECOND_ANGLE, "SECOND_ANGLE");
        names.put(NonSI.SPHERE, "SPHERE");
        names.put(NonSI.STOKE, "STOKE");
        names.put(NonSI.TON_UK, "TON_UK");
        names.put(NonSI.TON_US, "TON_US");
        names.put(NonSI.WEEK, "WEEK");
        names.put(NonSI.YARD, "YARD");
        names.put(NonSI.YEAR, "YEAR");
        names.put(NonSI.YEAR_CALENDAR, "YEAR_CALENDAR");
        names.put(NonSI.YEAR_SIDEREAL, "YEAR_SIDEREAL");
        //Prefix

        names.put(SI.ZETTA(Unit.ONE), "ZETTA");
        names.put(SI.EXA(Unit.ONE), "EXA");
        names.put(SI.PETA(Unit.ONE), "PETA");
        names.put(SI.TERA(Unit.ONE), "TERA");
        names.put(SI.GIGA(Unit.ONE), "GIGA");
        names.put(SI.MEGA(Unit.ONE), "MEGA");
        names.put(SI.KILO(Unit.ONE), "KILO");
        names.put(SI.HECTO(Unit.ONE), "HECTO");
        names.put(SI.DEKA(Unit.ONE), "DEKA");
        names.put(SI.DECI(Unit.ONE), "DECI");
        names.put(SI.CENTI(Unit.ONE), "CENTI");
        names.put(SI.MILLI(Unit.ONE), "MILLI");
        names.put(SI.MICRO(Unit.ONE), "MICRO");
        names.put(SI.NANO(Unit.ONE), "NANO");
        names.put(SI.PICO(Unit.ONE), "PICO");
        names.put(SI.FEMTO(Unit.ONE), "FEMTO");
        names.put(SI.ATTO(Unit.ONE), "ATTO");
        names.put(SI.ZEPTO(Unit.ONE), "ZEPTO");
        names.put(SI.ATTO(Unit.ONE), "ATTO");
        names.put(SI.YOCTO(Unit.ONE), "YOCTO");

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
            return "Unknown Unit: " + unit.toString();
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
        System.out.println("Found add units for: " + unit);

        for (Unit other : getAdditonalUnits()) {
            System.out.print(other + " ? ...");
            if (unit.getStandardUnit().isCompatible(other) && !other.equals(unit)) {
                System.out.println("is");
                units.add(other);
            }
            System.out.println("NOT");
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
