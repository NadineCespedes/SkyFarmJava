package com.example.skyfarmjava.api.dto;

import com.example.skyfarmjava.sistema.Produto;

import java.util.ArrayList;
import java.util.List;

public class DtoProduto implements DtoModelo{
    private List<Produto> lista = new ArrayList<>();
    public void adicionar(Produto p){
        lista.add(p);
    }
    public List<Produto> lista(){
        return lista;
    }
}
