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
    request.getRequestDispatcher("/complex").forward(request, response);
    %>
    <jsp:useBean id="now" class="net.javaguides.mavenwebapp.Game" type="net.javaguides.mavenwebapp.Game" scope="session" />

    <jsp:setProperty property="*" name="now"/>
<form action="complex" method="post">
    <p>Dificuldade:${sessionScope.now.dificuldade}</p>
    <p>Categoria:${sessionScope.now.categoria}</p>
    <p>${sessionScope.now.ui}</p>
    <p>Letra:
    <input type="text" name="letra" value="${sessionScope.now.letra}" maxlength="1" size="1" style="text-transform:uppercase" /></p>
    <p>
    <input type="submit" name="submit" value="submit" /></p>

    ${sessionScope.now.now}







</form>
</body>
</html>