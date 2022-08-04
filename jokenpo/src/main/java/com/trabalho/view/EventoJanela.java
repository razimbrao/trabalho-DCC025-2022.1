package com.trabalho.view;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.trabalho.util.*;

// Rafael de Oliveira Zimbrão - 202165124A
// Livia Ribeiro Pessamilio - 202165088A
// João Vitor Fernandes Ribeiro Carneiro Ramos - 202165076A

public class EventoJanela implements WindowListener {

    private Tela tela;

    public EventoJanela(Tela tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(java.awt.event.WindowEvent e) {
        try {
            String lerArquivo = Arquivo.lerArquivo("dados");
            tela.setListaVitorias(JSON.fromJSONArray(lerArquivo));
        } catch (FileNotFoundException ex) {
        }
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        //transforma listaVitorias em json e fechar arquivo
        try {
            String json = JSON.toJSON(tela.getListaVitorias());
            Arquivo.escreverArquivo("dados", json);
        } catch (IOException ex) {
        }
    }

    @Override
    public void windowClosed(java.awt.event.WindowEvent e) {
    }

    @Override
    public void windowIconified(java.awt.event.WindowEvent e) {
    }

    @Override
    public void windowDeiconified(java.awt.event.WindowEvent e) {
    }

    @Override
    public void windowActivated(java.awt.event.WindowEvent e) {
    }

    @Override
    public void windowDeactivated(java.awt.event.WindowEvent e) {
    }
}

