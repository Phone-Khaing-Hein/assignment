<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Class</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container mt-4">
		<h1>Using IOC Container</h1>
		<h3>Add New Registration</h3>
		
		<div class="row">
			<div class="col-4">
				<c:url var="save" value="/registrations">
					<c:param name="classId" value="${ param.classId }"></c:param>
				</c:url>
				<form action="${ save }" method="post">
					<div>
						<label for="student" class="form-label">Student</label>
						<input type="text" name="student" id="student" class="form-control" required="required"/>
					</div>
					<div class="mt-2">
						<label for="phone" class="form-label">Phone</label>
						<input type="text" name="phone" id="phone" class="form-control" required="required"/>
					</div>
					<div class="mt-2">
						<label for="email" class="form-label">Email</label>
						<input type="email" name="email" id="email" class="form-control" required="required"/>
					</div>
					<button type="submit" class="btn btn-primary mt-2 float float-end">Save Registration</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>