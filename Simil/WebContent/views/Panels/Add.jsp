<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	// begin admin authentication
	if ((request.getSession().getAttribute("username") != null)) {
		if (request.getSession().getAttribute("accountType").equals("Administrator")) {
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/Simil/supp/css/simil.css">
<title>Simil</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
	$(function() {
		$("#accordion").accordion();
	});
</script>

</head>
<body>
	<div id="wrapper">
		<div id="headerText">
			<h1>Add a Panel</h1>
		</div>
		<div id="newPanelForm">
			<form onsubmit="validatePanelForm()" method="post">
				<div class="formElement">
					<label>Panel Name: </label> <input type="text" name="name" required>
					<br>
				</div>
				<div class="formElement">
					<label>Panel Description: </label> <input type="text"
						name="description" required> <br>
				</div>
				<!-- Check boxes for related panels and panel moderators -->
				<div id="accordion">
					<h3>Related Panels</h3>
					<div id="orderedList"
						style="columns: 5 50px; -moz-columns: 5 50px; -webkit-columns: 5 50px">
						<ul id="panelList" style="list-style: none">

						</ul>
					</div>
				</div>
				<input type="submit">
			</form>
		</div>
		<a href="/Simil">Go Back</a>
	</div>
</body>
</html>

<script>
	$(document).ready(function() {
		getPanels();
	});

	function addPanel(name, description, related, moderators) {
		// Make the ajax call to insert a new panel into database
		var url = "/Simil/PanelServlet";
		$.ajax({
			url : url,
			type : "POST",
			data : {
				"name" : name,
				"description" : description,
				"related" : related,
				"moderators" : moderators
			},
			success : function() {
				//TODO: Redirect to admin tools, needs to be tested
				response.sendRedirect("/Simil/Dashboard/AdministratorTools")
			},
			error : function(err) {
				console.log(err);
			}
		});
	}

	function getPanels() {
		// Pass in a piece of data so it knows to get all panels?
		var url = "/Simil/PanelServlet";
		$.ajax({
			url : url,
			datatype : 'json',
			data: {"param": 'all'},
			success : function(data) {
				populatePanels(data)
			},
			error : function() {
				alert('error');
			}
		});
	}

	function populatePanels(data) {
		var $div = $('#panelList');
		for (var i = 0; i < data.length; i++) {
			console.log(data[i]);
			var name = data[i][0];
			var desc = data[i][1];
			var entry = "<li><input type = 'checkbox' value = '" + name + "'><div>"
					+ name + "/n" + desc + "</div></li>";
			$div.append(entry);
		}
	}

	function getRelatedPanels() {
		var panels[];
		$(":checked").each(function() {
			panels.push($(this).val());
		});	//name, description, related, moderators
		addPanel(document.forms[0].elements[0].value, document.forms[0].elements[1].value,
				panels, document.forms[0].elements[3].value);
	}

	function validatePanelForm() {
		var alertBool = false;
		var message = "";
		var name = document.forms[0].elements[0].value;
		var desc = document.forms[0].elements[1].value;
		//var related = document.forms[0].elements[2].value;
		var mods = document.forms[0].elements[3].value;

		if (name == null || name == "") {
			message += "Panel Name is a required field.\n";
			alertBool = true;
		}
		if (desc == null || desc == "") {
			message += "Panel Description is a required field.\n";
			alertBool = true;
		}
		/* if (related == null || related == "") {
			message += "Birth Date is a required field.\n";
			alertBool = true;
		} */
		if (mods == null || mods == "") {
			// Set current user as default mod if none selected
			// Might have to do something different here, to make mods an array?
			mods = request.getSession().getAttribute("username");
		}
		if (alertBool) {
			alert(message);
			return alertBool;
		}
		getRelatedPanels();
	}
</script>

<%
	// End the admin authentication
		} else {
			response.sendError(403, "Forbidden");
		}
	} else {
		response.sendError(403, "Forbidden");
	}
%>
