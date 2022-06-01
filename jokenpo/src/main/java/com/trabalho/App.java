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
         
        Campeonato liberta = new Campeonato(2);
        liberta.insereJogadores();
        liberta.chaveamento();
        liberta.resolveNivel();
        
        
        //teste de commit livia
    }
}
