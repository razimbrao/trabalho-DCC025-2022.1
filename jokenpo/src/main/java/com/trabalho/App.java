package com.trabalho;

import javax.swing.JOptionPane;
import com.trabalho.view.Tela;
// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A
public final class App {
    private App() {
    }

    public static void comecarJogo(Tela tela) {
        String[] opcoesTamanho = { "Semifinal", "Quartas de final", "Oitavas de final" };
        int tamanho = JOptionPane.showOptionDialog(null, "Selecione o tamanho do campeonato desejado:",
                "Tamanho da partida", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTamanho,
                opcoesTamanho[0]);

        Campeonato camp = new Campeonato((int) Math.pow(2, (tamanho + 1)), tela);
        camp.insereJogadores();
        camp.inserePartidas();
        camp.chaveamento();
        tela.setJogoComecou(false);
        enviarListaVitorias(tela, camp);
    }

    public static void enviarListaVitorias(Tela tela, Campeonato camp) {
        tela.setListaAux(camp.getListaVitorias());
        tela.somaListas();
    }

    public static void main(String[] args) {
        Tela tela = new Tela();
        while(true){
            while (!tela.getJogoComecou()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(tela.getJogoComecou())
                comecarJogo(tela);
        }
    }
}
