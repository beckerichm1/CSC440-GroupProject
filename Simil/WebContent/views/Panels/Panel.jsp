<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interests Catalog</title>
</head>

<script>
	$(document).ready(function() {
		getPanelDetails(<%request.getParameter("id");%>);
		console.log("Getting details for panelID: " + "<%request.getParameter("id");%>");
	});

	function getPanelDetails(id) {
		console.log("Getting details for panelID: " + id);
		var url = "/Simil/PanelServlet";
		$.ajax({
			url : url,
			datatype : 'json',
			type: "GET",
			data : {
				param: "one",
				id : id
			},
			success : function(data) {
				generatePage(data)
			},
			error : function() {
				alert('error');
			}
		});
	}

	function generatePage(data) {
		alert(data);
	}
</script>