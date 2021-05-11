package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (p.equals(".*")) return true;
        if (p.length() == 0) return s.equals("");
        if (p.length() == 1) return (p.equals(".") && s.length() == 1) || s.equals(p);

        if (s.length() == 0) {
            if (p.charAt(1) != '*') return false;
            else return isMatch(s, p.substring(2));
        }

        if (p.charAt(1) != '*') {
            if (!isMatch(s.substring(0,1), p.substring(0,1))) return false;
            else {
                s = s.substring(1);
                p = p.substring(1);
                return isMatch(s, p);
            }
        } else {
            if (p.charAt(0) == '.') {
                for (int i = 0; i <= s.length(); i++) {
                    if (isMatch(s.substring(i), p.substring(2))) return true;
                }
            }

            int i = 0;
            char commonChar = s.charAt(0);
            while (i < s.length() && s.charAt(i) == commonChar) i++;

            int j = 0;
            while (j <= i) {
                String checkedStr = j == 0 ? "" : String.valueOf(commonChar);
                if ((checkedStr.equals("") || isMatch(checkedStr, p.substring(0,1))) &&
                        isMatch(s.substring(j), p.substring(2)))
                    return true;
                j++;
            }
            return false;
        }
    }

    public boolean isMatch2(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }
}
