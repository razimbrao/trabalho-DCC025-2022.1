package com.trabalho;
import javax.swing.JOptionPane;

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
        
        String[] opcoesTamanho = {"Dois níveis", "Quatro níveis" , "Oito níveis"};
        int tamanho = JOptionPane.showOptionDialog(null, "Selecione o tamanho do campeonato desejado:", "Tamanho da partida"
                ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTamanho, opcoesTamanho[0]);
        
        Campeonato camp = new Campeonato((int)Math.pow(2, (tamanho + 1)));
        camp.insereJogadores();
        camp.inserePartidas();
        camp.chaveamento();
        camp.resolveNivel();
    }
}
