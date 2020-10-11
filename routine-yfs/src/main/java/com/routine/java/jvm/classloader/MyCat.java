package com.routine.java.jvm.classloader;

public class MyCat {

    public MyCat() {
        System.out.println("MyCat constructor" + this.getClass().getClassLoader());
    }



}
