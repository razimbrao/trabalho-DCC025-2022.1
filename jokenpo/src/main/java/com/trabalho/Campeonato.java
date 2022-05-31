package com.trabalho;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private int tamanhoInicial;
    private int tamanhoAtual = (this.listaPartidas.size())+1;
    private int nJogadores;
    private int iPartidas;
    private List<Jogador> listaJogadores = new ArrayList<>();
    private List<Partida> listaPartidas = new ArrayList<>();
    private List<Partida> asaEsquerda = new ArrayList<>();
    private List<Partida> asaDireita = new ArrayList<>();

    public Campeonato(int n){
        if(n==8 || n==4 || n==2)
            this.tamanhoInicial = n;
        else{
            System.out.println("ERRO: Número de partidas inválidas!");
            System.exit(0);
        }
        if(this.tamanhoInicial==8){
            System.out.println("Oitavas de final criada!");
        }else if(this.tamanhoInicial==4){
            System.out.println("Quartas de final criada!");

        }else if(this.tamanhoInicial==2)
            System.out.println("Semi finais criadas!");
    }

    public void addJogador(Jogador x){
        this.listaJogadores.add(x);
        this.nJogadores++;
        int id = this.nJogadores-1;
        x.setId(id);
    }

    public void removeJogador(Jogador x)
    {
        int index=x.getId();
        this.listaJogadores.remove(index);
    }

    public void addPartida(Partida x){
        this.listaPartidas.add(x);
        this.iPartidas++;
        int id = this.iPartidas-1;
        x.setId(id);
    }

    public void removePartida(Partida x)
    {
        int index=x.getId();
        this.listaPartidas.remove(index);
    }

    public void insereJogadores(){
        Jogador Rafael = new Jogador("Rafael");
        Jogador Vidal = new Jogador("Vidal");
        Jogador Vitin = new Jogador("Vitin");
        Jogador Cacho = new Jogador("Cacho");
        addJogador(Rafael);
        addJogador(Vidal);
        addJogador(Vitin);
        addJogador(Cacho);
    }

    public void inserePartidas() {
        Partida p1 = new Partida(listaJogadores.get(0), listaJogadores.get(1));
        Partida p2 = new Partida(listaJogadores.get(2), listaJogadores.get(3));
        addPartida(p1);
        addPartida(p2);
    }

    public void chaveamento(){
        //int sizeLJ = this.listaJogadores.size();
        if(this.tamanhoInicial==8){
            //oitavas
            for(int i=0; i<4; i++)
                asaEsquerda.add(this.listaPartidas.get(i));
            for(int i=4; i<8; i++)
                asaDireita.add(this.listaPartidas.get(i));

        }else if(this.tamanhoInicial==4){
            //quartas
            for(int i=0; i<2; i++)
                asaEsquerda.add(this.listaPartidas.get(i));
            for(int i=4; i<4; i++)
                asaDireita.add(this.listaPartidas.get(i));

        }else if(this.tamanhoInicial==2){
            //semi
                asaEsquerda.add(this.listaPartidas.get(0));
                asaDireita.add(this.listaPartidas.get(1));
        }
    }

    public void resolveNivel(){
        if(this.tamanhoAtual==8){
            //OITAVAS

        }else if(this.tamanhoAtual==4){
            //QUARTAS

        }else if(this.tamanhoAtual==2){
            //SEMI

        }else if(this.tamanhoAtual==1){
            //FINAL
            
        }
    }
}
