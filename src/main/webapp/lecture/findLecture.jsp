<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Lecture</title>
</head>
<body><br>
<a href="${prefix}/hiberversity/lecture/lectureIndex.jsp">Back</a><br><br>
Please enter lecture ID:<br>
<br>
<form action="${pageContext.request.contextPath}/findLecture">
	<input type="text" name="lectureId">
	<input type="submit">
</form>
</body>
</html>
