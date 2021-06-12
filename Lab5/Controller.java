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
        while (true) {
            int elevatorNum = 0;
            for (Elevator el : elevators) {

                ArrayList<Passenger> pasTemp = new ArrayList<Passenger>(el.getPassengers());
                if (el.getPassengers().size() > 0) {
                    for (Passenger ps : pasTemp) {
                        if (ps.getDestFloor() == el.getCurFloor()) {
                            el.removePassenger(ps);
                        }
                    }
                }

                ArrayList<Passenger> waitTemp = new ArrayList<Passenger>(el.getWaiting());
                if (el.getWaiting().size() > 0) {
                    for (Passenger ps : waitTemp) {
                        if (ps.getCurFloor() == el.getCurFloor()) {
                            if (el.getPassengers().size() < getSpace()) {
                                el.addPassenger(ps);
                                el.setDirection(el.getWaitingDir());
                            }
                            el.removeWaiting(ps);
                        }
                    }
                }

                if (el.getPassengers().size() == 0 && el.getWaiting().size() == 0) {
                    el.setWaitingDir(0);
                    el.setDirection(0);
                }

                if (el.getDirection() == el.getWaitingDir() && el.getDirection() == 0 && passengers.size() > 0) {
                    el.addWaiting(passengers.get(0));
                    el.setWaitingDir(passengers.get(0).getRequest().getDirection());

                    if (passengers.get(0).getCurFloor() > el.getCurFloor()) {
                        el.setDirection(1);
                    } else {
                        el.setDirection(0);
                    }

                    passengers.remove(0);
                }

                if (el.getDirection() == el.getWaitingDir() && el.getDirection() != 0 && passengers.size() > 0) {
                    ArrayList<Passenger> upstairs = new ArrayList<Passenger>();
                    ArrayList<Passenger> downstairs = new ArrayList<Passenger>();
                    ArrayList<Passenger> sideTemp = new ArrayList<Passenger>(passengers);

                    for (Passenger ps : sideTemp) {
                        if (el.getCurFloor() < ps.getRequest().getCurFloor()) {
                            upstairs.add(ps);
                        } else if (el.getCurFloor() > ps.getRequest().getCurFloor()) {
                            downstairs.add(ps);
                        }
                    }

                    if (el.getDirection() == 1) {
                        for (Passenger ps : upstairs) {
                            if (ps.getRequest().getDirection() == el.getWaitingDir()
                                    && el.getPassengers().size() < getSpace()) {
                                el.addWaiting(ps);
                                passengers.remove(ps);
                            }
                        }
                    }

                    if (el.getDirection() == -1) {
                        for (Passenger ps : downstairs) {
                            if (ps.getRequest().getDirection() == el.getWaitingDir()
                                    && el.getPassengers().size() < getSpace()) {
                                el.addWaiting(ps);
                                passengers.remove(ps);
                            }
                        }
                    }
                }

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
                System.out.println("Elevator id: " + elevatorNum + "; Current floor: " + el.getCurFloor()  +
                        "; Direction: " + direction + "; Engaged: " + el.getPassengers().size());

                el.move();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public void addElevator(Elevator el) {
        this.elevators.add(el);
    }

    public void addPassenger(Passenger pas) {
        this.passengers.add(pas);
    }

    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }

    public void setNumFloors(int floors) {
        this.numFloors = floors;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public ArrayList<Elevator> getElevators() {
        return this.elevators;
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
