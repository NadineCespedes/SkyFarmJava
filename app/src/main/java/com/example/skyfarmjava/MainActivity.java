package com.example.skyfarmjava;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.skyfarmjava.api.ApiComunicacao;
import com.example.skyfarmjava.api.dto.DtoAcessoSistema;
import com.example.skyfarmjava.api.rest.RestAutenticar;

public class MainActivity extends AppCompatActivity {

    TextView txLog;
    TextView txLoggin;
    TextView txSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        protudoActivity = new Intent(this, ProdutosActivity.class);

        txLog = findViewById(R.id.txLog);
        txLoggin = findViewById(R.id.edTxLogin);
        txSenha =  findViewById(R.id.edTxSenha);
        TextView ip = findViewById(R.id.edTxIp);
        Button buto = findViewById(R.id.btConnectar);
        txLog.setText("aa");
        buto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DtoAcessoSistema tentativa= new DtoAcessoSistema();
                tentativa.setLogin(txLoggin.getText()+"");
                tentativa.setSenha(txSenha.getText()+"");
                ApiComunicacao.setApiUrl(ip.getText()+"");
                txLog.setText("Processando");
                acao(tentativa);
            }
        });

    }
    Intent protudoActivity;


    public void acao(DtoAcessoSistema tentativa){
        Thread d= new Thread(() ->{
            RestAutenticar.chamarServico(tentativa);
            runOnUiThread(() ->{
                if(RestAutenticar.restDtoBasicos == null) {
                    txLog.setText("Usuario ou senha invalidos");
                }else {
                    txLog.setText("ok:"+((DtoAcessoSistema)RestAutenticar.restDtoBasicos).getLog());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usuarioLogado", ((DtoAcessoSistema)RestAutenticar.restDtoBasicos));
                    protudoActivity.putExtras(bundle);
                    startActivity(protudoActivity);
                }
            });
        });
        d.start();
    }

    public static void s (Runnable p){
        Looper lop = Looper.getMainLooper();
        Handler l = new Handler(lop);
        l.post(p);
    }

}