package ui;

import javax.swing.*;
import java.awt.*;

public class TeamMembersPanel extends JPanel {
    private JLabel teamName; // Label to display the team name
    private JList<String> teamMembersList; // List to display team members

    public TeamMembersPanel() {
        // Set the layout manager for this panel to BorderLayout
        setLayout(new BorderLayout());

        // Initialize the teamName label with text "Team Members:"
        teamName = new JLabel("Team Members:");
        // Set the font of the teamName label to Arial, bold, size 14
        teamName.setFont(new Font("Arial", Font.BOLD, 14));
        // Align the text of the teamName label to the left
        teamName.setHorizontalAlignment(SwingConstants.LEFT);

        // Array of team members' names
        String[] teamMembers = { "Ali Mohamed Abdelmoaty", "Ali Mohamed Abdelmoaty", "Ali Mohamed Abdelmoaty", "Ali Mohamed Abdelmoaty" };
        // Initialize the teamMembersList with the array of team members
        teamMembersList = new JList<>(teamMembers);
        // Set the font of the teamMembersList to Arial, plain, size 12
        teamMembersList.setFont(new Font("Arial", Font.PLAIN, 12));

        // Create a scroll pane to contain the teamMembersList
        JScrollPane scrollPane = new JScrollPane(teamMembersList);

        // Add the teamName label to the top (north) of the panel
        add(teamName, BorderLayout.NORTH);
        // Add the scroll pane (containing the teamMembersList) to the center of the panel
        add(scrollPane);
    }
}