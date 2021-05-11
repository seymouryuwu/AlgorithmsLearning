package binarySearch;

/*
https://leetcode.com/problems/divide-two-integers/
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        int halfIntMin = -1073741824;

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = - dividend;
        }

        if (divisor > 0) {
            negatives--;
            divisor = - divisor;
        }

        int quotient = 0;

        while (dividend <= divisor) {
            int loopSum = divisor;
            int loopQuotient = -1;

            while(loopSum >= halfIntMin && loopSum + loopSum > dividend) {
                loopSum += loopSum;
                loopQuotient += loopQuotient;
            }

            dividend = dividend - loopSum;
            quotient = quotient + loopQuotient;
        }

        if (negatives == 1) {
            return quotient;
        }

        return (- quotient);
    }
}
