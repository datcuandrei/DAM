package com.example.laborator4_dam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvAfisare;
    private Button btnDeschideAdaugare;
    private Button btnVeziLista;


    //LAB 5 -> Declaram lista globala de vehicule
    private ArrayList<DetailingVehicle> listaMasini = new ArrayList<>();
    //Launcher
    private final ActivityResultLauncher<Intent> addVehicleLauncher= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result->{
                if(result.getResultCode() == RESULT_OK && result.getData() !=null){
                    Intent data = result.getData();
                    //preluam obiectul Serializable trimis din AddVehicleActivity
                    DetailingVehicle vehicle = (DetailingVehicle) data.getSerializableExtra("detailing_obiect");

                    if(vehicle!=null){
                        //Lab 5 -> adaugam obiectul in lista
                        listaMasini.add(vehicle);

                        //Afisam in Textview si intr-un toast
                        tvAfisare.setText("\nUltimul obiect adaugat: " +vehicle.toString()) ;
                        Toast.makeText(this, "\nObiect preluat cu succes!", Toast.LENGTH_LONG).show();
                        tvAfisare.setText("\nMasini salvate in lista: " + listaMasini.size())   ;
                        Toast.makeText(this, "\nMasina adaugata cu succes!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvAfisare=findViewById(R.id.tvAfisare);
        btnDeschideAdaugare=findViewById(R.id.btnDeschideAdaugare);

        //Cand apasam click pe buton ne trimite in cealalta activitate
        btnDeschideAdaugare.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, AddVehicleActivity.class);
            addVehicleLauncher.launch(intent);
        });

        //Lab5 ->  buton care deschide a treia activitate (ListVehicles) si trimite lista
        btnVeziLista.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, ListVehiclesActivity.class);
            intent.putExtra("cheie_lista", listaMasini); // Trimitem intregul ArrayList
            startActivity(intent);
        });
    }
}