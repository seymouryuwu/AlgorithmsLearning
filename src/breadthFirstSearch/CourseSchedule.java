package breadthFirstSearch;

/*
https://leetcode.com/problems/course-schedule/
 */
import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> preR = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            preR.put(i, new HashSet<Integer>());
        }

        Map<Integer, Integer> indegree = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            indegree.put(i, 0);
        }

        for (int[] prerequisite : prerequisites) {
            indegree.put(prerequisite[0], indegree.get(prerequisite[0]) + 1);
            preR.get(prerequisite[1]).add(prerequisite[0]);
        }

        List<Integer> canFinishCourses = new ArrayList<>();
        Queue<Integer> noPrerequisites = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree.get(i) == 0) {
                noPrerequisites.offer(i);
            }
        }

        while (!noPrerequisites.isEmpty()) {
            Integer course = noPrerequisites.poll();
            canFinishCourses.add(course);
            for (Integer post : preR.get(course)) {
                indegree.put(post, indegree.get(post) - 1);
                if (indegree.get(post) == 0) {
                    noPrerequisites.offer(post);
                }
            }
        }

        return canFinishCourses.size() == numCourses;
    }
}
