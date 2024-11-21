package com.example.skyfarmjava.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyfarmjava.CarrinhoActivity;
import com.example.skyfarmjava.R;
import com.example.skyfarmjava.sistema.Produto;

public class Holder extends RecyclerView.ViewHolder{
    private View itemView;
    public Holder(@NonNull View itemView) {
        super(itemView);
        this.itemView=itemView;

    }
    public void adicionarCarrinho(View.OnClickListener click){
        Button ad =itemView.findViewById(R.id.btItemListaAdicionarCarrinho);
        ad.setOnClickListener(click);
    }
    public void setNome(String nome){
        TextView txNome = itemView.findViewById(R.id.txProdutoItemNome);
        txNome.setText(""+nome);
    }
    public void detalhe(View.OnClickListener click){
        ImageView vBt = itemView.findViewById(R.id.itemListaImgButton);
        vBt.setOnClickListener(click);
    }
}
