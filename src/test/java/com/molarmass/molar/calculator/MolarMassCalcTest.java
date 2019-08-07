package com.molarmass.molar.calculator;

import com.molarmass.molar.model.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MolarMassCalcTest {

    private MolarMassCalc calc;

    @Before
    public void before(){
        Map<String, Element> elemMap = new HashMap<>();
        Element lithium = new Element(null, "Li", 3.0, null, null);
        Element hydrogen = new Element(null, "H", 1.0, null, null);
        elemMap.put(lithium.getSymbol(), lithium);
        elemMap.put(hydrogen.getSymbol(), hydrogen);
        calc = new MolarMassCalc(elemMap);
    }

    @Test
    public void oneElementTest(){
        // Given
        Double expected = 1.0;

        // When
        Double actual = calc.getMolarMass("H");

        // Then
        Assert.assertEquals(expected, actual);
    }

}