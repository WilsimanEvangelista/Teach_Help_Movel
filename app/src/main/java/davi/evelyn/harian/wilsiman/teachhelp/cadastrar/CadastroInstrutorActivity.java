package davi.evelyn.harian.wilsiman.teachhelp.cadastrar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import davi.evelyn.harian.wilsiman.teachhelp.R;
import davi.evelyn.harian.wilsiman.teachhelp.activity.CadastroActivity;
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
                EditText etEndereco =  findViewById(R.id.etEndereco);
                final String newEndereco = etEndereco.getText().toString();
                if(newEndereco.isEmpty()) {
                    Toast.makeText(CadastroInstrutorActivity.this, "Campo de endereço não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

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
                LiveData<Boolean> resultLD = registerViewModel.register(newEndereco, newDescricaoInstrutor, newCurriculo, newDescricao);

                // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado indicando
                // se o cadastro deu certo ou não será guardado dentro do LiveData. Neste momento o
                // LiveData avisa que o resultado chegou chamando o método onChanged abaixo.
                resultLD.observe(CadastroInstrutorActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do cadastro. Se aBoolean for true, significa
                        // que o cadastro do usuário foi feito corretamente. Indicamos isso ao usuário
                        // através de uma mensagem do tipo toast e finalizamos a Activity. Quando
                        // finalizamos a Activity, voltamos para a tela de login.
                        if(aBoolean) {
                            Toast.makeText(CadastroInstrutorActivity.this, "Novo usuario registrado com sucesso", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else {
                            // Se o cadastro não deu certo, apenas continuamos na tela de cadastro e
                            // indicamos com uma mensagem ao usuário que o cadastro não deu certo.
                            Toast.makeText(CadastroInstrutorActivity.this, "Erro ao registrar novo usuário", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}





