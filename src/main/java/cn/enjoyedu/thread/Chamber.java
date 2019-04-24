package cn.enjoyedu.thread;

public class Chamber {
    public Chamber(int masterBlood){
        this.bloodStrips=masterBlood;
    }
    private static int maxCapacity=20;

    private int currentCapacity=0;

    private int bloodStrips;

    public int getBloodStrips() {
        return bloodStrips;
    }

    public synchronized void bulletsLoaded(){

        while (this.currentCapacity==this.maxCapacity){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        while (this.bloodStrips>0&&currentCapacity<maxCapacity){

            this.currentCapacity=this.currentCapacity+1;
            System.out.println("thread id ["+Thread.currentThread().getId()+"] 子弹上膛,当前子弹数:"+this.currentCapacity);
        }
        isReay();

    }

    public synchronized void shootingBullets(){
        while (this.currentCapacity==0){
            try {
                wait();
                System.out.println("当前血量:"+this.bloodStrips);
                System.out.println("shooting thread["+Thread.currentThread().getId() +"] is notified");
                System.out.println("当前子弹剩余数:"+this.currentCapacity);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (this.bloodStrips>0&&this.currentCapacity>0){
            this.currentCapacity=this.currentCapacity-1;
            System.out.println("thread id ["+Thread.currentThread().getId()+"] ==射击子弹后,当前子弹数:"+this.currentCapacity);
            this.bloodStrips--;
            System.out.println("当前血量:"+this.bloodStrips);

        }
        isReay();


    }
    public synchronized void isReay(){
        notifyAll();
    }
}
