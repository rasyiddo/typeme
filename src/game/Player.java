package game;

public class Player {
    private String name;
    private int score;

    // Konstruktor default (jika tidak diberi nama, default "Guest")
    public Player() {
        this.name = "Guest";
        this.score = 0;
    }

    // Konstruktor untuk nama saja (digunakan saat awal main)
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    // Konstruktor untuk leaderboard (nama + skor)
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Tambahkan skor
    public void addScore(int value) {
        this.score += value;
    }

    // Ambil skor
    public int getScore() {
        return score;
    }

    // Ambil nama pemain
    public String getName() {
        return name;
    }

    // Set nama (jika dibutuhkan)
    public void setName(String name) {
        this.name = name;
    }

    // Set skor (jika dibutuhkan)
    public void setScore(int score) {
        this.score = score;
    }
}
