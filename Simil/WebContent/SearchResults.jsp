<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ include file="/supp/html/RequiredLoginHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/Simil/supp/css/simil.css">
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>
	<div id="wrapper">
		<%@ include file="/supp/html/topNav.jsp"%>
		<%@ include file="/supp/html/sideMenu.jsp"%>

		<div id="content">
			<h1>Search Results for ${param.search}</h1>
			<div id="userSearchResults">
            </div>
            <div id="panelSearchResults">
            </div>
		</div>

	</div>
</body>
<script>
	$(document).ready(function() {
		var user = '${param.search}';
		var url = "/Simil/AccountServlet";
		$.ajax({
			type : "GET",
			url : url,
			data : {
				"param": "search",
				"id" : user
			},
			success : function(entryData) {
				// Print the results on page as links to user profiles.
                var $userResultDiv= $('#userSearchResults');
                var $panelResultDiv= $('#panelSearchResults');
				var row;
				
			    for(var i = 0; i < entryData.length; i++){
			    	row = "<div id = 'searchResultElement'><a href='/Simil/views/Home/UserPage.jsp?user="+ entryData[i] +"'>" + entryData[i] + "</a><div>"
			         $userResultDiv.append(row);
			    }
			},
			error : function(data) {
				console.log(data);
			}
		});

	});
</script>
</html>