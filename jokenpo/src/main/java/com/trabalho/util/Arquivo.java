package com.trabalho.util;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;



public class Arquivo {
    public static String lerArquivo(String caminho) throws FileNotFoundException{
        System.out.println("Lendo arquivo...");
        StringBuilder conteudo = new StringBuilder();
        File arquivo = new File(caminho);
        Scanner leitor = new Scanner(arquivo);
        while(leitor.hasNextLine()){
            conteudo.append(leitor.nextLine()).append("\n");
        }
        System.out.println("escrito: " + conteudo.toString());
        return conteudo.toString();
    }

    public static void escreverArquivo(String caminho, String conteudo) throws IOException{

        try {
            System.out.println("Salvando arquivo...");
            File arquivo = new File(caminho);
            FileWriter fwArquivo = new FileWriter(arquivo, false);
            BufferedWriter bwArquivo = new BufferedWriter(fwArquivo);
            System.out.println("escrevendo: " + conteudo);
            bwArquivo.write(conteudo + "\n");
            //fwArquivo.close();
            bwArquivo.close();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
    }
}
