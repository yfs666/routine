package com.routine.java.jvm.classloader;

public class MyTest12 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        // 调用loader的loadClass方法，并不是对类的主动使用，不会导致类的初始化
        Class<?> aClass = systemClassLoader.loadClass("com.routine.java.jvm.classloader.MyParent12");
        System.out.println(aClass);
        System.out.println("------------------");
        aClass = Class.forName("com.routine.java.jvm.classloader.MyParent12");
        System.out.println(aClass);
    }
}


class MyParent12 {
    static int a = 3;

    static {
        System.out.println("MyParent12 static block");
    }

    static void doSome() {
        System.out.println("MyParent static method doSome");
    }
}

