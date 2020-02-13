package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/9
 Describe:
    synchronized关键字锁的某个对象，如果对象的属性改变了，不会影响锁
 但是o的引用对象发生的了改变，锁对象就变了，响应的代码片段在T_09中
 *************************/
public class T_01 {

    int count = 10;

    Object o = new Object();

    /**
     * 1、synchronized锁的就是o这个对象本身，并不是对象的引用
     *  如果o的引用对象发生了改变，那么锁对象也就变了
     * 2、同步代码块在执行时，需要o对象作为锁
     * 3、多个线程执行时，同一时刻、保证只有一个线程拥有锁
     */
    public void m(){
        synchronized (o){
            System.out.println(Thread.currentThread().getName()+count--);
        }
    }
}
