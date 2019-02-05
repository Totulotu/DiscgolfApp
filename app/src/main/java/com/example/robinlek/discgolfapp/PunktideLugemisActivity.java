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
                lisaMangijaDialog.setContentView(R.layout.activity_lisa_mangija);                   // Paneb dialoogi vaateks paigutuse activity_lisa_mangija
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
                alustaDialog.setContentView(R.layout.lopeta_lisamine);                          // Paneb dialoogi vaateks paigutuse lopeta_lisamine
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
                            TextView tulemusPunktidTV = new TextView(PunktideLugemisActivity.this);
                            tulemusPunktidTV.setTextSize(20);
                            tulemusPunktidTV.setGravity(1);
                            tulemusPunktidTV.setHint("0");
                            tulemusPunktidTV.setTextColor(Color.BLACK);
                            punktiTR.addView(tulemusPunktidTV);

                            for(int p=0; p<radadeArv; p++) {
                                TextView textViewPar = new TextView(PunktideLugemisActivity.this);
                                textViewPar.setTextSize(12);
                                textViewPar.setGravity(1);
                                parMangijadTR.addView(textViewPar);
                                parAL.add(textViewPar);
                            }
                            TextView tulemusParTV = new TextView(PunktideLugemisActivity.this);
                            tulemusParTV.setTextSize(12);
                            tulemusParTV.setGravity(1);
                            tulemusParTV.setTextColor(Color.BLACK);
                            parMangijadTR.addView(tulemusParTV);
                            tulemusedViskedAL.add(tulemusParTV);
                        }

                        lisaMangijaTR.removeView(lisaMangijaBtn);
                        lisaMangijaTR.addView(arvutaBtn);

                        tabelTL.removeView(lopetaVaheTR);
                        tabelTL.removeView(alustaTR);

                        alustaDialog.dismiss();

                    }
                });

                // Tagasi tabelisse
                alustaTagasiBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alustaDialog.dismiss();
                    }
                });
            }
        });

        arvutaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog arvutaDialog = new Dialog(PunktideLugemisActivity.this);
                arvutaDialog.setContentView(R.layout.tulemused_dialog);
                arvutaDialog.setCancelable(true);
                arvutaDialog.show();

                Button tulemusJatkaBtn = arvutaDialog.findViewById(R.id.tulemusJatkaBtn);
                Button tulemusTagasiBtn = arvutaDialog.findViewById(R.id.tulemusTagasiBtn);

                tulemusJatkaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ArrayList<Integer> lopuPunktidAL = new ArrayList<>();
                        ArrayList<Integer> alguseParAL = new ArrayList<>();

                        for(int i=0; i<inimesteArv; i++) {

                            int tulemus = 0;
                            int visked = 0;
                            TextView tulemusedTV = new TextView(PunktideLugemisActivity.this);

                            for(int r=0; r<radadeArv; r++) {
                                TextView algneParTV = algneParAL.get(r);
                                boolean onPar = algneParTV.getText().toString().trim().equalsIgnoreCase("");

                                if(onPar) {
                                    algneParTV.setText("0");
                                }

                                TextView mangijaPunktidTV = punktidAL.get(radadeArv * i + r);
                                boolean onMangijaPunktid = mangijaPunktidTV.getText().toString().trim().equalsIgnoreCase("");

                                if(onMangijaPunktid) {
                                    mangijaPunktidTV.setText("0");
                                }

                                int algnePar = Integer.parseInt(algneParTV.getText().toString());
                                int mangijaPunktid = Integer.parseInt(mangijaPunktidTV.getText().toString());
                                visked += mangijaPunktid;
                                lopuPunktidAL.add(mangijaPunktid);
                                alguseParAL.add(algnePar);

                                if(algnePar == mangijaPunktid) {
                                    TextView hintParTV = parAL.get(radadeArv * i + r);
                                    hintParTV.setTextSize(12);
                                    hintParTV.setGravity(1);
                                    hintParTV.setHint("Par(0)");
                                } else if((algnePar - 4) == mangijaPunktid) {
                                    tulemus -= 4;
                                    TextView condorTV = parAL.get(radadeArv * i + r);
                                    condorTV.setTextSize(12);
                                    condorTV.setGravity(1);
                                    condorTV.setHint(" Condor(-4) ");
                                } else if((algnePar - 3) == mangijaPunktid) {
                                    tulemus -= 3;
                                    TextView condorTV = parAL.get(radadeArv * i + r);
                                    condorTV.setTextSize(12);
                                    condorTV.setGravity(1);
                                    condorTV.setHint(" Albatross(-3) ");
                                } else if((algnePar - 2) == mangijaPunktid) {
                                    tulemus -= 2;
                                    TextView condorTV = parAL.get(radadeArv * i + r);
                                    condorTV.setTextSize(12);
                                    condorTV.setGravity(1);
                                    condorTV.setHint(" Eagle(-2) ");
                                } else if((algnePar - 1) == mangijaPunktid) {
                                    tulemus -= 1;
                                    TextView condorTV = parAL.get(radadeArv * i + r);
                                    condorTV.setTextSize(12);
                                    condorTV.setGravity(1);
                                    condorTV.setHint(" Birdie(-1) ");
                                } else if((algnePar + 1) == mangijaPunktid) {
                                    tulemus += 1;
                                    TextView condorTV = parAL.get(radadeArv * i + r);
                                    condorTV.setTextSize(12);
                                    condorTV.setGravity(1);
                                    condorTV.setHint(" Bogey(+1) ");
                                } else if((algnePar + 2) == mangijaPunktid) {
                                    tulemus += 2;
                                    TextView condorTV = parAL.get(radadeArv * i + r);
                                    condorTV.setTextSize(12);
                                    condorTV.setGravity(1);
                                    condorTV.setHint(" Double Bogey(+2) ");
                                } else if((algnePar + 3) == mangijaPunktid) {
                                    tulemus += 3;
                                    TextView condorTV = parAL.get(radadeArv * i + r);
                                    condorTV.setTextSize(12);
                                    condorTV.setGravity(1);
                                    condorTV.setHint(" Triple Bogey(+3) ");
                                } else if((algnePar + 4) == mangijaPunktid) {
                                    tulemus += 4;
                                    TextView condorTV = parAL.get(radadeArv * i + r);
                                    condorTV.setTextSize(12);
                                    condorTV.setGravity(1);
                                    condorTV.setHint(" Quadtruple Bogey(+4) ");
                                } else if((algnePar + 4) < mangijaPunktid) {
                                    tulemus += mangijaPunktid - algnePar;
                                    int suuremPar = mangijaPunktid - algnePar;
                                    TextView suuremParTV = parAL.get(radadeArv * i + r);
                                    suuremParTV.setTextSize(12);
                                    suuremParTV.setGravity(1);
                                    suuremParTV.setHint("+" + suuremPar);
                                } else if((algnePar - 4) > mangijaPunktid) {
                                    tulemus += algnePar - mangijaPunktid;
                                    int vaiksemPar = algnePar - mangijaPunktid;
                                    TextView suuremParTV = parAL.get(radadeArv * i + r);
                                    suuremParTV.setTextSize(12);
                                    suuremParTV.setGravity(1);
                                    suuremParTV.setHint(" -" + vaiksemPar + " ");
                                }

                                if(r == radadeArv - 1){
                                    TextView viskeTulemusTV = tulemusedViskedAL.get(i);
                                    String loppTulemus = String.valueOf(tulemus);
                                    String viskeTulemus = "Viskeid: " + String.valueOf(visked);
                                    tulemusedTV.setText(loppTulemus);
                                    viskeTulemusTV.setText(viskeTulemus);
                                }
                            }

                            TableRow mangijad = mangijadAL.get(i);
                            TextView algneNimiTV = nimedAL.get(i);
                            mangijad.removeAllViews();

                            String nimi = algneNimiTV.getText().toString();
                            TextView nimiTV = new TextView(PunktideLugemisActivity.this);
                            nimiTV.setTextSize(22);
                            nimiTV.setTextColor(Color.BLACK);
                            nimiTV.setText(nimi);

                            mangijad.addView(nimiTV);

                            for(int j=0; j<radadeArv; j++) {
                                int punktid = lopuPunktidAL.get(j);
                                String lopuPunktid = Integer.toString(punktid);

                                TextView punktidTV = new TextView(PunktideLugemisActivity.this);
                                punktidTV.setWidth(150);
                                punktidTV.setTextSize(20);
                                punktidTV.setGravity(1);
                                punktidTV.setText(lopuPunktid);
                                punktidTV.setTextColor(Color.BLACK);

                                mangijad.addView(punktidTV);
                            }

                            tulemusedTV.setTextSize(20);
                            tulemusedTV.setGravity(1);
                            tulemusedTV.setTextColor(Color.BLACK);
                            mangijad.addView(tulemusedTV);

                            lopuPunktidAL.clear();
                        }

                        TextView parTV = findViewById(R.id.parTV);
                        parTR.removeAllViews();
                        parTR.addView(parTV);

                        for(int p=0; p<radadeArv; p++) {
                            int algusPar = alguseParAL.get(p);
                            String parString = Integer.toString(algusPar);
                            TextView algusParTV = new TextView(PunktideLugemisActivity.this);
                            algusParTV.setWidth(150);
                            algusParTV.setTextSize(14);
                            algusParTV.setGravity(1);
                            algusParTV.setText(parString);
                            algusParTV.setTextColor(Color.BLACK);

                            parTR.addView(algusParTV);
                        }

                        lisaMangijaTR.removeView(arvutaBtn);
                        lisaMangijaTR.addView(uusMangBtn);
                        arvutaDialog.dismiss();
                    }
                });

                tulemusTagasiBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arvutaDialog.dismiss();
                    }
                });
            }
        });

        uusMangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog uusMangDialog = new Dialog(PunktideLugemisActivity.this);
                uusMangDialog.setContentView(R.layout.uus_mang_dialog);
                uusMangDialog.setCancelable(true);
                uusMangDialog.show();

                Button uusMangBtn = uusMangDialog.findViewById(R.id.uusMangBtn);
                Button uusTagasiBtn = uusMangDialog.findViewById(R.id.uusTagasiBtn);

                uusMangBtn.setOnClickListener(new View.OnClickListener() {
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

                        finish();
                        startActivity(radadeArvIntent);
                    }
                });

                uusTagasiBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uusMangDialog.dismiss();
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
