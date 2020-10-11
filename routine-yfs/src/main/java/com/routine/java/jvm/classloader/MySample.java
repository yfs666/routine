package com.routine.java.jvm.classloader;

public class MySample {
    public MySample() {
        System.out.println("MySample constructor " + this.getClass().getClassLoader());
        MyCat myCat = new MyCat();
    }
}
