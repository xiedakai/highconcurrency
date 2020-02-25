package com.kay.jvm.ref;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //softRef_memory_enough();
        softRef_memory_not_enough();
    }

    /**
     * 内存足够,软引用对象不回收
     */
    public static void softRef_memory_enough(){
        Object obj = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(obj);
        System.out.println("obj = "+ obj);
        System.out.println("softReference obj = "+ softReference.get());

        obj = null;
        System.gc();
        System.out.println("obj = "+ obj);
        System.out.println("softReference obj = "+ softReference.get());

    }

    /**
     * 内存不足,软引用对象回收
     *
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_memory_not_enough(){
        Object obj = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(obj);
        System.out.println("obj = "+ obj);
        System.out.println("softReference obj = "+ softReference.get());

        obj = null;
        System.gc();

        try {
            byte[] bytes = new byte[30 * 1024 * 1024]; //30MB
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            System.out.println("obj = "+ obj);
            System.out.println("softReference obj= "+ softReference .get() );
        }

    }

}
