package cn.enjoyedu.ch3.answer;

import cn.enjoyedu.tools.SleepTools;


import java.util.concurrent.atomic.AtomicInteger;

/**
 *类说明：有一个残缺A类实现了线程安全的：
 *get方法和compareAndSet()方法
 *自行实现它的递增方法
 */
public class HalfAtomicInt {
    private AtomicInteger atomicI = new AtomicInteger(0);

    //时间在1秒内自旋出结果的限制
    private long whileTileEnd=1000;
    /*请完成这个递增方法*/
    public void increament() {
        long start =System.currentTimeMillis();
        boolean flag = atomicI.compareAndSet(atomicI.get(), atomicI.get() + 1);
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
        while (i>0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    anInt.increament();

                }
            }).start();
            i--;
        }

        SleepTools.ms(500);
        System.out.println("ret"+anInt.getCount());
    }
}
