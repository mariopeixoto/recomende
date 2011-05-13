<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="styles/jquery/jquery-ui.css" rel="stylesheet" type="text/css">
	<link href="styles/profile/get.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="scripts/jquery.js" ></script>
	<script type="text/javascript" src="scripts/jquery-http.js" ></script>
	<script type="text/javascript" src="scripts/jquery-ui.js" ></script>
	<script type="text/javascript">
		$(".score div").each( function (i) {
			var id = $(this).attr("term");
			var input = selectInputScore(id);
			$(this).slider( {
				max: 1.0,
				value: $(input).val(),
				step: 0.01,
				animate: true,
				slide: function(event, ui) {
					$(input).val(ui.value);
				},
				change : function(event, ui) {
					updateTermWeight(id, ui.value);
				}
			} );
		} );

		$(".term button").button({
			icons: {
	            primary: 'ui-icon-trash'
	        },
	        text: false
	    } );

		$(".term button").click(
			function () {
				var term = $(this).attr("term");
				removeTerm(term);
			}
		);

		$(".newterm button").button({
			icons: {
	            primary: 'ui-icon-plusthick'
	        },
	        text: false
	    } );

		$(".newterm button").click(addTerm);
		
		var url = "profile";

		function selectInputScore(id) {
			return ".score input[term='" + id + "']";
		}

		function updateTermWeight(term, weight) {
			if (term != "newElement") {
				var data = { weight: weight };
				$.put(url + "/" + term, data);
			}
		}

		function removeTerm(term) {
			$.delete_(url + "/" + term);
			$("div#" + term).remove();
		}

		function addTerm() {
			var term = $("#newTerm-input").val();
			var weight = $(selectInputScore("newElement")).val();
			var data = { term: term, weight: weight };
			$.post(url, data, addNewTermToList);
		}

		function addNewTermToList(data) {
			var term = $("#newTerm-input").val();
			var div = $("#profile").prepend(data);

			var input = selectInputScore(term);
			$(".score div[term='"+ term + "']").slider( {
					max: 1.0,
					value: $(input).val(),
					step: 0.01,
					animate: true,
					slide: function (event, ui) {
						$(input).val(ui.value);
					},
					change: function (event, ui) {
						updateTermWeight(term, ui.value);
					}
			} );

			$(".term button[term='" + term + "']").button({
				icons: {
		            primary: 'ui-icon-trash'
		        },
		        text: false
		    } );

			$(".term button[term='" + term + "']").click(
				function () {
					var term = $(this).attr("term");
					removeTerm(term);
				}
			);

			cleanNewTerm();
			
		}

		function cleanNewTerm() {
			$("#newTerm-input").val('');
			$(".score div[term='newElement']").slider( { value: 0 } );
			$(selectInputSource("newElement")).val(0);
		}
	</script>
</head>
<body>
	<h3><fmt:message key="profile" /></h3>
	<div id="newterm">
		<div class="newterm">
			<button><fmt:message key="add"/></button>
			<input id="newTerm-input" type="text" />
			<div class="score small">
				<div term="newElement"></div>
				<input type ="text" term="newElement" value="0"/>
			</div>
		</div>
	</div>
	<div id="profile">
		<c:forEach items="${elements}" var="element">
			<div class="term" id="${element.key}">
				<button term="${element.key}" class="small"><fmt:message key="delete"/></button>
				<div class="description">${element.key}</div>
				<div class="score small">
					<div term="${element.key}"></div>
					<input type="text" term="${element.key}" value="${element.value}"/>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>