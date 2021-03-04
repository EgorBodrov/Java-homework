package com.company;
import java.io.*;
import java.util.*;

public class LettersAnalysis {
    private Map<Character, Integer> dict;
    private FileReader inObj;
    private FileWriter outObj;

    public LettersAnalysis(String pathIn, String pathOut) throws IOException {
        try {
            inObj = new FileReader(pathIn);
            outObj = new FileWriter(pathOut, false);
            dict = new HashMap<Character, Integer>();
        } catch (IOException ex) {
            System.out.println("File not found, enter correct path.\n");
        }
    }

    public void count() throws IOException {
        try {
            int symbol;
            while ((symbol = inObj.read()) != -1) {
                if (Character.isLetter((char) symbol)) {
                    if (dict.get(Character.toUpperCase((char) symbol)) == null) {
                        dict.put(Character.toUpperCase((char) symbol), 1);
                    } else {
                        dict.put(Character.toUpperCase((char) symbol),
                                dict.get(Character.toUpperCase((char) symbol)) + 1);
                    }
                }
            }

            for (Map.Entry<Character, Integer> elem : dict.entrySet()) {
                outObj.write(elem.getKey() + " - " + elem.getValue() + " times\n");
            }
            outObj.flush();

        } catch (IOException ex) {
            System.out.println("Error in file access.\n");
        }
        finally {
            inObj.close();
            outObj.close();
        }
    }
}
