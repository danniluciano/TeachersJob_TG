package com.example.daniela.teachersjob.entidade;

import com.google.firebase.database.Exclude;

import java.util.Objects;

public class Usuario {

    private String idUsuario;
    private String nome;
    private String telefone;
    private String cidade;
    private String email;
    private String foto;
    private String senha;
    private String confirmarSenha;

    public Usuario(){
        this.idUsuario = new String();
        this.nome = new String();
        this.telefone = new String();
        this.email = new String();
        this.foto = new String();
    }

    public Usuario(String idUsuario, String nome, String telefone, String email, String foto) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.foto = foto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Exclude
    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario) &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(telefone, usuario.telefone) &&
                Objects.equals(cidade, usuario.cidade) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(foto, usuario.foto) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(confirmarSenha, usuario.confirmarSenha);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUsuario, nome, telefone, cidade, email, foto, senha, confirmarSenha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cidade='" + cidade + '\'' +
                ", email='" + email + '\'' +
                ", foto='" + foto + '\'' +
                ", senha='" + senha + '\'' +
                ", confirmarSenha='" + confirmarSenha + '\'' +
                '}';
    }
}
