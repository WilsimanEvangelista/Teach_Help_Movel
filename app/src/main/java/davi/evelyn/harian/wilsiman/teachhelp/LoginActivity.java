package davi.evelyn.harian.wilsiman.teachhelp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import davi.evelyn.harian.wilsiman.teachhelp.model.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    static int RESULT_REQUEST_PERMISSION = 2;
    LoginActivity loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        List<String> permissions = new ArrayList<>();
        permissions.add(android.Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        checkForPermissions(permissions);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


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