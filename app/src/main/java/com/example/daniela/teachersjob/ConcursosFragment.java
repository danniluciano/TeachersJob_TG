package com.example.daniela.teachersjob;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConcursosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_concursos, container, false);
    }

    private TextView publicarConcursoTextView;
    private TextView buscarConcursosTextInputEditText;
    private ListView listView;
    private List<Concurso> listaConcurso = new ArrayList<>();
    private ArrayAdapterConcurso adapterConcurso;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/concurso/");

    @Override
    public void onStart() {
        super.onStart();
        publicarConcursoTextView = getView().findViewById(R.id.publicarConcursoTextView);
        buscarConcursosTextInputEditText = getView().findViewById(R.id.buscarConcursosTextInputEditText);
        //PREENCHER LISTA DE VAGAS
        listView = getView().findViewById(R.id.ConcursoListView);
        adapterConcurso = new ArrayAdapterConcurso(getContext(), listaConcurso);
        listView.setAdapter(adapterConcurso);
        publicarConcursoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AdicionarConcursoActivity.class);
                startActivity(intent);
            }
        });
        ref.addChildEventListener(preenchedorDeConcursos);
    }

    private void filtrarFirebasae() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/concursos/");
        Query tituloConcursos = reference.orderByChild("titulo");

        tituloConcursos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaConcurso.clear();
                Concurso c;
                for (DataSnapshot concurso : dataSnapshot.getChildren()) {
                    c = concurso.getValue(Concurso.class);

                    if(c.getTitulo().toUpperCase().contains(buscarConcursosTextInputEditText.getText().toString().toUpperCase()))
                        listaConcurso.add(concurso.getValue(Concurso.class));

                }
                adapterConcurso.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    ChildEventListener preenchedorDeConcursos = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            listaConcurso.add(dataSnapshot.getValue(Concurso.class));
            adapterConcurso.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            for (int i = 0; i < listaConcurso.size(); i++) {
                if (listaConcurso.get(i).getId().equals(dataSnapshot.getKey())) {
                    listaConcurso.set(i, dataSnapshot.getValue(Concurso.class));
                }
            }
            adapterConcurso.notifyDataSetChanged();
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            listaConcurso.remove(dataSnapshot.getValue(Concurso.class));
            adapterConcurso.notifyDataSetChanged();
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }


    };

}
