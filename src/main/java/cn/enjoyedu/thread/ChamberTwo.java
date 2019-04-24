package cn.enjoyedu.thread;

import java.util.LinkedList;

public class ChamberTwo {
    private LinkedList<Integer> capacity=new LinkedList<>();

    public void loadBullets(Integer bullet){
        if (capacity != null) {

            synchronized (capacity){
                capacity.addLast(bullet);
                System.out.println("load bullet"+Thread.currentThread().getId());
                capacity.notifyAll();
            }

        }
    }
    public Integer shootingBullets() throws InterruptedException {

        synchronized (capacity){

            while (capacity.isEmpty()){
                capacity.wait();
            }
            return capacity.removeFirst();
        }

    }
}
