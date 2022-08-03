package com.trabalho.view;

import com.trabalho.*;
import com.trabalho.util.Arquivo;
import com.trabalho.util.JSON;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela extends JFrame {
    private boolean jogoComecou = false;
    private List<Usuario> listaVitorias = new ArrayList<Usuario>();
    private List<Usuario> listaAux;

    public boolean getJogoComecou() {
        return jogoComecou;
    }

    public void setListaAux(List<Usuario> listaAux) {
        this.listaAux = listaAux;
    }

    public List<Usuario> getListaAux() {
        return listaAux;
    }

    public void setJogoComecou(boolean jogoComecou) {
        this.jogoComecou = jogoComecou;
    }

    public List<Usuario> getListaVitorias() {
        return listaVitorias;
    }

    public void setListaVitorias(List<Usuario> listaVitorias) {
        this.listaVitorias = listaVitorias;
    }

    public void somaListas() {
        // concatenar listas
        listaVitorias = new ArrayList<Usuario>();
        System.out.println("entrou");
        for (Usuario usuario : listaVitorias) {
            if (usuario.getNome().equals(listaAux.get(0).getNome())) {
                usuario.setnVitorias(usuario.getnVitorias() + listaAux.get(0).getnVitorias());
            } else {
                listaVitorias.add(listaAux.get(0));
                break;
            }
        }

        for (Usuario usuario : listaAux) {
            listaVitorias.add(usuario);
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

        // leitura de arquivos
        this.addWindowListener(new EventoJanela(this));
        // print listaVitorias

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

        JPanel jpUsuarios = new JPanel();
        jpUsuarios.setBorder(BorderFactory.createTitledBorder("Usuarios"));
        jpUsuarios.setLayout(new BorderLayout());
        jpUsuarios.setPreferredSize(new Dimension(300, 355));

        JList<String> listaJ;
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Usuario usuario : listaVitorias) {
            model.addElement(usuario.getNome());
        }
        listaJ = new JList<>(model);
        listaJ.setVisible(true);
        jpUsuarios.add(new JScrollPane(listaJ), BorderLayout.CENTER);

        JPanel jpVitorias = new JPanel();
        jpVitorias.setBorder(BorderFactory.createTitledBorder("Vitórias"));
        jpVitorias.setLayout(new BorderLayout());
        jpVitorias.setPreferredSize(new Dimension(185, 355));

        JList<Integer> listaV;
        DefaultListModel<Integer> model2 = new DefaultListModel<>();
        for (Usuario usuario : listaVitorias) {
            model2.addElement(usuario.getnVitorias());
        }
        listaV = new JList<>(model2);
        listaV.setVisible(true);
        jpVitorias.add(new JScrollPane(listaV), BorderLayout.CENTER);

        JButton jbPassar = new JButton("Voltar");

        frame.add(jpUsuarios, BorderLayout.WEST);
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
