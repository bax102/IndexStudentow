<%--
  Created by IntelliJ IDEA.
  User: piotr
  Date: 19.04.2020
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>Formularz studenta</title>
</head>
<body>
<h1>Student form</h1>
<jsp:include page="/menu.jsp"/>

<br/>
<form action="${pageContext.request.contextPath}/student/add" method="post">
    Imie:       <input type="text" name="imie"/>
    <br/>
    Nazwisko:   <input type="text" name="nazwisko"/>
    <br/>
    Pełnoletni: <input type="checkbox" name="pelnoletni"/>
    <br/>
    Wzrost:     <input type="number" step="0.01" name="wzrost"/> <%--zmiennoprzecinkowe dzięki step--%>
    <br/>
    <input type="submit">
</form>

</body>
</html>