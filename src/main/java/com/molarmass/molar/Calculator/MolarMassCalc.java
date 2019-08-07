package com.molarmass.molar.Calculator;

import com.molarmass.molar.Model.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MolarMassCalc {

    private Map<String, Element> elemMap;
    private Pattern elem = Pattern.compile("^(([A-Z][a-z]?)(\\d*)|\\)(\\d*)|\\()");

    public MolarMassCalc(Map<String, Element> elemMap){
        this.elemMap = elemMap;
    }

    public Double getMolarMass(String formula){

        Stack<Double> outsideParen = new Stack<>();
        Double mass = 0.0;
        int index = 0;
        Matcher m = elem.matcher(formula);
        while(index < formula.length()){
            if(!m.find()){
                throw new IllegalArgumentException();
            }
            String group = m.group(0);

            if(group.charAt(0) == '('){
                outsideParen.push(mass);
                mass = 0.0;
            }
            else if(group.charAt(0) == ')'){
                if(outsideParen.empty()){
                    throw new IllegalArgumentException();
                }
                int multiplier = 1;
                if(!m.group(4).equals("")){
                    multiplier = Integer.parseInt(m.group(4));
                }
                mass*= multiplier;
                mass+= outsideParen.pop();
            }
            else{
                Element element = elemMap.get(m.group(2));
                if(element == null){
                    throw new IllegalArgumentException();
                }
                int multiplier= 1;
                if(!m.group(3).equals("")){
                    multiplier = Integer.parseInt(m.group(3));
                }
                mass+= element.getMass() * multiplier;
            }
            index = index + group.length();
            m.region(index, formula.length());
        }
        if(!outsideParen.empty()){
            throw new IllegalArgumentException();
        }
        return mass;
    }

    public static void main(String[] args) {
        MolarMassCalc calc = new MolarMassCalc(new HashMap<>());
        calc.getMolarMass("C4CH4()234)(ReRe5");
    }
}
