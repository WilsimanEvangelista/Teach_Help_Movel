package davi.evelyn.harian.wilsiman.teachhelp.model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Classe para representar um produto
 */
public class Instrutor {
    String id, nome, descricao, endereco, email, curriculo;
    public Bitmap foto;
    Date data_nascimento;

    public Instrutor(String id, String nome, String descricao, String endereco, String email,String curriculo, Bitmap foto, Date data_nascimento){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.email = email;
        this.curriculo = curriculo;
        this.foto = foto;
        this.data_nascimento = data_nascimento;

    }
}

