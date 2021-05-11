package heap;

import java.util.*;

public class UglyNumberII {
    // Approach 1: dynamic programming
    public int nthUglyNumber(int n) {
        List<Integer> uglyNumbers = new ArrayList<>();
        uglyNumbers.add(1);

        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        for (int i = 1; i < n; i++) {
            int last = uglyNumbers.get(i - 1);

            while (uglyNumbers.get(p2) * 2 <= last) {
                p2++;
            }

            while (uglyNumbers.get(p3) * 3 <= last) {
                p3++;
            }

            while (uglyNumbers.get(p5) * 5 <= last) {
                p5++;
            }

            int newNumber = Math.min(uglyNumbers.get(p2) * 2,
                    Math.min(uglyNumbers.get(p3) * 3,
                            uglyNumbers.get(p5) * 5));

            uglyNumbers.add(newNumber);
        }

        return uglyNumbers.get(n - 1);
    }

    // Approach 2: heap
    public int nthUglyNumber2(int n) {
        long[] primes = new long[3];
        primes[0] = 2;
        primes[1] = 3;
        primes[2] = 5;

        Queue<Long> candidates = new PriorityQueue<>();
        Set<Long> hash = new HashSet<>();

        Long number = 1L;
        for (long prime : primes) {
            candidates.offer(prime);
            hash.add(prime);
        }

        for (int i = 1; i < n; i++) {
            number = candidates.poll();
            for (long prime : primes) {
                long newCandidate = prime * number;
                if (!hash.contains(newCandidate)) {
                    candidates.offer(newCandidate);
                    hash.add(newCandidate);
                }
            }
        }

        return number.intValue();
    }
}
