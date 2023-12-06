package davi.evelyn.harian.wilsiman.teachhelp;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import davi.evelyn.harian.wilsiman.teachhelp.model.Instrutor;

public class ItemInstrutoresAdapter extends PagingDataAdapter<Instrutor, MyViewHolder> {

    public ItemInstrutoresAdapter(@NonNull DiffUtil.ItemCallback<Instrutor> diffCallback) {
        super(diffCallback);
    }
    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_instrutores, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Instrutor imageData = getItem(position);

        Bitmap thumb = imageData.foto;
        ImageView imageView = holder.itemView.findViewById(R.id.imgButton);
        imageView.setImageBitmap(thumb);
    }
}