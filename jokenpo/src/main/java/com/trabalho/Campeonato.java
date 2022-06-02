package com.trabalho;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private int tamanhoInicial;
    private int tamanhoAtual;
    private int nJogadores;
    public int indexPartidas;
    private List<Jogador> listaJogadores = new ArrayList<>();
    private List<Partida> listaPartidas = new ArrayList<>();
    public List<Partida> asaEsquerdaOitavas = new ArrayList<>();
    public List<Partida> asaDireitaOitavas = new ArrayList<>();
    public List<Partida> asaEsquerdaQuartas = new ArrayList<>();
    public List<Partida> asaDireitaQuartas = new ArrayList<>();
    public List<Partida> asaEsquerdaSemi = new ArrayList<>();
    public List<Partida> asaDireitaSemi = new ArrayList<>();

    public Campeonato(int n){
        if(n==8 || n==4 || n==2){
            this.tamanhoInicial = n;
            this.tamanhoAtual=n;
        }
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

    /*public void addPartida(Partida x){ antiga
        this.listaPartidas.add(x);
        this.indexPartidas++;
        int id = this.indexPartidas-1;
        x.setId(id);
    } */

    public void addPartida(Jogador j1, Jogador j2) {
        Partida aux = new Partida(j1, j2);
        aux.setId(indexPartidas);
        this.indexPartidas++;
        this.listaPartidas.add(aux);
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

        Jogador Livia = new Jogador("Livia");
        Jogador Luisa = new Jogador("Luisa");
        Jogador Caua = new Jogador("Caua");
        Jogador Luis = new Jogador("Luis");

        Jogador Pablo = new Jogador("Pablo");
        Jogador Miguel = new Jogador("Miguel");
        Jogador Tiago = new Jogador("Tiago");
        Jogador Hansel = new Jogador("Hansel");

        Jogador Guigui = new Jogador("Guigui");
        Jogador JK = new Jogador("JK");
        Jogador Froes = new Jogador("Froes");
        Jogador Gleiph = new Jogador("Gleiph");

        addJogador(Rafael);
        addJogador(Vidal);
        addJogador(Vitin);
        addJogador(Cacho);

/*         addJogador(Livia);
        addJogador(Luisa);
        addJogador(Caua);
        addJogador(Luis);

        addJogador(Pablo);
        addJogador(Miguel);
        addJogador(Tiago);
        addJogador(Hansel);

        addJogador(Guigui);
        addJogador(JK);
        addJogador(Froes);
        addJogador(Gleiph); */
    }

    public void inserePartidas(){
        int i, j=listaJogadores.size()-1;
        for(i=0; i<=listaJogadores.size()/2 && j>=listaJogadores.size()/2; i++){
            addPartida(listaJogadores.get(i), listaJogadores.get(j));
            j--;
        }
    }

    public void printListaJogadores(){
        for (Jogador j : listaJogadores) {
            System.out.println(j.getId() + " Jogador: " + j.getNome());
        }
    }

    public void printListaPartidas(){
        for (Partida p : listaPartidas) {
            System.out.println(p.getId() + " Partida: " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
        }
    }

    public void chaveamento(){ 
        if(this.tamanhoInicial==8){
            //oitavas
            for(int i=0; i<4; i++)
                asaEsquerdaOitavas.add(this.listaPartidas.get(i));
            for(int i=4; i<8; i++)
                asaDireitaOitavas.add(this.listaPartidas.get(i));

        }else if(this.tamanhoInicial==4){
            //quartas
            for(int i=0; i<2; i++)
                asaEsquerdaQuartas.add(this.listaPartidas.get(i));
            for(int i=2; i<4; i++)
                asaDireitaQuartas.add(this.listaPartidas.get(i));

        }else if(this.tamanhoInicial==2){
            //semi
                asaEsquerdaSemi.add(this.listaPartidas.get(0));
                asaDireitaSemi.add(this.listaPartidas.get(1));
        }
        printChaveamento();
    }

    public void printChaveamento() {
        if(this.tamanhoAtual==8){
            System.out.println("---- OITAVAS DE FINAL ----- \n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerdaOitavas);
            System.out.println("---------------------");
            System.out.println();
            System.out.println("ASA DIREITA:");
            printAsa(asaDireitaOitavas);
        }
        else if(this.tamanhoAtual==4){
            System.out.println("---- QUARTAS DE FINAL -----\n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerdaQuartas);
            System.out.println("---------------------");
            System.out.println();
            System.out.println("ASA DIREITA:");
            printAsa(asaDireitaQuartas);
        }
        else if(this.tamanhoAtual==2){
            System.out.println("---- SEMIFINAIS ----- \n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerdaSemi);
            System.out.println("---------------------");
            System.out.println();
            System.out.println("ASA DIREITA:");
            printAsa(asaDireitaSemi);
        }
    }
    public void printAsa(List<Partida> aux) {
        for (Partida p : aux) 
            System.out.println("Partida " + (p.getId()+1) + ": " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
        System.out.println();
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
            Jogador vencedorEsq = asaEsquerdaSemi.get(0).simulador();
            removeJogador(asaEsquerdaSemi.get(0).getPerdedor());
            asaEsquerdaSemi.get(0).imprimePlacar();
            System.out.println("Jogador " +vencedorEsq.getNome() + " avança para a final.");

            Jogador vencedorDir = asaDireitaSemi.get(0).simulador();
            removeJogador(asaDireitaSemi.get(0).getPerdedor());
            asaDireitaSemi.get(0).imprimePlacar();
            System.out.println("Jogador " + vencedorDir.getNome() + " avança para a final.");
            this.tamanhoAtual=0;
        }

        if(this.tamanhoAtual==0){
            //FINAL
            Jogador j1 = asaEsquerdaSemi.get(0).getVencedor(), j2 = asaDireitaSemi.get(0).getVencedor();    
            Partida f = new Partida(j1, j2);
            Jogador campeao = f.simulador();
            System.out.println(campeao.getNome() + " venceu o campeonato!");
            
        }
    }
}
