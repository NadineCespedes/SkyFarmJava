package com.example.skyfarmjava.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyfarmjava.sistema.CarrinhoDeCompra;
import com.example.skyfarmjava.sistema.Produto;
import com.example.skyfarmjava.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCarrinhoCompra extends RecyclerView.Adapter<HolderCarrinhoCompra> {
    private Context context;
    List<Produto> listItens = new ArrayList<>();

    public AdapterCarrinhoCompra() {
        listItens = new CarrinhoDeCompra().lista();

    }

    @NonNull
    @Override
    public HolderCarrinhoCompra onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderCarrinhoCompra(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_carrinho,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCarrinhoCompra holder, int position) {
        System.out.println("Position:"+position+"/"+listItens.size());
        if(position>=listItens.size())return;
        Produto item = listItens.get(position);
        //holder.setNome(item.getNome());

    }
    @Override
    public int getItemCount() {
        return listItens.size();
    }
}
