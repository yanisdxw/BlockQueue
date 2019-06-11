package com.dxw.blockQueue.consumer;

import com.dxw.blockQueue.blockqueue.BlockingQueue;

/**
 * @author ：dxw
 * @date ：Created in 2019/6/11 17:17
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class Consumer implements Runnable{

    private BlockingQueue queue;

    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }

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
}
