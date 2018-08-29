package com.example.daniela.teachersjob;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.daniela.teachersjob.entidade.Usuario;
import com.example.daniela.teachersjob.events.EventCadastrarUsuario;
import com.example.daniela.teachersjob.events.EventSelecionarFoto;

public class CadastrarLoginActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnSalvar;
    private TextInputEditText txtNome;
    private TextInputEditText txtTelefone;
    private TextInputEditText txtCidade;
    private TextInputEditText txtEmail;
    private TextInputEditText txtSenha;
    private TextInputEditText txtConfirmaSenha;
    private ImageView btnFotoCadastrar;
    private int REQUEST = 1;

    private EventCadastrarUsuario eventCadastrarUsuario;
    private EventSelecionarFoto eventSelecionarFoto;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_usuario_page);
        initVariables();
        initEvents();
    }

    private void initVariables() {

        this.btnSalvar = (Button) findViewById(R.id.confirmarButtonCadastrar);
        this.txtNome = (TextInputEditText) findViewById(R.id.nomeTextInputEditTextCadastrar);
        this.txtTelefone = (TextInputEditText) findViewById(R.id.telefoneTextInputEditTextCadastrar);
        this.txtCidade = (TextInputEditText) findViewById(R.id.cidadeTextInputEditTextCadastrar);
        this.txtEmail = (TextInputEditText) findViewById(R.id.emailTextInputEditTextCadastrar);
        this.txtSenha = (TextInputEditText) findViewById(R.id.senhaTextInputEditTextCadastrar);
        this.txtConfirmaSenha = (TextInputEditText) findViewById(R.id.confirmarSenhaTextInputEditTextCadastrar);
        this.btnFotoCadastrar = (ImageView) findViewById(R.id.fotoUsuarioImageView);
        this.eventSelecionarFoto = new EventSelecionarFoto(this);
        this.usuario = new Usuario();
        this.btnFotoCadastrar = (ImageView) findViewById(R.id.fotoUsuarioImageView);
    }

    private void initEvents() {
        btnFotoCadastrar.setOnClickListener(eventBtnSelecionarFoto);
        this.btnSalvar.setOnClickListener(eventCadastrar);
    }


    View.OnClickListener eventCadastrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            preencherObjeto(usuario);
            eventCadastrarUsuario = new EventCadastrarUsuario(usuario, CadastrarLoginActivity.this);
            eventCadastrarUsuario.executarEvento();
        }

    };



    private void preencherObjeto(Usuario usuario) {
        usuario.setEmail(txtEmail.getText().toString());
        usuario.setNome(txtNome.getText().toString());
        usuario.setSenha(txtSenha.getText().toString());
        usuario.setTelefone(txtTelefone.getText().toString());
        usuario.setConfirmarSenha(txtConfirmaSenha.getText().toString());
        usuario.setCidade(txtCidade.getText().toString());
        usuario.setFoto(MySingleton.getInstance().getBytesImageView(btnFotoCadastrar));
    }


    View.OnClickListener eventBtnSelecionarFoto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        eventSelecionarFoto.onRequestPermessionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Uri selected = data.getData();
                try{
                    Bitmap b = MediaStore.Images.Media.getBitmap(getContentResolver(), selected);
                    btnFotoCadastrar.setImageBitmap(b);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}