<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   <div class="container mt-5">
   					<a href="/names/logout"
									class="btn btn-warning btn-sm">Logout</a><br><br>
   <h1> <c:out value="${newBabyName.babyName }"></c:out></h1>
  					 <h2>Created by: ${newBabyName.creator.name }</h2>
					<h3>Gender: ${newBabyName.gender }</h3>
					<h3>Origin: ${newBabyName.origin }</h3>
					<h4>Meaning: ${newBabyName.meaning }</h4> 
										<c:choose>
								<c:when test="${liked}">
									<h5>You liked this name!</h5>
								</c:when>
								<c:otherwise>
									<form action="/names/${newBabyName.id }/like" method="post">
										<input type="hidden" name="_method" value="put" />
										<button class="btn btn-success btn-sm">Like</button>
									</form>

								</c:otherwise>
								</c:choose>
					<br><br>

							<c:choose>
   							<c:when test="${newBabyName.creator.id.equals(userId) }">
								<a href="/names/${newBabyName.id }/edit"
									class="btn btn-success btn-sm">Edit</a>
											<a href="/home" class="btn btn-success btn-sm">Back to Homepage</a>
							</c:when>
   							<c:otherwise>
   							<a href="/home" class="btn btn-success btn-sm">Back to Homepage</a>
   							</c:otherwise>	
   						</c:choose>
		


   
   </div>
</body>
</html>