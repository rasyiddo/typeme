package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerManager {
    private int duration;
    private int remainingSeconds;
    private Timer timer;
    private JLabel timerLabel;
    private Runnable onTimeUpCallback;

    public TimerManager(int durationInSeconds, Runnable onTimeUpCallback) {
        this.duration = durationInSeconds;
        this.remainingSeconds = durationInSeconds;
        this.onTimeUpCallback = onTimeUpCallback;

        timerLabel = new JLabel(formatTime(remainingSeconds));
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 48)); // 3x lebih besar
        timerLabel.setForeground(new Color(0, 255, 255));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingSeconds--;
                timerLabel.setText(formatTime(remainingSeconds));

                if (remainingSeconds <= 0) {
                    stopTimer();
                    if (onTimeUpCallback != null) {
                        onTimeUpCallback.run();
                    }
                }
            }
        });
        timer.start();
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }

    private String formatTime(int seconds) {
        int mins = seconds / 60;
        int secs = seconds % 60;
        return String.format("⏱ %02d:%02d", mins, secs);
    }
}
