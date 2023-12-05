package davi.evelyn.harian.wilsiman.teachhelp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import davi.evelyn.harian.wilsiman.teachhelp.R;
import davi.evelyn.harian.wilsiman.teachhelp.model.RegisterViewModel;

public class CadastroActivity extends AppCompatActivity {

    RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ImageButton imbDataNasc = findViewById(R.id.imbDataNasc);
        imbDataNasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CadastroActivity.this);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        EditText etDataNasc = findViewById(R.id.etDataNasc);
                        etDataNasc.setText(dia+"/"+mes+"/"+ano);
                    }
                });
            }
        });

        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // Quando o usuário clicar no botão cadastrar
        Button btnRegister =  findViewById(R.id.btnCadastrar);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Primeiro verificamos se o usuário digitou corretamente os dados de cadastro.
                // No nosso caso, apenas verificamos se o campo não está vazio no momento em que o
                // usuário clicou no botão cadastrar. Se o campo está vazio, exibimos uma mensagem para o
                // usuário indicando que ele não preencheu o campo e retornamos da função sem fazer
                // mais nada.
                EditText etName =  findViewById(R.id.etNome);
                final String newName = etName.getText().toString();
                if(newName.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Campo de Nome não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etEmail =  findViewById(R.id.etEmail);
                final String newEmail = etEmail.getText().toString();
                if(newEmail.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Campo de email não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }


                EditText etNewPassword =  findViewById(R.id.etPassword);
                final String newPassword = etNewPassword.getText().toString();
                if(newPassword.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Campo de senha não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etNewPasswordCheck =  findViewById(R.id.etSenha2);
                String newPasswordCheck = etNewPasswordCheck.getText().toString();
                if(newPasswordCheck.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Campo de checagem de senha não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!newPassword.equals(newPasswordCheck)) {
                    Toast.makeText(CadastroActivity.this, "Senha não confere", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDataNasc =  findViewById(R.id.etDataNasc);
                final String newDataNasc = etDataNasc.getText().toString();
                if(newDataNasc.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Campo de checagem de Data de Nascimento não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etEndereco =  findViewById(R.id.etEnderecoAluno);
                final String newEndereco = etEndereco.getText().toString();
                if(newEndereco.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Campo de Endereço não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }


                // O ViewModel possui o método register, que envia as informações para o servidor web.
                // O servidor web recebe as infos e cadastra um novo usuário. Se o usuário foi cadastrado
                // com sucesso, a app recebe o valor true. Se não o servidor retorna o valor false.
                //
                // O método de register retorna um LiveData, que na prática é um container que avisa
                // quando o resultado do servidor chegou.
                LiveData<Boolean> resultLD = registerViewModel.register(newName, newEmail, newPassword, newDataNasc, newEndereco);

                // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado indicando
                // se o cadastro deu certo ou não será guardado dentro do LiveData. Neste momento o
                // LiveData avisa que o resultado chegou chamando o método onChanged abaixo.
                resultLD.observe(CadastroActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do cadastro. Se aBoolean for true, significa
                        // que o cadastro do usuário foi feito corretamente. Indicamos isso ao usuário
                        // através de uma mensagem do tipo toast e finalizamos a Activity. Quando
                        // finalizamos a Activity, voltamos para a tela de login.
                        if(aBoolean) {
                            Toast.makeText(CadastroActivity.this, "Novo usuario registrado com sucesso", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else {
                            // Se o cadastro não deu certo, apenas continuamos na tela de cadastro e
                            // indicamos com uma mensagem ao usuário que o cadastro não deu certo.
                            Toast.makeText(CadastroActivity.this, "Erro ao registrar novo usuário", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
