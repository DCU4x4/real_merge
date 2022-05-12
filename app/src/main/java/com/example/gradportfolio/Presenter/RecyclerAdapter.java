package com.example.gradportfolio.Presenter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.example.gradportfolio.Model.BasketData;

import com.example.gradportfolio.R;
import com.example.gradportfolio.View.MainActivity;
import com.example.gradportfolio.View.ProductDetail;
import com.example.gradportfolio.View.ShoppingBasket;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>

{
    // adapter에 들어갈 list 입니다.
    private ArrayList<BasketData> listData = new ArrayList<BasketData>();
    private Intent intent;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml(양식)을 inflate 시킵니다.(화면에 보여불 리스트 개수만큼 inflate)
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_product, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // 22.04.10 클릭 이벤트 처리중
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), ProductDetail.class);
                intent.putExtra("number" ,position);
                intent.putExtra("title", listData.get(position).getBrandTitle());
                intent.putExtra("image", listData.get(position).getImageUrl());
                intent.putExtra("product_name", listData.get(position).getProductName());
                intent.putExtra("price",listData.get(position).getProductPrice());
                v.getContext().startActivity(intent);

            }
        });
        // 생성된 ItemViewHolder에 데이터를 바인딩
        // 예를 들어 데이터가 스크롤 되어서 맨 위에있던 뷰 홀더(레이아웃) 객체가 맨 아래로 이동한다면,
        // 그 레이아웃은 재사용 하되 데이터는 새롭게 바뀔 것이다. 고맙게도 아래에서 새롭게 보여질 데이터의 인덱스 값이 position이라는 이름으로 사용가능하다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.(총 개수만큼 재활용 될 뷰에 BasketData 바인딩)
        return listData.size();
    }

    public void addItem(BasketData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }


    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private Spinner spinner;
        private ImageView imageView;

        //22.04.07 클릭 이벤트 수정 중
        public void setItem(BasketData basketData){
            textView1.setText(basketData.getBrandTitle());
            textView2.setText(basketData.getProductName());
        }

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.recycle_brand_title);
            textView2 = itemView.findViewById(R.id.recycle_product_name);
            textView3 = itemView.findViewById(R.id.recycle_product_price);
            spinner = itemView.findViewById(R.id.recycle_spinner);
            imageView = itemView.findViewById(R.id.recycle_product_image);

        }

        void onBind(BasketData data) {
            textView1.setText(data.getBrandTitle());
            textView2.setText(data.getProductName());
            textView3.setText(data.getProductPrice());
            spinner.setAdapter(data.getSpinnerAdapter());
            Glide.with(ShoppingBasket.ct).load(data.getImageUrl()).into(imageView);
        }
    }
}

