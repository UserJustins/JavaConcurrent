package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/10
 Describe:
 线程在执行过程中遇到了异常，默认情况下(不进行catch)线程会释放锁
 *************************/
public class T_08 {
    int count = 0;

    public synchronized void m() {
        while (count < 10) {
            count++;
            System.out.println(Thread.currentThread().getName() + "," + count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {//线程会释放掉锁，要不想的话，进行catch
                //try {
                    int i = 1/0;
                //}catch (Exception e){
                 //   e.printStackTrace();
                //}
            }

        }

    }

    public static void main(String[] args) {
        T_08 t = new T_08();

        new Thread(() -> t.m(), "Thread1").start();
        new Thread(() -> t.m(), "Thread2").start();
    }
}
