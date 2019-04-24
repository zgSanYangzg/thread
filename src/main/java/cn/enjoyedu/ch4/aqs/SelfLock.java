package cn.enjoyedu.ch4.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com 
 *
 *类说明：
 */
public class SelfLock implements Lock {
    // 静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        //private static final long serialVersionUID = -4387327721959839431L;

        //TODO

        // 返回一个Condition，每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    // 仅需要将操作代理到Sync上即可
    private final Sync sync = new Sync();

    public void lock() {
    	System.out.println(Thread.currentThread().getName()+" ready get lock");
        sync.acquire(1);
        System.out.println(Thread.currentThread().getName()+" already got lock");
    }

    public boolean tryLock() {
        return false;//TODO
    }

    public void unlock() {
    	System.out.println(Thread.currentThread().getName()+" ready release lock");
        sync.release(1);
        System.out.println(Thread.currentThread().getName()+" already released lock");
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return false;//TODO
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}
