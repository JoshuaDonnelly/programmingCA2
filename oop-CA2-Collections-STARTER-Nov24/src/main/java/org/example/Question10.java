package org.example;
import java.util.Stack;

/**
 *  Name: Dylan Smyth
 *  Class Group: SD2a
 */
class Cell {
    int row, column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

public class Question10
{

    public static void main(String[] args) {
        // Define the maze (0 = open, 1 = wall)
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        // Starting and exit positions
        Cell start = new Cell(0, 0); // Top-left corner
        Cell exit = new Cell(4, 4); // Bottom-right corner

        // Solve the maze
        boolean solved = solveMaze(maze, start, exit);

        // Print the result
        if (solved) {
            System.out.println("Maze solved! Path is marked with 2.");
        } else {
            System.out.println("No path exists through the maze.");
        }

        printMaze(maze);
    }

    private static boolean solveMaze(int[][] maze, Cell start, Cell exit) {
        Stack<Cell> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell current = stack.pop();

            int row = current.row;
            int column = current.column;

            // Check if we reached the exit
            if (row == exit.row && column == exit.column) {
                maze[row][column] = 2; // Mark the exit as part of the path
                return true;
            }

            // Mark the current cell as visited
            maze[row][column] = 2;

            // Explore neighbours in all four directions
            for (int[] direction : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) { //Checks North South East West to find a direction
                int newRow = row + direction[0];
                int newColumn = column + direction[1];

                // Check if the neighbour is valid and unvisited
                if (isValidMove(maze, newRow, newColumn)) {
                    stack.push(new Cell(newRow, newColumn));
                }
            }
        }

        // If the stack becomes empty
        return false;
    }

    private static boolean isValidMove(int[][] maze, int row, int column) {
        // Check if the move is within bounds and on an open path (0)
        return row >= 0 && row < maze.length && column >= 0 && column < maze[0].length && maze[row][column] == 0;
    }

    private static void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print((cell == 1 ? "#" : (cell == 2 ? "." : " ")) + " ");
            }
            System.out.println();
        }
    }
}