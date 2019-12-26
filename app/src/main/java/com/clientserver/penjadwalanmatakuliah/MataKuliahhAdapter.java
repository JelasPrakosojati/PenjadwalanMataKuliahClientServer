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

public class MataKuliahhAdapter extends RecyclerView.Adapter<MataKuliahhAdapter.ViewHolder> {

    private Context context;
    private List<MataKuliahh> list;

    public MataKuliahhAdapter(Context context, List<MataKuliahh> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matakuliah,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MataKuliahh obj = list.get(position);
        holder.kodemk.setText(obj.getKodeMK());
        holder.namamk.setText("Nama Mata Kuliah: " + obj.getNamaMK());
        holder.smt.setText("Semester: " + obj.getSemester());
        holder.sks.setText("SKS: " + obj.getSKS());

        holder.md = obj;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kodemk, namamk, smt, sks;
        MataKuliahh md;
        private LinearLayout box_linear;

        public ViewHolder(View itemView) {
            super(itemView);

            kodemk = (TextView) itemView.findViewById(R.id.kodematakuliah);
            namamk = (TextView)itemView.findViewById(R.id.namamatakuliah);
            smt = (TextView) itemView.findViewById(R.id.semester);
            sks = (TextView)itemView.findViewById(R.id.sks);
            box_linear = itemView.findViewById(R.id.box_linear);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, InsertM.class);
                    update.putExtra("update",1);
                    update.putExtra("kode_matakuliah",md.getKodeMK());
                    update.putExtra("nama_matakuliah",md.getNamaMK());
                    update.putExtra("semester",md.getSemester());
                    update.putExtra("sks",md.getSKS());

                    context.startActivity(update);
                }
            });
        }
    }
}
