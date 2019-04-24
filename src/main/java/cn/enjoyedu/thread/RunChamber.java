package cn.enjoyedu.thread;

public class RunChamber {
    private static Chamber chamber=new Chamber(100);

    private static class CheckBullets extends Thread{
        @Override
        public void run() {
            while (chamber.getBloodStrips()>0){
                chamber.bulletsLoaded();
            }
        }
    }
    private static class CheckShooting extends Thread{
        @Override
        public void run() {
            while (chamber.getBloodStrips()>0){
                chamber.shootingBullets();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new CheckShooting().start();
        }
        for (int i = 0; i < 3; i++) {
            CheckBullets th1 = new CheckBullets();
            th1.setDaemon(true);
            th1.start();
        }
//        new CheckShooting().start();
//        new CheckBullets().start();
        chamber.isReay();
    }

}
