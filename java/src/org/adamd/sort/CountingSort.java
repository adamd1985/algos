package org.adamd.sort;

import java.util.Arrays;

public class CountingSort {
    public static int OPERATIONS = 0;

    public static int[] sort(int[] arr){
        OPERATIONS = 0;

        var maxElement = Arrays.stream(arr).max().getAsInt();
        int elementArray[] = new int[maxElement + 1]; // Default of 0

        Arrays.stream(arr).forEach(x -> {
            elementArray[x] += 1;
            OPERATIONS++;
        });
        for (var idx = 1; idx < elementArray.length; ++idx){
            elementArray[idx] += elementArray[idx - 1];
            OPERATIONS++;
        }

        int outputArray[] = new int[arr.length];
        Arrays.stream(arr).forEach(x -> {
            outputArray[elementArray[x] - 1] = x;
            --elementArray[x];

            assert elementArray[x] >= 0;

            OPERATIONS++;
        });

        return outputArray;
    }
}
