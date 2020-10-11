package com.routine.java.jvm.classloader;

public class MyTest18_1 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("/xxx/xx");
        // 把class文件放到 /Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/classes目录就可以演示启动类加载器
        Class<?> aClass = loader1.loadClass("com.xxx.xxx.a");
    }
}
