package com.routine.java.jvm;

public class MyTest6 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.instance();
        System.out.println(Singleton.counter1);
        System.out.println(Singleton.counter2);
    }
}

/**
 * 类先准备，然后从上到下的顺序进行初始化
 */
class Singleton {
    public static int counter1 = 1;

    private static Singleton singleton = new Singleton();

    private Singleton() {
        counter1++;
        counter2 = 10;
    }

    // 根据代码的顺序，静态变量在重新初始化的时候，赋值为0，
    public static int counter2 = 0;

    public static Singleton instance() {
        return singleton;
    }
}

class C {
    {
        // 代码块会在实例化之前执行，如果有static，则只会执行一次，如果无static，则每次实例化之前都会执行
        System.out.println("hello");
    }

    public C() {

    }
}