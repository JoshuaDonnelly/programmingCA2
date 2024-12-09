package org.example;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Dylan Smyth
 *  Class Group: SD2a
 */
public class Question4 {
    static class Cell {
        int row, col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) {
        // Initialize a 10x10 array with all zeros
        int[][] grid = new int[10][10];

        // Number of occupied cells
        int occupiedCount = 20;
        Random random = new Random();

        // Randomly place occupied cells (-1)
        for (int i = 0; i < occupiedCount; ) {
            int randomRow = random.nextInt(10); // Random row (0-9)
            int randomColumn = random.nextInt(10); // Random column (0-9)

            // If the cell is not already occupied, mark it as -1
            if (grid[randomRow][randomColumn] == 0) {
                grid[randomRow][randomColumn] = -1;
                i++;
            }
        }

        // Input starting position
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter starting row (0-9): ");
        int startRow = scanner.nextInt();
        System.out.print("Enter starting column (0-9): ");
        int startCol = scanner.nextInt();

        // Validate input
        if (startRow < 0 || startRow >= 10 || startCol < 0 || startCol >= 10) {
            System.out.println("Invalid starting position!");
            return;
        }

        // Check if the starting position is already occupied
        if (grid[startRow][startCol] == -1) {
            System.out.println("Starting position is occupied!");
            return;
        }

        // Stack for flood fill
        Stack<Cell> stack = new Stack<>();
        stack.push(new Cell(startRow, startCol));

        // Counter for the fill order
        int fillNumber = 1;

        // Flood fill algorithm
        while (!stack.isEmpty()) {
            Cell current = stack.pop();

            int row = current.row;
            int col = current.col;

            // If the cell is already filled or occupied, skip it
            if (grid[row][col] != 0) continue;

            // Fill the current cell
            grid[row][col] = fillNumber++;

            // Push unfilled neighbors onto the stack (NESW)
            if (row > 0 && grid[row - 1][col] == 0) stack.push(new Cell(row - 1, col)); // North
            if (col < 9 && grid[row][col + 1] == 0) stack.push(new Cell(row, col + 1)); // East
            if (row < 9 && grid[row + 1][col] == 0) stack.push(new Cell(row + 1, col)); // South
            if (col > 0 && grid[row][col - 1] == 0) stack.push(new Cell(row, col - 1)); // West
        }

        // Print the resulting grid
        System.out.println("Flood-filled grid:");
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == -1) {
                    System.out.print(" -1"); // Represent occupied cells as 'X'
                } else {
                    System.out.printf("%3d", cell);
                }
            }
            System.out.println();
        }
    }
}