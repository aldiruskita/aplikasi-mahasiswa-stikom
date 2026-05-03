package com.example.program_input_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "siswa_database.db";
    private static final int DATABASE_VERSION = 1;

    // Nama tabel dan kolom
    private static final String TABLE_SISWA = "mahasiswa";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_NIS = "nim";
    private static final String COLUMN_KELAS = "prodi";
    private static final String COLUMN_SEMESTER = "semester";
    private static final String COLUMN_NILAI_MTK = "nilai_pemrograman";
    private static final String COLUMN_NILAI_BIND = "nilai_basis_data";
    private static final String COLUMN_NILAI_BING = "nilai_jaringan";


    private static final String CREATE_TABLE_SISWA = "CREATE TABLE " + TABLE_SISWA + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAMA + " TEXT NOT NULL,"
            + COLUMN_NIS + " TEXT NOT NULL UNIQUE,"
            + COLUMN_KELAS + " TEXT NOT NULL,"
            + COLUMN_SEMESTER + " INTEGER NOT NULL,"
            + COLUMN_NILAI_MTK + " REAL NOT NULL,"
            + COLUMN_NILAI_BIND + " REAL NOT NULL,"
            + COLUMN_NILAI_BING + " REAL NOT NULL"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SISWA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SISWA);
        onCreate(db);
    }


    public long tambahSiswa(Siswa siswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, siswa.getNama());
        values.put(COLUMN_NIS, siswa.getNis());
        values.put(COLUMN_KELAS, siswa.getKelas());
        values.put(COLUMN_SEMESTER, siswa.getSemester());
        values.put(COLUMN_NILAI_MTK, siswa.getNilaiMatematika());
        values.put(COLUMN_NILAI_BIND, siswa.getNilaiBahasaIndonesia());
        values.put(COLUMN_NILAI_BING, siswa.getNilaiBahasaInggris());

        long id = db.insert(TABLE_SISWA, null, values);
        db.close();
        return id;
    }

    public List<Siswa> getAllSiswa() {
        List<Siswa> siswaList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SISWA + " ORDER BY " + COLUMN_NAMA + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Siswa siswa = new Siswa();
                siswa.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                siswa.setNama(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)));
                siswa.setNis(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIS)));
                siswa.setKelas(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KELAS)));
                siswa.setSemester(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SEMESTER)));
                siswa.setNilaiMatematika(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_MTK)));
                siswa.setNilaiBahasaIndonesia(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_BIND)));
                siswa.setNilaiBahasaInggris(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_BING)));

                siswaList.add(siswa);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return siswaList;
    }

    public Siswa getSiswa(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SISWA,
                null,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);

        Siswa siswa = null;
        if (cursor != null && cursor.moveToFirst()) {
            siswa = new Siswa();
            siswa.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            siswa.setNama(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)));
            siswa.setNis(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIS)));
            siswa.setKelas(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KELAS)));
            siswa.setSemester(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SEMESTER)));
            siswa.setNilaiMatematika(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_MTK)));
            siswa.setNilaiBahasaIndonesia(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_BIND)));
            siswa.setNilaiBahasaInggris(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NILAI_BING)));
            cursor.close();
        }

        db.close();
        return siswa;
    }

    public int updateSiswa(Siswa siswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, siswa.getNama());
        values.put(COLUMN_NIS, siswa.getNis());
        values.put(COLUMN_KELAS, siswa.getKelas());
        values.put(COLUMN_SEMESTER, siswa.getSemester());
        values.put(COLUMN_NILAI_MTK, siswa.getNilaiMatematika());
        values.put(COLUMN_NILAI_BIND, siswa.getNilaiBahasaIndonesia());
        values.put(COLUMN_NILAI_BING, siswa.getNilaiBahasaInggris());

        int rowsAffected = db.update(TABLE_SISWA, values, COLUMN_ID + "=?",
                new String[]{String.valueOf(siswa.getId())});
        db.close();
        return rowsAffected;
    }

    public void deleteSiswa(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SISWA, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int getJumlahSiswa() {
        String countQuery = "SELECT * FROM " + TABLE_SISWA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }
}
