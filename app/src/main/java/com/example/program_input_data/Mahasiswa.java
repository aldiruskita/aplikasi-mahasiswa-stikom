package com.example.program_input_data;

public class Mahasiswa {
    private int id;
    private String nama;
    private String nim;
    private String prodi;
    private int semester;
    private double nilaiPemrograman;
    private double nilaiBasisData;
    private double nilaiJaringan;
    private double rataRata;

    // Constructor
    public Mahasiswa() {
    }

    public Mahasiswa(int id, String nama, String nim, String prodi, int semester,
                     double nilaiPemrograman, double nilaiBasisData, double nilaiJaringan) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
        this.semester = semester;
        this.nilaiPemrograman = nilaiPemrograman;
        this.nilaiBasisData = nilaiBasisData;
        this.nilaiJaringan = nilaiJaringan;
        this.rataRata = (nilaiPemrograman + nilaiBasisData + nilaiJaringan) / 3;
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

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getNilaiPemrograman() {
        return nilaiPemrograman;
    }

    public void setNilaiPemrograman(double nilaiPemrograman) {
        this.nilaiPemrograman = nilaiPemrograman;
        hitungRataRata();
    }

    public double getNilaiBasisData() {
        return nilaiBasisData;
    }

    public void setNilaiBasisData(double nilaiBasisData) {
        this.nilaiBasisData = nilaiBasisData;
        hitungRataRata();
    }

    public double getNilaiJaringan() {
        return nilaiJaringan;
    }

    public void setNilaiJaringan(double nilaiJaringan) {
        this.nilaiJaringan = nilaiJaringan;
        hitungRataRata();
    }

    public double getRataRata() {
        return rataRata;
    }

    private void hitungRataRata() {
        this.rataRata = (nilaiPemrograman + nilaiBasisData + nilaiJaringan) / 3;
    }
}

