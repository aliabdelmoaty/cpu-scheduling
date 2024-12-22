package algorithms;

import java.util.*;

public class PSP {
    public static void schedule(Process[] processes) {
        int currentTime = 0;
        int completedProcesses = 0;
        int totalProcesses = processes.length;

        // Reset all processes
        for (Process p : processes) {
            p.setRemainingTime(p.getBurstTime());
            p.setStartTime(-1);
            p.setEndTime(-1);
            p.getExecutionSegments().clear();
        }

        // Queue for ready processes, sorted by priority and arrival time
        PriorityQueue<Process> readyQueue = new PriorityQueue<>((p1, p2) -> {
            if (p1.getPriority() != p2.getPriority()) {
                return p1.getPriority() - p2.getPriority();
            }
            return p1.getArrivalTime() - p2.getArrivalTime();
        });

        Process currentProcess = null;
        int lastProcessStartTime = -1;

        while (completedProcesses < totalProcesses) {
            // Check for newly arrived processes
            for (Process p : processes) {
                if (p.getArrivalTime() == currentTime && p.getRemainingTime() > 0 && !readyQueue.contains(p)) {
                    readyQueue.add(p);
                    // If preempting current process due to higher priority
                    if (currentProcess != null && 
                            p.getPriority() < currentProcess.getPriority()) {
                        currentProcess.addExecutionSegment(lastProcessStartTime, currentTime);
                        currentProcess.setLastPreemptionTime(currentTime);
                        readyQueue.add(currentProcess);
                        currentProcess = null;
                    }
                }
            }

            // If no current process, get one from ready queue
            if (currentProcess == null && !readyQueue.isEmpty()) {
                currentProcess = readyQueue.poll();
                currentProcess.updateWaitingTime(currentTime);
                lastProcessStartTime = currentTime;
                if (currentProcess.getStartTime() == -1) {
                    currentProcess.setStartTime(currentTime);
                }
            }

            // Process execution
            if (currentProcess != null) {
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);

                // Process completed
                if (currentProcess.getRemainingTime() == 0) {
                    currentProcess.addExecutionSegment(lastProcessStartTime, currentTime + 1);
                    currentProcess.setEndTime(currentTime + 1);
                    completedProcesses++;
                    currentProcess = null;
                }
            }

            // Update waiting time for processes in ready queue
            for (Process p : readyQueue) {
                p.updateWaitingTime(currentTime + 1);
                p.setLastPreemptionTime(currentTime + 1);
            }

            currentTime++;
        }

        // Calculate final metrics
        for (Process p : processes) {
            if (!p.getExecutionSegments().isEmpty()) {
                p.setTurnaroundTime(p.getEndTime() - p.getArrivalTime());
            }
        }
    }
}
