package com.company;

public class BoardUtil {

    public static String inputFormatting(String inputString) {
        StringBuilder outputString = new StringBuilder();
        char letter;
        for (int i = 0; i < inputString.toCharArray().length; i++) {
            letter = inputString.charAt(i);
            if (letter == '\'') {
                outputString.append("\'");
            } else {
                outputString.append(letter);
            }
        }
        return outputString.toString().toLowerCase().trim();
    }
}
