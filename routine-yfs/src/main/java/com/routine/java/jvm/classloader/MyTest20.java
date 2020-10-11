package com.routine.java.jvm.classloader;

import java.lang.reflect.Method;

public class MyTest20 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");
        Class<?> aClass = loader1.loadClass("xxx.xxx.MyPerson");
        Class<?> aClass2 = loader2.loadClass("xxx.xxx.MyPerson");
        // 两个都是被应用类加载器加载，因此他们结果是一样
        System.out.println(aClass == aClass2);
        Object o1 = aClass.newInstance();
        Object o2 = aClass2.newInstance();
        Method setMyPerson = aClass.getMethod("setMyPerson", Object.class);
        setMyPerson.invoke(o1, o2);
    }
}
