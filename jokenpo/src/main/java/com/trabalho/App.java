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
        String nome="Rafael";
        Jogador primeiro = new Jogador(nome);        
        Jogador segundo = new Jogador("Vitin");      
        Partida semi = new Partida(primeiro, segundo);
        semi.imprimePlacar();
        System.out.println("Numero de jogadores: "+ Jogador.getnJogadores()); 
    }
}
