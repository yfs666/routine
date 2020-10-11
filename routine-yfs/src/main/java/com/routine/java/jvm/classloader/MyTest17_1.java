package com.routine.java.jvm.classloader;

/**
 * 子加载器所加载的类，能访问父加载器所加载的类
 * 父加载器加载的类，不能访问子加载器所加载的类
 */
public class MyTest17_1 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("/xxx/xxx/");
        // 下面一行只是把字节码加载到内存中，此时还未实例化对象
        Class<?> clazz = loader1.loadClass("com.routine.java.jvm.classloader.MySample");
        Object o = clazz.newInstance();
    }
}
