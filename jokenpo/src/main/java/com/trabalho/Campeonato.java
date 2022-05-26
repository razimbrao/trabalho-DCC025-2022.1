package com.trabalho;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private Partida [] vetPartida;
    private int nPartidas;
    private List<Jogador> jogadores = new ArrayList<>();

    public Campeonato(int n){
        this.nPartidas = n;
        vetPartida = new Partida[nPartidas];
    }
}
