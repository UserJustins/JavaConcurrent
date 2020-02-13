package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/10
 Describe:
    保证张三、李四、王五的钱只能是自个儿的
        下面的代码片段实现了，但是我只能让set调用get才能保证每个线程的锁是同一把锁
 疑惑的是：set和get方法分开调，我怎么才能保证同步？

 *************************/
public class T_07 {
    String name;
    double balance;

    /**
     * @param balance 余额
     */
    public synchronized String set(String name,double balance){
        this.name = name;
        try {
            Thread.sleep(1000);//模拟业务方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
        return get(name);
    }
    public String get(String name){

        return name+"的账户余额是："+balance+ Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        T_07 t = new T_07();
        //new Thread(()->t.set("王五",100.0),"王五").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(t.set("王五", 500.0));
                //System.out.println(t.get("王五"));
            }
        },"王五").start();
        //new Thread(()->t.set("李四",200.0),"李四").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(t.set("李四", 400.0));
                //System.out.println(t.get("李四"));
            }
        },"李四").start();
        //new Thread(()->t.set("张三",300.0),"张三").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(t.set("张三", 300.0));
                //System.out.println(t.get("张三"));
            }
        },"张三").start();

    }
}
