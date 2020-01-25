package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class ContactList extends AppCompatActivity {

    DataBase db;
    RecyclerView recyclerView;
    ContactRecycle contactRecycle;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);





        context=this;

        db=new DataBase(this);
        List<Contact> conlist=db.getContactList();

        recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        contactRecycle=new ContactRecycle(context,conlist);
        recyclerView.setAdapter(contactRecycle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_2,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.menu_add){
            Intent intent=new Intent(ContactList.this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
