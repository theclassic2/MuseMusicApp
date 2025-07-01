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
<title>Muse - Music Discovery</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<a class="navbar-brand fs-1" href="#">Muse</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
					aria-controls="navbarNavAltMarkup" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-expand show text-end"
					id="navbarNavAltMarkup">
					<div class="navbar-nav">
						<a class="nav-link" href="/songs">Home</a> 
						<a class="nav-link" aria-current="page" href="/songs/curated">My Added Songs</a>
						<a class="nav-link" href="/songs/new">Add a Song</a> 
						<a class="nav-link active" aria-current="page" href="/songs/discover">Discover New Music</a> 
						<form action="/logout" method="post">
							<input class="nav-link" type="submit" value="logout" />
						</form>
					</div>
				</div>
			</div>
		</nav>
		<div>
			<h2 class="text-center display-4">All Music</h2>
		</div>
		<div class="container">
			<table class="table table-striped table-hover w-75">
				<thead>
					<tr>
						<th scope="col">Song</th>
						<th scope="col">Artist</th>
						<th scope="col">Album</th>
						<th scope="col">Added By</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="oneSong" items="${ allSongs }">
						<tr>
							<td><c:out value="${oneSong.name}" /></td>
							<td><c:out value="${oneSong.artist}" /></td>
							<td><c:out value="${oneSong.album}" /></td>
							<td>
								<a href="/songs/${oneSong.creator.id}/profile">
									<c:out value="${oneSong.creator.userName}" />
								</a>
							</td>
							<td>
								<a href="/songs/${oneSong.id}">View</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>