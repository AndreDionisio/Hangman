<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page isELIgnored="false"%>
<html>
<head>
<title>
Simple
</title>
</head>
<body>
<%

    if(request.getSession().getAttribute("now")==null)
    request.getRequestDispatcher("/game").forward(request, response);
    %>
    <jsp:useBean id="now" class="net.javaguides.mavenwebapp.Game" type="net.javaguides.mavenwebapp.Game" scope="session" />

    <jsp:setProperty property="*" name="now"/>
<form action="game" method="post">
   <p>Categoria:
           <input type="radio" name="categoria" value="0" ${sessionScope.now.categoria eq 'HUMANA' ? 'checked' :''}/> HUMANA
           <input type="radio" name="categoria" value="1" ${sessionScope.now.categoria eq 'EMPREENDORISMO' ? 'checked' :''}/> EMPREENDORISMO
           <input type="radio" name="categoria" value="2" ${sessionScope.now.categoria eq 'SOCIAL' ? 'checked' :''}/> SOCIAL</p>

       <p>Dificuldade:
           <input type="radio" name="dificuldade" value="0" ${sessionScope.now.categoria eq 'CASUAL' ? 'checked' :''}/> CASUAL
           <input type="radio" name="dificuldade" value="1" ${sessionScope.now.categoria eq 'REGULAR' ? 'checked' :''}/> REGULAR
           <input type="radio" name="dificuldade" value="2" ${sessionScope.now.categoria eq 'BRUTAL' ? 'checked' :''}/> BRUTAL</p>
   <p>${sessionScope.now.ui}</p>
    <p>Letra:
    <input type="text" name="letra" value="${sessionScope.now.letra}" maxlength="1" size="1" style="text-transform:uppercase" /></p>
    <p>
    <input type="submit" name="submit" value="submit" /></p>

    ${sessionScope.now.now}







</form>
</body>
</html>