package twoPointers;
/*
https://leetcode.com/problems/meeting-rooms-ii/
 */

import java.util.Arrays;

public class MeetingRoomII {
    // Approach 1: see heap.MeetingRoomsII

    // Approach 2: heap
    // see heap.MeetingRoomsII

    // Approach 3: two pointers
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        int length = intervals.length;

        int[] starts = new int[length];
        int[] ends = new int[length];

        for (int i = 0; i < length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int usedRoom = length;
        int endPointer = 0;
        for (int i = 0; i < length; i++) {
            if (starts[i] >= ends[endPointer]) {
                usedRoom--;
                endPointer++;
            }
        }

        return usedRoom;
    }

    // Approach 4: line sweep
    // see lineSweep.MeetingRoomsII
}
