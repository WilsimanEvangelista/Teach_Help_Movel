package davi.evelyn.harian.wilsiman.teachhelp.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

        // Quando o usuário clicar no bptão cadastrar
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

                // Supondo que você tenha um Spinner com o ID spnMateria
                Spinner spnMaterias = findViewById(R.id.spnMaterias);
                // Criar um ArrayAdapter usando o array de matérias
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.materias,
                        android.R.layout.simple_spinner_item
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMaterias.setAdapter(adapter);


            }
        });
    }
}





