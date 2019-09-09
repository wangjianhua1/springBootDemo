package com.example.demo.java8;

public class Java8Tester {
    static String salutation = "Hello ";

    public static void main(String[] args) {
        int M = 5;//一旦被lambda里引用则变为隐式final局部变量
        Java8Tester tester = new Java8Tester();
        //类型声明
        MathOperation addition = (int a, int b) -> a + b + M;
        //不用类型声明
        MathOperation substraction = (a, b) -> a - b;
        //大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        //没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        //匿名方法
        System.out.println("10+5=" + tester.operate(10, 5, new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        }));
        System.out.println("10+5=" + tester.operate(10, 5, addition));
        System.out.println("10-5=" + tester.operate(10, 5, substraction));
        System.out.println("10x5=" + tester.operate(10, 5, multiplication));
        System.out.println("10/5=" + tester.operate(10, 5, division));

        GreetingService greetingService = message -> {
            salutation = "hello 2 ";
            System.out.println(salutation + message);
        };
        greetingService.sayMsg("JAVA8 !");
        System.out.println(salutation);

    }

    interface MathOperation {
        int operation(int a, int b);
//        String say(String msg);//以上lambda方式，接口里只能有一个方法
    }

    interface GreetingService {
        void sayMsg(String msg);
    }

    private int operate(int a, int b, MathOperation operation) {
        return operation.operation(a, b);
    }
}
