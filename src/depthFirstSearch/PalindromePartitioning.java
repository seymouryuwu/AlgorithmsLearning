package depthFirstSearch;

/*
https://leetcode.com/problems/palindrome-partitioning/
 */
import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        List<String> partition = new ArrayList<>();
        if (isPalindrome(s)) {
            partition.add(s);
            result.add(new ArrayList<>(partition));
            partition.remove(partition.size() - 1);
        }

        helper(result, s, partition);

        return result;
    }

    private void helper(List<List<String>> result,
                        String s,
                        List<String> partition
    ) {


        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            if (!isPalindrome(left)) {
                continue;
            }

            partition.add(left);

            String right = s.substring(i, s.length());
            if (isPalindrome(right)) {
                partition.add(right);
                result.add(new ArrayList<>(partition));
                partition.remove(partition.size() - 1);
            }

            helper(result, right, partition);

            partition.remove(partition.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
