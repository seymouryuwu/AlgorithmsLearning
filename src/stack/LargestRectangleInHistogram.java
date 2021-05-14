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