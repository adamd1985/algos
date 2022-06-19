import math
import numpy as np

RUNS = 0


def bs(arr):
    def _swap(arr, left_idx, right_idx):
        temp = arr[left_idx]
        arr[left_idx] = arr[right_idx]
        arr[right_idx] = temp

        return arr

    global RUNS

    arr_size = len(arr)
    for outer_idx in range(arr_size - 1):
        RUNS += 1
        swapped = False
        for inner_idx in range(0, arr_size - outer_idx - 1):
            RUNS += 1
            if arr[inner_idx] > arr[inner_idx + 1]:
                arr = _swap(arr, inner_idx, inner_idx + 1)
                swapped = True

        if swapped is not True:
            return arr

    return arr


if __name__ == "__main__":
    arr_unsorted = np.random.randint(0, 9999, 100)
    arr_unsorted_len = len(arr_unsorted)
    arr_sorted = bs(arr_unsorted.copy())

    print(
        f'From unsorted:\n{arr_unsorted}\nto sorted:\n{arr_sorted}\n')
    print(
        f'In {RUNS} vs O(n) to O(n^2): {arr_unsorted_len} to {math.pow(arr_unsorted_len,2)}')
