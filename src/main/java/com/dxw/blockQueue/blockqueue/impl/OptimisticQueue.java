package com.dxw.blockQueue.blockqueue.impl;

import com.dxw.blockQueue.blockqueue.BlockingQueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：dxw
 * @date ：Created in 2019/6/11 18:09
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class OptimisticQueue<E> implements BlockingQueue<E> {

    private LinkedList<E> queue = new LinkedList<E>();
    private int limit;
    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    public OptimisticQueue(int limit) {
        this.limit = limit;
    }

    public void enqueue(E e) throws InterruptedException {
        try {
            lock.lock();
            while(limit == queue.size()) {
                System.out.println("存储量达到上限，请等待");
                full.await();
            }
            this.queue.add(e);
            empty.signal();
        }catch (InterruptedException error) {
            error.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public E dequeue() throws InterruptedException {
        E ret = null;
        try {
            lock.lock();
            while(0 == queue.size()) {
                System.out.println("队列为空，请等待");
                empty.await();
            }
            ret = this.queue.remove(0);
            full.signal();
        }catch (InterruptedException error) {
            error.printStackTrace();
        }finally{
            lock.unlock();
            return ret;
        }
    }

    public E getFront() {
        return queue.get(0);
    }

    public int getSize() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
