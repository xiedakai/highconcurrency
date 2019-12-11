package com.kay.nobug;

import java.util.Arrays;
import java.util.List;

public class ClearingKit {

    public static void main(String[] args) {
        String[] repeatAry="1,2".split(",");
        List<String> list=Arrays.asList(repeatAry);
        list.stream().forEach(str->{
            System.out.println(Arrays.asList("str: "+str));
        });

    }
}
