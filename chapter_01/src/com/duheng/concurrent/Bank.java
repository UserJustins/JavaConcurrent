package com.duheng.concurrent;

/*************************
 Author: 杜衡
 Date: 2020/1/1
 Describe:
        继承Thread方式的缺陷
 *************************/
public class Bank {
    public static void main(String[] args) {
        new TicketWindows().start();
        new TicketWindows().start();
    }

}

/**
 * 继承Thread类的方式创建的线程不共享资源
 */
class TicketWindows extends Thread{
    private final int ticketMax = 50;

    private int index = 1;

    @Override
    public void run() {

        while (index < ticketMax){

            System.out.println(Thread.currentThread().getName()+":---->"+(index++));
        }
    }
}
