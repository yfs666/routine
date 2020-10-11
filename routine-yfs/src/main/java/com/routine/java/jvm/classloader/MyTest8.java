package com.routine.java.jvm.classloader;

import java.util.Random;

public class MyTest8 {
    public static void main(String[] args) {
        System.out.println(FinalTest.y);
    }
}

class FinalTest{
    /**
     * 编译期间的常量，运行时不需要了
     */
    public static final int x = 3;
    public static final int y = new Random().nextInt(20);
    static {
        System.out.println("FinalTest static block");
    }
}
