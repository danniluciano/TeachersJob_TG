package com.example.daniela.teachersjob;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AlterarConcursosActivity extends AppCompatActivity {

    private Button alterarConcursoButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_concursos_page);
        alterarConcursoButton = findViewById(R.id.alterarConcursoButton);
        alterarConcursoButton.setOnClickListener(btnAlterar);
    }

    View.OnClickListener btnAlterar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(AlterarConcursosActivity.this, SeusConcursosActivity.class));
        }
    };
}
