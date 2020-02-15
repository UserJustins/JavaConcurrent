package com.duheng.concurrent.Wolfcode.ProductConsum;

/*************************
 Author: 杜衡
 Date: 2020/2/15
 Describe:
    是否有注释运行结果完全不一样？
 没有笔记，要时刻保存思考，主存和线程的工作内存
 *************************/
public class T_05 {

    public static void main(String[] args) {
        TT t = new TT();
        new Thread(t).start();

        while (true){
            if (t.isFlag()) {
                System.out.println("--------");
                break;
            }
        }

    }
}
class TT implements Runnable{
    private boolean flag = false;
    @Override
    public void run() {
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        this.flag = true;
        System.out.println(this.isFlag());
    }


    public boolean isFlag(){
        return this.flag;
    }

}