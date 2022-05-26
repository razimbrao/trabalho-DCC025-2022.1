package com.trabalho;

public class Jogador {
    private String nome;
    private static int nJogadores;
    
    public Jogador(String x){
        this.nome = x;
        nJogadores++;
    }
    public static int getnJogadores(){
        return nJogadores;
    }
}
