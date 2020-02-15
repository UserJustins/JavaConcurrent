package com.duheng.concurrent.Wolfcode.ProductConsum.T_04;

import java.util.ArrayList;
import java.util.List;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
    使用固定容量的容器，一次生产和消费多个
 *************************/
public class T_03<T> {

    final private List<T> list = new ArrayList<>();
    final private int MAX = 10;

    public synchronized void put(T t){
        while (list.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        this.notifyAll();
    }

    public synchronized void get(){
        while (list.size() != MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        list.clear();
        this.notifyAll();

    }

    public static void main(String[] args) {
        T_03 t = new T_03();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                t.put(Thread.currentThread().getName()+i);
            }

        },"product").start();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                t.get();
            }

        },"consumer").start();
    }
}
