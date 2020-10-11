package com.routine.java.jvm.classloader;

public class MyTest11 {
    public static void main(String[] args) {
        /**
         * 变量定义在父类，则是对父类的主动使用，而不是子类的，此处是对MyChild11的主动使用
         */
        System.out.println(MyChild11.a);
        System.out.println(MyChild11.b);
        MyChild11.doSome();
    }
}

class MyParent11 {
    static int a = 3;

    static {
        System.out.println("MyParent11 static block");
    }

    static void doSome() {
        System.out.println("MyParent static method doSome");
    }
}

class MyChild11 extends MyParent11 {
    static int b = 3;

    static {
        System.out.println("MyChild11 static block");
    }
}
