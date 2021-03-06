package com.example.daniela.teachersjob;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ArrayAdapterConcurso extends ArrayAdapter<Concurso> {

    public ArrayAdapterConcurso(Context context, List<Concurso> forecast){
        super (context, -1, forecast);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Context context = getContext();
        Concurso caraDaVez = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        ArrayAdapterConcurso.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mural_de_vagas, parent, false);
            viewHolder = new ArrayAdapterConcurso.ViewHolder();
            convertView.setTag(viewHolder);
            setDadosViewHolder(convertView, caraDaVez, viewHolder);
        } else {
            viewHolder = (ArrayAdapterConcurso.ViewHolder) convertView.getTag();
            update(viewHolder, caraDaVez);
        }

        return convertView;
    }

    private void update(ArrayAdapterConcurso.ViewHolder viewHolder, Concurso caraDaVez) {
        viewHolder.usuarioNomeTextView.setText(caraDaVez.getNome());
        viewHolder.dataTextView.setText(caraDaVez.getDataPublicacao());
        viewHolder.tituloVagaTextView.setText(caraDaVez.getTitulo());
        viewHolder.descricaoLocalVagaTextView.setText(caraDaVez.getLocal());
        viewHolder.descricaoDescricaoVagaTextView.setText(caraDaVez.getDescricao());
        viewHolder.fotoUsuarioImageViewCadastrado.setImageBitmap(MySingleton.getInstance().getBitmapFromString(caraDaVez.getFotoUsuario()));
        viewHolder.dataEncerramentoTextView.setText(caraDaVez.getDataEncerramento());
    }

    private void setDadosViewHolder(@NonNull View convertView, Concurso caraDaVez, ArrayAdapterConcurso.ViewHolder viewHolder) {
        viewHolder.usuarioNomeTextView = (TextView) convertView.findViewById(R.id.usuarioNomeTextView);
        viewHolder.usuarioNomeTextView.setText(caraDaVez.getNome());
        viewHolder.dataTextView = convertView.findViewById(R.id.dataTextView);
        viewHolder.dataTextView.setText(caraDaVez.getDataPublicacao());
        viewHolder.tituloVagaTextView = convertView.findViewById(R.id.tituloVagaTextView);
        viewHolder.tituloVagaTextView.setText(caraDaVez.getTitulo());
        viewHolder.descricaoLocalVagaTextView = convertView.findViewById(R.id.descricaoLocalVagaTextView);
        viewHolder.descricaoLocalVagaTextView.setText(caraDaVez.getLocal());
        viewHolder.descricaoDescricaoVagaTextView = convertView.findViewById(R.id.descricaoDescricaoVagaTextView);
        viewHolder.descricaoDescricaoVagaTextView.setText(caraDaVez.getDescricao());
        viewHolder.fotoUsuarioImageViewCadastrado = convertView.findViewById(R.id.fotoUsuarioImageViewCadastrado);
        viewHolder.fotoUsuarioImageViewCadastrado.setImageBitmap(MySingleton.getInstance().getBitmapFromString(caraDaVez.getFotoUsuario()));
        viewHolder.dataEncerramentoTextView = convertView.findViewById(R.id.dataEncerramentoVagaTextView);
        viewHolder.dataEncerramentoTextView.setText(caraDaVez.getDataEncerramento());
    }

    private static class ViewHolder {
        private TextView usuarioNomeTextView, dataEncerramentoTextView, tituloVagaTextView, descricaoLocalVagaTextView, descricaoDescricaoVagaTextView, dataTextView;
        private ImageView fotoUsuarioImageViewCadastrado;
    }
}
