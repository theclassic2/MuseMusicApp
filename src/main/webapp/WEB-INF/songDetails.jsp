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
<title>${currentSong.name}</title>
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
						<a class="nav-link text-light" href="/songs">Home</a> <a class="nav-link text-light"
							aria-current="page" href="/songs/curated">My Added Songs</a> <a
							class="nav-link text-light" href="/songs/new">Add a Song</a> <a
							class="nav-link text-light" href="/songs/discover">Discover New Music</a>
						<form action="/logout" method="post">
							<input class="nav-link text-light" type="submit" value="logout" />
						</form>
					</div>
				</div>
			</div>
		</nav>
		<div class="row bg-secondary mx-auto fs-3 p-5 min-vh-100">
			<div class="col mt-5">
			<p>
				Song Name:
				<c:out value="${currentSong.name}" />
			</p>
			<p>
				Artist:
				<c:out value="${currentSong.artist}" />
			</p>
			<p>
				ALbum:
				<c:out value="${currentSong.album}" />
			</p>
			<p>
				Release Year:
				<c:out value="${currentSong.releaseYear}" />
			</p>
			<p>
				Genre:
				<c:out value="${currentSong.genre}" />
			</p>
			<p>
				Added by:
				<c:out value="${currentSong.creator.userName}" />
			</p>
						<c:if test="${userID.equals(currentSong.creator.id) }">
				<a class="btn btn-success btn-lg"
					href="/songs/${currentSong.id}/edit">Edit Song</a>
				<form action="/songs/${currentSong.id}/delete" method="post">
					<input type="hidden" name="_method" value="delete"> <input
						class="btn btn-danger btn-lg mt-4" type="submit"
						value="Delete Song">
				</form>
			</c:if>
			</div>
			<div class="col mt-5">
			<p>Liked By:</p>
			<ul class="list-group">
				<c:forEach var="likedUser" items="${currentSong.likedUsers}">
					<li class="list-group-item bg-light"><c:out value="${likedUser.userName}" /></li>
				</c:forEach>
			</ul>
			<c:if test="${!userID.equals(currentSong.creator.id) }">
				<c:choose>
					<c:when test="${currentSong.likedUsers.contains(currentUser)}">
						<form action="/songs/${currentSong.id}/unlikeSong" method="POST">
							<input type="hidden" name="_method" value="delete"> <input
								class="btn btn-danger" type="submit" value="Remove Like">
						</form>
					</c:when>
					<c:otherwise>
						<form action="/songs/${currentSong.id}/likeSong" method="POST">
							<input class="btn btn-success" type="submit" value="Like Song">
						</form>
					</c:otherwise>
				</c:choose>
			</c:if>
				
				</div>
			</div>
			
		</div>
</body>
</html>