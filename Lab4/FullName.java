package com.company;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FullName {
    public static int getAge(LocalDateTime birthday) {
        LocalDateTime today = LocalDateTime.now();
        Duration range = Duration.between(birthday, today);
        return (int) (range.toDays() / 365.25);
    }

    public static void main(String[] args) throws DateTimeException {
        try {
            Scanner in = new Scanner(System.in);

            System.out.print("Введите ФИО: ");
            String[] fname = in.nextLine().split(" ");

            System.out.print("Введите дату: ");
            String[] date = in.nextLine().split("\\.");
            LocalDateTime birthday = LocalDateTime.of(Integer.parseInt(date[2]),
                    Integer.parseInt(date[1]),Integer.parseInt(date[0]), 0, 0, 0);

            System.out.println("Имя: " + fname[0] + " " +
                    Character.toUpperCase(fname[1].charAt(0)) + ". " +
                    Character.toUpperCase(fname[2].charAt(0)) + ".");

            if (fname[2].toCharArray()[fname[2].length() - 1] == 'ч') {
                System.out.println("Пол: мужской");
            } else {
                System.out.println("Пол: женский");
            }

            System.out.println("Полных лет: " + FullName.getAge(birthday));
        } catch (DateTimeException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
