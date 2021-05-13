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
            int elNumber = 0;
            for (Elevator el : this.elevators) {
                el.move();
                int floor = el.getCurFloor();

                ArrayList<Passenger> tmp = new ArrayList<Passenger>(el.getPassengers());
                for (Passenger passenger : tmp) {
                    if (passenger.getDestFloor() == floor) {
                        el.removePassenger(passenger);
                    }
                }

                if (el.getPassengers().size() == 0) {
                    el.setDirection(0);
                } else if (el.getCurFloor() == 0 && el.getDirection() == -1) {
                    el.setDirection(0);
                } else if (el.getCurFloor() == this.getNumFloors() - 1 && el.getDirection() == 1) {
                    el.setDirection(0);
                }


                int dir = el.getDirection();

                ArrayList<Passenger> tmp2 = new ArrayList<Passenger>(this.passengers);

                if (dir != 0) {
                    for (Passenger passenger : tmp2) {
                        if (passenger.getCurFloor() == floor
                                && passenger.getRequest().getDirection() == dir
                                && el.getPassengers().size() < this.getSpace()) {
                            el.addPassenger(passenger);
                            this.passengers.remove(passenger);
                        }
                    }
                } else {
                    ArrayList<Passenger> pickUp = new ArrayList<Passenger>();
                    ArrayList<Passenger> pickDown = new ArrayList<Passenger>();
                    for (Passenger passenger : tmp2) {
                        if (passenger.getCurFloor() != floor) {
                            if (passenger.getCurFloor() > floor) {
                                pickUp.add(passenger);
                            } else {
                                pickDown.add(passenger);
                            }
                        } else {
                            el.addPassenger(passenger);
                            this.passengers.remove(passenger);
                            el.setDirection(passenger.getRequest().getDirection());
                        }

                        if (pickUp.size() > pickDown.size()) {
                            el.setDirection(1);
                        } else {
                            el.setDirection(-1);
                        }
                    }
                }

                elNumber++;
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
                System.out.println("Elevator : " + elNumber + "; Current floor: " + el.getCurFloor() +
                        "; Direction: " + direction + "; Passenger number: " + el.getPassengers().size());
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
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
