package davi.evelyn.harian.wilsiman.teachhelp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Obtendo dadods digitados pelo usuário
                EditText etNome = (EditText) findViewById(R.id.etNome);
                //declarando a String nome e atribuindo o texto do editText à ela
                String nome = etNome.getText().toString();

                //Obtendo dadods digitados pelo usuário
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                //declarando a String email e atribuindo o texto do editText à ela
                String email = etEmail.getText().toString();

                //Obtendo dadods digitados pelo usuário
                EditText etSenha = (EditText) findViewById(R.id.etSenha);
                //declarando a String senha e atribuindo o texto do editText à ela
                String senha = etSenha.getText().toString();

                //Obtendo dadods digitados pelo usuário
                EditText etSenha2 = (EditText) findViewById(R.id.etSenha2);
                //declarando a String senha2 e atribuindo o texto do editText à ela
                String senha2 = etSenha2.getText().toString();

                //Obtendo dadods digitados pelo usuário
                EditText etDescricao = (EditText) findViewById(R.id.etDescricao);
                //declarando a String descricao e atribuindo o texto do editText à ela
                String descricao = etDescricao.getText().toString();

                Intent i = new Intent(CadastroActivity.this,LoginActivity.class);

                i.putExtra("Texto", email);

                startActivity(i);


            }
        });
    }
}