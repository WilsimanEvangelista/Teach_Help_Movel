package davi.evelyn.harian.wilsiman.teachhelp.instrutor;

import java.util.ArrayList;
import java.util.Date;

public class Instrutor {
    String nome, descricao, endereco, email, senha, curriculo;
    int foto, nota;
    Date data_nascimento;
    ArrayList<String> materias = new ArrayList<>();

    public Instrutor(String nome, String descricao, String endereco, String email, String senha,String curriculo, int foto, int nota, Date data_nascimento, ArrayList<String> materias){
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
        this.curriculo = curriculo;
        this.foto = foto;
        this.nota = nota;
        this.data_nascimento = data_nascimento;
        this.materias = materias;
    }
}
