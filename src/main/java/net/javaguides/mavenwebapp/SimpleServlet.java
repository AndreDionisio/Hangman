package net.javaguides.mavenwebapp;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Servlet implementation class SimpleServlet
 */
//@WebServlet("/hello")
public class SimpleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAgain(req,resp);
    }
    protected void doAgain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game now = (Game)req.getSession().getAttribute("now");
        if(now==null)
            doIt(req,resp);
        String letra = (String)req.getParameter("letra");
        now.setLetra(letra);


        now = validate(now);
        now.setUi("");
        now = gui(now);

        resp.setContentType("text/html;charset=UTF-8");

        req.getSession().setAttribute("now",now);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    private Game validate(Game game) {
        game.setAcertos(validation(game.getPalavra(),game.getAcertos(),game.getLetra(),game.getTela()));
        return game;
    }
    private char[] validation(String palavra, char[] acertos, String letra, String tela) {
        for (int i = 0; i < acertos.length; i++) { //para cada uma das posi��es (posicao � representada pela letra i)
           // if()
            //acertos[i] = 0;

            if (letra != null && !letra.isEmpty() && palavra.charAt(i) == letra.toUpperCase().charAt(0))
                acertos[i] = letra.charAt(0);
            if (tela != null && palavra.charAt(i) == tela.charAt(i))
                acertos[i] = tela.charAt(i);

        }
        return acertos;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doIt(req, resp);
}

    protected void doIt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game now = new Game();
        StringBuffer msg = new StringBuffer();
        String letra = req.getParameter("letra");

        now.setLetra(letra);
        resp.setContentType("text/plain");
        //resp.getWriter().write("Hello World! Maven Web Project Example."+letra);
        String [] palavra = {"ACOMPANHAMENTO","PROXIMIDADE","AUTONOMIA","DINAMISMO","CRIATIVIDADE","FLEXIBILIDADE","ESTABILIDADE","ENVOLVIMENTO"};
        String [] dificuldade = {"CASUAL","REGULAR","BRUTAL"};
        String [] categoria = {"HUMANA","EMPREENDORISMO","SOCIAL"};

        String [] categoriaPalavra = {"0|0","0|1","1|2","1|3","1,4","1|5","2|6","2|7"};
        //CASUAL primeira e a ultima letra aparece, regular aparece somente a primeira letra, brutal não exibe nenhuma letra.

        Random r = new Random();
        r.nextInt(categoriaPalavra.length);
        PrintWriter out = resp.getWriter();
        msg.append("Jogo da Forca\n");
        msg.append("Sobre a Categoria: em que as palavras estão relacionadas são \"HUMANA\",\"EMPREENDORISMO\" e \"SOCIAL\"\n");


        msg.append("Sobre a Dificuldade: CASUAL primeira e a ultima letra aparece, REGULAR aparece somente a primeira letra, BRUTAL não exibe nenhuma letra.\n");

        int adivinhe = r.nextInt(categoriaPalavra.length);
        int nivel = r.nextInt(dificuldade.length);
        int categoriaSorteada =  Integer.valueOf(categoriaPalavra[adivinhe].substring(0,1));
        int palavraSorteada = Integer.valueOf(categoriaPalavra[adivinhe].substring(2));
        now.setCategoria(categoria[categoriaSorteada]);
        now.setDificuldade(dificuldade[nivel]);
        now.setPalavra(palavra[palavraSorteada]);
        //msg.append("A categoria é " + now.getCategoria()+"\n");
        //msg.append("A dificuldade é " + now.getDificuldade()+"\n");

        String tela = "";
        if(nivel ==2)
            tela =  now.getPalavra().replaceAll("[^w]","0");
        if(nivel ==1)
            tela =  now.getPalavra().substring(0,1)+now.getPalavra().replaceAll("[^w]","0").substring(1);
        if(nivel ==0)
            tela =  now.getPalavra().substring(0,1)+now.getPalavra().replaceAll("[^w]","0").substring(1,now.getPalavra().length()-1)+now.getPalavra().substring(now.getPalavra().length()-1,now.getPalavra().length());
        now.setTela(tela);

        char[] acertos = tela.toCharArray();//new char[tela.length()];
        now.setAcertos(acertos);
        now = validate(now);
        now.setUi("");
        now = gui(now);

        now.setNow(msg.toString());
        //out.println(msg.toString());
        resp.setContentType("text/html;charset=UTF-8");


        req.getSession().setAttribute("now",now);


        req.getRequestDispatcher("index.jsp").forward(req, resp);
        out.flush();
        out.close();
    }
    private Game gui(Game game) {
        game.setUi(ui(game.getAcertos(),new StringBuffer(game.getUi()),game.getPalavra()).toString());
        return game;
    }
    private StringBuffer ui(char[] acertos, StringBuffer ui, String palavra) {
        for (int i = 0; i < palavra.length(); i++) {

            if (acertos[i] == '0') {
                ui.append(" _ ");

            } else {
                ui.append(" " + palavra.charAt(i) + " ");
            }
        }
        if(!ui.toString().contains("_"))
            ui.append("a palavra está correta. Parabéns."+"\n");
        return ui;
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