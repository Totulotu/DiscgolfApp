package com.example.robinlek.discgolfapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AlguseEkraan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alguse_ekraan);

        Button algusBtn = (Button)findViewById(R.id.algusBtn);                                      // Teeb nupu
        algusBtn.setOnClickListener(new View.OnClickListener() {                                    // Jälgib nupule vajutamist, kui
                                                                                                    //  vajutatakse liigub kood edasi
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), InfoActivity.class);       // Teeb intenti ehk tegevuse, mis läheb
                                                                                                    //  järgmisele activity-le ehk ekraanile
                startActivity(startIntent);                                                         // Käivitab intenti ja liigub
                                                                                                    //  järgmisele activity-le
            }
        });
    }
}
