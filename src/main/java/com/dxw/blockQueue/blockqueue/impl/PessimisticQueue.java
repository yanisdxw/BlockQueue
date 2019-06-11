package com.dxw.blockQueue.blockqueue.impl;

import com.dxw.blockQueue.blockqueue.BlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ：dxw
 * @date ：Created in 2019/6/11 16:33
 * @description：${description}
 * @modified By：
 * @version: $version$
 */


/**
 * 1. 你可以使用wait和notify函数来实现线程间通信。你可以用它们来实现多线程（>3）之间的通信。
 *
 * 2. 永远在synchronized的函数或对象里使用wait、notify和notifyAll，不然Java虚拟机会生成 IllegalMonitorStateException。
 *
 * 3. 永远在while循环里而不是if语句下使用wait。这样，循环会在线程睡眠前后都检查wait的条件，并在条件实际上并未改变的情况下处理唤醒通知。
 *
 * 4. 永远在多线程间共享的对象（在生产者消费者模型里即缓冲区队列）上使用wait。
 *
 * 5. 基于前文提及的理由，更倾向用 notifyAll()，而不是 notify()。
 */
public class PessimisticQueue<E> implements BlockingQueue<E> {

    private List<E> queue = new LinkedList<E>();
    private int  limit = 10;

    public PessimisticQueue(int limit){
        this.limit = limit;
    }

    public int getSize() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
    //入队操作
    //使用wait和notify函数的典型模板
    public synchronized void enqueue(E item) throws InterruptedException  {
        while(this.queue.size() == this.limit) {
            wait();
        }
        if(this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }

    //出队操作
    public synchronized E dequeue() throws InterruptedException{
        while(this.queue.size() == 0){
            wait();
        }
        if(this.queue.size() == this.limit){
            notifyAll();
        }

        return this.queue.remove(0);
    }

    public E getFront() {
        return queue.get(0);
    }
}
