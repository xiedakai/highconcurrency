package com.kay.jvm.ref;

import java.lang.ref.WeakReference;




public class WeakRefernceDemo {

    public static void main(String[] args) {


        Object obj = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(obj);
        System.out.println("obj = "+ obj);
        System.out.println("weakReference obj = "+ weakReference.get());

        obj = null;
        System.gc();
        System.out.println("obj = "+ obj);
        System.out.println("weakReference obj = "+ weakReference.get());

    }




}
