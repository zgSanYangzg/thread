package cn.enjoyedu.thread;


import cn.enjoyedu.ch2.forkjoin.sort.InsertionSort;
import cn.enjoyedu.ch2.forkjoin.sort.MergeSort;
import cn.enjoyedu.ch2.forkjoin.sum.MakeArray;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 请用ForkJoin实现一个归并排序：
 * 归并排序的代码实现类为：cn.enjoyedu.ch2.forkjoin.sort.MergeSort
 * 请将该单线程实现改造为ForkJoin实现。并调整排序数组的大小，观察数组大小不同的情况下，单线程实现和ForkJoin实现在性能上的差距。
 */
public class ForkjoinSort extends RecursiveTask<int[]> {
    private static Integer threadHold=80;
    private int[] sortArr;

    public ForkjoinSort(int[] sortArr) {
        this.sortArr=sortArr;

    }


    @Override
    protected int[] compute() {
        if(this.sortArr.length<=threadHold){
            return InsertionSort.sort(this.sortArr);
        }else{
            Integer mid = this.sortArr.length/2;
            int[] left = Arrays.copyOfRange(this.sortArr, 0, mid);
            int[] right = Arrays.copyOfRange(this.sortArr, mid, this.sortArr.length);

            ForkjoinSort leftSort = new ForkjoinSort(left);
            ForkjoinSort rightSort = new ForkjoinSort(right);
            invokeAll(leftSort,rightSort);
           return MergeSort.merge(leftSort.join(),rightSort.join());
        }
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        long start =System.currentTimeMillis();
        ForkjoinSort forkjoinSort = new ForkjoinSort(MakeArray.makeArray());
        forkJoinPool.invoke(forkjoinSort);
        int[] join = forkjoinSort.join();
        System.out.println("数组大小:"+MakeArray.ARRAY_LENGTH/1000+"千");
        System.out.println("forkjoin 阈值 ["+threadHold+" ] 耗时:"+(System.currentTimeMillis()-start));

        System.out.println("============================================");
        start = System.currentTimeMillis();
        MergeSort.sort(MakeArray.makeArray());
        System.out.println("单线程 spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
