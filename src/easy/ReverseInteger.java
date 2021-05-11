package easy;

import java.util.ArrayList;
import java.util.Collections;

/*
Given a 32-bit signed integer, reverse digits of an integer.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer
range: [−2^31,  2^31 − 1]. For this problem, assume that your function returns 0 when the reversed integer overflows.

Example 1:

Input: x = 123
Output: 321

Example 2:

Input: x = -123
Output: -321

Example 3:

Input: x = 120
Output: 21

Example 4:

Input: x = 0
Output: 0


Constraints:

-2^31 <= x <= 2^31 - 1
 */
public class ReverseInteger {
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) return 0;
        ArrayList<Integer> weights = new ArrayList<>();
        for (int i = 0; x%Math.pow(10, i) != x; i++) {
            int weight = (int) (x%Math.pow(10, i+1));
            for (int j = 0; j < weights.size(); j++) {
                weight -= weights.get(j)*Math.pow(10,j);
            }
            weights.add((int) (weight/Math.pow(10, i)));
        }

        Collections.reverse(weights);
        long reverseValue = 0;
        for (int i = 0; i < weights.size(); i++) {
            reverseValue += weights.get(i)*Math.pow(10,i);
            if (reverseValue > Integer.MAX_VALUE || reverseValue < Integer.MIN_VALUE) return 0;
        }
        return (int) reverseValue;
    }

    public int reverse2(int x) {
        long xLong = x;
        if (x < 0) xLong = -xLong;
        String xString = Long.toString(xLong);
        int xLength = xString.length();
        StringBuilder reverseString = new StringBuilder();
        for (int i = 0; i < xLength; i++) {
            reverseString.append(xString.charAt(xLength - 1 - i));
        }
        long reverseLong = Long.parseLong(reverseString.toString());
        if (x < 0) reverseLong = -reverseLong;
        if (reverseLong > Integer.MAX_VALUE || reverseLong < Integer.MIN_VALUE) return 0;
        else return (int) reverseLong;
    }

    public int reverse3(int x) {
        long reverseLong = 0;
        while (x != 0) {
            int weight = x%10;
            x /= 10;
            reverseLong = reverseLong*10+weight;
        }
        if (reverseLong > Integer.MAX_VALUE || reverseLong < Integer.MIN_VALUE) return 0;
        return (int) reverseLong;
    }
}
