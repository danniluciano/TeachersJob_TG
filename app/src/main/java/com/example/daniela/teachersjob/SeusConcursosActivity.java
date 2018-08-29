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

public class SeusConcursosActivity extends AppCompatActivity {


    private ListView listView;
    private List<Concurso> listaConcurso = new ArrayList<>();
    private ArrayAdapterSeusConcursos adapterConcurso;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/concurso/");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seus_concursos_page);
        listView = findViewById(R.id.concListView);
        adapterConcurso = new ArrayAdapterSeusConcursos(SeusConcursosActivity.this, listaConcurso);
        listView.setAdapter(adapterConcurso);
        filtrarFirebase();
    }

    private void filtrarFirebase() {
        Query nomeConcursos = ref.orderByChild("nome");

        nomeConcursos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaConcurso.clear();
                Concurso c;
                for (DataSnapshot concurso : dataSnapshot.getChildren()) {
                    c = concurso.getValue(Concurso.class);

                    String nomeVarGlobal = MySingleton.getInstance().getUsuario().getNome();
                    if(nomeVarGlobal.toUpperCase().contains(c.getNome().toUpperCase()))
                        listaConcurso.add(concurso.getValue(Concurso.class));

                }
                adapterConcurso.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
