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

// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A

public class Tela extends JFrame {
    private boolean jogoComecou = false;
    private List<Usuario> listaVitorias = new ArrayList<Usuario>();
    private List<Usuario> listaAux;
    private boolean temAdm;
    JList<Usuario> listaUsuarios;
    private int lastIndex;
    private JTextField tfVitorias = new JTextField();

    public JTextField getTfVitorias() {
        return tfVitorias;

    }

    public int getLastIndex() {
        return lastIndex;
    }

    public boolean getJogoComecou() {
        return jogoComecou;
    }

    public List<Usuario> getListaAux() {
        return listaAux;
    }
    public JList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Usuario> getListaVitorias() {
        return listaVitorias;
    }

    public void setTfVitorias(JTextField tfVitorias) {
        this.tfVitorias = tfVitorias;
    }

    public void setListaUsuarios(JList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public void setListaAux(List<Usuario> listaAux) {
        this.listaAux = listaAux;
    }

    public void setJogoComecou(boolean jogoComecou) {
        this.jogoComecou = jogoComecou;
    }

    public void setListaVitorias(List<Usuario> listaVitorias) {
        this.listaVitorias = listaVitorias;
    }

    public void somaListas() {
        // concatenar listas
        listaVitorias = new ArrayList<Usuario>();
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
        this.lastIndex = 0;
        OpcoesLogin();
        if (this.temAdm) {
            recebeSenhaAdm();
        }
        telaMenu();
    }

    public void OpcoesLogin() {
        String[] opcoesUsuario = { "Jogador", "Administrador" };
        int opcaoUsuario = JOptionPane.showOptionDialog(null, "Selecione o tipo de usuário:", "Escolha de Usuário",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesUsuario, opcoesUsuario[1]);
        if (opcaoUsuario == 1)
            this.temAdm = true;
        else
            this.temAdm = false;
    }

    public void recebeSenhaAdm() {
        String senha = JOptionPane.showInputDialog("Insira a senha de administrador:");
        ;

        try {
            if (!senha.equals("Gleiph")) {
                throw new SenhaAdmInvalida();
            }
        } catch (SenhaAdmInvalida ex) {
            JOptionPane.showMessageDialog(null, "ERRO: Senha de administrador incorreta.", "Erro",
                    JOptionPane.WARNING_MESSAGE);
            recebeSenhaAdm();
        }
    }

    public void telaMenu() {
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
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel painel = new JPanel();

        JPanel jpUsuarios = new JPanel();
        jpUsuarios.setBorder(BorderFactory.createTitledBorder("Usuários"));
        jpUsuarios.setLayout(new BorderLayout());
        jpUsuarios.setPreferredSize(new Dimension(300, 355));

        DefaultListModel<Usuario> model = new DefaultListModel<>();
        listaUsuarios = new JList<>(model);
        listaUsuarios.setVisible(true);
        listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (Usuario usuario : listaVitorias) {
            model.addElement(usuario);
        }

        listaUsuarios.setModel(model);
        listaUsuarios.addListSelectionListener(new TratarLista(this));
        jpUsuarios.add(new JScrollPane(listaUsuarios), BorderLayout.CENTER);
        painel.add(jpUsuarios, BorderLayout.WEST);

        JPanel jpConfigura = new JPanel();
        jpConfigura.setBorder(BorderFactory.createTitledBorder("Configuração"));
        jpConfigura.setLayout(new FlowLayout());
        jpConfigura.setPreferredSize(new Dimension(100, 355));
        jpConfigura.add(new JLabel("Vitórias:"));
        tfVitorias = new JTextField(5);
        jpConfigura.add(tfVitorias);

        if(temAdm){
            JButton btnEditar = new JButton("Editar");
            btnEditar.setPreferredSize(new Dimension(90, 20));
            jpConfigura.add(btnEditar);
            btnEditar.addActionListener(new EditarLista(this, frame));
        }

        JButton jbPassar = new JButton("Voltar");
        frame.add(jbPassar, BorderLayout.PAGE_END);
        painel.add(jpConfigura, BorderLayout.EAST);
        frame.add(painel);
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
