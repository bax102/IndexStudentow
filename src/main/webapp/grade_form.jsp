<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--dzięki poniższej linii możliwe jest wywoływanie funkcji/tagów html z jstl--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--dzięki lini poniżej możliwe jest wykonywanie dyrektyw z użyciem zmiennych adresując je przez ${}--%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Grade Form</title>
</head>
<body>
<h1>Formularz oceny dla studenta: <c:out value="${requestScope.student_identifier}"/></h1>
<jsp:include page="menu.jsp"/>

<br/>
<%--formularz zawiera 3 pola: ocena, przedmiot i identyfikator studenta któremu dodajemy ocenę. --%>
<%--gdy dodaje ocene, chce wysłać ją na servlet /grade/add--%>
<%--gdy edytuje ocene, chce wysłać ją na servlet /grade/edit--%>
<%--jeśli requestScope.gradeToEdit==null - to oznacza że nie edytuje oceny--%>
<form action="${pageContext.request.contextPath}${ requestScope.gradeToEdit == null ? '/grade/add' : '/grade/edit' }" method="post">
    <input type="hidden" name="studentGraded" value="<c:out value="${requestScope.student_identifier}"/>">
    <input type="hidden" name="editedGrade" value="${requestScope.gradeToEdit.id}">
    Przedmiot:
    <select name="przedmiot">
        <c:forEach var="przed" items="${requestScope.przedmioty}">
            <option value="${przed}" ${przed == requestScope.gradeToEdit.przedmiot ? 'selected': ''} >${przed.opis}</option>
        </c:forEach>
    </select>
    <br/>
    Ocena: <input type="number" step="0.5" min="1" max="6" name="ocena" value="${requestScope.gradeToEdit.ocena}"/>
    <br/>
    <input type="submit">
</form>
</body>
</html>
