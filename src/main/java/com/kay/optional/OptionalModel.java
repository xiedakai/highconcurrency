package com.kay.optional;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalModel {

    /**
     *
     * @return
     */
    public Optional<String> autoReturnOptional(){
        Stream.of("123").filter(s->s.equals("123"));
        //
        return Stream.of("123").findAny();
    }


    public void exp(){

    }

}
