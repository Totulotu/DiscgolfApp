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

                final EditText inimesteArvEditText = findViewById(R.id.et_inimesteArv);                                     // Aktiveerib EditTexti nimega inimesteArv, kuhu inimene saab infot sisestada
                final EditText radadeArvEditText = findViewById(R.id.et_radadeArv);                                         // Aktiveerib teise EditTexti nimega radadeArv

                boolean onInimesi = inimesteArvEditText.getText().toString().trim().equalsIgnoreCase("");      // Teeb boolean-i onInimesi , mis kontrollib kas inimsteArvu EditText-i on midagi sisestatud, kui ei ole siis teeb selle vääraks
                boolean onRada = radadeArvEditText.getText().toString().trim().equalsIgnoreCase("");           // Teeb boolean-i onRada, mis töötab samamoodi nagu eelmine, aga radadeArvu EditText-iga

                if(onInimesi) {                                                                                             // Kui onInimesi on väär siis kood liigub järgmisele reale, muidu jätab selle vahele
                    inimesteArvEditText.setError("Palun sisesta arv");                                                          // Paneb errori inimesteArvu EditText-i
                }

                if(onRada) {                                                                                                // Kui onRada on väär siis kood liigub järgmisele reale, muidu jätab selle vahele
                    radadeArvEditText.setError("Palun sisesta arv");                                                            // Paneb errori radadeArvu EditText-i
                }

                if((!onRada) && (!onInimesi)) {                                                                             // Kui onRada ja onInimesi on tõesed, siis liigub kood edasi, muidu jätab kõik vahele kuni reani 51
                    final int inimesteArv = Integer.parseInt(inimesteArvEditText.getText().toString());                     // Salvestab täisarvu inimesteArvu EditText-ist integer-iks (täisarvuks)
                    final int radadeArv = Integer.parseInt(radadeArvEditText.getText().toString());                         // Salvestab täisarvu radadeArvu EditText-ist integer-iks (täisarvuks)

                    Intent inimesteArvIntent = new Intent(InfoActivity.this, PunktideLugemisActivity.class);  // Teeb Intenti (kavatsuse) nimega  inimesteArvIntent, mis saadab inimeste arvu järgmisele Activity-le
                    inimesteArvIntent.putExtra("inimesteArv", inimesteArv);                                           // Lisab ekstra infot inimesteArvInent-i

                    Intent radadeArvIntent = new Intent(InfoActivity.this, PunktideLugemisActivity.class);    // Teeb Intenti (kavatsuse) nimega radadeArvIntent, mis saadab radade arvu järgmisele Activity-le
                    inimesteArvIntent.putExtra("radadeArv", radadeArv);                                               // Lisab ekstra infot radadeArvIntent-i

                    Intent startPunktideActivity = new Intent(getApplicationContext(), PunktideLugemisActivity.class);      // Teeb Intenti (kavatsuse), mis liigub järgmisele Activity-le
                    startActivity(inimesteArvIntent);                                                                       // Käivitab inimesteArvIntent-i
                    startActivity(radadeArvIntent);                                                                         // Käivitab radadeARvIntent-i
                    startActivity(startPunktideActivity);                                                                   // Kävitab järgmisele Activity-le liikuva Intent-i
                }
            }
        });
    }
}
