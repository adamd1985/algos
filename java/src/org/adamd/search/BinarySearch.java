package org.adamd.search;

public class BinarySearch {

    public static int recurs(int[] nums, int target, int start, int end) {
        if (start >= end){
            return -1;
        }

        final int mid = start+ (end - start) / 2;
        if (nums[mid] == target){
            return mid;
        }

        if (target < nums[mid]){
            return recurs(nums, target, start, mid - 1);
        }

        return recurs(nums, target, mid + 1, end);
    }

    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        return recurs(nums, target, start, end);
    }
}
