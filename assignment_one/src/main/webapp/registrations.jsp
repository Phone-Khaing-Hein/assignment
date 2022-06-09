<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrations</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container mt-4">
		<h1>Using IOC Container</h1>
		<h3>Registrations for ${ openClass.course.name }</h3>
		<c:url var="addNew" value="registration-edit">
			<c:param name="classId" value="${ openClass.id }"></c:param>
		</c:url>
		<a href="${ addNew }" class="btn btn-primary">Add New Registration</a>
		
		<div class="mt-4">
			
				<c:choose>
					<c:when test="${ empty registrations }">
						<div class="alert alert-warning">
							There is no Registration for ${ openClass.course.name }! Please Create New Registration!
						</div>
					</c:when>
					<c:otherwise>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>ID</th>
									<th>Course</th>
									<th>Teacher</th>
									<th>Student</th>
									<th>Phone</th>
									<th>Email</th>
									<th>Start Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ registrations }" var="r">
									<tr>
										<td>${ r.id }</td>
										<td>${ r.openClass.course.name }</td>
										<td>${ r.openClass.teacher }</td>
										<td>${ r.student }</td>
										<td>${ r.phone }</td>
										<td>${ r.email } months</td>
										<td>${ r.openClass.startDate }</td>
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