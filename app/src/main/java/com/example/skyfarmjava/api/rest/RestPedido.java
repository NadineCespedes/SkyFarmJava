package com.example.skyfarmjava.api.rest;

import com.example.skyfarmjava.api.dto.DtoModelo;
import com.example.skyfarmjava.api.dto.DtoPedido;

import org.json.JSONObject;

public class RestPedido implements RestModelo {
    @Override
    public String uri() {
        return "/pedido";
    }

    @Override
    public DtoModelo populaDados(JSONObject jsonObject) throws Exception {
        DtoPedido pedido = new DtoPedido();
        pedido.setStatus(""+jsonObject.get("status"));
        return null;
    }

    @Override
    public String requestMethod() {
        return "POST";
    }

    @Override
    public String getRequestJson() {
        JSONObject request = new JSONObject();
        return request.toString();
    }


}
