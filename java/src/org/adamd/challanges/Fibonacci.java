package org.adamd.challanges;

class Fibonacci {
    static private int ITERS = 0;

    public static void main(String[] args) {
        final var fib = new Fibonacci();

        fib.printFibonacci(7);
    }

    void printFibonacci(int value) {
        int[] memoized = new int[value];
        for (int i = 0; i < value; i++) {
            System.out.println(String.format("value is %d in %d iterations [O(n^2)]", fibonacciTabulated(i), Fibonacci.ITERS));
        }
    }

    public int fibonacci(int length, int[] memoized) {
        if (memoized != null && memoized[length] > 0) {
            System.out.println(String.format("CAHCED: %d", memoized[length]));
            return memoized[length];
        } else if (length > 1) {
            Fibonacci.ITERS += 1;
            var result = fibonacci(length - 2, memoized) + fibonacci(length - 1, memoized);
            if (memoized != null) {
                memoized[length] = result;
            }
            return result;
        }

        return length;
    }

    int fibonacciTabulated(int k) {
        if (k <= 1) {
            return k;
        }
        var first = 1;
        var second = 0;
        var result = 0;
        for (int i = 1; i < k; i++) {
            Fibonacci.ITERS += 1;
            result = first + second;
            second = first;
            first = result;
        }
        return result;
    }
}