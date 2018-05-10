<!DOCTYPE html>
<html>
	<head>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class = "container">
			<h2>Listado de farmacias</h2>

			<c:if test="${not empty farmacias}">
				<table border="1">
					<tr>
						<td>Nombre</td>
						<td>Direccion</td>
						<td>Telefono</td>
						<td>Barrio</td>
						<td>Dia de turno</td>
					</tr>
					<c:forEach var="farmacia" items="${farmacias}">
	            		<tr>
	            			<td>${farmacia.nombre}</td>
	            			<td>${farmacia.direccion.calle} ${farmacia.direccion.numero}</td>
	            			<td>${farmacia.telefono}</td>
	            			<td>${farmacia.direccion.barrio.nombre}</td>
	            			<td>${farmacia.diaDeTurno}</td>
	            		</tr>
	        		</c:forEach>
				</table>
	        </c:if>
			
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>