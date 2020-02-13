package com.duheng.concurrent.KouDingLang.ProductConsum._03;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
    共享资源，其实就是商品
 *************************/

public class ShareResource {
    private String name;
    private String gender;
    private boolean isEmpty = true;

    public synchronized void push(String name,String gender) {
        //① 生产者先运行，肯定为true，
        while (!isEmpty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //4、生产者开始生产
        //②生产者开始生产
        this.name = name;
        this.gender = gender;
        //5、生产完成、不空
        //③生产完成、不空
        isEmpty = false;
        this.notifyAll();//6、唤醒消费者//④没有谁需要唤醒
    }//7、run()执行完成释放锁//⑤run()执行完成释放锁，生产者和消费者都有可能拿到锁

    public synchronized void pop(){
        //⑥假设消费者拿到锁进来了isEmpty = false;直接消费
        //1、消费者先启动
        while (isEmpty) {//2、生产者还没有生产 true
            try {
                this.wait();//3、释放锁进入阻塞，因此现在只有生产者线程能运行，得到锁的必定是生产者
                //8、拿到锁以后接着判断
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //9、进行消费
        //⑦消费
        System.out.println(this.name+"--"+this.gender);
        isEmpty = true;
        //⑧唤醒生产者
        this.notifyAll();//10、唤醒生产者


    }
    //11、run()执行完成就释放锁
    //⑨、run()执行完成就释放锁

}
