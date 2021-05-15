package lineSweep;
/*
https://leetcode.com/problems/meeting-rooms-ii/
 */

import java.util.ArrayList;
import java.util.List;

public class MeetingRoomsII {
    // Approach 1:
    // see heap.MeetingRoomsII

    // Approach 2: heap
    // see heap.MeetingRoomsII

    // Approach 3: two pointers. time complexity N*log(N)
    // see twoPointers.MeetingRoomsII

    // Approach 4: line sweep
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        List<Point> points = new ArrayList<>();
        for (int[] interval : intervals) {
            points.add(new Point(interval[0], false));
            points.add(new Point(interval[1], true));
        }

        points.sort((Point a, Point b) -> {
            if (a.time != b.time) {
                return Integer.compare(a.time, b.time);
            } else {
                // if the points a and b are at the same time,
                // the one which is end will be at first.
                return (a.isEnd) ? -1 : 1;
            }
        });

        int max = 0;
        int count = 0;
        for (Point p : points) {
            if (p.isEnd) {
                count--;
            } else {
                count++;
            }

            max = Math.max(max, count);
        }

        return max;
    }
}

class Point {
    int time;
    boolean isEnd;

    public Point(int time, boolean isEnd) {
        this.time = time;
        this.isEnd = isEnd;
    }
}