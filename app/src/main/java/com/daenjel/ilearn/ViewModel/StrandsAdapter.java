package com.daenjel.ilearn.ViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.daenjel.ilearn.R;
import com.daenjel.ilearn.Model.StrandsModel;

import java.util.List;

public class StrandsAdapter extends RecyclerView.Adapter<StrandsAdapter.MyHolder> {

    private Context context;
    private List<StrandsModel> items;

    public StrandsAdapter(Context context, List<StrandsModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_strands,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        StrandsModel itemList = items.get(position);
        holder.animationView.setAnimation(itemList.getLottie());
        holder.strand.setText(itemList.getStrands());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        LottieAnimationView animationView;
        TextView strand;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            animationView = itemView.findViewById(R.id.strandImg);
            strand = itemView.findViewById(R.id.txtStrand);
        }
    }
}
