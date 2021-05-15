package heap;

/*
https://leetcode.com/problems/meeting-rooms-ii/
 */
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MeetingRoomsII {
    // Approach 1: doesn't use heap, stupid solution
    // time complexity is O(N*max(M)),
    // N is the number of intervals, M is the max range of the intervals.
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        int earliest = intervals[0][0];
        int latest= intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            earliest = Math.min(earliest, intervals[i][0]);
            latest = Math.max(latest, intervals[i][1]);
        }

        int[] timeSlots = new int[latest - earliest + 1];

        for (int[] i : intervals) {
            for (int timeSlot = i[0]; timeSlot < i[1]; timeSlot++) {
                int tS = timeSlot - earliest;
                timeSlots[tS] = timeSlots[tS] + 1;
            }
        }

        int requireRooms = 0;
        for (int i : timeSlots) {
            requireRooms = Math.max(requireRooms, i);
        }

        return requireRooms;
    }

    // Approach 2: heap,
    // time complexity is N*log(N)
    public int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Queue<Integer> ends = new PriorityQueue<>();
        for (int[] interval : intervals) {
            ends.offer(interval[1]);
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int[] interval : intervals) {
            if (ends.peek() <= interval[0]) {
                ends.poll();
            }
        }

        return ends.size();
    }

    // Approach 3: two pointers. time complexity N*log(N)
    // see twoPointers.MeetingRoomsII

    // Approach 4: line sweep
    // see lineSweep.MeetingRoomsII
}
