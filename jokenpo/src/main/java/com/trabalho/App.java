package com.trabalho;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
         
        Campeonato liberta = new Campeonato(8);
        System.out.println(liberta.indexPartidas);
        liberta.insereJogadores();
        liberta.inserePartidas();
        liberta.printListaJogadores();
        liberta.printListaPartidas();
        //liberta.chaveamento();

        /*System.out.println("Asa Esquerda:");
        for (Partida p : liberta.asaEsquerda) {
            System.out.println("Partida: " + p.getId());
        }

        System.out.println("Asa Direita:");
        for (Partida p : liberta.asaDireita) {
            System.out.println("Partida: " + p.getId());
        }


        liberta.resolveNivel();*/
        
        //teste de commit livia
    }
}
