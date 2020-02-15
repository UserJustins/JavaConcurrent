package com.duheng.concurrent.Wolfcode.ProductConsum._02;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:

 *************************/

public class ShareResource {
    private String name;
    private String gender;

    public void push(String name,String gender) {
        this.name = name;
        //睡一会就会出现性别紊乱,甚至性别为null的情况
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.gender = gender;
    }

    public void pop(){
        System.out.println(this.name+"--"+this.gender);
    }



}
