package com.routine.java.jvm.classloader;

import java.util.UUID;

/**
 * 当一个常量的值并非编译期间可以确定的，那么其值就不会被放到调用类的常量池中
 * 这是在程序运行时，回导致主动使用这个常量所在的类，显然这个类会被初始化
 */
public class MyTest3 {
    public static void main(String[] args) {
        System.out.println(MyParent3.str);
    }
}

class MyParent3 {
    /**
     * 不是编译期的常量，因为编译期间无法确定固定的值
     */
    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("MyParent static code");
    }
}

class MyChild {
}
