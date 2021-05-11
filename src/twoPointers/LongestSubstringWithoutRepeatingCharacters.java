package twoPointers;

/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    // Approach 1: bad solution
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length()-maxLength; i++) {
            Set<Character> charSet = new HashSet<>();
            int j = i;
            while (j < s.length()) {
                if (charSet.contains(s.charAt(j))) {
                    maxLength = Math.max(maxLength, charSet.size());
                    charSet.clear();
                    break;
                } else if (j == s.length()-1) {
                    maxLength = Math.max(maxLength, charSet.size()+1);
                    charSet.clear();
                    break;
                } else {
                    charSet.add(s.charAt(j));
                }
                j++;
            }
        }
        return maxLength;
    }

    // Approach 2: bad solution
    public int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        Map<Character, Integer> uniqueSubstring = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char checkedChar = s.charAt(i);
            if (uniqueSubstring.containsKey(checkedChar)) {
                int oldIndex = uniqueSubstring.get(checkedChar);
                Map<Character, Integer> newUniqueSubstring = new HashMap<>();
                for (Map.Entry<Character, Integer> substringChar : uniqueSubstring.entrySet()) {
                    if (substringChar.getValue() > oldIndex) {
                        newUniqueSubstring.put(substringChar.getKey(), substringChar.getValue());
                    }
                }
                uniqueSubstring = newUniqueSubstring;
            }
            uniqueSubstring.put(checkedChar, i);
            maxLength = Math.max(maxLength, uniqueSubstring.size());
        }
        return maxLength;
    }


    // Approach 3: two pointers.
    // maintain a hashset that stores the unique characters of a substring including s.charAt(j).
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }


    // Approach 4: two pointers.
    // maintain a hashmap that stores the latest index of each character.
    public int lengthOfLongestSubstring4(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    // Approach 5: same as approach 4.
    public int lengthOfLongestSubstring5(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int start = 0;
        int end = 0;
        Map<Character, Integer> charIndex = new HashMap<>();
        int longest = 1;

        for (; end < s.length(); end++) {
            char charAtEnd = s.charAt(end);
            if (!charIndex.containsKey(charAtEnd)) {
                longest = Math.max(longest, end - start + 1);
            } else {
                int charIdx = charIndex.get(charAtEnd);
                if (charIdx >= start) {
                    start = charIdx + 1;
                } else {
                    longest = Math.max(longest, end - start + 1);
                }
            }

            charIndex.put(charAtEnd, end);
        }

        return longest;
    }
}
