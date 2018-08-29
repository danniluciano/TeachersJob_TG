package com.example.daniela.teachersjob;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TermosCondicoesActivity extends AppCompatActivity {

    private Button euConcordoButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termos_page);
        euConcordoButton = findViewById(R.id.euConcordoButton);
        euConcordoButton.setOnClickListener(btnCadastrarEvent);
    }

    View.OnClickListener btnCadastrarEvent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(TermosCondicoesActivity.this, CadastrarLoginActivity.class));
        }
    };

}