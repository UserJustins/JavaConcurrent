package com.duheng.concurrent.Wolfcode.ProductConsum._02;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
 *************************/
public class Consumer implements Runnable{
    /**
     * 保证消费者和生产者操作的是同一个资源对象
     */
    private ShareResource source = null;

    public Consumer(ShareResource source){

        this.source = source;
    }

    /**
     * 消费者进行消费
     */
    @Override
    public void run() {
        //①消费者先启动，拿到锁，进入同步代码块
        synchronized (source){//注意同步块的粒度
            for (int i = 0; i < 10; i++) {
                //⑧唤醒生产者还不行，要②释放掉锁才行
                try {
                    source.wait();//②进来等着，释放了锁，生产者就会拿到锁

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //⑥生产者那边释放了锁，消费者得到了接着执行
                source.pop();
                //⑦消费完成，唤醒生产者起来生产
                source.notifyAll();
            }
        }
    }
}
