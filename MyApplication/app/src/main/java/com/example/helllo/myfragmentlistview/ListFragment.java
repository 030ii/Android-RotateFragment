package com.example.helllo.myfragmentlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by helllo on 2017. 5. 19..
 */

public class ListFragment extends Fragment {
    MainActivity activity;
    ListView listView;

    TextView textView;
    MyAdapter adapter;
    Bundle bundle = new Bundle();

    //가수 앨범을 담을 리스트(SingerItem 객체를 담아둘 ArrayList 생성)
    ArrayList<SingerItem> album = new ArrayList<SingerItem>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        album.add(new SingerItem(R.drawable.img01, "정은지", "에이핑크", "너란 봄"));
        album.add(new SingerItem(R.drawable.img02, "트와이스", "JYP엔터테인먼트", "KNOCK KNOCK"));
        album.add(new SingerItem(R.drawable.img03, "하이라이트", "어라운드어스", "얼굴 찌프리지 말아요"));
        album.add(new SingerItem(R.drawable.img04, "워너", "YG엔터테인먼트", "REALLY REALLY"));
        album.add(new SingerItem(R.drawable.img05, "걸스데이", "드림티엔터테인먼트", "I'LL BE YOURS"));
        album.add(new SingerItem(R.drawable.img06, "이엑스아이디", "바나나컬쳐엔터테인먼트", "낮 보다는 밤"));
        album.add(new SingerItem(R.drawable.img07, "지코", "세븐시즌스", "SHES A BABY"));

        adapter = new MyAdapter(getActivity(), R.layout.singer_item, album); // 어댑터 객체 생성

        listView = (ListView) rootView.findViewById(R.id.listView);
        textView = (TextView) rootView.findViewById(R.id.textView);

        listView.setAdapter(adapter); //어댑터 객체를 리스트 뷰에 설정

        //리스트뷰에서 아이템 클릭시 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* params
               - parent : 클릭한 아이템을 포함하는 부모 뷰(ListView)
               - view : 클릭한 항목의 View
               - position : 클릭한 아이템의 Adepter에서의 위치값(0, 1, 2,...)
               - id : DB를 사용했을 때 Cursor의 id 값값
            */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                activity = (MainActivity) getActivity();
                //position을 이용해 어댑터에서 아이템을 가져옴
                SingerItem curItem = (SingerItem) adapter.getItem(position);

                //getName() 메서드를 이용하여 아이템에서 이름을 가져옴
                String curName = curItem.getName();

                Toast.makeText(getContext(), "안녕하세요. " + curName + "입니다.", Toast.LENGTH_LONG).show();

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("img", album.get(position).resId);
                dataBundle.putString("name", album.get(position).name);
                dataBundle.putString("company", album.get(position).company);
                dataBundle.putString("song", album.get(position).song);

                activity.detailFragment.setArguments(dataBundle);

                if(activity.rotate == 0){ // 세로
                    textView.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.INVISIBLE);
                    activity.onFragmentChanged(1);

                } else if(activity.rotate == 1){ // 가로
                    activity.detailFragment.initView();
                }

            }
        });

        return rootView;
    }


}
