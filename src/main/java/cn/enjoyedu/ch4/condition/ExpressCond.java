package cn.enjoyedu.ch4.condition;

/**
 *类说明：
 */
public class ExpressCond {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/
    //TODO

    public ExpressCond() {
    }

    public ExpressCond(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public void changeKm(){
    	//TODO
        
        
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public  void changeSite(){
//    	siteLock.lock();
//    	try {
//    		this.site = "BeiJing";
//    		siteCond.signal();//通知其他在锁上等待的线程
//    	}finally {
//    		siteLock.unlock();
//    	}
    }

    /*当快递的里程数大于100时更新数据库*/
    public void waitKm(){
    	//TODO

        System.out.println("the Km is "+this.km+",I will change db");
    }

    /*当快递到达目的地时通知用户*/
    public void waitSite(){
//    	siteLock.lock();
//    	try {
//        	while(this.site.equals(CITY)) {
//        		try {
//    				siteCond.await();//当前线程进行等待
//    				System.out.println("check Site thread["+Thread.currentThread().getName()
//    						+"] is be notify");
//    			} catch (InterruptedException e) {
//    				// TODO Auto-generated catch block
//    				e.printStackTrace();
//    			}
//        	}
//    	}finally {
//    		siteLock.unlock();
//    	}

        System.out.println("the site is "+this.site+",I will call user");
    }
}
