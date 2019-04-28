package cn.enjoyedu.thread;

import java.util.LinkedList;

/**
 * 《wait/notify实现生产者和消费者程序》
 * 采用多线程技术，例如wait/notify，设计实现一个符合生产者和消费者问题的程序，对某一个对象（枪膛）进行操作，其最大容量是20颗子弹，生产者线程是一个压入线程，它不断向枪膛中压入子弹，消费者线程是一个射出线程，它不断从枪膛中射出子弹。
 */
public class ChamberTwo {
    private LinkedList<Integer> capacity=new LinkedList<>();

    public void loadBullets(Integer bullet) throws InterruptedException {
        synchronized (this){
            while (capacity.size()>=20){
                wait();
            }
            capacity.addLast(bullet);
            System.out.println("load bullet"+Thread.currentThread().getId());
            notifyAll();
        }

    }
    public Integer shootingBullets() throws InterruptedException {

        synchronized (this){
            while (capacity.size()<=0){
                wait();
            }
            Integer ret = capacity.removeFirst();
            System.out.println("shot bullet "+Thread.currentThread().getId()+" before "+capacity.size());
            notify();
            return ret;

        }

    }

    static class Consumer implements Runnable{
        ChamberTwo chamber;
        Consumer(ChamberTwo chamber){
            this.chamber=chamber;
        }

        @Override
        public void run() {
            try {
                while (true){
                    chamber.shootingBullets();
                    Thread.sleep(12);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Product implements Runnable{
        ChamberTwo chamber;
        public Product(ChamberTwo chamber){
            this.chamber=chamber;
        }
        @Override
        public void run() {
            try {
                while (true){
                    chamber.loadBullets(1);
                    Thread.sleep(6);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChamberTwo chamber = new ChamberTwo();
        for (int i=0;i<5;i++){
            Consumer consumer = new Consumer(chamber);
            new Thread(consumer).start();
        }
        for (int i=0;i<5;i++){
            Product consumer = new Product(chamber);
            new Thread(consumer).start();
        }
    }
}
