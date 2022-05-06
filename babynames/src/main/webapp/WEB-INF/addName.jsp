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
		<title>Add a new name</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
	
		   <div class="container mt-5">
   		<h1> Add a name!</h1>
		<form:form action="/names/new" method="post" modelAttribute="newBabyName">
		<form:hidden path="creator" value="${userId }" />
		<div class="form-control">
			<form:label path="babyName">New Name:</form:label>
			<form:input path="babyName" id="babyName" class="form-control" />
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
		<button class="btn btn-success btn-sm"> Add Name</button>	
		</form:form>
   
   </div>
	
	</body>
</html>