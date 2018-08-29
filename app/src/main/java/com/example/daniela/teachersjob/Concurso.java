package com.example.daniela.teachersjob;

public class Concurso {
    private String id;
    private String nome, titulo,local,dataEncerramento, descricao, dataPublicacao;
    private String fotoUsuario;

    public Concurso() {
        this.id = new String("");
        this.nome = new String();
        this.titulo = new String();
        this.local = new String();
        this.dataEncerramento = new String();
        this.descricao = new String();
        this.dataPublicacao = new String();
        this.fotoUsuario = new String();
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Concurso(String nome, String titulo, String local, String descricao, String dataEncerramento, String dataPublicacao, String bitmap){
        setNome(nome);
        setTitulo(titulo);
        setLocal(local);
        setDescricao(descricao);
        setDataEncerramento(dataEncerramento);
        setDataPublicacao(dataPublicacao);
        setFotoUsuario(bitmap);
    }

    public String getLocal() {
        return local;
    }

    public String getDataPublicacao() { return dataPublicacao; }

    public void setDataPublicacao(String dataPublicacao) { this.dataPublicacao = dataPublicacao; }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(String dataEncerramento) { this.dataEncerramento = dataEncerramento; }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    @Override
    public String toString() {
        return "Concurso{" +
                ", nome='" + nome + '\'' +
                ", titulo='" + titulo + '\'' +
                ", local='" + local + '\'' +
                ", dataEncerramento='" + dataEncerramento + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataPublicacao='" + dataPublicacao + '\'' +
                '}';
    }
}
