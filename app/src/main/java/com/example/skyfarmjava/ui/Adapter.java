package com.example.skyfarmjava.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyfarmjava.CarrinhoActivity;
import com.example.skyfarmjava.api.dto.DtoProduto;
import com.example.skyfarmjava.api.rest.RestProduto;
import com.example.skyfarmjava.sistema.CarrinhoDeCompra;
import com.example.skyfarmjava.sistema.Produto;
import com.example.skyfarmjava.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Holder> {
    private Context context;
    List<Produto> listItens = new ArrayList<>();
    Intent janelaDetalheItem;
    public Adapter(Context context) {
        new Thread(()->{
            RestProduto.chamarServico();
            DtoProduto dto =(DtoProduto) RestProduto.restDtoBasicos;
            listItens.addAll(dto.lista()) ;
        }).start();
        this.context=context;
        janelaDetalheItem = new Intent(context, CarrinhoActivity.class);
        janelaDetalheItem.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_lista,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        System.out.println("Position:"+position+"/"+listItens.size());
        if(position>=listItens.size())return;
        Produto item = listItens.get(position);
        holder.setNome(item.getNome());
        holder.detalhe(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(janelaDetalheItem);
            }
        });
        holder.adicionarCarrinho( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CarrinhoDeCompra().adicionarNoCarrinho(item);
                Toast.makeText(context, "Produto no carrinho de compras", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return listItens.size();
    }
}
