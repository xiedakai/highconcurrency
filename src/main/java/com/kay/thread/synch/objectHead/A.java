package com.kay.thread.synch.objectHead;

import org.openjdk.jol.info.ClassLayout;

/**
 *  https://www.cnblogs.com/LemonFive/p/11246086.html
 */
public class A {

    private boolean flag =false;

    public static void main(String[] args) {
        //seeObjectHead01();
        see_biased_lock();
    }

    /**
     *
     *
     *  查看一个简单对象的对象头
     *
         OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
         0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         8     4           (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
         12     1   boolean A.flag                                    false
         13     3           (loss due to the next object alignment)
         Instance size: 16 bytes
         Space losses: 0 bytes internal + 3 bytes external = 3 bytes total

     *
     */
    public static void seeSimpleObjectHead(){
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }

    /**
     *
     * 测试证明 对象头中的hash值与日志中的hash值相等
     *
     */
    public static void seeHashCode(){
        A a = new A();
        System.out.println("hashCOde = "+ a.hashCode());
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }


    /**
     * 偏向锁的对象
     *  特别注意：
     *      thread.getId()获取到的id并不是存在于对象头中的threadId
     *      经自己试验证明 thread.eetop字段和对象头中的threadId相等。
     *      故thread.eetop是线程的id。
     *  eetop
     *
     140451361333248+5=140451361333253
                        140451361333253
     011111111011110101100001100000000010000000000101
     *
     */
    public static void see_biased_lock(){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        A a = new A();
        synchronized (a){
            System.out.println("ThreadId = " +Thread.currentThread().getId());
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
    }

}
