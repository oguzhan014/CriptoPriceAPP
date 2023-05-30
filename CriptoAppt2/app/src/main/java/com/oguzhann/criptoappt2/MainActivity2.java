package com.oguzhann.criptoappt2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.oguzhann.criptoappt2.view.MainActivity;


public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView bottomNavim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");

        bottomNavim=findViewById(R.id.bnavim);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler,new PriceFrag()).commit();

        bottomNavim.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.price:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler,new PriceFrag()).commit();
                        break;
                    case R.id.person:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler,new PersonFrag()).commit();
                        break;
                }
                return true;
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent geriintent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(geriintent);

    }

}
