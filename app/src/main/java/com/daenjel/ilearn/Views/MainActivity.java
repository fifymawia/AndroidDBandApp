package com.daenjel.ilearn.Views;

import android.content.Intent;
import android.os.Bundle;

import com.daenjel.ilearn.ViewModel.HomeGridAdapter;
import com.daenjel.ilearn.Interface.ClickListener;
import com.daenjel.ilearn.Interface.RecyclerTouchListener;

import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.daenjel.ilearn.R;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    String name,email;
    List<Integer> itemList;
    RecyclerView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Intent gm = getIntent();

        name = gm.getStringExtra("name");
        email = gm.getStringExtra("email");



        View headerview = navigationView.getHeaderView(0);
        TextView tvname = (TextView) headerview.findViewById(R.id.tvname);
        TextView tvemail = (TextView) headerview.findViewById(R.id.tvemail);


        tvname.setText(name);
        tvemail.setText(email);

        itemList = new ArrayList<>();
        mListView = findViewById(R.id.areaList);
        loadArea();
        HomeGridAdapter adapter = new HomeGridAdapter(this,itemList);
        mListView.setLayoutManager(new GridLayoutManager(this,3));
        mListView.setAdapter(adapter);
        mListView.setHasFixedSize(true);
        mListView.addOnItemTouchListener(new RecyclerTouchListener(this, mListView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getApplicationContext(), Strands.class));
                Toast.makeText(getApplicationContext(), "Learning area "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void loadArea() {
        itemList.add(R.drawable.math);
        itemList.add(R.drawable.english);
        itemList.add(R.drawable.kiswahili);
        itemList.add(R.drawable.cre);
        itemList.add(R.drawable.arts);
        itemList.add(R.drawable.env);
        itemList.add(R.drawable.literacy);
        itemList.add(R.drawable.hygiene);
        itemList.add(R.drawable.music);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            moveTaskToBack(true);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(getApplicationContext(), Login.class));
        } else if (id == R.id.nav_grade) {
            startActivity(new Intent(getApplicationContext(), SignUp.class));
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_faqs) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
