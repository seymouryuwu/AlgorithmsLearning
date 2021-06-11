package hash;

import java.util.*;

public class GroupAnagrams {
    // Approach 1: sort
    // time complexity O(N*LlogL)
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }

        Map<String, List<String>> hash = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);

            if (!hash.containsKey(key)) {
                hash.put(key, new ArrayList<String>());
            }

            hash.get(key).add(str);
        }

        for (Map.Entry<String, List<String>> e : hash.entrySet()) {
            result.add(e.getValue());
        }

        return result;
    }

    // Approach 2: build string
    // time complexity O(N*L)
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }

        Map<String, List<String>> hash = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            int[] counts = new int[26];
            for (char c : chars) {
                counts[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int count : counts) {
                sb.append('#').append(count);
            }

            String key = sb.toString();

            if (!hash.containsKey(key)) {
                hash.put(key, new ArrayList<String>());
            }

            hash.get(key).add(str);
        }

        for (Map.Entry<String, List<String>> e : hash.entrySet()) {
            result.add(e.getValue());
        }

        return result;
    }
}
