package depthFirstSearch;

/*
https://leetcode.com/problems/word-ladder-ii/
 */
import java.util.*;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        if (wordList == null || wordList.size() == 0) {
            return results;
        }

        wordList.add(beginWord);
        Map<String, Set<String>> genericMap = buildGenericMap(wordList);

        Map<String, Integer> distanceToEnd = new HashMap<>();
        if (wordList.contains(endWord)) {
            distanceToEnd = findDistanceToEnd(endWord, wordList, genericMap);
        } else {
            return results;
        }


        List<String> sequence = new ArrayList<>();
        sequence.add(beginWord);
        findShortestSequence(beginWord, endWord, genericMap, distanceToEnd, sequence, results);

        return results;
    }

    private void findShortestSequence(String word,
                                      String endWord,
                                      Map<String, Set<String>> genericMap,
                                      Map<String, Integer> distanceToEnd,
                                      List<String> sequence,
                                      List<List<String>> results) {
        int distance = distanceToEnd.get(word);

        List<String> adjacentWords = findAdjacentWords(word, genericMap);
        for (String adjacent : adjacentWords) {
            if (distanceToEnd.get(adjacent) == distance - 1) {
                sequence.add(adjacent);

                if (adjacent.equals(endWord)) {
                    results.add(new ArrayList<>(sequence));
                } else {
                    findShortestSequence(adjacent,
                            endWord,
                            genericMap,
                            distanceToEnd,
                            sequence,
                            results);
                }

                sequence.remove(sequence.size() - 1);
            }
        }
    }

    private Map<String, Integer> findDistanceToEnd(String endWord,
                                                   List<String> wordList,
                                                   Map<String, Set<String>> genericMap) {
        Map<String, Integer> distanceMap = new HashMap<>();
        for (String word : wordList) {
            distanceMap.put(word, -1);
        }

        int distance = -1;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(endWord);
        visited.add(endWord);

        while (!queue.isEmpty()) {
            distance++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                distanceMap.put(word, distance);

                for (String adj : findAdjacentWords(word, genericMap)) {
                    if (!visited.contains(adj)) {
                        visited.add(adj);
                        queue.offer(adj);
                    }
                }
            }
        }

        return distanceMap;
    }

    private List<String> findAdjacentWords(String word, Map<String, Set<String>> genericMap) {
        List<String> adjacentWords = new ArrayList<>();
        List<String> genericPatterns = findGenericPatterns(word);
        for (String genericPattern : genericPatterns) {
            for (String adjacent : genericMap.get(genericPattern)) {
                if (!adjacent.equals(word)) {
                    adjacentWords.add(adjacent);
                }
            }
        }

        return adjacentWords;
    }

    private List<String> findGenericPatterns(String word) {
        List<String> genericPatterns = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char[] wordChar = word.toCharArray();
            wordChar[i] = '*';
            String generic = new String(wordChar);
            genericPatterns.add(generic);
        }

        return genericPatterns;
    }

    private Map<String, Set<String>> buildGenericMap(List<String> wordList) {
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
