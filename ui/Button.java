package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class Button extends JButton {
    // Default values
    private static final Color DEFAULT_BG_COLOR = new Color(00000000);  
    private static final Color DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final int DEFAULT_RADIUS = 20;
    private static final Color SHADOW_COLOR = Color.gray;
    private static final int SHADOW_SIZE = 20;

    // Flag to track whether the button is pressed or not
    private boolean isPressed = false;

    public Button() {
    }

    // Constructor taking the text
    public Button(String text) {
        // Call the other constructor with default colors
        this(text, DEFAULT_BG_COLOR, DEFAULT_TEXT_COLOR);

        // Add an ActionListener to handle button clicks
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle the flag when the button is clicked
                isPressed = !isPressed;
                repaint(); // Repaint the button to apply changes

                // If the button is pressed, start a timer to reset the pressed state after a
                // delay
                if (isPressed) {
                    Timer timer = new Timer(50, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            isPressed = false;
                            ((Timer) e.getSource()).stop();
                        }
                    });
                    timer.start();
                }
            }
        });
    }

    // Constructor taking the text and the color of the button
    private Button(String text, Color bgColor, Color textColor) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 12));
        setContentAreaFilled(true);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setBackground(bgColor);
        setForeground(textColor);
    }

    // To change the shape of the button
    @Override
    protected void paintComponent(Graphics g) {
        // Create a Graphics2D object for more advanced graphics operations
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw shadow only if the button is not pressed
        if (!isPressed) {
            g2.setColor(SHADOW_COLOR);
            g2.fillRoundRect(SHADOW_SIZE, SHADOW_SIZE, width - SHADOW_SIZE, height - SHADOW_SIZE, DEFAULT_RADIUS,
                    DEFAULT_RADIUS);
        }

        // Create a rounded rectangle for the button shape
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, DEFAULT_RADIUS,
                DEFAULT_RADIUS);

        // Fill the button background
        g2.setColor(getBackground());
        g2.fill(roundedRectangle);

        // Draw inner shadow if the button is not pressed
        if (!isPressed) {
            g2.fillRoundRect(0, 0, width - SHADOW_SIZE, height - SHADOW_SIZE, DEFAULT_RADIUS, DEFAULT_RADIUS);
        }

        // Set content area filled to false to allow custom painting
        setContentAreaFilled(false);

        // Call the super class to paint the text and other components
        super.paintComponent(g);
    }

   
}