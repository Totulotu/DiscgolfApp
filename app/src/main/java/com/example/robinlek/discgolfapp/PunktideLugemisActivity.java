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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PunktideLugemisActivity extends AppCompatActivity {

    String mangijaNimi;
    TableLayout tabelTL;
    TableRow lisaMangijaTR;
    TableRow lopetaLisamineTR;
    TableRow lopetaVaheTR;
    Button lisaMangijaBtn;
    Button arvutaBtn;
    Button lopetaLisamineBtn;
    ArrayList<TextView> algneParAL = new ArrayList<>();
    ArrayList<TextView> parAL = new ArrayList<>();
    ArrayList<TextView> punktidAL = new ArrayList<>();
    ArrayList<TextView> tulemusedAL = new ArrayList<>();
    ArrayList<TextView> tulemusedParAL = new ArrayList<>();

    int inimesteArv = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punktide_lugemis);

        Intent radadeArvIntent = getIntent();
        final int radadeArv = radadeArvIntent.getIntExtra("radadeArvInt", 0);

        final TableRow radaTR = findViewById(R.id.radaTR);
        final TableRow parTR = findViewById(R.id.parTR);
        final TextView tulemusTV = findViewById(R.id.tulemusTV);

        radaTR.removeView(tulemusTV);

        arvutaBtn = findViewById(R.id.arvutaBtn);
        lopetaLisamineBtn = findViewById(R.id.lopetaLisamineBtn);
        lisaMangijaBtn = findViewById(R.id.lisaMangijaBtn);
        lisaMangijaTR = findViewById(R.id.lisaMangijaTR);
        lopetaLisamineTR = findViewById(R.id.lopetaLisamineTR);
        lopetaVaheTR = findViewById(R.id.lopetaVaheTR);
        tabelTL = findViewById(R.id.tabelTL);

        lisaMangijaTR.removeView(arvutaBtn);
        lopetaLisamineTR.removeView(lopetaLisamineBtn);

        for(int i=0; i<radadeArv; i++) {
            TextView textView = new TextView(this);
            textView.setWidth(150);
            int num = i + 1;
            String radaNr = "" + num;
            textView.setText(radaNr);
            textView.setGravity(1);
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);

            radaTR.addView(textView);
        }

        for (int i=0; i<radadeArv; i++) {
            EditText editText = new EditText(PunktideLugemisActivity.this);
            editText.setWidth(150);
            editText.setInputType(2);
            editText.setTextSize(14);
            editText.setGravity(1);
            editText.setHint("Par");
            editText.setTextColor(Color.BLACK);

            algneParAL.add(editText);

            parTR.addView(editText);
        }

        lisaMangijaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog lisaMangijaDialog = new Dialog(PunktideLugemisActivity.this);
                lisaMangijaDialog.setContentView(R.layout.activity_lisa_mangija);
                lisaMangijaDialog.setCancelable(true);
                lisaMangijaDialog.show();

                DisplayMetrics ekraaniMoodud = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(ekraaniMoodud);

                int laius = ekraaniMoodud.widthPixels;
                int korgus = ekraaniMoodud.heightPixels;

//                Button lisaBtn = findViewById(R.id.lisaBtn);
//                lisaBtn.setWidth((int) (laius * 0.8));

//                EditText mangijaNimiEDT = findViewById(R.id.mangijaNimiEDT);
//                mangijaNimiEDT.setWidth((int)(laius * 0.80));
//                mangijaNimiEDT.setHeight((int)(korgus * 0.40));

                Window lisaMangijaWindow = lisaMangijaDialog.getWindow();
                lisaMangijaWindow.setLayout((int)(laius * 0.80), (int)(korgus * 0.40));

                final Button viimaneLisaMangijaBtn = lisaMangijaDialog.findViewById(R.id.lisaBtn);
                Button tagasiBtn = lisaMangijaDialog.findViewById(R.id.tagasiBtn);

                viimaneLisaMangijaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText mangijaNimiEDT = lisaMangijaDialog.findViewById(R.id.mangijaNimiEDT);
                        boolean onNimi = mangijaNimiEDT.getText().toString().trim().equalsIgnoreCase("");

                        if(onNimi) {
                            mangijaNimiEDT.setError("Palun sisesta nimi");
                        }

                        if(!onNimi) {
                            mangijaNimi = mangijaNimiEDT.getText().toString();

                            tabelTL.removeView(lisaMangijaTR);
                            tabelTL.removeView(lopetaVaheTR);
                            tabelTL.removeView(lopetaLisamineTR);

                            TableRow nimiTR = new TableRow(PunktideLugemisActivity.this);
                            TextView textView = new TextView(PunktideLugemisActivity.this);
                            textView.setTextSize(22);
                            textView.setTextColor(Color.BLACK);
                            textView.setText(mangijaNimi);

                            tabelTL.addView(nimiTR);
                            nimiTR.addView(textView);

                            for(int i=0; i<radadeArv; i++) {
                                EditText editText = new EditText(PunktideLugemisActivity.this);
                                editText.setWidth(150);
                                editText.setInputType(2);
                                editText.setTextSize(20);
                                editText.setGravity(1);
                                editText.setHint("0");
                                editText.setTextColor(Color.BLACK);

                                nimiTR.addView(editText);
                                punktidAL.add(editText);
                            }
                            TextView tulemusPunktidTV = new TextView(PunktideLugemisActivity.this);
                            tulemusPunktidTV.setTextSize(20);
                            tulemusPunktidTV.setGravity(1);
                            tulemusPunktidTV.setHint("0");
                            tulemusPunktidTV.setTextColor(Color.BLACK);
                            nimiTR.addView(tulemusPunktidTV);
                            tulemusedAL.add(tulemusPunktidTV);

                            TableRow parTR = new TableRow(PunktideLugemisActivity.this);
                            tabelTL.addView(parTR);

                            TextView tuhiTV = new TextView(PunktideLugemisActivity.this);
                            tuhiTV.setText("");
                            parTR.addView(tuhiTV);

                            for(int i=0; i<radadeArv; i++) {
                                TextView textViewPar = new TextView(PunktideLugemisActivity.this);

                                textViewPar.setTextSize(12);
                                textViewPar.setGravity(1);
                                parTR.addView(textViewPar);
                                parAL.add(textViewPar);
                            }
                            TextView tulemusParTV = new TextView(PunktideLugemisActivity.this);
                            tulemusParTV.setTextSize(12);
                            tulemusParTV.setGravity(1);
                            tulemusParTV.setTextColor(Color.BLACK);
                            parTR.addView(tulemusParTV);
                            tulemusedParAL.add(tulemusParTV);

                            tabelTL.addView(lisaMangijaTR);
                            tabelTL.addView(lopetaVaheTR);
                            tabelTL.addView(lopetaLisamineTR);

                            if(inimesteArv == 0) {
                                for (int i=0; i<radadeArv; i++) {
                                    TextView lisaTV = new TextView(PunktideLugemisActivity.this);
                                    lisaTV.setWidth(150);
                                    lisaMangijaTR.addView(lisaTV);
                                }
                                lisaMangijaTR.addView(arvutaBtn);
                                radaTR.addView(tulemusTV);
                                lopetaLisamineTR.addView(lopetaLisamineBtn);
                            }

                            inimesteArv += 1;
                            Toast.makeText(PunktideLugemisActivity.this, "Mängija " + mangijaNimi + " lisatud", Toast.LENGTH_SHORT).show();
                            lisaMangijaDialog.dismiss();
                        }
                    }
                });

                tagasiBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lisaMangijaDialog.dismiss();
                    }
                });

//                startActivity(new Intent(PunktideLugemisActivity.this, LisaMangija.class));
            }
        });

        lopetaLisamineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabelTL.removeView(lisaMangijaBtn);
                tabelTL.removeView(lopetaVaheTR);
                tabelTL.removeView(lopetaLisamineTR);

            }
        });

        arvutaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i<inimesteArv; i++) {

                    int tulemus = 0;

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
                            TextView loppTulemusTV = tulemusedAL.get(i);
                            String loppTulemus = String.valueOf(tulemus);
                            loppTulemusTV.setText(loppTulemus);
                        }
                    }
                }
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
