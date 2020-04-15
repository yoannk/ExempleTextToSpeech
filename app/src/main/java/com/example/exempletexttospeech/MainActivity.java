package com.example.exempletexttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Context context;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        initSpinnerLangues();
        initTextToSpeech(0);

        final EditText txtMessage = findViewById(R.id.txtMessage);
        Button btnMessageParle = findViewById(R.id.btnMessageParle);

        btnMessageParle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = txtMessage.getText().toString().trim();

                if (message.isEmpty()) {
                    Toast.makeText(context, "Veuillez saisir un message", Toast.LENGTH_SHORT).show();
                    return;
                }

                textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, "");
            }
        });
    }

    private void initTextToSpeech(final int langue) {
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {

                    switch (langue) {
                        case 1:
                            textToSpeech.setLanguage(Locale.FRANCE);
                            break;
                        case 2:
                            textToSpeech.setLanguage(Locale.ENGLISH);
                            break;
                        default:
                            textToSpeech.setLanguage(Locale.FRANCE);
                            break;
                    }

                }
            }
        });
    }

    private void initSpinnerLangues() {
        Spinner sprLangues = findViewById(R.id.sprLangues);

        ArrayList<String> langues = new ArrayList<>();
        langues.add(0, "Sélectionnez la langue");
        langues.add(1, "Français");
        langues.add(2, "Anglais");

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, langues);
        sprLangues.setAdapter(adapter);

        sprLangues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initTextToSpeech(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
