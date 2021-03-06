package com.example.daniela.teachersjob;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class AdicionarVagaActivity extends AppCompatActivity {

    //variáveis do Firebase
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference vagasReference;
    private TextInputLayout tituloTextInputLayoutPublicar;
    private TextInputLayout localTextInputLayoutPublicar;
    private TextInputLayout dataEncerramentoTextInputLayoutPublicar;
    private TextInputLayout descricaoTextInputLayoutPublicar;
    private TextInputEditText tituloTextInputEditTextPublicar;
    private TextInputEditText localTextInputEditTextPublicar;
    private TextInputEditText dataEncerramentoTextInputEditTextPublicar;
    private TextInputEditText descricaoTextInputEditTextPublicar;
    private Button confirmarButtonPublicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adiciona_vaga_page);
        tituloTextInputLayoutPublicar =
                findViewById(R.id.tituloTextInputLayoutPublicar);
        localTextInputLayoutPublicar =
                findViewById(R.id.localTextInputLayoutPublicar);
        dataEncerramentoTextInputLayoutPublicar =
                findViewById(R.id.dataEncerramentoTextInputLayoutPublicar);
        descricaoTextInputLayoutPublicar =
                findViewById(R.id.descricaoTextInputLayoutPublicar);
        tituloTextInputEditTextPublicar =
                findViewById(R.id.tituloTextInputEditTextPublicar);
        localTextInputEditTextPublicar =
                findViewById(R.id.localTextInputEditTextPublicar);
        dataEncerramentoTextInputEditTextPublicar =
                findViewById(R.id.dataEncerramentoTextInputEditTextPublicar);
        descricaoTextInputEditTextPublicar =
                findViewById(R.id.descricaoTextInputEditTextPublicar);
        confirmarButtonPublicar =
                findViewById(R.id.confirmarButtonPublicar);
        configuraFirebase();

        confirmarButtonPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                String dataPublicada = sdf.format(date);
                String nome = MySingleton.getInstance().getUsuario().getNome();
                String titulo =
                        tituloTextInputEditTextPublicar.getEditableText().toString();
                String local =
                        localTextInputEditTextPublicar.getEditableText().toString();
                String dataEncerramento =
                        dataEncerramentoTextInputEditTextPublicar.getEditableText().toString();
                String descricao =
                        descricaoTextInputEditTextPublicar.getEditableText().toString();
                String id = vagasReference.push().getKey();
                Vaga vaga = new Vaga(nome, titulo, local, descricao, dataEncerramento, dataPublicada, MySingleton.getInstance().getUsuario().getFoto());
                vagasReference.child(id).setValue(vaga);
                Toast.makeText(AdicionarVagaActivity.this,
                        getString(R.string.publicacao_vaga_confirmada),
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void configuraFirebase (){
        firebaseDatabase = FirebaseDatabase.getInstance();
        vagasReference = firebaseDatabase.getReference("/vagas/");
    }
}
