package greedy;

public class JumpGameII {
    // Approach 1: greedy, from back
    public int jump(int[] nums) {
        int last = nums.length - 1;
        int count = 0;
        while (last != 0) {
            int toReach = last;
            for (int i = toReach; i >= 0; i--) {
                if (nums[i] >= toReach - i) {
                    last = i;
                }
            }

            count++;
        }

        return count;
    }

    // Approach 2: greedy, from front
    public int jump2(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int maxPosition = nums[0];
        int currentJumpCanReach = nums[0];
        int count = 1;

        for (int i = 0; i < nums.length; i++) {
            if (currentJumpCanReach < i) {
                currentJumpCanReach = maxPosition;
                count++;
            }

            maxPosition = Math.max(maxPosition, nums[i] + i);
        }

        return count;
    }
}
