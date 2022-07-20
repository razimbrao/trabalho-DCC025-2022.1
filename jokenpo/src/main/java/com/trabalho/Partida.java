package com.trabalho;

// Rafael de Oliveira Zimbrão - 202165124A

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A

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

    public void imprimePartida(String frase1, String frase2
            , int jogadaJ1, int jogadaJ2, Jogador j1, Jogador j2, Jogador vencedor){
        String[] resultados = {"pedra", "papel", "tesoura", "lagarto", "spock"};

        JFrame frame = new JFrame("Partida");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel painel = new JPanel ();
        painel.setLayout(new BorderLayout());
        painel.setBackground(Color.white);
        frame.getContentPane().add (painel);


        // tentar botar texto

        JLabel jlabel = new JLabel(j1.getNome() + ": " + resultados[jogadaJ1] + " // " + j2.getNome() + ": " + resultados[jogadaJ2]);
        jlabel.setFont(new Font("Arial",0,30));
        jlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        painel.add(jlabel, BorderLayout.NORTH);
        JLabel parabens = new JLabel(frase1 + vencedor.getNome() + frase2);
        parabens.setFont(new Font("Arial", 0, 20));
        parabens.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        painel.add(parabens, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);

        // Colocando imagem

        ImageIcon iconeJ1 = new ImageIcon(resultados[jogadaJ1] + "Flip.jpg");
        Image image = iconeJ1.getImage();
        Image newimg1 = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
        iconeJ1 = new ImageIcon(newimg1);
        JLabel jogada1 = new JLabel(iconeJ1);
        painel.add(jogada1, BorderLayout.WEST);



        ImageIcon iconeJ2 = new ImageIcon(resultados[jogadaJ2] + ".jpg");
        image = iconeJ2.getImage();
        Image newimg2 = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
        iconeJ2 = new ImageIcon(newimg2);
        JLabel jogada2 = new JLabel(iconeJ2);
        painel.add(jogada2, BorderLayout.EAST);
        
        JButton passar = new JButton("Próximo"); 
        passar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });
        painel.add(passar, BorderLayout.PAGE_END);

        frame.setVisible(true);
        
        
    }

    public Jogador simulador(){
        // Cria loop do melhor de três
        String frase1;
        String frase2;
        Jogador vencedorImprime;

        int i = 0;
        while(i < 3 || (pontuacaoJ1 == pontuacaoJ2))
        {

            int jogadaJ1 = j1.selecionaJogada();
            int jogadaJ2 = j2.selecionaJogada();

            // switch case com todas as possiblidades

            switch((jogadaJ1*10) + jogadaJ2)
            {
                case 21:
                    frase1 = "Tesoura corta papel, jogador ";
                    vencedorImprime = j1;
                    frase2 = " ganha com tesoura";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ1++;
                    break;
                case 12:
                    frase1 = "Tesoura corta papel, jogador ";
                    vencedorImprime = j2;
                    frase2 = " ganha com tesoura";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ2++;
                    break;
                case 10:
                    frase1 = "Papel cobre pedra, jogador ";
                    vencedorImprime = j1;
                    frase2 = " ganha com papel";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ1++;
                    break;
                case 1:
                    frase1 = "Papel cobre pedra, jogador ";
                    vencedorImprime = j2;
                    frase2 = " ganha com papel";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ2++;
                    break;
                case 3:
                    frase1 = "Pedra esmaga lagarto, jogador ";
                    vencedorImprime = j1;
                    frase2 = " ganha com pedra";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ1++;
                    break;
                case 30:
                    frase1 = "Pedra esmaga lagarto, jogador ";
                    vencedorImprime = j2;
                    frase2 = " ganha com pedra";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ2++;
                    break;
                case 34:
                    frase1 = "Lagarto evenena Spock, jogador ";
                    vencedorImprime = j1;
                    frase2 = " ganha com lagarto";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ1++;
                    break;
                case 43:
                    frase1 = "Lagarto evenena Spock, jogador ";
                    vencedorImprime = j2;
                    frase2 = " ganha com lagarto";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ2++;
                    break;
                case 42:
                    frase1 = "Spock amassa tesoura, jogador ";
                    vencedorImprime = j1;
                    frase2 = " ganha com Spock";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ1++;
                    break;
                case 24:
                    frase1 = "Spock amassa tesoura, jogador ";
                    vencedorImprime = j2;
                    frase2 = " ganha com Spock";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ2++;
                    break;
                case 23:
                    frase1 = "Tesoura decapita lagarto, jogador ";
                    vencedorImprime = j1;
                    frase2 = " ganha com tesoura";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ1++;
                    break;
                case 32:
                    frase1 = "Tesoura decapita lagarto, jogador ";
                    vencedorImprime = j2;
                    frase2 = " ganha com tesoura";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ2++;
                    break;
                case 31:
                    frase1 = "Lagarto come papel, jogador ";
                    vencedorImprime = j1;
                    frase2 = " ganha com lagarto";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ1++;
                    break;
                case 13:
                    frase1 = "Lagarto come papel, jogador ";
                    vencedorImprime = j2;
                    frase2 = " ganha com lagarto";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ2++;
                    break;
                case 14:
                    frase1 = "Papel desmente Spock, jogador ";
                    vencedorImprime = j1;
                    frase2 = " ganha com papel";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ1++;
                    break;
                case 41:
                    frase1 = "Papel desmente Spock, jogador ";
                    vencedorImprime = j2;
                    frase2 = " ganha com papel";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ2++;
                    break;
                case 40:
                    frase1 = "Spock pulveriza pedra, jogador ";
                    vencedorImprime = j1;
                    frase2 = " ganha com Spock";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ1++;
                    break;
                case 4:
                    frase1 = "Spock pulveriza pedra, jogador ";
                    vencedorImprime = j2;
                    frase2 = " ganha com Spock";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ2++;
                    break;
                case 2:
                    frase1 = "Pedra amassa tesoura, jogador ";
                    vencedorImprime = j1;
                    frase2 = " ganha com pedra";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
                    pontuacaoJ1++;
                    break;
                case 20:
                    frase1 = "Pedra amassa tesoura, jogador ";
                    vencedorImprime = j2;
                    frase2 = " ganha com pedra";
                    this.imprimePartida(frase1, frase2, jogadaJ1, jogadaJ2, j1, j2, vencedorImprime);
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
