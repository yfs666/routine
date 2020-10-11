package com.routine.java.jvm.classloader;

public class MyTest7 {
    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> clazz = Class.forName("java.lang.String");
//        ClassLoader classLoader = clazz.getClassLoader();
        Class<?> clazz = Class.forName("com.routine.java.jvm.classloader.C7");
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println(classLoader);
    }
}

class C7 {

}
