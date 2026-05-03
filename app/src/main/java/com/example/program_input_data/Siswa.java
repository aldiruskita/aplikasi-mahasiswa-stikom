package com.example.program_input_data;

public class Siswa {
    private int id;
    private String nama;
    private String nis;
    private String kelas;
    private int semester;
    private double nilaiMatematika;
    private double nilaiBahasaIndonesia;
    private double nilaiBahasaInggris;
    private double rataRata;

    // Constructor
    public Siswa() {
    }

    public Siswa(int id, String nama, String nis, String kelas, int semester,
                 double nilaiMatematika, double nilaiBahasaIndonesia, double nilaiBahasaInggris) {
        this.id = id;
        this.nama = nama;
        this.nis = nis;
        this.kelas = kelas;
        this.semester = semester;
        this.nilaiMatematika = nilaiMatematika;
        this.nilaiBahasaIndonesia = nilaiBahasaIndonesia;
        this.nilaiBahasaInggris = nilaiBahasaInggris;
        this.rataRata = (nilaiMatematika + nilaiBahasaIndonesia + nilaiBahasaInggris) / 3;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getNilaiMatematika() {
        return nilaiMatematika;
    }

    public void setNilaiMatematika(double nilaiMatematika) {
        this.nilaiMatematika = nilaiMatematika;
        hitungRataRata();
    }

    public double getNilaiBahasaIndonesia() {
        return nilaiBahasaIndonesia;
    }

    public void setNilaiBahasaIndonesia(double nilaiBahasaIndonesia) {
        this.nilaiBahasaIndonesia = nilaiBahasaIndonesia;
        hitungRataRata();
    }

    public double getNilaiBahasaInggris() {
        return nilaiBahasaInggris;
    }

    public void setNilaiBahasaInggris(double nilaiBahasaInggris) {
        this.nilaiBahasaInggris = nilaiBahasaInggris;
        hitungRataRata();
    }

    public double getRataRata() {
        return rataRata;
    }

    private void hitungRataRata() {
        this.rataRata = (nilaiMatematika + nilaiBahasaIndonesia + nilaiBahasaInggris) / 3;
    }
}
