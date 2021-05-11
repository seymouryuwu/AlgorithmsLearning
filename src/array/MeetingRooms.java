package array;

/*
https://leetcode.com/problems/meeting-rooms/
 */
import java.util.Arrays;

public class MeetingRooms {
    // Approach 1
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
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
                if (timeSlots[tS] > 0) {
                    return false;
                } else {
                    timeSlots[tS] = timeSlots[tS] + 1;
                }
            }
        }

        return true;
    }

    // Approach 2
    public boolean canAttendMeetings2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }
}
