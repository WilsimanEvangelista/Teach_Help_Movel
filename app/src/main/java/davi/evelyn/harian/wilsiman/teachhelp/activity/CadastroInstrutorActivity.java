package davi.evelyn.harian.wilsiman.teachhelp.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import davi.evelyn.harian.wilsiman.teachhelp.R;
import davi.evelyn.harian.wilsiman.teachhelp.model.CadastroInstrutorViewModel;
import davi.evelyn.harian.wilsiman.teachhelp.model.RegisterViewModel;


public class CadastroInstrutorActivity extends CadastroActivity {


    RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_instrutor);

        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // Quando o usuário clicar no botão cadastrar
        Button btnRegister =  findViewById(R.id.btnSalvar);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Primeiro verificamos se o usuário digitou corretamente os dados de cadastro.
                // No nosso caso, apenas verificamos se o campo não está vazio no momento em que o
                // usuário clicou no botão cadastrar. Se o campo está vazio, exibimos uma mensagem para o
                // usuário indicando que ele não preencheu o campo e retornamos da função sem fazer
                // mais nada.

                EditText etDescricao =  findViewById(R.id.etDescricao);
                final String newDescricaoInstrutor = etDescricao.getText().toString();
                if(newDescricaoInstrutor.isEmpty()) {
                    Toast.makeText(CadastroInstrutorActivity.this, "Campo de descrição não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }


                EditText etCurriculo =  findViewById(R.id.etCurriculoCadastro);
                final String newCurriculo = etCurriculo.getText().toString();
                if(newCurriculo.isEmpty()) {
                    Toast.makeText(CadastroInstrutorActivity.this, "Campo de currículo não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }



                // O ViewModel possui o método register, que envia as informações para o servidor web.
                // O servidor web recebe as infos e cadastra um novo usuário. Se o usuário foi cadastrado
                // com sucesso, a app recebe o valor true. Se não o servidor retorna o valor false.
                //
                // O método de register retorna um LiveData, que na prática é um container que avisa
                // quando o resultado do servidor chegou.

            }
        });
    }
}





