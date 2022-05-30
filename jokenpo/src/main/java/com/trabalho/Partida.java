package com.trabalho;
import java.util.*;

public class Partida {
    private Jogador jogador1;
    private Jogador jogador2;
    private int pontuacaoJ1;
    private int pontuacaoJ2;
    private Jogador vencedor;

    public Partida(Jogador j1, Jogador j2){
        this.jogador1 = j1;
        this.jogador2 = j2;
        pontuacaoJ1 = 0;
        pontuacaoJ2 = 0;
    }

    public Jogador simulador(Jogador j1, Jogador j2){
        // Cria loop do melhor de três
        
        int i = 0;
        while(i < 3 || (pontuacaoJ1 != 0 && pontuacaoJ2 !=0))
        {
            String[] resultados = {"pedra", "papel", "tesoura", "lagarto", "spock"};
            
            // Seleciona o valor de cada um aleatoriamente 
            
            String jogadaJ1 = "tesoura"; //resultados[(int)((0 + (5 - 0)) * Math.random())];
            String jogadaJ2 = "papel"; //resultados[(int)((0 + (5 - 0)) * Math.random())];
            String[] jogadas = {jogadaJ1, jogadaJ2};
            
            System.out.println("j1: " + jogadaJ1 + " j2: " + jogadaJ2);
            
            // dicionário com os resultados possíveis
            
            //HashMap<String[], Integer[]> relacoesEntreJogadas = new HashMap<>();
            //relacoesEntreJogadas.put({"tesoura", "papel"}, {1, 1});
                
            // atualiza loop
                
            i++;
        }
        
        // Imprime e retorna o jogador 
        
        
        if(pontuacaoJ1 > pontuacaoJ2)
        {
            vencedor = j1;
        }
        else
        {
            vencedor = j2;
        }
        
        System.out.println("O vencedor eh " + j1.getNome());
        return j1;
    }

    public void imprimePlacar(){
        System.out.println(this.pontuacaoJ1 + " x " + this.pontuacaoJ2);
    }
}
