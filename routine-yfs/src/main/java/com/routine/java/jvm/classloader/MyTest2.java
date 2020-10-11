package com.routine.java.jvm.classloader;

/**
 * 在类的编译阶段，常量就会被放在调用该常量的方法所在的那个类的常量池中
 * 本质上，调用类并没有直接引用到定义常量的类，因此并不会触发定义该常量的类的初始化，也可以解释静态代码块没有被执行
 * 例如：MyParent2.str回放到MyTest2的常量池中，之后他俩之间就不会有任何关系，即使编译后把MyParent2的class文件删除都没有问题
 *
 * 反编译
 */
public class MyTest2 {
    public static void main(String[] args) {
        System.out.println(MyParent2.str);
    }
}


class MyParent2 {
    public static final String str = "MyParent2 hello world";

    static {
        System.out.println("MyParent2 static block");
    }
}
