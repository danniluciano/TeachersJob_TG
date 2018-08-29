package com.example.daniela.teachersjob;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class VagasFragment extends Fragment {

    private TextView publicarVagaTextView;
    private TextView buscarVagasTextInputEditText;
    private ListView listView;
    private List<Vaga> listaVagas = new ArrayList<>();
    private ArrayAdapterVaga adapterVaga;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/vagas/");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vagas, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        publicarVagaTextView = getView().findViewById(R.id.publicarVagaTextView);
        buscarVagasTextInputEditText = getView().findViewById(R.id.buscarVagasTextInputEditText);
        //PREENCHER LISTA DE VAGAS
        listView = getView().findViewById(R.id.vagasListView);
        adapterVaga = new ArrayAdapterVaga(getContext(), listaVagas);
        listView.setAdapter(adapterVaga);
        publicarVagaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AdicionarVagaActivity.class);
                startActivity(intent);
            }
        });
        ref.addChildEventListener(preenchedorDeVagas);
    }

    private void filtrarFirebasae() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/vagas/");
        Query tituloVagas = reference.orderByChild("titulo");

        tituloVagas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaVagas.clear();
                Vaga v;
                for (DataSnapshot vagas : dataSnapshot.getChildren()) {
                    v = vagas.getValue(Vaga.class);

                    if(v.getTitulo().toUpperCase().contains(buscarVagasTextInputEditText.getText().toString().toUpperCase()))
                        listaVagas.add(vagas.getValue(Vaga.class));

                }
                adapterVaga.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    ChildEventListener preenchedorDeVagas = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            listaVagas.add(dataSnapshot.getValue(Vaga.class));
            adapterVaga.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            for (int i = 0; i < listaVagas.size(); i++) {
                if (listaVagas.get(i).getId().equals(dataSnapshot.getKey())) {
                    listaVagas.set(i, dataSnapshot.getValue(Vaga.class));
                }
            }
            adapterVaga.notifyDataSetChanged();
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            listaVagas.remove(dataSnapshot.getValue(Vaga.class));
            adapterVaga.notifyDataSetChanged();
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }


    };
}
