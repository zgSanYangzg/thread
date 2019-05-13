package cn.enjoyedu.thread;

import cn.enjoyedu.ch5.answer.IBoundedBuffer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MySemphoreBlockQueue<E> implements IBoundedBuffer<E> {
    private List<E> queueList=new LinkedList();
    private int limit;
    private Semaphore useFul;
    private Semaphore useLes;

    public MySemphoreBlockQueue(int queueSize){
        this.limit=queueSize;
        this.useFul=new Semaphore(0);
        this.useLes=new Semaphore(limit);

    }
    @Override
    public void put(E x) throws InterruptedException {

        useLes.acquire();
        synchronized (queueList){
            queueList.add(x);
        }
        useFul.release();
    }

    @Override
    public E take() throws InterruptedException {
        E e;
        useFul.acquire();
        synchronized (queueList){
            e=queueList.remove(0);
        }
        useLes.release();
        return e;
    }
}
