package algorithms;

public class Process {
    private String name;
    private int arrivalTime;
    private int burstTime;
    private int priority; // This is the priority level
    private int startTime;
    private int endTime;
    private int waitingTime;
    private int turnaroundTime;

    public Process(String name, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority; // Initialize priority
        this.startTime = 0;
        this.endTime = 0;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }

    // Getter for priority
    public int getPriority() {
        return priority; // Return the priority level
    }

    public String[] toStringArray() {
        return new String[] {
                name, String.valueOf(arrivalTime), String.valueOf(burstTime), String.valueOf(priority),
                String.valueOf(startTime), String.valueOf(endTime),
                String.valueOf(turnaroundTime), String.valueOf(waitingTime)
        };
    }

    public String getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }
}
