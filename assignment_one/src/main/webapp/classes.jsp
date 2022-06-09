<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Classes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container mt-4">
		<h1>Using IOC Container</h1>
		<h3>Classes for ${ course.name }</h3>
		<c:url var="addNew" value="class-edit">
			<c:param name="courseId" value="${ course.id }"></c:param>
		</c:url>
		<a href="${ addNew }" class="btn btn-primary">Add New Class</a>
		
		<div class="mt-4">
			
				<c:choose>
					<c:when test="${ empty classes }">
						<div class="alert alert-warning">
							There is no Class for ${ course.name }! Please Create New Class!
						</div>
					</c:when>
					<c:otherwise>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>ID</th>
									<th>Course</th>
									<th>Teacher</th>
									<th>Start Date</th>
									<th>Fees</th>
									<th>Duration</th>
									<th>Description</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ classes }" var="c">
									<tr>
										<td>${ c.id }</td>
										<td>${ c.course.name }</td>
										<td>${ c.teacher }</td>
										<td>${ c.startDate }</td>
										<td>${ c.course.fees }</td>
										<td>${ c.course.duration } months</td>
										<td>${ c.course.description }</td>
										<td>
											<c:url var="registrations" value="/registrations">
												<c:param name="classId" value="${ c.id }"></c:param>
											</c:url>
											<a href="${ registrations }">Registrations</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
		</div>
	</div>
</body>
</html>