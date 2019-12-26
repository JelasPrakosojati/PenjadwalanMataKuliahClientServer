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

public class DosennAdapter extends RecyclerView.Adapter<DosennAdapter.ViewHolder> {

    private Context context;
    private List<Dosenn> list;

    public DosennAdapter(Context context, List<Dosenn> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dosen,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Dosenn obj = list.get(position);
        holder.nip.setText(obj.getNIP());
        holder.namadosen.setText("Nama Dosen: " + obj.getNamaDosen());
        holder.fotodosen.setText("Foto Dosen: " + obj.getFotoDosen());

        holder.md = obj;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nip, namadosen, fotodosen;
        Dosenn md;
        private LinearLayout box_linear;

        public ViewHolder(View itemView) {
            super(itemView);

            nip = (TextView) itemView.findViewById(R.id.nip);
            namadosen = (TextView) itemView.findViewById(R.id.namadosen);
            fotodosen = (TextView) itemView.findViewById(R.id.fotodosen);
            box_linear = itemView.findViewById(R.id.box_linear);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, InsertD.class);
                    update.putExtra("update",1);
                    update.putExtra("nip",md.getNIP());
                    update.putExtra("nama_dosen",md.getNamaDosen());
                    update.putExtra("foto_dosen",md.getFotoDosen());

                    context.startActivity(update);
                }
            });
        }
    }
}
