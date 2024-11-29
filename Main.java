import javax.swing.SwingUtilities;

import ui.ProcessScheduler;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProcessScheduler scheduler = new ProcessScheduler();
            scheduler.setVisible(true);
        });
    }
}