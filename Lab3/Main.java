package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter input file path");
            String in = scan.nextLine();

            System.out.println("Enter output file path");
            String out = scan.nextLine();

            LettersAnalysis test = new LettersAnalysis(in, out);
            test.count();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
