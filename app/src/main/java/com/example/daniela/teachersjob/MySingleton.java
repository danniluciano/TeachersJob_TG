package com.example.daniela.teachersjob;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;

import com.example.daniela.teachersjob.entidade.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class MySingleton {
    private static MySingleton ptr;
    private Usuario usuario;

    private MySingleton(){
        this.usuario = new Usuario();
    }

    public void setUsuario(Usuario usuario){
        this.usuario.setFoto(usuario.getFoto());
        this.usuario.setCidade(usuario.getCidade());
        this.usuario.setEmail(usuario.getEmail());
        this.usuario.setIdUsuario(usuario.getIdUsuario());
        this.usuario.setNome(usuario.getNome());
        this.usuario.setSenha(usuario.getSenha());
        this.usuario.setTelefone(usuario.getTelefone());
    }

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/vagas/");

    public Usuario getUsuario(){
        return this.usuario;
    }

    public static MySingleton getInstance(){
        if(ptr== null)
            ptr = new MySingleton();
        return ptr;
    }


    public String getBytesImageView(ImageView btnFotoCadastrar) {
        Bitmap imagem = ((BitmapDrawable) btnFotoCadastrar.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imagem.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        return Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT);
    }

    public Bitmap getBitmapFromString(String image64){
        return getBitmapFromString(Base64.decode(image64,Base64.DEFAULT));
    }

    public Bitmap getBitmapFromString(byte[] data){
        return BitmapFactory.decodeByteArray(data,0,data.length);
    }

    public void setBitmapImageFromString(String imageBase64, ImageView btnFotoCadastrar){
        byte[] data = Base64.decode(imageBase64, Base64.DEFAULT);
        btnFotoCadastrar.setImageBitmap(getBitmapFromString(data));
    }
}
