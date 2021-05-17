package lineSweep;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class EmployeeFreeTime {
    private static class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    private static class Point {
        int time;
        boolean isStart;

        public Point(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        if (schedule == null || schedule.size() == 0) {
            return result;
        }

        Queue<Point> points = new PriorityQueue<>((a, b) -> {
            if (a.time != b.time) {
                return Integer.compare(a.time, b.time);
            }

            // if the time of a and b is the same, start go first
            if (a.isStart && !b.isStart) {
                return -1;
            }

            if (!a.isStart && b.isStart) {
                return 1;
            }

            return 0;
        });

        for (List<Interval> eachPerson : schedule) {
            for (Interval interval : eachPerson) {
                points.offer(new Point(interval.start, true));
                points.offer(new Point(interval.end, false));
            }
        }

        int freeStart = Integer.MIN_VALUE;
        int count = 0;
        while (!points.isEmpty()) {
            Point point = points.poll();
            if (point.isStart) {
                if (count == 0) {
                    result.add(new Interval(freeStart, point.time));
                }

                count++;
            } else {
                count--;

                if (count == 0) {
                    freeStart = point.time;
                }
            }
        }

        result.remove(0);

        return result;
    }
}


