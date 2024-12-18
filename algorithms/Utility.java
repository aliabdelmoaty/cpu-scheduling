package algorithms;

import java.util.ArrayList;
import java.util.List;

import classes.Row;

public class Utility {
    /**
     * Creates a deep copy of a list of Row objects.
     * 
     * @param rows The list of rows to copy.
     * @return A new list containing copies of the original rows.
     */
    public static List<Row> deepCopy(List<Row> rows) {
        List<Row> copiedList = new ArrayList<>();
        for (Row row : rows) {
            Row copiedRow = new Row(row.getProcessName(), row.getArrivalTime(), row.getBurstTime());
            copiedRow.setWaitingTime(row.getWaitingTime());
            copiedRow.setTurnaroundTime(row.getTurnaroundTime());
            copiedList.add(copiedRow);
        }
        return copiedList;
    }
}
