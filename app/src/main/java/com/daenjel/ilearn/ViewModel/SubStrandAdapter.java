package com.daenjel.ilearn.ViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daenjel.ilearn.Model.Sub_Strand_Model;
import com.daenjel.ilearn.R;

import java.util.List;

public class SubStrandAdapter extends RecyclerView.Adapter<SubStrandAdapter.MyHolder>{

    private Context context;
    private List<Sub_Strand_Model> items;

    public SubStrandAdapter(Context context, List<Sub_Strand_Model> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public SubStrandAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_sub_strands,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubStrandAdapter.MyHolder holder, int position) {
        Sub_Strand_Model itemList = items.get(position);
        holder.strand.setText(itemList.getStrands());
        holder.tracker.setText(itemList.getTracker());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView strand,tracker;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tracker = itemView.findViewById(R.id.tracker);
            strand = itemView.findViewById(R.id.txtStrand);
        }
    }
}