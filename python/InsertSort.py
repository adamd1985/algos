import math
import numpy as np

RUNS = 0


def _swap(arr, s, e):
    if s != e:
        temp = arr[s]
        arr[s] = arr[e]
        arr[e] = temp

    return arr


def isort(arr, size):
    global RUNS
    for idx in range(1, size):
        inner_idx = idx
        while (inner_idx > 0 and arr[inner_idx-1] > arr[inner_idx]):
            RUNS += 1
            _swap(arr, inner_idx - 1, inner_idx)
            inner_idx -= 1

    return arr


if __name__ == "__main__":
    arr_unsorted = np.random.randint(1, 1000, 100)
    arr_unsorted_len = len(arr_unsorted)
    arr_sorted = isort(arr_unsorted.copy(), arr_unsorted_len)

    print(
        f'From unsorted:\n{arr_unsorted}\nto sorted:\n{arr_sorted}\n')
    print(
        f'In {RUNS} vs worse: {arr_unsorted_len} to {math.ceil(math.pow(arr_unsorted_len, 2))} :: O(n) to O(n^2)')
