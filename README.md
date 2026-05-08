# 📚 Aplikasi Data Siswa

## 👨‍💻 Identitas
- **Nama:** Made Aldi Ruskita Salahin  
- **NIM:** 230040070  

Aplikasi Android untuk mengelola data siswa menggunakan database SQLite.  
Aplikasi ini mendukung pengelolaan data siswa secara lengkap dengan tampilan modern dan mudah digunakan.

---

# ✨ Fitur Utama

## 🗄️ Kelola Data dengan SQLite
- Menggunakan SQLite sebagai database lokal
- Menyimpan data secara permanen
- Mendukung operasi CRUD:
  - Create (Tambah Data)
  - Read (Lihat Data)
  - Update (Edit Data)
  - Delete (Hapus Data)

---

## 📋 Menampilkan Data di List
- Menggunakan RecyclerView
- Desain CardView modern
- Menampilkan:
  - Nama Siswa
  - NIS
  - Kelas
  - Rata-rata Nilai

### 🎨 Indikator Warna Nilai
| Warna | Keterangan | Nilai |
|------|-------------|--------|
| 🟢 Hijau | Sangat Baik | ≥ 80 |
| 🟠 Orange | Baik | 60 - 79 |
| 🔴 Merah | Perlu Perbaikan | < 60 |

---

## 📊 Menyimpan Nilai dalam Database
- Nilai tersimpan permanen di SQLite
- Perhitungan rata-rata otomatis
- Validasi nilai:
  - Minimum 0
  - Maksimum 100

---

# 🛠️ Teknologi yang Digunakan
- Android Studio
- Java / Kotlin
- SQLite Database
- RecyclerView
- CardView
- Material Design

---

# 📂 Struktur Data Siswa

| Field | Tipe Data |
|------|------------|
| ID | Integer |
| Nama | Text |
| NIS | Text |
| Kelas | Text |
| Nilai Tugas | Integer |
| Nilai UTS | Integer |
| Nilai UAS | Integer |
| Rata-rata | Double |

---

# 🚀 Cara Menjalankan Aplikasi

```bash
1. Clone atau download project
2. Buka project di Android Studio
3. Tunggu proses Gradle selesai
4. Jalankan aplikasi menggunakan emulator atau HP Android
```

---

# 📁 Struktur Project

```bash
app/
├── java/com/example/datasiswa/
│   ├── MainActivity.java
│   ├── DatabaseHelper.java
│   ├── SiswaAdapter.java
│   ├── TambahSiswaActivity.java
│   └── EditSiswaActivity.java
│
├── res/layout/
│   ├── activity_main.xml
│   ├── item_siswa.xml
│   ├── activity_tambah_siswa.xml
│   └── activity_edit_siswa.xml
```

---

# 📱 Tampilan Aplikasi
- Halaman daftar siswa
- Tambah data siswa
- Edit data siswa
- Hapus data siswa
- Detail nilai siswa
- Tampilan responsif dan menarik

---

# 🎯 Tujuan Aplikasi
Aplikasi ini dibuat untuk:
- Mempermudah pengelolaan data siswa
- Mengurangi pencatatan manual
- Menyimpan data secara aman
- Membantu monitoring nilai siswa

---

# 👨‍💻 Developer
**Made Aldi Ruskita Salahin**  
NIM: 230040070
