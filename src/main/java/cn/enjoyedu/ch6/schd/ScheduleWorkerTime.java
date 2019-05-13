package cn.enjoyedu.ch6.schd;

import cn.enjoyedu.tools.SleepTools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *类说明：定时任务的工作类
 */
public class ScheduleWorkerTime implements Runnable{
    public final static int Long_8 = 8;//普通任务类型
    public final static int Short_2 = 2;//会抛出异常的任务类型
    public final static int Normal_5 = 5;//抛出异常但会捕捉的任务类型

    public static SimpleDateFormat formater = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static AtomicInteger count = new AtomicInteger(0);
    
    private int taskType;
    public ScheduleWorkerTime(int taskType) {
        this.taskType = taskType;
    }

    @Override
    public void run() {
    	if(count.get()==0) {
            System.out.println("Long_8....begin:"+formater.format(new Date()));
            SleepTools.second(Long_8);
            System.out.println("Long_8....end:"+formater.format(new Date())); 
            count.incrementAndGet();
    	}else if(count.get()==1) {
    		System.out.println("Short_2 ...begin:"+formater.format(new Date()));
    		SleepTools.second(Short_2);
    		System.out.println("Short_2 ...end:"+formater.format(new Date()));
            count.incrementAndGet();    		
    	}else {
    		System.out.println("Normal_5...begin:"+formater.format(new Date()));
    		SleepTools.second(Normal_5);
    		System.out.println("Normal_5...end:"+formater.format(new Date()));
    		count.incrementAndGet(); 
    	}
//    	if(taskType==Long_8) {
//            System.out.println("Long_8....begin:"+formater.format(new Date()));
//            SleepTools.second(Long_8);
//            System.out.println("Long_8....end:"+formater.format(new Date()));
//    	}else if(taskType==Short_2) {
//    		System.out.println("Short_2 ...begin:"+formater.format(new Date()));
//    		SleepTools.second(Short_2);
//    		System.out.println("Short_2 ...end:"+formater.format(new Date()));
//    	}else {
//    		System.out.println("Normal_5...begin:"+formater.format(new Date()));
//    		SleepTools.second(Normal_5);
//    		System.out.println("Normal_5...end:"+formater.format(new Date()));
//    	}
    }
}
