package com.trabalho;

// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A
public class Bot extends Jogador {

    public Bot(String x) {
        super(x);
        setEhBot(true);
    }

    /**
     *
     * @return
     */
    @Override
    public int selecionaJogada()
    {
        int jogada = (int)((0 + (5 - 0)) * Math.random());
        return jogada;
    }
}
