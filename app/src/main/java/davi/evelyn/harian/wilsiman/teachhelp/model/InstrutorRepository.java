package davi.evelyn.harian.wilsiman.teachhelp.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import davi.evelyn.harian.wilsiman.teachhelp.util.Config;
import davi.evelyn.harian.wilsiman.teachhelp.util.HttpRequest;
import davi.evelyn.harian.wilsiman.teachhelp.util.Util;

/**
 * Essa classe concentra todos os métodos de conexão entre a app e o servidor web
 */
public class InstrutorRepository {

    Context context;

    public InstrutorRepository(Context context) {
        this.context = context;
    }


    public boolean register(String newName, String newEmail, String newPassword, String newDataNasc, String newEndereco) {

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.TEACHHELP_APP_URL + "cadastrar.php", "POST", "UTF-8");
        httpRequest.addParam("novo_nome", newName);
        httpRequest.addParam("novo_email", newEmail);
        httpRequest.addParam("nova_senha", newPassword);
        httpRequest.addParam("nova_data_nasc", newDataNasc);
        httpRequest.addParam("novo_endereco", newEndereco);

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo servidor. O InputStream é convertido em uma String. Essa
            // String é a resposta do servidor web em formato JSON.
            //
            // Em caso de sucesso, será retornada uma String JSON no formato:
            //
            // {"sucesso":1}
            //
            // Em caso de falha, será retornada uma String JSON no formato:
            //
            // {"sucesso":0, "erro":"usuario já existe"}
            result = Util.inputStream2String(is, "UTF-8");

            Log.i("HTTP REGISTER RESULT", result);

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, significa que o usuário foi registrado com sucesso.
            if (success == 1) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return false;

    }

    public boolean login(String email, String senha) {

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.TEACHHELP_APP_URL + "login.php", "POST", "UTF-8");
        httpRequest.setBasicAuth(email, senha);

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo servidor. O InputStream é convertido em uma String. Essa
            // String é a resposta do servidor web em formato JSON.
            //
            // Em caso de sucesso, será retornada uma String JSON no formato:
            //
            // {"sucesso":1}
            //
            // Em caso de falha, será retornada uma String JSON no formato:
            //
            // {"sucesso":0, "erro":"usuario ou senha não confere"}
            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP LOGIN RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, significa que o usuário foi autenticado com sucesso.
            if (success == 1) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return false;
    }


    public boolean addInstrutor(String descricao,String materia, String curriculo) {

        // Para cadastrar um produto, é preciso estar logado. Então primeiro otemos o login e senha
        // salvos na app.
        String login = Config.getLogin(context);
        String password = Config.getPassword(context);

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.TEACHHELP_APP_URL + "cadastroProf.php", "POST", "UTF-8");
        httpRequest.addParam("email", login);
        httpRequest.addParam("nova_descricao", descricao);
        httpRequest.addParam("nova_materia", materia);
        httpRequest.addParam("novo_curriculo", curriculo);

        // Para esta ação, é preciso estar logado. Então na requisição HTTP setamos o login e senha do
        // usuário. Ao executar a requisição, o login e senha do usuário serão enviados ao servidor web,
        // o qual verificará se o login e senha batem com aquilo que está no BD. Somente depois dessa
        // verificação de autenticação é que o servidor web irá realizar esta ação.
        httpRequest.setBasicAuth(login, password);



        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo servidor. O InputStream é convertido em uma String. Essa
            // String é a resposta do servidor web em formato JSON.
            //
            // Em caso de sucesso, será retornada uma String JSON no formato:
            //
            // {"sucesso":1}
            //
            // Em caso de falha, será retornada uma String JSON no formato:
            //
            // {"sucesso":0,"erro":"Erro ao criar produto"}
            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP ADD PRODUCT RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucess");

            // Se sucesso igual a 1, significa que o produto foi adicionado com sucesso.
            if(success == 1) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return false;
    }
    public List<Instrutor> loadInstrutor(Integer limit, Integer offSet) {
        return new ArrayList<>();
    }

    /**
     */


    public UserProfile loadProfileDetails() {

        // Para obter a lista de produtos é preciso estar logado. Então primeiro otemos o login e senha
        // salvos na app.
        String login = Config.getLogin(context);
        String password = Config.getPassword(context);

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.TEACHHELP_APP_URL + "pegar_detalhes_perfil.php", "GET", "UTF-8");
        httpRequest.addParam("email", login);

        // Para esta ação, é preciso estar logado. Então na requisição HTTP setamos o login e senha do
        // usuário. Ao executar a requisição, o login e senha do usuário serão enviados ao servidor web,
        // o qual verificará se o login e senha batem com aquilo que está no BD. Somente depois dessa
        // verificação de autenticação é que o servidor web irá realizar esta ação.
        httpRequest.setBasicAuth(login, password);

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo servidor. O InputStream é convertido em uma String. Essa
            // String é a resposta do servidor web em formato JSON.
            //
            // Em caso de sucesso, será retornada uma String JSON no formato:
            //
            // {"sucesso":1,"nome":"produto 1","preco":"10.00", "img":"www.imgur.com/img1.jpg", "descricao":"produto 1","criado_em":"2022-10-03 19:43:31.42905","criado_por":"daniel"}
            //
            // Em caso de falha, será retornada uma String JSON no formato:
            //
            // {"sucesso":0,"erro":"Erro ao obter detalhes do produto"}
            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, os detalhes do produto são obtidos da String JSON e um objeto
            // do tipo Product é criado para guardar esses dados
            if(success == 1) {

                // obtém os dados detalhados do produto. A imagem não vem junto. Ela é obtida
                // separadamente depois, no momento em que precisa ser exibida na app. Isso permite
                // que os dados trafeguem mais rápido.
                String name = jsonObject.getString("nome");
                String email = jsonObject.getString("email");
                String descricao = jsonObject.getString("descricao");
                String dt_nasc = jsonObject.getString("dt_nasc");
                String endereco = jsonObject.getString("endereco");
                String img = jsonObject.getString("foto");
                String professor = jsonObject.getString("professor");

                String materia = "";
                String curriculo = "";
                if(professor.equals("sim")) {
                    curriculo = jsonObject.getString("curriculo");
                    materia = jsonObject.getString("materia");
                }

                // Cria um objeto Product e guarda os detalhes do produto dentro dele.
                UserProfile p = new UserProfile();
                p.nome = name;
                p.email = email;
                p.descricao = descricao;
                p.endereco = endereco;
                p.img = img;
                p.dt_nasc = dt_nasc;
                p.professor = professor;
                p.materia = materia;
                p.curriculo = curriculo;
                return p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}