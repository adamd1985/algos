import math
import numpy as np

RUNS = 0


def _swap(arr, left_idx, right_idx):
    temp = arr[left_idx]
    arr[left_idx] = arr[right_idx]
    arr[right_idx] = temp

    return arr


def _heapify(arr, size, cur_index):
    global RUNS

    if cur_index >= size:
        return arr

    RUNS += 1
    smallest_idx = cur_index
    left_idx = (cur_index * 2) + 1
    right_idx = (cur_index * 2) + 2

    if (left_idx < size) and (arr[smallest_idx] > arr[left_idx]):
        smallest_idx = left_idx
    if (right_idx < size) and (arr[smallest_idx] > arr[right_idx]):
        smallest_idx = right_idx

    if smallest_idx != cur_index:
        _swap(arr, cur_index, smallest_idx)
        _heapify(arr, size, smallest_idx)

    return arr


def min_hs(arr):
    global RUNS

    for cur_index in range(len(arr) // 2 - 1, -1, -1):
        _heapify(arr, len(arr), cur_index)

    return arr


if __name__ == "__main__":
    arr = [6, 5, 3, 2, 8, 10, 9]
    print(f'From unsorted:\n{arr}\n')
    arr = min_hs(arr)
    print(f'to sorted:\n{arr}\n')
    print(
        f'In {RUNS} vs O(n) to O(nlogN): {len(arr)} to {len(arr) * math.log(len(arr))}')
