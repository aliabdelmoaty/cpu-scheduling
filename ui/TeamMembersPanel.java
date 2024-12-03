package ui;

import javax.swing.*;
import java.awt.*;

public class TeamMembersPanel extends JPanel {
    private JLabel teamName;
    private JList<String> teamMembersList;

    public TeamMembersPanel() {
        setLayout(new BorderLayout()); 

        teamName = new JLabel("Team Members:");
        teamName.setFont(new Font("Arial", Font.BOLD, 14));
        teamName.setHorizontalAlignment(SwingConstants.LEFT);

        String[] teamMembers = { "Ali Mohamed Abdelmoaty", "Ali Mohamed Abdelmoaty", "Ali Mohamed Abdelmoaty", "Ali Mohamed Abdelmoaty" };
        teamMembersList = new JList<>(teamMembers);
        teamMembersList.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(teamMembersList);

        add(teamName, BorderLayout.NORTH);
        add(scrollPane);

    }
}
