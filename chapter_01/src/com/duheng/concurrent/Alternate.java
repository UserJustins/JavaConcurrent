package com.duheng.concurrent;

/*************************
 Author: 杜衡
 Date: 2019/12/30
 Describe:
    交替的打印数字 Alternate是交替的意思
 *************************/
public class Alternate {
    public static void main(String[] args) {
        new Thread("WRITE-thread"){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"--->write");
                }
            }
        }.start();
        new Thread("READ-thread"){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName()+"---------->read");
                }
            }
        }.start();
    }
}
