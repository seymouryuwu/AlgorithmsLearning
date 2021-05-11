package binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UglyNumberIII {
    public int nthUglyNumber(int n, int a, int b, int c) {
        int[] factors = new int[]{a, b, c};
        Arrays.sort(factors);
        List<List<Integer>> lcm = new ArrayList<>();
        findLCM(factors, lcm);

        return findFromUglyNumber(n, factors, lcm, factors[0]);
    }

    // binary search

    private int findFromUglyNumber(int n, int[] factors, List<List<Integer>> lcm, long ugly) {
        long smallest = factors[0];
        long addMultipleOfSmallest = 1;

        long Kth = findKthByUglyNumber(factors, lcm, ugly);
        long uglyNumber = ugly;
        while (Kth < n) {
            uglyNumber = ugly + smallest * addMultipleOfSmallest;
            addMultipleOfSmallest = addMultipleOfSmallest * 2;

            Kth = 0;
            for (int factor : factors) {
                Kth += uglyNumber / factor;
            }

            if (Kth < n) {
                continue;
            }

            for (int i = 0; i < lcm.size(); i++) {
                if (i % 2 == 0) {
                    for (int leastCommonMultiple : lcm.get(i)) {
                        Kth -= (uglyNumber / leastCommonMultiple);
                    }
                } else {
                    for (int leastCommonMultiple : lcm.get(i)) {
                        Kth += (uglyNumber / leastCommonMultiple);
                    }
                }
            }
        }

        if (Kth == n) {
            return (int) uglyNumber;
        }

        addMultipleOfSmallest = addMultipleOfSmallest / 2;
        if (addMultipleOfSmallest == 1) {
            Kth = findKthByUglyNumber(factors, lcm, ugly);
            while (Kth != n) {
                ugly++;
                for (int i = 1; i < factors.length; i++) {
                    if (ugly % factors[i] == 0) {
                        Kth++;
                        break;
                    }
                }
            }

            return (int) ugly;
        } else {
            ugly = ugly + smallest * addMultipleOfSmallest / 2;
            return findFromUglyNumber(n, factors, lcm, ugly);
        }
    }

    private long findKthByUglyNumber(int[] factors, List<List<Integer>> lcm, long ugly) {
        long Kth = 0;
        for (int factor : factors) {
            Kth += ugly / factor;
        }

        for (int i = 0; i < lcm.size(); i++) {
            if (i % 2 == 0) {
                for (int leastCommonMultiple : lcm.get(i)) {
                    Kth -= (ugly / leastCommonMultiple);
                }
            } else {
                for (int leastCommonMultiple : lcm.get(i)) {
                    Kth += (ugly / leastCommonMultiple);
                }
            }
        }

        return Kth;
    }

    private void findLCM(int[] factors, List<List<Integer>> result) {
        for (int i = 1; i < factors.length; i++) {
            for (int j = result.size() - 1; j >= 0; j--) {
                List<Integer> previousLCM = result.get(j);

                if (j == result.size() - 1) {
                    result.add(new ArrayList<>());
                }

                List<Integer> nextLCM = result.get(j + 1);
                for (int prevLCM : previousLCM) {
                    nextLCM.add(lcm(prevLCM, factors[i]));
                }
            }

            if (result.size() == 0) {
                result.add(new ArrayList<>());
            }

            List<Integer> firstLCM = result.get(0);
            for (int j = i - 1; j >= 0; j--) {
                firstLCM.add(lcm(factors[j], factors[i]));
            }
        }
    }

    private int gcd(int num1, int num2) {
        if (num1 == 0) {
            return num2;
        }

        return gcd(num2 % num1, num1);
    }

    private int lcm(int num1, int num2) {
        int gcd = gcd(num1, num2);
        long longLCM = (long)num1 * (long)num2 / gcd;
        return (longLCM > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int)longLCM;
    }
}
