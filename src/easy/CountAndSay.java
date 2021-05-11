package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) return "1";

        String previousString = countAndSay(n-1);

        int charCount = 0;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < previousString.length(); i++) {
            char currentChar = previousString.charAt(i);
            if (i == previousString.length()-1 || currentChar != previousString.charAt(i+1)) {
                output.append(charCount+1).append(currentChar);
                charCount = 0;
            } else {
                charCount++;
            }
        }

        return output.toString();
    }
}
