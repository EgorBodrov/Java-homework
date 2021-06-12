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

        int elevatorNum = 0;
        System.out.println("--------------------------------------------------------");
        for (Elevator el : ctrl.getElevators()) {
            String direction = "";
            switch (el.getDirection()) {
                case -1:
                    direction = "down";
                    break;
                case 1:
                    direction = "up";
                    break;
                default:
                    direction = "stopped";
                    break;
            }
            elevatorNum++;
            System.out.println("Elevator id: " + elevatorNum + "; Current floor: " + el.getCurFloor() +
                    "; Direction: " + direction + "; Engaged: " + el.getPassengers().size());
        }
        Thread threadHouse = new Thread(house);
        threadHouse.start();
        threadCtrl.start();
    }
}
