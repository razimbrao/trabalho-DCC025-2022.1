package com.trabalho.view;

import com.trabalho.*;
import com.trabalho.util.Arquivo;
import com.trabalho.util.JSON;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela extends JFrame {
    private boolean jogoComecou = false;
    private List<Jogador> listaVitorias = new ArrayList<>();
    private List<Jogador> listaAux;

    public boolean getJogoComecou() {
        return jogoComecou;
    }

    public void setListaAux(List<Jogador> listaAux) {
        this.listaAux = listaAux;
    }

    public void setJogoComecou(boolean jogoComecou) {
        this.jogoComecou = jogoComecou;
    }

    public List<Jogador> getListaVitorias() {
        return listaVitorias;
    }

    public void setListaVitorias(List<Jogador> listaVitorias) {
        this.listaVitorias = listaVitorias;
    }

    public void somaListas() {
        // concatenar listas
        int i = 0;
        if(listaVitorias!=null)
            for (Jogador jAux : listaAux) {
                if (jAux.getNome().equals(listaVitorias.get(i).getNome())) {
                    listaVitorias.get(i).setnVitorias(listaVitorias.get(i).getnVitorias() + jAux.getnVitorias());
                } else {
                    listaVitorias.add(jAux);
                }
                i++;
            }
        else
            listaVitorias = listaAux;
        // print listaVitorias
        for (int k = 0; k < listaVitorias.size(); k++) {
            System.out.println(listaVitorias.get(k).getNome() + ": " + listaVitorias.get(k).getnVitorias());
        }
    }

    public Tela() {
        setTitle("Jokenpo");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // titulo
        JLabel titulo = new JLabel("Jokenpo");
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBackground(Color.white);
        add(titulo, BorderLayout.NORTH);

        // painel de opcoes
        GridLayout grid = new GridLayout(4, 1);
        JPanel painel = new JPanel();
        grid.setVgap(10);
        painel.setLayout(grid);
        JButton btnJogar = new JButton("Jogar");
        JButton btnVitorias = new JButton("Lista de Vitorias");
        JButton btnSair = new JButton("Sair");

        // adicionando os botoes ao painel
        painel.add(btnJogar);
        painel.add(btnVitorias);
        painel.add(btnSair);
        painel.setBackground(Color.white);
        painel.setBorder(new EmptyBorder(80, 200, 80, 200));
        add(painel, BorderLayout.CENTER);
        setVisible(true);

        //leitura de arquivos
        this.addWindowListener(new EventoJanela(this));

        // eventos dos botoes
        btnJogar.addActionListener(e -> {
            this.jogoComecou = true;
            setVisible(false);
        });

        btnVitorias.addActionListener(e -> {
            setVisible(false);
            imprimeTelaVitorias();
        });

        btnSair.addActionListener(e -> {
            try {
                String json = JSON.toJSON(getListaVitorias());
                Arquivo.escreverArquivo("dados", json);
            } catch (IOException ex) {
            }
            System.exit(0);
        });
    }

    public void imprimeTelaVitorias() {
        JFrame frame = new JFrame("Vitórias");
        frame.setSize(500, 380);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel jpJogadores = new JPanel();
        jpJogadores.setBorder(BorderFactory.createTitledBorder("Jogadores"));
        jpJogadores.setLayout(new BorderLayout());
        jpJogadores.setPreferredSize(new Dimension(300, 355));

        JList<String> listaJ;
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Jogador jogador : listaVitorias) {
            model.addElement(jogador.getNome());
        }
        listaJ = new JList<>(model);
        listaJ.setVisible(true);
        jpJogadores.add(new JScrollPane(listaJ), BorderLayout.CENTER);

        JPanel jpVitorias = new JPanel();
        jpVitorias.setBorder(BorderFactory.createTitledBorder("Vitórias"));
        jpVitorias.setLayout(new BorderLayout());
        jpVitorias.setPreferredSize(new Dimension(185, 355));

        JList<Integer> listaV;
        DefaultListModel<Integer> model2 = new DefaultListModel<>();
        for (Jogador jogador : listaVitorias) {
            model2.addElement(jogador.getnVitorias());
        }
        listaV = new JList<>(model2);
        listaV.setVisible(true);
        jpVitorias.add(new JScrollPane(listaV), BorderLayout.CENTER);

        JButton jbPassar = new JButton("Próximo");

        frame.add(jpJogadores, BorderLayout.WEST);
        frame.add(jpVitorias, BorderLayout.EAST);
        frame.add(jbPassar, BorderLayout.PAGE_END);
        frame.setVisible(true);
        jbPassar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                setVisible(true);
            }
        });
    }
}
