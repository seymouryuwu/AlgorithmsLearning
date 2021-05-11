package array;

/*
https://leetcode.com/problems/partition-array-into-disjoint-intervals/
 */
public class PartitionArrayIntoDisjointIntervals {
    public int partitionDisjoint(int[] A) {
        int[] maxFromStart = new int[A.length];
        int[] minFromEnd = new int[A.length];

        int max = A[0];
        for (int i = 0; i < A.length; i++) {
            max = Math.max(max, A[i]);
            maxFromStart[i] = max;
        }

        int min = A[A.length - 1];
        for (int i = A.length - 1; i >= 0; i--) {
            min = Math.min(min, A[i]);
            minFromEnd[i] = min;
        }

        for (int i = 0; i < A.length - 1; i++) {
            if (maxFromStart[i] <= minFromEnd[i + 1]) {
                return i + 1;
            }
        }

        return -1;
    }
}
