package org.example;

import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

/**
 *  Name: Joshua Donnelly
 *  Class Group: SD2a
 */
public class Question3 {   //Nested HTML (Stack)


    public static boolean validate(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        Stack<String> stack = new Stack<>();
        while (scanner.hasNext()) {

            String tag = scanner.next();

            // checking if "br"
            if (tag.equals("<br>")) {
                continue;
            }
            if (isOpeningTag(tag))
            {
                stack.push(tag);
            }
            else if (isClosingTag(tag)) {
                if (stack.isEmpty() || !tagMatches(stack.pop(), tag)) //tags dont match or no opener
                return false;
            }
        }
        scanner.close();
        return stack.isEmpty();
    }
    //checking if tags are openers or closers
    private static boolean isOpeningTag(String tag) {
        return tag.matches("<[^/].*>");
    }
    private static boolean isClosingTag(String tag) {
        return tag.matches("</.*>");
    }
    //check if open tag matches closer
    private static boolean tagMatches(String openTag, String closeTag) {
        //extract the inside of tags to see if openers and closers match
        String openName = openTag.substring(1, openTag.length() - 1);
        String closeName = closeTag.substring(2, closeTag.length() - 1);
        return openName.equals(closeName);
    }


    /*
        This function tests the files in the files array to see if
         they are valid.
         tags_valid.txt should return true;
         tags_invalid.txt should output as invalid;


     */
    public static void main(String[] args) throws FileNotFoundException {
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};
        for (String fName : files) {
            System.out.print(fName + ": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
            } else {
                System.out.println("This file is invalid");
            }
        }

    }


}






