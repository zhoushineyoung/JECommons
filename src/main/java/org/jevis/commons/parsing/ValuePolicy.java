/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jevis.commons.parsing;

/**
 *
 * @author bf
 */
public class ValuePolicy {

    public static boolean checkValue(SampleParserContainer parser) {
        boolean validValue = parser.getValueParser().isValueValid();
        return validValue;
    }

    public static boolean checkDatapoint(SampleParserContainer parser) {
        return parser.getDpParser().isMappingSuccessfull();
    }
}
