package davi.evelyn.harian.wilsiman.teachhelp;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import davi.evelyn.harian.wilsiman.teachhelp.model.Instrutor;

public class InstrutorComparator extends DiffUtil.ItemCallback<Instrutor> {
    @Override
    public boolean areItemsTheSame(@NonNull Instrutor oldItem, @NonNull Instrutor newItem) {
        return oldItem.email.equals(newItem.email);
    }


    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Instrutor oldItem, @NonNull Instrutor newItem) {
        return oldItem.nome.equals(newItem.nome) &&
                oldItem.descricao.equals(newItem.descricao) &&
                oldItem.curriculo.equals(newItem.curriculo)&&
                oldItem.foto.equals(newItem.foto) &&
                oldItem.email.equals(newItem.email) &&
                oldItem.materia.equals(newItem.materia) &&
                oldItem.endereco.equals(newItem.endereco);
    }

}
