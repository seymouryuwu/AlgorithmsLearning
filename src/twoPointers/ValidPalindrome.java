package twoPointers;

/*
https://leetcode.com/problems/valid-palindrome/
 */
import java.util.ArrayList;
import java.util.List;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        List<String> trimmed = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') ||
                    (c >= 'A' && c <= 'Z') ||
                    (c >= '0' && c <= '9')) {
                trimmed.add(String.valueOf(c).toLowerCase());
            }
        }

        int left = 0;
        int right = trimmed.size() - 1;

        while (left < right) {
            if (!trimmed.get(left).equals(trimmed.get(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
