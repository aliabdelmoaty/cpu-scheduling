
# CPU Scheduler Simulator

A Java-based GUI application demonstrating various CPU scheduling algorithms with visualization capabilities.

## Features
- 6 Scheduling Algorithms:
  - FCFS (First-Come, First-Served)
  - SJF (Shortest Job First)
  - Priority Scheduling (Non-preemptive and Preemptive)
  - Round Robin
  - SRT (Shortest Remaining Time)
  - PSP (Priority Scheduling Preemptive)
- Interactive Gantt chart visualization
- Process table management
- Performance metrics (waiting time, turnaround time)


2. In the GUI:
- Add processes using the input fields
- Select scheduling algorithm from the buttons
- View results in table and Gantt chart
- For Round Robin, enter time quantum when prompted

## Algorithms Implemented
| Algorithm       | Type          | Preemptive |
|-----------------|---------------|------------|
| FCFS            | Non-preemptive| No         |
| SJF             | Non-preemptive| No         |
| Priority (PSN)  | Non-preemptive| No         |
| Priority (PSP)  | Preemptive    | Yes        |
| Round Robin     | Preemptive    | Yes        |
| SRT             | Preemptive    | Yes        |


## Project Structure
```
src/
├── algorithms/    # Scheduling algorithm implementations
├── ui/            # GUI components
Main.java          # Entry point
```

