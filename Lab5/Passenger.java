package sample;

public class Passenger {
    private int curFloor;
    private int destFloor;
    private Request request;

    Passenger(int from, int to, Request req) {
        this.setCurFloor(from);
        this.setDestFloor(to);
        this.setRequest(req);
    }

    public void setCurFloor(int from) {
        this.curFloor = from;
    }

    public void setDestFloor(int to) {
        this.destFloor = to;
    }

    public void setRequest(Request req) {
        this.request = req;
    }

    public int getCurFloor() {
        return this.curFloor;
    }

    public int getDestFloor() {
        return this.destFloor;
    }

    public Request getRequest() {
        return this.request;
    }
}
