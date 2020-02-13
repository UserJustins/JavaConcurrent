package com.duheng.concurrent;

import sun.applet.Main;

/*************************
 Author: 杜衡
 Date: 2019/12/30
 Describe:
        创建线程实现同时读和写的功能，这个列子看起来不太明智，可是试着使用多个循环
 *************************/
public class ReadWithWrite {
    /*
        分别创建两个线程来同时做这件事
     */
    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                readFromDataBase();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();
    }

    public static void readFromDataBase(){
        try {
            println("read dataBase begain...");
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("read is over....");
    }
    public static void writeDataToFile(){
        try {
            println("write data begain...");
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("write is over....");
    }

    public static void println(String message){
        System.out.println(message);
    }
}
