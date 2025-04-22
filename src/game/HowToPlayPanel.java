package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HowToPlayPanel extends JPanel {

    private final String[] instructions = {
            "1. You will be given words or sentences to type as shown",
            "2. Type them exactly as displayed in the input field",
            "3. Each correct character gives you 10 points",
            "4. If your input contains typos, it will count as mistakes",
            "5. You have limited time and a maximum number of allowed mistakes",
            "",
            "Level System:",
            "- Level 1: Random words, 3 typo limit, 20 seconds",
            "- Level 2: Short sentences, 5 typo limit, 30 seconds",
            "- Level 3: Paragraphs, 10 typo limit, 90 seconds",
            "",
            "Game Over Conditions:",
            "- If you exceed the maximum number of mistakes",
            "- Or if you run out of time before completing all items",
            "",
            "Good luck and have fun typing!"
    };

    public HowToPlayPanel(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // ===== Title =====
        JLabel titleLabel = new JLabel("HOW TO PLAY", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.CYAN);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // ===== Content =====
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(30, 30, 30));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 30, 100));

        contentPanel.add(Box.createVerticalStrut(40)); // spacing ke bawah

        for (String line : instructions) {
            JLabel label = new JLabel(line, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setForeground(new Color(0, 255, 255));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            contentPanel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(30, 30, 30));
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // ===== Back Button =====
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(new Color(0, 255, 255));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255)));
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(240, 50));
        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(new MainMenuUI(frame));
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLocationRelativeTo(null);
            frame.revalidate();
            frame.repaint();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        bottomPanel.add(backButton);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
