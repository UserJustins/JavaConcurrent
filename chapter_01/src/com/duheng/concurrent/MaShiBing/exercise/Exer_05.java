package com.duheng.concurrent.MaShiBing.exercise;

/*************************
 Author: 杜衡
 Date: 2020/2/11
 Describe:
    怎么保证线程的执行顺序？
 1.t.join():阻塞主线程，当前线程t执行完成后，接着执行。
 显然这个方法根本就不行，线程1进入了阻塞状态，需要2来唤醒，可2永远都执行不到
 2.
 *************************/
public class Exer_05 {
}
