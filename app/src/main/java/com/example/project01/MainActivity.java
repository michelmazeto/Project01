package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonEntrar;
    EditText editEmail;
    EditText editSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEntrar = findViewById(R.id.buttonEntr);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();

                if(email.isEmpty() && senha.isEmpty()){
                    editEmail.setError("Campo Obrigatório");
                    editSenha.setError("Campo Obrigatório");
                } else if (email.isEmpty()) {
                    editEmail.setError("Campo Obrigatório");
                } else if (senha.isEmpty()) {
                    editSenha.setError("Campo Obrigatório");
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        editEmail.setError("Por favor, digite um e-mail válido");
                    } else {
                        String valores = "Email: " + email + " Senha: " + senha;
                        Log.e("Valores: ", valores);

                        Toast.makeText(getApplicationContext(), "Usuário Logado", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(MainActivity.this, Principal.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}