# Aplikasi Data Siswa

Aplikasi Android untuk mengelola data siswa dengan database SQLite.

Fitur Utama

Kelola Data dengan SQLite 
- Database SQLite untuk menyimpan data siswa secara persisten
- Operasi CRUD (Create, Read, Update, Delete) lengkap
- Struktur database yang terorganisir dengan baik

 Menampilkan Data di List 
- RecyclerView untuk menampilkan daftar siswa
- CardView dengan desain modern dan menarik
- Informasi ringkas: Nama, NIS, Kelas, dan Rata-rata Nilai
- Indikator warna untuk rata-rata nilai:
  - 🟢 Hijau: Nilai ≥ 80 (Sangat Baik)
  - 🟠 Orange: Nilai 60-79 (Baik)
  - 🔴 Merah: Nilai < 60 (Perlu Perbaikan)

Menyimpan Nilai dalam Database 
- Perhitungan otomatis rata-rata nilai
- Validasi input nilai (0-100)
- Data tersimpan permanen di SQLite


