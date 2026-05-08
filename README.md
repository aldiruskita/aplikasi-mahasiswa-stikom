# 📱 Aplikasi Data Mahasiswa ITB STIKOM Bali

<div align="center">
  <img src="app/src/main/res/drawable/logo_stikom.png" alt="Logo ITB STIKOM Bali" width="200"/>
  
  ### Sistem Manajemen Data Mahasiswa Berbasis Android
  
  [![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
  [![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)
  [![SQLite](https://img.shields.io/badge/Database-SQLite-blue.svg)](https://www.sqlite.org/)
  [![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
</div>

---

## 👨‍🎓 Informasi Pengembang

**Nama:** Made Aldi Ruskita Salahin  
**NIM:** 230040070  
**Institusi:** ITB STIKOM Bali  
**Program Studi:** Teknik Informatika

---

## 📖 Deskripsi

Aplikasi **Data Mahasiswa ITB STIKOM Bali** adalah aplikasi Android native yang dirancang untuk mengelola data mahasiswa dengan mudah dan efisien. Aplikasi ini menggunakan database SQLite untuk penyimpanan data lokal dan menyediakan antarmuka yang user-friendly untuk operasi CRUD (Create, Read, Update, Delete).

### ✨ Fitur Utama

- 📝 **Input Data Mahasiswa** - Tambah data mahasiswa baru dengan validasi lengkap
- 📋 **Tampilan List** - Lihat semua data mahasiswa dalam RecyclerView yang responsif
- ✏️ **Edit Data** - Update informasi mahasiswa yang sudah ada
- 🗑️ **Hapus Data** - Hapus data mahasiswa dengan konfirmasi
- 📊 **Statistik Real-time** - Lihat total mahasiswa dan rata-rata nilai
- 🎨 **Badge Warna** - Indikator visual berdasarkan performa nilai
- 🔍 **Detail View** - Lihat informasi lengkap mahasiswa

---

## 🎯 Fitur Lengkap

### 1. Manajemen Data Mahasiswa
- Input data: Nama, NIM, Program Studi, Semester
- Input nilai: Pemrograman, Basis Data, Jaringan Komputer
- Perhitungan rata-rata nilai otomatis
- Validasi input untuk memastikan data valid

### 2. Validasi Input
- ✅ Semua field wajib diisi
- ✅ Nilai harus dalam range 0-100
- ✅ Semester harus antara 1-14
- ✅ NIM harus unik (tidak boleh duplikat)
- ✅ NIM tidak dapat diubah setelah data dibuat

### 3. Sistem Badge Warna
| Warna | Range Nilai | Kategori |
|-------|-------------|----------|
| 🟢 Hijau | ≥ 80 | Excellent |
| 🟠 Orange | 60 - 79 | Good |
| 🔴 Merah | < 60 | Poor |

### 4. Statistik Dashboard
- **Total Mahasiswa** - Jumlah total data mahasiswa
- **Rata-rata Nilai** - Rata-rata nilai keseluruhan mahasiswa

---

## 🛠️ Teknologi yang Digunakan

| Teknologi | Deskripsi |
|-----------|-----------|
| **Java** | Bahasa pemrograman utama |
| **Android SDK** | Framework pengembangan Android |
| **SQLite** | Database lokal untuk penyimpanan data |
| **RecyclerView** | Menampilkan list data dengan efisien |
| **CardView** | Desain card untuk item list |
| **Material Design** | Komponen UI modern dan responsif |
| **AlertDialog** | Dialog untuk input dan konfirmasi |

---

## 📦 Struktur Database

### Tabel: `mahasiswa`

| Kolom | Tipe Data | Constraint | Deskripsi |
|-------|-----------|------------|-----------|
| `id` | INTEGER | PRIMARY KEY, AUTOINCREMENT | ID unik mahasiswa |
| `nama` | TEXT | NOT NULL | Nama lengkap mahasiswa |
| `nim` | TEXT | NOT NULL, UNIQUE | Nomor Induk Mahasiswa |
| `prodi` | TEXT | NOT NULL | Program Studi |
| `semester` | INTEGER | NOT NULL | Semester saat ini (1-14) |
| `nilai_pemrograman` | REAL | NOT NULL | Nilai mata kuliah Pemrograman (0-100) |
| `nilai_basis_data` | REAL | NOT NULL | Nilai mata kuliah Basis Data (0-100) |
| `nilai_jaringan` | REAL | NOT NULL | Nilai mata kuliah Jaringan Komputer (0-100) |

**Database Name:** `mahasiswa_database.db`  
**Database Version:** 1

---

## 📱 Screenshot Aplikasi

### Tampilan Utama
- Header dengan logo ITB STIKOM Bali
- Statistik dashboard (Total Mahasiswa & Rata-rata Nilai)
- List mahasiswa dengan badge warna
- Floating Action Button untuk tambah data

### Dialog Input/Edit
- Form input dengan Material Design
- Validasi real-time
- Hint text yang jelas

### Dialog Detail
- Informasi lengkap mahasiswa
- Nilai per mata kuliah
- Rata-rata nilai dengan warna indikator

---

## 🚀 Cara Instalasi

### Prasyarat
- Android Studio (versi terbaru)
- JDK 8 atau lebih tinggi
- Android SDK API Level 21 (Lollipop) atau lebih tinggi
- Gradle 9.3.1 atau lebih tinggi

### Langkah Instalasi

1. **Clone Repository**
   ```bash
   git clone https://github.com/aldiruskita/aplikasi-mahasiswa-stikom.git
   cd aplikasi-mahasiswa-stikom
   ```

2. **Buka di Android Studio**
   - Buka Android Studio
   - Pilih "Open an Existing Project"
   - Navigasi ke folder project dan klik OK

3. **Sync Gradle**
   - Android Studio akan otomatis sync Gradle
   - Tunggu hingga proses selesai

4. **Build Project**
   ```bash
   ./gradlew assembleDebug
   ```

5. **Install di Device/Emulator**
   - Hubungkan device Android atau jalankan emulator
   - Klik tombol "Run" di Android Studio
   - Atau install manual APK dari `app/build/outputs/apk/debug/app-debug.apk`

---

## 💻 Cara Menggunakan

### 1. Menambah Data Mahasiswa
1. Klik tombol **+** (Floating Action Button) di pojok kanan bawah
2. Isi semua field yang tersedia:
   - Nama Lengkap
   - NIM (Nomor Induk Mahasiswa)
   - Program Studi
   - Semester (1-14)
   - Nilai Pemrograman (0-100)
   - Nilai Basis Data (0-100)
   - Nilai Jaringan Komputer (0-100)
3. Klik tombol **Simpan**
4. Data akan muncul di list dengan badge warna sesuai rata-rata nilai

### 2. Melihat Detail Mahasiswa
1. Klik pada card mahasiswa di list
2. Dialog detail akan muncul menampilkan semua informasi
3. Klik **Tutup** untuk kembali ke list

### 3. Mengedit Data Mahasiswa
1. Klik tombol **Edit** (ikon pensil) pada card mahasiswa
2. Ubah data yang ingin diubah (kecuali NIM)
3. Klik tombol **Update**
4. Data akan diperbarui di database dan list

### 4. Menghapus Data Mahasiswa
1. Klik tombol **Delete** (ikon tempat sampah) pada card mahasiswa
2. Konfirmasi penghapusan akan muncul
3. Klik **Hapus** untuk menghapus data
4. Data akan dihapus dari database dan list

---

## 📂 Struktur Project

```
program_input_data/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/program_input_data/
│   │   │   │   ├── MainActivity.java          # Activity utama
│   │   │   │   ├── DatabaseHelper.java        # Helper untuk SQLite
│   │   │   │   ├── Mahasiswa.java             # Model class
│   │   │   │   └── MahasiswaAdapter.java      # RecyclerView adapter
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml      # Layout utama
│   │   │   │   │   ├── item_siswa.xml         # Layout item list
│   │   │   │   │   ├── dialog_input_siswa.xml # Layout dialog input
│   │   │   │   │   └── dialog_detail_siswa.xml# Layout dialog detail
│   │   │   │   ├── drawable/                  # Icon dan drawable
│   │   │   │   ├── values/                    # Colors, strings, themes
│   │   │   │   └── mipmap/                    # App icons
│   │   │   └── AndroidManifest.xml
│   │   └── androidTest/                       # Instrumented tests
│   └── build.gradle.kts                       # Build configuration
├── gradle/                                    # Gradle wrapper
├── README.md                                  # Dokumentasi
├── REFACTORING_SUMMARY.md                    # Summary refactoring
└── build.gradle.kts                          # Root build file
```

---

## 🔧 Konfigurasi Build

### build.gradle.kts (Module: app)

```kotlin
android {
    compileSdk = 35
    
    defaultConfig {
        applicationId = "com.example.program_input_data"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
}
```

---

## 🧪 Testing

### Build APK Debug
```bash
./gradlew assembleDebug
```

### Build APK Release
```bash
./gradlew assembleRelease
```

### Run Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

---

## 📝 Changelog

### Version 1.0.0 (8 Mei 2026)
- ✅ Implementasi CRUD lengkap untuk data mahasiswa
- ✅ Database SQLite dengan validasi
- ✅ RecyclerView dengan CardView
- ✅ Dialog input/edit/detail
- ✅ Perhitungan rata-rata nilai otomatis
- ✅ Badge warna berdasarkan performa
- ✅ Statistik dashboard
- ✅ Logo ITB STIKOM Bali
- ✅ Refactoring naming convention (Siswa → Mahasiswa)

---

## 🐛 Known Issues

Saat ini tidak ada known issues. Jika Anda menemukan bug, silakan laporkan di [Issues](https://github.com/aldiruskita/aplikasi-mahasiswa-stikom/issues).

---

## 🔮 Future Improvements

- [ ] Export data ke Excel/CSV
- [ ] Import data dari file
- [ ] Fitur pencarian dan filter
- [ ] Sorting berdasarkan nama/NIM/nilai
- [ ] Grafik statistik nilai
- [ ] Backup dan restore database
- [ ] Dark mode
- [ ] Multi-language support
- [ ] Cloud sync (Firebase)
- [ ] Print report

---

## 🤝 Kontribusi

Kontribusi sangat diterima! Jika Anda ingin berkontribusi:

1. Fork repository ini
2. Buat branch baru (`git checkout -b feature/AmazingFeature`)
3. Commit perubahan (`git commit -m 'Add some AmazingFeature'`)
4. Push ke branch (`git push origin feature/AmazingFeature`)
5. Buat Pull Request

---

## 📄 License

Project ini menggunakan MIT License. Lihat file `LICENSE` untuk detail lebih lanjut.

---

## 📞 Kontak

**Made Aldi Ruskita Salahin**

- 📧 Email: [your-email@example.com]
- 🔗 GitHub: [@aldiruskita](https://github.com/aldiruskita)
- 🌐 LinkedIn: [Your LinkedIn Profile]

---

## 🙏 Acknowledgments

- ITB STIKOM Bali - Institusi pendidikan
- Android Documentation - Referensi pengembangan
- Material Design - Design guidelines
- Stack Overflow Community - Problem solving

---

<div align="center">
  
  ### ⭐ Jika project ini bermanfaat, jangan lupa beri star! ⭐
  
  **Made with ❤️ by Made Aldi Ruskita Salahin**
  
  © 2026 ITB STIKOM Bali. All Rights Reserved.
  
</div>
