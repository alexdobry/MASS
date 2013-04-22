<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<script type="text/javascript" src="js/mass.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MASS - Mobile Advanced Shopping Service</title>
</head>
<body>

	<div class="container well">
		<h1>Willkommen bei MASS</h1>
		<h2>Hier können Angebote verfasst werden</h2>

		<form action="/" method="POST" class="form-horizontal">
			<div class="control-group">
				<label class="control-label" for="productname">Produktname: </label>
				<div class="controls"> 
					<input type="text" id="productname" name="productname"> 
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="oldprice">Alter Preis:</label>
				<div class="controls"> 
					<input type="text" id="oldprice" name="oldprice"> 
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="price">Neuer Preis:</label>
				<div class="controls"> 
					<input type="text" id="price" name="price"> 
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="date">Aktionsende:</label>
				<div class="controls"> 
					<input type="date" id="date" name="date">  
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="category">Kategory:</label>
				<div class="controls"> 
					<select id="category" name="category">
						<c:forEach items="${categories}" var="category">
							<option value="${category}">${category}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="subCategory">Unter Kategorie</label>
				<div class="controls">
					<select id="subCategory" name="subCategory"></select>
				</div>
			</div>
			<div class="control-group">	
				<label class="control-label" for="dealer">Händler:</label>
				<div class="controls">
					<select id="dealer" name="dealer"></select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="brand">Marke:</label>
				<div class="controls">
					<select id="brand" name="brand">
<!-- 						<option value="brand">samsung</option>
						<option value="brand">lg</option>
						<option value="brand">sony</option>
						<option value="brand">toschiba</option> -->
					</select>
				</div>
			</div>

			<div id="criteria">
<!-- 				<div class="control-group">
					<label class="control-label" for="criteria1">zoll:</label>
					<div class="controls"> 
						<input type="text" id="criteria1" name="criteria1"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="criteria2">hz:</label>
					<div class="controls"> 
						<input type="text" id="criteria2" name="criteria2"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="criteria3">typ:</label>
					<div class="controls"> 
						<input type="text" id="criteria3" name="criteria3"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="criteria4">hd:</label>
					<div class="controls"> 
						<input type="text" id="criteria4" name="criteria4"> 
					</div>
				</div> -->
			</div>
			
			<input type="submit" value="Angebot erstellen">
		</form>
	</div>

</body>
</html>