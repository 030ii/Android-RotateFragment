package com.example.helllo.myfragmentlistview;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ListFragment listFragment;
    DetailFragment detailFragment;

    int rotate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 처음 화면 방향에 따른 처리 (회전 없이 기본 화면 방향)
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 세로 화면일 때
            rotate = 0;
            listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
            detailFragment = new DetailFragment();
        }
        else {
            // 가로 화면일 때
            rotate = 1;
            listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
            detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
            getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();
        }

    }

    public void onFragmentChanged(int index) {
        if (index == 0) {
            listFragment.textView.setVisibility(View.VISIBLE);
            listFragment.listView.setVisibility(View.VISIBLE);

            getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment, listFragment).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment, detailFragment).commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().remove(listFragment).commit();
        getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){ // 세로 방향 시 처리
            rotate = 0;
            listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
            detailFragment = new DetailFragment();

        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){ // 가로 방향 전환 시 처리
            rotate = 1;
            listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
            detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);

        }
        getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();
    }



}
