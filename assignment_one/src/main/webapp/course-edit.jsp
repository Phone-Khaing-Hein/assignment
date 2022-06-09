<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Course</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container mt-4">
		<h1>Using IOC Container</h1>
		<h3>Add New Course</h3>
		
		<div class="row">
			<div class="col-4">
				<c:url var="save" value="/courses"></c:url>
				<form action="${ save }" method="post">
					<div>
						<label for="name" class="form-label">Course Name</label>
						<input type="text" name="name" id="name" class="form-control" required="required" />
					</div>
					<div class="mt-2">
						<label for="fees" class="form-label">Course Fees</label>
						<input type="number" name="fees" id="fees" class="form-control" required="required"/>
					</div>
					<div class="mt-2">
						<label for="duration" class="form-label">Course Duration</label>
						<input type="number" name="duration" id="duration" class="form-control" required="required"/>
					</div>
					<div class="mt-2">
						<label for="description" class="form-label">Course Description</label>
						<textarea name="description" id="description" cols="40" rows="4" class="form-control"></textarea>
					</div>
					<button type="submit" class="btn btn-primary mt-2 float float-end">Save Course</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>