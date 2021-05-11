package easy;

import java.util.Arrays;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        boolean isMax = true;
        for (int digit : digits) {
            if (digit != 9) {
                isMax = false;
                break;
            }
        }
        if (isMax) {
            int[] output = new int[digits.length+1];
            Arrays.fill(output, 0);
            output[0] = 1;
            return output;
        }

        digits[digits.length-1] += 1;
        for (int i = digits.length-1; i >= 0; i--) {
            if (digits[i] != 10) break;
            else {
                digits[i] = 0;
                digits[i-1] += 1;
            }
        }
        return digits;
    }

    public int[] plusOne2(int[] digits) {
        int n = digits.length;

        for (int idx = n - 1; idx >= 0; --idx) {
            if (digits[idx] == 9) {
                digits[idx] = 0;
            }
            else {
                digits[idx]++;
                return digits;
            }
        }
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }
}
