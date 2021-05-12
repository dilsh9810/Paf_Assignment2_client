<%@page import="Com.category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/category.js"></script>

</head>
<body>

<div class="container"><div class="row"><div class="col-6"> 

	<h1>Category Management</h1>

	<form id="formCategory" name="formCategory">
 		Category ID: 
 		<input id="categoryId" name="categoryId" type="text" class="form-control form-control-sm">
 
		 <br>Category name: 
 		 <input id="categoryName" name="categoryName" type="text" class="form-control form-control-sm">
 
 		 <br> Category Description: 
		 <input id="categoryDesc" name="categoryDesc" type="text" class="form-control form-control-sm">
 
 		 <br>Tag Code: 
 		 <input id="tagCode" name="tagCode" type="text" class="form-control form-control-sm">
 
 		 <br>Tag Name:
 		 <input id="tagName" name="tagName" type="text" class="form-control form-control-sm">
 		 
 		 <br>
 		 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 
 		 <input type="hidden" id="hidIDSave" name="hidIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	
	<div id="divCategoryGrid">
		<% 
			category catObj = new category();
			out.print(catObj.readCategories());
		%>
	</div>
	
	
</div></div></div>

</body>
</html>