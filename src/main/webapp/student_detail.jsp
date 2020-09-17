<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--dzięki poniższej linii możliwe jest wywoływanie funkcji/tagów html z jstl--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--dzięki lini poniżej możliwe jest wykonywanie dyrektyw z użyciem zmiennych adresując je przez ${}--%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Student details</title>
</head>
<body>
<h1>Detailed information about a student with id: <c:out value="${requestScope.studentDetails.id}"/></h1>
<jsp:include page="/menu.jsp"/>

<br/>
<table>
    <tr>
        <td>Imie:</td>
        <td><c:out value="${requestScope.studentDetails.imie}"/></td>
    </tr>
    <tr>
        <td>Nazwisko:</td>
        <td><c:out value="${requestScope.studentDetails.nazwisko}"/></td>
    </tr>
    <tr>
        <td>Wzrost:</td>
        <td><c:out value="${requestScope.studentDetails.wzrost}"/></td>
    </tr>
    <tr>
        <td>Pełnoletni:</td>
        <td><c:out value="${requestScope.studentDetails.pelnoletni}"/></td>
    </tr>
</table>
<br/>

<%--Link do strony dodawania ocen - możemy przesłać dane o studencie.--%>
<a href="${pageContext.request.contextPath}/grade/add?studentId=<c:out value="${requestScope.studentDetails.id}"/>">Dodaj ocenę (temu studentowi)</a>

<table style="border: 1px solid">
    <thead>
    <tr>
        <td>Id</td>
        <td>Przedmiot</td>
        <td>Ocena</td>
        <td>Data dodania</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ocena" items="${requestScope.studentDetails.gradeList}">
        <tr>
            <td><c:out value="${ocena.id}"/></td>
            <td><c:out value="${ocena.przedmiot}"/></td>
            <td><c:out value="${ocena.ocena}"/></td>
            <td><c:out value="${ocena.dataDodania}"/></td>

                <%--Nowa treść--%>
            <td>
                <a href="${pageContext.request.contextPath}/grade/delete?gradeId=<c:out value="${ocena.id}"/>">Usun</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/grade/edit?gradeId=<c:out value="${ocena.id}"/>">Edytuj</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>