package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/9
 Describe:
    分析下面的代码 加synchronized和不加的情况
 *************************/
public class T_04 implements Runnable{
    int count = 10;

    /**
     * 其中的一种情况：
     * THREAD0count:8 这两个8是胡闹
     * THREAD1count:8
     * THREAD2count:7 从头到尾方法中就自个儿
     * THREAD4count:5 这两个是线程3输在了打印到控制台的这个点
     * THREAD3count:6
     *      线程1拿到10进来减1，但是还没来得及打印出count值(获取线程的名字不得要时间呀)
     *    count是9，此时线程2进来了又减1等于8.接着线程1和线程2接连打印count的值了，就是
     *    8，程序完了也就没有线程1和2什么事了。
     *      线程3进来了，它减1和打印一气呵成没有其它的线程来打扰。count是7。线程4进来减1
     *    count是6了，输出语句中它也拿到count=6的值了，马上就要打印到控制台了，可偏偏就
     *    在此时线程5进来减1，同样输出语句中它也拿到count=5的值了。也就是说线程4和线程5
     *    分别拿着count=6和count=5准备打印了，按理说线程进来的早先打印，可是多线程哪有
     *    什么先到先得的道理呀，没这规矩嘛。线程5就不客气抢先一步了
     *
     *
     *
     *
     */
    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+"count:"+count);
    }

    public static void main(String[] args) {
        T_04 t = new T_04();
        for (int i = 0; i <5; i++) {
            //创建五个线程
            new Thread(t,"THREAD"+i).start();
        }
    }
}
