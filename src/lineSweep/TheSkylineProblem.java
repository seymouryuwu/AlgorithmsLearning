package lineSweep;
/*
https://leetcode.com/problems/the-skyline-problem/
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TheSkylineProblem {
    private static class Side {
        int x;
        boolean isLeft;
        int y;

        public Side(int x, boolean isLeft, int y) {
            this.x = x;
            this.isLeft = isLeft;
            this.y = y;
        }
    }

    // Approach 1: line sweep
    // see Approach 2, more elegant
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return result;
        }

        Queue<Side> sides = new PriorityQueue<Side>((a, b) -> {
            // the sides with smaller x will be before the sides with larger x.
            if (a.x != b.x) {
                return Integer.compare(a.x, b.x);
            }

            // if a and b are at the same x,
            // the right sides will be before the left sides
            if (!a.isLeft && b.isLeft) {
                return -1;
            }

            if (a.isLeft && !b.isLeft) {
                return 1;
            }

            // if a and b are at the same x, and they are both right sides,
            // the sides with smaller y will be before the sides with larger y.
            // so the sides with smaller y will be checked first.
            if (!a.isLeft) {
                return Integer.compare(a.y, b.y);
            }

            // if a and b are at the same x, and they are both left sides,
            // the sides with larger y will be before the sides with larger y.
            // so the sides with larger y will be checked first.
            return Integer.compare(b.y, a.y);
        });

        for (int[] building : buildings) {
            sides.offer(new Side(building[0], true, building[2]));
            sides.offer(new Side(building[1], false, building[2]));
        }

        Queue<Integer> height = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int highest = 0;
        while (!sides.isEmpty()) {
            Side side = sides.poll();
            if (side.isLeft) {
                if (side.y > highest) {
                    List<Integer> point = new ArrayList<>();
                    point.add(side.x);
                    point.add(side.y);
                    result.add(point);

                    highest = side.y;
                }

                height.offer(side.y);
            } else { // right side
                height.remove(side.y);

                if (side.y == highest) {
                    if (height.isEmpty()) {
                        if (sides.isEmpty() || sides.peek().x != side.x) {
                            List<Integer> point = new ArrayList<>();
                            point.add(side.x);
                            point.add(0);
                            result.add(point);

                            highest = 0;
                        } else if (sides.peek().y < side.y) {
                            highest = 0;
                        }
                    } else if (height.peek() < highest) { // side is the last left side at x
                        if (sides.peek().x != side.x) {
                            List<Integer> point = new ArrayList<>();
                            point.add(side.x);
                            point.add(height.peek());
                            result.add(point);

                            highest = height.peek();
                        } else {
                            if (sides.peek().y < side.y) {
                                highest = height.peek();

                                if (sides.peek().y <= height.peek()) {
                                    List<Integer> point = new ArrayList<>();
                                    point.add(side.x);
                                    point.add(height.peek());
                                    result.add(point);
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    // Approach 2: line sweep, still
    // change the order of left sides and right sides in sides,
    // it will be more elegant
    public List<List<Integer>> getSkyline2(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return result;
        }

        Queue<Side> sides = new PriorityQueue<Side>((a, b) -> {
            // the sides with smaller x will be before the sides with larger x.
            if (a.x != b.x) {
                return Integer.compare(a.x, b.x);
            }

            // if a and b are at the same x,
            // the right sides will be after the left sides
            if (!a.isLeft && b.isLeft) {
                return 1;
            }

            if (a.isLeft && !b.isLeft) {
                return -1;
            }

            // if a and b are at the same x, and they are both right sides,
            // the sides with smaller y will be before the sides with larger y.
            // so the sides with smaller y will be checked first.
            if (!a.isLeft) {
                return Integer.compare(a.y, b.y);
            }

            // if a and b are at the same x, and they are both left sides,
            // the sides with larger y will be before the sides with smaller y.
            // so the sides with larger y will be checked first.
            //if (a.isLeft) {
            return Integer.compare(b.y, a.y);
            //}
        });

        for (int[] building : buildings) {
            sides.offer(new Side(building[0], true, building[2]));
            sides.offer(new Side(building[1], false, building[2]));
        }

        Queue<Integer> height = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        while (!sides.isEmpty()) {
            Side side = sides.poll();
            if (side.isLeft) {
                if (height.isEmpty() || side.y > height.peek()) {
                    List<Integer> point = new ArrayList<>();
                    point.add(side.x);
                    point.add(side.y);
                    result.add(point);
                }

                height.offer(side.y);
            } else { // right side
                height.remove(side.y);

                if (height.isEmpty() || side.y > height.peek()) {
                    List<Integer> point = new ArrayList<>();
                    point.add(side.x);
                    point.add((height.isEmpty() ? 0 : height.peek()));
                    result.add(point);
                }
            }
        }

        return result;
    }
}



// Approach 3: Divide and conquer
// see leetcode solution
