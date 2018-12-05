package gcsenxmk.q.customer;

// Basic Queue Object
// Called AlohaQueue to be differentiated from java.util.lang.queue

public class AlohaQueue {
    private String name;
    private int estWaitTime;
    private int numPeople;
    private final int imageResource;

    AlohaQueue(String name, int estWaitTime, int numPeople, int imageResource) {
        this.name = name;
        this.estWaitTime = estWaitTime;
        this.numPeople = numPeople;
        this.imageResource = imageResource;

    }

    // Methods to get the basic details of the queue
    String getName() {return name;}
    int getWaitTime() {return estWaitTime;}
    int getNumPeople() {return numPeople;}
    int getImageResource() {return imageResource;}

}
