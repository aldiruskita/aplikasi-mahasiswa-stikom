package com.example.program_input_data;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MahasiswaAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private DatabaseHelper databaseHelper;
    private List<Mahasiswa> mahasiswaList;
    private FloatingActionButton fabAdd;
    private TextView tvJumlahMahasiswa, tvAvgNilai;
    private LinearLayout tvEmptyState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi views
        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fabAdd);
        tvJumlahMahasiswa = findViewById(R.id.tvJumlahSiswa);
        tvAvgNilai = findViewById(R.id.tvAvgIpk);
        tvEmptyState = findViewById(R.id.tvEmptyState);

        // Inisialisasi database
        databaseHelper = new DatabaseHelper(this);

        // Setup RecyclerView
        mahasiswaList = new ArrayList<>();
        adapter = new MahasiswaAdapter(this, mahasiswaList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Load data
        loadData();

        // Tombol tambah mahasiswa
        fabAdd.setOnClickListener(v -> showInputDialog(null));
    }

    private void loadData() {
        mahasiswaList = databaseHelper.getAllMahasiswa();
        adapter.updateData(mahasiswaList);
        updateUI();
    }

    private void updateUI() {
        int jumlah = mahasiswaList.size();
        tvJumlahMahasiswa.setText(String.valueOf(jumlah));
        
        // Hitung rata-rata nilai
        double avgNilai = 0;
        if (jumlah > 0) {
            double totalNilai = 0;
            for (Mahasiswa mahasiswa : mahasiswaList) {
                totalNilai += mahasiswa.getRataRata();
            }
            avgNilai = totalNilai / jumlah;
        }
        tvAvgNilai.setText(String.format(Locale.getDefault(), "%.2f", avgNilai));
        
        if (jumlah == 0) {
            tvEmptyState.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void showInputDialog(Mahasiswa mahasiswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_input_siswa, null);

        TextInputEditText etNama = dialogView.findViewById(R.id.etNama);
        TextInputEditText etNim = dialogView.findViewById(R.id.etNis);
        TextInputEditText etProdi = dialogView.findViewById(R.id.etKelas);
        TextInputEditText etSemester = dialogView.findViewById(R.id.etSemester);
        TextInputEditText etNilaiPemrograman = dialogView.findViewById(R.id.etNilaiMatematika);
        TextInputEditText etNilaiBasisData = dialogView.findViewById(R.id.etNilaiBahasaIndonesia);
        TextInputEditText etNilaiJaringan = dialogView.findViewById(R.id.etNilaiBahasaInggris);

        // Jika edit, isi data yang ada
        boolean isEdit = mahasiswa != null;
        if (isEdit) {
            etNama.setText(mahasiswa.getNama());
            etNim.setText(mahasiswa.getNim());
            etProdi.setText(mahasiswa.getProdi());
            etSemester.setText(String.valueOf(mahasiswa.getSemester()));
            etNilaiPemrograman.setText(String.valueOf(mahasiswa.getNilaiPemrograman()));
            etNilaiBasisData.setText(String.valueOf(mahasiswa.getNilaiBasisData()));
            etNilaiJaringan.setText(String.valueOf(mahasiswa.getNilaiJaringan()));
            etNim.setEnabled(false); // NIM tidak bisa diubah
        }

        builder.setView(dialogView)
                .setTitle(isEdit ? "Edit Data Mahasiswa" : "Tambah Data Mahasiswa")
                .setPositiveButton(isEdit ? "Update" : "Simpan", (dialog, which) -> {
                    String nama = etNama.getText().toString().trim();
                    String nim = etNim.getText().toString().trim();
                    String prodi = etProdi.getText().toString().trim();
                    String semesterStr = etSemester.getText().toString().trim();
                    String nilaiPemrogramanStr = etNilaiPemrograman.getText().toString().trim();
                    String nilaiBasisDataStr = etNilaiBasisData.getText().toString().trim();
                    String nilaiJaringanStr = etNilaiJaringan.getText().toString().trim();

                    // Validasi input
                    if (nama.isEmpty() || nim.isEmpty() || prodi.isEmpty() || semesterStr.isEmpty() ||
                            nilaiPemrogramanStr.isEmpty() || nilaiBasisDataStr.isEmpty() || nilaiJaringanStr.isEmpty()) {
                        Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        int semester = Integer.parseInt(semesterStr);
                        double nilaiPemrograman = Double.parseDouble(nilaiPemrogramanStr);
                        double nilaiBasisData = Double.parseDouble(nilaiBasisDataStr);
                        double nilaiJaringan = Double.parseDouble(nilaiJaringanStr);

                        // Validasi range nilai
                        if (nilaiPemrograman < 0 || nilaiPemrograman > 100 || 
                            nilaiBasisData < 0 || nilaiBasisData > 100 ||
                            nilaiJaringan < 0 || nilaiJaringan > 100) {
                            Toast.makeText(this, "Nilai harus antara 0-100!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Validasi semester
                        if (semester < 1 || semester > 14) {
                            Toast.makeText(this, "Semester harus antara 1-14!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (isEdit) {
                            // Update data
                            mahasiswa.setNama(nama);
                            mahasiswa.setProdi(prodi);
                            mahasiswa.setSemester(semester);
                            mahasiswa.setNilaiPemrograman(nilaiPemrograman);
                            mahasiswa.setNilaiBasisData(nilaiBasisData);
                            mahasiswa.setNilaiJaringan(nilaiJaringan);

                            int result = databaseHelper.updateMahasiswa(mahasiswa);
                            if (result > 0) {
                                Toast.makeText(this, "Data berhasil diupdate!", Toast.LENGTH_SHORT).show();
                                loadData();
                            } else {
                                Toast.makeText(this, "Gagal mengupdate data!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Tambah data baru
                            Mahasiswa newMahasiswa = new Mahasiswa();
                            newMahasiswa.setNama(nama);
                            newMahasiswa.setNim(nim);
                            newMahasiswa.setProdi(prodi);
                            newMahasiswa.setSemester(semester);
                            newMahasiswa.setNilaiPemrograman(nilaiPemrograman);
                            newMahasiswa.setNilaiBasisData(nilaiBasisData);
                            newMahasiswa.setNilaiJaringan(nilaiJaringan);

                            long id = databaseHelper.tambahMahasiswa(newMahasiswa);
                            if (id > 0) {
                                Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
                                loadData();
                            } else {
                                Toast.makeText(this, "Gagal menyimpan data! NIM mungkin sudah ada.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Format nilai tidak valid!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    private void showDetailDialog(Mahasiswa mahasiswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_detail_siswa, null);

        TextView tvDetailNama = dialogView.findViewById(R.id.tvDetailNama);
        TextView tvDetailNim = dialogView.findViewById(R.id.tvDetailNis);
        TextView tvDetailProdi = dialogView.findViewById(R.id.tvDetailKelas);
        TextView tvDetailSemester = dialogView.findViewById(R.id.tvDetailSemester);
        TextView tvDetailNilaiPemrograman = dialogView.findViewById(R.id.tvDetailNilaiMtk);
        TextView tvDetailNilaiBasisData = dialogView.findViewById(R.id.tvDetailNilaiBind);
        TextView tvDetailNilaiJaringan = dialogView.findViewById(R.id.tvDetailNilaiBing);
        TextView tvDetailRataRata = dialogView.findViewById(R.id.tvDetailRataRata);

        tvDetailNama.setText(mahasiswa.getNama());
        tvDetailNim.setText(mahasiswa.getNim());
        tvDetailProdi.setText(mahasiswa.getProdi());
        tvDetailSemester.setText(String.valueOf(mahasiswa.getSemester()));
        tvDetailNilaiPemrograman.setText(String.format(Locale.getDefault(), "%.2f", mahasiswa.getNilaiPemrograman()));
        tvDetailNilaiBasisData.setText(String.format(Locale.getDefault(), "%.2f", mahasiswa.getNilaiBasisData()));
        tvDetailNilaiJaringan.setText(String.format(Locale.getDefault(), "%.2f", mahasiswa.getNilaiJaringan()));
        tvDetailRataRata.setText(String.format(Locale.getDefault(), "%.2f", mahasiswa.getRataRata()));

        // Set warna berdasarkan rata-rata nilai
        if (mahasiswa.getRataRata() >= 80) {
            tvDetailRataRata.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        } else if (mahasiswa.getRataRata() >= 60) {
            tvDetailRataRata.setTextColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
        } else {
            tvDetailRataRata.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        }

        builder.setView(dialogView)
                .setPositiveButton("Tutup", null)
                .show();
    }

    @Override
    public void onEditClick(Mahasiswa mahasiswa) {
        showInputDialog(mahasiswa);
    }

    @Override
    public void onDeleteClick(Mahasiswa mahasiswa) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Data")
                .setMessage("Apakah Anda yakin ingin menghapus data " + mahasiswa.getNama() + "?")
                .setPositiveButton("Hapus", (dialog, which) -> {
                    databaseHelper.deleteMahasiswa(mahasiswa.getId());
                    Toast.makeText(this, "Data berhasil dihapus!", Toast.LENGTH_SHORT).show();
                    loadData();
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    @Override
    public void onItemClick(Mahasiswa mahasiswa) {
        showDetailDialog(mahasiswa);
    }
}
