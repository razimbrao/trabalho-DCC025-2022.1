package com.trabalho;

import javax.swing.JOptionPane;

// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A
public final class App {
    private App() {
    }

    public static void main(String[] args) {

        String[] opcoesTamanho = { "Semifinal", "Quartas de final", "Oitavas de final" };
        int tamanho = JOptionPane.showOptionDialog(null, "Selecione o tamanho do campeonato desejado:",
                "Tamanho da partida", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTamanho,
                opcoesTamanho[0]);

        Campeonato camp = new Campeonato((int) Math.pow(2, (tamanho + 1)));
        camp.insereJogadores();
        camp.inserePartidas();
        camp.chaveamento();
    }
}
