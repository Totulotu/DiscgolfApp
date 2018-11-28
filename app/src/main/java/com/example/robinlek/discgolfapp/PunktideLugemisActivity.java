package com.example.robinlek.discgolfapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

public class PunktideLugemisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punktide_lugemis);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.punktide_lugemine);
        final ScrollView scrollView = findViewById(R.id.VerticalSV);
        final TableLayout tableLayout = findViewById(R.id.tableLayoutTest);

        Intent inimesteArvIntent = getIntent();
        int inimesteArv = inimesteArvIntent.getIntExtra("inimesteArv", 0);

        Intent radadeArvIntent = getIntent();
        int radadeArv = radadeArvIntent.getIntExtra("radadeArv", 0);

        Button testBtn = (Button)findViewById(R.id.testBtn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            EditText editText = new EditText(PunktideLugemisActivity.this);
            editText.setText("0");
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setTop(100);
            editText.setWidth(60);

            tableLayout.addView(editText);
            }
        });


//        for(int i=0; i!=inimesteArv; i++) {
//            EditText editText = new EditText(PunktideLugemisActivity.this);
//            editText.setText("0");
//            editText.setBackgroundColor(Color.parseColor("#FF0000"));
//            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//
//            linearLayout.addView(editText);
//        }
    }

}
