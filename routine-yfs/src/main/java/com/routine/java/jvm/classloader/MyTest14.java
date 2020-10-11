package com.routine.java.jvm.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MyTest14 {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        String resource = "com/routine/java/jvm/classloader/MyParent13.class";
        Enumeration<URL> urls = classLoader.getResources(resource);
        while (urls.hasMoreElements()) {
            System.out.println(urls.nextElement());
        }
        System.out.println("------------------------------------------------------");
        // 启动类加载器加载rt.jar中的类
        Class<?> clazz = String.class;
        System.out.println(clazz.getClassLoader());
    }
}
