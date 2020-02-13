package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/9
 Describe:
    静态方法中，锁是什么呢？
        当前所在类的Class对象
 *************************/
public class T_03 {
    static int count = 10;

    public static void m1(){
        synchronized (T_03.class){//static方法的锁是T_03.class
            if (count > 0) {

                System.out.println(Thread.currentThread().getName()+count--);
            }
        }
    }

    public static synchronized void m2(){
        if (count > 0) {

            System.out.println(Thread.currentThread().getName()+count--);
        }
    }

}
