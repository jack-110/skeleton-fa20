package bearmaps;

import java.util.ArrayList;

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T> {

    MinHeap minHeap;

    public ArrayHeapMinPQ(T[] array){
        this.minHeap = new MinHeap();
    }

    @Override
    public void add(T item, double priority) {

    }

    @Override
    public boolean contains(T item) {
        return false;
    }

    @Override
    public T getSmallest() {
        return null;
    }

    @Override
    public T removeSmallest() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void changePriority(T item, double priority) {

    }

    /* create a heap. */
    private static class MinHeap<T extends Comparable<T>>{

        /* Position k:father floor-round(k/2) and it's children 2k and 2k+1. */
        private ArrayList<T> arrayList;
        /* k = size - 1. */
        private int size = 1;
        private T inititem;

        public MinHeap( ){
            arrayList = new ArrayList<>();
            arrayList.add(inititem);
        }

        /* add an item into heaps. */
        public void add(T item){
            arrayList.add(item);
            size++;
            sink(item,size-1);
        }

        /* delete the smallest item from heaps. */
        public void del(){}

        /* sink the item into right place. */
        private void sink(T item, int k){
            while (k >= 2){
                int fatherposition = (int) Math.floor(k/2);
                T father = arrayList.get(fatherposition);
                if(item.compareTo(father) < 0){
                    swap(k,fatherposition);
                    k = fatherposition;
                    sink(item, k);
                }
                break;
            }
        }
        /* swap the items. */
        private void swap(int position1, int position2){
            T tmp = arrayList.get(position1);
            arrayList.set(position1,arrayList.get(position2));
            arrayList.set(position2,tmp);
        }
    }

    public static void main(String[] args){
        MinHeap<Integer> minHeap1 = new MinHeap<Integer>();
        minHeap1.add(32);
        minHeap1.add(15);
        minHeap1.add(2);
        minHeap1.add(17);
        minHeap1.add(19);
        minHeap1.add(26);
        minHeap1.add(41);
        minHeap1.add(17);
        minHeap1.add(17);
    }
}
