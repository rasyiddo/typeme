package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogFactory {

    public static String showInputDialog(JFrame parent, String message) {
        JTextField inputField = new JTextField(30);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(new Color(0, 255, 255));
        inputField.setCaretColor(new Color(0, 255, 255));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255)));

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setForeground(new Color(0, 255, 255));
        messageLabel.setFont(new Font("Arial", Font.BOLD, 22));
        messageLabel.setPreferredSize(new Dimension(400, 40));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(10, 10));
        inputPanel.setBackground(new Color(44, 62, 80));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(messageLabel, BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);

        JButton cancelButton = createCustomButton("Cancel");
        JButton okButton = createCustomButton("OK");

        final JDialog dialog = new JDialog(parent, true);
        dialog.setUndecorated(true);
        dialog.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255)));

        final String[] result = new String[1];

        cancelButton.addActionListener(e -> {
            result[0] = null;
            dialog.dispose();
        });

        okButton.addActionListener(e -> {
            result[0] = inputField.getText();
            dialog.dispose();
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBackground(new Color(44, 62, 80));
        buttonPanel.add(cancelButton);
        buttonPanel.add(okButton);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(44, 62, 80));
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.getContentPane().add(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);

        return result[0];
    }

    public static int showOptionDialog(Component parent, String title, String message, String[] options) {
        JTextArea textArea = new JTextArea(message);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setForeground(new Color(0, 255, 255));
        textArea.setBackground(Color.BLACK);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(1, options.length, 10, 0));
        buttonPanel.setBackground(new Color(44, 62, 80));

        final int[] selected = {-1};

        for (int i = 0; i < options.length; i++) {
            String optionText = options[i];
            JButton button = createCustomButton(optionText);

            final int index = i;
            button.addActionListener(e -> {
                selected[0] = index;
                Window window = SwingUtilities.getWindowAncestor(button);
                window.dispose();
            });

            buttonPanel.add(button);
        }

        JLabel header = new JLabel(title, SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setForeground(new Color(0, 255, 255));
        header.setOpaque(true);
        header.setBackground(new Color(44, 62, 80));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(new Color(44, 62, 80));
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(textArea, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), true);
        dialog.setUndecorated(true);
        dialog.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255)));
        dialog.setContentPane(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);

        return selected[0];
    }

    public static void showScrollableDialog(Component parent, String title, String content) {
        JTextArea textArea = new JTextArea(content);
        textArea.setFont(new Font("Courier New", Font.BOLD, 20));
        textArea.setForeground(new Color(0, 255, 255));
        textArea.setBackground(Color.BLACK);
        textArea.setEditable(false);
        textArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JButton closeButton = createCustomButton("Close");
        closeButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(closeButton);
            window.dispose();
        });

        JLabel header = new JLabel(title, SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setForeground(new Color(0, 255, 255));
        header.setOpaque(true);
        header.setBackground(new Color(44, 62, 80));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(44, 62, 80));
        buttonPanel.setLayout(new GridLayout(1, 1));
        buttonPanel.add(closeButton);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(new Color(44, 62, 80));
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), true);
        dialog.setUndecorated(true);
        dialog.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255)));
        dialog.setContentPane(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private static JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(new Color(0, 255, 255));
        button.setBackground(new Color(44, 62, 80));
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255)));
        return button;
    }
}