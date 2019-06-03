<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.cs
s">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"><
/script>
 <script
src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
</script>
<title>Insert title here</title>
<link type="text/css" href="/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<h2>List of People</h2>
	<table class="table table-bordered">
		<tr>
			<th>key</th>
			<th>name</th>
			<th>motherId</th>
			<th>fatherId</th>
			<th>gender</th>
			<th>dateOfBirth</th>

		</tr>
		<tbody>
			<c:forEach items="${people}" var="person" varStatus="itr">
				<tr>
					<td>${person.key}</td>
					<td>${person.name}</td>
					<td>${person.m}</td>
					<td>${person.f}</td>
					<td>${person.g}</td>
					<td>${person.dob}</td>
					<td><a href="/GE/edit/${person.key}" class="btn btn-info">Edit</a>
						<a href="/GE/person/delete2/${person.key}" class="btn btn-danger">Delete</a>
						<a href="/GE/person/ancestors/${person.key}" class="btn btn-info">Show
							Ancestors</a> <a href="/GE/person/decendants/${person.key}"
						class="btn btn-info">Show decendants</a></td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
	<a href="/GE/test" class="btn btn-info">Show table as a Family Tree</a>

	<a href="/GE/create" class="btn btn-primary">Add new Person</a>





</body>
</html>