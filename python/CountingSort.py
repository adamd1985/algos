import math
import numpy as np

RUNS = 0


def cs(arr):
    global RUNS
    arr_size = len(arr)
    max_element = max(arr)

    # count of each element and cumulative
    count_arr = [0] * (max_element + 1)
    for idx in range(0, arr_size):
        RUNS += 1
        count_arr[arr[idx]] += 1

    count_arr_size = len(count_arr)
    for idx in range(1, count_arr_size):
        RUNS += 1
        count_arr[idx] += count_arr[idx - 1]

    # Create output array from finding position through count array
    output_arr = [0] * arr_size
    for idx in range(0, arr_size):
        RUNS += 1
        output_arr[count_arr[arr[idx]] - 1] = arr[idx]
        count_arr[arr[idx]] -= 1

    return output_arr


if __name__ == "__main__":
    arr_unsorted = np.random.randint(0, 99, 50)
    arr_unsorted_len = len(arr_unsorted)
    arr_sorted = cs(arr_unsorted)

    print(
        f'From unsorted:\n{arr_unsorted}\nto sorted:\n{arr_sorted}\n')
    print(
        f'In {RUNS} vs O(n+k): {arr_unsorted_len + max(arr_unsorted)}')
