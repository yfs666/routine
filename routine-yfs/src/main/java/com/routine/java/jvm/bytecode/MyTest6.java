package com.routine.java.jvm.bytecode;

/**
 * 方法的动态分派
 * 方法接收者：方法是由哪一个对象调用的，invokevirtual字节码指令的多态查找的流程
 * 比较方法重载和重写
 * 方法重载是静态的，是编译器行为，方法重写是动态的，是运行期行为
 */
public class MyTest6 {
    public static void main(String[] args) {
        Fruit apple= new Apple();
        Fruit orange= new Orange();
        apple.test();
        orange.test();
    }
}

class Fruit {
    public void test() {
        System.out.println("Fruit");
    }
}


class Apple extends Fruit {
    public void test() {
        System.out.println("Apple");
    }
}


class Orange extends Fruit {
    public void test() {
        System.out.println("Orange");
    }
}

