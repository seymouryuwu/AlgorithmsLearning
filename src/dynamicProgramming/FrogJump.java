package dynamicProgramming;

/*
https://leetcode.com/problems/frog-jump/
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FrogJump {
    public boolean canCross(int[] stones) {
        int lastStone = stones[stones.length - 1];
        Map<Integer, Set<Integer>> candidateJumps = new HashMap<>();

        for (int i = 0; i < stones.length - 1; i++) {
            candidateJumps.put(stones[i], new HashSet<Integer>());
        }

        Set<Integer> initial = candidateJumps.get(stones[0]);
        initial.add(1);
        candidateJumps.put(stones[0], initial);

        for (int i = 0; i < stones.length - 1; i++) {
            Set<Integer> jumpSet = candidateJumps.get(stones[i]);
            if (jumpSet.isEmpty()) {
                return false;
            }

            for (int jump : jumpSet) {
                int unitJumped = stones[i] + jump;
                if (unitJumped == lastStone) {
                    return true;
                }

                if (candidateJumps.containsKey(unitJumped)) {
                    Set<Integer> unitJumpedSet = candidateJumps.get(unitJumped);
                    unitJumpedSet.add(jump);
                    unitJumpedSet.add(jump + 1);
                    if (jump - 1 != 0) {
                        unitJumpedSet.add(jump - 1);
                    }
                }
            }
        }

        return false;
    }
}
