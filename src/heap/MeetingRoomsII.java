package heap;

/*
https://leetcode.com/problems/meeting-rooms-ii/
 */
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MeetingRoomsII {
    // Approach 1: doesn't use heap,
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

        Queue<Integer> allocator = new PriorityQueue<Integer>((a, b) -> a - b);

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        allocator.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            allocator.add(intervals[i][1]);
        }

        return allocator.size();
    }

    // Approach 3: time complexity N*log(N)
    public int minMeetingRooms3(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start, (a, b) -> a - b);
        Arrays.sort(end, (a, b) -> a - b);

        int startPointer = 0;
        int endPointer = 0;

        int usedRooms = 0;

        while (startPointer < intervals.length) {
            if (start[startPointer] >= end[endPointer]) {
                usedRooms--;
                endPointer++;
            }

            usedRooms++;
            startPointer++;
        }

        return usedRooms;
    }
}
