package com.kay.thread.jmm;

public class JMMInAction {



   static class MyData{

        private volatile int num = 0;

        public void addTo60(){
            this.num = 60;
        }

        public void plusPlus(){
            this.num++;
        }

    }


    public static void testYuanZiXing(){
       //volatile 不保证原子性
        MyData myData = new MyData();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for(int j = 0; j < 1000; j++){
                    myData.plusPlus();
                }
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){
            /**
             * jvm默认有GC线程 + 主线程
             * 当还有上面的自定义线程未结束时，让出当前线程。
             */
            Thread.currentThread().yield();
        }
        System.out.println("num = "+myData.num);
    }

    public static void testKeJianXing(){
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" come in ");
            try {
                Thread.currentThread().sleep(3*1000);
                System.out.println(Thread.currentThread().getName()+" wake up  ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
        },"a").start();

        while (myData.num==0){
            /**
             * 1. 当没有volatile修饰时,即使被子线程修改为60,主线程也一直看不见最新的值,线程不会退出
             * 2. 当被volatile修饰,  子线程修改了值，主线程能立马看见
             */
        }
        System.out.println(Thread.currentThread().getName()+"\t main finish ");
    }


    public static void main(String[] args) {
        //testKeJianXing();
        testYuanZiXing();
    }

}