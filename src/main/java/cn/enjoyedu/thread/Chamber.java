package cn.enjoyedu.thread;

/**
 * 《wait/notify实现生产者和消费者程序》
 * 采用多线程技术，例如wait/notify，设计实现一个符合生产者和消费者问题的程序，对某一个对象（枪膛）进行操作，其最大容量是20颗子弹，生产者线程是一个压入线程，它不断向枪膛中压入子弹，消费者线程是一个射出线程，它不断从枪膛中射出子弹。
 */
public class Chamber {
    private static int maxCapacity=20;

    private int currentCapacity=0;

    private int bloodStrips=100;

    public int getBloodStrips() {
        return bloodStrips;
    }

    public synchronized void bulletsLoaded(){
        if (this.bloodStrips>0){
            while (this.currentCapacity==this.maxCapacity){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.currentCapacity++;
            System.out.println("thread id ["+Thread.currentThread().getId()+"] 子弹上膛,当前子弹数:"+this.currentCapacity);

            isReay();
        }


    }

    public synchronized void shootingBullets(){
        if (this.bloodStrips>0){
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
            this.currentCapacity--;
            this.bloodStrips--;
            System.out.println("thread id ["+Thread.currentThread().getId()+"] ==射击子弹后,当前子弹数:"+this.currentCapacity);
            System.out.println("当前血量:"+this.bloodStrips);

            isReay();
        }


    }
    public synchronized void isReay(){
        notifyAll();
    }
}
