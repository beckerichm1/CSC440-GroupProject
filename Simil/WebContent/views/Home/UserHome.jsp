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
		<div id="nav">
			<div id="logoDiv">
				<a id="logo" href="/Simil"><span id="logoSpan">Simil!</span></a>
			</div>

		</div>
		
		<div id="sideMenu">
            <div id="linkContainer">
                <ul>
                    <li><div class="menuItem"></div><a href="/Simil/views/Interests/Catalog.jsp">Interests</a></li>
                    <li><a href="/Simil/views/Panels/Catalog.jsp">Panels</a></li>
                    <%
                        //System.out.println("Account: " + request.getSession().getAttribute("accountType"));
                        if (request.getSession().getAttribute("accountType").equals("Administrator")) {
                    %>
                     <li><a href="/Simil/views/Dashboard/AdministratorTools.jsp">Administrator Tools</a></li>
                    <%
                        }
                    %>
                </ul>
            </div>
		</div>

			<div id="content">
			<%-- 	<h1>Welcome,
					<%=session.getAttribute("username")%></h1> --%>

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
				<form action="/Simil/LogoutController" method="POST">
					<button type="submit" value="Logout">Logout</button>
				</form>
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

		for (var x = 0; x < data[0].length; x++) {
			var entry = "<li>" + data[0][x] + "</li>";
			$interests.append(entry);
		}
		for (var i = 0; i < data[1].length; i++) {
			var entry = "<li>" + data[1][i] + "</li>";
			$panels.append(entry);
		}
	}
</script>