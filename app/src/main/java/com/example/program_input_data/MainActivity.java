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

public class MainActivity extends AppCompatActivity implements SiswaAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private SiswaAdapter adapter;
    private DatabaseHelper databaseHelper;
    private List<Siswa> siswaList;
    private FloatingActionButton fabAdd;
    private TextView tvJumlahSiswa, tvAvgIpk;
    private LinearLayout tvEmptyState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fabAdd);
        tvJumlahSiswa = findViewById(R.id.tvJumlahSiswa);
        tvAvgIpk = findViewById(R.id.tvAvgIpk);
        tvEmptyState = findViewById(R.id.tvEmptyState);


        databaseHelper = new DatabaseHelper(this);


        siswaList = new ArrayList<>();
        adapter = new SiswaAdapter(this, siswaList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadData();

        fabAdd.setOnClickListener(v -> showInputDialog(null));
    }

    private void loadData() {
        siswaList = databaseHelper.getAllSiswa();
        adapter.updateData(siswaList);
        updateUI();
    }

    private void updateUI() {
        int jumlah = siswaList.size();
        tvJumlahSiswa.setText(String.valueOf(jumlah));
        
        // Hitung rata-rata nilai
        double avgIpk = 0;
        if (jumlah > 0) {
            double totalIpk = 0;
            for (Siswa siswa : siswaList) {
                totalIpk += siswa.getRataRata();
            }
            avgIpk = totalIpk / jumlah;
        }
        tvAvgIpk.setText(String.format(Locale.getDefault(), "%.2f", avgIpk));
        
        if (jumlah == 0) {
            tvEmptyState.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void showInputDialog(Siswa siswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_input_siswa, null);

        TextInputEditText etNama = dialogView.findViewById(R.id.etNama);
        TextInputEditText etNis = dialogView.findViewById(R.id.etNis);
        TextInputEditText etKelas = dialogView.findViewById(R.id.etKelas);
        TextInputEditText etSemester = dialogView.findViewById(R.id.etSemester);
        TextInputEditText etNilaiMatematika = dialogView.findViewById(R.id.etNilaiMatematika);
        TextInputEditText etNilaiBahasaIndonesia = dialogView.findViewById(R.id.etNilaiBahasaIndonesia);
        TextInputEditText etNilaiBahasaInggris = dialogView.findViewById(R.id.etNilaiBahasaInggris);


        boolean isEdit = siswa != null;
        if (isEdit) {
            etNama.setText(siswa.getNama());
            etNis.setText(siswa.getNis());
            etKelas.setText(siswa.getKelas());
            etSemester.setText(String.valueOf(siswa.getSemester()));
            etNilaiMatematika.setText(String.valueOf(siswa.getNilaiMatematika()));
            etNilaiBahasaIndonesia.setText(String.valueOf(siswa.getNilaiBahasaIndonesia()));
            etNilaiBahasaInggris.setText(String.valueOf(siswa.getNilaiBahasaInggris()));
            etNis.setEnabled(false);
        }

        builder.setView(dialogView)
                .setTitle(isEdit ? "Edit Data Mahasiswa" : "Tambah Data Mahasiswa")
                .setPositiveButton(isEdit ? "Update" : "Simpan", (dialog, which) -> {
                    String nama = etNama.getText().toString().trim();
                    String nis = etNis.getText().toString().trim();
                    String kelas = etKelas.getText().toString().trim();
                    String semesterStr = etSemester.getText().toString().trim();
                    String nilaiMtkStr = etNilaiMatematika.getText().toString().trim();
                    String nilaiBindStr = etNilaiBahasaIndonesia.getText().toString().trim();
                    String nilaiBingStr = etNilaiBahasaInggris.getText().toString().trim();


                    if (nama.isEmpty() || nis.isEmpty() || kelas.isEmpty() || semesterStr.isEmpty() ||
                            nilaiMtkStr.isEmpty() || nilaiBindStr.isEmpty() || nilaiBingStr.isEmpty()) {
                        Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        int semester = Integer.parseInt(semesterStr);
                        double nilaiMtk = Double.parseDouble(nilaiMtkStr);
                        double nilaiBind = Double.parseDouble(nilaiBindStr);
                        double nilaiBing = Double.parseDouble(nilaiBingStr);

                        if (nilaiMtk < 0 || nilaiMtk > 100 || nilaiBind < 0 || nilaiBind > 100 ||
                                nilaiBing < 0 || nilaiBing > 100) {
                            Toast.makeText(this, "Nilai harus antara 0-100!", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        if (semester < 1 || semester > 14) {
                            Toast.makeText(this, "Semester harus antara 1-14!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (isEdit) {
                            // Update data
                            siswa.setNama(nama);
                            siswa.setKelas(kelas);
                            siswa.setSemester(semester);
                            siswa.setNilaiMatematika(nilaiMtk);
                            siswa.setNilaiBahasaIndonesia(nilaiBind);
                            siswa.setNilaiBahasaInggris(nilaiBing);

                            int result = databaseHelper.updateSiswa(siswa);
                            if (result > 0) {
                                Toast.makeText(this, "Data berhasil diupdate!", Toast.LENGTH_SHORT).show();
                                loadData();
                            } else {
                                Toast.makeText(this, "Gagal mengupdate data!", Toast.LENGTH_SHORT).show();
                            }
                        } else {

                            Siswa newSiswa = new Siswa();
                            newSiswa.setNama(nama);
                            newSiswa.setNis(nis);
                            newSiswa.setKelas(kelas);
                            newSiswa.setSemester(semester);
                            newSiswa.setNilaiMatematika(nilaiMtk);
                            newSiswa.setNilaiBahasaIndonesia(nilaiBind);
                            newSiswa.setNilaiBahasaInggris(nilaiBing);

                            long id = databaseHelper.tambahSiswa(newSiswa);
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

    private void showDetailDialog(Siswa siswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_detail_siswa, null);

        TextView tvDetailNama = dialogView.findViewById(R.id.tvDetailNama);
        TextView tvDetailNis = dialogView.findViewById(R.id.tvDetailNis);
        TextView tvDetailKelas = dialogView.findViewById(R.id.tvDetailKelas);
        TextView tvDetailSemester = dialogView.findViewById(R.id.tvDetailSemester);
        TextView tvDetailNilaiMtk = dialogView.findViewById(R.id.tvDetailNilaiMtk);
        TextView tvDetailNilaiBind = dialogView.findViewById(R.id.tvDetailNilaiBind);
        TextView tvDetailNilaiBing = dialogView.findViewById(R.id.tvDetailNilaiBing);
        TextView tvDetailRataRata = dialogView.findViewById(R.id.tvDetailRataRata);

        tvDetailNama.setText(siswa.getNama());
        tvDetailNis.setText(siswa.getNis());
        tvDetailKelas.setText(siswa.getKelas());
        tvDetailSemester.setText(String.valueOf(siswa.getSemester()));
        tvDetailNilaiMtk.setText(String.format(Locale.getDefault(), "%.2f", siswa.getNilaiMatematika()));
        tvDetailNilaiBind.setText(String.format(Locale.getDefault(), "%.2f", siswa.getNilaiBahasaIndonesia()));
        tvDetailNilaiBing.setText(String.format(Locale.getDefault(), "%.2f", siswa.getNilaiBahasaInggris()));
        tvDetailRataRata.setText(String.format(Locale.getDefault(), "%.2f", siswa.getRataRata()));

        // Set warna berdasarkan rata-rata nilai
        if (siswa.getRataRata() >= 80) {
            tvDetailRataRata.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        } else if (siswa.getRataRata() >= 60) {
            tvDetailRataRata.setTextColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
        } else {
            tvDetailRataRata.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        }

        builder.setView(dialogView)
                .setPositiveButton("Tutup", null)
                .show();
    }

    @Override
    public void onEditClick(Siswa siswa) {
        showInputDialog(siswa);
    }

    @Override
    public void onDeleteClick(Siswa siswa) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Data")
                .setMessage("Apakah Anda yakin ingin menghapus data " + siswa.getNama() + "?")
                .setPositiveButton("Hapus", (dialog, which) -> {
                    databaseHelper.deleteSiswa(siswa.getId());
                    Toast.makeText(this, "Data berhasil dihapus!", Toast.LENGTH_SHORT).show();
                    loadData();
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    @Override
    public void onItemClick(Siswa siswa) {
        showDetailDialog(siswa);
    }
}