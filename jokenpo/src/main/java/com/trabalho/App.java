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
         
        Campeonato camp = new Campeonato(2);
        camp.insereJogadores();
        camp.inserePartidas();
        camp.chaveamento();
        camp.resolveNivel();
        

    }
}
