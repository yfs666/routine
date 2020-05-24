package com.yfs.mvc;

import java.util.HashMap;

public class MyService {

    private int age;

    private String name;

    public HashMap<String,Object> getResult() {
        return new HashMap(){{
            put("a","aa");
            put("b",123);
        }};
    }

}
