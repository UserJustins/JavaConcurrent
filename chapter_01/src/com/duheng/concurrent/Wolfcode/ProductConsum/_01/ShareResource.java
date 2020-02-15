package com.duheng.concurrent.Wolfcode.ProductConsum._01;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
    共享资源，其实就是商品
 *************************/

public class ShareResource {
    private String name;
    private String gender;

    public synchronized void push(String name,String gender) {
        this.name = name;
        //睡一会就会出现性别紊乱,甚至性别为null的情况
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.gender = gender;
    }

    public synchronized void pop(){
        System.out.println(this.name+"--"+this.gender);
    }


    @Override
    public String toString() {
        return "ShareResource{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
