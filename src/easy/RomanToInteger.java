package easy;

import java.util.HashMap;
import java.util.Map;

/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply
X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same
principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.

Example 1:

Input: s = "III"
Output: 3

Example 2:

Input: s = "IV"
Output: 4

Example 3:

Input: s = "IX"
Output: 9

Example 4:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

Example 5:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


Constraints:

1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
public class RomanToInteger {
    public int romanToInt(String s) {
        int output = 0;

        while (s.length() > 0) {
            if (s.charAt(0) == 'I') {
                if (s.length() > 1 && s.charAt(1) == 'V') {
                    output += 4;
                    s = s.substring(2);
                } else if (s.length() > 1 && s.charAt(1) == 'X') {
                    output += 9;
                    s = s.substring(2);
                } else {
                    output += 1;
                    s = s.substring(1);
                }
            } else if (s.charAt(0) == 'V') {
                output += 5;
                s = s.substring(1);
            } else if (s.charAt(0) == 'X') {
                if (s.length() > 1 && s.charAt(1) == 'L') {
                    output += 40;
                    s = s.substring(2);
                } else if (s.length() > 1 && s.charAt(1) == 'C') {
                    output += 90;
                    s = s.substring(2);
                } else {
                    output += 10;
                    s = s.substring(1);
                }
            } else if (s.charAt(0) == 'L') {
                output += 50;
                s = s.substring(1);
            } else if (s.charAt(0) == 'C') {
                if (s.length() > 1 && s.charAt(1) == 'D') {
                    output += 400;
                    s = s.substring(2);
                } else if (s.length() > 1 && s.charAt(1) == 'M') {
                    output += 900;
                    s = s.substring(2);
                } else {
                    output += 100;
                    s = s.substring(1);
                }
            } else if (s.charAt(0) == 'D') {
                output += 500;
                s = s.substring(1);
            } else if (s.charAt(0) == 'M') {
                output += 1000;
                s = s.substring(1);
            } else return 0;
        }
        return output;
    }

    public int romanToInt2(String s) {
        int output = 0;

        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        while (s.length() > 0) {

            if (s.length() > 1 && romanValues.get(s.charAt(0)) < romanValues.get(s.charAt(1))) {
                output += (romanValues.get(s.charAt(1)) - romanValues.get(s.charAt(0)));
                s = s.substring(2);
            } else {
                output += romanValues.get(s.charAt(0));
                s = s.substring(1);
            }
        }
        return output;
    }

    public int romanToInt3(String s) {
        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        int charIndex = s.length()-1;
        int output = romanValues.get(s.charAt(charIndex));

        while (charIndex > 0) {
            int indexValue = romanValues.get(s.charAt(charIndex));
            int previousIndexValue = romanValues.get(s.charAt(charIndex-1));
            if (previousIndexValue < indexValue) {
                output -= previousIndexValue;
            } else {
                output += previousIndexValue;
            }
            charIndex--;
        }
        return output;
    }
}
