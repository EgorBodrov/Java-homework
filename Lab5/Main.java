package com.company;

public class Main {

    public static void main(String[] args) {
        int floors = 17;
        int space = 4;
        int elevators = 3;
        Controller ctrl = new Controller(floors, space, elevators);
        ctrl.start();
    }
}
