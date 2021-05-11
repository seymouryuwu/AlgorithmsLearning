package twoPointers;

/*
https://leetcode.com/problems/valid-palindrome-ii/
 */
public class ValidPalindromeII {
    boolean isRemoved = false;

    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (isRemoved) {
                    return false;
                } else {
                    isRemoved = true;

                    return (validPalindrome(s.substring(left + 1, right + 1)) ||
                            validPalindrome(s.substring(left, right)));
                }
            }
        }

        return true;
    }
}
