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
        ListModel<Integer> model = this.tela.getListaV().getModel();
        int vitorias = model.getElementAt(tela.getLastIndex());
        tela.getTfVitorias().setText(String.valueOf(vitorias));
        tela.getListaVitorias().get(0).setnVitorias(vitorias);
        frame.repaint();
        //tela.repaint();
//        tela.getLista().setModel(model);

    }

}
