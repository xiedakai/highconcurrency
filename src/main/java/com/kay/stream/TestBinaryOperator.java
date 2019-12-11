package com.kay.stream;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class TestBinaryOperator {

    public static void main(String[] args) {
        TestBinaryOperator testBinaryOperator=new TestBinaryOperator();
       double  result=testBinaryOperator.biFunctionCompute(10.0, 15.0, (value1, value2) -> value1 + value2);
        System.out.println("result = [" + result + "]");

        Function<String,String> function1 = item -> item +"返回值";
        System.out.println(function1.apply("123"));


        Consumer<String> function2 = iterm -> {System.out.println(iterm);};//lambda语句，使用大括号，没有return关键字，表示没有返回值
        function2.accept("888");

    }

    //BiFunction写法
    public Double biFunctionCompute(Double para1, Double para2, BiFunction<Double, Double, Double> biFunction) {
        return biFunction.apply(para1, para2);

    }
}
