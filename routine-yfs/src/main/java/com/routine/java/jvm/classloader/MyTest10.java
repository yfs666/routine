package com.routine.java.jvm.classloader;

public class MyTest10 {
    static {
        System.out.println("MyTest10 static block");
    }

    public static void main(String[] args) {
        MyParent10 myParent10;
        System.out.println("1------------");
        myParent10 = new MyParent10();
        System.out.println("2------------");
        System.out.println(myParent10.a);
        System.out.println("3------------");
        System.out.println(MyChild10.b);
    }
}

class MyParent10 {
    static int a = 3;

    static {
        System.out.println("MyParent10 static block");
    }
}


class MyChild10 extends MyParent10{
    static int b = 4;

    static {
        System.out.println("MyChild10 static block");
    }
}
