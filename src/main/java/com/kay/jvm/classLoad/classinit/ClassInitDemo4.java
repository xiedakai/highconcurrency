package com.kay.jvm.classLoad.classinit;

class Parent{

    public static int A = 1;
    static {
        A = 2;
    }
    //public static int A = 1;
}

class Sub extends Parent{
    public static int B = A;
}


public class ClassInitDemo4 {

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }


}
