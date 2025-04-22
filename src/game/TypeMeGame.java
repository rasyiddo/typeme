package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TypeMeGame {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Memainkan background music
        SoundPlayer sound = new SoundPlayer();
        sound.playSound("sounds/background.wav");

        // Create window
        JFrame window = new JFrame("TYPE ME - Java Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH); // Mode Fullscreen
        window.setUndecorated(false); // Bisa diset true jika mau borderless fullscreen

        // Menambahkan Panel Main Menu
        window.setContentPane(new MainMenuUI(window));
        window.setVisible(true);

        // Memastikan layout memenuhi fullscreen (revalidate after visible)
        SwingUtilities.invokeLater(() -> {
            window.revalidate();
            window.repaint();
        });

        // Musik stop saat ditutup
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sound.stopSound();
            }
        });
    }
}
