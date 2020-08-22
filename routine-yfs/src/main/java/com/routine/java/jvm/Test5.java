package com.routine.java.jvm;

import java.util.Random;

public class Test5 {
    public static void main(String[] args) {
        System.out.println(MyChild5.b);
    }
}

// 程序在运行期间，压根都没有加载下面两个类
interface MyParent5 {
    // 静态变量，编译期间不会加载，如果是通过一个方法获取，例如是一个随机数算法，那么编译的时候，接口static是多余的，不加也是静态的，类则不是
    public static final int a = 10;
}

interface MyChild5 extends MyParent5 {
    public static final int b = 5;
}