package com.example.data;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*Созданре переменных*/
public class MainActivity extends AppCompatActivity {
    private EditText edName, edSecondName, edEmail;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";

    @Override
    /*переход на xml файл*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    /*нахождение переменных*/
    private void init(){
        edName = findViewById(R.id.edName);
        edSecondName = findViewById(R.id.edSecondName);
        edEmail = findViewById(R.id.edEmail);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }
    /*сохранение прееменных и создание нового юзера */
    public void onClickSave(View view){
        String id = mDataBase.getKey();
        String name = edName.getText().toString();
        String sec_name = edSecondName.getText().toString();
        String email = edEmail.getText().toString();
        User newUser = new User(id, name, sec_name, email);
        /*условие при котором выводится сохранено или было оставлено пустое поле*/
        if(!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(sec_name) && !TextUtils.isEmpty(email)){
            mDataBase.push().setValue(newUser);
            Toast.makeText(this, "Сохранено", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_LONG).show();
        }

    }
    /*переход на ReadActivity.class*/
    public void onClickRead(View view){
        Intent i = new Intent(com.example.data.MainActivity.this , com.example.data.ReadActivity.class);
        startActivity(i);
    }
}