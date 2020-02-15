package com.duheng.concurrent.Wolfcode.ProductConsum._02;

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

    /**
     *
     * 想多了，一直不理解结果是这样；ShareResource不是容器，每次就只是一个对象
     * 会覆盖的
     */
    @Override
    public void run() {
        //③生产者拿到了锁进入代码块中
        synchronized (source){

            for (int i = 0; i < 10; i++) {

                if (i % 2==0) {
                    source.push("杜衡","男");
                }else{
                    source.push("姜莹","女");
                }
                //④生产完成后，唤醒消费者
                source.notifyAll();
                try {
                    source.wait();//⑤并且要释放掉锁，这样阻塞状态的消费者就能拿到锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

}
}
