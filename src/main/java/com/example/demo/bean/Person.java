package com.example.demo.bean;

public class Person {
    static {
        System.out.println("这是静态代码块");

    }

    public static int age = 3333;

    {
        System.out.println("这是普通代码块");
    }

    public int Age = 222;

    public Person() {

        System.out.println("构造器");
    }

    public static void main(String[] args) {
        Person person=new Person();
        System.out.println(111);
    }


}