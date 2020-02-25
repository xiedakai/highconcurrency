package com.kay.thread.atomic.unsafe;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/***
 *
 * 深入了解unsafe需要汇编操作系统  c++等相关的知识.暂不深入吧
 *
 *
 */
public class UnsafeTest {


    public static void main(String[] args) {
        //getUnsafe();
        //init();
        modifyFiled();
    }

    /**
     * 获取unsafe实例
     *
     * @return
     */
    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe"); // final私有的示例
            field.setAccessible(true);
            //获取那个对象上的字段  因为是静态方法，所以传null
            Unsafe unsafe = (Unsafe) field.get(null);
            System.out.println(unsafe);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Unsafe加载类 绕过初始化，直接开辟内存，创建对象
     */
    public static void init() {
        try {

            Simple simple1 = Simple.class.newInstance(); //会调用构造方法
            System.out.println("simple1 = " + simple1.i);
            //通过classLoader加载一个类,并不会初始化,不调用构造方法
            Class.forName("com.kay.thread.atomic.unsafe.UnsafeTest$Simple");

            //使用Unsafe 绕过初始化，直接开辟内存
            Unsafe unsafe = getUnsafe();
            Simple simple2 = (Simple) unsafe.allocateInstance(Simple.class);
            System.out.println("simple2 = " + simple2.i);
            System.out.println("simple2 ClassLoader= " + simple2.getClass().getClassLoader());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *  通过unsafe 强制修改私有属性的值
     *  q:这个和反射修改属性值有什么区别???
     *
     *
     */
    public static void modifyFiled() {
        Guard guard1 = new Guard();
        guard1.work(); //不能工作

        Unsafe unsafe = getUnsafe();
        try {
            Field field = guard1.getClass().getDeclaredField("allow_filed");
            // 通过unsafe 强制修改私有属性的值
            unsafe.putLong(guard1, unsafe.objectFieldOffset(field), 100);//第二个参数是偏移量
            guard1.work(); //被unsafe修改了allow_filed的值 能工作了

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    static class Simple {
        private long i;

        Simple() {
            this.i = 1L;
            System.out.println("==========");
        }

        public long getI() {
            return i;
        }

        public void setI(long i) {
            this.i = i;
        }
    }


    static class Guard {

        private int allow_filed = 1;

        private boolean allow() {
            return allow_filed == 100;
        }

        public void work() {
            if (allow()) {
                System.out.println("is working");
            }
        }
    }


}
