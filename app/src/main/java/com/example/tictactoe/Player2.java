package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Player2 extends AppCompatActivity {
    String option="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);
        //Main

        ImageView img1 = findViewById(R.id.imgview1);
        Button bt1 = findViewById(R.id.bt1);
        EditText et1 = findViewById(R.id.player1);

        //intent from MainActivity.java
        Intent intent = getIntent();
        String player1Name = intent.getStringExtra("player1name");
        String player1Option = intent.getStringExtra("p1Option");
        System.out.println("Player1 Name "+player1Name);
        System.out.println("Player Option "+player1Option);

        //et1.setText(player1Name);

        ArrayList<String> arrayList = new ArrayList<String>();
        if(player1Option.equals("select image") || player1Option.equals("x"))
        {
            arrayList.clear();
            arrayList.add("superman");
            arrayList.add("batman");
            arrayList.add("o");

        }
        else if(player1Option.equals("superman"))
        {
            arrayList.clear();

            arrayList.add("batman");
            arrayList.add("x");
            arrayList.add("o");
        }else if(player1Option.equals("batman"))
        {
            arrayList.clear();

            arrayList.add("superman");
            arrayList.add("x");
            arrayList.add("o");
        }else if(player1Option.equals("o"))
        {
            arrayList.clear();

            arrayList.add("superman");
            arrayList.add("batman");
            arrayList.add("x");
        }
        Spinner sp1 = findViewById(R.id.spinner1);
        //Creating ArrayAdopter fro player2
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arrayList);

        //Adding elements into spinner
        sp1.setAdapter(arrayAdapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                option = parent.getItemAtPosition(position).toString();
                System.out.println("2 "+option);

                if(option.equals("superman"))
                {
                    img1.setImageResource(R.drawable.superman);
                }else if(option.equals("batman"))
                {
                    img1.setImageResource(R.drawable.batman);
                }else if(option.equals("x"))
                {
                    img1.setImageResource(R.drawable.x);
                }else if(option.equals("o"))
                {
                    img1.setImageResource(R.drawable.o);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Player2.this,GameTime.class);
                intent1.putExtra("Player1Name",player1Name);
                intent1.putExtra("Player1Option",player1Option);
                intent1.putExtra("Player2Name",et1.getText().toString());
                intent1.putExtra("Player2Option",option);
                startActivity(intent1);
            }
        });
    }
}