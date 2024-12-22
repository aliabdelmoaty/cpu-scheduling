package algorithms;

import java.util.*;

public class SRT {
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

        // Queue for ready processes, sorted by remaining time and arrival time
        PriorityQueue<Process> readyQueue = new PriorityQueue<>((p1, p2) -> {
            if (p1.getRemainingTime() != p2.getRemainingTime()) {
                return p1.getRemainingTime() - p2.getRemainingTime();
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
                    // If preempting current process, update its waiting time
                    if (currentProcess != null &&
                            p.getRemainingTime() < currentProcess.getRemainingTime()) {
                        currentProcess.addExecutionSegment(lastProcessStartTime, currentTime);
                        // Set preemption time for the current process
                        currentProcess.setLastPreemptionTime(currentTime);
                        readyQueue.add(currentProcess);
                        currentProcess = null;
                    }
                }
            }

            // If no current process, get one from ready queue
            if (currentProcess == null && !readyQueue.isEmpty()) {
                currentProcess = readyQueue.poll();
                // Update waiting time when process resumes/starts
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
