<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- JSTL Tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<!-- To Display validation errors -->
<%@ page isErrorPage="true" %>   




<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Edit Name</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
	
		   <div class="container mt-5">
   		<h1> Edit a name!</h1>
		<form:form action="/names/${newBabyName.id }/edit" method="put" modelAttribute="newBabyName">
		<div class="form-control">
			<form:hidden path="creator" value="${newBabyName.creator.id }" />
			<form:label path="babyName">Name:</form:label>
			<form:input path="babyName" class="form-control" readOnly="true"/>
			<form:errors path="babyName" class="text-danger" />
		</div>	
		<div class="form-control">
		<form:label path="gender">Typical Gender:</form:label>
			<form:select path="gender">
					<form:option value="Male">Male</form:option>
					<form:option value="Female">Female</form:option>
					<form:option value="Neutral">Neutral</form:option>
			<form:errors path="gender" class="text-danger" />
			</form:select>
		</div>
		<div class="form-control">
			<form:label path="origin"> Origin:</form:label>
			<form:input path="origin" class="form-control" />
			<form:errors path="origin" class="text-danger" />
		</div>
		<div class="form-control">
			<form:label path="meaning"> Meaning:</form:label>
			<form:input path="meaning" class="form-control" />
			<form:errors path="meaning" class="text-danger" />
		</div>			
		<a href="/home" class="btn btn-warning btn-sm">Cancel</a>
		<button class="btn btn-success btn-sm">Submit</button>	
			</form:form>
									<c:choose>
   							<c:when test="${newBabyName.creator.id.equals(userId) }">
								
									<form action="/names/${newBabyName.id }/delete" method="post">
										<input type="hidden" name="_method" value="delete" />
										<button class="btn btn-danger btn-sm">Delete</button>
									</form>
							</c:when>
   							<c:otherwise>
   							
   							</c:otherwise>	
   						</c:choose>
   
   </div>
	
	</body>
</html>