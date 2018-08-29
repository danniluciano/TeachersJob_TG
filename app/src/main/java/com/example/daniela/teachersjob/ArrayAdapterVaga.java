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

public class ArrayAdapterVaga extends ArrayAdapter<Vaga> {

    public ArrayAdapterVaga(Context context, List<Vaga> forecast){
        super (context, -1, forecast);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Context context = getContext();
        Vaga caraDaVez = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mural_de_vagas, parent, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            setDadosViewHolder(convertView, caraDaVez, viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
            update(viewHolder, caraDaVez);
        }

        return convertView;
    }

    private void update(ViewHolder viewHolder, Vaga caraDaVez){
        viewHolder.usuarioNomeTextView.setText(caraDaVez.getNome());
        viewHolder.dataTextView.setText(caraDaVez.getDataPublicacao());
        viewHolder.tituloVagaTextView.setText(caraDaVez.getTitulo());
        viewHolder.descricaoLocalVagaTextView.setText(caraDaVez.getLocal());
        viewHolder.descricaoDescricaoVagaTextView.setText(caraDaVez.getDescricao());
        viewHolder.fotoUsuarioImageViewCadastrado.setImageBitmap(MySingleton.getInstance().getBitmapFromString(caraDaVez.getFotoUsuario()));
        viewHolder.dataEncerramentoTextView.setText(caraDaVez.getDataEncerramento());
        String nomeUsuario = MySingleton.getInstance().getUsuario().getNome();
        //Se a vaga for do usuário
        /*if (nomeUsuario.equalsIgnoreCase(caraDaVez.getNome())) {
            Context context_alterar = viewHolder.alterarImageView.getContext();
            int id_alterar = context_alterar.getResources().getIdentifier("ic_settings2", "drawable", context_alterar.getPackageName());
            viewHolder.alterarImageView.setImageResource(id_alterar);
            Context context_excluir = viewHolder.excluirImageView.getContext();
            int id_excluir = context_excluir.getResources().getIdentifier("ic_delete", "drawable", context_excluir.getPackageName());
            viewHolder.excluirImageView.setImageResource(id_excluir);
        } */
    }

    private void setDadosViewHolder(@NonNull View convertView, Vaga caraDaVez, ViewHolder viewHolder) {
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
        String nomeUsuario = MySingleton.getInstance().getUsuario().getNome();
        //Se a vaga for do Usuário
        /*if (nomeUsuario.equalsIgnoreCase(caraDaVez.getNome())) {
            int id_alterar = getContext().getResources().getIdentifier("ic_settings2", "drawable", getContext().getPackageName());
            viewHolder.alterarImageView = convertView.findViewById(R.id.alterarImageView);
            viewHolder.alterarImageView.setImageResource(id_alterar);

            int id_excluir = getContext().getResources().getIdentifier("ic_delete", "drawable", getContext().getPackageName());
            viewHolder.excluirImageView = convertView.findViewById(R.id.excluirImageView);
            viewHolder.excluirImageView.setImageResource(id_excluir);
        }*/
    }

    private static class ViewHolder{

        private TextView usuarioNomeTextView, dataEncerramentoTextView,tituloVagaTextView,descricaoLocalVagaTextView, descricaoDescricaoVagaTextView, dataTextView;
        private ImageView fotoUsuarioImageViewCadastrado, alterarImageView, excluirImageView;

    }
}