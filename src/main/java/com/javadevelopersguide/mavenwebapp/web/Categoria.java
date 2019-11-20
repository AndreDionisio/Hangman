package com.javadevelopersguide.mavenwebapp.web;
import net.javaguides.mavenwebapp.Game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Categoria extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        any(req, resp);
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        any(req, resp);
    }
    private void any(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        String categoria = req.getParameter("categoria");
        String dificuldade = req.getParameter("dificuldade");
        req.getSession().setAttribute("categoria",categoria);
        String [] dificuldades = {"CASUAL","REGULAR","BRUTAL"};
        String [] categorias = {"HUMANA","EMPREENDORISMO","SOCIAL"};
        Game now = new Game();

        try {
            now.setDificuldade(dificuldades[Integer.valueOf(dificuldade)]);
        }catch (Exception e){
            Random r = new Random();
            now.setDificuldade(dificuldades[r.nextInt(dificuldades.length)]);
        }
        try{
            now.setCategoria(categorias[Integer.valueOf(categoria)]);
        }catch (Exception e){
            Random r = new Random();
            now.setDificuldade(categorias[r.nextInt(categorias.length)]);
        }
        req.getSession().setAttribute("now",now);
        req.getRequestDispatcher("game.jsp").forward(req, resp);

    }



        public void doSome(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
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

}