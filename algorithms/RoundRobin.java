package algorithms;

import java.util.*;

import classes.*;

/**
 * Implements the Round Robin scheduling algorithm.
 */
public class RoundRobin extends CPUScheduler {
    @Override
    public void process() {
        // Sort the rows by arrival time
        Collections.sort(this.getRows(), Comparator.comparingInt(Row::getArrivalTime));

        List<Row> rows = Utility.deepCopy(this.getRows()); // Create a deep copy of rows
        int time = rows.get(0).getArrivalTime(); // Initialize time to the first arrival time
        int timeQuantum = this.getTimeQuantum(); // Time quantum

        // Queue for the Round Robin scheduling
        Queue<Row> readyQueue = new LinkedList<>();
        readyQueue.addAll(rows);

        Map<String, Integer> remainingBurstTime = new HashMap<>();
        for (Row row : rows) {
            remainingBurstTime.put(row.getProcessName(), row.getBurstTime());
        }

        while (!readyQueue.isEmpty()) {
            Row row = readyQueue.poll(); // Get the next process in the queue
            int burstTimeLeft = remainingBurstTime.get(row.getProcessName());
            int executionTime = Math.min(burstTimeLeft, timeQuantum);

            // Add the execution event to the timeline
            this.getTimeline().add(new Event(row.getProcessName(), time, time + executionTime));
            time += executionTime;

            // Update the remaining burst time
            burstTimeLeft -= executionTime;
            remainingBurstTime.put(row.getProcessName(), burstTimeLeft);

            // Check if the process is completed
            if (burstTimeLeft > 0) {
                // Re-add the process to the queue if it has remaining burst time
                readyQueue.add(row);
            }
        }

        // Calculate waiting time and turnaround time for each process
        for (Row row : this.getRows()) {
            int totalExecutionTime = 0;
            int lastFinishTime = 0;

            for (Event event : this.getTimeline()) {
                if (event.getProcessName().equals(row.getProcessName())) {
                    if (totalExecutionTime == 0) {
                        // First execution: Waiting time starts from arrival time
                        row.setWaitingTime(event.getStartTime() - row.getArrivalTime());
                    } else {
                        // Subsequent executions: Add waiting time between executions
                        row.setWaitingTime(row.getWaitingTime() + (event.getStartTime() - lastFinishTime));
                    }

                    // Update total execution time and last finish time
                    totalExecutionTime += event.getFinishTime() - event.getStartTime();
                    lastFinishTime = event.getFinishTime();
                }
            }

            // Set turnaround time
            row.setTurnaroundTime(totalExecutionTime + row.getWaitingTime());
        }
    }
}
