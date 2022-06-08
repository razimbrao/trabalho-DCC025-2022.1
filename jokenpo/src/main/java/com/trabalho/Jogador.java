package com.trabalho;

// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A

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

