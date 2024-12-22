package algorithms;

import java.util.*;

public class PSN {
    public static void schedule(Process[] processes) {
        int currentTime = 0;
        int completedProcesses = 0;
        int totalProcesses = processes.length;

        // Create a list of unprocessed processes
        List<Process> unprocessedProcesses = new ArrayList<>(Arrays.asList(processes));
        
        // Reset all processes
        for (Process p : processes) {
            p.setStartTime(-1);
            p.setEndTime(-1);
            p.setWaitingTime(0);
            p.getExecutionSegments().clear();
        }

        while (completedProcesses < totalProcesses) {
            List<Process> availableProcesses = new ArrayList<>();
            
            // Find processes that have arrived
            for (Process p : unprocessedProcesses) {
                if (p.getArrivalTime() <= currentTime) {
                    availableProcesses.add(p);
                }
            }
            
            if (availableProcesses.isEmpty()) {
                // No processes available, move time to next arrival
                int nextTime = Integer.MAX_VALUE;
                for (Process p : unprocessedProcesses) {
                    if (p.getArrivalTime() > currentTime) {
                        nextTime = Math.min(nextTime, p.getArrivalTime());
                    }
                }
                currentTime = nextTime;
                continue;
            }
            
            // Find process with highest priority (lower number means higher priority)
            Process selectedProcess = Collections.min(availableProcesses,
                    Comparator.comparingInt(Process::getPriority));
            
            // Set process times
            selectedProcess.setStartTime(currentTime);
            selectedProcess.setWaitingTime(currentTime - selectedProcess.getArrivalTime());
            selectedProcess.addExecutionSegment(currentTime, currentTime + selectedProcess.getBurstTime());
            
            // Update current time and end time
            currentTime += selectedProcess.getBurstTime();
            selectedProcess.setEndTime(currentTime);
            selectedProcess.setTurnaroundTime(currentTime - selectedProcess.getArrivalTime());
            
            // Remove from unprocessed list
            unprocessedProcesses.remove(selectedProcess);
            completedProcesses++;
        }
    }
}
