package ui;

import algorithms.Process;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ProcessGanttChart extends JPanel {
    private List<Process> processes;
    private int totalTime;
    private final float HEIGHT_RATIO = 0.25f; // Height as a proportion of panel height
    private final float PADDING_RATIO = 0.05f; // Padding as a proportion of panel width
    private float animationProgress = 0.0f;
    private final Map<String, Color> processColors = new HashMap<>();
    private final Random random = new Random();

    public ProcessGanttChart() {
        setBackground(new Color(245, 247, 250));
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }

    public void updateChart(Process[] processes) {
        this.processes = java.util.Arrays.asList(processes);
        
        // Sort processes first by start time, then by name for stable ordering
        java.util.Arrays.sort(this.processes.toArray(new Process[0]), (p1, p2) -> {
            int compareStart = p1.getStartTime() - p2.getStartTime();
            return compareStart != 0 ? compareStart : p1.getName().compareTo(p2.getName());
        });

        // Reset animation
        animationProgress = 0.0f;
        
        // Assign random colors to new processes
        for (Process p : processes) {
            if (!processColors.containsKey(p.getName())) {
                processColors.put(p.getName(), new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            }
        }

        // Calculate total time
        this.totalTime = 0;
        for (Process p : processes) {
            this.totalTime = Math.max(this.totalTime, p.getEndTime());
        }

        // Start smooth animation
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                animationProgress += 0.015f;  // Slower animation
                if (animationProgress >= 1.0f) {
                    animationProgress = 1.0f;
                    timer.cancel();
                }
                repaint();
            }
        }, 0, 16);
    }

    private String formatTime(int time) {
        return String.format("t=%d", time);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (processes == null || processes.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int width = getWidth();
        int height = getHeight();
        int padding = (int) (width * PADDING_RATIO);
        int chartHeight = (int) (height * HEIGHT_RATIO);
        int y = padding;

        // Draw timeline
        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(padding, y + chartHeight + 20, width - padding, y + chartHeight + 20);

        // Calculate time step for markers
        int timeStep = calculateTimeStep(totalTime);

        // Draw time markers and grid lines
        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
        for (int t = 0; t <= totalTime; t += timeStep) {
            int x = padding + (int)((t * (width - 2 * padding)) / totalTime);
            // Grid line
            g2.setColor(new Color(240, 240, 240));
            g2.drawLine(x, padding, x, y + chartHeight);
            // Time marker
            g2.setColor(new Color(100, 100, 100));
            g2.drawString(formatTime(t), x - 15, y + chartHeight + 35);
        }

        // Draw process blocks
        for (Process p : processes) {
            Color baseColor = processColors.get(p.getName());
            Color lighterColor = createLighterColor(baseColor);
            
            // Draw each execution segment
            for (Process.ExecutionSegment segment : p.getExecutionSegments()) {
                int x1 = padding + (int)((segment.getStartTime() * (width - 2 * padding)) / totalTime * animationProgress);
                int x2 = padding + (int)((segment.getEndTime() * (width - 2 * padding)) / totalTime * animationProgress);
                int blockWidth = x2 - x1;

                // Draw shadow
                g2.setColor(new Color(0, 0, 0, 20));
                g2.fill(new RoundRectangle2D.Float(x1 + 3, y + 3, blockWidth, chartHeight, 15, 15));

                // Draw segment block with gradient
                GradientPaint gradient = new GradientPaint(
                    x1, y, baseColor,
                    x1, y + chartHeight, lighterColor
                );
                g2.setPaint(gradient);
                RoundRectangle2D.Float block = new RoundRectangle2D.Float(x1, y, blockWidth, chartHeight, 15, 15);
                g2.fill(block);

                // Draw border
                g2.setColor(baseColor.darker());
                g2.setStroke(new BasicStroke(1.5f));
                g2.draw(block);

                // Draw text if segment is wide enough
                String processText = p.getName();
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(processText);
                
                if (textWidth + 10 <= blockWidth) {
                    g2.setColor(Color.WHITE);
                    g2.drawString(processText, 
                        x1 + (blockWidth - textWidth) / 2,
                        y + chartHeight / 2 + fm.getAscent() / 2 - 2);
                }
            }

            // Draw process name above first segment if it exists
            if (!p.getExecutionSegments().isEmpty()) {
                Process.ExecutionSegment firstSegment = p.getExecutionSegments().get(0);
                int x1 = padding + (int)((firstSegment.getStartTime() * (width - 2 * padding)) / totalTime);
                g2.setColor(baseColor.darker());
                g2.drawString(p.getName(), x1, y - 5);
            }
        }
    }

    private Color createLighterColor(Color base) {
        float[] hsb = Color.RGBtoHSB(base.getRed(), base.getGreen(), base.getBlue(), null);
        return Color.getHSBColor(hsb[0], hsb[1] * 0.7f, Math.min(1.0f, hsb[2] * 1.3f));
    }

    private int calculateTimeStep(int totalTime) {
        if (totalTime <= 10) return 1;
        if (totalTime <= 20) return 2;
        if (totalTime <= 50) return 5;
        if (totalTime <= 100) return 10;
        return 20;
    }
}