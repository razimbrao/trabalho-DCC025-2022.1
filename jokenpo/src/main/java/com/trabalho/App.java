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
        liberta.insereJogadores();
        liberta.inserePartidas();
        liberta.chaveamento();

        //liberta.resolveNivel();
    }
}
