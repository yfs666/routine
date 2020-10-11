package com.routine.java.jvm.classloader;

import java.lang.reflect.Method;

/**
 * 类加载器的双亲委托模型的好处
 * 1、可以确保java核心库的类型的安全：所有的java应用都至少会引用java.lang.Object类，也就是在运行期Object这个类会被加载到java虚拟机中；
 * 如果这个加载过程是由java应用自己的类加载器完成的，那么很可能就会在JVM中存在多个版本的Object，而这些类之间是不兼容的，相互不可见的，这就是
 * 命名空间在发挥着作用，借助于双亲委托机制，java核心类库中的加载工作都是启动类加载器统一完成，从而确保了java应用都是使用了同一版本的java核心
 * 类库，他们之间是相互兼容的
 * 2、可以确保java核心类库所提供的类不会被自定义的类所取代
 * 3、不同的类加载器可以为相同名称(binary name)的类创建额外的命名空间，相同名称的类，可以并行在java虚拟机中，只需要使用不同的类加载器加载即可，
 * 不同的类加载器所加载的类之间是不兼容的，这就能相当于在java虚拟机内部创建了一个又一个相互隔离的java类空间，这类技术在很多框架中都得到了实际应用
 *
 */
public class MyTest21 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("/xxx/xxx");
        MyTest16 loader2 = new MyTest16("loader2");
        loader2.setPath("/xxx/xxx");
        // 删除classpath下的class文件
        Class<?> aClass = loader1.loadClass("xxx.xxx.MyPerson");
        Class<?> aClass2 = loader2.loadClass("xxx.xxx.MyPerson");
        System.out.println(aClass == aClass2);
        Object o1 = aClass.newInstance();
        Object o2 = aClass2.newInstance();
        // 不同的loader加载，找不到对象，因为他们是两个不同的命名空间
        Method setMyPerson = aClass.getMethod("setMyPerson", Object.class);
        setMyPerson.invoke(o1, o2);
    }
}
