package com.company;
import java.util.ArrayList;

public class Controller extends Thread {
    private int numFloors;
    private int space;
    private int numElevators;
    private ArrayList<Elevator> elevators = new ArrayList<Elevator>();
    private ArrayList<Passenger> passengers = new ArrayList<Passenger>();

    Controller(int floors, int space, int numElevators) {
        this.setNumFloors(floors);
        this.setSpace(space);
        this.setNumElevators(numElevators);
        for (int i = 0; i < numElevators; i++) {
            this.addElevator(new Elevator(0));
        }
    }

    @Override
    public void run() {
        System.out.println("--------------------------------------------------------");
        while (true) {
            for (Elevator el : this.elevators) {
                el.move();
                
            }

        }
    }

    public void addElevator(Elevator el) {
        this.elevators.add(el);
    }

    public void addPassenger(Passenger pas) {
        this.passengers.add(pas);
    }
    
    public void setNumFloors(int floors) {
        this.numFloors = floors;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    private void setNumElevators(int num) {
        this.numElevators = num;
    }

    public int getNumFloors() {
        return this.numFloors;
    }

    public int getSpace() {
        return this.space;
    }

    public int getNumElevators() {
        return this.numElevators;
    }

}
