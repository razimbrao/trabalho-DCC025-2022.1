package com.trabalho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import com.trabalho.view.Tela;

import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.datafaker.Faker;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A

public class Campeonato {
    private int tamanho;
    private int nJogadores;
    private int indexPartidas;
    private boolean temUsuario;
    private boolean temAdm;
    private Partida partidaFinal;
    private Jogador campeao;
    private Tela tela;

    private List<Jogador> listaJogadores = new ArrayList<>();
    private List<Jogador> listaJogadoresAux = new ArrayList<>();
    private List<Usuario> listaVitorias = new ArrayList<>();
    private List<Partida> listaPartidas = new ArrayList<>();
    private List<Partida> asaEsquerda = new ArrayList<>();
    private List<Partida> asaDireita = new ArrayList<>();

    public Campeonato(int n, Tela tela) { // informa qual o tamanho do campeonato
        this.tamanho = n;
        String[] opcoesJogo = { "Simular", "Jogar" };
        int opcaoJogo = JOptionPane.showOptionDialog(null, "Selecione o modo de jogo:", "Modo de Jogo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesJogo, opcoesJogo[1]);
        if (opcaoJogo == 1){
            this.temUsuario = true;
            String[] opcoesUsuario = { "Jogador", "Administrador" };
            int opcaoUsuario = JOptionPane.showOptionDialog(null, "Selecione o tipo de usuário:", "Escolha de Usuário",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesUsuario, opcoesUsuario[1]);
            if(opcaoUsuario == 1)
                this.temAdm = true;
            else
                this.temAdm = false;
        }
        else
            this.temUsuario = false;
        this.tela = tela;
    }

    public List<Usuario> getListaVitorias() {
        ordenaListaVitorias();
        return listaVitorias;
    }

    public void addJogador(Jogador x) { // add o jogador na listaJogadores
        this.listaJogadores.add(x);
        this.nJogadores++;
        int id = this.nJogadores - 1;
        x.setId(id);
        //se Jogador for Usuario, adiciona na listaVitorias
        if (x instanceof Usuario) {
            this.listaVitorias.add((Usuario) x);
        }
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
            if(temAdm == true){
                vetJogador[i] = new Administrador(nome);
                String senha = this.recebeSenhaAdm();
                addJogador(vetJogador[i]);
            }
            else{
                vetJogador[i] = new Usuario(nome);
                addJogador(vetJogador[i]);
            }
        }

        for (; i < vetJogador.length; i++) {
            aux = faker.pokemon().name();
            if (!nome.equals(aux))
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

    public void ordenaListaVitorias(){
        Collections.sort(listaVitorias);
    }

    public void chaveamento() { // faz o chaveamento do campeonato
        if (this.tamanho == 8) {
            // oitavas
            for (int i = 0; i < 4; i++)
                asaEsquerda.add(this.listaPartidas.get(i));
            for (int i = 4; i < 8; i++)
                asaDireita.add(this.listaPartidas.get(i));
            try {
                telaChaveamento("Oitavas de final");
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Campeonato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (this.tamanho == 4) {
            // quartas
            asaEsquerda.clear();
            asaDireita.clear();
            for (int i = 0; i < 2; i++)
                asaEsquerda.add(this.listaPartidas.get(i));
            for (int i = 2; i < 4; i++)
                asaDireita.add(this.listaPartidas.get(i));
            try {
                telaChaveamento("Quartas de final");
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Campeonato.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (this.tamanho == 2) {
            // semi
            asaEsquerda.clear();
            asaDireita.clear();
            asaEsquerda.add(this.listaPartidas.get(0));
            asaDireita.add(this.listaPartidas.get(1));
            try {
                telaChaveamento("Semi-final");
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Campeonato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (this.tamanho == 0) {
            partidaFinal = new Partida(asaEsquerda.get(0).getVencedor(), asaDireita.get(0).getVencedor());
            try {
                telaFinal();
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Campeonato.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        resolveNivel();
    }

    public Jogador resolvePartida(Partida partida) {
        Jogador vencedor = partida.simulador();
        return vencedor;
    }

    public void resolveNivel() {
        switch (this.tamanho) {
            case 8:
                Jogador vetO[] = new Jogador[tamanho];

                vetO[0] = resolvePartida(asaEsquerda.get(0));
                vetO[1] = resolvePartida(asaEsquerda.get(1));
                vetO[2] = resolvePartida(asaEsquerda.get(2));
                vetO[3] = resolvePartida(asaEsquerda.get(3));
                vetO[4] = resolvePartida(asaDireita.get(0));
                vetO[5] = resolvePartida(asaDireita.get(1));
                vetO[6] = resolvePartida(asaDireita.get(2));
                vetO[7] = resolvePartida(asaDireita.get(3));
                for (Jogador jogador : vetO) {
                    listaJogadoresAux.add(jogador);
                }

                listaJogadores.clear();
                listaPartidas.clear();
                listaJogadores = new ArrayList<>(listaJogadoresAux);
                listaJogadoresAux.clear();
                inserePartidas();

                this.tamanho = 4;
                chaveamento();
                break;
            case 4:
                Jogador vetQ[] = new Jogador[tamanho];
                vetQ[0] = resolvePartida(asaEsquerda.get(0));
                vetQ[1] = resolvePartida(asaEsquerda.get(1));
                vetQ[2] = resolvePartida(asaDireita.get(0));
                vetQ[3] = resolvePartida(asaDireita.get(1));

                for (Jogador jogador : vetQ) {
                    listaJogadoresAux.add(jogador);
                }

                listaJogadores.clear();
                listaPartidas.clear();
                listaJogadores = new ArrayList<>(listaJogadoresAux);
                inserePartidas();

                this.tamanho = 2;
                chaveamento();
                break;
            case 2:
                Jogador v1 = resolvePartida(asaEsquerda.get(0));
                listaJogadoresAux.add(v1);
                Jogador v2 = resolvePartida(asaDireita.get(0));
                listaJogadoresAux.add(v2);
                this.tamanho = 0;
                chaveamento();
                break;
            case 0:
                Jogador campeao = resolvePartida(partidaFinal);
                if(temAdm == true)
                    this.configuraTelaVitoriasAdm(campeao);
                else
                    this.imprimeTelaVitorias();
                break;
            default:
                break;
        }
    }

    public String recebeNome() {
        String nome;
        if(this.temAdm == true)
            nome = JOptionPane.showInputDialog("Insira o nome do administrador:");
        else
            nome = JOptionPane.showInputDialog("Insira o nome do jogador:");

        try {

            if (nome.length() < 2) {
                throw new NomeUsuarioInvalido();
            }
        } catch (NomeUsuarioInvalido ex) {
            JOptionPane.showMessageDialog(null, "ERRO: O nome de usuário deve ter pelo menos 2 caracteres.", "Erro",
                    JOptionPane.WARNING_MESSAGE);
            nome = recebeNome();
        }

        return nome;
    }

    public String recebeSenhaAdm(){
        String senha = JOptionPane.showInputDialog("Insira a senha de administrador:");;

        try{
            if(!senha.equals("Gleiph")){
                throw new SenhaAdmInvalida();
            }
        } catch (SenhaAdmInvalida ex){
            JOptionPane.showMessageDialog(null, "ERRO: Senha de administrador incorreta.", "Erro",
                    JOptionPane.WARNING_MESSAGE);
            senha = recebeSenhaAdm();
        }

        return senha;
    }

    // ------------------ TELAS ---------------------------------- //

    public void mensagemVencedorFinal() {
        JFrame frame = new JFrame("Vencedor");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBackground(Color.white);
        frame.getContentPane().add(painel);

        // tentar botar texto

        JLabel jlabel = new JLabel(campeao.getNome() + " venceu o campeonato!");
        jlabel.setFont(new Font("Arial", 0, 30));
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
        Image newimg = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        iconeTrofeu = new ImageIcon(newimg); // transform it back
        JLabel trofeu = new JLabel(iconeTrofeu);
        painel.add(trofeu, BorderLayout.CENTER);

        frame.setVisible(true);

        JButton sair = new JButton("Sair");
        painel.add(sair, BorderLayout.PAGE_END);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                tela.setVisible(true);
            }
        });
    }

    public void telaChaveamento(String fase) {
        JFrame frame = new JFrame("Chaveamento - " + fase);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        // titulo do painel
        JLabel titulo = new JLabel("Chaveamento - " + fase);
        titulo.setFont(new Font("Arial", 0, 30));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setBackground(Color.white);
        painel.add(titulo, BorderLayout.PAGE_START);

        // painel com as Asas
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
        asaE.setText("ASA ESQUERDA");
        for (Partida p : asaEsquerda) {
            asaE.setText(
                    asaE.getText() + "\n" + (p.getId() + 1) + ": " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
        }
        asaE.setMargin(new Insets(80, 80, 25, 25));
        painelAsas.add(asaE, BorderLayout.CENTER);
        asaD.setText("ASA DIREITA");
        for (Partida p : asaDireita) {
            asaD.setText(
                    asaD.getText() + "\n" + (p.getId() + 1) + ": " + p.getJ1().getNome() + " x " + p.getJ2().getNome());
        }
        asaD.setMargin(new Insets(80, 25, 25, 80));
        painelAsas.add(asaD, BorderLayout.CENTER);
        painel.add(painelAsas, BorderLayout.CENTER);
        frame.add(painel);
        frame.setVisible(true);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void imprimeTelaVitorias(){
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
        for(Jogador jogador : listaVitorias){
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
        for(Jogador jogador : listaVitorias){
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
                frame.setVisible(false);
                mensagemVencedorFinal();
            }
        });
    }



    public void configuraTelaVitoriasAdm(Jogador campeao){
        ordenaListaVitorias();
        JFrame frame = new JFrame("Vitórias");
        frame.setSize(500, 380); //500, 380
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel jpJogadores = new JPanel();
        jpJogadores.setBorder(BorderFactory.createTitledBorder("Jogadores"));
        jpJogadores.setLayout(new BorderLayout());
        jpJogadores.setPreferredSize(new Dimension(300, 355));

        JList<String> listaJ;
        DefaultListModel<String> model = new DefaultListModel<>();
        for(Jogador jogador : listaVitorias){
            model.addElement(jogador.getNome());
        }
        listaJ = new JList<>(model);
        listaJ.setVisible(true);
        jpJogadores.add(new JScrollPane(listaJ), BorderLayout.CENTER);


        JPanel jpVitorias = new JPanel();
        jpVitorias.setBorder(BorderFactory.createTitledBorder("Vitórias"));
        jpVitorias.setLayout(new BorderLayout());
        jpVitorias.setPreferredSize(new Dimension(75, 355)); //185, 355

        JList<Integer> listaV;
        DefaultListModel<Integer> model2 = new DefaultListModel<>();
        for(Jogador jogador : listaVitorias){
            model2.addElement(jogador.getnVitorias());
        }
        listaV = new JList<>(model2);
        listaV.setVisible(true);
        jpVitorias.add(new JScrollPane(listaV), BorderLayout.CENTER);


        JPanel jpConfigura = new JPanel();
        jpConfigura.setBorder(BorderFactory.createTitledBorder("Configuração"));
        jpConfigura.setLayout(new FlowLayout());
        jpConfigura.setPreferredSize(new Dimension(100, 355));
        jpConfigura.add(new JLabel("Vitórias:"));
        JTextField tfVitorias = new JTextField(5);
        jpConfigura.add(tfVitorias);
        JButton btnEditar = new JButton("Editar");
        btnEditar.setPreferredSize(new Dimension(90, 20));
        jpConfigura.add(btnEditar);

/*         btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListModel<Integer> model;
                model = listaV.getModel();
                Jogador jogador = model.getElementAt(jpVitorias.getLastIndex());
                jogador.setnVitorias(frame.tfVitorias.getText());

                frame.repaint();
            }
        }); */

        JButton jbPassar = new JButton("Próximo");
        jbPassar.setPreferredSize(new Dimension(500, 25));

        frame.add(jpJogadores, BorderLayout.WEST);
        frame.add(jpVitorias, BorderLayout.CENTER);
        frame.add(jpConfigura, BorderLayout.EAST);
        frame.add(jbPassar, BorderLayout.SOUTH);
        frame.setVisible(true);
        jbPassar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                mensagemVencedorFinal();
            }
        });
    }

    public void telaFinal() {
        JFrame frame = new JFrame("Chaveamento - FINAL");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        // titulo do painel
        JLabel titulo = new JLabel("Chaveamento - final");
        titulo.setFont(new Font("Arial", 0, 30));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setBackground(Color.white);
        painel.add(titulo, BorderLayout.PAGE_START);

        // painel com as Asas
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
        asaE.setText("ASA ESQUERDA \n"+ asaEsquerda.get(0).getVencedor().getNome());
        asaE.setMargin(new Insets(80, 160, 25, 25));
        painelAsas.add(asaE, BorderLayout.CENTER);
        asaD.setText("ASA DIREITA \n" + asaDireita.get(0).getVencedor().getNome());
        asaD.setMargin(new Insets(80, 60, 25, 80));
        painelAsas.add(asaD, BorderLayout.CENTER);
        painel.add(painelAsas, BorderLayout.CENTER);
        frame.add(painel);
        frame.setVisible(true);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();

    }

    // ------------------ FIM-TELAS ---------------------------------- //
}
