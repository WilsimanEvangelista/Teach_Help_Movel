package davi.evelyn.harian.wilsiman.teachhelp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                // Obtém o inflador do layout
                LayoutInflater inflater = getLayoutInflater();

                // Defini o layout da caixa de diálogo
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.tvRecSenha, null));
                // Navegação para tela de HomeActivity
               setPositiveButton(R.string.btnRecSenha, new DialogInterface.OnClickListener() {
                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                            }
                        })
                        .setNegativeButton(R.string.btnVoltar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //LoginDialogFragment.this.getDialog().cancel();
                            }
                        });
               // return builder.create();

            }
        });
    }

}