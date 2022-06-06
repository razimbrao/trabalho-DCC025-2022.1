package com.trabalho;

public class Partida {
    private Jogador j1;
    private Jogador j2;
    private int pontuacaoJ1;
    private int pontuacaoJ2;
    private Jogador vencedor;
    private Jogador perdedor;
    private int id;

    public Partida(Jogador j1, Jogador j2){
        this.j1 = j1;
        this.j2 = j2;
        pontuacaoJ1 = 0;
        pontuacaoJ2 = 0;
    }

    public Jogador simulador(){
        // Cria loop do melhor de três
        
        int i = 0;
        while(i < 3 || (pontuacaoJ1 == pontuacaoJ2))
        {
            String[] resultados = {"pedra", "papel", "tesoura", "lagarto", "spock"};
            
            int jogadaJ1 = (int)((0 + (5 - 0)) * Math.random());
            int jogadaJ2 = (int)((0 + (5 - 0)) * Math.random());
            //String[] jogadas = {jogadaJ1, jogadaJ2};
            
            System.out.println(j1.getNome() + ": " + resultados[jogadaJ1] + " // " + j2.getNome() + ": " + resultados[jogadaJ2]);
            
            // switch case com todas as possiblidades
            
            switch((jogadaJ1*10) + jogadaJ2)
            {
                case 21:
                    System.out.println("Tesoura corta papel, jogador " + j1.getNome() + " ganha com tesoura");
                    pontuacaoJ1++;
                    break;
                case 12:
                    System.out.println("Tesoura corta papel, jogador " + j2.getNome() + " ganha com tesoura");
                    pontuacaoJ2++;
                    break;
                case 10:
                    System.out.println("Papel cobre pedra, jogador " + j1.getNome() + " ganha com papel");
                    pontuacaoJ1++;
                    break;
                case 1:
                    System.out.println("Papel cobre pedra, jogador " + j2.getNome() + " ganha com papel");
                    pontuacaoJ2++;
                    break;                    
                case 3:
                    System.out.println("Pedra esmaga lagarto, jogador " + j1.getNome() + " ganha com pedra");
                    pontuacaoJ1++;
                    break;
                case 30:
                    System.out.println("Pedra esmaga lagarto, jogador " + j2.getNome() + " ganha com pedra");
                    pontuacaoJ2++;
                    break; 
                case 34:
                    System.out.println("Lagarto evenena Spock, jogador " + j1.getNome() + " ganha com lagarto");
                    pontuacaoJ1++;
                    break;
                case 43:
                    System.out.println("Lagarto evenena Spock, jogador " + j2.getNome() + " ganha com lagarto");
                    pontuacaoJ2++;
                    break;        
                case 42:
                    System.out.println("Spock amassa tesoura, jogador " + j1.getNome() + " ganha com Spock");
                    pontuacaoJ1++;
                    break;
                case 24:
                    System.out.println("Spock amassa tesoura, jogador " + j2.getNome() + " ganha com Spock");
                    pontuacaoJ2++;
                    break; 
                case 23:
                    System.out.println("Tesoura decapita lagarto, jogador " + j1.getNome() + " ganha com tesoura");
                    pontuacaoJ1++;
                    break;
                case 32:
                    System.out.println("Tesoura decapita lagarto, jogador " + j2.getNome() + " ganha com tesoura");
                    pontuacaoJ2++;
                    break;     
                case 31:
                    System.out.println("Lagarto come papel, jogador " + j1.getNome() + " ganha com lagarto");
                    pontuacaoJ1++;
                    break;
                case 13:
                    System.out.println("Lagarto come papel, jogador " + j2.getNome() + " ganha com lagarto");
                    pontuacaoJ2++;
                    break;   
                case 14:
                    System.out.println("Papel desmente Spock, jogador " + j1.getNome() + " ganha com papel");
                    pontuacaoJ1++;
                    break;
                case 41:
                    System.out.println("Papel desmente Spock, jogador " + j2.getNome() + " ganha com papel");
                    pontuacaoJ2++;
                    break;    
                case 40:
                    System.out.println("Spock pulveriza pedra, jogador " + j1.getNome() + " ganha com Spock");
                    pontuacaoJ1++;
                    break;
                case 4:
                    System.out.println("Spock pulveriza pedra, jogador " + j2.getNome() + " ganha com Spock");
                    pontuacaoJ2++;
                    break; 
                case 2:
                    System.out.println("Pedra amassa tesoura, jogador " + j1.getNome() + " ganha com pedra");
                    pontuacaoJ1++;
                    break;
                case 20:
                    System.out.println("Pedra amassa tesoura, jogador " + j2.getNome() + " ganha com pedra");
                    pontuacaoJ2++;
                    break;                     
                default:
                    System.out.println("Empate");
            }
                
            i++;
        }
          
        if(pontuacaoJ1 > pontuacaoJ2)
        {
            this.vencedor = j1;
            this.perdedor = j2;
        }
        else
        {
            this.vencedor = j2;
            this.perdedor = j1;
        }
        
        System.out.println("O vencedor eh " + vencedor.getNome());
        imprimePlacar();
        
        return vencedor;
    }

    public void imprimePlacar(){
        System.out.println(this.pontuacaoJ1 + " x " + this.pontuacaoJ2);
    }

    public void setId(int x){
        this.id=x;
    }
    
    public int getId(){
        return this.id;
    }
    public Jogador getPerdedor(){
        return this.perdedor;
    }
    public Jogador getVencedor(){
        return this.vencedor;
    }

    public Jogador getJ1(){
        return this.j1;
    }
    public Jogador getJ2(){
        return this.j2;
    }
}
