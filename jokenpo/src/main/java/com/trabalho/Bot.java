/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trabalho;

/**
 *
 * @author lilik
 */
public class Bot extends Jogador {

    public Bot(String x) {
        super(x);
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
