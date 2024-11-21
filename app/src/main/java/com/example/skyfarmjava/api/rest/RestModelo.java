package com.example.skyfarmjava.api.rest;

import com.example.skyfarmjava.api.dto.DtoModelo;

import org.json.JSONObject;

public interface RestModelo {
    public  String uri();
    public DtoModelo populaDados(JSONObject jsonObject ) throws Exception;
    public String requestMethod();
    public String getRequestJson() throws Exception;

}
