package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/10
 Describe:
 *  切记：不要使用字符串常量作为锁定对象
 *  在下面的代码片段中，m1和m2方法其实锁定的是同一个对象
 *  这种情况下会发生比较诡异的现象，比如你用到了一个类库，不巧的是该类库
 * 中的代码也锁定了字符串“Hello”，但是你读不到源码，压根就不知道，此时在
 * 自己的代码中使用字符串常量当锁对象的话，就会造成死锁。
 *  因为你的程序和你用到的类库不经意间使用同一把锁
 *

 *************************/
public class T_10 {
    String s1 = "Hello";
    String s2 = "Hello";

    public void m1(){
        synchronized(s1){

        }
    }

    public void m2(){
        synchronized(s2){

        }
    }
}
