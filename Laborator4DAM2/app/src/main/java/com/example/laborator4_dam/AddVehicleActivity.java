package com.example.laborator4_dam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddVehicleActivity extends AppCompatActivity {

    private EditText editTextModelMasina;
    private EditText editTextPret;
    private RadioGroup radioGroupMurdarie;
    private CheckBox checkBoxTratamentPiele;
    private Switch switchOzonificare;
    private RatingBar ratingBar;
    private Button buttonFinalizare;
    private Spinner spinnerPachet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_add_vehicle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initializam view-urile
        editTextModelMasina = findViewById(R.id.editTextModelMasina);
        editTextPret=findViewById(R.id.editTextPret);
        radioGroupMurdarie=findViewById(R.id.radioGroupMurdarie);
        checkBoxTratamentPiele =findViewById(R.id.checkBoxTratamentPiele);
        switchOzonificare=findViewById(R.id.switchOzonificare);
        ratingBar=findViewById(R.id.ratingBar);
        buttonFinalizare=findViewById(R.id.buttonFinalizare );

        //Populam Spinner-ul cu optiuni
        String[] pachete= {"Detailing Interior", "Detailing Exterior", "Full Detailing"};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pachete)  ;
        spinnerPachet.setAdapter(adapter);

        //Evenimentul de click la salvare pe buton
        buttonFinalizare.setOnClickListener(v->{
            String model = editTextModelMasina.getText().toString().trim();
            String pretStr = editTextPret.getText().toString().trim();

            if(model.isEmpty() || pretStr.isEmpty()){
                Toast.makeText(this, "Completeaza modelul si pretul!", Toast.LENGTH_LONG).show();
                return;
            }

            double pret = Double.parseDouble(pretStr);
            String pachetSelectat = spinnerPachet.getSelectedItem().toString();

            //Preluam valoarea din RadioGroup
            int selectedRadioButtonId=radioGroupMurdarie.getCheckedRadioButtonId();
            RadioButton selectedRadioButton=findViewById(selectedRadioButtonId);
            String gradMurdarie=selectedRadioButton!=null ? selectedRadioButton.getText().toString() : "Medie";

            boolean tratamentPiele=checkBoxTratamentPiele.isChecked();
            boolean ozonificare=switchOzonificare.isChecked();
            float rating = ratingBar.getRating();

            //Cream instanta clasei DetailingVehicle
            DetailingVehicle vehicle = new DetailingVehicle(model, pret, pachetSelectat, gradMurdarie, tratamentPiele, ozonificare, rating);

            //Trimitem obiectul inapoi in MainActivity prin Intent
            Intent resultIntent = new Intent();
            resultIntent.putExtra("detailing_obiect", vehicle);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}