package com.daenjel.ilearn.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.daenjel.ilearn.Interface.ClickListener;
import com.daenjel.ilearn.Interface.RecyclerTouchListener;
import com.daenjel.ilearn.R;
import com.daenjel.ilearn.Model.StrandsModel;
import com.daenjel.ilearn.ViewModel.StrandsAdapter;

import java.util.ArrayList;
import java.util.List;

public class Strands extends AppCompatActivity {

    RecyclerView strandsView;
    List<StrandsModel> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strands);

        itemList = new ArrayList<>();
        loadItems();
        strandsView = findViewById(R.id.listStrand);
        StrandsAdapter adapter = new StrandsAdapter(this,itemList);
        strandsView.setHasFixedSize(true);
        strandsView.setAdapter(adapter);
        strandsView.setLayoutManager(new GridLayoutManager(this,2));
        strandsView.addOnItemTouchListener(new RecyclerTouchListener(this,strandsView,new ClickListener(){

            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getApplicationContext(),Sub_Strands.class));
                Toast.makeText(getApplicationContext(), "Strands "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void loadItems() {
        itemList.add(new StrandsModel("numbers.json","Numbers"));
        itemList.add(new StrandsModel("measure.json","Measurement"));
        itemList.add(new StrandsModel("geometry.json","Geometry"));
        itemList.add(new StrandsModel("numbers.json","Numbers"));
        itemList.add(new StrandsModel("measure.json","Measurement"));
        itemList.add(new StrandsModel("data.json","Measurement"));
    }
}
