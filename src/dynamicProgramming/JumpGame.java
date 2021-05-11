package dynamicProgramming;

/*
https://leetcode.com/problems/jump-game/
 */
public class JumpGame {
    // Approach 1: dynamic programming
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        int lastIndex = nums.length - 1;
        boolean[] canReach = new boolean[nums.length - 1];

        for (int i = canReach.length - 1; i >= 0; i--) {
            if (nums[i] >= lastIndex - i) {
                canReach[i] = true;
                continue;
            }

            boolean thisCanReach = false;
            for (int j = 1; j <= nums[i]; j++) {
                if (canReach[i + j]) {
                    thisCanReach = true;
                    break;
                }
            }

            canReach[i] = thisCanReach;
        }

        return canReach[0];
    }

    // Approach 2: greedy
    public boolean canJump2(int[] nums) {
        int lastPosition = nums.length - 1;
        for (int i = lastPosition; i >=0; i--) {
            if (nums[i] >= lastPosition - i) {
                lastPosition = i;
            }
        }

        return (lastPosition == 0);
    }
}
