package cn.enjoyedu.thread;

public class ChamberTwoWorker implements Runnable {
    public ChamberTwoWorker(int maxCapacity,ChamberTwo chamber) {
        this.maxCapacity = maxCapacity;
        this.chamberTwo=chamber;
    }


    private int maxCapacity;
    private ChamberTwo chamberTwo;
    @Override
    public void run() {

        while (maxCapacity>0){
            try {
                Integer bullets = chamberTwo.shootingBullets();
                if(bullets!=0){
                    chamberTwo.loadBullets(bullets);
                }


            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        int threadC=50;
        int bulletsCapacity=20;
        ChamberTwo chamberTwo=new ChamberTwo();

        for (int i = 0; i < threadC; i++) {
            Thread thread = new Thread(new ChamberTwoWorker(bulletsCapacity, chamberTwo));
            thread.start();
        }
    }
}
