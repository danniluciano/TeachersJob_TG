package com.example.daniela.teachersjob.events;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.daniela.teachersjob.MainActivity;
import com.example.daniela.teachersjob.MySingleton;
import com.example.daniela.teachersjob.R;
import com.example.daniela.teachersjob.dao.UsuarioDAO;
import com.example.daniela.teachersjob.entidade.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class EventUsuarioEntrar implements EventoApplication{

    private AppCompatActivity activity;
    private Usuario usuarioLogin;
    private Usuario usuarioBanco;
    private UsuarioDAO usuarioDAO;

    public EventUsuarioEntrar(AppCompatActivity activity, Usuario usuario){
        this.activity = activity;
        this.usuarioLogin = usuario;
        this.usuarioBanco = new Usuario();
        this.usuarioBanco.setEmail(usuario.getEmail());
        this.usuarioBanco.setSenha(usuario.getSenha());
        this.usuarioDAO = new UsuarioDAO();
    }

    @Override
    public void executarEvento() {
        usuarioDAO.getUsuario(usuarioLogin,callBack);
    }

    private void postExecutarEvento() {

        try {
            if (usuarioBanco.getSenha().equals(usuarioLogin.getSenha()) && usuarioBanco.getEmail().equals(usuarioLogin.getEmail())) {
                usuarioLogin.setNome(usuarioBanco.getNome());
                usuarioLogin.setSenha(usuarioBanco.getSenha());
                usuarioLogin.setTelefone(usuarioBanco.getTelefone());
                usuarioLogin.setSenha(usuarioBanco.getSenha());
                usuarioLogin.setEmail(usuarioBanco.getEmail());
                usuarioLogin.setCidade(usuarioBanco.getCidade());
                usuarioLogin.setFoto(usuarioBanco.getFoto());
                MySingleton.getInstance().setUsuario(usuarioLogin);
                activity.startActivity(new Intent(activity, MainActivity.class));
            } else {
                Toast.makeText(this.activity, activity.getResources().getString(R.string.login_incorreto), Toast.LENGTH_LONG).show();
            }
        }catch(NullPointerException e){
            e.printStackTrace();
            Toast.makeText(this.activity, activity.getResources().getString(R.string.login_incorreto), Toast.LENGTH_LONG).show();
        }
    }

    private ValueEventListener callBack = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            usuarioBanco = dataSnapshot.getValue(Usuario.class);
            postExecutarEvento();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.d("ERRO", databaseError.getMessage());
        }
    };

}