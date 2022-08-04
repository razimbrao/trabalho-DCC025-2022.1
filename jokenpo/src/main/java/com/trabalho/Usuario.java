package com.trabalho;

import javax.swing.JOptionPane;

// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A
public class Usuario extends Jogador {

    public Usuario(String x) {
        super(x);
        setEhBot(false);
    }


    @Override
    public int selecionaJogada() {
        String[] opcoesJogada = { "Pedra", "Papel", "Tesoura", "Lagarto", "Spock" };
        int jogada = JOptionPane.showOptionDialog(null, "Selecione sua Jogada:", "Jogada:", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoesJogada, opcoesJogada[0]);
        return jogada;
    }
}
