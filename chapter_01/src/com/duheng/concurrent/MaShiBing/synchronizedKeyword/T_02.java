package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/9
 Describe:
    前一个代码，为了锁而去new一个对象出来，显然不合理
        可以使用当前对象（this)充当锁
 *************************/
public class T_02 {
    int count = 10;

    /**
     * 和该方法等同的写法就是m2()
     */
    public void m1 (){
        synchronized (this){
            if (count > 0) {

                System.out.println(Thread.currentThread().getName()+count--);
            }
        }
    }

    /**
     * 和m1()是一样的，同样拿this充当锁
     */
    public synchronized void m2(){
        if (count > 0) {

            System.out.println(Thread.currentThread().getName()+count--);
        }
    }
}
