package com.example.helllo.myfragmentlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

//어댑터객체 클래스 선언(리스트뷰에 사용할 데이터를 관리하고, 각 아이템을 위한 뷰 객체를 생성)
public class MyAdapter extends BaseAdapter {
    Context mContext;//전달받은 Context 객체를 저장할 변수
    int singer_item;
    ArrayList<SingerItem> album;
    LayoutInflater inflater;

    //어댑터 생성자
    public MyAdapter(Context context, int singer_item, ArrayList<SingerItem> album) {
        mContext = context;
        this.singer_item = singer_item;
        this.album = album;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /* 어댑터를 리스트뷰에 설정하면 리스트뷰(위젯)가 자동 호출함
            - 리스트뷰가 아댑터에게 요청하는 메서드들... */

    /* 어댑터에서 관리하고 있는 데이터(아이템)의 갯수를 반환
       (itemsList의 크기(size) 반환) */
    @Override
    public int getCount() {
        return album.size();//리스트의 크기
    }

    //파라미터로 전달된 인덱스에 해당하는 데이터를 반환
    @Override
    public Object getItem(int position) {
        return album.get(position);//리스트에서 아이템을 가져와 반환
    }

    //현재 아이템의 Id값을 인덱스값(position)을 반환
    @Override
    public long getItemId(int position) {
        return position;
    }

    //리스트에 아이템을 추가
    public void addItem(SingerItem item) {
        album.add(item);
    }

    //리스트의 모든 아이템을 삭제
    public void clear() {
        album.clear();
    }

    //화면에 보일 아이템을 위한 뷰를 만들어 반환
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //아이템을 위한 레이아웃 생성
        SingerLayout singerLayout = null;

        if (convertView == null) {
            singerLayout = new SingerLayout(mContext);
        } else {
            singerLayout = (SingerLayout) convertView;
        }

        //아이템의 인덱스값(position)을 이용해 리스트에 들어있는 아이템을 가져옴
        SingerItem items = album.get(position);

        //아이템에서 이미지 리소스 id를 가져와, 레이아웃에 이미지 설정
        singerLayout.setImage(items.getResId());

        //아이템에서 이름을 가져와, 레이아웃에 이름 설정
        singerLayout.setNameText(items.getName());

        //아이템에서 소속을 가져와, 레이아웃에 소속 설정
        singerLayout.setCompany(items.getCompany());

        //아이템에서 노래를 가져와, 레이아웃에 노래 설정
        singerLayout.setSong(items.getSong());

        return singerLayout;//레이아웃을 리턴
    }
}
