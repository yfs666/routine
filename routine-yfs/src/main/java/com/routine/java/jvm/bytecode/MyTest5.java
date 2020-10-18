package com.routine.java.jvm.bytecode;

/**
 * 方法的静态分派
 * GranPa5 g1 = new Father5();
 * 以上代码，g1的静态类型是GranPa5，而g1的实际类型(实际指向的类型)是Father5;
 * 结论：变量的静态类型是不会发生变化的，而变量的实际类型则是发生变化的（多态的一种体现），实际类型在运行期间确定
 *
 *
 */
public class MyTest5 {
    public void test(GranPa5 granPa5) {
        System.out.println("granPa5");
    }

    /**
     * 方法的重载是一种静态的行为，编译器就可以完全确定
     * @param father5 参数
     */
    public void test(Father5 father5) {
        System.out.println("father5");
    }

    public void test(Son5 son5) {
        System.out.println("son5");
    }

    public static void main(String[] args) {
        GranPa5 g1 = new Father5();
        GranPa5 g2 = new Son5();
        MyTest5 myTest5 = new MyTest5();
        myTest5.test(g1);
        myTest5.test(g2);
    }
}

class GranPa5 {

}

class Father5 extends GranPa5 {

}


class Son5 extends Father5 {

}
