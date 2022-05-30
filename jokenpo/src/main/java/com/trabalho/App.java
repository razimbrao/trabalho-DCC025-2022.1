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
         
        Jogador Carlitos = new Jogador("Carlitos");
        Jogador Bob = new Jogador("Bob");
        Partida teste = new Partida(Carlitos, Bob);
        Jogador vencedor = teste.simulador(Carlitos, Bob);
        
        
        //teste de commit livia
    }
}
