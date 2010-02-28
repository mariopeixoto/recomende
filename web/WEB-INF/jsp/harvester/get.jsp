<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="styles/jquery/jquery-ui.css" rel="stylesheet" type="text/css">
	<link href="styles/harvester/get.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="scripts/jquery.js" ></script>
	<script type="text/javascript" src="scripts/jquery-http.js" ></script>
	<script type="text/javascript" src="scripts/jquery-ui.js" ></script>
	<script type="text/javascript">

		var url = "harvester";

		bindButtons();
	
		function bindButtons() {
			$(".harvester button").button(
				{
					icons: {
		            primary: 'ui-icon-trash'
		        },
		        text: false
	    	} );

			$(".harvester button").click( function () {
				var id = $(this).attr("hId");
				removeHarvester(id);
	    	} );

			$(".newharvester button").button(
				{
					icons: {
		            primary: 'ui-icon-plusthick'
		        },
		        text: false
	    	} );

			$(".newharvester button").click(addNewHarvester);
		}

		function removeHarvester(id) {
			$.delete_(url + "/" + id);
			$("div#" + id).remove();
		}

		function addNewHarvester() {
			var name = $("#name").val();
			var endPoint = $("#endpoint").val();
			var type = $("#type").val();
			var data = { name: name, endPoint: endPoint, type: type };
			$.post(url, data, addNewHarvesterToList);
		}

		function addNewHarvesterToList(data) {
			$("#harvesters").append(data);
			$("#name").val('');
			$("#endpoint").val('');
			$("#type option:first").attr("selected", "selected");

			$(".harvester button:last").button(
				{
					icons: {
		            primary: 'ui-icon-trash'
		        },
		        text: false
	    	} );

			$(".harvester button:last").click( function () {
				var id = $(this).attr("hId");
				removeHarvester(id);
	    	} );
			
		}
		
	</script>
</head>
<body>
	<h3><fmt:message key="harvesters" /></h3>
	<div id="harvesters">
		<div class="newharvester">
				<button><fmt:message key="add" /></button>
				<div>
					<label for="name"><fmt:message key="name" /></label>
					<input type="text" name="name" id="name"/>
				</div>
				<div>
					<label for="endpoint"><fmt:message key="endpoint" /></label>
					<input type="text" name="endpoint" id="endpoint"/>
				</div>
				<select id="type">
					<option><fmt:message key="select.harvester.type" /></option>
					<c:forEach items="${types}" var="type">
						<option value="${type}">${type}</option>
					</c:forEach>
				</select>
			</div>
		<c:forEach items="${harvesters}" var="harvester">
			<div class="harvester" id="${harvester.id}">
				<button hId="${harvester.id}"><fmt:message key="delete" /></button>
				<div>${harvester.name}</div>
				<input type="text" value="${harvester.endPoint}" name="endpoint"/>
				<select>
					<c:forEach items="${types}" var="type">
						<c:choose>
							<c:when test="${harvester.harvesterType eq type}">
								<option value="${type}" selected="selected">${type}</option>
							</c:when>
							<c:otherwise>
								<option value="${type}">${type}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>		
		</c:forEach>
	</div>
</body>
</html>