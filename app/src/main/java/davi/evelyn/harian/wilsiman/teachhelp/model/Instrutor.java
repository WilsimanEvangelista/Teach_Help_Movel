package davi.evelyn.harian.wilsiman.teachhelp.model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Classe para representar um produto
 */
public class Instrutor {

    public String nome;
    public String descricao;
    public String endereco;
    public String email;
    public String curriculo;
    public String materia;

    public String foto;
    Date data_nascimento;

    public Instrutor(String nome, String descricao, String endereco, String email,String curriculo, String materia, String foto, Date data_nascimento){
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.email = email;
        this.curriculo = curriculo;
        this.materia = materia;
        this.foto = foto;
        this.data_nascimento = data_nascimento;

    }
}

