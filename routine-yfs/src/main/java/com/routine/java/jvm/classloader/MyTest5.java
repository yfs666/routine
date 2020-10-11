package com.routine.java.jvm.classloader;


/**
 * 当一个接口初始化的时候，并不需要其父接口都完成了初始化，这里类和接口还是不一样的。
 * 接口中的常量值默认是public static final ，因此，无此三个标识的常量，也是会放到常量池中的，除非是编译期间不能确定其值。
 */
public class MyTest5 {
    public static void main(String[] args) {
        System.out.println(MyChild5.b);
        // 下面即使接口中没有final，也不会通过编译，因为默认是有final的
//        MyParent5.a = 15;
    }
}

// 程序在运行期间，压根都没有加载下面两个类
interface MyParent5 {
    // 静态变量，编译期间不会加载，如果是通过一个方法获取，例如是一个随机数算法，那么编译的时候，接口static是多余的，不加也是静态的，类则不是
//    public static int a = 10;
    Thread thread = new Thread() {
        {
            // 看接口和类，是否输出来做对比
            System.out.println("thread invoke ...");
        }
    };
}

interface MyChild5 extends MyParent5 {
    public static final int b = 5;
}


class D {
    /**
     * 首次使用才执行
     */
    static {
        System.out.println("static D");
    }

    /**
     * 每次初始化都执行
     */
    {
        System.out.println(" not static D");
    }
    public D() {
        System.out.println("construct D");
    }
}
