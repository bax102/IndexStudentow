<%@ page import="ps.sda.javagdy2.database.EntityDao" %>
<%@ page import="ps.sda.javagdy2.database.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: akohsin
  Date: 19.04.2020
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--dzięki poniższej linii możliwe jest wywoływanie funkcji/tagów html z jstl--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--dzięki lini poniżej możliwe jest wykonywanie dyrektyw z użyciem zmiennych adresując je przez ${}--%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Student list</title>
</head>
<body>
<h1>Student list</h1>
<jsp:include page="/menu.jsp"/>

<table class="border">
    <thead>
    <tr>
        <td>Id</td>
        <td>Imie</td>
        <td>Nazwisko</td>
        <td>Pełnoletni</td>
        <td>Wzrost</td>
    </tr>
    </thead>
    <tbody> <%-- JSTL --%>
    <c:forEach var="student" items="${requestScope.students}">
        <tr>
            <td class="border"><c:out value="${student.id}"/></td>
            <td><c:out value="${student.imie}"/></td>
            <td><c:out value="${student.nazwisko}"/></td>
            <td><c:out value="${student.pelnoletni}"/></td>
            <td><c:out value="${student.wzrost}"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/student/remove?identToRemove=<c:out value="${student.id}"/>">Usun</a>
            </td>

                <%--NOWA TRESC--%>
            <td>
                <a href="${pageContext.request.contextPath}/student/detail?identifier=<c:out value="${student.id}"/>">Szczegoly</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <%--    <%--%>
    <%--        for (Student student : list) {--%>
    <%--            out.print("<tr>");--%>
    <%--            out.print("<td style=\"border: 1px solid\">" + student.getId() + "</td>");--%>
    <%--            out.print("<td style=\"border: 1px solid\">" + student.getImie() + "</td>");--%>
    <%--            out.print("<td style=\"border: 1px solid\">" + student.getNazwisko() + "</td>");--%>
    <%--            out.print("<td style=\"border: 1px solid\">" + student.isPelnoletni() + "</td>");--%>
    <%--            out.print("<td style=\"border: 1px solid\">" + student.getWzrost() + "</td>");--%>
    <%--            out.print("</tr>");--%>
    <%--        }--%>
    <%--    %>--%>
</table>

</body>
</html>
