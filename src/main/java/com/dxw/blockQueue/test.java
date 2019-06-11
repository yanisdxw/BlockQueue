package com.dxw.blockQueue;

import com.dxw.blockQueue.blockqueue.BlockingQueue;
import com.dxw.blockQueue.blockqueue.impl.PessimisticQueue;

/**
 * @author ：dxw
 * @date ：Created in 2019/6/11 16:20
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class test {

    public static void main(String[] args) {
        final BlockingQueue<Integer> queue = new PessimisticQueue<Integer>(3); //缓冲区允许放3个数据

        for(int i = 0; i < 2; i ++) {
            new Thread() { //开启两个线程不停的往缓冲区存数据

                @Override
                public void run() {
                    while(true) {
                        try {
                            Thread.sleep((long) (Math.random()*1000));
                            System.out.println(Thread.currentThread().getName() + "准备放数据"
                                    + (queue.getSize() == 3?"..队列已满，正在等待":"..."));
                            queue.enqueue(1);
                            System.out.println(Thread.currentThread().getName() + "存入数据，"
                                    + "队列目前有" + queue.getSize() + "个数据");
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

            }.start();
        }

        new Thread() { //开启一个线程不停的从缓冲区取数据

            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "准备取数据"
                                + (queue.getSize() == 0?"..队列已空，正在等待":"..."));
                        queue.dequeue();
                        System.out.println(Thread.currentThread().getName() + "取出数据，"
                                + "队列目前有" + queue.getSize() + "个数据");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
