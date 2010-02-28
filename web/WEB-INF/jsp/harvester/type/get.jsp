<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="styles/jquery/jquery-ui.css" rel="stylesheet" type="text/css">
	<link href="styles/harvester/type/get.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="scripts/jquery.js" ></script>
	<script type="text/javascript" src="scripts/jquery-http.js" ></script>
	<script type="text/javascript" src="scripts/jquery-ui.js" ></script>
	<script type="text/javascript">

		var url = "harvester/type";

		bindButtons();
	
		function bindButtons() {
			$(".type button").button(
				{
					icons: {
		            primary: 'ui-icon-trash'
		        },
		        text: false
	    	} );

			$(".type button").click( function () {
				var id = $(this).attr("hId");
				removeType(id);
	    	} );

			$(".newtype button").button(
				{
					icons: {
		            primary: 'ui-icon-plusthick'
		        },
		        text: false
	    	} );

			$(".newtype button").click(addNewType);
		}

		function removeType(id) {
			$.delete_(url + "/" + id);
			$("div#" + id).remove();
		}

		function addNewType() {
			var name = $("#name").val();
			var clazz = $("#clazz").val();
			var data = { name: name, clazz: clazz };
			$.post(url, data, addNewTypeToList);
		}

		function addNewTypeToList(data) {
			$("#types").append(data);
			$("#name").val('');
			$("#clazz option:first").attr("selected", "selected");

			$(".type button:last").button(
				{
					icons: {
		            primary: 'ui-icon-trash'
		        },
		        text: false
	    	} );

			$(".type button:last").click( function () {
				var id = $(this).attr("hId");
				removeType(id);
	    	} );
			
		}
		
	</script>
</head>
<body>
	<h3><fmt:message key="harvester.types" /></h3>
	<div id="types">
		<div class="newtype">
				<button><fmt:message key="add" /></button>
				<div>
					<label for="name"><fmt:message key="name" /></label>
					<input type="text" name="name" id="name"/>
				</div>
				<select id="clazz">
					<option><fmt:message key="select.class" /></option>
					<c:forEach items="${classes}" var="clazz">
						<option value="${clazz.name}">${clazz}</option>
					</c:forEach>
				</select>
			</div>
		<c:forEach items="${types}" var="type">
			<div class="type" id="${type.type}">
				<button hId="${type.type}"><fmt:message key="delete" /></button>
				<div>${type.type}</div>
				<select>
					<c:forEach items="${classes}" var="clazz">
						<c:choose>
							<c:when test="${type.harvesterClass eq clazz}">
								<option value="${clazz.name}" selected="selected">${clazz}</option>
							</c:when>
							<c:otherwise>
								<option value="${clazz.name}">${clazz}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>		
		</c:forEach>
	</div>
</body>
</html>