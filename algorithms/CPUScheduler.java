package algorithms;

import java.util.ArrayList;
import java.util.List;

import classes.*;

public abstract class CPUScheduler {
    private final List<Row> rows; // List of process rows
    private final List<Event> timeline; // Timeline of events
    private int timeQuantum; // Time quantum for scheduling (if needed)

    public CPUScheduler() {
        rows = new ArrayList<>();
        timeline = new ArrayList<>();
        timeQuantum = 1;
    }

    public boolean add(Row row) {
        return rows.add(row);
    }

    public void setTimeQuantum(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public int getTimeQuantum() {
        return timeQuantum;
    }

    public double getAverageWaitingTime() {
        double totalWaitingTime = 0.0;

        for (Row row : rows) {
            totalWaitingTime += row.getWaitingTime();
        }

        return rows.isEmpty() ? 0.0 : totalWaitingTime / rows.size();
    }

    public double getAverageTurnAroundTime() {
        double totalTurnaroundTime = 0.0;

        for (Row row : rows) {
            totalTurnaroundTime += row.getTurnaroundTime();
        }

        return rows.isEmpty() ? 0.0 : totalTurnaroundTime / rows.size();
    }

    public Event getEvent(Row row) {
        for (Event event : timeline) {
            if (row.getProcessName().equals(event.getProcessName())) {
                return event;
            }
        }
        return null;
    }

    public Row getRow(String processName) {
        for (Row row : rows) {
            if (row.getProcessName().equals(processName)) {
                return row;
            }
        }
        return null;
    }

    public List<Row> getRows() {
        return rows;
    }

    public List<Event> getTimeline() {
        return timeline;
    }

    public abstract void process();
}
