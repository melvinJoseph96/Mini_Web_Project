<%@page import="uk.ac.le.cs.CO3098.spring.repository.PersonRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link rel="stylesheet"
href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.cs
s">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src="<%=request.getContextPath() %>/js/jquery.js"></script>
 <script type="text/javascript">var contexPath = "<%=request.getContextPath() %>"</script>
 <script src="<%=request.getContextPath() %>/js/user.js"></script>
    <title>Create an account</title>
    <link type="text/css" href="/css/bootstrap.css" rel="stylesheet" />
    <script>
  
    
    
    
   
    
   
    </script>
</head>
<body>
<h2>Create new Person</h2>
<form action="/GE/save" method="post"> 
    <table class="table table-bordered">
        <tbody>
            <!--  <tr><th>id</th><td><input type="text" name="id" required="required"></td></tr>-->
            <tr><th>Name</th><td><input type="text" name="name" required="required"></td></tr>
            <tr><th>Mother Id</th><td><input type="text" name="m" ></td></tr>
            <tr><th>Father Id</th><td><input type="text" name="f" ></td></tr>
            <tr><th>Gender</th><td><input type="text" name="g" ></td></tr>
            <tr><th>Date Of Birth</th><td><input type="text" name="dob" ></td></tr>
            
           <tr><td colspan="2"><a href="./listAll" class="btn btn-primary">Back</a> <input type="submit" value="Add" = class="btn btn-success"></tr>
        </tbody>
    </table>
    
    
 </form>   


</body>
</html>