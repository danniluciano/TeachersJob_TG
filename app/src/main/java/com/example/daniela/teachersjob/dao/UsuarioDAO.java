package com.example.daniela.teachersjob.dao;

import com.example.daniela.teachersjob.entidade.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UsuarioDAO{

    private DatabaseReference ref;
    private Usuario usuario;

    public UsuarioDAO(){
        ref = FirebaseDatabase.getInstance().getReference("/usuario/");
    }

    public void writeUsuario(Usuario usuario){
        usuario.setIdUsuario(ref.push().getKey());
        ref.child(usuario.getEmail()).setValue(usuario);
    }

    public Usuario getUsuario(Usuario usuario, ValueEventListener listener) {

        this.usuario = usuario;
        this.ref.child(usuario.getEmail()).addListenerForSingleValueEvent(listener);
        return usuario;
    }

}