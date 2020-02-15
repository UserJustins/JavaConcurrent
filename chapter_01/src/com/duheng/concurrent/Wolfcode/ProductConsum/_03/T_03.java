package com.duheng.concurrent.Wolfcode.ProductConsum._03;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
    如果没有线程在等待池中，调用notifyAll会怎样?
 什么也没有发生
 *************************/
public class T_03 {

    public synchronized void m (){
        System.out.println("m");
        this.notifyAll();
    }

    public static void main(String[] args) {
        T_03 t = new T_03();
        new Thread(()->t.m()).start();
        new Thread(()->t.m()).start();
        new Thread(()->t.m()).start();
    }
}
