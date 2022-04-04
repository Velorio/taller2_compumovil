package com.example.Taller2_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton buttonCamera, buttonContacts, buttonMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCamera = findViewById(R.id.ButtonCamera);
        buttonContacts = findViewById(R.id.ButtonContacts);
        buttonMaps = findViewById(R.id.ButtonMaps);
    }

    public void lanzarActividadContactos(View v){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }
}