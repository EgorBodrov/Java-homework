package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of elevators: ");
        int elevators = Integer.parseInt(in.nextLine());
        System.out.print("Enter elevator's capacity: ");
        int capacity = Integer.parseInt(in.nextLine());
        System.out.print("Enter number of floors: ");
        int floors = Integer.parseInt(in.nextLine());
        System.out.print("Enter max number of people on the same floor: ");
        int maxPeopleOnFloor = Integer.parseInt(in.nextLine());

        Controller ctrl = new Controller(floors, capacity, elevators);
        Building house = new Building(maxPeopleOnFloor, floors, ctrl);
        Thread threadCtrl = new Thread(ctrl);

        Thread threadHouse = new Thread(house);
        threadHouse.start();
        threadCtrl.start();
    }
}
