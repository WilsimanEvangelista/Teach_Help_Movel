package davi.evelyn.harian.wilsiman.teachhelp.model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Classe para representar um produto
 */
public class Instrutor {

    public String id_instrutor;
    public String id_aluno;

    public String nome;
    public String descricao;
    public static String endereco;
    public String email;
    public String curriculo;
    public String materia;

    public String foto;
    public String data_nascimento;

    public Instrutor(String id_instrutor, String id_aluno, String nome, String descricao, String endereco, String email,String curriculo, String materia, String foto, String data_nascimento){
        this.id_aluno = id_aluno;
        this.id_instrutor = id_instrutor;
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.email = email;
        this.curriculo = curriculo;
        this.materia = materia;
        this.foto = foto;
        this.data_nascimento = data_nascimento;

    }

    public Instrutor(){

    }
}

