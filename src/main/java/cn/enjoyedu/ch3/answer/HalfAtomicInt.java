package cn.enjoyedu.ch3.answer;

import cn.enjoyedu.tools.SleepTools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *类说明：有一个残缺A类实现了线程安全的：
 *get方法和compareAndSet()方法
 *自行实现它的递增方法
 */
public class HalfAtomicInt {
    private AtomicInteger atomicI = new AtomicInteger(0);

    /*请完成这个递增方法*/
    public void increament() {
        atomicI.compareAndSet(atomicI.get(),atomicI.get()+1);
        System.out.println(atomicI.get());
    }
    
    public int getCount() {
    	return atomicI.get();
    }

    public boolean compareAndSet(int oldValue,int newValue){
        return atomicI.compareAndSet(oldValue,newValue);
    }

    public static void main(String[] args) {
        HalfAtomicInt anInt = new HalfAtomicInt();
        int i=500;
        List<Thread> lst =new ArrayList<>();
        while (i>=0){
            Thread th1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    anInt.increament();
                }
            });
            lst.add(th1);
            i--;
        }

        for (Thread td : lst){
            td.start();
            System.out.println(anInt.getCount());
        }
        SleepTools.ms(100);
        System.out.println("ret"+anInt.getCount());

    }
}
