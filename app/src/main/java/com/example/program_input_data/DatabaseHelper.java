package com.example.program_input_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mahasiswa_database.db";
    private static final int DATABASE_VERSION = 1;

    // Nama tabel dan kolom
    private static final String TABLE_MAHASISWA = "mahasiswa";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_NIM = "nim";
    private static final String COLUMN_PRODI = "prodi";
    private static final String COLUMN_SEMESTER = "semester";
    private static final String COLUMN_NILAI_PEMROGRAMAN = "nilai_pemrograman";
    private static final String COLUMN_NILAI_BASIS_DATA = "nilai_basis_data";
    private static final String COLUMN_NILAI_JARINGAN = "nilai_jaringan";

    // Query create table
    private static final String CREATE_TABLE_MAHASISWA = "CREATE TABLE " + TABLE_MAHASISWA + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAMA + " TEXT NOT NULL,"
            + COLUMN_NIM + " TEXT NOT NULL UNIQUE,"
            + COLUMN_PRODI + " TEXT NOT NULL,"
            + COLUMN_SEMESTER + " INTEGER NOT NULL,"
            + COLUMN_NILAI_PEMROGRAMAN + " REAL NOT NULL,"
            + COLUMN_NILAI_BASIS_DATA + " REAL NOT NULL,"
            + COLUMN_NILAI_JARINGAN + " REAL NOT NULL"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAHASISWA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        onCreate(db);
    }

    // Menambah data mahasiswa
    public long tambahMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_NIM, mahasiswa.getNim());
        values.put(COLUMN_PRODI, mahasiswa.getProdi());
        values.put(COLUMN_SEMESTER, mahasiswa.getSemester());
        values.put(COLUMN_NILAI_PEMROGRAMAN, mahasiswa.getNilaiPemrograman());
        values.put(COLUMN_NILAI_BASIS_DATA, mahasiswa.getNilaiBasisData());
        values.put(COLUMN_NILAI_JARINGAN, mahasiswa.getNilaiJaringan());

        long id = db.insert(TABLE_MAHASISWA, null, values);
        db.close();
        return id;
    }

    // Mendapatkan semua data mahasiswa
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MAHASISWA + " ORDER BY " + COLUMN_NAMA + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                mahasiswa.setNama(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)));
                mahasiswa.setNim(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIM)));
                mahasiswa.setProdi(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODI)));
                mahasiswa.setSemester(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SEMESTER)));
                mahasiswa.setNilaiPemrograman(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_PEMROGRAMAN)));
                mahasiswa.setNilaiBasisData(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_BASIS_DATA)));
                mahasiswa.setNilaiJaringan(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_JARINGAN)));

                mahasiswaList.add(mahasiswa);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return mahasiswaList;
    }

    // Mendapatkan satu data mahasiswa berdasarkan ID
    public Mahasiswa getMahasiswa(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MAHASISWA,
                null,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);

        Mahasiswa mahasiswa = null;
        if (cursor != null && cursor.moveToFirst()) {
            mahasiswa = new Mahasiswa();
            mahasiswa.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            mahasiswa.setNama(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)));
            mahasiswa.setNim(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIM)));
            mahasiswa.setProdi(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODI)));
            mahasiswa.setSemester(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SEMESTER)));
            mahasiswa.setNilaiPemrograman(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_PEMROGRAMAN)));
            mahasiswa.setNilaiBasisData(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_BASIS_DATA)));
            mahasiswa.setNilaiJaringan(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_JARINGAN)));
            cursor.close();
        }

        db.close();
        return mahasiswa;
    }

    // Update data mahasiswa
    public int updateMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_NIM, mahasiswa.getNim());
        values.put(COLUMN_PRODI, mahasiswa.getProdi());
        values.put(COLUMN_SEMESTER, mahasiswa.getSemester());
        values.put(COLUMN_NILAI_PEMROGRAMAN, mahasiswa.getNilaiPemrograman());
        values.put(COLUMN_NILAI_BASIS_DATA, mahasiswa.getNilaiBasisData());
        values.put(COLUMN_NILAI_JARINGAN, mahasiswa.getNilaiJaringan());

        int rowsAffected = db.update(TABLE_MAHASISWA, values, COLUMN_ID + "=?",
                new String[]{String.valueOf(mahasiswa.getId())});
        db.close();
        return rowsAffected;
    }

    // Hapus data mahasiswa
    public void deleteMahasiswa(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MAHASISWA, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Mendapatkan jumlah mahasiswa
    public int getJumlahMahasiswa() {
        String countQuery = "SELECT * FROM " + TABLE_MAHASISWA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }
}
