package org.example;

import java.util.*;
/**
 *  Name: Joshua Donnelly
 *  Class Group: SD2a
 */
public class Question8  // Multi-company (Queue)
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
        Map<String, Queue<Block>> stockMap = new HashMap<String, Queue<Block>>();
        String command="";
        do {
            System.out.println("Enter a command in buy/sell - Company - Quantity - Price");
            System.out.print(">");
            command = in.next();
            if(command.equalsIgnoreCase("buy"))
            {
                String symbol = in.next();
                int qty = in.nextInt();
                double price = in.nextDouble();
                // Code to buy shares here
                stockMap.putIfAbsent(symbol, new LinkedList<>());
                Queue<Block> queue = stockMap.get(symbol);
                queue.add(new Block(qty, price));

                System.out.println("Bought " + qty + " shares of " + symbol + " at " + price + " each.");
            }
            else if(command.equals("sell"))
            {
                String symbol = in.next();
                int qtyToSell = in.nextInt();
                double sellPrice = in.nextDouble();
                // Code to sell shares and calculate profit here
                Queue<Block> queue = stockMap.get(symbol);
                if (queue.isEmpty())
                {
                    System.out.println("No shares of " + symbol + " found.");
                    continue;
                }
                double totalGain = 0;

                while (qtyToSell > 0 && !queue.isEmpty())
                {
                    Block currentBlock = queue.peek();

                    if (currentBlock.quantity <= qtyToSell)
                    {
                        totalGain += currentBlock.price * (sellPrice - currentBlock.price);
                        qtyToSell -= currentBlock.quantity;
                        queue.poll();
                    }
                    else {
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
