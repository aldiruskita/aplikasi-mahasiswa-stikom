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

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder> {

    private Context context;
    private List<Siswa> siswaList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(Siswa siswa);
        void onDeleteClick(Siswa siswa);
        void onItemClick(Siswa siswa);
    }

    public SiswaAdapter(Context context, List<Siswa> siswaList, OnItemClickListener listener) {
        this.context = context;
        this.siswaList = siswaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
        return new SiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaViewHolder holder, int position) {
        Siswa siswa = siswaList.get(position);
        
        holder.tvNama.setText(siswa.getNama());
        holder.tvNis.setText("NIM: " + siswa.getNis());
        holder.tvKelas.setText(siswa.getKelas() + " - Semester " + siswa.getSemester());
        holder.tvRataRata.setText(String.format(Locale.getDefault(), "%.2f", siswa.getRataRata()));

        // Set badge background berdasarkan rata-rata nilai
        if (siswa.getRataRata() >= 80) {
            holder.ipkBadge.setBackgroundResource(R.drawable.badge_excellent);
        } else if (siswa.getRataRata() >= 60) {
            holder.ipkBadge.setBackgroundResource(R.drawable.badge_good);
        } else {
            holder.ipkBadge.setBackgroundResource(R.drawable.badge_poor);
        }

        holder.btnEdit.setOnClickListener(v -> listener.onEditClick(siswa));
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(siswa));
        holder.cardView.setOnClickListener(v -> listener.onItemClick(siswa));
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    public void updateData(List<Siswa> newSiswaList) {
        this.siswaList = newSiswaList;
        notifyDataSetChanged();
    }

    static class SiswaViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        TextView tvNama, tvNis, tvKelas, tvRataRata;
        ImageButton btnEdit, btnDelete;
        LinearLayout ipkBadge;

        public SiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvNis = itemView.findViewById(R.id.tvNis);
            tvKelas = itemView.findViewById(R.id.tvKelas);
            tvRataRata = itemView.findViewById(R.id.tvRataRata);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            ipkBadge = itemView.findViewById(R.id.ipkBadge);
        }
    }
}
