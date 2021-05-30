package string;
/*
https://leetcode.com/problems/one-edit-distance/
 */

public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        int m = s.length();
        int n = t.length();

        if (m == n) {
            if (s.equals(t)) {
                return false;
            }

            for (int i = 0; i < m - 1; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
            }

            return true;
        }

        // delete from s
        if (m == n + 1) {
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(i + 1).equals(t.substring(i));
                }
            }

            return true;
        }

        // inset to s
        if (m == n - 1) {
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }

            return true;
        }

        return false;
    }
}
