package com.trabalho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A
import javax.swing.JButton;

import com.github.javafaker.Faker;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Campeonato {
    private int tamanho;
    private int nJogadores;
    private int indexPartidas;
    private boolean temUsuario;
    private int numPartidaMax;
    private int numPartidaAtual;
    private boolean proxChaveamento;

    private Partida partidaFinal;
    private List<Jogador> listaJogadores = new ArrayList<>();
    private List<Partida> listaPartidas = new ArrayList<>();
    private List<Jogador> listaJogadoresAux = new ArrayList<>();
    private List<Partida> asaEsquerda = new ArrayList<>();
    private List<Partida> asaDireita = new ArrayList<>();

    public Campeonato(int n) { // informa qual o tamanho do campeonato
        this.tamanho = n; // TODO: por try/catch depois
        String[] opcoesJogo = {"Simular", "Jogar"};
        int opcaoJogo = JOptionPane.showOptionDialog(null, "Selecione o modo de jogo:", "Modo de Jogo"
                ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesJogo, opcoesJogo[1]);
         if (opcaoJogo == 1)
            this.temUsuario = true;
         else
            this.temUsuario = false;
        this.proxChaveamento = true;
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
        Faker faker = new Faker();
        String nome = faker.pokemon().name(), aux;
        int i = 0, size = this.tamanho * 2;
        if (temUsuario == true) {
            size = size - 1;
            i = 1;

            nome = this.recebeNome();

            vetJogador[i] = new Usuario(nome);
            addJogador(vetJogador[i]);
        }

        for (; i < vetJogador.length; i++) {
            aux = faker.pokemon().name();
            if(!nome.equals(aux))
                nome = aux;
            else
                nome = faker.pokemon().name();

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
        this.proxChaveamento = false;
        if (this.tamanho == 8) {
            // oitavas
            for (int i = 0; i < 4; i++)
                asaEsquerda.add(this.listaPartidas.get(i));
            for (int i = 4; i < 8; i++)
                asaDireita.add(this.listaPartidas.get(i));
            telaChaveamento("Oitavas de final");

        } else if (this.tamanho == 4) {
            // quartas
            asaEsquerda.clear();
            asaDireita.clear();
            for (int i = 0; i < 2; i++)
                asaEsquerda.add(this.listaPartidas.get(i));
            for (int i = 2; i < 4; i++)
                asaDireita.add(this.listaPartidas.get(i));
            telaChaveamento("Quartas de final");

        } else if (this.tamanho == 2) {
            // semi
            asaEsquerda.clear();
            asaDireita.clear();
            asaEsquerda.add(this.listaPartidas.get(0));
            asaDireita.add(this.listaPartidas.get(1));
            telaChaveamento("Semi final");

        } else if (this.tamanho == 0) {
            partidaFinal = new Partida(asaEsquerda.get(0).getVencedor(), asaDireita.get(0).getVencedor());
            telaChaveamento("Final");
        }
        //printChaveamento();
    }

    public void resolveNivel() {
        if (this.tamanho == 8) {
            // OITAVAS
            this.numPartidaMax = 7;
            this.numPartidaAtual = 0;

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
            if(proxChaveamento)
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

    public String recebeNome()
    {
        String nome = JOptionPane.showInputDialog("Insira o nome do usuário:");
        try{

            if(nome.length() < 2)
            {
                throw new nomeUsuarioInvalido();
            }
        }
        catch(nomeUsuarioInvalido ex)
        {
            JOptionPane.showMessageDialog(null, "ERRO: O nome de usuário deve ter pelo menos 2 caracteres.", "Erro", JOptionPane.WARNING_MESSAGE);
            nome = recebeNome();
        }

        return nome;
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

    public void telaChaveamento(String fase) {
        JFrame frame = new JFrame("Chaveamento - " + fase);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        //titulo do painel
        JLabel titulo = new JLabel("Chaveamento - " + fase);
        titulo.setFont(new Font("Arial", 0, 30));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setBackground(Color.white);
        painel.add(titulo, BorderLayout.PAGE_START);


        //painel com as Asas
        JPanel painelAsas = new JPanel();
        GridLayout gridAsas = new GridLayout(1, 2);
        painelAsas.setLayout(gridAsas);
        painelAsas.setBackground(Color.white);

        JTextArea asaE = new JTextArea(1, 5);
        JTextArea asaD = new JTextArea(1, 5);
        asaE.setFont(new Font("Arial", 0, 25));
        asaE.setEditable(false);
        asaD.setFont(new Font("Arial", 0, 25));
        asaD.setEditable(false);
        asaE.setText("  ASA ESQUERDA");
        for (Partida p : asaEsquerda) {
            asaE.setText(asaE.getText() + "\n" + (p.getId() + 1) + ": " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
        }
        asaE.setMargin(new Insets(80, 80, 25, 25));
        painelAsas.add(asaE, BorderLayout.CENTER);
        asaD.setText("  ASA DIREITA");
        for (Partida p : asaDireita) {
            asaD.setText(asaD.getText() + "\n" + (p.getId() + 1) + ": " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
        }
        asaD.setMargin(new Insets(80, 25, 25, 80));
        painelAsas.add(asaD, BorderLayout.CENTER);
        painel.add(painelAsas, BorderLayout.CENTER);

        //painel com o botao
        JButton passar = new JButton("Próximo");
        painel.add(passar, BorderLayout.PAGE_END);
        frame.add(painel);
        frame.setVisible(true);
        passar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                proxChaveamento = true;
                resolveNivel();
            }
        });
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
