package com.example.skyfarmjava.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyfarmjava.R;

public class HolderCarrinhoCompra extends RecyclerView.ViewHolder{
    private View itemView;
    public HolderCarrinhoCompra(@NonNull View itemView) {
        super(itemView);
        this.itemView=itemView;
    }
    public void setNome(String nome){
        TextView txNome = itemView.findViewById(R.id.txCarrinhoProdutoNome);
        txNome.setText(""+nome);
    }
}
