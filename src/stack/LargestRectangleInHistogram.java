package stack;

import java.util.Stack;

public class LargestRectangleInHistogram {
    int largest = 0;

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        // this is a monotonic stack
        // pop out rectangles when they are higher than the one which is poshed in.
        Stack<Rectangle> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            Rectangle rectangle = new Rectangle(i, height, 1);
            pushRectangle(rectangle, stack);
        }

        pushRectangle(new Rectangle(heights.length, 0, 1), stack);

        return largest;
    }

    private void pushRectangle(Rectangle rectangle, Stack<Rectangle> stack) {
        while (!stack.isEmpty() && stack.peek().height >= rectangle.height) {
            // pop the top
            Rectangle top = stack.pop();
            top.width += (rectangle.index - top.index - 1);
            largest = Math.max(largest, top.height * top.width);
        }

        if (stack.isEmpty()) {
            rectangle.width += rectangle.index;
        } else {
            rectangle.width += (rectangle.index - stack.peek().index - 1);
        }

        stack.push(rectangle);
    }

    // same approach
    // but stack stores the indexes of the heights of every rectangles.
    // and instead count the width of left side and right side separately,
    // my width will be (the index of the one who kicks me out) - (the index of the one who is just below me in the stack) - 1.
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        // this is a monotonic stack stores the indexes of the heights of every rectangle
        // pop out rectangles when they are higher than the one which is poshed in.
        Stack<Integer> stack = new Stack<>();
        int largest = 0;

        for (int i = 0; i <= heights.length; i++) {
            int height = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] >= height) {
                // pop the top
                int h = heights[stack.pop()];
                int w = (stack.isEmpty()) ? i : (i - stack.peek() - 1);
                largest = Math.max(largest, h * w);
            }

            stack.push(i);
        }

        return largest;
    }
}

class Rectangle {
    int index;
    int height;
    int width;

    public Rectangle(int index, int height, int width) {
        this.index = index;
        this.height = height;
        this.width = width;
    }
}