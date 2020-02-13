package com.duheng.concurrent.MaShiBing.exercise;

import java.util.ArrayList;
import java.util.List;

/*************************
 Author: 杜衡
 Date: 2020/2/11
 Describe:
    随便使用哪个容器，使用add和size方法实现自己的add和size方法
    两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数；
 当线程的1添加到5的时候，线程2给出提示并结束
    以下的代码能够实现吗？
 如果了解JMM的话，不难看出实现不了
 *************************/
public class Exer_01 {
    List<Integer> list = new ArrayList<>();

    public void add(Integer i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }


    public static void main(String[] args) {
        Exer_01 e = new Exer_01();

        //创建线程1实现添加
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                e.add(i);
                System.out.println("add" + i);
                try {
                    Thread.sleep(1000);//为了执行慢一点
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }).start();
        //创建线程2实现对线程1的监控
        new Thread(()->{
            while (true){
                if (e.size()==5) {
                    break;
                }
            }
            System.out.println("t2结束");
        }).start();
    }
}
