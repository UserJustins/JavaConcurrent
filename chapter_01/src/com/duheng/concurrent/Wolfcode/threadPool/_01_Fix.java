package com.duheng.concurrent.Wolfcode.threadPool;

import java.util.concurrent.*;

/*************************
 Author: 杜衡
 Date: 2020/2/14
 Describe:
 *************************/
public class _01_Fix {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            Future<Integer> result = threadPool.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    TimeUnit.SECONDS.sleep(1);
                    for (int i = 0; i < 5; i++) {
                        sum += i;
                    }
                    return sum;
                }
            });
            System.out.println(result.get());
        }


        threadPool.shutdown();
    }
}
