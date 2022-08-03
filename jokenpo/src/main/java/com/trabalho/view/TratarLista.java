package com.trabalho.view;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.trabalho.Usuario;

public class TratarLista implements ListSelectionListener {

    Tela tela;

    public TratarLista(Tela tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int firstIndex = tela.getListaUsuarios().getSelectedIndex();

        if (firstIndex != -1) {
            Usuario elementAt = tela.getListaUsuarios().getModel().getElementAt(firstIndex);
            tela.getTfNome().setText(elementAt.getNome());
            tela.getTfVitorias().setText(String.valueOf(elementAt.getnVitorias()));
            tela.setLastIndex(firstIndex);
        }
    }

}
