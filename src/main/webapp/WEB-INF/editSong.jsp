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
<title>Edit Music</title>
<link href="
https://cdn.jsdelivr.net/npm/bootswatch@5.3.7/dist/flatly/bootstrap.min.css
" rel="stylesheet">
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-primary">
			<div class="container-fluid">
				<a class="navbar-brand fs-1 text-success" href="/songs">Muse</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
					aria-controls="navbarNavAltMarkup" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-expand show text-end"
					id="navbarNavAltMarkup">
					<div class="navbar-nav">
						<a class="nav-link text-light" href="/songs">Home</a>
						 <a
							class="nav-link text-light" href="/songs/discover">Discover New Music</a> 
						<a class="nav-link active text-light" aria-current="page" href="/songs/new">Add
							a Song</a> <a class="nav-link text-light" href="/songs/discover">Discover
							New Music</a>
						<form action="/logout" method="post">
							<input class="nav-link text-light" type="submit" value="logout" />
						</form>
					</div>
				</div>
			</div>
		</nav>
			<h2 class="text-center bg-secondary mb-0 p-4">Edit <c:out value="${currentSong.name}"/></h2>

		<div class="container-fluid d-flex justify-content-center bg-secondary mt-0
					min-vh-100">
			<form:form action="/songs/${currentSong.id}/edit" method="post" modelAttribute="currentSong">
				<input type="hidden" name="_method" value="put">
				<p>
					<form:label path="name">Name:</form:label>
					<form:errors class="text-danger" path="name" />
					<form:input class="form-control form-control-lg w-100" path="name" />
				</p>
				<p>
					<form:label path="artist">Artist:</form:label>
					<form:errors class="text-danger" path="artist" />
					<form:input class="form-control form-control-lg w-100"
						path="artist" />
				</p>
				<p>
					<form:label path="album">Album:</form:label>
					<form:errors class="text-danger" path="album" />
					<form:input class="form-control form-control-lg w-100" path="album" />
				</p>
				<p>
					<form:label path="releaseYear">Release Year:</form:label>
					<form:errors class="text-danger" path="releaseYear" />
					<form:input class="form-control form-control-lg w-100"
						type="number" path="releaseYear" />
				</p>
				<p>
					<form:label path="genre">Genre:</form:label>
					<form:errors class="text-danger" path="genre" />
					<form:select path="genre" size="1" class="form-select w-100">
						<form:option value="" label="Select a Genre" />
						<form:options items="${genres}" />
					</form:select>
				</p>
				<button class="btn btn-success btn-lg">Submit</button>
				<a href="/songs" class="btn btn-primary btn-lg">Cancel</a>
			</form:form>
		</div>
	</div>
</body>
</html>