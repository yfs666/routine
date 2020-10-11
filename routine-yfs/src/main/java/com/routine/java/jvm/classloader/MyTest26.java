package com.routine.java.jvm.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 线程上下文类加载器的一般使用模式（获取-使用-还原）
 * ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
 * try {
 *      Thread.currentThread().setContextClassLoader(targetCl);
 *      myMethod();
 * } finally {
 *      Thread.currentThread().setContextClassLoader(classLoader);
 * }
 * myMethod里面则调用了Thread.currentThread().getContextClassLoader()获取当前上下文类加载器做某些事情
 * 如果一个类由类加载器A加载，那么这个类的依赖类也是由相同的类加载器加载
 * ContextClassLoader就是为了破坏java类加载委托机制
 *
 * 当高层提供了同一的接口让低层实现，同时又要高层加载（或实例化）低层类时，就必须要通过线程上下文类加载器来帮忙高层的ClassLoader找到并加载该类
 */
public class MyTest26 {
    public static void main(String[] args) {
        // 如果修改了上下文加载器，可能就加载不到，比如下面可能会导致加载不到，因为他不扫描我们自己引入的jar包的内容
//        Thread.currentThread().setContextClassLoader(MyTest26.class.getClassLoader().getParent());
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println(driver.getClass() + ">>>>>>>>>>>>>>>>>>>" + driver.getClass().getClassLoader());
        }
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(ServiceLoader.class.getClassLoader());
    }
}
