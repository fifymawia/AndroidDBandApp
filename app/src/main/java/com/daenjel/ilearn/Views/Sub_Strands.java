package com.daenjel.ilearn.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.daenjel.ilearn.R;
import com.daenjel.ilearn.Model.Sub_Strand_Model;
import com.daenjel.ilearn.ViewModel.SubStrandAdapter;

import java.util.ArrayList;
import java.util.List;

public class Sub_Strands extends AppCompatActivity {


    RecyclerView strandsView;
    List<Sub_Strand_Model> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__strands);

        itemList = new ArrayList<>();
        loadItems();
        strandsView = findViewById(R.id.listSubStrand);
        SubStrandAdapter adapter = new SubStrandAdapter(this,itemList);
        strandsView.setHasFixedSize(true);
        strandsView.setAdapter(adapter);
        strandsView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    private void loadItems() {
        itemList.add(new Sub_Strand_Model("numbers.json","Numbers"));
        itemList.add(new Sub_Strand_Model("measure.json","Measurement"));
        itemList.add(new Sub_Strand_Model("geometry.json","Geometry"));
        itemList.add(new Sub_Strand_Model("numbers.json","Numbers"));
        itemList.add(new Sub_Strand_Model("measure.json","Measurement"));
        itemList.add(new Sub_Strand_Model("data.json","Measurement"));
    }
}
