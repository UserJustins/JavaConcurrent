package com.duheng.concurrent.MaShiBing.exercise;

import java.util.ArrayList;
import java.util.List;

/*************************
 Author: 杜衡
 Date: 2020/2/11
 Describe:
    共享资源使用volatile，我们知道volatile只有可见性，不保证原子性
    该代码还是存在问题：
        1、线程2一直while很浪费CPU的资源
        2、因为不保证同步，当“e.size()==5”时线程1可能都运行完了，线程2都来不及通知
 可以把睡眠时间去掉，问题2一下子就暴露出来了


 因此单纯的加volatile的根本就不能解决问题。
 *************************/
public class Exer_02 {
    volatile List<Integer> list = new ArrayList<>();

    public void add(Integer i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }


    public static void main(String[] args) {
        Exer_02 e = new Exer_02();

        //创建线程1实现添加
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                e.add(i);
                System.out.println("add" + i);
               /* try {
                    Thread.sleep(1000);//为了执行慢一点
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }*/
            }
        }).start();
        //创建线程2实现对线程1的监控
        new Thread(()->{
            while (true){
                //在这判断的时候，线程1都不知道加到多少了，可线程2还没有通知呢
                if (e.size()==5) {
                    break;
                }
            }
            System.out.println("t2结束");
            //当size等于5的时候，程序来到这，该线程提醒并结束
        }).start();
    }
}
