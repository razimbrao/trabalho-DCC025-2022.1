package com.trabalho;

public class Partida {
    private Jogador jogador1;
    private Jogador jogador2;
    private int pontuacaoJ1;
    private int pontuacaoJ2;

    public Partida(Jogador j1, Jogador j2){
        this.jogador1 = j1;
        this.jogador2 = j2;
    }

    public void simulador(){

    }

    public void imprimePlacar(){
        System.out.println(this.pontuacaoJ1 + " x " + this.pontuacaoJ2);
    }
}
