import math
import numpy as np

RUNS = 0


def _merge(arr, start_idx, mid_idx, end_idx):
    global RUNS

    # Preparation
    size_left = mid_idx - start_idx + 1
    size_right = end_idx - mid_idx
    left_arr = [0] * size_left
    right_ar = [0] * size_right
    for left_idx in range(0, size_left):
        left_arr[left_idx] = arr[start_idx + left_idx]
    for right_idx in range(0, size_right):
        right_ar[right_idx] = arr[1 + mid_idx + right_idx]

    # Sort and merge.
    left_idx = right_idx = 0
    arr_idx = start_idx
    while left_idx < size_left and right_idx < size_right:
        RUNS += 1
        if left_arr[left_idx] <= right_ar[right_idx]:
            arr[arr_idx] = left_arr[left_idx]
            left_idx += 1
        else:
            arr[arr_idx] = right_ar[right_idx]
            right_idx += 1
        arr_idx += 1
    while left_idx < size_left:
        RUNS += 1
        arr[arr_idx] = left_arr[left_idx]
        left_idx += 1
        arr_idx += 1
    while right_idx < size_right:
        RUNS += 1
        arr[arr_idx] = right_ar[right_idx]
        right_idx += 1
        arr_idx += 1

    return arr


def ms(arr, start, end):
    if start < end:
        mid = (start + end) // 2

        arr = ms(arr, start, mid)
        arr = ms(arr, mid + 1, end)

        arr = _merge(arr, start, mid, end)
    return arr


if __name__ == "__main__":
    arr_unsorted = np.random.randint(1, 1000, 100)
    arr_unsorted_len = len(arr_unsorted)
    arr_sorted = ms(arr_unsorted.copy(), 0, arr_unsorted_len - 1)

    print(
        f'From unsorted:\n{arr_unsorted}\nto sorted:\n{arr_sorted}\n')
    print(
        f'In {RUNS} vs worse number of runs of: {math.ceil(arr_unsorted_len * math.log(arr_unsorted_len))} :: O(n Log n)')
