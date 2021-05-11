package medium;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        int maxLength = 1;
        int p = 0;
        boolean isEven = false;
        int i = 0;
        while (i < s.length()-1) {
            for (int j = 1; i-j+1 >= 0 && i+j < s.length(); j++) {
                if (s.charAt(i-j+1) == s.charAt(i+j)) {
                    p = (2*j) >= maxLength ? i : p ;
                    isEven = (2 * j) >= maxLength || isEven;
                    maxLength = Math.max(maxLength, 2*j);

                } else break;
            }
            i++;
            for (int j = 1; i-j >= 0 && i+j < s.length() ; j++) {
                if (s.charAt(i-j) == s.charAt(i+j)) {
                    p = (2*j+1) >= maxLength ? i : p;
                    isEven = (2 * j + 1) < maxLength && isEven;
                    maxLength = Math.max(maxLength, 2*j+1);

                } else break;
            }
        }
        String output;
        if (isEven) {
            output = s.substring(p-maxLength/2+1, p+maxLength/2+1);
        } else {
            output = s.substring(p-maxLength/2, p+maxLength/2+1);
        }
        return output;
    }
}
