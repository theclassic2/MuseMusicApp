<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muse - Music Discovery Starts Here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1 class="text-center">Muse</h1>
		<h2 class="text-center fw-light fst-italic">Music Discovery Starts Here</h2>
		<div class="m-2 p-2 mx-auto" style="width: 900px;">
			<div class="d-flex mx-auto my-4 justify-content-between">
				<div class="border border-dark p-3 col-5 bg-light">
					<h2>Register</h2>
					<form:form action="/register" method="post"
						modelAttribute="newUser">
						<p>
							<form:label path="userName">User Name:</form:label>
							<form:errors class="text-danger" path="userName" />
							<form:input class="form-control form-control-lg w-75"
								path="userName" />
						</p>
						<p>
							<form:label path="email">Email:</form:label>
							<form:errors class="text-danger" path="email" />
							<form:input class="form-control form-control-lg w-75"
								path="email" />
						</p>
						<p>
							<form:label path="password">Password:</form:label>
							<form:errors class="text-danger" path="password" />
							<form:input class="form-control form-control-lg w-75"
								type="password" path="password" />
						</p>
						<p>
							<form:label path="confirm">Confirm Password:</form:label>
							<form:errors class="text-danger" path="confirm" />
							<form:input class="form-control form-control-lg w-75"
								type="password" path="confirm" />
						</p>
						<button class="btn btn-primary btn-lg">Register</button>
					</form:form>
				</div>
				<div class="border border-dark p-3 col-5 bg-light">
					<h2 class="caution">Login</h2>
					<form:form action="/login" method="post"
						modelAttribute="loginUser">
						<p>
							<form:label path="loginEmail">Email:</form:label>
							<form:errors class="text-danger" path="loginEmail" />
							<form:input class="form-control form-control-lg w-75"
								path="loginEmail" />
						</p>
						<p>
							<form:label path="loginPassword">Password:</form:label>
							<form:errors class="text-danger" path="loginPassword" />
							<form:input class="form-control form-control-lg w-75"
								type="password" path="loginPassword" />
						</p>
						<button class="btn btn-primary btn-lg">Login</button>
					</form:form>
				</div> 
			</div>
		</div>
	</div>
</body>
</html>