package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name:
 *  Class Group:
 */

public class Question6      // Flight take-off (Queue)
{
    Queue<String> takeoffQueue = new LinkedList<>();
    Queue<String> landingQueue = new LinkedList<>();

    public void takeoff (String flightCode)
    {
        takeoffQueue.add(flightCode);
        System.out.println("Flight "+flightCode+" added to takeoff queue");
    }
    public void land (String flightCode)
    {
        landingQueue.add(flightCode);
        System.out.println("Flight "+flightCode+" added to landing queue");
    }
    public void next ()
    {
        if (!landingQueue.isEmpty())
        {
            String flight = landingQueue.poll();
            System.out.println("Flight "+flight+" has landed");
        } else if (!takeoffQueue.isEmpty())
        {
            String flight = takeoffQueue.poll();
            System.out.println("Flight "+flight+" has taken off");
        }
        else
        {
            System.out.println("No flights waiting...");
        }
    }
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        Question6 airport = new Question6 ();
        System.out.println("Enter commands:\ntakeoff <flightCode> \n" +
                "land <flightCode> \nnext \nquit");
        while (true)
        {
            System.out.println("> ");
            String input = kb.nextLine().trim();

            if (input.isEmpty())
            {
                System.out.println("Invalid command");
                continue;
            }
            String[] tokens = input.split("\\s+");
            String command = tokens[0].toLowerCase();

            switch (command)
            {
                case "takeoff":
                    airport.takeoff(tokens[1]);
                    break;
                    case "land":
                        airport.land(tokens[1]);
                        break;
                        case "next":
                            airport.next();
                            break;
                            case "quit":
                                System.out.println("Exiting application...");
                                System.exit(0);
                                default:
                                    System.out.println("Invalid command "+command);
            }

        }
    }
}
