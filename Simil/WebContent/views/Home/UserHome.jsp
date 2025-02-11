<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	try {
		if (request.getSession().getAttribute("username") == null) {

			response.sendRedirect("/Simil");

		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/Simil/supp/css/simil.css">
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simil</title>
</head>
<body>
	<div id="wrapper">
        <%@ include file="/supp/html/topNav.jsp" %>
		<%@ include file="/supp/html/sideMenu.jsp" %>

			<div id="content">
			<h1>Welcome, <%=session.getAttribute("username")%></h1><a class="link1" href="/Simil/views/Home/UserPage.jsp?user=<%=session.getAttribute("username")%>">View my page</a>

				<div class='section'>
					<h4>My Panels:</h4>
					<div id="panels">
						<ul id="panel-list"></ul>
					</div>
				</div>
				<div class='section'>
					<h4>My Interests:</h4>
					<div id="interests">
						<ul id="interest-list"></ul>
					</div>
				</div>
			</div>
		
	</div>
</body>
</html>
<script>
	$(document).ready(function() {
		$.ajax({
			type : "GET",
			url : "/Simil/UserHomeServlet",
			dataType : "json",
			success : function(data) {
				displayInfo(data)
			}
		});
	});

	function displayInfo(data) {
		var $interests = $('#interest-list');
		var $panels = $('#panel-list');
		var panelName;
		var panelID;
		console.log(data);
		console.log(data[0]);
		console.log(data[1]);
		console.log(data[2]);
		console.log(data[2][1]);
		for (var x = 0; x < data[0].length; x++) {
			var entry = "<li>" + data[0][x] + "</li>";
			$interests.append(entry);
		}
		for (var i = 0; i < data[1].length; i++) {
			panelName = data[1][i];
			panelID = data[2][i];
			var entry = "<li><a class='link1' href='/Simil/views/Panels/Panel.jsp?id=" + panelID + "&name=" + panelName +"'>" + panelName+ "</a></li>";
			$panels.append(entry);
		}
	}
	
	
	$(function(){
		var expanded = false;
		$('#sideMenu').click(function(){
			if (!expanded){
				  $(this).animate({'left' : '0px'}, {duration : 200});
			      expanded = true;
			}
			else{
			    $(this).animate({'left' : '-145px'}, {duration: 200});
			    expanded = false;
			}
		});
	});
</script>