package com.example.skyfarmjava.api.rest;

import com.example.skyfarmjava.api.ApiComunicacao;
import com.example.skyfarmjava.api.dto.DtoAcessoSistema;
import com.example.skyfarmjava.api.dto.DtoModelo;

import org.json.JSONObject;

public class RestAutenticar implements RestModelo {
    DtoAcessoSistema acesso;

    public RestAutenticar(DtoAcessoSistema acesso) {
        this.acesso = acesso;
    }

    @Override
    public String uri() {
        return "/autenticar";
    }

    @Override
    public DtoModelo populaDados(JSONObject jsonObject) throws Exception {
        DtoAcessoSistema novo = new DtoAcessoSistema();
        novo.setLogin(jsonObject.getString("login"));
        novo.setSenha(jsonObject.getString("senha"));
        return novo;
    }

    @Override
    public String requestMethod() {
        return "POST";
    }

    @Override
    public String getRequestJson() throws Exception{
        JSONObject request = new JSONObject();
        request.put("login",acesso.getLogin());
        request.put("senha",acesso.getSenha());
        return request.toString();
    }


    public static DtoModelo restDtoBasicos = null;
    public static void chamarServico(DtoAcessoSistema dto ) {
            dto.setLogin("sistema");
            dto.setSenha("senha");
            try {
                ApiComunicacao p = new ApiComunicacao();
                RestAutenticar rt = new RestAutenticar(dto);
                restDtoBasicos = p.endPointUnico(rt);
                //textView.setText(">>"+((DtoAcessoSistema)restDtoBasicos).getLogin()+"/"+((DtoAcessoSistema)restDtoBasicos).getSenha());
                dto.setLog("Sucesso");
                restDtoBasicos = dto;
            } catch (Exception e) {
                // textView.setText("houve erro:"+e.getMessage());
                restDtoBasicos = null;
                e.printStackTrace();
            }

    }
}
