package sample;
import java.util.ArrayList;

public class Elevator {
    private int curFloor;
    private int direction = 0;
    private int waitingDir = 0;
    private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
    private ArrayList<Passenger> waiting = new ArrayList<Passenger>();

    Elevator(int curFloor) {
        this.setCurFloor(curFloor);
    }

    public void move() {
        this.setCurFloor(this.getCurFloor() + this.getDirection());
    }

    public void setWaitingDir(int dir) {
        this.waitingDir = dir;
    }

    public int getWaitingDir() {
        return this.waitingDir;
    }

    public void removeWaiting(Passenger person) {
        this.waiting.remove(person);
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

    public ArrayList<Passenger> getWaiting() {
        return this.waiting;
    }

    public void addPassenger(Passenger person) {
        this.passengers.add(person);
    }

    public void addWaiting(Passenger person) {
        this.waiting.add(person);
    }

    public void removePassenger(Passenger person) {
        this.passengers.remove(person);
    }
}
