package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private String name;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int startTime;
    private int endTime;
    private int turnaroundTime;
    private int remainingTime;
    private int waitingTime; // Add this field
    private int lastPreemptionTime; // Add this field
    private List<ExecutionSegment> executionSegments;

    public Process(String name, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.startTime = 0; // Initialize to -1 to indicate not started
        this.waitingTime = 0;
        this.lastPreemptionTime = arrivalTime;
        this.executionSegments = new ArrayList<>();
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

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void updateWaitingTime(int currentTime) {
        if (lastPreemptionTime > 0) {
            waitingTime += currentTime - lastPreemptionTime;
        }
    }

    public void setLastPreemptionTime(int time) {
        this.lastPreemptionTime = time;
    }

    public int getLastPreemptionTime() {
        return lastPreemptionTime;
    }

    // Modify calculateWaitingTime to return the accumulated waiting time
    public int calculateWaitingTime() {
        return waitingTime;
    }

    public int calculateTurnaroundTime() {
        if (endTime == -1)
            return 0;
        return endTime - arrivalTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void addExecutionSegment(int start, int end) {
        executionSegments.add(new ExecutionSegment(start, end));
    }

    public List<ExecutionSegment> getExecutionSegments() {
        return executionSegments;
    }

    public String[] toStringArray() {
        return new String[] {
                name, String.valueOf(arrivalTime), String.valueOf(burstTime), String.valueOf(priority),
                String.valueOf(waitingTime), String.valueOf(endTime), String.valueOf(turnaroundTime)
        };
    }

    // Inner class to represent execution segments
    public static class ExecutionSegment {
        private int startTime;
        private int endTime;

        public ExecutionSegment(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }
    }
}
