package com.example.skyfarmjava.api.rest;

import com.example.skyfarmjava.api.ApiComunicacao;
import com.example.skyfarmjava.api.dto.DtoAcessoSistema;
import com.example.skyfarmjava.api.dto.DtoModelo;
import com.example.skyfarmjava.api.dto.DtoProduto;
import com.example.skyfarmjava.sistema.Produto;

import org.json.JSONArray;
import org.json.JSONObject;

public class RestProduto implements RestModelo {
    @Override
    public String uri() {
        return "/produto/listar";
    }

    @Override
    public DtoModelo populaDados(JSONObject jsonObject) throws Exception {
        DtoProduto dto = new DtoProduto();
        JSONArray value = jsonObject.getJSONArray("value");
        for(int i=0;i<value.length();i++){
            JSONObject produto= value.getJSONObject(i);
            dto.adicionar(new Produto(produto.getString("nome"),produto.getInt("id")));
        }
        return dto;
    }

    @Override
    public String requestMethod() {
        return "GET";
    }

    @Override
    public String getRequestJson() throws Exception {
        return null;
    }

    public static DtoModelo restDtoBasicos = null;
    public static void chamarServico( ) {
        try {
            ApiComunicacao p = new ApiComunicacao();
            RestProduto rt = new RestProduto();
            restDtoBasicos = p.endPointUnico(rt);
            //textView.setText(">>"+((DtoAcessoSistema)restDtoBasicos).getLogin()+"/"+((DtoAcessoSistema)restDtoBasicos).getSenha());

        } catch (Exception e) {
            // textView.setText("houve erro:"+e.getMessage());
            restDtoBasicos = null;
            e.printStackTrace();
        }

    }
}
