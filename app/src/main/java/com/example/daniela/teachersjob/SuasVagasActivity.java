package com.example.daniela.teachersjob;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SuasVagasActivity extends AppCompatActivity {

    private ListView listView;
    private List<Vaga> listaVagas = new ArrayList<>();
    private ArrayAdapterSuasVagas adapterVaga;
    private LinearLayout linearLayoutVaga;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/vagas/");
    private ImageView alterarImageView;
    private ImageView excluirImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_vagas_page);
        listView = findViewById(R.id.vagasListView);
        adapterVaga = new ArrayAdapterSuasVagas(SuasVagasActivity.this, listaVagas);
        listView.setAdapter(adapterVaga);
        alterarImageView = findViewById(R.id.alterarImageView);
        excluirImageView = findViewById(R.id.excluirImageView);
        filtrarFirebase();
    }

    private void filtrarFirebase() {
        Query nomeVagas = ref.orderByChild("nome");
        nomeVagas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaVagas.clear();
                Vaga v;
                for (DataSnapshot vagas : dataSnapshot.getChildren()) {
                    v = vagas.getValue(Vaga.class);

                    String nomeVarGlobal = MySingleton.getInstance().getUsuario().getNome();
                    if(nomeVarGlobal.toUpperCase().contains(v.getNome().toUpperCase()))
                        listaVagas.add(vagas.getValue(Vaga.class));

                }
                adapterVaga.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
