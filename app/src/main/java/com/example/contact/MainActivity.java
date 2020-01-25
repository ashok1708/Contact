package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.bumptech.glide.request.RequestOptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    DataBase db;

    EditText edtFname,edtMname,edtLname,edtCom,edtPhone,edtEmail;
    ImageView imgExp,imgDes;
    ImageView imgPic;
    Spinner spinner;
    ImageView ivBack;
    Pattern p;
    Matcher m;
    String imagePath;




    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DataBase(this);

        edtFname=findViewById(R.id.edtFname);
        edtMname=findViewById(R.id.edtMname);
        edtLname=findViewById(R.id.edtLname);
        edtPhone=findViewById(R.id.edtPhone);
        edtCom=findViewById(R.id.edtCompany);
        edtEmail=findViewById(R.id.edtMail);

        imgExp=findViewById(R.id.imgExp);
        imgDes=findViewById(R.id.imgDes);

        imgPic=findViewById(R.id.imgPic);
        spinner=findViewById(R.id.spnr);

        ivBack=findViewById(R.id.iv_back);



        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.group_array));
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterType);

        imgExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgExp.setVisibility(View.INVISIBLE);
                imgDes.setVisibility(View.VISIBLE);

                edtMname.setVisibility(View.VISIBLE);
                edtLname.setVisibility(View.VISIBLE);
            }
        });

        imgDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgExp.setVisibility(View.VISIBLE);
                imgDes.setVisibility(View.INVISIBLE);

                edtMname.setVisibility(View.GONE);
                edtLname.setVisibility(View.GONE);
            }
        });

        imgPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });





    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            imagePath=selectedImage.toString();

            //imgPic.setImageURI(selectedImage);
             RequestOptions circleOptions = new RequestOptions()
                    .centerCrop()
                    .circleCrop()      // responsible for circle crop
                    .placeholder(R.color.colorGrey)    // replace with your placeholder image or remove if don't want to set
                    .error(R.color.colorGrey)     // replace with your placeholder image or remove if don't want to set
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(selectedImage) .apply(circleOptions).centerCrop().into(imgPic);
        }
    }


            @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.menu)
        {
            String Fname=edtFname.getText().toString();
            if(TextUtils.isEmpty(Fname))
            {
                edtFname.setError("Fill the First Name.");
            }
            else{
                String Mname=edtMname.getText().toString();
                if(Mname.isEmpty())
                {
                    Mname="";
                }

                String Lname=edtLname.getText().toString();
                if(TextUtils.isEmpty(Lname))
                {
                    edtLname.setError("Fill the Surname.");
                }
                else
                {
                    String mobile=edtPhone.getText().toString();
                    if(TextUtils.isEmpty(mobile))
                    {
                        edtPhone.setError("Mobile Number cannot be empty.");

                   }
                    else{
                        if(!(mobile.length()>=11&&mobile.length()<=15))
                        {
                            edtPhone.setError("Mobile Number should be in between 11 to 15.");
                        }
                        else
                        {

                            String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                            p = Pattern.compile(EMAIL_STRING);
                            m = p.matcher(edtEmail.getText().toString());
                            if(!m.matches()){
                                edtEmail.setError("Email id not vaild...");
                            }
                            else{
                                boolean isInserted= db.insertData(edtFname.getText().toString(),edtMname.getText().toString(),edtLname.getText().toString(),edtPhone.getText().toString(),edtEmail.getText().toString(),imagePath);
                                if(isInserted)
                                {
                                    Toast.makeText(MainActivity.this, "Contact Saved", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this, ShowDetail.class);
                                    intent.putExtra("mobile",edtPhone.getText().toString());
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(MainActivity.this, "Failed to Save", Toast.LENGTH_LONG).show();

                        }


                        }
                    }

                }

            }

             }
        return super.onOptionsItemSelected(item);
    }
}
