package com.duheng.concurrent.Wolfcode.ProductConsum._03;

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
        for (int i = 0; i < 10; i++) {
            source.pop();
        }
    }
}
