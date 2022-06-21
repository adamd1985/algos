package org.adamd.sort;

public class BubbleSort {
    public static int OPERATIONS = 0;

    private static int[] swap(int[] arr, int leftIdx, int rightIdx){
        var temp = arr[leftIdx];
        arr[leftIdx] = arr[rightIdx];
        arr[rightIdx] = temp;
        return arr;
    }

    public static int[] sort(int[] arr){
        OPERATIONS = 0;
        for (var outerIdx = 0; outerIdx < arr.length; ++outerIdx){
            var swapped = false;
            for (var innerIdx = 0; innerIdx < arr.length - outerIdx - 1; ++innerIdx){
                OPERATIONS++;

                if (arr[innerIdx] > arr[innerIdx + 1]){
                    swapped = true;
                    arr = swap(arr, innerIdx, innerIdx + 1);
                }
            }
            if (!swapped){
                return arr;
            }
        }
        return arr;
    }
}
