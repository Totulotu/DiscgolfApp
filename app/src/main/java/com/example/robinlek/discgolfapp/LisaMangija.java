package com.example.robinlek.discgolfapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LisaMangija extends DialogFragment {

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//
//        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//
//        builder.setView(inflater.inflate(R.layout.activity_lisa_mangija, null));
//        builder.setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        return builder.create();
//    }
//
//        @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lisa_mangija);
//
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        int laius = dm.widthPixels;
//        int korgus = dm.heightPixels;
//
//        getWindow().setLayout((int)(laius * 0.75), (int)(korgus * 0.55));
//
//        Button lisaBtn = findViewById(R.id.lisaBtn);
//        lisaBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                EditText mangijaNimiEDT = findViewById(R.id.mangijaNimiEDT);
//
//                boolean onNimi = mangijaNimiEDT.getText().toString().trim().equalsIgnoreCase("");
//
//                if(onNimi) {
//                    mangijaNimiEDT.setError("Palun sisesta nimi");
//                }
//
//                if(!onNimi) {
//                    String mangijaNimi = mangijaNimiEDT.getText().toString();
//
//                    Intent lisaMangijaIntent = new Intent(LisaMangija.this, PunktideLugemisActivity.class);
//                    lisaMangijaIntent.putExtra("mangijaNimi", mangijaNimi);
//
//                    startActivity(lisaMangijaIntent);
//                }
//
//            }
//        });
//
//    }
}
