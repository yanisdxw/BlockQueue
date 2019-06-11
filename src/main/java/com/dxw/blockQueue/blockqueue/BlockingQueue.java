package com.dxw.blockQueue.blockqueue;

/**
 * @author ：dxw
 * @date ：Created in 2019/6/11 16:21
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface BlockingQueue<E> {
    void enqueue(E e) throws InterruptedException;
    E dequeue() throws InterruptedException;
    E getFront();
    int getSize();
    boolean isEmpty();
}
