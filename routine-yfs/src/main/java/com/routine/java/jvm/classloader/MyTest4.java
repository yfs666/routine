package com.routine.java.jvm.classloader;

public class MyTest4 {
    public static void main(String[] args) {
//        MyParent4 myParent4 = new MyParent4();
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        // 只有首次主动使用才被初始化，而不是每次都初始化
//        MyParent4 myParent5 = new MyParent4();
        // 初始化数组不算初始化该类
        /**
         * 数组是在运行期间生成的类型，而不是固定的类型，多维数组同样的道理
         * 左方括号表示数组，一个[表示一维数罪，两个[表示二维数组，然后都是拼接一个L，后面紧跟对应的数组元素的类型
         * 数组的父类型是Object
         * class [Lcom.routine.java.jvm.classloader.MyParent4;
         * MyParent4 static code
         * class com.routine.java.jvm.classloader.MyParent4
         */
        MyParent4[] myParent4s = new MyParent4[1];
        System.out.println(myParent4s.getClass());
        MyParent4 myParent4 = new MyParent4();
        System.out.println(myParent4.getClass());

        int[] ints = new int[1];
        /**
         * class [I
         * class java.lang.Object
         */
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());
    }
}

class MyParent4 {
    static {
        System.out.println("MyParent4 static code");
    }
}
