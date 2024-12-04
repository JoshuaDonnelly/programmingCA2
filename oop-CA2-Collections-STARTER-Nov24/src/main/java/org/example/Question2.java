package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name:
 *  Class Group:
 */
public class Question2  // Car Parking - Stack
{
    public static void runSimulation() {
        Stack<Integer> driveway = new Stack<Integer>();
        Stack<Integer> street = new Stack<Integer>();
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter positive numbers to add cars, " +
                "negative numbers to retrieve cars, and 0 to exit.");
        try {
            while (true) {
                System.out.println("Enter Prompts");
                int userInput = kb.nextInt();
                if (userInput == 0) {
                    System.out.println("Exiting");
                    break;
                } else if (userInput > 0) {
                    driveway.push(userInput);
                    System.out.println("Car " + userInput + " added.");

                } else {
                    int selectedCar = Math.abs(userInput);
                    if (!driveway.contains(selectedCar)) {
                        System.out.println("Car " + selectedCar + " not in driveway.");
                    } else {
                        while (driveway.peek() != selectedCar) {
                            int movedCar = driveway.pop();
                            street.push(movedCar);
                            System.out.println("Moved " + movedCar + " to the street");

                        }
                        driveway.pop();
                        System.out.println("Car " + selectedCar + " retrieved.");

                        while (!street.isEmpty()) {
                            int movedCar = street.pop();
                            driveway.push(movedCar);
                            System.out.println("Moved " + movedCar + " to the driveway.");
                        }
                    }
                }
                System.out.println("Current driveway: " + driveway);
                System.out.println("Current street: " + street);
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("A valid prompt must be entered (i.e natural numbers)");

        }
    }


    public static void main(String[] args) {
        runSimulation();
    }
}
