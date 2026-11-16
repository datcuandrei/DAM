package com.example.laborator4_dam;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListVehiclesActivity extends AppCompatActivity {

    private ListView listViewMasini;
    private ArrayList<DetailingVehicle> listaMasini;
    private ArrayAdapter<DetailingVehicle> adapter; // am mutat adapterul aici
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_list_views);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Lab 5-> preluam lista trimita din MainActivity
        listaMasini = (ArrayList<DetailingVehicle>)getIntent().getSerializableExtra("cheie_lista");

        if(listaMasini !=null && !listaMasini.isEmpty()){
            //Folosim ArrayAdapter pentru a afisa obiectele (se foloseste automat toString())
            ArrayAdapter<DetailingVehicle> adapter = new ArrayAdapter<>(
                    this,  android.R.layout.simple_list_item_1,
                    listaMasini
            );
            listViewMasini.setAdapter(adapter)  ;
        }

        //Lab 5-> Click simplu -> toast cu obiectul selectat
        listViewMasini.setOnItemClickListener((parent, view, position, id)->{
            DetailingVehicle selectedVehicle = listaMasini.get(position);
            Toast.makeText(this, "Selectat: "+ selectedVehicle.toString(), Toast.LENGTH_LONG).show();
        });

        //Lab 5-> Click Lung -> sterge elementul din ListView
        listViewMasini.setOnItemLongClickListener((parent,view,position,id)->{
            DetailingVehicle deletedVehicle = listaMasini.get(position);
            //Stergem din Arraylist
            listaMasini.remove(position);
            //Anuntam adapterul ca s-au schimbat datele ca sa schimbe interfata vizuala
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "S-a sters: "+deletedVehicle.toString(), Toast.LENGTH_LONG).show();
            return true;
        });
    }
}