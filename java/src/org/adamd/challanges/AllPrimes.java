package org.adamd.challanges;

import java.util.Arrays;
import java.util.stream.IntStream;

public class AllPrimes {
    public static int[] primesUpTo(int limit) {
        Boolean[] arePrimes = new Boolean[limit + 1];
        Arrays.fill(arePrimes, true);

        for (var i = 2; i <= limit; ++i) {
            if (arePrimes[i]) {
                for (var j = i * i; j < limit; j += i) {
                    arePrimes[j] = false;
                }
            }
        }

        var primes = IntStream.range(0, limit).filter(x -> arePrimes[x]);
        return primes.toArray();
    }
}
