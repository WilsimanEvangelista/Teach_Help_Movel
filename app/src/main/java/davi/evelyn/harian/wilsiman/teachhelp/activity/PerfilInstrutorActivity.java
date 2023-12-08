package davi.evelyn.harian.wilsiman.teachhelp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import davi.evelyn.harian.wilsiman.teachhelp.R;
import davi.evelyn.harian.wilsiman.teachhelp.model.Instrutor;
import davi.evelyn.harian.wilsiman.teachhelp.model.ViewInstrutorViewModel;
import davi.evelyn.harian.wilsiman.teachhelp.util.Config;
import davi.evelyn.harian.wilsiman.teachhelp.util.ImageCache;

public class PerfilInstrutorActivity extends AppCompatActivity {

    Instrutor instrutorSalvo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_instrutor);

        // Para obter os detalhes do produto, a app envia o id do produto ao servidor web. Este
        // último responde com os detalhes do produto referente ao pid.

        // O pid do produto é enviado para esta tela quando o produto é clicado na tela de Home.
        // Aqui nós obtemos o pid.
        Intent i = getIntent();
        String pid = i.getStringExtra("idAluno");

        EditText etData = findViewById(R.id.etData);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        final Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog startTimeDlg = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etData.setText(formatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        ImageButton imbCalendario = findViewById(R.id.imbCalendario);
        imbCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimeDlg.show();
            }
        });

        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        ViewInstrutorViewModel viewProductViewModel = new ViewModelProvider(this).get(ViewInstrutorViewModel.class);

        // O ViewModel possui o método getProductDetailsLD, que obtém os detalhes de um produto em
        // específico do servidor web.
        //
        // O método getProductDetailsLD retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou. Ele guarda os detalhes de um produto que o servidor
        // entregou para a app.
        LiveData<Instrutor> instrutorLiveData = viewProductViewModel.getInstrutorDetailsLD(pid);

        // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado contendo uma produto
        // será guardado dentro do LiveData. Neste momento o
        // LiveData avisa que o produto chegou chamando o método onChanged abaixo.
        instrutorLiveData.observe(this, new Observer<Instrutor>() {
            @Override
            public void onChanged(Instrutor instrutor) {

                // product contém os detalhes do produto que foram entregues pelo servidor web
                if(instrutor != null) {

                    instrutorSalvo = instrutor;

                    // aqui nós obtemos a imagem do produto. A imagem não vem logo de cara. Primeiro
                    // obtemos os detalhes do produto. Uma vez recebidos os campos de id, nome, preço,
                    // descrição, criado por, usamos o id para obter a imagem do produto em separado.
                    // A classe ImageCache obtém a imagem de um produto específico, guarda em um cache
                    // o seta em um ImageView fornecido.
                    ImageView imvInstrutorPhoto = findViewById(R.id.ivPhotoInstrutor);
                    if(instrutor.foto == null) {
                        imvInstrutorPhoto.setImageResource(R.drawable.no_image);
                    }
                    else {
                        ImageCache.loadImageUrlToImageView(PerfilInstrutorActivity.this, Config.TEACHHELP_APP_RAIZ_URL + instrutor.foto, imvInstrutorPhoto, -1, 200);
                    }

                    // Abaixo nós obtemos os dados do produto e setamos na interfa de usuário
                    TextView tvName = findViewById(R.id.tvNomeProf);
                    tvName.setText(instrutor.nome);


                    TextView tvEndereco = findViewById(R.id.tvEnderecoProf);
                    tvEndereco.setText(Instrutor.endereco);

                    TextView tvEmail = findViewById(R.id.tvEmail);
                    tvEmail.setText(instrutor.email);

                    TextView tvDescricao = findViewById(R.id.tvDescProf);
                    tvDescricao.setText(instrutor.descricao);

                    TextView tvMateria = findViewById(R.id.tvMateria);
                    tvMateria.setText(instrutor.materia);

                    TextView tvCurriculo = findViewById(R.id.tvCurriculo);
                    tvCurriculo.setText(instrutor.curriculo);
                }
                else {
                    Toast.makeText(PerfilInstrutorActivity.this, "Não foi possível obter os detalhes do instrutor", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button agendarAula = findViewById(R.id.btnAgendarAula);
        agendarAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = etData.getText().toString();
                if (data.isEmpty()) {
                    Toast.makeText(PerfilInstrutorActivity.this, "Campo de data não preenchido", Toast.LENGTH_LONG);
                    return;
                }
                if (instrutorSalvo == null) {
                    Toast.makeText(PerfilInstrutorActivity.this, "Os dados do instrutor ainda não foram carregados", Toast.LENGTH_LONG);
                    return;
                }
                String textoEmail = "Olá " + instrutorSalvo.nome + "!\n\n Gostaria de marcar uma aula com você para o dia " + data + ". \n\n Desde já, agradeço.";

                //criando uma intenção de envio navegando para a tela em questão
                Intent i = new Intent(Intent.ACTION_SENDTO);

                //especificando a utilização de apps de email
                i.setData(Uri.parse("mailto:"));

                //criando um array de email
                String[] emails = new String[] {instrutorSalvo.email};

                //inserindo os textos digitados pelo usuário na app de email
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, "Teachhelp: marcar aula");
                i.putExtra(Intent.EXTRA_TEXT, textoEmail);

                    //se tivesse mais um app de email, createChooser ("criar escolhedor")
                startActivity(Intent.createChooser(i, "Escolha o APP"));

            }
        });
    }
}