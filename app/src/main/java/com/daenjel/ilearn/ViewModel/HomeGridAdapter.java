package com.daenjel.ilearn.ViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daenjel.ilearn.R;

import java.util.List;

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.MyHolder> {

    Context context;
    List<Integer> items;
    LayoutInflater inflater;

    public HomeGridAdapter(Context context, List<Integer> items) {
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HomeGridAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = inflater.inflate(R.layout.view_home_grid,parent,false);
        return new MyHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeGridAdapter.MyHolder holder, int position) {

        holder.area.setImageResource(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView area;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            area = itemView.findViewById(R.id.areaImg);
        }
    }
}
