package com.example.skyfarmjava.api;

import com.example.skyfarmjava.api.dto.DtoModelo;
import com.example.skyfarmjava.api.rest.RestModelo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiComunicacao
{
    private static String BASE_URL = "http://:5029";
    public static void setApiUrl(String ip){
        BASE_URL = "http://"+ip+":5029";
    }

    public DtoModelo endPointUnico(RestModelo base) throws  Exception{
        HttpURLConnection conn= chamaAPI(base);
        preparaJsonParaEnvio(conn,base);
        if(conn.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
            return null;
        }
        StringBuilder resultado = carregaResposta(conn);
        return geraObjetosDeRetorno(resultado,base);
    }
    public List<DtoModelo> endPointLista(RestModelo base) throws  Exception{
        HttpURLConnection conn= chamaAPI(base);
        if(conn.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
            return null;
        }
        StringBuilder resultado = carregaResposta(conn);
        return geraListaDeObjetosDeRetorno(resultado,base);
    }
    private HttpURLConnection chamaAPI(RestModelo base) throws Exception{
        URL url = new URL(BASE_URL + base.uri());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(base.requestMethod());
        conn.setRequestProperty("Content-Type", "application/json");

        return conn;
    }
    private void preparaJsonParaEnvio(HttpURLConnection conn ,RestModelo base) throws Exception{
        if(base.getRequestJson() == null)return;
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        writer.write(base.getRequestJson()); // Escreve o JSON no corpo da requisição.
        writer.flush();
        writer.close();
    }
    private StringBuilder carregaResposta(HttpURLConnection conn) throws  Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        return result;
    }
    private List<DtoModelo> geraListaDeObjetosDeRetorno(StringBuilder result,RestModelo base) throws Exception {
        JSONArray jsonArray = new JSONArray(result.toString());
        List<DtoModelo> lista= new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            lista.add(base.populaDados(jsonObject));
        }
        return lista;
    }

    private DtoModelo geraObjetosDeRetorno(StringBuilder result,RestModelo base) throws Exception {
        JSONObject jsonObject = new JSONObject(result.toString());
        return base.populaDados(jsonObject);
    }
}
