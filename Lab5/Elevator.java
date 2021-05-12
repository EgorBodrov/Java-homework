package com.company;
import java.util.ArrayList;

public class Elevator {
    private int curFloor;
    private int direction = 0;
    private ArrayList<Passenger> passengers = new ArrayList<Passenger>();

    Elevator(int curFloor) {
        this.setCurFloor(curFloor);
    }

    public void move() {
        this.setCurFloor(this.getCurFloor() + this.getDirection());
        for (Passenger passenger : passengers) {
            if (passenger.getDestFloor() == this.getCurFloor()) {
                this.removePassenger(passenger);
            }
        }
    }

    public void setCurFloor(int floor) {
        this.curFloor = floor;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

    public int getCurFloor() {
        return this.curFloor;
    }

    public int getDirection() {
        return this.direction;
    }

    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }

    public void addPassenger(Passenger person) {
        this.passengers.add(person);
    }

    public void removePassenger(Passenger person) {
        this.passengers.remove(person);
    }

}
