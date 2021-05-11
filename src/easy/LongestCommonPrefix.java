package easy;

/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.


Constraints:

0 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lower-case English letters.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        boolean isCommonChar = true;
        int sameUntilIndex = 0;
        while (isCommonChar) {
            for (String str : strs) {
                if (str.length() <= sameUntilIndex ||
                        str.charAt(sameUntilIndex) != strs[0].charAt(sameUntilIndex)) {
                    isCommonChar = false;
                    break;
                }
            }
            sameUntilIndex++;
        }

        if (sameUntilIndex == 0) return "";
        else return strs[0].substring(0,sameUntilIndex-1);
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}
