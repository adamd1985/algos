package org.adamd.datastruct;

import java.util.Arrays;

public class HeapTree {
    public static void main(String[] args) {
        int[] ints = {123,343,656,234,3,3244};

        // Index of last non-leaf node
        int startIdx = (ints.length / 2) - 1;
        for (int i = startIdx; i >= 0; i--) {
            ints = HeapTree.heapifyDown(ints, i);
        }

        System.out.println(Arrays.toString(ints));
    }

    public static int[] heapifyUp(int[] ints, int startIndex){
        int parentIdx = (startIndex -1 )/ 2;
        if (parentIdx >= 0 && ints[parentIdx] < ints[startIndex]){

            // swap the two if heap property is violated
            int temp = ints[parentIdx];
            ints[parentIdx] = ints[startIndex];
            ints[startIndex] = temp;

            // call heapify-up on the parent
            return heapifyUp(ints, parentIdx);
        }
        return ints;
    }

    public static int[] heapifyDown(int[] ints, int startIndex){
        final int leftIndex = (startIndex * 2) + 1;
        final int rightIndex = (startIndex * 2) + 2;

        int largestIdx = startIndex;
        if (leftIndex < ints.length) {
            if (ints[largestIdx] < ints[leftIndex]){
                largestIdx = leftIndex;
            }
        }
        if (rightIndex < ints.length) {
            if (ints[largestIdx] < ints[rightIndex]){
                largestIdx = rightIndex;
            }
        }
        if (largestIdx != startIndex){
            int temp = ints[startIndex];
            ints[startIndex] = ints[largestIdx];
            ints[largestIdx] = temp;

            heapifyDown(ints, largestIdx);
        }

        return ints;
    }
}
