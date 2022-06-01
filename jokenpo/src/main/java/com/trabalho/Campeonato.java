package com.trabalho;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private int tamanhoInicial;
    private int tamanhoAtual;
    private int nJogadores;
    private int indexPartidas;
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
        this.indexPartidas++;
        int id = this.indexPartidas-1;
        x.setId(id);
    }

    public void removePartida(Partida x)
    {
        this.listaPartidas.remove(x.getId());
    }

    public void insereJogadores(){ //teste 
        Jogador Rafael = new Jogador("Rafael");
        Jogador Vidal = new Jogador("Vidal");
        Jogador Vitin = new Jogador("Vitin");
        Jogador Cacho = new Jogador("Cacho");
        addJogador(Rafael);
        addJogador(Vidal);
        addJogador(Vitin);
        addJogador(Cacho);
    }

    public void inserePartidas(){ // teste
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
        this.tamanhoAtual=this.listaPartidas.size();
        if(this.tamanhoAtual==8){
            //OITAVAS
    
        }

        if(this.tamanhoAtual==4){
            //QUARTAS

        }

        if(this.tamanhoAtual==2){
            //SEMI
            Jogador vencedorEsq = asaEsquerda.get(0).simulador();
            removeJogador(asaEsquerda.get(0).getPerdedor());
            System.out.println("Jogador " +vencedorEsq.getNome() + "avança para a final.");
            removePartida(asaEsquerda.get(0));

            Jogador vencedorDir = asaDireita.get(0).simulador();
            removeJogador(asaDireita.get(0).getPerdedor());
            System.out.println("Jogador " +vencedorDir.getNome() + "avança para a final.");
            removePartida(asaDireita.get(0));
            this.tamanhoAtual=this.listaPartidas.size();
        }
        if(this.tamanhoAtual==0){
            //FINAL
            Jogador j1 = asaEsquerda.get(0).getVencedor(), j2 = asaDireita.get(0).getVencedor();    
            Partida f = new Partida(j1, j2);
            f.simulador();
            f.imprimePlacar();
        }
    }
}
