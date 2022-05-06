

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Joy Bundler Names</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
	<h1>Joy Bundler Names</h1>
	
<div class="container">
  <div class="row">
    <div class="col-sm-4">
<div class="register">
		<h3>Register</h3>
		<form:form action="/register" method="post" modelAttribute="newUser">
			<div class="form-control">
				<form:label path="name">Name:</form:label>
				<form:input path="name" class="form-control" />
				<form:errors path="name" class="text-danger" />
			</div>
			<div class="form-control">
				<form:label path="email">Email:</form:label>
				<form:input path="email" class="form-control" />
				<form:errors path="email" class="text-danger" />
			</div>
			<div class="form-control">
				<form:label path="password">Password:</form:label>
				<form:password path="password" class="form-control" />
				<form:errors path="password" class="text-danger" />
			</div>
			<div class="form-control">
				<form:label path="confirm">Confirm Password:</form:label>
				<form:password path="confirm" class="form-control" />
				<form:errors path="confirm" class="text-danger" />
			</div>
			<input type="submit" value="Register" class="btn btn-success btn-sm" />
		</form:form>
		</div>
    </div>
    <div class="col-sm-4">
<div class="login">
		<h3>Login</h3>
		<form:form action="/login" method="post" modelAttribute="newLogin">

			<div class="form-control">
				<form:label path="email">Email:</form:label>
				<form:input path="email" class="form-control" />
				<form:errors path="email" class="text-danger" />
			</div>
			<div class="form-control">
				<form:label path="password">Password:</form:label>
				<form:password path="password" class="form-control" />
				<form:errors path="password" class="text-danger" />
			</div>

			<input type="submit" value="Login" class="btn btn-success btn-sm" />
		</form:form>
		</div>
    </div>
    </div>
</div>
</body>
</html>