package com.oguzhann.criptoappt2.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oguzhann.criptoappt2.R;
import com.oguzhann.criptoappt2.model.CriptoModel;

import java.util.ArrayList;

public class RcyclerViewAdapter extends RecyclerView.Adapter<RcyclerViewAdapter.RowHolder> {

    private ArrayList<CriptoModel> criptoList;

    private String[] colors = {"#d6e03e", "#cd9a98", "#0600ff", "#bbb1a6","#a9c399","#cd9a98" , "#ff451c","#75fba4","#005401","#ff555f"};

    public RcyclerViewAdapter(ArrayList<CriptoModel> criptoList) {
        this.criptoList = criptoList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(criptoList.get(position),colors,position);

    }

    @Override
    public int getItemCount() {
        return criptoList.size();
    }

    public static class RowHolder extends RecyclerView.ViewHolder {


        TextView textName;
        TextView textPrice;

        public RowHolder(@NonNull View itemView) {
            super(itemView);


        }
        public void bind(CriptoModel Criptomodel, String[] colors, int position) {
            textName = itemView.findViewById(R.id.txt_name);
            textPrice = itemView.findViewById(R.id.txt_price);
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
          //  itemView.setBackgroundColor(Color.parseColor(colors[position % colors.length]));

            textName.setText(Criptomodel.getCurrency());
            textPrice.setText(Criptomodel.getPrice());




        }
    }

}
