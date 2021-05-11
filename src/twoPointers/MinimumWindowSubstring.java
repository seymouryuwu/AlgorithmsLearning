package twoPointers;

/*
https://leetcode.com/problems/minimum-window-substring/
 */
import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    // Approach 1
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> tCharCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char charAtI = t.charAt(i);
            if (!tCharCount.containsKey(charAtI)) {
                tCharCount.put(charAtI, 1);
            } else {
                tCharCount.put(charAtI, 1 + tCharCount.get(charAtI));
            }
        }

        int minWindow = Integer.MAX_VALUE;
        int ansStart = 0;
        int ansEnd = 0;

        Map<Character, Integer> windowCharCount = new HashMap<>();
        int start = 0;
        int end = 0;

        // the sum of target chars in window. ( not greater than the number in target)
        int sumWindow = 0;

        while (end < s.length()) {
            char charAtEnd = s.charAt(end);
            if (tCharCount.containsKey(charAtEnd)) {
                if (!windowCharCount.containsKey(charAtEnd)) {
                    windowCharCount.put(charAtEnd, 1);
                } else {
                    windowCharCount.put(charAtEnd, 1 + windowCharCount.get(charAtEnd));
                }

                if (windowCharCount.get(charAtEnd) <= tCharCount.get(charAtEnd)) {
                    sumWindow++;
                }

                while (sumWindow == t.length()) {
                    if (end - start + 1 < minWindow) {
                        ansStart = start;
                        ansEnd = end;
                        minWindow = end - start + 1;
                    }

                    char charAtStart = s.charAt(start);
                    if (windowCharCount.containsKey(charAtStart)) {
                        if (windowCharCount.get(charAtStart) <= tCharCount.get(charAtStart)) {
                            sumWindow--;
                        }

                        windowCharCount.put(charAtStart, windowCharCount.get(charAtStart) - 1);
                    }

                    start++;
                }
            }

            end++;
        }

        if (minWindow == Integer.MAX_VALUE) {
            return "";
        } else {
            return s.substring(ansStart, ansEnd + 1);
        }
    }

    // Approach 2
    public String minWindow2(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> tCharCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char charAtI = t.charAt(i);
            if (!tCharCount.containsKey(charAtI)) {
                tCharCount.put(charAtI, 1);
            } else {
                tCharCount.put(charAtI, 1 + tCharCount.get(charAtI));
            }
        }

        int minWindow = Integer.MAX_VALUE;
        int ansStart = 0;
        int ansEnd = 0;

        Map<Character, Integer> windowCharCount = new HashMap<>();
        int start = 0;
        int end = 0;

        int passableChar = 0;

        while (end < s.length()) {
            char charAtEnd = s.charAt(end);
            if (tCharCount.containsKey(charAtEnd)) {
                if (!windowCharCount.containsKey(charAtEnd)) {
                    windowCharCount.put(charAtEnd, 1);
                } else {
                    windowCharCount.put(charAtEnd, 1 + windowCharCount.get(charAtEnd));
                }

                if (windowCharCount.get(charAtEnd).intValue() == tCharCount.get(charAtEnd).intValue()) {
                    passableChar++;
                }

                while (passableChar == tCharCount.size()) {
                    if (end - start + 1 < minWindow) {
                        ansStart = start;
                        ansEnd = end;
                        minWindow = end - start + 1;
                    }

                    char charAtStart = s.charAt(start);
                    if (windowCharCount.containsKey(charAtStart)) {
                        if (windowCharCount.get(charAtStart).intValue() == tCharCount.get(charAtStart).intValue()) {
                            passableChar--;
                        }

                        windowCharCount.put(charAtStart, windowCharCount.get(charAtStart) - 1);
                    }

                    start++;
                }
            }

            end++;
        }

        if (minWindow == Integer.MAX_VALUE) {
            return "";
        } else {
            return s.substring(ansStart, ansEnd + 1);
        }
    }
}
