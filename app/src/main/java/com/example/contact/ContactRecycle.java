package com.example.contact;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactRecycle extends RecyclerView.Adapter<ContactRecycle.ViewHolder> {


    Context ctx;
    List<Contact> contact_list;
    public ContactRecycle(Context ctx,List<Contact> contact_list) {
        this.contact_list=contact_list;
        this.ctx=ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        String fname=contact_list.get(position).getFname();
        String mname=contact_list.get(position).getMname();
        String lname=contact_list.get(position).getLname();

//        fname=fname.substring(0,1).toUpperCase()+fname.substring(1);
//        lname=lname.substring(0,1).toUpperCase()+lname.substring(1);

        holder.tvName.setText(fname+" "+mname+" "+lname);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(ctx,ShowDetail.class);
                intent.putExtra("mobile",contact_list.get(position).getMobile());
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contact_list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);



        }


    }




}
