package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ShowDetail extends AppCompatActivity {

    TextView tvName,tvNumber,tvType;
    ImageView ivBack,ivPic;

    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        tvName=findViewById(R.id.tvname);
        tvNumber=findViewById(R.id.tvNumber);
        tvType=findViewById(R.id.type);
        ivBack=findViewById(R.id.iv_back);
        ivPic=findViewById(R.id.iv_pic);

        String mobile=getIntent().getStringExtra("mobile");

        db=new DataBase(this);
        Cursor res=db.searchQuery(mobile);

        if(res.moveToFirst()){
            do{

        String name=res.getString(1)+" "+res.getString(2)+" "+res.getString(3);
        tvName.setText(name.substring(0,1).toUpperCase()+name.substring(1));
        tvNumber.setText(res.getString(4));

                Uri img=Uri.parse(res.getString(6));
                RequestOptions circleOptions = new RequestOptions()
                        .centerCrop()
                        .circleCrop()      // responsible for circle crop
                        .placeholder(R.color.colorGrey)    // replace with your placeholder image or remove if don't want to set
                        .error(R.color.colorGrey)     // replace with your placeholder image or remove if don't want to set
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(this).load(img) .apply(circleOptions).centerCrop().into(ivPic);

            }while (res.moveToNext());
        }





        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowDetail.this,ContactList.class);
                startActivity(intent);
            }
        });



    }
}
