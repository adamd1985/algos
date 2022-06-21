package org.adamd;

public class MS {
    public int[] arr;

    public MS(int[] arr ){
        this.arr = arr;
    }

    private void merge(int arr[], int left, int mid, int right) {

        int len1 = mid - left + 1;
        int len2 = right - mid;

        int leftArr[] = new int[len1];
        int rightArr[] = new int[len2];

        for (int i = 0; i < len1; i++)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < len2; j++)
            rightArr[j] = arr[mid + 1 + j];

        int i, j, k;
        i = 0;
        j = 0;
        k = left;

        while (i < len1 && j < len2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < len2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public void sort(int[] a, int s, int e){
        if (s < e){
            int m =  (s + e)/2;
            sort(a, s, m);
            sort(a, m+1, e);
            merge(a, s, m, e);
        }
    }

    public int[] sort(){
        sort(this.arr, 0, this.arr.length - 1);

        return this.arr;
    }
}
