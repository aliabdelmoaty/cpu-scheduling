package algorithms;

import java.util.*;

public class RoundRobin {
    public static void schedule(Process[] processes, int quantum) {
        Queue<Process> readyQueue = new LinkedList<>();
        int currentTime = 0;
        int completedProcesses = 0;
        
        // Reset all processes
        for (Process p : processes) {
            p.setRemainingTime(p.getBurstTime());
            p.setStartTime(-1);
            p.setEndTime(-1);
            p.setWaitingTime(0);
            p.getExecutionSegments().clear();
        }

        while (completedProcesses < processes.length) {
            // Check for newly arrived processes
            for (Process p : processes) {
                if (p.getArrivalTime() == currentTime && p.getRemainingTime() > 0) {
                    readyQueue.offer(p);
                }
            }

            // If queue is empty but processes remain, advance time
            if (readyQueue.isEmpty()) {
                currentTime++;
                continue;
            }

            // Get next process from queue
            Process currentProcess = readyQueue.poll();
            
            // Set start time if process hasn't started yet
            if (currentProcess.getStartTime() == -1) {
                currentProcess.setStartTime(currentTime);
            }

            // Calculate execution time for this quantum
            int executionTime = Math.min(quantum, currentProcess.getRemainingTime());
            
            // Add execution segment
            currentProcess.addExecutionSegment(currentTime, currentTime + executionTime);
            
            // Update process state
            currentProcess.setRemainingTime(currentProcess.getRemainingTime() - executionTime);
            currentTime += executionTime;

            // Check for process completion
            if (currentProcess.getRemainingTime() == 0) {
                currentProcess.setEndTime(currentTime);
                currentProcess.setTurnaroundTime(currentTime - currentProcess.getArrivalTime());
                completedProcesses++;
            } else {
                // Add newly arrived processes before re-adding current process
                for (Process p : processes) {
                    if (p.getArrivalTime() > currentTime - executionTime && 
                        p.getArrivalTime() <= currentTime && 
                        p.getRemainingTime() > 0 && 
                        p != currentProcess) {
                        readyQueue.offer(p);
                    }
                }
                readyQueue.offer(currentProcess);
            }

            // Update waiting time for processes in ready queue
            for (Process p : readyQueue) {
                if (p != currentProcess) {
                    p.setWaitingTime(p.getWaitingTime() + executionTime);
                }
            }
        }
    }
}
