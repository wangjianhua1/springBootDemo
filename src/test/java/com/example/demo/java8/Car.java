package com.example.demo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * JDK 1.8之前已有的函数式接口:
 * <p>
 * ·        java.lang.Runnable
 * <p>
 * ·        java.util.concurrent.Callable
 * <p>
 * ·        java.security.PrivilegedAction
 * <p>
 * ·        java.util.Comparator
 * <p>
 * ·        java.io.FileFilter
 * <p>
 * ·        java.nio.file.PathMatcher
 * <p>
 * ·        java.lang.reflect.InvocationHandler
 * <p>
 * ·        java.beans.PropertyChangeListener
 * <p>
 * ·        java.awt.event.ActionListener
 * <p>
 * ·        javax.swing.event.ChangeListener
 * JDK 1.8 新增加的函数接口：
 * <p>
 * ·        java.util.function
 */
public class Car {
    //函数式接口
    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

    public interface Vehicle {
        default void print() {
            System.out.println("我是一辆车!");
        }
        static void length(){
            System.out.println("4米");
        }
    }

    public interface FourWheeler {
        default void print() {
            System.out.println("我是一辆四轮车!");
        }
    }

    /**
     * 重写默认方法
     */
    class MyCar implements Vehicle, FourWheeler {

        @Override
        public void print() {
            System.out.println("我是一辆四轮汽车!");
        }
    }

    /**
     * 指定父类中默认方法
     */
    class My2Car implements Vehicle, FourWheeler {
        public void print() {
            Vehicle.super.print();
            Vehicle.length();
        }
    }


    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println(" Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        //构造器引用：它的语法是Class:new,或者更一般的Class<T>::new 实例如下：
        Car car = Car.create(Car::new);
        Car car1 = Car.create(Car::new);
        Car car2 = Car.create(Car::new);
        Car car3 = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car, car1, car2, car3);
        //静态方法引用，它的语法是Class::static_method.实例如下
        cars.forEach(Car::collide);
        //特定类的任意对象的方法引用，语法Class::method
        cars.forEach(Car::repair);
        //特定对象的方法引用，语法是instance::method
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
        cars.forEach(System.out::println);//打印对象的toString方法


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //接口里default方法不需要实现
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer n) {
                return n % 2 == 0;
            }
        };
        for (Integer it : list) {
            if (predicate.test(it)) {
                System.out.println(it + " ");
            }
        }

    }
}
