package com.example.robinlek.discgolfapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button alustaLugemistBtn = (Button)findViewById(R.id.alustaLugemistBtn);                                            // Teeb nupu
        alustaLugemistBtn.setOnClickListener(new View.OnClickListener() {                                                   // Jälgib nupule vajutamist
            @Override
            public void onClick(View v) {                                                                                   // Kui nupule vajutatakse liigub kood edasi

                final EditText radadeArvEditText = findViewById(R.id.et_radadeArv);                                         // Aktiveerib teise EditTexti nimega radadeArv

                boolean onRada = radadeArvEditText.getText().toString().trim().equalsIgnoreCase("");// Teeb boolean-i onRada, mis töötab samamoodi nagu eelmine, aga radadeArvu EditText-iga

                if(onRada) {                                                                                                // Kui onRada on väär siis kood liigub järgmisele reale, muidu jätab selle vahele
                    radadeArvEditText.setError("Palun sisesta arv");                                                            // Paneb errori radadeArvu EditText-i
                }

                if((!onRada)) {                                                                             // Kui onRada ja onInimesi on tõesed, siis liigub kood edasi
                    int radadeArv = Integer.parseInt(radadeArvEditText.getText().toString());                         // Salvestab täisarvu radadeArvu EditText-ist integer-iks (täisarvuks)

                    if(radadeArv == 0) {
                        radadeArvEditText.setError("Palun sisesta 0 suurem arv");
                    } else {
                        Intent radadeArvIntent = new Intent(InfoActivity.this, PunktideLugemisActivity.class);                  // Teeb Intenti (kavatsuse) nimega radadeArvIntent, mis saadab radade arvu järgmisele Activity-le
                        radadeArvIntent.putExtra("radadeArvInt", radadeArv);                                                     // Lisab ekstra infot radadeArvIntent-i
                        startActivity(radadeArvIntent);                                                                         // Käivitab radadeArvIntent-i
                    }
//                    Intent startPunktideActivity = new Intent(getApplicationContext(), PunktideLugemisActivity.class);      // Teeb Intenti (kavatsuse), mis liigub järgmisele Activity-le
//                    startActivity(startPunktideActivity);                                                                   // Kävitab järgmisele Activity-le liikuva Intent-i
                }
            }
        });
    }
}
