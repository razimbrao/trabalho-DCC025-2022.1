package com.trabalho;
// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A

public abstract class Jogador implements Comparable<Jogador>{
    private String nome;
    private static int nJogadores;
    private boolean ehBot;
    private int id;
    private int nVitorias;

    public abstract int selecionaJogada();


    public String getNome() {
        return nome;
    }

    public Jogador(String x){
        this.nome = x;
        nJogadores++;
        this.nVitorias = 0;
    }
    public static int getnJogadores(){
        return nJogadores;
    }

    public void setId(int x){
        this.id=x;
    }

    public int getId(){
        return this.id;
    }

    public boolean getEhBot() {
        return this.ehBot;
    }

    public void setEhBot(boolean x) {
        this.ehBot = x;
    }

    public int getnVitorias() {
        return nVitorias;
    }

    public void atualizaNumVitorias(){
        this.nVitorias++;
    }

    public void setnVitorias(int nVitorias) {
        this.nVitorias = nVitorias;
    }

    @Override
    public int compareTo(Jogador auxJogador) {
        return (auxJogador.getnVitorias() - this.nVitorias);
    }

    @Override
    public String toString() {
        return this.nome;
    }

}

