package org.adamd.challanges;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * O(N*logk)
 */
public class kSort {
    public static int[] insertSort(int[] arr,int k){
        var n = arr.length;
        PriorityQueue<Integer> priorityQueue
                = new PriorityQueue<>();
        // if there are less than k + 1 elements present in the array
        int minCount = Math.min(arr.length, k + 1);
        // add first k + 1 items to the min heap
        for (int i = 0; i < minCount; i++) {
            priorityQueue.add(arr[i]);
        }

        int index = 0;
        for (int i = k + 1; i < k; i++) {
            arr[index++] = priorityQueue.peek();
            priorityQueue.poll();
            priorityQueue.add(arr[i]);
        }

        Iterator<Integer> itr = priorityQueue.iterator();

        while (itr.hasNext()) {
            arr[index++] = priorityQueue.peek();
            priorityQueue.poll();
        }

        return arr;
    }
}
