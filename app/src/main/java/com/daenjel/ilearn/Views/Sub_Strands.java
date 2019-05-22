package com.daenjel.ilearn.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
                View dialog = LayoutInflater.from(Sub_Strands.this).inflate(R.layout.slider,null,false);
                Button start = dialog.findViewById(R.id.btnSlider);
                AlertDialog.Builder slideDialog = new AlertDialog.Builder(Sub_Strands.this);
                slideDialog.setIcon(R.mipmap.ic_launcher);
                slideDialog.setTitle("Choose Slide");
                slideDialog.setView(dialog);
                final AlertDialog test = slideDialog.show();

                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), Display.class));
                        test.dismiss();
                    }
                });
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
