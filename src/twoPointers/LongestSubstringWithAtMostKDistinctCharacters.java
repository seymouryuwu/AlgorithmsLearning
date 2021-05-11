package twoPointers;

/*
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }

        // key is the distinct characters in the window,
        // value is the latest index in s of this character.
        Map<Character, Integer> distinctChars = new HashMap<>();

        int longest = 0;

        int start = 0;
        int end = 0;

        while (end < s.length()) {
            char charAtEnd = s.charAt(end);
            if (distinctChars.size() == k && !distinctChars.containsKey(charAtEnd)) {
                while (distinctChars.get(s.charAt(start)) > start) {
                    start++;
                }

                distinctChars.remove(s.charAt(start));
                start++;
            }

            distinctChars.put(charAtEnd, end);
            longest = Math.max(longest, end - start + 1);

            end++;
        }

        return longest;
    }
}
