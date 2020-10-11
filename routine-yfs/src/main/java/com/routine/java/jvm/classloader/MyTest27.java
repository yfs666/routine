package com.routine.java.jvm.classloader;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyTest27 {
    public static void main(String[] args) throws Exception {
        Class.forName("");
        Connection connection = DriverManager.getConnection("", "", "");
    }
}
