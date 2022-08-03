package com.trabalho.view;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
/**
 *
 * @author gleiph
 */
public class TratarLista implements ListSelectionListener {

    Tela tela;

    public TratarLista(Tela tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int firstIndex = tela.getListaV().getSelectedIndex();

        if (firstIndex != -1) {
            int elementAt = tela.getListaV().getModel().getElementAt(firstIndex);
            tela.getTfVitorias().setText(String.valueOf(elementAt));
            tela.setLastIndex(firstIndex);
        }
    }

}
