package com.trabalho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A

public class Campeonato {
    private int tamanho;
    private int nJogadores;
    private int indexPartidas;
    private boolean temUsuario;

    private Partida partidaFinal;
    private List<Jogador> listaJogadores = new ArrayList<>();
    private List<Partida> listaPartidas = new ArrayList<>();
    private List<Jogador> listaJogadoresAux = new ArrayList<>();
    private List<Partida> asaEsquerda = new ArrayList<>();
    private List<Partida> asaDireita = new ArrayList<>();

    public Campeonato(int n) { // informa qual o tamanho do campeonato
        Scanner sc = new Scanner(System.in);
        this.tamanho = n; // TODO: por try/catch depois
        System.out.println("Digite 1 para jogar o campeonato ou 2 para apenas simular.");
        if (sc.nextInt() == 1)
            this.temUsuario = true;
        else
            this.temUsuario = false;

        if (this.tamanho == 8) {
            System.out.println("Oitavas de final criadas!");
        } else if (this.tamanho == 4) {
            System.out.println("Quartas de final criadas!");

        } else if (this.tamanho == 2)
            System.out.println("Semifinais criadas!");
    }

    public void addJogador(Jogador x) { // add o jogador na listaJogadores
        this.listaJogadores.add(x);
        this.nJogadores++;
        int id = this.nJogadores - 1;
        x.setId(id);
    }

    public void addPartida(Jogador j1, Jogador j2) { // add a partida na listaPartidas
        Partida aux = new Partida(j1, j2);
        aux.setId(indexPartidas);
        this.indexPartidas++;
        this.listaPartidas.add(aux);
    }

    public void insereJogadores() { // => insere os jogadores na listaJogadores
        Jogador vetJogador[] = new Jogador[tamanho * 2];
        String nome;
        int i = 0, size = this.tamanho * 2;
        Scanner sc = new Scanner(System.in);
        if (temUsuario == true) {
            size = size - 1;
            i = 1;
            System.out.println("Insira o nome do Usuário: ");
            nome = sc.nextLine();
            vetJogador[i] = new Usuario(nome);
            addJogador(vetJogador[i]);
        }

        System.out.println("Insira " + size + " bots:");
        for (; i < vetJogador.length; i++) {
            System.out.println("Insira o nome do Bot " + i + ": ");
            nome = sc.nextLine();
            vetJogador[i] = new Bot(nome);
            addJogador(vetJogador[i]);
        }
    }

    public void inserePartidas() { // pega a lista de jogadores e cria as partidas na listaPartidas
        int i, j = listaJogadores.size() - 1;
        for (i = 0; i <= listaJogadores.size() / 2 && j >= listaJogadores.size() / 2; i++) {
            addPartida(listaJogadores.get(i), listaJogadores.get(j));
            j--;
        }
    }

    public void chaveamento() { // faz o chaveamento do campeonato
        if (this.tamanho == 8) {
            // oitavas
            for (int i = 0; i < 4; i++) {
                asaEsquerda.add(this.listaPartidas.get(i));
            }
            for (int i = 4; i < 8; i++) {
                asaDireita.add(this.listaPartidas.get(i));
            }
        } else if (this.tamanho == 4) {
            // quartas
            asaEsquerda.clear();
            asaDireita.clear();
            for (int i = 0; i < 2; i++)
                asaEsquerda.add(this.listaPartidas.get(i));
            for (int i = 2; i < 4; i++)
                asaDireita.add(this.listaPartidas.get(i));

        } else if (this.tamanho == 2) {
            // semi
            asaEsquerda.clear();
            asaDireita.clear();
            asaEsquerda.add(this.listaPartidas.get(0));
            asaDireita.add(this.listaPartidas.get(1));
        } else if (this.tamanho == 0) {
            partidaFinal = new Partida(asaEsquerda.get(0).getVencedor(), asaDireita.get(0).getVencedor());
        }
        printChaveamento();
    }

    public void resolveNivel() {
        if (this.tamanho == 8) {
            // OITAVAS
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

            this.tamanho = 4;
            chaveamento();
        }

        if (this.tamanho == 4) {
            // QUARTAS
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
            this.tamanho = 2;
            chaveamento();
        }

        if (this.tamanho == 2) {
            // SEMI
            Jogador v1 = asaEsquerda.get(0).simulador();
            listaJogadoresAux.add(v1);
            System.out.println("Jogador " + v1.getNome() + " avança para a final.");

            Jogador v2 = asaDireita.get(0).simulador();
            listaJogadoresAux.add(v2);
            System.out.println("Jogador " + v2.getNome() + " avança para a final.");
            this.tamanho = 0;
            chaveamento();
        }

        if (this.tamanho == 0) {
            // FINAL
            Jogador campeao = partidaFinal.simulador();
            System.out.println(campeao.getNome() + " venceu o campeonato!");

        }
    }

    // ------------------ PRINTS ---------------------------------- //

    public void printChaveamento() {
        if (this.tamanho == 8) {
            System.out.println("---- OITAVAS DE FINAL ----- \n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerda);
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            printAsa(asaDireita);
            System.out.println();
        }
        if (this.tamanho == 4) {
            System.out.println("\n---- QUARTAS DE FINAL -----\n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerda);
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            printAsa(asaDireita);
            System.out.println();
        }
        if (this.tamanho == 2) {
            System.out.println("\n---- SEMIFINAIS ----- \n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerda);
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            printAsa(asaDireita);
            System.out.println();
        }
        if (this.tamanho == 0) {
            System.out.println("\n---- GRANDE FINAL ----- \n");
            System.out.println("ASA ESQUERDA:");
            System.out.println(asaEsquerda.get(0).getVencedor().getNome());
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            System.out.println(asaDireita.get(0).getVencedor().getNome() + "\n");
        }
    }

    public void printListaJogadores() {
        for (Jogador j : listaJogadores) {
            System.out.println(j.getId() + " Jogador: " + j.getNome());
        }
    }

    public void printListaPartidas() {
        for (Partida p : listaPartidas) {
            System.out.println(p.getId() + " Partida: " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
        }
    }

    public void printAsa(List<Partida> aux) {
        for (Partida p : aux)
            System.out.println("Partida " + (p.getId() + 1) + ": " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
    }
    // ------------------ FIM-PRINTS ---------------------------------- //

    /*
     * LOGICA DO ID NÃO TA FUNCIONANDO
     * public void removeJogador(Jogador x)
     * {
     * int index=x.getId();
     * this.listaJogadores.remove(index);
     * }
     * 
     * public void removePartida(Partida x)
     * {
     * this.listaPartidas.remove(x.getId());
     * }
     */
}