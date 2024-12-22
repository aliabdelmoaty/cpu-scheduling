package algorithms;

public class FCFS {
    public static void schedule(Process[] processes) {
        // Sort processes by arrival time
        Process[] sortedProcesses = processes.clone();
        java.util.Arrays.sort(sortedProcesses, (p1, p2) -> p1.getArrivalTime() - p2.getArrivalTime());

        int currentTime = 0;
        
        for (Process process : sortedProcesses) {
            // Clear previous execution segments
            process.getExecutionSegments().clear();
            
            // If current time is less than arrival time, update it
            if (currentTime < process.getArrivalTime()) {
                currentTime = process.getArrivalTime();
            }
            
            // Set start time and calculate waiting time
            process.setStartTime(currentTime);
            process.setWaitingTime(currentTime - process.getArrivalTime());
            
            // Add execution segment
            process.addExecutionSegment(currentTime, currentTime + process.getBurstTime());
            
            // Update current time and set end time
            currentTime += process.getBurstTime();
            process.setEndTime(currentTime);
            
            // Calculate and set turnaround time
            process.setTurnaroundTime(currentTime - process.getArrivalTime());
        }
    }
}
