package hash;

/*
https://leetcode.com/problems/two-sum-iii-data-structure-design/
 */
import java.util.HashMap;
import java.util.Map;

public class TwoSumIII_DataStructureDesign {
    Map<Integer, Integer> hash;

    /** Initialize your data structure here. */
    public TwoSumIII_DataStructureDesign() {
        hash = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (hash.containsKey(number)) {
            hash.put(number, hash.get(number) + 1);
        } else {
            hash.put(number, 1);
        }
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            int complement = value - k;

            if ((complement == k && v >= 2) ||
                    (complement != k && hash.containsKey(complement))) {
                return true;
            }
        }

        return false;
    }
}
