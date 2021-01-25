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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String option="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et1 = findViewById(R.id.player1);



        Spinner sp1 = findViewById(R.id.spinner1);
        ImageView img1 = findViewById(R.id.imgview1);
        img1.setImageResource(R.drawable.x);

        Button bt1 = findViewById(R.id.bt1);

        //creating arraylist for player1
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("x");
        arrayList.add("o");
        arrayList.add("superman");
        arrayList.add("batman");


        //Creating ArrayAdopter fro player1
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arrayList);

        //Adding elements into spinner
        sp1.setAdapter(arrayAdapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                option = parent.getItemAtPosition(position).toString();
                System.out.println(option);
                switch(position)
                {

                    case 1:
                            img1.setImageResource(R.drawable.o);
                            break;
                    case 2:
                          img1.setImageResource(R.drawable.superman);
                          break;
                    case 3:
                           img1.setImageResource(R.drawable.batman);
                           break;
                    default:
                            img1.setImageResource(R.drawable.x);
                            break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et1.getText().toString().equals("") && !option.equals(""))
                {
                    System.out.println(et1.getText().toString());
                    System.out.println(option);

                    String player1 = et1.getText().toString();


                    Intent intent = new Intent(MainActivity.this,Player2.class);
                    intent.putExtra("player1name",et1.getText().toString());
                    intent.putExtra("p1Option",option);
                    startActivity(intent);



                }else{
                    Toast.makeText(MainActivity.this, "Enter every field", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}