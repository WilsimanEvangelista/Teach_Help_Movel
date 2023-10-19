package davi.evelyn.harian.wilsiman.teachhelp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView etEmail

        Intent i = getIntent();

        String emailDigitado = i.getStringExtra( "Texto");
        TextView email = findViewById(R.id.etEmail);

        email.setText(emailDigitado);

        TextView tvRecSenha = findViewById(R.id.tvRecSenha);
        tvRecSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.dialog_recuperar_senha, null))
                        // Add action buttons
                        .setPositiveButton(R.string.btnRecSenha, new DialogInterface.OnClickListener() {
                            Intent in = new Intent(LoginActivity.this, HomeActivity.class);
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