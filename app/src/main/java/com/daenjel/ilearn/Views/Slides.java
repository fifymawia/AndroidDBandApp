package com.daenjel.ilearn.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daenjel.ilearn.Interface.ClickListener;
import com.daenjel.ilearn.Interface.RecyclerTouchListener;
import com.daenjel.ilearn.R;
import com.daenjel.ilearn.ViewModel.SlidesAdapter;

import java.util.ArrayList;
import java.util.List;

public class Slides extends AppCompatActivity {

    List<Integer> itemList;
    RecyclerView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slides);

        itemList = new ArrayList<>();
        mListView = findViewById(R.id.slideList);
        loadSlides();
        SlidesAdapter adapter = new SlidesAdapter(this,itemList);
        mListView.setLayoutManager(new GridLayoutManager(this,4));
        mListView.setAdapter(adapter);
        mListView.setHasFixedSize(true);
        mListView.addOnItemTouchListener(new RecyclerTouchListener(this, mListView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getApplicationContext(), Display.class));
                Toast.makeText(getApplicationContext(), "Slides "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void loadSlides() {
        itemList.add(R.drawable.slides);
        itemList.add(R.drawable.slides);
        itemList.add(R.drawable.slides);
        itemList.add(R.drawable.slides);
        itemList.add(R.drawable.slides);
        itemList.add(R.drawable.slides);
        itemList.add(R.drawable.slides);
        itemList.add(R.drawable.slides);
        itemList.add(R.drawable.slides);
    }
}
