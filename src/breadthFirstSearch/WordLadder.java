package breadthFirstSearch;

/*
https://leetcode.com/problems/word-ladder/
 */
import java.util.*;

public class WordLadder {
    // Approach 1
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();

        Set<String> wordHash = new HashSet<>(wordList);

        Queue<String> queue = new LinkedList<>();
        Set<String> hash = new HashSet<>();
        queue.offer(beginWord);
        hash.add(beginWord);
        int number = 0;

        while (!queue.isEmpty() && number < n + 2) {
            number++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return number;
                }

                findAdjacent(word, wordHash, hash, queue);
            }
        }

        return 0;
    }

    private void findAdjacent(String word,
                              Set<String> wordHash,
                              Set<String> hash,
                              Queue<String> queue) {
        for (int i = 0; i < word.length(); i++) {
            char replace = word.charAt(i);
            String front = word.substring(0,i);
            String back = word.substring(i+1);

            for (char c = 'a'; c <= 'z'; c++) {
                String newWord = front + c + back;
                if (!hash.contains(newWord) && wordHash.contains(newWord)) {
                    queue.offer(newWord);
                    hash.add(newWord);
                }
            }
        }
    }

    // Approach 2: speed up
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();

        Map<String, Set<String>> genericMap = buildGeneric(wordList);

        Queue<String> queue = new LinkedList<>();
        Set<String> hash = new HashSet<>();
        queue.offer(beginWord);
        hash.add(beginWord);
        int number = 0;

        while (!queue.isEmpty() && number < n + 2) {
            number++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return number;
                }

                findAdjacent(word, genericMap, hash, queue);
            }
        }

        return 0;
    }

    private void findAdjacent(String word,
                              Map<String, Set<String>> genericMap,
                              Set<String> hash,
                              Queue<String> queue) {
        for (int i = 0; i < word.length(); i++) {
            char[] wordChar = word.toCharArray();
            wordChar[i] = '*';
            String generic = new String(wordChar);
            if (genericMap.containsKey(generic)) {
                for (String adj : genericMap.get(generic)) {
                    if (!hash.contains(adj)) {
                        queue.offer(adj);
                        hash.add(adj);
                    }
                }
            }
        }
    }

    private Map<String, Set<String>> buildGeneric(List<String> wordList) {
        Map<String, Set<String>> genericMap = new HashMap<>();

        int length = wordList.get(0).length();
        for (String word : wordList) {
            for (int i = 0; i < length; i++) {
                char[] wordChar = word.toCharArray();
                wordChar[i] = '*';
                String generic = new String(wordChar);

                Set<String> genericSet;
                if (genericMap.containsKey(generic)) {
                    genericSet = genericMap.get(generic);
                } else {
                    genericSet = new HashSet<>();
                }

                genericSet.add(word);
                genericMap.put(generic, genericSet);
            }
        }

        return genericMap;
    }
}
