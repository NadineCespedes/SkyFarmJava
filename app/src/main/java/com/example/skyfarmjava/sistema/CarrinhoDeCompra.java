package com.example.skyfarmjava.sistema;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompra {


    private static List<Produto> listaProduto = new ArrayList<>();
    public CarrinhoDeCompra(){

    }

    public List<Produto> lista(){
        return listaProduto;
    }

    public void adicionarNoCarrinho(Produto novo){

        listaProduto.forEach(item ->{
            if(item.getId() == novo.getId()){
                item.setQuantidade(item.getQuantidade()+1);
                return;
            }
        });
        novo.setQuantidade(1);
        listaProduto.add(novo);
    }
    public void retirarDoCarrinho(Produto novo){

        Produto paraDeletar = null;

        for(Produto item: listaProduto) {
            if (item.getId() != novo.getId()) continue;
                if (item.getQuantidade() > 1) {
                    item.setQuantidade(item.getQuantidade() - 1);
                    return;
                } else {
                    paraDeletar = item;
                }

        }

        listaProduto.remove(paraDeletar);
    }

}
