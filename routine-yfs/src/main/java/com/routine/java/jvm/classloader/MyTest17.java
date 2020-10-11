package com.routine.java.jvm.classloader;

public class MyTest17 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        // 下面一行只是把字节码加载到内存中，此时还未实例化对象
        Class<?> clazz = loader1.loadClass("com.routine.java.jvm.classloader.MySample");
        Object o = clazz.newInstance();
    }
}
