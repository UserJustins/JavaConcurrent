package com.duheng.concurrent.KouDingLang.ProductConsum._01;

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

    public Producter(ShareResource  source){
        this.source = source;

    }

    /**
     *
     * 想多了，一直不理解结果是这样；ShareResource不是容器，每次就只是一个对象
     * 会覆盖的
     */
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
