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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChanged(0);
            }
        });

        initView();

        if(activity.rotate == 1) { // 가로일 때 '돌아가기' 버튼 제거
            backBtn.setVisibility(View.GONE);
        }

        return rootView;
    }

    public void initView (){
        Bundle dataBundle = getArguments();

        if (dataBundle != null) {
            imageView.setImageResource(dataBundle.getInt("img"));
            nameTextView.setText(dataBundle.getString("name"));
            companyTextView.setText(dataBundle.getString("company"));
            songTextView.setText(dataBundle.getString("song"));
        }
    }
}
