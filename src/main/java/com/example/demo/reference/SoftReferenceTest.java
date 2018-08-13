package com.example.demo.reference;

import java.lang.ref.SoftReference;
import java.util.Objects;

/**
 * 强引用：只要强引用还存在，垃圾收集器永远不会回收掉被引用的对象。（当对象不再使用时可以手动置为空）
 * 软引用：用来描述一些还有用并非必要的对象。
 * 对于软引用关联着的对象，在系统将要发生内存溢出异常之前，将会把这些对象列入回收范围进行第二次回收。
 * 如果这次回收还没有足够的内存，才会抛出内存溢出异常
 * 弱引用：当垃圾收集器工作时，无论当前内存是否足够，都会回收掉只被弱引用关联的对象
 * 虚引用：一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用来获取一个对象的实例。为一个对象设置虚引用关联的唯一目的就是能在这个对象被收集器回收时收到一个系统通知
 *
 * @JDk1.2
 */
public class SoftReferenceTest {
    private Object obj = new Object();//此obj引用的是堆

    private static class BiggerObject implements Cloneable {//占用空间打的一个对象
        public int[] value;
        public String name;

        public BiggerObject(String name) {
            this.name = name;
            value = new int[10240];
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static void main(String[] args) {
        try {
            int count = 100;//mark //此count引用，存于栈中,内容存在栈中
            SoftReference[] values = new SoftReference[count];//mark //此count引用，存于栈中,内容存在堆中
            for (int i = 0; i < count; i++) {
                values[i] = new SoftReference<BiggerObject>(new BiggerObject("Object-" + i));
            }
            System.out.println(((BiggerObject) Objects.requireNonNull(values[values.length - 1].get())).name);
            for (int i = 0; i < 10; i++) {
                System.out.println(values[i].get());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

/**
 * 实现Cloneable接口，并重写clone方法
 * 1.浅复制：复制一个对象时候，如果有引用属性，只能简单福州话对象，引用都不变
 * 2.深复制：复制对象时候，引用属性也需要实现Cloneable接口,并重写，需要在clone方法里调用引用对象的复制方法
 * <p>
 * TODO 运行时常量池相对于Class文件常量池的另外一个重要特征是具备动态性，
 * Java语言并不要求常量一定只有编译期才会产生，也就是并非预置入Class文件中常量池的内容才能进入方法区运行时常量池，
 * 运行期间也可能将新的常量放入池中，这种特性被开发人员利用的比较多的便是String类的intern()方法。
 * 既然运行时常量池是方法区的一部分，自然受到方法区内存的限制，当常量池无法再申请到内存时会抛出OutOfMemoryError异常。
 * <p>
 * <a href="https://blog.csdn.net/zhoudaxia/article/details/26454421/">虚拟机</a>
 * 操作码的作用分别
 * invokeinterface:调用一个接口方法
 * invokespecial:调用一个初始化方法，私有方法或者父类的方法
 * invokestatic:调用静态方法
 * invokevirtual:调用实例方法
 * <p>
 * 单例模式初始化时候需要锁是因为在jvm实例化对象时候经历
 * new dup astore_1
 * new 一个对象底层会做:
 * 1、jvm加载未加载的字节码，开辟空间
 * 2、静态初始化
 * 3、成员初始化
 * 4、构造器初始化
 * <p>
 * 1) 装载：查找并加载类的二进制数据；
 * 2)链接：
 *      验证：确保被加载类的正确性；
 *      准备：为类的静态变量分配内存，并将其初始化为默认值；
 *      解析：把类中的符号引用转换为直接引用；
 * 3)初始化：为类的静态变量赋予正确的初始值；
 */

