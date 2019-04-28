package cn.enjoyedu.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *小明中了彩票享受生活去了，他遗留了一个类，是个计数器的功能，现在请你完成这个计数器。
 * 在这个类里，他已经完成了线程安全的get方法和compareAndSet()方法，请你用循环CAS机制完成它。
 * 这个类是cn.enjoyedu.ch3.answer.HalfAtomicInt
 * 完成后自行启动多个线程测试是否能正常工作。
 */
public class HalfAtomicInt {
    private AtomicInteger atomicI = new AtomicInteger(0);

    //时间在1秒内自旋出结果的限制
    private long whileTileEnd=1000;
    /*请完成这个递增方法*/
    public void increament() {
        long start =System.currentTimeMillis();
        int temp=atomicI.get();
        boolean flag = atomicI.compareAndSet(temp, temp + 1);
        long cuTime = System.currentTimeMillis()-start;
        whileTileEnd=whileTileEnd-cuTime;
        if (flag==false&&whileTileEnd>0){
            System.out.println("failed retry the over time is "+whileTileEnd);
            increament();
        }else{
            whileTileEnd=1000;
        }
    }
    
    public int getCount() {
    	return atomicI.get();
    }

    public boolean compareAndSet(int oldValue,int newValue){
        return atomicI.compareAndSet(oldValue,newValue);
    }

    public static void main(String[] args) {
        HalfAtomicInt anInt = new HalfAtomicInt();
        int i=5000;
        CountDownLatch latch=new CountDownLatch(i);
        while (i>0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    anInt.increament();
                    latch.countDown();
                }
            }).start();
            i--;
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ret"+anInt.getCount());
    }
}
