package br.com.senai.stdevelopment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaPrincipalActivity extends AppCompatActivity {

    private Button abrir;
    private EditText nome;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        abrir = findViewById(R.id.btnAbrir);
        nome = findViewById(R.id.editNome);
        senha = findViewById(R.id.editSenha);

        abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nome.getText().length() == 0 && senha.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Insira seu nome de usuário e sua senha", Toast.LENGTH_LONG).show();
                } else if (nome.getText().length() == 0 || senha.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Insira seu nome de usuário e sua senha", Toast.LENGTH_LONG).show();
                } else if(nome.getText().length() != 0 && senha.getText().length() != 0) {
                    Intent intent = new Intent(TelaPrincipalActivity.this, SobreNosActivity.class);
                    intent.putExtra("nome", nome.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
