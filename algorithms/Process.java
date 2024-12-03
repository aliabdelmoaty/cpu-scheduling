package algorithms;

public class Process {

    private String name;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int startTime;
    private int endTime;
    private int turnaroundTime;

    public Process(String name, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
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

    public int getPriority() {
        return priority;
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

    public int getTurnaroundTime() {
        return turnaroundTime;
    }
    

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }
    

   public String[] toStringArray() {
        return new String[] {
                name, String.valueOf(arrivalTime), String.valueOf(burstTime), String.valueOf(priority),
                        String.valueOf(startTime), String.valueOf(endTime), String.valueOf(turnaroundTime)
        };
    }

    
}
