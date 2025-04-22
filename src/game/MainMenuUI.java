package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuUI extends JPanel {
    private JLabel titleLabel;
    private Timer animationTimer;
    private int yOffset = 0;
    private int direction = 1;
    private final boolean useTypingEffect = true;

    public MainMenuUI(JFrame frame) {
        setBackground(new Color(20, 20, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(40));

        JPanel titleWrapper = new JPanel(null);
        titleWrapper.setPreferredSize(new Dimension(800, 120));
        titleWrapper.setMaximumSize(new Dimension(800, 120));
        titleWrapper.setBackground(new Color(20, 20, 20));

        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.CYAN);
        titleLabel.setSize(350, 60);
        titleLabel.setLocation((800 - 350) / 2, 30);
        titleWrapper.add(titleLabel);
        add(titleWrapper);

        if (useTypingEffect) {
            String fullTitle = "TYPE ME";
            StringBuilder displayed = new StringBuilder();
            Timer typingTimer = new Timer(300, null);
            typingTimer.addActionListener(new ActionListener() {
                int index = 0;
                public void actionPerformed(ActionEvent e) {
                    if (index < fullTitle.length()) {
                        displayed.append(fullTitle.charAt(index++));
                        titleLabel.setText(displayed.toString());
                    } else {
                        ((Timer) e.getSource()).stop();
                        startBounceAnimation();
                    }
                }
            });
            typingTimer.start();
        } else {
            titleLabel.setText("TYPE ME");
            startBounceAnimation();
        }

        add(Box.createVerticalStrut(60));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(20, 20, 20));
        buttonPanel.setMaximumSize(new Dimension(300, 350));

        String[] buttonTexts = {
                "How to Play", "Start Game", "Leaderboard", "About the Game", "Credit"
        };

        for (String text : buttonTexts) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setPreferredSize(new Dimension(300, 45));
            button.setMaximumSize(new Dimension(300, 45));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFocusPainted(false);
            button.setBackground(new Color(30, 30, 30));
            button.setForeground(new Color(0, 255, 255));
            button.setBorder(BorderFactory.createLineBorder(Color.CYAN));
            buttonPanel.add(button);
            buttonPanel.add(Box.createVerticalStrut(15));

            button.addActionListener(e -> {
                if (animationTimer != null) animationTimer.stop();
                switch (text) {
                    case "Start Game":
                        String name = DialogFactory.showInputDialog(frame, "Enter your name:");
                        if (name != null && !name.trim().isEmpty()) {
                            Player player = new Player(name.trim());
                            frame.setContentPane(new GameUI(new Level1(5), player));
                        } else {
                            DialogFactory.showOptionDialog(this, "Warning", "Name cannot be empty!", new String[]{"OK"});
                            if (animationTimer != null) animationTimer.start();
                            return;
                        }
                        break;
                    case "How to Play":
                        frame.setContentPane(new HowToPlayPanel(frame));
                        break;
                    case "About the Game":
                        frame.setContentPane(new AboutPanel(frame));
                        break;
                    case "Credit":
                        frame.setContentPane(new CreditPanel(frame));
                        break;
                    case "Leaderboard":
                        String leaderboardText = Leaderboard.getFormattedLeaderboard();
                        DialogFactory.showScrollableDialog(frame, "LEADERBOARD", leaderboardText);
                        if (animationTimer != null) animationTimer.start();
                        return;
                }
                frame.revalidate();
                frame.repaint();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            });
        }

        add(buttonPanel);
        add(Box.createVerticalStrut(40));

        // --- Shortcut baru: CTRL + SHIFT + L untuk reset leaderboard
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK),
                "resetLeaderboard"
        );
        getActionMap().put("resetLeaderboard", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = DialogFactory.showOptionDialog(
                        frame,
                        "Reset Leaderboard",
                        "Are you sure you want to clear the leaderboard? This cannot be undone.",
                        new String[]{"Yes", "Cancel"}
                );
                if (confirm == 0) {
                    Leaderboard.clear();
                    DialogFactory.showOptionDialog(frame, "Info", "Leaderboard has been cleared.", new String[]{"OK"});
                }
            }
        });
    }

    private void startBounceAnimation() {
        animationTimer = new Timer(50, e -> {
            yOffset += direction;
            if (yOffset >= 20 || yOffset <= -20) {
                direction *= -1;
            }
            titleLabel.setLocation((800 - 350) / 2, 30 + yOffset);
        });
        animationTimer.start();
    }
}
