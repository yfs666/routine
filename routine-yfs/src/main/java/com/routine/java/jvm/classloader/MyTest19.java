package com.routine.java.jvm.classloader;

import com.sun.crypto.provider.AESKeyGenerator;

public class MyTest19 {
    public static void main(String[] args) {
        // 修改环境变量，也可以修改执行结果
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(MyTest19.class.getClassLoader());
    }
}
