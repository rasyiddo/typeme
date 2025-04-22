package game;

public abstract class Level {

    protected String targetWord;
    protected int wordCount;
    protected int currentWordIndex = 0;

    public Level(int wordCount) {
        this.wordCount = wordCount;
    }

    public abstract String generateWord();

    public abstract String getLevelName();

    //  Tambahan baru: waktu maksimum (dalam detik) untuk setiap level
    public abstract int getTimeLimit();

    public int calculateScore(String userInput) {
        int score = 0;
        for (int i = 0; i < userInput.length() && i < targetWord.length(); i++) {
            if (userInput.charAt(i) == targetWord.charAt(i)) {
                score += 10;
            } else {
                break;
            }
        }
        return score;
    }

    public boolean isInputCorrect(String userInput) {
        return userInput.equals(targetWord);
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setTargetWord(String word) {
        this.targetWord = word;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public boolean hasNextWord() {
        return currentWordIndex < wordCount;
    }

    public void advanceWord() {
        currentWordIndex++;
    }

    public int getCurrentWordIndex() {
        return currentWordIndex;
    }
}
