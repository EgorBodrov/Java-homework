package com.company;

public class Request {
    private int curFloor;
    private int direction;

    Request(int floor, int dir) {
        this.setCurFloor(floor);
        this.setDirection(dir);
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
}
