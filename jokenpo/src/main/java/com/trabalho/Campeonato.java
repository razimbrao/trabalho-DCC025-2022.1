package com.trabalho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A

import com.github.javafaker.Faker;
import javax.swing.JOptionPane;

public class Campeonato {
    private int tamanho;
    private int nJogadores;
    private int indexPartidas;
    private boolean temUsuario;

    private Partida partidaFinal;
    private List<Jogador> listaJogadores = new ArrayList<>();
    private List<Partida> listaPartidas = new ArrayList<>();
    private List<Jogador> listaJogadoresAux = new ArrayList<>();
    private List<Partida> asaEsquerda = new ArrayList<>();
    private List<Partida> asaDireita = new ArrayList<>();

    public Campeonato(int n) { // informa qual o tamanho do campeonato
        Scanner sc = new Scanner(System.in);
        this.tamanho = n; // TODO: por try/catch depois
        String[] opcoesJogo = {"Simular", "Jogar"};
        int opcaoJogo = JOptionPane.showOptionDialog(null, "Selecione o modo de jogo:", "Modo de Jogo"
                ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesJogo, opcoesJogo[1]);
        System.out.println("Digite 1 para jogar o campeonato ou 2 para apenas simular.");
         if (opcaoJogo == 1)
            this.temUsuario = true;
         else
            this.temUsuario = false;

        if (this.tamanho == 8) {
            System.out.println("Oitavas de final criadas!");
        } else if (this.tamanho == 4) {
            System.out.println("Quartas de final criadas!");

        } else if (this.tamanho == 2)
            System.out.println("Semifinais criadas!");
    }

    public void addJogador(Jogador x) { // add o jogador na listaJogadores
        this.listaJogadores.add(x);
        this.nJogadores++;
        int id = this.nJogadores - 1;
        x.setId(id);
    }

    public void addPartida(Jogador j1, Jogador j2) { // add a partida na listaPartidas
        Partida aux = new Partida(j1, j2);
        aux.setId(indexPartidas);
        this.indexPartidas++;
        this.listaPartidas.add(aux);
    }

    public void insereJogadores() { // => insere os jogadores na listaJogadores
        Jogador vetJogador[] = new Jogador[tamanho * 2];
        Faker faker = new Faker( new Locale("pt-BR") );
        String nome = faker.name().firstName(), aux;
        int i = 0, size = this.tamanho * 2;
        Scanner sc = new Scanner(System.in);
        if (temUsuario == true) {
            size = size - 1;
            i = 1;
            System.out.println("Insira o nome do Usuário: ");
            nome = sc.nextLine();
            vetJogador[i] = new Usuario(nome);
            addJogador(vetJogador[i]);
        }

        for (; i < vetJogador.length; i++) {
            aux = faker.name().firstName();
            if(!nome.equals(aux))
                nome = aux;
            else
                nome = faker.name().firstName();

            vetJogador[i] = new Bot(nome);
            addJogador(vetJogador[i]);
        }
    }

    public void inserePartidas() { // pega a lista de jogadores e cria as partidas na listaPartidas
        int i, j = listaJogadores.size() - 1;
        for (i = 0; i <= listaJogadores.size() / 2 && j >= listaJogadores.size() / 2; i++) {
            addPartida(listaJogadores.get(i), listaJogadores.get(j));
            j--;
        }
    }

    public void chaveamento() { // faz o chaveamento do campeonato
        if (this.tamanho == 8) {
            // oitavas
            for (int i = 0; i < 4; i++)
                asaEsquerda.add(this.listaPartidas.get(i));
            for (int i = 4; i < 8; i++)
                asaDireita.add(this.listaPartidas.get(i));

        } else if (this.tamanho == 4) {
            // quartas
            asaEsquerda.clear();
            asaDireita.clear();
            for (int i = 0; i < 2; i++)
                asaEsquerda.add(this.listaPartidas.get(i));
            for (int i = 2; i < 4; i++)
                asaDireita.add(this.listaPartidas.get(i));

        } else if (this.tamanho == 2) {
            // semi
            asaEsquerda.clear();
            asaDireita.clear();
            asaEsquerda.add(this.listaPartidas.get(0));
            asaDireita.add(this.listaPartidas.get(1));
        } else if (this.tamanho == 0) {
            partidaFinal = new Partida(asaEsquerda.get(0).getVencedor(), asaDireita.get(0).getVencedor());
        }
        printChaveamento();
    }

    public void resolveNivel() {
        if (this.tamanho == 8) {
            // OITAVAS
            Jogador vetV[] = new Jogador[tamanho];
            vetV[0] = asaEsquerda.get(0).simulador();
            vetV[1] = asaEsquerda.get(1).simulador();
            vetV[2] = asaEsquerda.get(2).simulador();
            vetV[3] = asaEsquerda.get(3).simulador();
            vetV[4] = asaDireita.get(0).simulador();
            vetV[5] = asaDireita.get(1).simulador();
            vetV[6] = asaDireita.get(2).simulador();
            vetV[7] = asaDireita.get(3).simulador();

            for (Jogador jogador : vetV) {
                listaJogadoresAux.add(jogador);
                System.out.println("Jogador " + jogador.getNome() + " avança para as quartas de final.");
            }

            listaJogadores.clear();
            listaPartidas.clear();
            listaJogadores = new ArrayList<>(listaJogadoresAux);
            listaJogadoresAux.clear();
            inserePartidas();

            this.tamanho = 4;
            chaveamento();
        }

        if (this.tamanho == 4) {
            // QUARTAS

            Jogador vetV[] = new Jogador[tamanho];
            vetV[0] = asaEsquerda.get(0).simulador();
            vetV[1] = asaEsquerda.get(1).simulador();
            vetV[2] = asaDireita.get(0).simulador();
            vetV[3] = asaDireita.get(1).simulador();

            for (Jogador jogador : vetV) {
                listaJogadoresAux.add(jogador);
                System.out.println("Jogador " + jogador.getNome() + " avança para as semifinais.");
            }

            listaJogadores.clear();
            listaPartidas.clear();
            listaJogadores = new ArrayList<>(listaJogadoresAux);
            inserePartidas();
            this.tamanho = 2;
            chaveamento();
        }

        if (this.tamanho == 2) {
            // SEMI
            Jogador v1 = asaEsquerda.get(0).simulador();
            listaJogadoresAux.add(v1);
            System.out.println("Jogador " + v1.getNome() + " avança para a final.");

            Jogador v2 = asaDireita.get(0).simulador();
            listaJogadoresAux.add(v2);
            System.out.println("Jogador " + v2.getNome() + " avança para a final.");
            this.tamanho = 0;
            chaveamento();
        }

        if (this.tamanho == 0) {
            // FINAL
            Jogador campeao = partidaFinal.simulador();
            this.mensagemVencedorFinal(campeao);

        }
    }

    // ------------------ PRINTS ---------------------------------- //
    
    public void mensagemVencedorFinal(Jogador campeao) {
        JFrame frame = new JFrame("Vencedor");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        JPanel painel = new JPanel ();
        painel.setLayout(new BorderLayout());
        painel.setBackground(Color.white);
        frame.getContentPane().add (painel);
        
        
        // tentar botar texto
        
        JLabel jlabel = new JLabel(campeao.getNome() + " venceu o campeonato!");
        jlabel.setFont(new Font("Arial",0,30));
        jlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        painel.add(jlabel, BorderLayout.NORTH);
        JLabel parabens = new JLabel("Parabéns!");
        parabens.setFont(new Font("Arial", 0, 20));
        parabens.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        painel.add(parabens, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        
        // Colocando imagem
        
        ImageIcon iconeTrofeu = new ImageIcon("trofeu.jpg");
        Image image = iconeTrofeu.getImage(); // transform it 
        Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        iconeTrofeu = new ImageIcon(newimg);  // transform it back
        JLabel trofeu = new JLabel(iconeTrofeu);
        painel.add(trofeu, BorderLayout.CENTER);
        
        frame.setVisible(true);
        
    }
    
    public void printChaveamento() {
        if (this.tamanho == 8) {
            System.out.println("---- OITAVAS DE FINAL ----- \n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerda);
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            printAsa(asaDireita);
            System.out.println();
        }
        if (this.tamanho == 4) {
            System.out.println("\n---- QUARTAS DE FINAL -----\n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerda);
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            printAsa(asaDireita);
            System.out.println();
        }
        if (this.tamanho == 2) {
            System.out.println("\n---- SEMIFINAIS ----- \n");
            System.out.println("ASA ESQUERDA:");
            printAsa(asaEsquerda);
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            printAsa(asaDireita);
            System.out.println();
        }
        if (this.tamanho == 0) {
            System.out.println("\n---- GRANDE FINAL ----- \n");
            System.out.println("ASA ESQUERDA:");
            System.out.println(asaEsquerda.get(0).getVencedor().getNome());
            System.out.println("---------------------");
            System.out.println("ASA DIREITA:");
            System.out.println(asaDireita.get(0).getVencedor().getNome() + "\n");
        }
    }

    public void printListaJogadores() {
        for (Jogador j : listaJogadores) {
            System.out.println(j.getId() + " Jogador: " + j.getNome());
        }
    }

    public void printListaPartidas() {
        for (Partida p : listaPartidas) {
            System.out.println(p.getId() + " Partida: " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
        }
    }

    public void printAsa(List<Partida> aux) {
        for (Partida p : aux)
            System.out.println("Partida " + (p.getId() + 1) + ": " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
    }
    // ------------------ FIM-PRINTS ---------------------------------- //
}
