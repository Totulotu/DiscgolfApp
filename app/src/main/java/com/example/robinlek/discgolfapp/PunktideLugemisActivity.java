package com.example.robinlek.discgolfapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PunktideLugemisActivity extends AppCompatActivity {

    String mangijaNimi;     // Loob teksti nimega mangijaNimi
    int inimesteArv = 0;    // Loob täisarvu inimesteArv ja paneb väärtuseks 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punktide_lugemis);

        final Intent radadeArvIntent = getIntent();                                                // Saab kavatsuse radadeArvIntent
        final int radadeArv = radadeArvIntent.getIntExtra("radadeArvInt", 0);   // Loob täisarvu radade arv ja paneb väärtuseks eelmisel ekraanil sisestatud numbri

        final TextView tulemusTV = findViewById(R.id.tulemusTV);            // Aktiveerib tekstivaate tulemusTV
        final Button arvutaBtn = findViewById(R.id.arvutaBtn);              // Aktiveerib nupu arvutaBtn
        final Button uusMangBtn = findViewById(R.id.uusMangBtn);            // Aktiveerib nupu uusMangBtn
        final Button alustaBtn = findViewById(R.id.lopetaLisamineBtn);      // Aktiveerib nupu
        final Button lisaMangijaBtn = findViewById(R.id.lisaMangijaBtn);    // Aktiveerib lisaMangijaBtn
        final TableRow lisaMangijaTR = findViewById(R.id.lisaMangijaTR);    // Aktiveerib tabelirea lisaMangijaTR
        final TableRow alustaTR = findViewById(R.id.lopetaLisamineTR);      // Aktiveerib tabelirea alustaTR
        final TableRow lopetaVaheTR = findViewById(R.id.lopetaVaheTR);      // Aktiveerib tabelirea lopetaVaheTR
        final TableRow radaTR = findViewById(R.id.radaTR);                  // Aktiveerib tabelirea radaTR
        final TableRow parTR = findViewById(R.id.parTR);                    // Aktiveerib tabeliread parTR
        final TableLayout tabelTL = findViewById(R.id.tabelTL);             // Aktiveerib tabeli tabelTL

        final ArrayList<TextView> algneParAL = new ArrayList<>();           // Loob listi algneParAL, kuhu saab lisada tekstivaateid
        final ArrayList<TextView> parAL = new ArrayList<>();                // Loob listi parAL, kuhu saab lisada tekstivaateid
        final ArrayList<TextView> punktidAL = new ArrayList<>();            // Loob listi punktidAL, kuhu saab lisada tekstivaateid
        final ArrayList<TextView> tulemusedViskedAL = new ArrayList<>();    // Loob listi tulemusedViskedAL, kuhu saab lisada tekstivaateid
        final ArrayList<TextView> nimedAL = new ArrayList<>();              // Loob listi nimedAL, kuhu saab lisada tekstivaateid
        final ArrayList<TableRow> mangijadAL = new ArrayList<>();           // Loob listi mangijadAL, kuhu saab lisada tabeliridasid
        final ArrayList<TableRow> parMangijadAL = new ArrayList<>();        // Loob listi parMangijadAL, kuhu saab lisada tabeliridasid

        radaTR.removeView(tulemusTV);           // Eemaldab radaTR-ist vaate tulemusTV
        lisaMangijaTR.removeView(arvutaBtn);    // Eemaldab lisaMangijaTR-ist vaate arvutaBtn
        alustaTR.removeView(alustaBtn);         // Eemaldab alustaTR-ist vaate alustaBtn
        alustaTR.removeView(uusMangBtn);        // Eemaldab alustaTR-ist vaate uusMangBtn

        // Loob radade numbrid
        for(int i=0; i<radadeArv; i++) {                        // Alustab kordust, mis käib kuni väide i<radadeArv on tõene
            TextView rajaNumTV = new TextView(this);    // Loob uue tekstivaate nimega rajaNumTV
            rajaNumTV.setWidth(150);                            // Muudab rajaNumTV laiuse 150-ks
            int num = i + 1;                                    // Loob täisarvu num ja määrab summaks i + 1
            String rajaNum = "" + num;                          // Loob teksti rajaNum ja määrab tekstiks täisarvu num
            rajaNumTV.setText(rajaNum);                         // Muudab rajaNumTV teksti tajaNum-iks
            rajaNumTV.setGravity(1);                            // Muudab rajaNumTV gravitatsiooni 1-ks ehk keskele
            rajaNumTV.setTextSize(20);                          // Muudab rajaNumTV teksti suuruse 20-ks
            rajaNumTV.setTextColor(Color.BLACK);                // Muudab rajaNumTV teksti värvi musdaks

            radaTR.addView(rajaNumTV);                          // Lisab tekstivaate rajaNumTV tabeliritta radaTR
        }

        // Loob par-i alad
        for (int i=0; i<radadeArv; i++) {                                           // Alustab kordust, mis käib kuni väide i<radadeArv on tõene
            EditText parEDT = new EditText(PunktideLugemisActivity.this);   // Loob uue muudatava tekstivälja nimega parEDT
            parEDT.setWidth(150);                                                   // Muudab parEDT laiuse 150-ks
            parEDT.setInputType(2);                                                 // Muudab parEDT teksti sisestus tüübi 2-ks
            parEDT.setTextSize(14);                                                 // Muudab parEDT teksti suuruse 14-ks
            parEDT.setGravity(1);                                                   // Muudab parEDT gravitatsiooni 1-ks
            parEDT.setHint("Par");                                                  // Muudab parEDT vihjie Par-iks
            parEDT.setTextColor(Color.BLACK);                                       // Muudab parEDT teksti värvi musdaks

            algneParAL.add(parEDT);                                                 // Lisab parEDT listi algneParAL
            parTR.addView(parEDT);                                                  // Lisab ParEDT tabeliritta parTR
        }

//        DisplayMetrics ekraaniMoodud = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(ekraaniMoodud);
//
//        final int laius = ekraaniMoodud.widthPixels;
//        final int korgus = ekraaniMoodud.heightPixels;

        // Mangija lisamine
        lisaMangijaBtn.setOnClickListener(new View.OnClickListener() {  // Aktiveerib järgneva koodi, kui vajudatakse nupule lisaMangijaBtn
            @Override
            public void onClick(View v) {

                final Dialog lisaMangijaDialog = new Dialog(PunktideLugemisActivity.this);  // Loob dialoogi lisaMangijaDialog
                lisaMangijaDialog.setContentView(R.layout.activity_lisa_mangija);                   // Paneb lisaMangijaDialog-i vaateks paigutuse activity_lisa_mangija
                lisaMangijaDialog.setCancelable(true);                                              // Laseb lisaMangijaDilog-i sellest mööda vajutades kinni panna
                lisaMangijaDialog.show();                                                           // Näitab lisaMangijaDialog-i

//                Button lisaBtn = findViewById(R.id.lisaBtn);
//                lisaBtn.setWidth((int) (laius * 0.8));

//                EditText mangijaNimiEDT = findViewById(R.id.mangijaNimiEDT);
//                mangijaNimiEDT.setWidth((int)(laius * 0.80));
//                mangijaNimiEDT.setHeight((int)(korgus * 0.40));

//                Window lisaMangijaWindow = lisaMangijaDialog.getWindow();
//                lisaMangijaWindow.setLayout((int)(laius * 0.80), (int)(korgus * 0.40));

                final Button viimaneLisaMangijaBtn = lisaMangijaDialog.findViewById(R.id.lisaBtn);  // Aktiveerib nupu viimaneLisaMangijaBtn
                Button tagasiBtn = lisaMangijaDialog.findViewById(R.id.tagasiBtn);                  // Aktiveerib nupu tagasiBtn

                // Lisa mängija
                viimaneLisaMangijaBtn.setOnClickListener(new View.OnClickListener() {   // Aktiveerib järgneva koodi, kui vajudatakse nupule viimaneLisaMangijaBtn
                    @Override
                    public void onClick(View v) {

                        final EditText mangijaNimiEDT = lisaMangijaDialog.findViewById(R.id.mangijaNimiEDT);             // Loob muudatava tekstivälja mangijaNimiEDT
                        boolean onNimi = mangijaNimiEDT.getText().toString().trim().equalsIgnoreCase("");   // Loob väite onNimi, mis on tõene siis kui mangijaNimiEDT on tühi

                        if(onNimi) {                                        // Kui onNimi väide on tõene, siis liigub kodd edasi
                            mangijaNimiEDT.setError("Palun sisesta nimi");  // Paneb mangijaNimiEDT-le errori "Palun sisesta nimi"
                        }

                        if(!onNimi) {                                           // Kui onNimi väide on väär, siis liigub kood edasi
                            mangijaNimi = mangijaNimiEDT.getText().toString();  // Võtab mangijaNimiEDT-st teksti ja teeb selle tekstiks nimega mangijaNimi

                            tabelTL.removeView(lisaMangijaTR);  // Eemaldab tabelist tabelTL vaate lisaMangijaTR
                            tabelTL.removeView(lopetaVaheTR);   // Eemaldab tabelist tabelTL vaate lopetaVaheTR
                            tabelTL.removeView(alustaTR);       // Eemaldab tabelist tabelTL vaate alustaTR

                            final TableRow nimiTR = new TableRow(PunktideLugemisActivity.this);     // Loob uue tabelirea nimega nimiTR
                            TextView mangijaNimiTV = new TextView(PunktideLugemisActivity.this);    // Loob uue tekstivaate nimega mangijaNimiTV
                            mangijaNimiTV.setTextSize(22);                                                  // Muudab mangijaNimiTV teksti suuruse 22-ks
                            mangijaNimiTV.setTextColor(Color.BLACK);                                        // Muudab mangijaNimiTV teksti värvi musdaks
                            mangijaNimiTV.setText(mangijaNimi);                                             // Paneb mangijaNimiTV tekstiks mangijaNimi

                            tabelTL.addView(nimiTR);        // Lisab tabelisse tabelTL vaate nimiTR
                            nimiTR.addView(mangijaNimiTV);  // Lisab tabeliritta nimiTR vaate mangijaNimiTV
                            nimedAL.add(mangijaNimiTV);     // Lisab listi nimedAL vaate mangijaNimiTV
                            mangijadAL.add(nimiTR);         // Lisab listi mangijadAL vaate nimiTR

                            final TableRow parTR = new TableRow(PunktideLugemisActivity.this);  // Loob uue tabelirea nimega parTR
                            tabelTL.addView(parTR);                                                     // Lisab tabelisse tebelTL vaate parTR
                            parMangijadAL.add(parTR);                                                   // Lisab listi parMangijadAL vaate parTR

                            TextView tuhiTV = new TextView(PunktideLugemisActivity.this);   // Loob uued tekstivaate nimega tuhiTV
                            parTR.addView(tuhiTV);                                                  // Lisab tabeliritta vaate tuhiTV

                            ImageButton eemaldaBtn = new ImageButton(PunktideLugemisActivity.this); // Loob uue pildiga nupu nimega eemaldaBtn
                            eemaldaBtn.setImageResource(R.drawable.eemalda_nupp);                           // Muudab eemaldaBtn pildi pildiks nimega eemalda_nupp
                            eemaldaBtn.setBackgroundResource(0);                                            // Kaotab eemaldaBtn-i tagant ära halli tausta
//                            Button eemaldaBtn = new Button(PunktideLugemisActivity.this);
//                            eemaldaBtn.setBackgroundResource(R.color.sinine);
//                            eemaldaBtn.setTextColor(getResources().getColor(R.color.valge));
//                            eemaldaBtn.setText("-");
//                            eemaldaBtn.setTextSize(24);
//                            eemaldaBtn.setWidth(40);
//                            eemaldaBtn.setHeight(32);

                            // Mängija eemaldamine
                            eemaldaBtn.setOnClickListener(new View.OnClickListener() { // Aktiveerib järgneva koodi, kui vajudatakse nupule eemaldaBtn
                                @Override
                                public void onClick(View v) {
                                    tabelTL.removeView(nimiTR); // Kustutab tabelist tabelTL vaate nimiTR
                                    tabelTL.removeView(parTR);  // Kustutab tabelist tabelTL vaate parTR
                                }
                            });

                            nimiTR.addView(eemaldaBtn); // Lisab tabelirittae nimiTR vaate eemaldaBtn

                            tabelTL.addView(lisaMangijaTR); // Lisab tabelisse tabelTL vaate lisaMangijaTR
                            tabelTL.addView(lopetaVaheTR);  // Lisab tabelisse tabelTL vaate lopetaVaheTR
                            tabelTL.addView(alustaTR);      // Lisab tabelisse tabelTL vaate alustaTR

                            // AlustaBtn ja tulemuste ala lisatakse siin
                            if(inimesteArv == 0) {                                                          // Kui inimesteArv = 0, siis aktiveerub järgnev kood
                                for (int i=0; i<radadeArv+1; i++) {                                         // Alustab kordust, mis käib kuni väide i<radadearv+1 tõene
                                    TextView lisaTV = new TextView(PunktideLugemisActivity.this);   // Loob uue tekstivaate nimega lisaTV
                                    lisaTV.setWidth(150);                                                   // Paneb lisaTV laiuseks 150
                                    lisaMangijaTR.addView(lisaTV);                                          // Lisab tabeliritta lisaMangijaTR uue vaate lisaTV
                                }
                                radaTR.addView(tulemusTV);      // Lisab tabeliritta radaTR uue vaate tulemusTV
                                alustaTR.addView(alustaBtn);    // Lisab tabeliritta alustaTR uue vaate alustaBtn
                            }

                            // lisaMangijaDialog-i lõpp
                            inimesteArv += 1;                                                                                                            // Liidab inimeseteArv-ule 1
                            Toast.makeText(PunktideLugemisActivity.this, "Mängija " + mangijaNimi + " lisatud", Toast.LENGTH_SHORT).show(); // Tekitab korraks ekraani alla teksti "Mängija (mängijaNimi) listatud"
                            lisaMangijaDialog.dismiss();                                                                                                 // Sulgeb lisaMangijaDialog-i
                        }
                    }
                });

                // Tagasi tabelisse
                tagasiBtn.setOnClickListener(new View.OnClickListener() {   // Aktiveerib järgneva koodi, kui vajudatakse nupule tagasiBtn
                    @Override
                    public void onClick(View v) {
                        lisaMangijaDialog.dismiss();    // Sulgeb lisaMangijaDialog-i
                    }
                });

//                startActivity(new Intent(PunktideLugemisActivity.this, LisaMangija.class));
            }
        });

        // Mängu alustamine ehk tabeli tekitamine
        alustaBtn.setOnClickListener(new View.OnClickListener() {   // Aktiveerib järgneva koodi, kui vajudatakse nupule alustaBtn
            @Override
            public void onClick(View v) {

                final Dialog alustaDialog = new Dialog(PunktideLugemisActivity.this);   // Loob dialoogi alustaDialog
                alustaDialog.setContentView(R.layout.lopeta_lisamine);                          // Paneb alustaDialog-i vaateks paigutuse lopeta_lisamine
                alustaDialog.setCancelable(true);                                               // Laseb alustaDialog-i sellest mööda vajutades kinni panna
                alustaDialog.show();                                                            // Näitab alustaDialog-i

//                Window lopetaLisamineWindow = alustaDialog.getWindow();
//                lopetaLisamineWindow.setLayout((int)(laius * 0.80), (int)(korgus * 0.40));

                Button alustaJatkaBtn = alustaDialog.findViewById(R.id.alustaJatkaBtn);     // Aktiveerib nupu alustaJatkaBtn
                Button alustaTagasiBtn = alustaDialog.findViewById(R.id.alustaTagasiBtn);   // Aktiveerib nupu alustaTagasiBtn

                // Tabeli tekitamine
                alustaJatkaBtn.setOnClickListener(new View.OnClickListener() {  // Aktiveerib järgneva koodi, kui vajudatakse nupule alustaJatkaBtn
                    @Override
                    public void onClick(View v) {

                        for(int i = 0; i<mangijadAL.size(); i++) { // Alustab kordust, mis käib kuni väide i<mangijadAL.size()(tabelirdidade arv listis) on tõene

                            TextView mangijaNimi = nimedAL.get(i);          // Teeb uue tekstivaate mangijaNimi ja paneb tekstiks nimedAL-i listist teksti, mis on samal kohal i väärtusega
                            TableRow punktiTR = mangijadAL.get(i);          // Teeb uue tabelirea punktiTR ja paneb reaks mangijadAL-i listist rea, mis on samal kohal i väärtusega
                            TableRow parMangijadTR = parMangijadAL.get(i);  // Teeb uue tabelirea parMangijad ja paneb reaks parMngijadAL-i listist rea, mis on samal kohal i väärtusega

                            punktiTR.removeAllViews();  // Eemaldab tabelireast punktiTR kõik vaated

                            String nimi = mangijaNimi.getText().toString();                         // Loob uue teksti nimega nimi ja paneb tekstiks mangijaNimi teksti
                            TextView nimiTV = new TextView(PunktideLugemisActivity.this);   // Loob uue tekstivaate nimega nimiTV
                            nimiTV.setTextSize(22);                                                 // Muudab nimiTV teksti suuruse 22-ks
                            nimiTV.setTextColor(Color.BLACK);                                       // Muudab nimiTV teksti värvi musdaks
                            nimiTV.setText(nimi);                                                   // Paneb nimiTV tekstiks teksti nimi

                            punktiTR.addView(nimiTV);   // Lisa tabeliritta punktiTR vaate nimiTV

                            // Inimeste punkti alade tekitamine
                            for(int p=0; p<radadeArv; p++) {                                                // Alustab kordust, mis käib kuni väide p<radadearv on tõene
                                EditText punktidEDT = new EditText(PunktideLugemisActivity.this);   // Loob uue muudatava tekstivälja nimega punktidEDT
                                punktidEDT.setWidth(150);                                                   // Muudab punktidEDT laiuse 150-ks
                                punktidEDT.setInputType(2);                                                 // Muudab punktidEDT teksti sisestus tüübi 2-ks
                                punktidEDT.setTextSize(20);                                                 // Muudab punktidEDT teksti suuruse 20-ks
                                punktidEDT.setGravity(1);                                                   // Muudab punktidEDT gravitastiooni 1-ks ehk keskele
                                punktidEDT.setHint("0");                                                    // Paneb punktidEDT vihjeks "0"
                                punktidEDT.setTextColor(Color.BLACK);                                       // Muudab punktidEDT teksti värvi musdaks

                                punktiTR.addView(punktidEDT);   // Lisab tabeliritta punktiTR vaate punktidEDT
                                punktidAL.add(punktidEDT);      // Lisab listi punktidAL vaate punktidEDT
                            }
                            TextView tulemusPunktidTV = new TextView(PunktideLugemisActivity.this); // Loob uue teksitvaate nimega tulemusPunktidTV
                            tulemusPunktidTV.setTextSize(20);                                               // Muudab tulemusPunktidTV teksti suuruse 20-ks
                            tulemusPunktidTV.setGravity(1);                                                 // Muudab tulemusPunktidTV gravitatsioon 1-ks ehk keskele
                            tulemusPunktidTV.setHint("0");                                                  // Paneb tulemusPunktidTV vihjeks "0"
                            tulemusPunktidTV.setTextColor(Color.BLACK);                                     // Muudab tulemusPunktidTV teksti värvi musdaks

                            punktiTR.addView(tulemusPunktidTV); // Lisab tabeliritta punktiTR vaate tulemusPunktidTV

                            // Inimste vahele tekkiv par
                            for(int p=0; p<radadeArv; p++) {                                            // Alustab kordust, mis käib kuni väide p<radadeArv on tõene
                                TextView parTV = new TextView(PunktideLugemisActivity.this);    // Loob uue tekstivaate nimega parTV
                                parTV.setTextSize(12);                                                  // Muudab parTV teksti suuruse 12-ks
                                parTV.setGravity(1);                                                    // Muudab parTV gravitatsioon 1-ks ehk keskele

                                parMangijadTR.addView(parTV);   // lisab tabeliritta parMangijadTR vaate parTV
                                parAL.add(parTV);               // Lisab listi parAL vaate parTV
                            }
                            TextView tulemusParTV = new TextView(PunktideLugemisActivity.this); // Loob uue tekstivaate nimega tulemusParTV
                            tulemusParTV.setTextSize(12);                                               // Muudab tulemusParTV teksti suuruse 12-ks
                            tulemusParTV.setGravity(1);                                                 // Muudab tulemusParTV gravitatsiooni 1-ks ehk keskele
                            tulemusParTV.setTextColor(Color.BLACK);                                     // Muudab tulemusParTV teksti värvi musdaks

                            parMangijadTR.addView(tulemusParTV);    // Lisab tabeliritta parMangijadTR vaate tulemusParTV
                            tulemusedViskedAL.add(tulemusParTV);    // Liasb listi tulemusedViskedAL vaate tulemusParTV
                        }

                        lisaMangijaTR.removeView(lisaMangijaBtn);   // Eemaldab tabelireast lisaMangijaTR vaate lisaMangijaBtn
                        lisaMangijaTR.addView(arvutaBtn);           // Lisab tabeliritta lisaMangijaTR vaate arvutaBtn

                        tabelTL.removeView(lopetaVaheTR);   // Eemaldab tabelist tabelTL vaate lopetaVaheTR
                        tabelTL.removeView(alustaTR);       // Eemaldab tabelist tabelTL vaate alustaTR

                        alustaDialog.dismiss(); // Sulgeb alustaDialog-i

                    }
                });

                // Tagasi tabelisse
                alustaTagasiBtn.setOnClickListener(new View.OnClickListener() { // Aktiveerib järgneva koodi, kui vajudatakse nupule alustaTagasiBtn
                    @Override
                    public void onClick(View v) {
                        alustaDialog.dismiss(); // Sulgeb alustaDialog-i
                    }
                });
            }
        });

        // Punktide arvutamine
        arvutaBtn.setOnClickListener(new View.OnClickListener() {   // Aktiveerib järgneva koodi, kui vajudatakse nupule arvutaBtn
            @Override
            public void onClick(View v) {

                final Dialog arvutaDialog = new Dialog(PunktideLugemisActivity.this);   // Loob dialoogi arvutaDialog
                arvutaDialog.setContentView(R.layout.tulemused_dialog);                         // Paneb arvutaDialog-i vaateks paigutuse tulemused_dialog
                arvutaDialog.setCancelable(true);                                               // Laseb arvutaDialog-i sellest mööda vajutades kinni panna
                arvutaDialog.show();                                                            // Näitab arvutaDialog-i

                Button tulemusJatkaBtn = arvutaDialog.findViewById(R.id.tulemusJatkaBtn);   // Akriveerib nupu tulemusJatkaBtn
                Button tulemusTagasiBtn = arvutaDialog.findViewById(R.id.tulemusTagasiBtn); // Aktiveeriv nupu tulemusTagasiBtn

                tulemusJatkaBtn.setOnClickListener(new View.OnClickListener() { // Aktiveerib järgneva koodi, kui vajudatakse nupule tulemusJatkaBtn
                    @Override
                    public void onClick(View v) {

                        ArrayList<Integer> lopuPunktidAL = new ArrayList<>();   // Loob listi lopuPunktidAL, kuhu saab lisada täisarve
                        ArrayList<Integer> alguseParAL = new ArrayList<>();     // Loob listi aluseParAL, kuhu saab lisada täisarve

                        for(int i=0; i<inimesteArv; i++) {  // Alustab kordust, mis käib kuni väide i<inimesteArv on tõene

                            int tulemus = 0;    // Loob uue täisarvu tulemus ja paneb selle väärtuseks 0
                            int visked = 0;     // Loob uues täisarvu visked ja paneb selle väärtuseks 0

                            TextView tulemusedTV = new TextView(PunktideLugemisActivity.this);  // Loob uue tekstivaate nimega tulemusedTV

                            for(int r=0; r<radadeArv; r++) {    // Alustab kordust, mis käib kuni väide r<radadeArv on tõene

                                TextView algneParTV = algneParAL.get(r);                                                     // Loob tekstivaate algneParTV ja paneb tekstiks sama teksti, mis on algneParAL listis r kohal
                                boolean onPar = algneParTV.getText().toString().trim().equalsIgnoreCase("");    // Loob väite, mis on tõene, kui algneParTV on tühi

                                if(onPar) {                     // Kui onPar väide on tõene liigub kood edasi
                                    algneParTV.setText("0");    // Paneb algneParTV tekstiks 0
                                }

                                TextView mangijaPunktidTV = punktidAL.get(radadeArv * i + r);                                                // Loob tekstivaate mangijaPunktidTV ja paneb tekstiks sama teksti, mis on punktidAL listis kohal (radadeArv * i + r)
                                boolean onMangijaPunktid = mangijaPunktidTV.getText().toString().trim().equalsIgnoreCase("");   // Loob väite, mis on tõene, kui mangijaPunktidTV on tühi

                                if(onMangijaPunktid) {              // Kui onMangijaPunktid väide on tõene liigub kood edasi
                                    mangijaPunktidTV.setText("0");  // Paneb mangijaPunktidTV tekstiks 0
                                }

                                int algnePar = Integer.parseInt(algneParTV.getText().toString());               // Loob täisarvu algnePar ja paneb väärtuseks numbri, mis on algneParTV-s
                                int mangijaPunktid = Integer.parseInt(mangijaPunktidTV.getText().toString());   // Loob täisarvu mangijaPunktid ja paneb vääruseks numbri, mis on mangijaPunktidTV-s

                                visked += mangijaPunktid;   // Liidab täisarvule visked mangijaPunktid

                                lopuPunktidAL.add(mangijaPunktid);  // Lisab listi lopuPunktidAL täisarvu mangijaPunktid
                                alguseParAL.add(algnePar);          // Lisab listi alguseParAL täisarvu algnePar

                                if(algnePar == mangijaPunktid) {                        // Kui väide (algnePar = mangijaPunktid) on tõene liigub kood edasi
                                    TextView hintParTV = parAL.get(radadeArv * i + r);  // Loob uue tekstivaate hintParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    hintParTV.setTextSize(12);                          // Muudab hintParTV teksti suuruse 12-ks
                                    hintParTV.setGravity(1);                            // Muudab hindParTV gravitatsiooni 1-ks ehk keskele
                                    hintParTV.setHint("Par(0)");                        // Paneb hintParTV tekstiks "Par(0)"

                                } else if((algnePar - 4) == mangijaPunktid) {           // Kui väide ((algnePar - 4) = mangijaPunktid) on tõene liigub kood edasi
                                    tulemus -= 4;                                       // Lahutab täisarvust tulemus -4
                                    TextView hintParTV = parAL.get(radadeArv * i + r);  // Loob uue tekstivaate hintParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    hintParTV.setTextSize(12);                          // Muudab hintParTV teksti suuruse 12-ks
                                    hintParTV.setGravity(1);                            // Muudab hindParTV gravitatsiooni 1-ks ehk keskele
                                    hintParTV.setHint(" Condor(-4) ");                  // Paneb hintParTV tekstiks "Condor(-4)"

                                } else if((algnePar - 3) == mangijaPunktid) {           // Kui väide ((algnePar - 3) = mangijaPunktid) on tõene liigub kood edasi
                                    tulemus -= 3;                                       // Lahutab täisarvust tulemus -3
                                    TextView hintParTV = parAL.get(radadeArv * i + r);  // Loob uue tekstivaate hintParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    hintParTV.setTextSize(12);                          // Muudab hintParTV teksti suuruse 12-ks
                                    hintParTV.setGravity(1);                            // Muudab hindParTV gravitatsiooni 1-ks ehk keskele
                                    hintParTV.setHint(" Albatross(-3) ");               // Paneb hintParTV tekstiks "Albatross(-3)"

                                } else if((algnePar - 2) == mangijaPunktid) {           // Kui väide ((algnePar - 2) = mangijaPunktid) on tõene liigu kood edasi
                                    tulemus -= 2;                                       // Lahutab täisarvust tulemus -2
                                    TextView hintParTV = parAL.get(radadeArv * i + r);  // Loob uue tekstivaate hintParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    hintParTV.setTextSize(12);                          // Muudab hintParTV teksti suuruse 12-ks
                                    hintParTV.setGravity(1);                            // Muudab hindParTV gravitatsiooni 1-ks ehk keskele
                                    hintParTV.setHint(" Eagle(-2) ");                   // Paneb hintParTV tekstiks "Eagle(-2)"

                                } else if((algnePar - 1) == mangijaPunktid) {           // Kui väide ((algnePar - 1) = mangijaPunktid) on tõene liigu kood edasi
                                    tulemus -= 1;                                       // Lahutab täisarvust tulemus -1
                                    TextView hintParTV = parAL.get(radadeArv * i + r);  // Loob uue tekstivaate hintParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    hintParTV.setTextSize(12);                          // Muudab hintParTV teksti suuruse 12-ks
                                    hintParTV.setGravity(1);                            // Muudab hindParTV gravitatsiooni 1-ks ehk keskele
                                    hintParTV.setHint(" Birdie(-1) ");                  // Paneb hintParTV tekstiks "Birdie(-1)"

                                } else if((algnePar + 1) == mangijaPunktid) {           // Kui väide ((algnePar + 1) = mangijaPunktid) on tõene liigu kood edasi
                                    tulemus += 1;                                       // Liidab täisarvule tulemus +1
                                    TextView hintParTV = parAL.get(radadeArv * i + r);  // Loob uue tekstivaate hintParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    hintParTV.setTextSize(12);                          // Muudab hintParTV teksti suuruse 12-ks
                                    hintParTV.setGravity(1);                            // Muudab hindParTV gravitatsiooni 1-ks ehk keskele
                                    hintParTV.setHint(" Bogey(+1) ");                   // Paneb hintParTV tekstiks "Bogey(+1)"

                                } else if((algnePar + 2) == mangijaPunktid) {           // Kui väide ((algnePar + 2) = mangijaPunktid) on tõene liigu kood edasi
                                    tulemus += 2;                                       // Liidab täisarvule tulemus +2
                                    TextView hintParTV = parAL.get(radadeArv * i + r);  // Loob uue tekstivaate hintParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    hintParTV.setTextSize(12);                          // Muudab hintParTV teksti suuruse 12-ks
                                    hintParTV.setGravity(1);                            // Muudab hindParTV gravitatsiooni 1-ks ehk keskele
                                    hintParTV.setHint(" Double Bogey(+2) ");            // Paneb hintParTV tekstiks "Double Bogey(+2)"

                                } else if((algnePar + 3) == mangijaPunktid) {           // Kui väide ((algnePar + 3) = mangijaPunktid) on tõene liigu kood edasi
                                    tulemus += 3;                                       // Liidab täisarvule tulemus +3
                                    TextView hintParTV = parAL.get(radadeArv * i + r);  // Loob uue tekstivaate hintParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    hintParTV.setTextSize(12);                          // Muudab hintParTV teksti suuruse 12-ks
                                    hintParTV.setGravity(1);                            // Muudab hindParTV gravitatsiooni 1-ks ehk keskele
                                    hintParTV.setHint(" Triple Bogey(+3) ");            // Paneb hintParTV tekstiks "Triple Bogey(+3)"

                                } else if((algnePar + 4) == mangijaPunktid) {           // Kui väide ((algnePar + 4) = mangijaPunktid) on tõene liigu kood edasi
                                    tulemus += 4;                                       // Liidab täisarvule tulemus +4
                                    TextView hintParTV = parAL.get(radadeArv * i + r);  // Loob uue tekstivaate hintParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    hintParTV.setTextSize(12);                          // Muudab hintParTV teksti suuruse 12-ks
                                    hintParTV.setGravity(1);                            // Muudab hindParTV gravitatsiooni 1-ks ehk keskele
                                    hintParTV.setHint(" Quadtruple Bogey(+4) ");        // Paneb hintParTV tekstiks "Quadtruple Bogey(+4)"

                                } else if((algnePar + 4) < mangijaPunktid) {            // Kui väide ((algnePar + 4) < mangijaPunktid) on tõene liigu kood edasi
                                    tulemus += mangijaPunktid - algnePar;               // Liidab täisarvule tulemus (manijaPunktid - algnePar)
                                    int suuremPar = mangijaPunktid - algnePar;          // Loob uue täisarvu suuremPar ja paneb selle väärtuseks (mangijaPunktid - algnePar)
                                    TextView suuremParTV = parAL.get(radadeArv * i + r);// Loob uue tekstivaate suuremParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    suuremParTV.setTextSize(12);                        // Muudab suuremParTV teksti suuruse 12-ks
                                    suuremParTV.setGravity(1);                          // Muudab suuremParTV gravitatsiooni 1-ks ehk keskele
                                    suuremParTV.setHint(" +" + suuremPar + " ");         // Paneb suuremParTV tekstiks (" +" + suuremPar + " ")

                                } else if((algnePar - 4) > mangijaPunktid) {            // Kui väide ((algnePar - 4) > mangijaPunktid) on tõene liigu kood edasi
                                    tulemus += algnePar - mangijaPunktid;               // Liidab täisarvule tulemus (algnePar - mangijaPunktid)
                                    int vaiksemPar = algnePar - mangijaPunktid;         // Loob uue täisarvu vaiksemPar ja paneb selle väärtuseks (algnePar - mangijaPunktid)
                                    TextView vaiksemParTV = parAL.get(radadeArv * i + r);// Loob uue tekstivaate vaiksemParTV ja paneb tekstiks sama teksti, mis on parAL listis kohal (radadeARv * i + r)
                                    vaiksemParTV.setTextSize(12);                       // Muudab vaiksemParTV teksti suuruse 12-ks
                                    vaiksemParTV.setGravity(1);                         // Muudab vaiksemParTV gravitatsiooni 1-ks ehk keskele
                                    vaiksemParTV.setHint(" -" + vaiksemPar + " ");      // Paneb vaiksemParTV tekstiks (" -" + vaiksemPar + " ")
                                }

                                if(r == radadeArv - 1){                                         // Kui väide (r = radaderArv - 1) on tõene liigub kood edasi
                                    TextView viskeTulemusTV = tulemusedViskedAL.get(i);         // Loob uue tekstivaate viskeTulemusTV ja paneb tekstiks sama teksi, mis on tulemusedViskedAL listis kohal i
                                    String loppTulemus = String.valueOf(tulemus);               // Loob teksti nimega loppTulemus ja paneb tekstiks täisarvu tulemus väärtuse
                                    String viskeTulemus = "Viskeid: " + String.valueOf(visked); // Loob teksti nimega viskeTulemus ja paneb tekstiks "Viskeid: " + täisarvu visked väärtuse
                                    tulemusedTV.setText(loppTulemus);                           // Paneb tulemusedTV tekstiks teksti loppTulemus
                                    viskeTulemusTV.setText(viskeTulemus);                       // Paneb viskeTulemusTV tekstiks teksti viskeTulemus
                                }
                            }

                            TableRow mangijadTR = mangijadAL.get(i);    // Loob uue tabelirea nimega mangijadTR ja paneb selleks reaks mangijadAL listis kohal i oleva rea
                            TextView algneNimiTV = nimedAL.get(i);      // Loob uue tekstivaate nimega algneNimiTV ja paneb selle tekstiks listis nimedAL i kohal oleva teksti
                            mangijadTR.removeAllViews();                // Eemaldab tabelireast mangijadTR kõik vaated

                            String nimi = algneNimiTV.getText().toString();                         // Loob uue teksti nimega nimi ja paneb selle tekstiks sama teksti, mis on tekstivaates algneNimiTV
                            TextView nimiTV = new TextView(PunktideLugemisActivity.this);   // Loob uue tekstivaate nimega nimiTV
                            nimiTV.setTextSize(22);                                                 // Muudab nimiTV teksti suuruse 22-ks
                            nimiTV.setTextColor(Color.BLACK);                                       // Muudab nimiTV teksti värvi musdaks
                            nimiTV.setText(nimi);                                                   // Paneb nimiTV tekstiks teksti nimi

                            mangijadTR.addView(nimiTV); // Lisab tabeliritta mangijadTR vaate nimiTV

                            for(int j=0; j<radadeArv; j++) {                    // Alustab kordust, mis käib kuni väide j<radadeArv on tõene
                                int punktid = lopuPunktidAL.get(j);             // Loob uue täisarvu nimega punktid ja paneb selle väärtuseks lopuPunktidAL listist täisarvu, mis on kohal j
                                String lopuPunktid = Integer.toString(punktid); // Loob uue teksti nimega lopuPunktid ja paneb tekstiks täisarvu punktid

                                TextView punktidTV = new TextView(PunktideLugemisActivity.this);    // Loob uue tekstivaate nimega punktidTV
                                punktidTV.setWidth(150);                                                    // Muudab punktidTV laiuse 150-ks
                                punktidTV.setTextSize(20);                                                  // Muudab punktidTV teksti suuruse 20-ks
                                punktidTV.setGravity(1);                                                    // Muudab punktidTV gravitatsiooni 1-ks ehk keskele
                                punktidTV.setText(lopuPunktid);                                             // Paneb punktidTV tekstiks teksti lopuPunktid
                                punktidTV.setTextColor(Color.BLACK);                                        // Muudab punktidTV teksti värvi musdaks

                                mangijadTR.addView(punktidTV);  // Lisab tabeliritta mangijadTR vaate punktidTV
                            }

                            tulemusedTV.setTextSize(20);            // Muudab tulemusedTV teksti suuruse 20-ks
                            tulemusedTV.setGravity(1);              // Muudab tulemusedTV gravitatsiooni 1-ks ehk keskele
                            tulemusedTV.setTextColor(Color.BLACK);  // Muudab tulemusedTV teksti värvi musdaks
                            mangijadTR.addView(tulemusedTV);        // Lisab tabeliritta mangijadTR vaate tulemusedTV

                            lopuPunktidAL.clear();  // Teeb listi lopuPunktidAL tühjaks
                        }

                        TextView parTV = findViewById(R.id.parTV);  // Aktiveerib tekstivaate parTV
                        parTR.removeAllViews();                     // Kustutab tabelireast parTR kõik vaated
                        parTR.addView(parTV);                       // Lisab tabeliritta parTR vaate parTV

                        for(int p=0; p<radadeArv; p++) {                                                // Alustab kordust, mis käib kuni väide p<radadeArv on tõene
                            int algusPar = alguseParAL.get(p);                                          // Loob uue täisarvu nimega alusPar, mille väärtus tuleb täisarvust listis alguseParAL, mis on kohal p
                            String parString = Integer.toString(algusPar);                              // Loob uue teksti nimega parString ja paneb tekstiks täisarvu algusPar
                            TextView algusParTV = new TextView(PunktideLugemisActivity.this);   // Loob uue tekstivaate nimega algusParTV
                            algusParTV.setWidth(150);                                                   // Muudab algusParTV laiuse 150-ks
                            algusParTV.setTextSize(14);                                                 // Muudab algusParTV teksti suuruse 14-ks
                            algusParTV.setGravity(1);                                                   // Muudab algusParTV gravitatsiooni 1-ks ehk keskele
                            algusParTV.setText(parString);                                              // Paneb algusParTV tekstiks teksti parString
                            algusParTV.setTextColor(Color.BLACK);                                       // Muudab algusParTV teksti värvi musdaks

                            parTR.addView(algusParTV);  // Lisab tabeliritta parTR vaate algusParTV
                        }

                        lisaMangijaTR.removeView(arvutaBtn);    // Eemaldab tabelireast lisaMangijaTR vaate arvutaBtn
                        lisaMangijaTR.addView(uusMangBtn);      // Lisab tabeliritta lisaManijaTR vaate uusMangBtn
                        arvutaDialog.dismiss();                 // Sulge arvutaDialog-i
                    }
                });

                tulemusTagasiBtn.setOnClickListener(new View.OnClickListener() {    // Aktiveerib järgneva koodi, kui vajudatakse nupule tulemusTagasiBtn
                    @Override
                    public void onClick(View v) {
                        arvutaDialog.dismiss(); // Sulgeb arvutaDialog-i
                    }
                });
            }
        });

        uusMangBtn.setOnClickListener(new View.OnClickListener() {  // Aktiveerib järgneva koodi, kui vajudatakse nupule uusMangBtn
            @Override
            public void onClick(View v) {

                final Dialog uusMangDialog = new Dialog(PunktideLugemisActivity.this);  // Loob uue dialoogi nimega uusMangDialog
                uusMangDialog.setContentView(R.layout.uus_mang_dialog);                         // Paneb  uusMangDialog-i vaateks paigutuse uus_mang_dialog
                uusMangDialog.setCancelable(true);                                              // Laseb uusMangDialog-i sellest mööda vajutades kinni panna
                uusMangDialog.show();                                                           // Näitab uusMangDialog-i

                Button uusMangBtn = uusMangDialog.findViewById(R.id.uusMangBtn);        // Aktiveerib nupu uusMangBtn
                Button uusTagasiBtn = uusMangDialog.findViewById(R.id.uusTagasiBtn);    // Aktiveerib nupu uusTagasiBtn

                uusMangBtn.setOnClickListener(new View.OnClickListener() {  // Aktiveerib järgneva koodi, kui vajudatakse nupule uusMangBtn
                    @Override
                    public void onClick(View v) {

//                        algneParAL.clear();
//                        punktidAL.clear();
//                        tulemusedViskedAL.clear();
//
//                        TextView parTV = findViewById(R.id.parTV);
//                        parTR.removeAllViews();
//                        parTR.addView(parTV);
//
//                        for(int i=0; i<radadeArv; i++) {
//                            EditText editText = new EditText(PunktideLugemisActivity.this);
//                            editText.setWidth(150);
//                            editText.setInputType(2);
//                            editText.setTextSize(14);
//                            editText.setGravity(1);
//                            editText.setHint("Par");
//                            editText.setTextColor(Color.BLACK);
//
//                            algneParAL.add(editText);
//
//                            parTR.addView(editText);
//                        }
//
//                        for(int p=0; p<inimesteArv; p++) {
//                            TableRow mangijaParTR = parMangijadAL.get(p);
//                            mangijaParTR.removeAllViews();
//                        }
//
//                        parMangijadAL.clear();
//
//                        for(int j=0; j<inimesteArv; j++) {
//
//                            final TableRow mangijaTR = mangijadAL.get(j);
//                            mangijaTR.removeAllViews();
//                            tabelTL.removeView(mangijaTR);
//                            tabelTL.addView(mangijaTR);
//                            TextView nimiTV = nimedAL.get(j);
//                            String nimi = nimiTV.getText().toString();
//
//                            TextView mangijaTV = new TextView(PunktideLugemisActivity.this);
//                            mangijaTV.setTextSize(22);
//                            mangijaTV.setTextColor(Color.BLACK);
//                            mangijaTV.setText(nimi);
//                            mangijaTR.addView(mangijaTV);
//
//                            final TableRow parTR = new TableRow(PunktideLugemisActivity.this);
//                            tabelTL.addView(parTR);
//                            parMangijadAL.add(parTR);
//
//                            TextView tuhiTV = new TextView(PunktideLugemisActivity.this);
//                            tuhiTV.setText("hey");
//                            parTR.addView(tuhiTV);
//
//                            ImageButton eemaldaBtn = new ImageButton(PunktideLugemisActivity.this);
//                            eemaldaBtn.setImageResource(R.drawable.eemalda_nupp);
//                            eemaldaBtn.setBackgroundResource(0);
//
//                            eemaldaBtn.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    tabelTL.removeView(mangijaTR);
//                                    tabelTL.removeView(parTR);
//                                }
//                            });
//
//                            mangijaTR.addView(eemaldaBtn);
//                        }
//
//                        mangijadAL.clear();
//
//                        lisaMangijaTR.removeAllViews();
//                        tabelTL.removeView(lisaMangijaTR);
//                        tabelTL.removeView(lopetaVaheTR);
//                        tabelTL.removeView(alustaTR);
//
//                        tabelTL.addView(lisaMangijaTR);
//                        lisaMangijaTR.addView(lisaMangijaBtn);
//                        for (int l=0; l<radadeArv + 1; l++) {
//                            TextView tuhiTV = new TextView(PunktideLugemisActivity.this);
//                            lisaMangijaTR.addView(tuhiTV);
//                        }
//
//                        tabelTL.addView(lopetaVaheTR);
//                        tabelTL.addView(alustaTR);

                        finish();                       // Lõpetab kõik protsessid
                        startActivity(radadeArvIntent); // Aktiveerib tegevuse radadeArvIntent
                    }
                });

                uusTagasiBtn.setOnClickListener(new View.OnClickListener() {    // Aktiveerib järgneva koodi, kui vajudatakse nupule uusTagasiBtn
                    @Override
                    public void onClick(View v) {
                        uusMangDialog.dismiss();    // Sulgeb uusMangDialog-i
                    }
                });
            }
        });

//        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.punktide_lugemine);
//        final ScrollView scrollView = findViewById(R.id.VerticalSV);
//        final TableLayout tableLayout = findViewById(R.id.tableLayoutTest);
//
//        Intent inimesteArvIntent = getIntent();
//        int inimesteArv = inimesteArvIntent.getIntExtra("inimesteArv", 0);
//
//        Button testBtn = (Button)findViewById(R.id.testBtn);
//        testBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            EditText editText = new EditText(PunktideLugemisActivity.this);
//            editText.setText("0");
//            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//            editText.setTop(100);
//            editText.setWidth(60);
//
//            tableLayout.addView(editText);
//            }
//        });


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