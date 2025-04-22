package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GameUI extends JPanel {
    private Level currentLevel;
    private Player player;
    private JLabel wordLabel;
    private JTextArea wordArea;
    private JTextField inputField;
    private JLabel scoreLabel;
    private JProgressBar progressBar;
    private List<String> historyLog = new ArrayList<>();
    private int totalMistakes = 0;
    private TimerManager timerManager;
    private final int maxTypos;

    public GameUI(Level level, Player player) {
        this.currentLevel = level;
        this.player = player;
        this.maxTypos = (level instanceof Level1) ? 3 : (level instanceof Level2) ? 5 : 10;

        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        wordLabel = new JLabel("", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 28));
        wordLabel.setForeground(Color.BLACK);
        wordLabel.setOpaque(true);
        wordLabel.setBackground(Color.WHITE);
        wordLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        wordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        wordArea = new JTextArea();
        wordArea.setFont(new Font("Arial", Font.BOLD, 22));
        wordArea.setForeground(Color.BLACK);
        wordArea.setBackground(Color.WHITE);
        wordArea.setLineWrap(true);
        wordArea.setWrapStyleWord(true);
        wordArea.setEditable(false);
        wordArea.setFocusable(false);
        wordArea.setMargin(new Insets(15, 15, 15, 15));
        wordArea.setMaximumSize(new Dimension(800, 100));
        wordArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setPreferredSize(new Dimension(800, 45));
        inputField.setMaximumSize(new Dimension(800, 45));
        inputField.setBackground(Color.WHITE);
        inputField.setForeground(Color.BLACK);
        inputField.setCaretColor(Color.BLACK);
        inputField.setMargin(new Insets(5, 15, 5, 15));
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputField.addActionListener(e -> handleInput());

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        progressBar = new JProgressBar(0, level.getWordCount());
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        timerManager = new TimerManager(level.getTimeLimit(), this::handleTimeUp);
        timerManager.startTimer();
        JLabel timerLabel = timerManager.getTimerLabel();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(30, 30, 30));

        centerPanel.add(Box.createVerticalStrut(60));
        if (level instanceof Level3) {
            centerPanel.add(wordArea);
        } else {
            centerPanel.add(wordLabel);
        }
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(inputField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(timerLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(progressBar);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(scoreLabel);

        add(centerPanel, BorderLayout.CENTER);

        displayNextQuestion();
    }

    private void handleInput() {
        String typed = inputField.getText();
        String target = currentLevel.getTargetWord();
        inputField.setText("");

        int mistakes = calculateLevenshteinDistance(typed, target);
        totalMistakes += mistakes;

        historyLog.add("Question: " + target + "\nInput: " + typed + "\nMistakes: " + mistakes);

        int correctChars = target.length() - mistakes;
        if (correctChars < 0) correctChars = 0;

        player.addScore(correctChars * 10);
        scoreLabel.setText("Score: " + player.getScore());

        inputField.setBackground(mistakes == 0 ? new Color(0, 255, 0, 100) : new Color(255, 0, 0, 100));
        Timer blinkTimer = new Timer(400, e -> inputField.setBackground(Color.WHITE));
        blinkTimer.setRepeats(false);
        blinkTimer.start();

        currentLevel.advanceWord();
        progressBar.setValue(currentLevel.getCurrentWordIndex());

        if (totalMistakes > maxTypos) {
            timerManager.stopTimer();
            showGameOverDialog("Game ended due to too many mistakes!");
        } else if (currentLevel.hasNextWord()) {
            displayNextQuestion();
        } else {
            timerManager.stopTimer();
            showSummaryDialog();
        }
    }

    private void displayNextQuestion() {
        String question = currentLevel.generateWord();
        currentLevel.setTargetWord(question);

        if (currentLevel instanceof Level3) {
            wordArea.setText(question);
        } else {
            wordLabel.setText(question);
        }
    }

    private void handleTimeUp() {
        timerManager.stopTimer();
        showGameOverDialog("Time is up! Game ended.");
    }

    private void showGameOverDialog(String reason) {
        Leaderboard.addScore(player.getName(), player.getScore());

        StringBuilder message = new StringBuilder();
        message.append("GAME OVER\n").append(reason).append("\n\n");
        message.append("Player: ").append(player.getName()).append("\n");
        message.append("Final Score: ").append(player.getScore()).append("\n");
        message.append("Total Mistakes: ").append(totalMistakes).append("\n\n");
        message.append("Mistake Details:\n\n");
        for (String log : historyLog) {
            message.append(log).append("\n\n");
        }

        String[] options = {"Play Again", "Back to Menu", "Exit"};
        int option = DialogFactory.showOptionDialog(this, "Game Over", message.toString(), options);

        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (option == 0) {
            topFrame.setContentPane(new GameUI(new Level1(5), new Player(player.getName())));
        } else if (option == 1) {
            topFrame.setContentPane(new MainMenuUI(topFrame));
        } else {
            System.exit(0);
        }

        topFrame.revalidate();
        topFrame.repaint();
    }

    private void showSummaryDialog() {
        // Dihapus: Leaderboard.addScore(...)
        StringBuilder message = new StringBuilder();
        message.append("You have completed this level!\n\n");
        message.append("Player: ").append(player.getName()).append("\n");
        message.append("Final Score: ").append(player.getScore()).append("\n");
        message.append("Total Mistakes: ").append(totalMistakes).append("\n\n");

        if (totalMistakes == 0) {
            message.append("\ud83c\udf89 Perfect! You completed this level with no mistakes! \ud83c\udf89\n\n");
        }

        message.append("Mistake Details:\n\n");
        for (String log : historyLog) {
            message.append(log).append("\n\n");
        }

        String[] options;
        if (currentLevel instanceof Level1) {
            options = new String[]{"Proceed to Level 2", "Back to Menu", "Exit"};
        } else if (currentLevel instanceof Level2) {
            options = new String[]{"Proceed to Level 3", "Back to Menu", "Exit"};
        } else {
            options = new String[]{"Play Again", "Back to Menu", "Exit"};
        }

        int option = DialogFactory.showOptionDialog(this, "Level Complete", message.toString(), options);

        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (currentLevel instanceof Level3) {
            if (option == 0) {
                topFrame.setContentPane(new GameUI(new Level1(5), new Player(player.getName())));
            } else if (option == 1) {
                topFrame.setContentPane(new MainMenuUI(topFrame));
            } else {
                System.exit(0);
            }
        } else {
            if (option == 0) {
                if (currentLevel instanceof Level1) {
                    topFrame.setContentPane(new GameUI(new Level2(5), player));
                } else if (currentLevel instanceof Level2) {
                    topFrame.setContentPane(new GameUI(new Level3(5), player));
                }
            } else if (option == 1 || option == JOptionPane.CLOSED_OPTION) {
                topFrame.setContentPane(new MainMenuUI(topFrame));
            } else {
                System.exit(0);
            }
        }

        topFrame.revalidate();
        topFrame.repaint();
    }

    private int calculateLevenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1])
                    );
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}
