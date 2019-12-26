package com.clientserver.penjadwalanmatakuliah;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class JadwallAdapter extends RecyclerView.Adapter<JadwallAdapter.ViewHolder> {

    private Context context;
    private List<Jadwall> list;

    public JadwallAdapter(Context context, List<Jadwall> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Jadwall obj = list.get(position);
        holder.idj.setText(obj.getIdJadwal());
        holder.ruang.setText("Ruang: " + obj.getRuang());
        holder.hari.setText("Hari: " + obj.getHari());
        holder.jammulai.setText("Jam Mulai: " + obj.getJamMulai());
        holder.jamakhir.setText("Jam Akhir: " + obj.getJamAkhir());
        holder.matakuliah.setText("Mata Kuliah: " + obj.getMataKuliah());
        holder.dosen.setText("Dosen: " + obj.getDosen());

        holder.md = obj;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idj, ruang, hari, jammulai, jamakhir, matakuliah, dosen;
        Jadwall md;
        private LinearLayout box_linear;

        public ViewHolder(View itemView) {
            super(itemView);

            idj = (TextView) itemView.findViewById(R.id.id);
            ruang = (TextView) itemView.findViewById(R.id.ruang);
            hari = (TextView)itemView.findViewById(R.id.hari);
            jammulai =(TextView) itemView.findViewById(R.id.jammulai);
            jamakhir = (TextView) itemView.findViewById(R.id.jamakhir);
            matakuliah =(TextView) itemView.findViewById(R.id.matakuliah);
            dosen = (TextView) itemView.findViewById(R.id.dosen);
            box_linear = itemView.findViewById(R.id.box_linear);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, InsertJ.class);
                    update.putExtra("update",1);
                    update.putExtra("id_jadwal",md.getIdJadwal());
                    update.putExtra("ruang",md.getRuang());
                    update.putExtra("hari",md.getHari());
                    update.putExtra("jam_mulai",md.getJamMulai());
                    update.putExtra("jam_akhir",md.getJamAkhir());
                    update.putExtra("matakuliah",md.getMataKuliah());
                    update.putExtra("dosen",md.getDosen());

                    context.startActivity(update);
                }
            });
        }
    }
}
