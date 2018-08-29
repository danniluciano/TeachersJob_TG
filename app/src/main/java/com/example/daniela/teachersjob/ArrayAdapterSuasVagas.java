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

public class ArrayAdapterSuasVagas extends ArrayAdapter<Vaga> {

    public ArrayAdapterSuasVagas(Context context, List<Vaga> forecast){
        super (context, -1, forecast);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Context context = getContext();
        Vaga caraDaVez = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        ArrayAdapterSuasVagas.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.publicacoes_alterar_excluir, parent, false);
            viewHolder = new ArrayAdapterSuasVagas.ViewHolder();
            convertView.setTag(viewHolder);
            setDadosViewHolder(convertView, caraDaVez, viewHolder);
        }
        else{
            viewHolder = (ArrayAdapterSuasVagas.ViewHolder) convertView.getTag();
            update(viewHolder, caraDaVez);
        }

        return convertView;
    }

    private void update(ArrayAdapterSuasVagas.ViewHolder viewHolder, Vaga caraDaVez){
        viewHolder.usuarioNomeTextView.setText(caraDaVez.getNome());
        viewHolder.dataTextView.setText(caraDaVez.getDataPublicacao());
        viewHolder.tituloVagaTextView.setText(caraDaVez.getTitulo());
        viewHolder.descricaoLocalVagaTextView.setText(caraDaVez.getLocal());
        viewHolder.descricaoDescricaoVagaTextView.setText(caraDaVez.getDescricao());
        viewHolder.fotoUsuarioImageViewCadastrado.setImageBitmap(MySingleton.getInstance().getBitmapFromString(caraDaVez.getFotoUsuario()));
        viewHolder.dataEncerramentoTextView.setText(caraDaVez.getDataEncerramento());
    }

    private void setDadosViewHolder(@NonNull View convertView, Vaga caraDaVez, ArrayAdapterSuasVagas.ViewHolder viewHolder) {
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

    private static class ViewHolder{

        private TextView usuarioNomeTextView, dataEncerramentoTextView,tituloVagaTextView,descricaoLocalVagaTextView, descricaoDescricaoVagaTextView, dataTextView;
        private ImageView fotoUsuarioImageViewCadastrado;

    }

}
