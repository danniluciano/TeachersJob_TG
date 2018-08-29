package com.example.daniela.teachersjob;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AreaDeAtuacaoActivity extends AppCompatActivity {

    ArrayList<String> select = new ArrayList<String>();
    private Button cadastrar;
    private Button pular;

    private DatabaseReference ref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_de_atuacao_page);

        cadastrar = (Button) findViewById(R.id.confirmarButton);
        pular = (Button) findViewById(R.id.pularButton);

        cadastrar.setOnClickListener(btnCadastrarEvent);
        pular.setOnClickListener(btnCadastrarEvent);
    }

    View.OnClickListener btnCadastrarEvent = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            ref = FirebaseDatabase.getInstance().getReference("Disciplinas/");
            if(view.getId()==R.id.confirmarButton) {
                ref.setValue(select);
            }
            Intent intent = new Intent(AreaDeAtuacaoActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };

    public void selecionaCheck (View view){
        boolean check =  ((CheckBox)view).isChecked();
        switch (view.getId())
        {
            case R.id.portuguesCheckBox:
                if(check){
                    select.add("Português");

                }
                else{
                    select.remove("Português");
                }
                break;

            case R.id.espanholCheckBox:
                if(check){
                    select.add("Espanhol");
                }
                else{
                    select.remove("Espanhol");
                }
                break;

            case R.id.cienciasCheckBox:
                if(check){
                    select.add("Ciências");
                }
                else{
                    select.remove("Ciências");
                }
                break;

            case R.id.historiaCheckBox:
                if(check){
                    select.add("História");
                }
                else{
                    select.remove("História");
                }
                break;

            case R.id.geografiaCheckBox:
                if(check){
                    select.add("Geografia");
                }
                else{
                    select.remove("Geografia");
                }
                break;

            case R.id.inglesCheckBox:
                if(check){
                    select.add("Inglês");
                }
                else{
                    select.remove("Inglês");
                }
                break;

            case R.id.matematicaCheckBox:
                if(check){
                    select.add("Matemática");
                }
                else{
                    select.remove("Matemática");
                }
                break;

            case R.id.filosofiaCheckBox:
                if(check){
                    select.add("Filosofia");
                }
                else{
                    select.remove("Filosofia");
                }
                break;

            case R.id.sociologiaCheckBox:
                if(check){
                    select.add("Sociologia");
                }
                else{
                    select.remove("Sociologia");
                }
                break;

            case R.id.artesCheckBox:
                if(check){
                    select.add("Artes");
                }
                else{
                    select.remove("Artes");
                }
                break;

            case R.id.fisicaCheckBox:
                if(check){
                    select.add("Física");
                }
                else{
                    select.remove("Física");
                }
                break;

            case R.id.biologiaCheckBox:
                if(check){
                    select.add("Biologia");
                }
                else{
                    select.remove("Biologia");
                }
                break;

            case R.id.educacaoFisicaCheckBox:
                if(check){
                    select.add("Educação Física");
                }
                else{
                    select.remove("Educação Física");
                }
                break;

            case R.id.quimicaCheckBox:
                if(check){
                    select.add("Química");
                }
                else{
                    select.remove("Química");
                }
                break;

            case R.id.ensinoMedioCheckBox:
                if(check){
                    select.add("Ensino Médio");
                }
                else{
                    select.remove("Ensino Médio");
                }
                break;

            case R.id.ensinoFundamentalCheckBox:
                if(check){
                    select.add("Ensino Fundamental II");
                }
                else{
                    select.remove("Ensino Fundamental II");
                }
                break;
        }

    }

}