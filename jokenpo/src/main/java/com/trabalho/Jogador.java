package com.trabalho;

public class Jogador {
    private String nome;
    private static int nJogadores;
    private int id;

    public String getNome() {
        return nome;
    }
    
    public Jogador(String x){
        this.nome = x;
        nJogadores++;
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
}

