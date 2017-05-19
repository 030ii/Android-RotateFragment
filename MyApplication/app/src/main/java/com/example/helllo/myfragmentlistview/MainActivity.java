package com.example.helllo.myfragmentlistview;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListFragment listFragment;
    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        detailFragment = new DetailFragment();
    }

    public void onFragmentChanged(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, listFragment).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){ // 세로 방향 시 처리
            Toast.makeText(this, "세로", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction().remove(listFragment).commit();
            getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();

            listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
            detailFragment = new DetailFragment();

        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){ // 가로 방향 전환 시 처리
            Toast.makeText(this, "가로 ", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction().remove(listFragment).commit();
            getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();

            listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
            detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);

        }
    }
}
