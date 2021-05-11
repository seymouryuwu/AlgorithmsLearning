package array;

/*
https://leetcode.com/problems/intersection-of-two-arrays/
 */
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }

        Set<Integer> setNums1 = new HashSet<>();
        for (int num : nums1) {
            setNums1.add(num);
        }

        Set<Integer> intersection = new HashSet<>();

        for (int num : nums2) {
            if (setNums1.contains(num)) {
                intersection.add(num);
            }
        }

        int[] result = new int[intersection.size()];
        int i = 0;
        for (Integer num : intersection) {
            result[i] = num;
            i++;
        }

        return result;
    }
}
