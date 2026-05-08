package com.example.program_input_data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {

    private Context context;
    private List<Mahasiswa> mahasiswaList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(Mahasiswa mahasiswa);
        void onDeleteClick(Mahasiswa mahasiswa);
        void onItemClick(Mahasiswa mahasiswa);
    }

    public MahasiswaAdapter(Context context, List<Mahasiswa> mahasiswaList, OnItemClickListener listener) {
        this.context = context;
        this.mahasiswaList = mahasiswaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        Mahasiswa mahasiswa = mahasiswaList.get(position);
        
        holder.tvNama.setText(mahasiswa.getNama());
        holder.tvNim.setText("NIM: " + mahasiswa.getNim());
        holder.tvProdi.setText(mahasiswa.getProdi() + " - Semester " + mahasiswa.getSemester());
        holder.tvRataRata.setText(String.format(Locale.getDefault(), "%.2f", mahasiswa.getRataRata()));

        // Set badge background berdasarkan rata-rata nilai
        if (mahasiswa.getRataRata() >= 80) {
            holder.ipkBadge.setBackgroundResource(R.drawable.badge_excellent);
        } else if (mahasiswa.getRataRata() >= 60) {
            holder.ipkBadge.setBackgroundResource(R.drawable.badge_good);
        } else {
            holder.ipkBadge.setBackgroundResource(R.drawable.badge_poor);
        }

        holder.btnEdit.setOnClickListener(v -> listener.onEditClick(mahasiswa));
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(mahasiswa));
        holder.cardView.setOnClickListener(v -> listener.onItemClick(mahasiswa));
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public void updateData(List<Mahasiswa> newMahasiswaList) {
        this.mahasiswaList = newMahasiswaList;
        notifyDataSetChanged();
    }

    static class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        TextView tvNama, tvNim, tvProdi, tvRataRata;
        ImageButton btnEdit, btnDelete;
        LinearLayout ipkBadge;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvNim = itemView.findViewById(R.id.tvNis);
            tvProdi = itemView.findViewById(R.id.tvKelas);
            tvRataRata = itemView.findViewById(R.id.tvRataRata);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            ipkBadge = itemView.findViewById(R.id.ipkBadge);
        }
    }
}
