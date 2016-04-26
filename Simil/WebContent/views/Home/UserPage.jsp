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
		<%@ include file="/supp/html/topNav.jsp"%>
		<%@ include file="/supp/html/sideMenu.jsp"%>


		<div id="content">
			<div id="profileDiv">
				<div id="userImage">
					<img src="/Simil/supp/imgs/manatee.jpeg">
				</div>
				<div id="userName">${param.user}</div>
				<div id="bioDiv">
					<h3>Bio</h3>

				</div>
				<div id="userPanelDiv">
					<h3>Panels</h3>

				</div>
				<div id="userBuddiesDiv">
					<h3>Buddies</h3>

				</div>
				<div id="userInterestDiv">
					<h3>Interests</h3>

				</div>


			</div>

		</div>

	</div>
</body>
<script>
	$(document).ready(function() {
		var username = '${param.user}';
		var url = "/Simil/AccountServlet";

		// Get string related to the account (bio, interests, panels)
		$.ajax({
			type : "GET",
			url : url,
			data : {
				"id" : username,
				"param" : "info"
			},
			success : function(data) {
				console.log(data);
				// userPanelDiv  userBuddiesDiv  userInterestDiv
				// Print the results on page, some as links.
				var bio = data[0];
				var $div = $('#bioDiv');
				var entry = "<p>" + bio + "</p>";
				$div.append(entry);

				var interests = data[1][0].split("_");
				$div = $('#userInterestDiv');
				for ( var x in interests) {
					entry = "<p>" + interests[x] + "</p>";
					$div.append(entry);
				}

				$div = $('#userPanelDiv');
				var panelName;
				var panelID;
				console.log("Data size is " + data.length);
				for ( var i = 0; i < data[2].length; i++) {
					   
						panelName = data[2][i];
						panelID = data[3][i];
						entry = "<a class='link3' href='/Simil/views/Panels/Panel.jsp?id=" 
								+ panelID + "&name=" + panelName +"'>" + panelName + "</a></br>";
						$div.append(entry);
				}
			},
			error : function(data) {
				console.log(data);
			}
		});

		// Get Buddies
		$.ajax({
			type : "GET",
			url : url,
			data : {
				"id" : username,
				"param" : "buddies"
			},
			success : function(data) {
				var $div = $('#userBuddiesDiv');
				for(var x in data){
					var friend = data[x];
					$div.append("<a class='link3' href='/Simil/views/Home/UserPage.jsp?user=" + friend + "'>" 
							+ friend + "</a></br>");
				}
			},
			error : function(data) {
				console.log(data);
			}
		});

	});
</script>
</html>