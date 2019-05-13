package cn.enjoyedu.ch6.schd;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 *类说明：演示ScheduledThreadPoolExecutor的用法
 */
public class ScheduledCase {
    public static void main(String[] args) {
    	
    	ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(1);
    	
        //延时Runnable任务（仅执行一次）
        //TODO

        //固定延时间隔执行的任务
        //TODO

    	
        //固定时间间隔执行的任务,从理论上说第二次任务在6000 ms后执行，第三次在
        //6000*2 ms后执行
        //TODO

//        // 固定时间间隔执行的任务,开始执行后就触发异常,next周期将不会运行
//        schedule.scheduleAtFixedRate(new ScheduleWorker(ScheduleWorker.HasException),
//                0, 3000, TimeUnit.MILLISECONDS);
//
//        // 固定时间间隔执行的任务,虽然抛出了异常,但被捕捉了,next周期继续运行
//        schedule.scheduleAtFixedRate(new ScheduleWorker(ScheduleWorker.ProcessException),
//                0, 3000, TimeUnit.MILLISECONDS);


    }
}
