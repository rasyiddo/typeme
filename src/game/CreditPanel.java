package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreditPanel extends JPanel {
    private final String[] creditLines = {
            "Our sincere gratitude to",
            "",
            "our Java Programming Lecturer:",
            "Irene A. Lazarusli, S.Kom, MT.",
            "for her guidance throughout our journey",
            "",
            "Java Programming Lab Assistant:",
            "Kelvin Wiriyatama",
            "who made everything possible for us",
            "",
            "Developed as part of the Java Programming Course Final Project",
            "at Universitas Pelita Harapan - Lippo Village, Class of 2024."
    };

    private Timer scrollTimer;

    public CreditPanel(JFrame frame) {
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(new Color(0, 255, 255));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255)));
        backButton.setFocusPainted(false);
        add(backButton);

        JPanel clipPanel = new JPanel(null);
        clipPanel.setBackground(new Color(30, 30, 30));
        add(clipPanel);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(30, 30, 30));

        for (String line : creditLines) {
            JLabel label = new JLabel(line, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setForeground(new Color(0, 255, 255));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            textPanel.add(label);
        }

        clipPanel.add(textPanel);

        SwingUtilities.invokeLater(() -> {
            int width = frame.getWidth();
            int height = frame.getHeight();

            int buttonWidth = 240;
            int buttonHeight = 50;
            int buttonX = (width - buttonWidth) / 2;
            int buttonY = height - 80;
            backButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

            int clipHeight = buttonY - 40;
            clipPanel.setBounds(0, 0, width, clipHeight);
            clipPanel.setPreferredSize(new Dimension(width, clipHeight));
            clipPanel.setLayout(null);

            Dimension textSize = textPanel.getPreferredSize();
            textPanel.setSize(textSize);
            textPanel.setLocation((width - textSize.width) / 2, clipHeight);

            clipPanel.revalidate();
            clipPanel.repaint();

            scrollTimer = new Timer(20, e -> {
                int y = textPanel.getY() - 1;
                if (y + textSize.height < 0) {
                    y = clipHeight;
                }
                textPanel.setLocation(textPanel.getX(), y);
            });
            scrollTimer.start();
        });

        backButton.addActionListener((ActionEvent e) -> {
            if (scrollTimer != null) scrollTimer.stop();
            frame.setContentPane(new MainMenuUI(frame));
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLocationRelativeTo(null);
            frame.revalidate();
            frame.repaint();
        });
    }
}
