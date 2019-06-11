package com.dxw.blockQueue;

import com.dxw.blockQueue.blockqueue.BlockingQueue;
import com.dxw.blockQueue.blockqueue.impl.OptimisticQueue;
import com.dxw.blockQueue.blockqueue.impl.PessimisticQueue;
import com.dxw.blockQueue.consumer.Consumer;
import com.dxw.blockQueue.producer.Producer;

/**
 * @author ：dxw
 * @date ：Created in 2019/6/11 17:34
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class testCP {
    public static void main(String[] args) {
        final BlockingQueue<Integer> queue = new OptimisticQueue<Integer>(3); //缓冲区允许放3个数据
        Producer p = new Producer(queue);
        Consumer c = new Consumer(queue);
        new Thread(p).start();
        new Thread(c).start();
    }
}
