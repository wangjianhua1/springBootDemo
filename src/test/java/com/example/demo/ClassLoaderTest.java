package com.example.demo;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader.getSystemClassLoader();
        Class.forName("");
    }
}
