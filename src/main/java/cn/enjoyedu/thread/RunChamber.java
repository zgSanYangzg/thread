package cn.enjoyedu.thread;

import cn.enjoyedu.tools.SleepTools;

public class RunChamber {

    private static class CheckBullets extends Thread{
        private static Chamber chamber;
        public CheckBullets(Chamber chamber){
            this.chamber=chamber;
        }
        @Override
        public void run() {
            while (true){
                chamber.bulletsLoaded();
                SleepTools.ms(12);
            }
        }
    }
    private static class CheckShooting extends Thread{
        private static Chamber chamber;
        public CheckShooting(Chamber chamber){

            this.chamber=chamber;
        }
        @Override
        public void run() {
            while (true){

                chamber.shootingBullets();
                SleepTools.ms(12);
            }
        }
    }

    public static void main(String[] args) {
        Chamber chamber=new Chamber();
        for (int i = 0; i < 3; i++) {
            CheckShooting checkShooting = new CheckShooting(chamber);
            new Thread(checkShooting).start();
        }
        for (int i = 0; i < 3; i++) {
            CheckBullets th1 = new CheckBullets(chamber);
            new Thread(th1).start();
        }

    }

}
