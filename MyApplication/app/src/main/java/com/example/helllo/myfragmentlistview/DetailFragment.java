package com.example.helllo.myfragmentlistview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by helllo on 2017. 5. 19..
 */

public class DetailFragment extends Fragment {
    MainActivity activity;
    ImageView imageView;
    TextView nameTextView, companyTextView, songTextView;
    Button backBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_detail, container, false);

        activity = (MainActivity) getActivity();

        //부분화면 레이아웃에 정의된 객체 참조
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        nameTextView = (TextView) rootView.findViewById(R.id.nameTextView);
        companyTextView = (TextView) rootView.findViewById(R.id.companyTextView);
        songTextView = (TextView) rootView.findViewById(R.id.songTextView);
        backBtn = (Button) rootView.findViewById(R.id.backBtn);

        // 돌아가기 버튼을 누르면
       backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ListFragment listFragment = new ListFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment, listFragment).commit();
                activity.onFragmentChanged(0); // 파라미터가 0이면 listFragment를 보이도록 하는것으로 정의했음
            }
        });

        initView();

        if(activity.rotate == 1) { // 가로일 때
            backBtn.setVisibility(View.GONE); // '돌아가기' 버튼 없애기
        }

        return rootView;
    }

    public void initView (){
        Bundle dataBundle = getArguments(); // 전달된 정보를 얻기 위해 dataBundle을 가져옴

        if (dataBundle != null) { // 만약 전달된 정보가 있다면
            // 그 정보들을 받아 화면에 세팅
            imageView.setImageResource(dataBundle.getInt("img"));
            nameTextView.setText(dataBundle.getString("name"));
            companyTextView.setText(dataBundle.getString("company"));
            songTextView.setText(dataBundle.getString("song"));
        }
    }
}
