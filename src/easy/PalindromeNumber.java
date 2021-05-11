package easy;

/*
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Follow up: Could you solve it without converting the integer to a string?

Example 1:

Input: x = 121
Output: true

Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Example 4:

Input: x = -101
Output: false


Constraints:

-2^31 <= x <= 2^31 - 1
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x<0) x = -x;
        String xString = Integer.toString(x);
        for (int i = 0; i < xString.length()/2; i++) {
            if (!(xString.charAt(i) == xString.charAt(xString.length()-1-i))) return false;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x <0) return false;
        int xReverse = 0;
        int xTemporary = x;
        while(xTemporary != 0) {
            int digital = xTemporary%10;
            xTemporary /= 10;
            xReverse = xReverse*10+digital;
        }
        return xReverse == x;
    }
}

