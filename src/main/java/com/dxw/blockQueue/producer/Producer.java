package com.dxw.blockQueue.producer;

import com.dxw.blockQueue.blockqueue.BlockingQueue;

/**
 * @author ：dxw
 * @date ：Created in 2019/6/11 17:32
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class Producer implements Runnable {

    private BlockingQueue queue;

    public Producer(BlockingQueue queue){
        this.queue = queue;
    }

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
}
