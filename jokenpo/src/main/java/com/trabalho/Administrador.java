/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trabalho;
import javax.swing.JOptionPane;
/**
 *
 * @author Usuário
 */

// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A
public class Administrador extends Jogador{
    
    public Administrador(String x) {
        super(x);
        setEhBot(false);
    }

    @Override
    public int selecionaJogada() {
        String[] opcoesJogada = {"Pedra", "Papel", "Tesoura", "Lagarto", "Spock"};
        int jogada = JOptionPane.showOptionDialog(null, "Selecione sua Jogada:", "Jogada:"
                ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesJogada, opcoesJogada[0]);
        //int jogada = Integer.parseInt(JOptionPane.showInputDialog("Insira sua jogada: 0-pedra/1-papel/2-tesoura/3-lagarto/4-spock"));
        return jogada;
    }
}
