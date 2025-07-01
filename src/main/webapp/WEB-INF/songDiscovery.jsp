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
<link
	href="
https://cdn.jsdelivr.net/npm/bootswatch@5.3.7/dist/flatly/bootstrap.min.css
"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-primary">
			<div class="container-fluid">
				<a class="navbar-brand fs-1 text-success" href="/songs ">Muse</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
					aria-controls="navbarNavAltMarkup" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-expand show text-end"
					id="navbarNavAltMarkup">
					<div class="navbar-nav">
						<a class="nav-link text-light" href="/songs">Home</a> <a
							class="nav-link text-light" aria-current="page"
							href="/songs/curated">My Added Songs</a> <a
							class="nav-link text-light" href="/songs/new">Add a Song</a> <a
							class="nav-link active text-success" aria-current="page"
							href="/songs/discover">Discover New Music</a>
						<form action="/logout" method="post">
							<input class="nav-link text-light link-danger-hover"
								type="submit" value="logout" />
						</form>
					</div>
				</div>
			</div>
		</nav>
		<div class="container min-vh-100 bg-secondary mt-0">
			<h2 class="text-center display-4 p-4 mb-0">All Music</h2>
			<div class="bg-secondary px-5 py-4">
				<form method="get" action="/songs/discover/genre">
					<select class="form-select w-25 py-1" name="genre" size="1"
						onchange="this.form.submit()">
						<option value="">-- Search By Genre--</option>
						<c:forEach items="${genres}" var="genre">
							<option value="${genre}"
								${genre == selectedGenre ? 'selected' : ''}>${genre}</option>
						</c:forEach>
					</select>
				</form>
			</div>
			<div class="d-flex justify-content-center">
				<table class="table table-striped table-hover table-light table-borderless w-75">
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
								<td><a href="/songs/${oneSong.creator.id}/profile"> <c:out
											value="${oneSong.creator.userName}" />
								</a></td>
								<td><a href="/songs/${oneSong.id}">View</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>