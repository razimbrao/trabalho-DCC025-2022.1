package com.trabalho.view;

import com.trabalho.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class Tela extends JFrame {
    private boolean jogoComecou = false;

    public boolean getJogoComecou() {
        return jogoComecou;
    }

    public Tela() {
        setTitle("Jokenpo");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //setResizable(false);

        //titulo
        JLabel titulo = new JLabel("Jokenpo");
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBackground(Color.white);
        add(titulo, BorderLayout.NORTH);

        //painel de opcoes
        GridLayout grid = new GridLayout(4, 1);
        JPanel painel = new JPanel();
        grid.setVgap(10);
        painel.setLayout(grid);
        JButton btnJogar = new JButton("Jogar");
        JButton btnVitorias = new JButton("Lista de Vitorias");
        JButton btnInfo = new JButton("Informações");
        JButton btnSair = new JButton("Sair");

        //adicionando os botoes ao painel
        painel.add(btnJogar);
        painel.add(btnVitorias);
        painel.add(btnInfo);
        painel.add(btnSair);
        painel.setBackground(Color.white);
        painel.setBorder(new EmptyBorder(80, 200, 80, 200));
        add(painel, BorderLayout.CENTER);
        setVisible(true);

        //eventos dos botoes
        btnJogar.addActionListener(e -> {
            this.jogoComecou = true;
            dispose();
        });

        btnVitorias.addActionListener(e -> {
            dispose();

        });
    }
}


