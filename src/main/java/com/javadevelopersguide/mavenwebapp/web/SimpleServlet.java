package com.javadevelopersguide.mavenwebapp.web;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String [] palavra = {"ACOMPANHAMENTO","PROXIMIDADE","AUTONOMIA","DINAMISMO","CRIATIVIDADE","FLEXIBILIDADE","ESTABILIDADE","ENVOLVIMENTO"};

        String [] categoria = {"HUMANA","EMPREENDORISMO","SOCIAL"};

        String [] categoriaPalavra = {"0|0","0|1","1|2","1|3","1,4","1|5","2|6","2|7"};
        //CASUAL primeira e a ultima letra aparece, regular aparece somente a primeira letra, brutal não exibe nenhuma letra.
        String [] dificuldade = {"CASUAL","REGULAR","BRUTAL"};
        Random r = new Random();
        r.nextInt(categoriaPalavra.length);
        PrintWriter out = response.getWriter();
        out.println("Jogo da Forca");
        out.println("Sobre a Categoria: em que as palavras estão relacionadas são \"HUMANA\",\"EMPREENDORISMO\" e \"SOCIAL\"");


        out.println("Sobre a Dificuldade: CASUAL primeira e a ultima letra aparece, regular aparece somente a primeira letra, brutal não exibe nenhuma letra.");
        int adivinhe = r.nextInt(categoriaPalavra.length);
        int nivel = r.nextInt(dificuldade.length);
        int categoriaSorteada =  Integer.valueOf(categoriaPalavra[adivinhe].substring(0,1));
        int palavraSorteada = Integer.valueOf(categoriaPalavra[adivinhe].substring(2));
        out.println("A categoria é " + categoria[categoriaSorteada]);
        out.println("A dificuldade é " + dificuldade[nivel]);
        String[] regex = {"^[^w]|[^w]$",
        "[^w]$",
        "[^w]"};

        if(nivel ==2)
            out.println("A palavra é " + palavra[palavraSorteada].replaceAll("[^w]","_"));
        if(nivel ==1)
            out.println("A palavra é " + palavra[palavraSorteada].substring(0,1)+palavra[palavraSorteada].replaceAll("[^w]","_").substring(1));
        if(nivel ==0)
            out.println("A palavra é " + palavra[palavraSorteada].substring(0,1)+palavra[palavraSorteada].replaceAll("[^w]","_").substring(1,palavra[palavraSorteada].length()-1)+palavra[palavraSorteada].substring(palavra[palavraSorteada].length()-1,palavra[palavraSorteada].length()));
        out.println("A palavra é " + palavra[palavraSorteada]);
        out.flush();
        out.close();
    }
    private String noPersonDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        \n" +
                "|       \n"+
                "|        \n" +
                "|       \n" +
                "|\n" +
                "|\n";
    }

    private String addHeadDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|       \n"+
                "|        \n" +
                "|       \n" +
                "|\n" +
                "|\n";
    }

    private String addBodyDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|        | \n"+
                "|        |\n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String addOneArmDraw() {
        return   " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / |  \n"+
                "|        |\n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String addSecondArmDraw() {
        return  " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|        |\n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String addFirstLegDraw() {
        return   " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|        |\n" +
                "|       / \n" +
                "|\n" +
                "|\n";
    }

    private String fullPersonDraw() {
        return   " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|        |\n" +
                "|       / \\ \n" +
                "|\n" +
                "|\n";
    }
}