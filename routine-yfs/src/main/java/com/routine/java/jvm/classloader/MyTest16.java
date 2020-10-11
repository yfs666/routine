package com.routine.java.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyTest16 extends ClassLoader {

    private String classLoaderName;

    private String path;

    public MyTest16(String classLoaderName) {
        // 系统类加载器当做该类的父加载器
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent, String classLoaderName) {
        // 显式指定该类的父加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("使用自定义的类加载器");
        byte[] data = this.loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        name = name.replace(".", "/");
        try {
//            this.classLoaderName = this.classLoaderName.replace(".", "/");
            is = new FileInputStream(new File(this.path + name + ".class"));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != is.read()) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void test(ClassLoader classLoader) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = classLoader.loadClass("com.routine.java.jvm.classloader.MyTest1");
        Object o = clazz.newInstance();
        System.out.println(o);
        System.out.println(o.getClass().getClassLoader());
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MyTest16 myTest16 = new MyTest16("loader1");
        // 设置类所在的绝对路径,使用工程外的路径
        // classpath中不能存在该类，否则会直接加载，就不会到绝对路径去加载
        myTest16.setPath("");
        test(myTest16);




    }


    public void setPath(String path) {
        this.path = path;
    }
}
