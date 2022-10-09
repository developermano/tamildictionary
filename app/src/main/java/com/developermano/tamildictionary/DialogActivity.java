package com.developermano.tamildictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.*;
import java.lang.reflect.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.*;

public class DialogActivity extends AppCompatActivity {
    Button searchbutton,closebutton;
    AutoCompleteTextView searchfield;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        searchbutton=findViewById(R.id.button);
        closebutton=findViewById(R.id.button2);
        searchfield=findViewById(R.id.autoCompleteTextView);

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "dictionary.json");
        JsonParser jsr=new JsonParser();
        //String user=jsr.parse(jsonFileString).getAsJsonArray().get(56855).getAsJsonObject().get("word_list").getAsJsonArray().get(0).getAsString();
        JsonArray arr1=jsr.parse(jsonFileString).getAsJsonArray();
        JsonArray arr=arr1.get(56855).getAsJsonObject().get("word_list").getAsJsonArray();
        List<String> searchlist = new ArrayList<String>();
        for(int i = 0; i < arr.size(); i++){
            searchlist.add(arr.get(i).getAsString());
        }



        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,searchlist
        );
        searchfield.setAdapter(arrayAdapter);

        
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogActivity.super.onBackPressed();
            }
        });

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchtext=searchfield.getText().toString();
                if(searchtext==null){
                    Toast.makeText(getApplicationContext(),"please type any word",Toast.LENGTH_SHORT).show();

                }else{
                    if(searchlist.indexOf(searchtext)!=-1){
                  String  tamiloutput =arr1.get(searchlist.indexOf(searchtext)).getAsJsonObject().get("tamil").getAsString();
Toast.makeText(getApplicationContext(),tamiloutput, Toast.LENGTH_LONG).show();}
                    else{
                        Toast.makeText(getApplicationContext(),"the word is not found in my dictionary",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}