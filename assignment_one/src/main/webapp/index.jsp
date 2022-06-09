<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Courses</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container mt-4">
		<h1>Using IOC Container</h1>
		<h3>Courses</h3>
		<c:url var="addNew" value="course-edit"></c:url>
		<a href="${ addNew }" class="btn btn-primary">Add New Course</a>
		
		<div class="mt-4">
			
				<c:choose>
					<c:when test="${ empty courses }">
						<div class="alert alert-warning">
							There is no courses! Please Create New Course!
						</div>
					</c:when>
					<c:otherwise>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>ID</th>
									<th>Name</th>
									<th>Duration</th>
									<th>Fees</th>
									<th>Description</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ courses }" var="course">
									<tr>
										<td>${ course.id }</td>
										<td>${ course.name }</td>
										<td>${ course.duration } months</td>
										<td>${ course.fees }</td>
										<td>${ course.description }</td>
										<td>
											<c:url var="classes" value="classes">
												<c:param name="courseId" value="${ course.id }"></c:param>
											</c:url>
											<a href="${ classes }">Open Classes</a>
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