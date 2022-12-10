package ru.nsu.fit.lylova.functions;

import ru.nsu.fit.lylova.CalculatorFunction;

import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Math.pow;

public class FunctionAdd extends CalculatorFunction {
    public FunctionAdd(){
    }

    @Override
    public void produce(Stack<Double> calculatorStack) throws Exception {
        if (calculatorStack.size() < 2) {
            throw new Exception("Not enough elements in calculatorStack");
        }
        double a = calculatorStack.pop();
        double b = calculatorStack.pop();
        double result = a + b;
        if (Double.isNaN(result)) {
            throw new Exception("Cannot add");
        }
        calculatorStack.push(result);
    }
}
