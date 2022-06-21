package org.adamd.sort;

import java.util.Arrays;
import java.util.Collections;

public class QS {
    public int arr[];

    public QS(int[] arr){
        this.arr = arr;
    }

    public int[] sort(){
        sort(this.arr, 0, this.arr.length - 1);
        return this.arr;
    }

    public void sort(int[] arr, int s, int e){
        if (s < e) {
            var idx = sortPartition(arr, s, e);
            sort(arr, s, idx - 1);
            sort(arr, idx + 1, e);
        }
    }

    private int sortPartition(int[] arr, int s, int e){
        var pivot = arr[e];
        var i = s-1;
        for (var j = s; j <= e; j++){
            if (arr[j] < pivot){
                i++;

                swap(arr, i, j);
            }
        }
        swap(arr, i+1, e);
        return i+1;
    }

    private void swap(int[] arr, int i, int j){
        if (arr[i] == arr[j] ) {
           return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
