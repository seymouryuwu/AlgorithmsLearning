package binarySearch;

/*
https://leetcode.com/problems/search-a-2d-matrix/
 */
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        if (searchMatrixInRow(matrix, target, matrix.length - 1)) {
            return true;
        }

        int start = 0;
        int end = matrix.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (searchMatrixInRow(matrix, target, start)) {
            return true;
        }

        return false;
    }

    private boolean searchMatrixInRow(int[][] matrix, int target, int x) {
        int start = 0;
        int end = matrix[0].length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[x][mid] == target) {
                return true;
            } else if (matrix[x][mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (matrix[x][start] == target) {
            return true;
        }

        if (matrix[x][end] == target) {
            return true;
        }

        return false;
    }
}
