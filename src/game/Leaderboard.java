package game;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Leaderboard {

    private static final String FILE_NAME = "leaderboard.txt";
    private static final int MAX_ENTRIES = 10;

    public static void addScore(String name, int score) {
        List<LeaderboardEntry> entries = loadScores();
        entries.add(new LeaderboardEntry(name, score));
        entries.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

        if (entries.size() > MAX_ENTRIES) {
            entries = entries.subList(0, MAX_ENTRIES);
        }

        saveScores(entries);
    }

    public static String getFormattedLeaderboard() {
        List<LeaderboardEntry> entries = loadScores();
        StringBuilder sb = new StringBuilder();
        int rank = 1;
        for (LeaderboardEntry entry : entries) {
            sb.append(rank++).append(". ")
                    .append(entry.getName())
                    .append(" - ")
                    .append(entry.getScore())
                    .append("\n");
        }
        return sb.toString();
    }

    private static List<LeaderboardEntry> loadScores() {
        List<LeaderboardEntry> entries = new ArrayList<>();

        try {
            Path path = Paths.get(FILE_NAME);
            if (!Files.exists(path)) return entries;

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    entries.add(new LeaderboardEntry(name, score));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Failed to load leaderboard: " + e.getMessage());
        }

        return entries;
    }

    private static void saveScores(List<LeaderboardEntry> entries) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (LeaderboardEntry entry : entries) {
                writer.println(entry.getName() + "," + entry.getScore());
            }
        } catch (IOException e) {
            System.err.println("Failed to save leaderboard: " + e.getMessage());
        }
    }

    public static void clear() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // Kosongkan isi file
        } catch (IOException e) {
            System.err.println("Failed to clear leaderboard: " + e.getMessage());
        }
    }

    private static class LeaderboardEntry {
        private final String name;
        private int score;

        public LeaderboardEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() { return name; }
        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }
    }
}
