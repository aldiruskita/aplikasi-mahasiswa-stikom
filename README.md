#  Aplikasi Data Siswa

##  Identitas
- **Nama:** Made Aldi Ruskita Salahin  
- **NIM:** 230040070  

Aplikasi Android untuk mengelola data siswa menggunakan database SQLite.  
Aplikasi ini mendukung pengelolaan data siswa secara lengkap dengan tampilan modern, responsif, dan mudah digunakan.

---

#  Fitur Utama

##  Kelola Data dengan SQLite
- Menggunakan SQLite sebagai database lokal
- Menyimpan data secara permanen
- Mendukung operasi CRUD:
  -  Create (Tambah Data)
  -  Read (Lihat Data)
  -  Update (Edit Data)
  -  Delete (Hapus Data)

---

##  Menampilkan Data di List
- Menggunakan RecyclerView
- Desain modern dengan CardView
- Menampilkan informasi:
  - Nama Siswa
  - NIS
  - Kelas
  - Rata-rata Nilai

###  Indikator Warna Nilai

| Warna | Keterangan | Nilai |
|------|-------------|--------|
| 🟢 Hijau | Sangat Baik | ≥ 80 |
| 🟠 Orange | Baik | 60 - 79 |
| 🔴 Merah | Perlu Perbaikan | < 60 |

---

##  Penyimpanan Nilai
- Nilai tersimpan permanen di SQLite
- Perhitungan rata-rata otomatis
- Validasi nilai:
  - Minimum: 0
  - Maksimum: 100



#  Tampilan Aplikasi

##  Halaman Utama
Menampilkan daftar data siswa menggunakan RecyclerView dengan desain CardView modern.

![Demo Aplikasi 1](https://raw.githubusercontent.com/aldiruskita/aplikasi-mahasiswa-stikom/6bcdaee51705aad1dc7dcdcc64d02ff6c724daf0/demo1.png)

---

##  Halaman Tambah / Edit Data
Halaman untuk menambahkan dan mengedit data siswa beserta nilai.

![Demo Aplikasi 2](https://raw.githubusercontent.com/aldiruskita/aplikasi-mahasiswa-stikom/6bcdaee51705aad1dc7dcdcc64d02ff6c724daf0/demo2.png)

---

#  Tujuan Aplikasi
Aplikasi ini dibuat untuk:
- Mempermudah pengelolaan data siswa
- Mengurangi pencatatan manual
- Menyimpan data secara aman
- Membantu monitoring nilai siswa

---

#  Developer
**Made Aldi Ruskita Salahin**  
NIM: 230040070

---

# 🔗 Repository GitHub
https://github.com/aldiruskita/aplikasi-mahasiswa-stikom
