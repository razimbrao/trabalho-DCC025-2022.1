package com.trabalho;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private int tamanho;
    private int nJogadores;
    private int indexPartidas;
    
    private Partida partidaFinal;
    private List<Jogador> listaJogadores = new ArrayList<>();
    private List<Partida> listaPartidas = new ArrayList<>();
    private List<Jogador> listaJogadoresAux = new ArrayList<>();
    private List<Partida> asaEsquerda = new ArrayList<>();
    private List<Partida> asaDireita = new ArrayList<>();

    public Campeonato(int n){ //informa qual o tamanho do campeonato
        if(n==8 || n==4 || n==2){
            this.tamanho = n;
        }
        else{
            System.out.println("ERRO: Número de partidas inválidas!");
            System.exit(0);
        }
        if(this.tamanho==8){
            System.out.println("Oitavas de final criadas!");
        }else if(this.tamanho==4){
            System.out.println("Quartas de final criadas!");

        }else if(this.tamanho==2)
            System.out.println("Semifinais criadas!");
    }

    public void addJogador(Jogador x){ // add o jogador na listaJogadores
        this.listaJogadores.add(x);
        this.nJogadores++;
        int id = this.nJogadores-1;
        x.setId(id);
    }

    public void addPartida(Jogador j1, Jogador j2) { // add a partida na listaPartidas
        Partida aux = new Partida(j1, j2);
        aux.setId(indexPartidas);
        this.indexPartidas++;
        this.listaPartidas.add(aux);
    }

    public void insereJogadores(){ //teste => insere sozinho os jogadores na listaJogadores
        Jogador Rafael = new Jogador("Rafael"); addJogador(Rafael);
        Jogador Vidal = new Jogador("Vidal"); addJogador(Vidal);
        Jogador Vitin = new Jogador("Vitin"); addJogador(Vitin);
        Jogador Cacho = new Jogador("Cacho"); addJogador(Cacho);

        Jogador Livia = new Jogador("Livia"); addJogador(Livia);
        Jogador Luisa = new Jogador("Luisa"); addJogador(Luisa);
        Jogador Caua = new Jogador("Caua"); addJogador(Caua);
        Jogador Luis = new Jogador("Luis"); addJogador(Luis);
        
        Jogador Pablo = new Jogador("Pablo"); addJogador(Pablo);
        Jogador Miguel = new Jogador("Miguel"); addJogador(Miguel);
        Jogador Tiago = new Jogador("Tiago"); addJogador(Tiago);
        Jogador Hansel = new Jogador("Hansel"); addJogador(Hansel);

        Jogador Guigui = new Jogador("Guigui"); addJogador(Guigui);
        Jogador JK = new Jogador("JK"); addJogador(JK);
        Jogador Froes = new Jogador("Froes"); addJogador(Froes);
        Jogador Gleiph = new Jogador("Gleiph"); addJogador(Gleiph);
    }

    public void inserePartidas(){ //pega a lista de jogadores e cria as partidas na listaPartidas
        int i, j=listaJogadores.size()-1;
        for(i=0; i<=listaJogadores.size()/2 && j>=listaJogadores.size()/2; i++){
            addPartida(listaJogadores.get(i), listaJogadores.get(j));
            j--;
        }
    }

    public void chaveamento(){  //faz o chaveamento do campeonato
        if(this.tamanho==8){
            //oitavas
            for(int i=0; i<4; i++){
                asaEsquerda.add(this.listaPartidas.get(i));
            }
            for(int i=4; i<8; i++){
                asaDireita.add(this.listaPartidas.get(i));
            }
        }else if(this.tamanho==4){
            //quartas
            asaEsquerda.clear();
            asaDireita.clear();
            for(int i=0; i<2; i++)
                asaEsquerda.add(this.listaPartidas.get(i));
            for(int i=2; i<4; i++)
                asaDireita.add(this.listaPartidas.get(i));

        }else if(this.tamanho==2){
            //semi
            asaEsquerda.clear();
            asaDireita.clear();
            asaEsquerda.add(this.listaPartidas.get(0));
            asaDireita.add(this.listaPartidas.get(1));
        }else if(this.tamanho==0){
            partidaFinal = new Partida(asaEsquerda.get(0).getVencedor(), asaDireita.get(0).getVencedor());
        }
        printChaveamento();
    }

    public void resolveNivel(){
        if(this.tamanho==8){
            //OITAVAS
            Jogador v1 = asaEsquerda.get(0).simulador();
            listaJogadoresAux.add(v1);
            System.out.println("Jogador " + v1.getNome() + " avança para as quartas de final.");

            Jogador v2 = asaEsquerda.get(1).simulador();
            listaJogadoresAux.add(v2);

            System.out.println("Jogador " + v2.getNome() + " avança para as quartas de final.");

            Jogador v3 = asaEsquerda.get(2).simulador();
            listaJogadoresAux.add(v3);
            System.out.println("Jogador " + v3.getNome() + " avança para as quartas de final.");

            Jogador v4 = asaEsquerda.get(3).simulador();
            listaJogadoresAux.add(v4);
            System.out.println("Jogador " + v4.getNome() + " avança para as quartas de final.");

            Jogador v5 = asaDireita.get(0).simulador();
            listaJogadoresAux.add(v5);
            System.out.println("Jogador " + v5.getNome() + " avança para as quartas de final.");

            Jogador v6 = asaDireita.get(1).simulador();
            listaJogadoresAux.add(v6);
            System.out.println("Jogador " + v6.getNome() + " avança para as quartas de final.");

            Jogador v7 = asaDireita.get(2).simulador();
            listaJogadoresAux.add(v7);
            System.out.println("Jogador " + v7.getNome() + " avança para as quartas de final.");

            Jogador v8 = asaDireita.get(3).simulador();
            listaJogadoresAux.add(v8);
            System.out.println("Jogador " + v8.getNome() + " avança para as quartas de final.");


            listaJogadores.clear();
            listaPartidas.clear();
            listaJogadores = new ArrayList<>(listaJogadoresAux);
            listaJogadoresAux.clear();
            inserePartidas();

            this.tamanho=4;
            chaveamento();
        }

        if(this.tamanho==4){
            //QUARTAS
            Jogador v1 = asaEsquerda.get(0).simulador();
            listaJogadoresAux.add(v1);
            System.out.println("Jogador " + v1.getNome() + " avança para as semifinais.");

            Jogador v2 = asaEsquerda.get(1).simulador();
            listaJogadoresAux.add(v2);
            System.out.println("Jogador " + v2.getNome() + " avança para as semifinais.");

            Jogador v3 = asaDireita.get(0).simulador();
            listaJogadoresAux.add(v3);
            System.out.println("Jogador " + v3.getNome() + " avança para as semifinais.");

            Jogador v4 = asaDireita.get(1).simulador();
            listaJogadoresAux.add(v4);
            System.out.println("Jogador " + v4.getNome() + " avança para as semifinais.");

            listaJogadores.clear();
            listaPartidas.clear();
            listaJogadores = new ArrayList<>(listaJogadoresAux);
            inserePartidas();
            this.tamanho=2;
            chaveamento(); 
        } 

        if(this.tamanho==2){
            //SEMI
            Jogador v1 = asaEsquerda.get(0).simulador();
            listaJogadoresAux.add(v1);
            System.out.println("Jogador " +v1.getNome() + " avança para a final.");

            Jogador v2 = asaDireita.get(0).simulador();
            listaJogadoresAux.add(v2);
            System.out.println("Jogador " + v2.getNome() + " avança para a final.");
            this.tamanho=0;
            chaveamento();
        }

        if(this.tamanho==0){
            //FINAL
            Jogador campeao = partidaFinal.simulador();
            System.out.println(campeao.getNome() + " venceu o campeonato!");
    
        } 
    }

    // ------------------ PRINTS ---------------------------------- //

    public void printChaveamento() {
        if(this.tamanho==8){
            System.out.println("---- OITAVAS DE FINAL ----- \n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerda);
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            printAsa(asaDireita);
            System.out.println();
        }
        if(this.tamanho==4){
            System.out.println("\n---- QUARTAS DE FINAL -----\n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerda);
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            printAsa(asaDireita);
            System.out.println();
        }
        if(this.tamanho==2){
            System.out.println("\n---- SEMIFINAIS ----- \n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerda);
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            printAsa(asaDireita);
            System.out.println();
        }
        if(this.tamanho==0){
            System.out.println("\n---- GRANDE FINAL ----- \n");
            System.out.println("ASA ESQUERDA:");
            System.out.println(asaEsquerda.get(0).getVencedor().getNome());
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            System.out.println(asaDireita.get(0).getVencedor().getNome()+"\n");

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

    public void printAsa(List<Partida> aux) {
        for (Partida p : aux) 
            System.out.println("Partida " + (p.getId()+1) + ": " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
    }
    // ------------------ FIM-PRINTS ---------------------------------- //

       /*  LOGICA DO ID NÃO TA FUNCIONANDO
   public void removeJogador(Jogador x) 
    {
        int index=x.getId();
        this.listaJogadores.remove(index);
    }
    
    public void removePartida(Partida x)
    {
        this.listaPartidas.remove(x.getId());
    } */
}