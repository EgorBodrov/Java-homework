package sample;
import java.util.Random;

public class Building extends Thread {
    private int maxPeople;
    private int numFloors;
    private ElevatorController controller;

    Building(int maxPeople, int floors, ElevatorController ctrl) {
        this.setMaxPeople(maxPeople);
        this.setController(ctrl);
        this.setNumFloors(floors);
    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            int from = random.nextInt(this.getNumFloors());
            int people = random.nextInt(maxPeople) + 1;
            for (int i = 0; i < people; i++) {
                int to = random.nextInt(this.getNumFloors());
                while (to == from) {
                    to = random.nextInt(this.getNumFloors());
                }

                int dir = to - from;
                if (dir > 0) {
                    dir = 1;
                }
                else {
                    dir = -1;
                }

                Request req = new Request(from, dir);
                Passenger pas = new Passenger(from, to, req);

                if (this.getController().getPassengers().size() < this.getNumFloors()) {
                    this.getController().addPassenger(pas);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void setController(ElevatorController ctrl) {
        this.controller = ctrl;
    }

    public void setNumFloors(int floors) {
        this.numFloors = floors;
    }

    public void setMaxPeople(int people) {
        this.maxPeople = people;
    }


    public int getMaxPeople() {
        return this.maxPeople;
    }

    public int getNumFloors() {
        return this.numFloors;
    }

    public ElevatorController getController() {
        return this.controller;
    }
}
