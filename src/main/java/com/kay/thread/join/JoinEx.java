package com.kay.thread.join;

public class JoinEx {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    count += 1;
                }
            }
        });
        t.start();
        t.join();
        System.out.println(count);

    }

}
