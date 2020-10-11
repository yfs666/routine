package com.routine.java.jvm.classloader;

/**
 * 扩展类加载器要加载的话，需要从jar包中读取，直接读.class文件不行
 */
public class MyTest22 {

    static {
        System.out.println("MyTest22 static block...");
    }

    public static void main(String[] args) {
        System.out.println(MyTest22.class.getClassLoader());
        System.out.println(MyTest2.class.getClassLoader());
    }
}
