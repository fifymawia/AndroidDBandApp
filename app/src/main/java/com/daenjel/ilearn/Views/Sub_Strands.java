package com.daenjel.ilearn.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.daenjel.ilearn.Interface.ClickListener;
import com.daenjel.ilearn.Interface.RecyclerTouchListener;
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
        strandsView.addOnItemTouchListener(new RecyclerTouchListener(this, strandsView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getApplicationContext(), Slides.class));
                Toast.makeText(getApplicationContext(), "Sub Strand "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void loadItems() {
        itemList.add(new Sub_Strand_Model("Number Concept","14%"));
        itemList.add(new Sub_Strand_Model("Whole Numbers","78%"));
        itemList.add(new Sub_Strand_Model("Addition","56%"));
        itemList.add(new Sub_Strand_Model("Subtraction","5%"));
    }
}
