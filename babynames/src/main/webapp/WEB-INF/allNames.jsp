<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- JSTL Tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isErrorPage="true"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Baby Names</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="jumbotron text-center">
	<h1>
		<%
String name=(String)session.getAttribute("name");
out.print("Hello, " + name + " here are some name suggestions");
%>
</h1>
</div>
	
	<br>
	<br>
	<h2>Baby Names</h2>
	<br>

	<table class="table">
			<thead>
				<tr>
					<th></th>
					<th>Name</th>
					<th>Typical Gender</th>
					<th>Origin</th>
					<th>Number of likes</th>
				</tr>
			</thead>
		<tbody>
			<c:forEach var="babyName" items="${babyNames }">
			
				<tr>
					<td>						<c:choose>
								<c:when test="${ babyName.likedUsers.contains(user)}">
									<form action="/names/${babyName.id }/unlike" method="post">
										<input type="hidden" name="_method" value="put" />
										<button class="btn btn-danger btn-sm">Unlike</button>
									</form>
								</c:when>
								<c:otherwise>
									<form action="/names/${babyName.id }/like" method="post">
										<input type="hidden" name="_method" value="put" />
										<button class="btn btn-success btn-sm">Like</button>
									</form>

								</c:otherwise>
							</c:choose> </td>
					<td><a href="/names/${babyName.id }"><c:out
								value="${babyName.babyName }"></c:out></a></td>
					<td>${babyName.gender }</td>
					<td>${babyName.origin }</td>
					<td> ${babyName.likedUsers.size()}</td>
					</tr>
						<tr>	<td>
							${gift.likedUsers.size()}</td>

			</c:forEach>
		</tbody>
	</table>
	<a href="/names/new" class="btn btn-success btn-sm">Add a new name</a>
	<a href="/logout" class="btn btn-danger btn-sm">Logout</a>
</body>
</html>