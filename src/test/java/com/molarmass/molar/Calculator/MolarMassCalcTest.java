package com.molarmass.molar.Calculator;

import com.molarmass.molar.Model.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void oneElementMultipleTest(){
        // Given
        Double expected = 9.0;

        // When
        Double actual = calc.getMolarMass("Li3");

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void multipleElementsTest(){
        // Given
        Double expected = 6.0;

        // When
        Double actual = calc.getMolarMass("LiH3");

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void groupingTest(){
        // Given
        Double expected = 7.0;

        // When
        Double actual = calc.getMolarMass("H(LiH3)");

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void groupingTestMultiple(){
        // Given
        Double expected = 18.0;

        // When
        Double actual = calc.getMolarMass("Li2(H3Li)2");

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void nestedGroupingTest(){
        // Given
        Double expected = 50.0;

        // When
        Double actual = calc.getMolarMass("(H(Li2H)3)2Li2");

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void extraParen(){
        // When
        Double actual = calc.getMolarMass("(H(LiH2)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void extraThesis(){
        // When
        Double actual = calc.getMolarMass("H(LiH2))");
    }

    @Test(expected = IllegalArgumentException.class)
    public void unknownElement(){
        // When
        Double actual = calc.getMolarMass("CH4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidCharacter(){
        // When
        Double actual = calc.getMolarMass("Li3#");
    }

}