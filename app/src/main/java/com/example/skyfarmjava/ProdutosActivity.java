package com.example.skyfarmjava;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyfarmjava.api.dto.DtoAcessoSistema;
import com.example.skyfarmjava.ui.Adapter;

public class ProdutosActivity extends AppCompatActivity {
    TextView txBemVindo;
    Intent carrinho;
    Intent home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_produtos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();
        DtoAcessoSistema usuario =(DtoAcessoSistema)extras.get("usuarioLogado");
        txBemVindo = findViewById(R.id.txProdutosUsuarioLogado);
        txBemVindo.setText("Bem vindo, "+usuario.getLogin()+"!");

        carrinho = new Intent(this, CarrinhoActivity.class);
        ImageView bt = findViewById(R.id.listaProdutoBtCarrinho);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(() ->{
                    startActivity(carrinho);
                });

            }
        });

        home = new Intent(this, CarrinhoActivity.class);
        ImageView btHome = findViewById(R.id.listaProdutoBtHome);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(() ->{


                    Looper lop = Looper.getMainLooper();
                    Handler l = new Handler(lop);
                    l.post(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(home);
                        }
                    });

                });

            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycleProdutosLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new Adapter(this.getBaseContext()));
    }
}