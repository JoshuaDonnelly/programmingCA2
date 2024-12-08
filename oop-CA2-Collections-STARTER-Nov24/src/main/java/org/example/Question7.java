package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 *  Name:
 *  Class Group:
 */
public class Question7  // Shares Tax Calculations (Queue)
{
    static class Block {
        int quantity;
        double price;

        Block (int q, double p) {
            this.quantity = q;
            this.price = p;
        }
    }
    public static void main(String[] args) {

       Scanner in = new Scanner(System.in);
       Queue<Block> q = new LinkedList<Block>();
        String command="";
            do {
            System.out.println("Enter a command in buy/sell - Quantity - Price");
            System.out.print(">");
            command = in.next();
            if(command.equalsIgnoreCase("buy"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();
                q.add(new Block(qty, price));
                System.out.println("Bought " + qty + " shares at " + price + " each.");
            }
            else if(command.equals("sell")) {
                int qtyToSell = in.nextInt();
                double sellPrice = in.nextDouble();
                double totalGain = 0;

                while (qtyToSell > 0 && !q.isEmpty()) {
                    Block currentBlock = q.peek();

                    if (currentBlock.quantity <= qtyToSell) {
                        totalGain += currentBlock.price * (sellPrice - currentBlock.price);
                        qtyToSell -= currentBlock.quantity;
                        q.poll();
                    } else {
                        totalGain += qtyToSell * (sellPrice - currentBlock.price);
                        currentBlock.quantity -= qtyToSell;
                        qtyToSell = 0;
                    }
                }
                if (qtyToSell > 0) {
                    System.out.println("Not enough shares to sell");
                } else {
                    System.out.println("Total gain: " + totalGain);
                }
            }
            else if (!command.equalsIgnoreCase("quit")) {
                System.out.println("Invalid Command");
            }
            }while(!command.equalsIgnoreCase("quit"));
            ;
            System.out.println("Exiting the program.");
        }
    }


