package com.kay.lamda;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * 行为参数化：
 * 应对变化
 *
 */
public class ActionParameterization<T> {
    public static void main(String[] args) {

        List<String> stringList= Stream.of("aa","bb","cc").collect(Collectors.toList());
        List<Integer> intList= Stream.of(100,1001,200).collect(Collectors.toList());

        //
       List<String> strResult=  ActionParameterization.filter(stringList,t->t.equals("aa"));
       List<Integer> intResult=  ActionParameterization.filter(intList,t->t>=200);

       System.out.println("strResult==> "+strResult);
       System.out.println("intResult==> "+intResult);

    }
    public  static <T> List<T>  filter(List<T> list, Predicate<T> predicate){
       return list.stream().filter(t->predicate.test(t)).collect(Collectors.toList());
    }

}
