package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Level2 extends Level {
    private List<String> sentenceList;
    private int currentIndex = 0;

    public Level2(int wordCount) {
        super(wordCount);
        this.sentenceList = new ArrayList<>(Arrays.asList(
                "Program Java dimulai dari method main sebagai titik awal.",
                "Loop digunakan untuk mengulang proses hingga kondisi terpenuhi.",
                "Variabel menyimpan nilai yang bisa berubah selama eksekusi.",
                "Scanner digunakan untuk membaca input dari pengguna aplikasi.",
                "Class adalah cetakan dari object dalam paradigma OOP Java.",
                "Debugging penting untuk menemukan dan memperbaiki kesalahan.",
                "Komentar dipakai menjelaskan maksud dari potongan kode program.",
                "Exception ditangani menggunakan try dan catch di dalam blok.",
                "Tipe data boolean menyimpan nilai true atau false dalam Java.",
                "IDE seperti IntelliJ mempermudah saat menulis kode program.",
                "Kompilasi mengubah kode Java menjadi bytecode untuk JVM.",
                "Array menyimpan data sejenis dalam satu struktur berurutan.",
                "Library Java menyediakan fungsi umum yang sering digunakan.",
                "Objek dibuat dari class dengan keyword new dalam Java.",
                "Konstanta adalah nilai tetap yang tidak dapat diubah kembali.",
                "Class bisa memiliki method, field, dan konstruktor tersendiri.",
                "If else digunakan untuk mengatur logika percabangan program.",
                "Java adalah bahasa pemrograman berorientasi objek modern.",
                "Program modular memudahkan pengujian dan pengembangan kode.",
                "Field menyimpan data internal dari sebuah object di Java.",
                "Looping while akan berjalan selama kondisi masih terpenuhi.",
                "Inheritance memungkinkan class mewarisi sifat class lain.",
                "Mahasiswa belajar Java dalam praktikum tiap minggunya.",
                "Static method bisa dipanggil tanpa membuat objek terlebih dulu.",
                "Operator aritmatika seperti + dan - digunakan dalam kalkulasi.",
                "ArrayList menyimpan data dinamis lebih fleksibel dari array.",
                "IDE membantu mahasiswa menyelesaikan tugas Java lebih mudah.",
                "Syntax error terjadi jika struktur kode tidak sesuai aturan.",
                "Mahasiswa membuat kalkulator sederhana saat belajar Java.",
                "Java mendukung pemrograman desktop, mobile, dan juga web.",
                "While loop cocok saat kondisi harus diperiksa sebelum berjalan.",
                "Komentar satu baris diawali dengan dua garis miring di Java.",
                "Konsep OOP mencakup inheritance, encapsulation, dan polymorph.",
                "File Java harus disimpan dengan nama class public di dalamnya.",
                "Praktikum Java membantu memahami teori lewat latihan langsung."
        ));
        Collections.shuffle(this.sentenceList);
        this.currentIndex = 0;
    }

    @Override
    public String generateWord() {
        if (currentIndex < wordCount) {
            targetWord = sentenceList.get(currentIndex);
            return targetWord;
        } else {
            return "";
        }
    }

    @Override
    public String getLevelName() {
        return "Level 2";
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
        return 90; // 30 detik untuk Level 2
    }
}
