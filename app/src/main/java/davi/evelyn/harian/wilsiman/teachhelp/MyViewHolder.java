package davi.evelyn.harian.wilsiman.teachhelp;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    public int w;
    public MyViewHolder(@NonNull View itemView, int w){
        super(itemView);
        this.w = w;
    }
}
