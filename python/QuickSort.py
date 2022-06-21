import math
import numpy as np

RUNS = 0


def _swap(arr, s, e):
    if s != e:
        temp = arr[s]
        arr[s] = arr[e]
        arr[e] = temp

    return arr


def _partition(arr, s, e):
    global RUNS

    pivot = arr[e]
    i = s - 1
    j = s

    while j < e:
        if arr[j] <= pivot:
            i += 1
            arr = _swap(arr, i, j)
        j += 1
        RUNS += 1

    arr = _swap(arr, i + 1, j)

    return i + 1


def qs(arr, s, e):

    if s < e:

        pivot = _partition(arr, s, e)

        arr = qs(arr, s, pivot - 1)
        arr = qs(arr, pivot+1, e)

    return arr


if __name__ == "__main__":
    arr_unsorted = np.random.randint(1, 1000, 20)
    arr_unsorted_len = len(arr_unsorted)
    arr_sorted = qs(arr_unsorted.copy(), 0, arr_unsorted_len - 1)

    print(
        f'From unsorted:\n{arr_unsorted}\nto sorted:\n{arr_sorted}\n')
    print(
        f'In {RUNS} vs worse number of runs of: {math.ceil(arr_unsorted_len * math.log(arr_unsorted_len))} :: O(n Log n)')
