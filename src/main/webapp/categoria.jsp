<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page isELIgnored="false"%>
<html>
<head>
<title>
Categoria
</title>
</head>
<body>
<form action="categoria" method="post">
    <p>${sessionScope.now.ui}</p>

    <p>Categoria:
        <input type="radio" name="categoria" value="0" ${sessionScope.now.categoria eq 'HUMANA' ? 'checked' :''}/> HUMANA
        <input type="radio" name="categoria" value="1" ${sessionScope.now.categoria eq 'EMPREENDORISMO' ? 'checked' :''}/> EMPREENDORISMO
        <input type="radio" name="categoria" value="2" ${sessionScope.now.categoria eq 'SOCIAL' ? 'checked' :''}/> SOCIAL</p>

    <p>Dificuldade:
        <input type="radio" name="dificuldade" value="0" ${sessionScope.now.categoria eq 'CASUAL' ? 'checked' :''}/> CASUAL
        <input type="radio" name="dificuldade" value="1" ${sessionScope.now.categoria eq 'REGULAR' ? 'checked' :''}/> REGULAR
        <input type="radio" name="dificuldade" value="2" ${sessionScope.now.categoria eq 'BRUTAL' ? 'checked' :''}/> BRUTAL</p>


    <input type="submit" name="submit" value="submit" /></p>









</form>
</body>
</html>