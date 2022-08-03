package com.trabalho.view;

import javax.swing.JFrame;
import javax.swing.ListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.trabalho.Usuario;

public class EditarLista implements ActionListener{

    private Tela tela;
    private JFrame frame;

    public EditarLista(Tela tela, JFrame frame) {
        this.tela = tela;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ListModel<Usuario> model = this.tela.getListaUsuarios().getModel();
        Usuario usuario = model.getElementAt(tela.getLastIndex());
        int vitorias = Integer.parseInt(tela.getTfVitorias().getText());
        System.out.println("entrou vit:" + vitorias);
        usuario.setnVitorias(vitorias);
        frame.repaint();
    }

}
