package com.example.exempletexttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Context context;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initTextToSpeech("");

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

    private void initTextToSpeech(String langue) {
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.FRANCE);
                }
            }
        });
    }
}
