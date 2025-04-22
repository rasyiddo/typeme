package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Level3 extends Level {
    private List<String> paragraphList;
    private int currentIndex = 0;

    public Level3(int wordCount) {
        super(wordCount);
        this.paragraphList = new ArrayList<>(Arrays.asList(
                "Java adalah bahasa OOP yang sangat umum digunakan mahasiswa. Class dan object adalah dasar dari Java. Semua program Java dimulai dari method main.",
                "Scanner digunakan untuk membaca input dari pengguna. Input bisa berupa angka, teks, atau boolean. Scanner berasal dari package java.util.",
                "Loop di Java mencakup for, while, dan do while untuk pengulangan. Loop membantu mengeksekusi kode berulang kali. Kondisi harus bernilai true.",
                "Array menyimpan data berurutan dalam indeks yang tetap. ArrayList menyimpan data fleksibel dan dinamis. Semua indeks dimulai dari angka nol.",
                "Komentar membantu menjelaskan potongan kode kepada pembaca. Komentar baris tunggal diawali dengan //. Komentar blok diawali dengan /* dan diakhiri */.",
                "Debugging membantu mahasiswa mencari kesalahan dalam program. IDE menyediakan fitur breakpoint dan console log. Ini penting untuk evaluasi tugas.",
                "Java menggunakan exception handling untuk error runtime. Try dan catch adalah blok dasar penanganan error. Tanpa ini program akan crash.",
                "Konstanta menyimpan nilai tetap yang tidak dapat diubah. Gunakan keyword final untuk mendeklarasikan konstanta. Contoh: final int MAX = 10;",
                "Mahasiswa membuat kalkulator saat belajar dasar Java. Program ini mencakup operasi + - * dan /. Logika ini sering digunakan di ujian praktik.",
                "Tipe data di Java mencakup int, double, char, dan boolean. Pemilihan tipe data mempengaruhi efisiensi. Tipe harus sesuai kebutuhan variabel.",
                "IDE seperti IntelliJ membantu mengetik, compile, dan debug. Fitur autocomplete sangat mempercepat proses coding. Banyak digunakan di kampus IT.",
                "Method menyimpan blok kode yang dapat digunakan berulang kali. Bisa static atau non-static tergantung konteks. Method bisa menerima parameter.",
                "Proyek akhir biasanya aplikasi GUI berbasis Java. Mahasiswa menyusun struktur file dan dokumentasi. Evaluasi dilihat dari logika dan UI.",
                "Inheritance memungkinkan pewarisan atribut dari class. Subclass mewarisi method dan variabel dari superclass. Ini mempercepat proses coding.",
                "Java dipakai untuk aplikasi desktop, mobile, dan enterprise. Java berjalan di banyak OS melalui JVM. Ini keunggulan dibanding bahasa lain."
        ));
        Collections.shuffle(this.paragraphList);
        this.currentIndex = 0;
    }

    @Override
    public String generateWord() {
        if (currentIndex < wordCount) {
            targetWord = paragraphList.get(currentIndex);
            return targetWord;
        } else {
            return "";
        }
    }

    @Override
    public String getLevelName() {
        return "Level 3";
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
        return 120; // 90 detik untuk Level 3
    }
}
