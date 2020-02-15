package com.duheng.concurrent.Wolfcode.ProductConsum._03;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
 *************************/
public class Producter implements Runnable{
    /**
     * 保证消费者和生产者操作的是同一个资源对象
     */
    private ShareResource source = null;

    public Producter(ShareResource source){
        this.source = source;

    }


    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

                if (i % 2==0) {
                    source.push("杜衡","男");
                }else{
                    source.push("姜莹","女");
                }

        }
}
}
