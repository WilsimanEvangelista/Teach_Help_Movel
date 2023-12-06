package davi.evelyn.harian.wilsiman.teachhelp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import davi.evelyn.harian.wilsiman.teachhelp.activity.HomeActivity;
import davi.evelyn.harian.wilsiman.teachhelp.model.Instrutor;
import davi.evelyn.harian.wilsiman.teachhelp.util.ImageCache;

public class ItemInstrutoresAdapter extends PagingDataAdapter<Instrutor, MyViewHolder> {

    HomeActivity homeActivity;

    public ItemInstrutoresAdapter(HomeActivity homeActivity, @NonNull DiffUtil.ItemCallback<Instrutor> diffCallback) {
        super(diffCallback);
        this.homeActivity = homeActivity;
    }
    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_instrutores, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (parent.getWidth() * 0.45);
        view.setLayoutParams(layoutParams);

        return new MyViewHolder(view, layoutParams.width);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Instrutor instrutor = getItem(position);

        ImageView imgInstrutor = holder.itemView.findViewById(R.id.imgInstrutor);

        // somente agora o a imagem é obtida do servidor. Caso a imagem já esteja salva no cache da app,
        // não baixamos ela de novo
        ImageCache.loadImageUrlToImageView(homeActivity, instrutor.foto, imgInstrutor, holder.w, -1);

        TextView tvNome = holder.itemView.findViewById(R.id.tvNome);
        tvNome.setText(instrutor.nome);

        TextView tvDescricao = holder.itemView.findViewById(R.id.tvDescricao);
        tvDescricao.setText(instrutor.descricao);
    }
}