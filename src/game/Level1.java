package game;

import java.util.*;

public class Level1 extends Level {
    private List<String> wordList;
    private int currentIndex;

    public Level1(int wordCount) {
        super(wordCount);
        this.wordList = new ArrayList<>(Arrays.asList(
                "password", "keyboard", "function", "variable", "computer",
                "hardware", "software", "database", "operator", "compiler",
                "bytecode", "terminal", "platform", "protocol", "internet",
                "language", "tutorial", "security", "markdown", "iterator",
                "instance", "practice", "debugger", "solution", "learning",
                "resource", "download", "packages", "comments", "keywords",
                "encoding", "analyzer", "observer", "pemroses", "komponen",
                "ekstensi", "variabel", "pemindai", "terkecil"
        ));
        Collections.shuffle(this.wordList);
        this.currentIndex = 0;
    }

    @Override
    public String generateWord() {
        if (currentIndex < wordCount) {
            return wordList.get(currentIndex);
        } else {
            return "";
        }
    }

    @Override
    public String getLevelName() {
        return "Level 1";
    }

    @Override
    public boolean hasNextWord() {
        return currentIndex < wordCount;
    }

    @Override
    public void advanceWord() {
        currentIndex++;
    }

    @Override
    public int getTimeLimit() {
        return 20; // 20 detik untuk Level 1
    }
}
